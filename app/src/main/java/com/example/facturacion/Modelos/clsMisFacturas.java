package com.example.facturacion.Modelos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class clsMisFacturas extends RealmObject {

    @PrimaryKey
    private int IdClienteFac;
    private int IdCliente;
    private String NumFactura;
    private String FechaFactura;
    private String FechaVencimineto;
    private String MontoFactura;
    private String PagosFactura;
    private String SaldoFactura;
    private String EstadoFactura;

    public clsMisFacturas(int idClienteFac, int idCliente, String numFact, String fechaFact, String fechaVence,
                          String montoFactura, String pagosFactura, String saldoFact, String estadofact) {
        IdClienteFac = idClienteFac;
        NumFactura = numFact;
        FechaFactura = fechaFact;
        FechaVencimineto = fechaVence;
        MontoFactura = montoFactura;
        PagosFactura = pagosFactura;
        SaldoFactura = saldoFact;
        IdCliente = idCliente;
        EstadoFactura = estadofact;
    }

    public clsMisFacturas(){
        IdClienteFac = 0;
        IdCliente = 0;
        NumFactura = "";
        FechaFactura = "";
        FechaVencimineto = "";
        MontoFactura = "";
        PagosFactura = "";
        SaldoFactura = "";
        EstadoFactura = "";
    }

    public int getIdClienteFac() {
        return IdClienteFac;
    }

    public void setIdClienteFac(int idClienteFac) {
        IdClienteFac = idClienteFac;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNumFactura() {
        return NumFactura;
    }

    public void setNumFactura(String numFactura) {
        NumFactura = numFactura;
    }

    public String getFechaFactura() {
        return FechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        FechaFactura = fechaFactura;
    }

    public String getFechaVencimineto() {
        return FechaVencimineto;
    }

    public void setFechaVencimineto(String fechaVencimineto) {
        FechaVencimineto = fechaVencimineto;
    }

    public String getMontoFactura() {
        return MontoFactura;
    }

    public void setMontoFactura(String montoFactura) {
        MontoFactura = montoFactura;
    }

    public String getPagosFactura() {
        return PagosFactura;
    }

    public void setPagosFactura(String pagosFactura) {
        PagosFactura = pagosFactura;
    }

    public String getSaldoFactura() {
        return SaldoFactura;
    }

    public void setSaldoFactura(String saldoFactura) {
        SaldoFactura = saldoFactura;
    }

    public String getEstadoFactura() {
        return EstadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        EstadoFactura = estadoFactura;
    }

    @Override
    public String toString() {
        return "clsMisFacturas{" +
                "IdClienteFac=" + IdClienteFac +
                ", NumFactura='" + NumFactura + '\'' +
                ", FechaFactura='" + FechaFactura + '\'' +
                ", FechaVencimineto='" + FechaVencimineto + '\'' +
                ", MontoFactura='" + MontoFactura + '\'' +
                ", PagosFactura='" + PagosFactura + '\'' +
                ", SaldoFactura='" + SaldoFactura + '\'' +
                ", IdCliente'" + IdCliente + '\'' +
                ", Estado'" + EstadoFactura + '\'' +
                '}';
    }
}

