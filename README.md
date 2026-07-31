# TecnoStore

## Descripción

TecnoStore es una aplicación de consola desarrollada en Java para administrar una tienda de celulares. El sistema permite gestionar el inventario, los clientes y las ventas, utilizando una base de datos MySQL para almacenar la información.

## Funcionalidades

- Registrar, actualizar, eliminar y listar celulares.
- Registrar, actualizar, eliminar y listar clientes.
- Registrar ventas con uno o varios celulares.
- Calcular automáticamente el total de la venta con IVA.
- Actualizar el stock después de cada venta.
- Consultar celulares con stock bajo.
- Mostrar el top 3 de celulares más vendidos.
- Consultar las ventas realizadas por mes.
- Generar un archivo `reporte_ventas.txt` con el resumen de las ventas.

## Tecnologías utilizadas

- Java
- MySQL
- JDBC
- Git y GitHub

## Estructura del proyecto

```text
src
│
├── Logica
│   ├── GestorCelulares
│   ├── GestorClientes
│   ├── GestorVentas
│   └── ItemVenta
│
├── Modelo
│
├── Persistencia
│   ├── Conexion
│   ├── CelularesDAO
│   ├── ClientesDAO
│   ├── VentasDAO
│   └── ModeloDAO
│
├── Patron
│   └── FactoryCelular
│
├── Utilidades
│   ├── Validadores
│   └── ReporteUtils
│
└── Main
```

## Instalación

1. Clonar el repositorio.
2. Crear la base de datos ejecutando el archivo `tecnostore_db.sql`.
3. Configurar el usuario y la contraseña de MySQL en la clase `Conexion`.
4. Ejecutar el proyecto desde el IDE.

## Ejemplo de menú

```text
====== MENÚ PRINCIPAL ======

1. Gestión de celulares
2. Gestión de clientes
3. Gestión de ventas
4. Reportes
5. Salir
```

## Autor

Juan Angel Mantilla Villalba - Jotaa-a