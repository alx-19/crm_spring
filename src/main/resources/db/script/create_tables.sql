/* Création des tables */

/* Création des tables sans ordre vu que les contraintes sont ajoutees apres */
-- revoir les attributs

/* Ajout des clés primaires sans ordre */
-- sélectionner les id voir firstname/lastname/usernamer en tant que PK

/* Ajout des clés étrangères dans l'ordre */
-- indiquer les _id qui seront rappeler dans la table Orders et Customers



create table users
(
    id       serial
        primary key,
    lastname varchar(30),
    mail     varchar(255),
    password varchar(255),
    phone    bigint,
    username varchar(30),
    grants   varchar(10)
);

alter table users
    owner to "user";

create table customers
(
    id            serial
        primary key,
    active        boolean,
    city          varchar(30),
    firstname     varchar(100),
    lastname      varchar(100),
    mail          varchar(30),
    mobile        varchar(15),
    notes         varchar(255),
    phone         varchar(15),
    street_name   varchar(30),
    street_number integer,
    zipcode       integer,
    user_id       integer not null
        constraint fkrh1g1a20omjmn6kurd35o3eit
            references users
);

alter table customers
    owner to "user";

create table orders
(
    id          serial
        primary key,
    date        varchar(10),
    name        varchar(100),
    price       bigint,
    reference   integer,
    status      varchar(10),
    user_id     integer not null
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users,
    product_id  integer,
    customer_id integer not null
        constraint fkpxtb8awmi0dk6smoh2vp1litg
            references customers
);

alter table orders
    owner to "user";

create table products
(
    id        serial
        primary key,
    name      varchar(30),
    photo_url varchar(255),
    price_ht  bigint,
    surface   bigint,
    type      varchar(30),
    order_id  integer
        constraint fkr3aftk48ylvntpui7l04kbcc1
            references orders
);

alter table products
    owner to "user";

alter table orders
    add constraint fkkp5k52qtiygd8jkag4hayd0qg
        foreign key (product_id) references products;

