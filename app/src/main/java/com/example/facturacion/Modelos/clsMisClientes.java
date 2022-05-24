package com.example.facturacion.Modelos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class clsMisClientes extends RealmObject {


    @PrimaryKey
    private int IdCliente;
    private String Nombre;
    private String Cedula;
    private String TelefonoCelular;
    private String Correo;
    private String Saldo;

    public clsMisClientes(int idCliente, String nombre, String cedula, String telefonoCelular, String correo, String saldo) {
        IdCliente = idCliente;
        Nombre = nombre;
        Cedula = cedula;
        TelefonoCelular = telefonoCelular;
        Correo = correo;
        Saldo = saldo;
    }
    public clsMisClientes() {
        IdCliente = 0;
        Nombre = "";
        Cedula = "";
        TelefonoCelular = "";
        Correo = "";
        Saldo = "";
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getTelefonoCelular() {
        return TelefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        TelefonoCelular = telefonoCelular;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getSaldo() {
        return Saldo;
    }

    public void setSaldo(String saldo) {
        Saldo = saldo;
    }

    @Override
    public String toString() {
        return "clsMisClientes{" +
                "IdCliente=" + IdCliente +
                ", Nombre='" + Nombre + '\'' +
                ", Cedula='" + Cedula + '\'' +
                ", TelefonoCelular='" + TelefonoCelular + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Saldo='" + Saldo + '\'' +
                '}';
    }
}
