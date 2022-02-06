//sprawdza czy uzytkownik posiada konto w bazie o podanym loginie i hasle

CREATE OR REPLACE FUNCTION czyZalogowany(log TEXT, pass TEXT) RETURNS INT AS $$
DECLARE 
    id INT;
    rec RECORD;
BEGIN
    SELECT log_uzyt_id, haslo INTO rec FROM projektbd.danelogowania WHERE login = log;
    IF rec.haslo = pass THEN
        id := rec.log_uzyt_id;
    ELSE
        id := -1;
    END IF;
    RETURN id;
END;
$$LANGUAGE 'plpgsql';

//zwraca maksymalny(najwiekszy) indeks z obecnie zarejestrowanych klientów

CREATE OR REPLACE FUNCTION klientIndex() RETURNS int as $$
DECLARE
	x INT;
	myrec RECORD;
BEGIN
	SELECT MAX(log_uzyt_id) AS var INTO myrec FROM projektbd.danelogowania;
	x := myrec.var;
	RETURN x+2;
END;
$$LANGUAGE 'plpgsql';

//zwraca liste dostepnych dla uzytkownika pokoi które spełniają jego warunki

CREATE OR REPLACE FUNCTION zwroc_pokoje(liczba_osob INT, data1 DATE, data2 DATE, nazwa TEXT) 
RETURNS TABLE(id_pokoju INTEGER) as $$
DECLARE 
	kursor_pokoj CURSOR FOR SELECT * FROM projektbd.pokoj WHERE liczba_osob <= ilosc_miejsc AND hotel_nazwa=nazwa AND dostepny='True';
    	rec RECORD;
    	zapytanie TEXT;
    	temp_Var RECORD;
BEGIN
	OPEN kursor_pokoj;
	LOOP
		FETCH kursor_pokoj INTO rec;
          	EXIT WHEN NOT FOUND;

	 	zapytanie := 'SELECT pokoj_id FROM projektbd.zamowienie 
		WHERE
 	 	(pokoj_id = $1 AND (($2>data_przyjazdu AND $2<data_odjazdu) 
		OR ($3>data_przyjazdu AND $3<data_odjazdu) 
		OR (data_przyjazdu>$2 AND data_odjazdu<$3))) 
		LIMIT 1';
         	EXECUTE zapytanie INTO temp_Var USING rec.pokoj_id, data1, data2, nazwa;
          	IF temp_Var IS NULL THEN
              		id_pokoju := rec.pokoj_id;
              		RETURN NEXT;
          END IF;
    END LOOP;
    CLOSE kursor_pokoj;
END;
$$LANGUAGE 'plpgsql';

//zwraca ostatni indeks zamowienia

CREATE OR REPLACE FUNCTION zamowienieIndex() RETURNS int AS $$
DECLARE 
    x INT;
    rec RECORD;
BEGIN
    SELECT MAX(zamowienie_id) AS latest INTO rec FROM projektbd.zamowienie;
    x := rec.latest;
    RETURN x;
END;
$$LANGUAGE 'plpgsql';

//usuwanie z tabeli
delete from projektbd.zamowienie where zamowienie_id = ;

DELETE FROM projektbd.pracownik_hotelu WHERE pracownik_id = " + id";


//selecty z tabel

SELECT * FROM projektbd.czyZalogowany(\'" + login + "\',\'" + password + "\');

SELECT imie ||\' \'|| nazwisko AS result FROM projektbd.klient WHERE klient_id=id;

SELECT * FROM projektbd.pokojeView WHERE pokoj_id = " + id";

SELECT * FROM projektbd.HotelPracownicyPozycja WHERE nazwa =?;

//insert
INSERT INTO projektbd.klient(\"klient_id\",\"imie\",\"nazwisko\",\"email\",\"numer\",\"plec\",\"typ\") VALUES(\'"
                            + ind + "\',\'" + imie + "\',\'" + nazwisko + "\',\'" + email + "\',\'" + telefon + "\',\'"
                            + a + "\',\'" + typ + "\' )");
