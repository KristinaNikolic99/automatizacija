use db_automatizacija;

INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (350, 1, 'Voćni sok 100% jabuka, od autohtonih sorta jabuka', 'Sok od jabuke', 's_jabuka.jpg');
INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (330, 1, 'Voćni sok 100% kruska, bez šećera', 'Sok od kruške', 's_kruska.jpg');
INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (310, 1, 'Sok od 100% ceđene šargarepe, bez šećera', 'Sok od šargarepe', 's_šargarepa.jpg');
INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (390, 1, 'Sok od 100% ceđene narandže, bez šećera i nerazblažen u vodi', 'Sok od narandže', 's_narandža.jpg');
INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (420, 1, 'Kašasti voćni sok od jagode, zaslađen sa bagremovim medom', 'Sok od jagode sa medom', 's_jagoda_med.jpg');
INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (480, 1, 'Kašasti voćni sok od maline, zaslađen sa bagremovim medom', 'Sok od maline sa medom', 's_malina_med.jpg');
INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (410, 1, '100% prirodan sok, od mešavine voća i povrća', 'Sok od cvekle, jabuke i šargarepe', 's_cvekla_jabuka_šargarepa.jpg');
INSERT INTO sok (cena, kolicina, opis, vrsta, slika) VALUES (450, 1, 'Sok od 100% organskog paradajza', 'Sok od paradajza', 's_paradajz.jpg');

INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('paradajz', 'voće');
INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('crvena paprika', 'povrće');
INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('celer', 'povrće');
INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('morska so', 'so');

INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('cvekla', 'voće');
INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('jabuka', 'voće');
INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('šargarepa', 'povrće');

INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('malina', 'voće');
INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('bagremov med', 'med');
INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('voda', 'voda');

INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('jagoda', 'voće');

INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('narandža', 'voće');

INSERT INTO sastojak (naziv, vrsta_ploda) VALUES ('kruška', 'voće');

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (5, 1, 8);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (2, 2, 8);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (1, 3, 8);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.02, 4, 8);

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (4, 5, 7);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (2, 6, 7);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.4, 7, 7);

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.8, 8, 6);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.1, 9, 6);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.2, 10, 6);

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.8, 11, 5);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.1, 9, 5);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.2, 10, 5);

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (6, 12, 4);

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.9, 7, 3);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.2, 10, 3);

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (8, 13, 2);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.2, 10, 2);

INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (8, 6, 1);
INSERT INTO detalji_sastava (kolicina, sastojak, sok) VALUES (0.2, 10, 1);


INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (50, 'Komad', 1);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (25, 'Komad', 2);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (20, 'Komad', 3);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (0.9, 'Kilogram', 4);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (30, 'Komad', 5);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (40, 'Komad', 6);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (8, 'Kilogram', 7);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (12, 'Kilogram', 8);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (2, 'Kilogram', 9);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (20, 'Litar', 10);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (10, 'Kilogram', 11);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (30, 'Komad', 12);
INSERT INTO zaliha (kolicina, mera, sastojak) VALUES (30, 'Komad', 13);


INSERT INTO masina (naziv, serijski_broj) VALUES ('Alfa', '1111A');
INSERT INTO masina (naziv, serijski_broj) VALUES ('Beta', '1112B');
INSERT INTO masina (naziv, serijski_broj) VALUES ('Gama', '1113G');

INSERT INTO proizvodnja (masina, sok) VALUES (1, 1);
INSERT INTO proizvodnja (masina, sok) VALUES (1, 2);
INSERT INTO proizvodnja (masina, sok) VALUES (1, 3);
INSERT INTO proizvodnja (masina, sok) VALUES (1, 6);
INSERT INTO proizvodnja (masina, sok) VALUES (2, 1);
INSERT INTO proizvodnja (masina, sok) VALUES (2, 4);
INSERT INTO proizvodnja (masina, sok) VALUES (2, 5);
INSERT INTO proizvodnja (masina, sok) VALUES (2, 6);
INSERT INTO proizvodnja (masina, sok) VALUES (2, 8);
INSERT INTO proizvodnja (masina, sok) VALUES (3, 1);
INSERT INTO proizvodnja (masina, sok) VALUES (3, 5);
INSERT INTO proizvodnja (masina, sok) VALUES (3, 7);
INSERT INTO proizvodnja (masina, sok) VALUES (3, 8);