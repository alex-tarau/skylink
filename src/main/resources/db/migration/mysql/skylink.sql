create table airport (
    id int primary key auto_increment,
    code varchar(3) not null,
    name varchar(100) unique not null,
    street varchar(100) not null,
    city varchar(100) not null,
    country varchar(100) not null
);

create table airline (
    id int primary key auto_increment,
    name varchar(100) unique not null,
    contact_number varchar(20) not null,
    operating_region varchar(100) not null
);

create table flight (
    id int primary key auto_increment,
    origin_airport_id int not null,
    destination_airport_id int not null,
    airline_id int not null,
    flight_number varchar(20) unique not null,
    created_at datetime not null,
    modified_at datetime,
    arrival_at datetime not null,
    departure_at datetime not null,
    available_seats int not null,
    constraint fk$origin_airport$id foreign key (origin_airport_id) references airport (id),
    constraint fk$destination_airport$id foreign key (destination_airport_id) references airport (id),
    constraint fk$airline$id foreign key (airline_id) references airline (id)
);

create table passenger (
    id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null,
    birth_date date not null,
    created_at datetime not null,
    modified_at datetime,
    passport_number varchar(9) not null
);


create table reservation (
    id int primary key auto_increment,
    flight_id int not null,
    passenger_id int not null,
    created_at datetime not null,
    modified_at datetime,
    constraint fk$flight$id foreign key (flight_id) references flight (id),
    constraint fk$passenger_reservation$id foreign key (passenger_id) references passenger (id)
);

create table payment (
    id int primary key auto_increment,
    reservation_id int not null,
    method ENUM('VISA','MASTER_CARD','AMEX','PAYPAL') not null,
    amount decimal(10, 2) not null,
    status Enum('PENDING','SUCCESS','FAIL') not null,
    created_at datetime not null,
    sent_at datetime,
    modified_at datetime,
    constraint fk$reservation$id foreign key (reservation_id) references reservation (id)
);