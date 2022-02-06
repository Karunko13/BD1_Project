INSERT INTO kategoria_pokoju VALUES(1,100);
INSERT INTO kategoria_pokoju VALUES(2,80);
INSERT INTO kategoria_pokoju VALUES(3,50);

//dodane pokoi do bazy
INSERT INTO pokoj VALUES(1,1,1,2,'True','Warszawa',2);
INSERT INTO pokoj VALUES(2,2,1,6,'True','Warszawa',1);
INSERT INTO pokoj VALUES(3,3,1,1,'True','Warszawa',3);
INSERT INTO pokoj VALUES(4,4,1,2,'True','Warszawa',2);
INSERT INTO pokoj VALUES(10,10,2,2,'True','Warszawa',2);
INSERT INTO pokoj VALUES(11,11,2,6,'True','Warszawa',1);
INSERT INTO pokoj VALUES(12,12,2,1,'True','Warszawa',3);
INSERT INTO pokoj VALUES(13,13,2,2,'True','Warszawa',2);
INSERT INTO pokoj VALUES(14,14,3,2,'True','Warszawa',1);
INSERT INTO pokoj VALUES(15,15,3,6,'True','Warszawa',1);
INSERT INTO pokoj VALUES(16,16,3,1,'True','Warszawa',3);
INSERT INTO pokoj VALUES(17,17,3,2,'True','Warszawa',1);


INSERT INTO pokoj VALUES(18,1,1,2,'True','Zakopane',2);
INSERT INTO pokoj VALUES(19,2,1,6,'True','Zakopane',1);
INSERT INTO pokoj VALUES(20,3,1,1,'True','Zakopane',3);
INSERT INTO pokoj VALUES(21,4,1,2,'True','Zakopane',2);
INSERT INTO pokoj VALUES(22,5,2,2,'True','Zakopane',2);
INSERT INTO pokoj VALUES(23,6,2,6,'True','Zakopane',1);
INSERT INTO pokoj VALUES(24,7,2,1,'True','Zakopane',3);
INSERT INTO pokoj VALUES(25,8,2,2,'True','Zakopane',2);
INSERT INTO pokoj VALUES(26,9,3,2,'True','Zakopane',1);
INSERT INTO pokoj VALUES(27,10,3,6,'True','Zakopane',1);
INSERT INTO pokoj VALUES(28,11,3,1,'True','Zakopane',3);
INSERT INTO pokoj VALUES(29,12,3,2,'True','Zakopane',1);

INSERT INTO pokoj VALUES(30,1,1,2,'True','Gdynia',1);
INSERT INTO pokoj VALUES(31,2,1,6,'True','Gdynia',3);
INSERT INTO pokoj VALUES(32,3,1,1,'True','Gdynia',2);
INSERT INTO pokoj VALUES(33,4,1,2,'True','Gdynia',1);
INSERT INTO pokoj VALUES(34,5,2,2,'True','Gdynia',2);
INSERT INTO pokoj VALUES(35,6,2,6,'True','Gdynia',1);
INSERT INTO pokoj VALUES(36,7,2,1,'True','Gdynia',3);
INSERT INTO pokoj VALUES(37,8,2,2,'True','Gdynia',3);
INSERT INTO pokoj VALUES(38,9,3,2,'True','Gdynia',2);
INSERT INTO pokoj VALUES(39,10,3,6,'True','Gdynia',1);
INSERT INTO pokoj VALUES(40,11,3,1,'True','Gdynia',3);
INSERT INTO pokoj VALUES(41,12,3,2,'True','Gdynia',2);


INSERT INTO pokoj VALUES(42,13,4,2,'True','Gdynia',1);
INSERT INTO pokoj VALUES(43,14,4,3,'True','Gdynia',3);
INSERT INTO pokoj VALUES(44,15,4,5,'True','Gdynia',3);
INSERT INTO pokoj VALUES(45,16,5,8,'True','Gdynia',2);
INSERT INTO pokoj VALUES(46,17,5,1,'True','Gdynia',3);
INSERT INTO pokoj VALUES(47,18,5,3,'True','Gdynia',1);
INSERT INTO pokoj VALUES(48,19,6,1,'True','Gdynia',3);

INSERT INTO pokoj VALUES(49,18,2,2,'True','Warszawa',2);
INSERT INTO pokoj VALUES(50,19,3,2,'True','Warszawa',1);
INSERT INTO pokoj VALUES(51,20,3,6,'True','Warszawa',1);
INSERT INTO pokoj VALUES(52,21,3,5,'True','Warszawa',3);
INSERT INTO pokoj VALUES(53,22,3,4,'True','Warszawa',3);
INSERT INTO pokoj VALUES(54,23,4,2,'True','Warszawa',1);
INSERT INTO pokoj VALUES(55,24,4,7,'True','Warszawa',3);
INSERT INTO pokoj VALUES(56,25,4,1,'True','Warszawa',2);
INSERT INTO pokoj VALUES(57,26,5,3,'True','Warszawa',1);
INSERT INTO pokoj VALUES(58,27,5,2,'True','Warszawa',2);
INSERT INTO pokoj VALUES(59,28,5,6,'True','Warszawa',1);

INSERT INTO pokoj VALUES(60,13,2,1,'True','Zakopane',1);
INSERT INTO pokoj VALUES(61,14,2,2,'True','Zakopane',3);
INSERT INTO pokoj VALUES(62,15,3,2,'True','Zakopane',2);
INSERT INTO pokoj VALUES(63,16,3,6,'True','Zakopane',1);
INSERT INTO pokoj VALUES(64,17,4,1,'True','Zakopane',3);
INSERT INTO pokoj VALUES(65,18,5,2,'True','Zakopane',2);

INSERT INTO pozycja_pracownika VALUES(1,'Recepcja');
INSERT INTO pozycja_pracownika VALUES(2,'Obsługa');
INSERT INTO pozycja_pracownika VALUES(3,'Sprzątanie');
INSERT INTO pozycja_pracownika VALUES(4,'Ochrona');
INSERT INTO pozycja_pracownika VALUES(5,'Zarząd');

INSERT INTO pracownik_hotelu VALUES(1,'Tomasz','Głaz',3000,'tomgl@gmail',13,1,1);
INSERT INTO pracownik_hotelu VALUES(2,'Adam','Sztaba',2500,'adamsz@gmail',3,2,1);
INSERT INTO pracownik_hotelu VALUES(3,'Waldemar','Płaz',1432,'waldpl@gmail',8,3,1);
INSERT INTO pracownik_hotelu VALUES(4,'Maria','Nowak',4500,'marian@gmail',12,4,1);
INSERT INTO pracownik_hotelu VALUES(5,'Adrian','Pompa',3000,'adrianpom@gmail',13,5,1);
INSERT INTO pracownik_hotelu VALUES(6,'Kamil','Sztaba',2500,'kamilszt@gmail',3,1,1);
INSERT INTO pracownik_hotelu VALUES(7,'Miłosz','Kalosz',1432,'miloszka@gmail',8,4,1);
INSERT INTO pracownik_hotelu VALUES(8,'Anna','Kowal',400,'annakow@gmail',12,5,1);

INSERT INTO pracownik_hotelu VALUES(9,'Alojzy','Polak',3000,'alpol@gmail',13,4,2);
INSERT INTO pracownik_hotelu VALUES(10,'Michal','Wariat',2500,'michw@gmail',3,3,2);
INSERT INTO pracownik_hotelu VALUES(11,'Krzysztof','Kowalski',6000,'t@gmail',8,5,2);
INSERT INTO pracownik_hotelu VALUES(12,'Maryla','Rodak',5000,'marno@gmail',12,1,2);
INSERT INTO pracownik_hotelu VALUES(13,'Marian','Grabowski',3000,'marb@gmail',13,1,2);
INSERT INTO pracownik_hotelu VALUES(14,'Marian','Banas',2500,'t@gmail',3,5,2);
INSERT INTO pracownik_hotelu VALUES(15,'Daniel','Stan',1432,'danst@gmail',8,3,2);
INSERT INTO pracownik_hotelu VALUES(16,'Julia','Niemiec',8611,'juln@gmail',12,4,2);

INSERT INTO pracownik_hotelu VALUES(17,'Julia','Baran',3000,'jbar@gmail',13,5,3);
INSERT INTO pracownik_hotelu VALUES(18,'Marian','Sztaba',2500,'marsz@gmail',3,2,3);
INSERT INTO pracownik_hotelu VALUES(19,'Waldemar','Pawlak',1432,'walpaw@gmail',8,3,3);
INSERT INTO pracownik_hotelu VALUES(20,'Piotr','Nowak',4001,'piotrn@gmail',12,4,3);
INSERT INTO pracownik_hotelu VALUES(21,'Tomasz','Andrus',3000,'tomand@gmail',13,1,3);
INSERT INTO pracownik_hotelu VALUES(22,'Grzegorz','Adamek',2500,'gada@gmail',3,2,3);
INSERT INTO pracownik_hotelu VALUES(23,'Miroslaw','Złodziej',1432,'mirzl@gmail',8,5,3);
INSERT INTO pracownik_hotelu VALUES(24,'Beata','Mastek',3500,'bemas@gmail',12,4,3);