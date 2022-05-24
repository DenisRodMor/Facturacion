package com.example.facturacion.Modelos;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class clsServicioCliente {

    private Realm realm;

    public clsServicioCliente(Realm _realm)
    {
        this.realm = _realm;
    }

    public clsServicioCliente() {

    }

    //********************************************************************************
    private int CalcularId()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number idActual = mRealm.where(clsMisClientes.class).max("IdCliente");
        int siguienteid;
        if(idActual==null){
            siguienteid = 1;
        }else{
            siguienteid = idActual.intValue() + 1;
        }
        return siguienteid;
    }
    //*********************************************************************************
    public void CrearCliente(String _Nombre, String _Cedula,String _TelefonoCel, String _Correo, String _Saldo){

        int mIdCliente = CalcularId();
        realm.beginTransaction();
        clsMisClientes agregar = realm.createObject(clsMisClientes.class,mIdCliente);
        agregar.setNombre(_Nombre);
        agregar.setCedula(_Cedula);
        agregar.setTelefonoCelular(_TelefonoCel);
        agregar.setCorreo(_Correo);
        agregar.setSaldo(_Saldo);
        realm.commitTransaction();
    }
    //*********************************************************************************
    public List<clsMisClientes> ListaClientes(){
        RealmResults<clsMisClientes> mresultado = realm.where(clsMisClientes.class).findAll();
        return realm.copyFromRealm(mresultado);
    }

    //**************************************************************************************************
    public void updatebyId(int id,String _Nombre, String _Cedula,String _TelefonoCel, String _Correo) {

        realm.beginTransaction();
        clsMisClientes cliente1 = realm.where(clsMisClientes.class).equalTo("IdCliente", id).findFirst();
        cliente1.setNombre(_Nombre);
        cliente1.setCedula(_Cedula);
        cliente1.setTelefonoCelular(_TelefonoCel);
        cliente1.setCorreo(_Correo);
        realm.insertOrUpdate(cliente1);
        realm.commitTransaction();
        if (cliente1 != null) {
            Log.d("TAG", "id" + cliente1.getIdCliente() + "Nombre" + cliente1.getNombre() + "Cedula" + cliente1.getCedula() + "Telefono" + cliente1.getTelefonoCelular() + "Correo" + cliente1.getCorreo());
        }
    }
    //**************************************************************************************************
    public clsMisClientes obtener(int id){
        clsMisClientes cliente1 = realm.where(clsMisClientes.class).equalTo("IdCliente",id).findFirst();
        return cliente1;
    }
    //**************************************************************************************************
    public void eliminar(int id){

        clsMisClientes cl = obtener(id);
        realm.beginTransaction();
        cl.deleteFromRealm();
        realm.commitTransaction();
    }
}
