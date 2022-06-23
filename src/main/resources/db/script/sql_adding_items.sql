-- INJECTION DES DONNEES
-- check items in all inserts

-- USERS
insert into users (id, lastname, mail, password, phone, username, grants )
values (0, 'Smith', 'bob.smith@breizhouse.fr', 'ADMIN', '0623453597', 'Bob', 'ADMIN' );
insert into users (id, lastname, mail, password, phone, username, grants )
values (1, 'Doe', 'john.doe@breizhouse.fr', 'COMMERCIAL', '0623453597', 'John', 'COMMERCIAL'  );

-- CUSTOMERS
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone,
                       street_name, street_number, zipcode, user_id) values (0, true, 'Angers', 'Clara', 'Ziriani',
                                                                             'clara.z@gmail.com', '0720394857', 'Famille 5 personnes', '0234361111', 'rue de la gare', 23, 49000, 1);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (1, true, 'Nantes', 'Marc', 'Cochic', 'marc.c@gmail.com',
                                                                '0720390034', 'Famille 2 personnes', '0234362222', 'allée des platanes', 65, 44000, 0);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (2, true, 'Rennes', 'Bastien', 'Prille', 'bastien.p@gmail.com',
                                                                '0720398930', 'Seul', '0234363333', 'chemin du parc', 98, 35000, 0);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (3, true, 'Quimper', 'Alexandre', 'Deniel', 'alex.d@gmail.com',
                                                                '0720390021', 'Famille 4 personnes', '0234364444', 'rue des Lys', 03, 29000, 1);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (4, true, 'Vannes', 'Julie', 'Lebarot', 'julie.l@gmail.com',
                                                                '0720384756', 'Famille 2 personnes naissance prévue', '0234365555', 'parc du trocadero', 01, 56000, 0);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (5, true, 'Rennes', 'Manille', 'Tremon', 'manille.t@gmail.com',
                                                                '0620309803', 'Couple 2 personnes', '0234366666', 'chemin des brumes', 53, 35000, 1);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (6, true, 'Angers', 'Estelle', 'Gérard', 'estelle.g@gmail.com',
                                                                '0620394800', 'Seul', '0234362223', 'allée des fleurs', 21, 49000, 0);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (7, true, 'Nantes', 'Yann', 'Coadour', 'yann.c@gmail.com',
                                                                '0720394000', 'Modèle produit à revoir', '0234368888', 'Lieu dit La Grande Haie', 09, 44000, 1);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (8, true, 'Quimper', 'Laurent', 'Fiba', 'laurent.f@gmail.com',
                                                                '0620393526', 'Client recommandé', '0234364008', 'rue de la Mairie', 102, 29000, 1);
insert into customers (id, active, city, firstname, lastname, mail, mobile, notes, phone, street_name,
                       street_number, zipcode, user_id) values (9, false, 'Vannes', 'Marie', 'Michel', 'marie.m@gmail.com',
                                                                '0720302839', 'Famille 7 personnes', '0234300758', 'Place de la foire', 38, 56000, 1);

-- ORDERS
insert into orders (id, date, name , price, reference, status, user_id, product_id, customer_id)
values (0, 12/01/2020, 'Modele 1', 99000, 'COM0102', 'En cours', 0, 1, 0);
insert into orders (id, date, name , price, reference, status, user_id, product_id, customer_id)
values (1, 12/02/2021, 'Modele 2', 1990000, 'COM045', 'Payée', 0, 2, 1);
insert into orders (id, date, name , price, reference, status, user_id, product_id, customer_id)
values (2, 12/03/2019, 'Modele 3', 2990000, 'COM025', 'Validé', 1, 3, 2);
insert into orders (id, date, name , price, reference, status, user_id, product_id, customer_id)
values (3, 12/04/2018, 'Modele 4', 3990000, 'COM099', 'En cours', 1, 4, 3);
insert into orders (id, date, name , price, reference, status, user_id, product_id, customer_id)
values (4, 12/05/2020, 'Modele 5', 4990000, 'COM0105', 'Terminée', 0, 5, 4);
insert into orders (id, date, name , price, reference, status, user_id, product_id, customer_id)
values (5, 12/06/2021, 'Modele 6', 5990000, 'COM0130', 'Validé', 1, 6, 5);

-- PRODUCTS
insert into products (id, name, photo_url, price_ht, surface, type)
values (0, 'Modele 1', '', 99000, 50, 'T2');
insert into products (id, name, photo_url, price_ht, surface, type, order_id)
values (1, 'Modele 2', '', 199000, 55, 'T3', 1);
insert into products (id, name, photo_url, price_ht, surface, type, order_id)
values (2, 'Modele 3', '', 299000, 60, 'T4', 2);
insert into products (id, name, photo_url, price_ht, surface, type, order_id)
values (3, 'Modele 4', '', 399000, 70, 'T5', 3);
insert into products (id, name, photo_url, price_ht, surface, type, order_id)
values (4, 'Modele 5', '', 499000, 80, 'T5', 4);
insert into products (id, name, photo_url, price_ht, surface, type, order_id)
values (5, 'Modele 6', '', 599000, 85, 'T6', 5);

