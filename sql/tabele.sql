CREATE TABLE "uslugi" (
  "dodatkowe_uslugi_id" serial PRIMARY KEY,
  "basen" text,
  "silownia" text,
  "sauna" text,
  "cena_od_osoby" numeric,
  "zamowienie_id" int
);

CREATE TABLE "rachunek" (
  "rachunek_id" serial PRIMARY KEY,
  "zamowienie_id" int,
  "kwota" numeric,
  "status" varchar
);
CREATE TABLE "platnosc" (
  "platnosc_id" serial PRIMARY KEY,
  "czy_dokokano" boolean,
  "rachunek_id" numeric
);

CREATE TABLE "konto_w_serwisie" (
  "konto_id" serial PRIMARY KEY,
  "login" text,
  "kwota" text
);

CREATE TABLE "hotel" (
  "hotel_id" serial PRIMARY KEY,
  "nazwa" text,
  "adres" text,
  "kraj" text,
  "gwiazdki" numeric,
  "wolne_miejsca" BOOLEAN
);

CREATE TABLE "klient" (
  "klient_id" serial PRIMARY KEY,
  "imie" text,
  "nazwisko" text,
  "email" text,
  "numer" int,
  "plec" text,
  "typ" text,
  "konto_id" int
);

CREATE TABLE "pokoj" (
  "pokoj_id" serial PRIMARY KEY,
  "numer" int,
  "pietro" int,
  "ilosc_miejsc" int,
  "dostepny" boolean,
  "hotel_nazwa" int,
  "kategoria_id" int,

);

CREATE TABLE "pracownik_hotelu" (
  "pracownik_id" serial PRIMARY KEY,
  "imie" text,
  "nazwisko" text,
  "pensja" int,
  "email" text,
  "staz" int,
  "pozycja_id" int,
  "hotel_id" int

);
CREATE TABLE "zamowienie"(
  "zamowienie_id" serial PRIMARY KEY,
  "data_przyjazdu" date,
  "data_odjazdu" date,
  "liczba_dzieci" int,
  "liczba_doroslych" int,
  "pokoj_id" int,
  "klient_id" int,
)