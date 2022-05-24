package com.example.facturacion.Modelos;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class clsServicioPagosFacturas {

    private Realm realm;

    public clsServicioPagosFacturas(Realm _realm) {
        this.realm = _realm;
    }

    //***********************************************************************************
    public int CalcularNumeroPago()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number idActual = mRealm.where(clsPagosFacturasCliente.class).max("NumeroDePago");
        int siguienteid;

        if(idActual==null){
            siguienteid = 1;
        }else{
            siguienteid = idActual.intValue() + 1;
        }
        return siguienteid;
    }
    //**********************************************************************************
    public void CrearPago(int _idCliente, int _idClienteFac, int idClientePago, String numFact, String fechaPago, int montoFacturaPago,
                          int abono, int saldoFact){
        int saldoact,saldoactcliente,pago;
        int mIdClientePago = CalcularNumeroPago();
        clsMisFacturas saldo = realm.where(clsMisFacturas.class).equalTo("IdClienteFac",_idClienteFac).findFirst();
        clsMisClientes saldocliente = realm.where(clsMisClientes.class).equalTo("IdCliente",_idCliente).findFirst();
        if(saldo.getSaldoFactura()==null){
            saldo.setSaldoFactura("0");
        }
        saldoact = Integer.parseInt(saldo.getSaldoFactura());
        if(saldocliente.getSaldo()==null){
            saldocliente.setSaldo("0");
        }
        saldoactcliente = Integer.parseInt(saldocliente.getSaldo());
        pago = Integer.parseInt(saldo.getPagosFactura());
        realm.beginTransaction();
        clsPagosFacturasCliente agregar = realm.createObject(clsPagosFacturasCliente.class,mIdClientePago);

        agregar.setIdClientePago(idClientePago);
        agregar.setNumFact(numFact);
        agregar.setFechaPago(fechaPago);
        agregar.setMontoFacturaPago(String.valueOf(montoFacturaPago));
        agregar.setAbono(String.valueOf(abono));
        agregar.setSaldoFact(String.valueOf(saldoFact-abono));
        saldo.setSaldoFactura(String.valueOf(saldoact-abono));
        saldo.setPagosFactura(String.valueOf(pago+abono));
        saldocliente.setSaldo(String.valueOf(saldoactcliente-abono));
        realm.commitTransaction();
    }
    //********************************************************************************************
    public List<clsPagosFacturasCliente> ListaDePagos(){
        RealmResults<clsPagosFacturasCliente> mresultado = realm.where(clsPagosFacturasCliente.class).findAll();
        return realm.copyFromRealm(mresultado);
    }
    //******************************************************************************************
    public clsPagosFacturasCliente obtener(int id){
        clsPagosFacturasCliente pago = realm.where(clsPagosFacturasCliente.class).equalTo("IdClientePago",id).findFirst();
        return pago;
    }
    //*********************************************************************************************
    public void ListaDePagosEliminar(){
        realm.beginTransaction();
        realm.delete(clsPagosFacturasCliente.class);
        realm.commitTransaction();
    }
    public void eliminarFact(int id){
        clsMisFacturas saldo = realm.where(clsMisFacturas.class).equalTo("IdClienteFac",id).findFirst();
        int saldofact = Integer.parseInt(saldo.getSaldoFactura());
        if (saldofact==0) {
            clsMisFacturas facturas = realm.where(clsMisFacturas.class).equalTo("IdClienteFac", id).findFirst();
            realm.beginTransaction();
            facturas.deleteFromRealm();
            realm.commitTransaction();
        }
    }

}
