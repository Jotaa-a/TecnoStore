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

USE tecnostore_db;

-- ==========================
-- MARCAS
-- ==========================
INSERT INTO marcas(nombre) VALUES
('Samsung'),
('Apple'),
('Xiaomi'),
('Motorola'),
('Huawei'),
('Honor'),
('Realme'),
('Google');

-- ==========================
-- SISTEMAS OPERATIVOS
-- ==========================
INSERT INTO sistema_operativo(nombre) VALUES
('Android'),
('iOS');

-- ==========================
-- MODELOS
-- ==========================
INSERT INTO modelos(id_marca,id_so,modelo,gama) VALUES
(1,1,'Galaxy A16','Baja'),
(1,1,'Galaxy A36','Media'),
(1,1,'Galaxy S25','Alta'),

(2,2,'iPhone 13','Media'),
(2,2,'iPhone 16','Alta'),

(3,1,'Redmi Note 14','Media'),
(3,1,'POCO X7 Pro','Alta'),

(4,1,'Moto G85','Media'),
(4,1,'Edge 60','Alta'),

(5,1,'Nova 13','Media'),

(6,1,'Honor X8C','Media'),

(7,1,'Realme 14 Pro','Alta'),

(8,1,'Pixel 9','Alta');

-- ==========================
-- CELULARES
-- ==========================
INSERT INTO celulares(id_modelo,precio,stock) VALUES
(1,650000,15),
(2,1200000,10),
(3,4200000,6),

(4,2800000,8),
(5,5600000,2),

(6,1100000,12),
(7,2400000,7),

(8,1300000,11),
(9,3100000,5),

(10,2200000,6),

(11,1700000,9),

(12,2600000,2),

(13,4700000,0);

-- ==========================
-- CLIENTES
-- ==========================
INSERT INTO clientes(nombre,identificacion,correo,telefono) VALUES
('Juan Pérez','1001001001','juan@gmail.com','3001111111'),
('María Gómez','1001001002','maria@gmail.com','3002222222'),
('Carlos Rodríguez','1001001003','carlos@gmail.com','3003333333'),
('Laura Torres','1001001004','laura@gmail.com','3004444444'),
('Andrés Díaz','1001001005','andres@gmail.com','3005555555'),
('Sofía Herrera','1001001006','sofia@gmail.com','3006666666'),
('Miguel Castro','1001001007','miguel@gmail.com','3007777777'),
('Valentina Rojas','1001001008','valentina@gmail.com','3008888888');

-- ==========================
-- VENTAS
-- ==========================
INSERT INTO ventas(id_cliente,fecha,total) VALUES
(1,'2026-07-01',650000),
(2,'2026-07-02',5600000),
(3,'2026-07-03',2200000),
(1,'2026-07-05',3900000),
(5,'2026-07-06',1100000),
(6,'2026-07-08',5400000);

-- ==========================
-- DETALLE VENTAS
-- ==========================
INSERT INTO detalle_ventas(id_venta,id_celular,cantidad,subtotal) VALUES

(1,1,1,650000),

(2,5,1,5600000),

(3,10,1,2200000),

(4,2,1,1200000),
(4,8,2,2600000),

(5,6,1,1100000),

(6,3,1,4200000),
(6,1,2,1300000);

