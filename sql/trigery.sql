//wyzwalacz działający po dokonaniu rezerwacji(insercie do tabeli zamowienie)
CREATE OR REPLACE FUNCTION platnosc_zamowienie() RETURNS TRIGGER AS $$
DECLARE
    cena NUMERIC;
    liczba_dni INT;
    rec RECORD;
BEGIN
    liczba_dni := projektbd.liczba_dni(NEW.data_przyjazdu, NEW.data_odjazdu);
    SELECT ilosc_miejsc, cena_od_osoby INTO rec FROM projektbd.pokojeView WHERE pokoj_id = NEW.pokoj_id;
    cena := liczba_dni*rec.cena_od_osoby*(NEW.liczba_doroslych + 0.5*NEW.liczba_dzieci);
    RAISE NOTICE 'PLATNOSC REZERWACJA: %', cena;
    RAISE NOTICE 'LICZBA DNI: %', liczba_dni;
    INSERT INTO projektbd.rachunek("rachunek_id", "zamowienie_id", "kwota","stan") VALUES(NEW.zamowienie_id+3, NEW.zamowienie_id, cena, 0);
    INSERT INTO projektbd.platnosc("platnosc_id","czy_dokonano","rachunek_id") VALUES(NEW.zamowienie_id+15,'False',NEW.zamowienie_id+3);
    RETURN NEW;
END;
$$LANGUAGE 'plpgsql';

CREATE TRIGGER trigger_platnosc_zamowienie AFTER INSERT ON projektbd.zamowienie FOR EACH ROW EXECUTE PROCEDURE platnosc_zamowienie();

//wyzwalacz dodajacy do rachunku kwote należną za usługi dodatkowe

CREATE OR REPLACE FUNCTION platnosc_uslugi() RETURNS TRIGGER AS $$
DECLARE
    rec RECORD;
    r_id INT;
    dodatkowa_kwota NUMERIC;
BEGIN
    r_id := projektbd.zamowienieIndex();
    SELECT liczba_doroslych, liczba_dzieci INTO rec FROM projektbd.zamowienie WHERE zamowienie_id = r_id;
    dodatkowa_kwota := 25*(rec.liczba_doroslych + rec.liczba_dzieci);
    RAISE NOTICE 'CENA USLUGI: %', dodatkowa_kwota;
    UPDATE projektbd.rachunek SET kwota = kwota + dodatkowa_kwota WHERE zamowienie_id = r_id;
    RETURN NEW;
END;
$$LANGUAGE 'plpgsql';

CREATE TRIGGER trigger_platnosc_uslugi AFTER INSERT ON projektbd.uslugi FOR EACH ROW EXECUTE PROCEDURE platnosc_uslugi();