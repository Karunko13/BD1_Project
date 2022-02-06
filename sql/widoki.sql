//tworzymy wirtualna tabele laczaca pokoj i kategoria_pokoju
CREATE VIEW pokojeView AS SELECT pokoj_id, numer, pietro, ilosc_miejsc,dostepny, hotel_nazwa,  p.kategoria_id, cena_od_osoby FROM pokoj p JOIN kategoria_pokoju k ON p.kategoria_id = k.kategoria_id;


CREATE OR REPLACE VIEW PokojAndZamowienie AS SELECT r.*, p.numer, p.pietro, p.ilosc_miejsc, p.kategoria_id, p.cena_od_osoby, p.hotel_nazwa FROM zamowienie r JOIN pokojeView p ON r.pokoj_id = p.pokoj_id;

CREATE VIEW PokojAndZamowienieAndUslugi AS SELECT r.*, u.basen, u.silownia, u.sauna  FROM PokojAndZamowienie r JOIN uslugi u ON r.zamowienie_id = u.zamowienie_id;

CREATE OR REPLACE  VIEW ZamowienieAndRachunek AS SELECT r.*, o.rachunek_id, o.kwota, o.stan FROM PokojAndZamowienieAndUslugi r JOIN rachunek o ON r.zamowienie_id = o.zamowienie_id;

CREATE VIEW ZamowienieMergeALL AS SELECT r.*, u.imie, u.nazwisko FROM projektbd.ZamowienieAndRachunek r JOIN projektbd.klient u ON r.klient_id = u.klient_id;

CREATE VIEW HotelAndPokoj AS SELECT r.*, u.hotel_id, u.adres, u.kraj, u.gwiazdki FROM PokojAndZamowienie r JOIN hotel u ON r.hotel_nazwa = u.nazwa;

CREATE VIEW WszystkieInf AS SELECT r.*, u.basen, u.silownia, u.sauna FROM HotelAndPokoj r JOIN uslugi u ON r.zamowienie_id = u.zamowienie_id;

CREATE VIEW HotelPracownicy AS SELECT r.*, u.nazwa FROM pracownik_hotelu r JOIN hotel u ON u.hotel__id=r.hotel_id;

CREATE VIEW HotelPracownicyPozycja AS SELECT r.*, u.opis FROM projektbd.HotelPracownicy r JOIN pozycja_pracownika u ON u.pozycja_id=r.pozycja_id;