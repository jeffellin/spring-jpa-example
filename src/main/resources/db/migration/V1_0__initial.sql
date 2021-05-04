
create table Address(
    id int,
    customer_id int,
    line1 varchar(50),
    line2 varchar(50)
);

create table Customer(
    id int,
    phone_number varchar(50)
);
