drop table product;

create table product(
    id int not null,
    product_name varchar(255) unique not null,
    price double not null,
    primary key(id)
);