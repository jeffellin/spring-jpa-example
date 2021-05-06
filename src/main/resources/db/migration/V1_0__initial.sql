
create table Address(
    id int primary key auto_increment,
    customer_id int,
    line1 varchar(50),
    line2 varchar(50)
);

create table Customer(
    id int primary key auto_increment,
    phone_number varchar(50),
    first_name varchar(50),
    last_name varchar(50)
);
