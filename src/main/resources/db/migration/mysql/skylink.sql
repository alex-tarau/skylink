create table airport (
    id int primary key,
    airport_code varchar(3) not null,
    name varchar(100),
    location varchar(255),
);

create table airline (
    id int primary key,
    name varchar(100) not null,
    contact_number varchar(20) not null,
    operating_region varchar(100) not null
);

create table flight (
    id int primary key,
    flight_number varchar(20) unique not null,
    departure datetime not null,
    arrival datetime not null,
    available_seats int not null,
    airport_id int not null,
    airline_id int not null
    constraint fk$airport$id foreign key (airport_id) references airport (id)
    constraint fk$airline$id foreign key (airline_id) references airline (id)
);

create table passenger (
    id int primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null,
    number varchar(20) not null
);


create table reservation (
    id int primary key,
    status varchar(20),
    flight_id int not null,
    passenger_id int not null,
    constraint fk$flight$id foreign key (flight_id) references flight (id)
    constraint fk$passenger$id foreign key (passenger_id) references passenger (id)
);

create table payment (
    id int primary key,
    reservation_id int unique not null,
    method varchar(50) not null,
    amount decimal(10, 2) not null,
    created_at datetime not null,
    sent_at datetime not null,
    constraint fk$reservation$id foreign key (reservation_id) references reservation (id)
);