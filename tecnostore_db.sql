drop database if exists tecnostore_db;
create database tecnostore_db;
use tecnostore_db;

/*Tabla de CLIENTES*/
create table clientes (
    id_cl int primary key auto_increment,
    nombre varchar(100) not null,
    identificacion varchar(50) not null unique,
    correo varchar(50) not null unique,
    telefono varchar(50) not null unique
);

-- Tabla de MARCAS
create table marcas (
    id_mks int primary key auto_increment,
    nombre varchar(100) not null
);

-- Tabla de SISTEMA_OPERATIVO
create table sistema_operativo (
    id_so int primary key auto_increment,
    nombre varchar(100) not null
);

-- Tabla de MODELOS
create table modelos (
    id_md int primary key auto_increment,
    id_marca int not null,
    id_so int not null, 
    modelo varchar(100) not null,
    gama enum('Alta', 'Media', 'Baja') not null,
    foreign key (id_marca) references marcas(id_mks),
    foreign key (id_so) references sistema_operativo(id_so)
);

-- Tabla de CELULARES
create table celulares (
    id_ce int primary key auto_increment,
    id_modelo int not null, 
    precio decimal(10,2) not null,
    stock int not null,
    foreign key (id_modelo) references modelos(id_md)
);

-- Tabla de VENTAS
create table ventas (
    id_v int primary key auto_increment,
    id_cliente int not null,
    fecha date default (current_date),
    total decimal(10,2) not null,
    foreign key (id_cliente) references clientes(id_cl)
);  

-- Tabla de DETALLE DE VENTAS
create table detalle_ventas (
    id_dv int primary key auto_increment,
    id_venta int not null,
    id_celular int not null,
    cantidad int not null,
    subtotal decimal(10,2) not null,
    foreign key(id_venta) references ventas(id_v),
    foreign key(id_celular) references celulares(id_ce)
);


