drop database if exists dbpuntodeventa;

create database dbpuntodeventa;
use dbpuntodeventa;

create table cliente(
  id_cliente int auto_increment primary key not null,
  identificacion varchar(20) unique not null,
  nombres varchar(100) not null,
  apellido_paterno varchar(100) not null,
  apellido_materno varchar(100) not null,
  direccion varchar(100) not null,
  telefono varchar(20),
  correo_electronico varchar(100),
  sexo varchar(20) not null,
  edad int not null,
  talla int,
  peso int,
  fecha_registro date
);

select * from cliente;