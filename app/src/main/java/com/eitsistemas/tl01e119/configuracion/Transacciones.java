package com.eitsistemas.tl01e119.configuracion;

public class Transacciones
{
    //Nombre de la base de datos
     public static final String NameDataBase = "InfomacionPersonal";

     //Creacion de tabla y objetos

    public static  final String tablaperson = "datos";

    //campos de la tabla datos

    public static String Id = "id";
    public static String pais = "pais";
    public static String nombre = "nombre";
    public static String telefono = "telefono";
    public static String nota = "nota";

    // Consultas SQL DDL
    public static String CreateTBDatos = "CREATE TABLE datos (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "pais TEXT, nombre TEXT, telefono TEXT, nota TEXT)";

    public static String DropTBDatos ="DROP TABLE IF EXISTS datos";


}
