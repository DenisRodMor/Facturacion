package com.example.facturacion.Modelos;

import java.util.List;

import io.realm.Realm;

public class clsServicioFacturas {
    private Realm realm;


    public clsServicioFacturas(Realm _realm)
    {
        this.realm = _realm;
    }
    //************************************************************************************
    private int CalcularIdFac()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number idActual = mRealm.where(clsMisFacturas.class).max("IdClienteFac");
        return  idActual == null ? 1 : idActual.intValue() + 1;
    }
    //************************************************************************************
    public void CrearFactura( int _idCliente, String _NumFact, String _FechaFact,String _FechaVenc, int _Montofact,
                              String _Pagosfact, String _SaldoFact, String _Estadofact){
        int mIdCliente = CalcularIdFac();
        int saldoact;
        clsMisClientes saldo = realm.where(clsMisClientes.class).equalTo("IdCliente",_idCliente).findFirst();
        realm.beginTransaction();
        if(saldo.getSaldo()==null){
            saldo.setSaldo("0");
        }
        saldoact = Integer.parseInt(saldo.getSaldo());
        clsMisFacturas agregar = realm.createObject(clsMisFacturas.class,mIdCliente);
        agregar.setIdCliente(_idCliente);
        agregar.setNumFactura(_NumFact);
        agregar.setFechaFactura(_FechaFact);
        agregar.setFechaVencimineto(_FechaVenc);
        agregar.setMontoFactura(String.valueOf(_Montofact));
        agregar.setPagosFactura(_Pagosfact);
        agregar.setSaldoFactura(_SaldoFact);
        agregar.setEstadoFactura(_Estadofact);
        saldo.setSaldo(String.valueOf(saldoact+_Montofact));
        realm.commitTransaction();
    }
    //***********************************************************************************************
    public List<clsMisFacturas> ListaFacturasPorId(int id){
        List<clsMisFacturas> facturas = realm.where(clsMisFacturas.class).equalTo("IdCliente",id).findAll();
        return facturas;
    }

    //*****************************************************************************************************
    public clsMisFacturas obtener(int id){
        clsMisFacturas factura = realm.where(clsMisFacturas.class).equalTo("IdClienteFac",id).findFirst();
        return factura;
    }

    //*********************************************************************************************
    public void ListaDeFacturasEliminar(){
        realm.beginTransaction();
        realm.delete(clsMisFacturas.class);
        realm.commitTransaction();
    }

}
