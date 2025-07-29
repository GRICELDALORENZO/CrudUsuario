import { Roles } from "./Roles";

/*
ID_USUARIO NUMBER, 
    NOMBRE NVARCHAR2(100),
    APP NVARCHAR2(50),
    APM NVARCHAR2(50),
    SEXO NVARCHAR2(25),
    CORREO NVARCHAR2(100),
    FECHA_NACIMIENTO DATE,
    FECHA_CREACION DATE,
    ROL_ID NUMBER,*/
export class Usuario{
    idUsuario !: Number;
    nombre !: String;
    app !: String;
    apm !: String;
    sexo !: String;
    correo !: String;
    fechaNacimiento !: Date;
    fechaCreacion !: Date;
    rolId !: Roles;
}