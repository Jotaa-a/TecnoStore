package Tecnostore;

import Persistencia.Conexion;

public class TecnoStore {

    public static void main(String[] args) {
        Conexion c= new Conexion();
        c.conectar();
    }
}
