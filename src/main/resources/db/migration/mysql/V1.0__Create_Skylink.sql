create table skylink_airport (
    id int primary key auto_increment,
    code varchar(3) not null,
    name varchar(100) unique not null,
    street varchar(100) not null,
    city varchar(100) not null,
    country varchar(100) not null,
    created_at datetime not null,
    modified_at datetime,
    description varchar(1000)
);

create table skylink_airline (
    id int primary key auto_increment,
    name varchar(100) unique not null,
    contact_number varchar(20) not null,
    operating_region varchar(100) not null,
    created_at datetime not null,
    modified_at datetime,
    description varchar(1000)
);

create table skylink_airplane(
   id int primary key auto_increment,
   name varchar(100) unique not null,
   serial_number varchar(10) unique not null,
   manufacturer varchar(50) not null,
   model varchar(50) not null,
   model_year int not null,
   economy_seats int not null,
   economy_plus_seats int not null,
   business_seats int not null,
   first_class_seats int not null,
   deliver_date date not null,
   created_at datetime not null,
   modified_at datetime,
   description varchar(1000)
);

create table skylink_flight (
    id int primary key auto_increment,
    name varchar(20) unique not null,
    airplane_id int not null,
    origin_airport_id int not null,
    destination_airport_id int not null,
    airline_id int not null,
    status enum('ON_SCHEDULE','IN_FLIGHT','ARRIVED','DELAYED') not null,
    days_of_week varchar(100) not null,
    created_at datetime not null,
    modified_at datetime,
    arrival_at datetime not null,
    departure_at datetime not null,
    constraint fk$airplane$id foreign key (airplane_id) references skylink_airplane (id),
    constraint fk$origin_airport$id foreign key (origin_airport_id) references skylink_airport (id),
    constraint fk$destination_airport$id foreign key (destination_airport_id) references skylink_airport (id),
    constraint fk$airline$id foreign key (airline_id) references skylink_airline (id)
);

create table skylink_passenger (
    id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null,
    birth_date date not null,
    children_passengers_id int,
    created_at datetime not null,
    modified_at datetime,
    passport_number varchar(9) not null,
    description varchar(1000),
    constraint fk$child_passengers$id foreign key (children_passengers_id) references skylink_passenger (id)
);

create table skylink_review (
    id int primary key auto_increment,
    name varchar(100) not null,
    passenger_id int not null,
    airline_id int,
    airport_id int,
    flight_id int,
    status enum('NOT_STARTED','IN_PROGRESS','COMPLETED','CLOSE') not null,
    rating tinyint not null,
    helpful boolean,
    created_at datetime not null,
    modified_at datetime,
    description varchar(1000),
    constraint fk$passenger_review$id foreign key (passenger_id) references skylink_passenger (id),
    constraint fk$airline_review$id foreign key (airline_id) references skylink_airline (id),
    constraint fk$airport_review$id foreign key (airport_id) references skylink_airport (id),
    constraint fk$flight_review$id foreign key (flight_id) references skylink_flight (id)
);

create table skylink_reservation (
    id int primary key auto_increment,
    name varchar(100) unique not null,
    flight_id int not null,
    passenger_id int not null,
    seat enum('ECONOMY','ECONOMY_PLUS','BUSINESS','FIRST_CLASS') not null,
    seat_number varchar(3) unique not null,
    created_at datetime not null,
    modified_at datetime,
    description varchar(1000),
    constraint fk$flight$id foreign key (flight_id) references skylink_flight (id),
    constraint fk$passenger_reservation$id foreign key (passenger_id) references skylink_passenger (id)
);

create table skylink_payment (
    id int primary key auto_increment,
    name varchar(100) not null,
    reservation_id int not null,
    method enum('VISA','MASTER_CARD','AMEX','PAYPAL') not null,
    amount decimal(10, 2) not null,
    status enum('PENDING','SUCCESS','FAIL') not null,
    created_at datetime not null,
    modified_at datetime,
    sent_at datetime,
    description varchar(1000),
    constraint fk$reservation$id foreign key (reservation_id) references skylink_reservation (id)
);