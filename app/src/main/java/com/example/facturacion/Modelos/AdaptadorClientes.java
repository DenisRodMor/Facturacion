package com.example.facturacion.Modelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.facturacion.R;

import java.util.List;

public class AdaptadorClientes extends BaseAdapter {

    private Context context;
    private List<clsMisClientes> lista;
    private int layout;

    public AdaptadorClientes(Context context, List<clsMisClientes> lista, int layout) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
    }




    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public clsMisClientes getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String _ID = "ID: ";
        String _Nombre = "Nombre: ";
        String _Cedula = "Identificacion: ";
        String _Telefono = "Telefono: ";
        String _Correo = "Email: ";
        String _Saldo = "Saldo total: ";
        AdaptadorClientes.viewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new viewHolder();
            vh._id = (TextView) convertView.findViewById(R.id.txtAdapIdCliente);
            vh._nombre = (TextView) convertView.findViewById(R.id.txtAdapNombre);
            vh._Cedula = (TextView) convertView.findViewById(R.id.txtAdapCed);
            vh._Telefono = (TextView) convertView.findViewById(R.id.txtAdapCel);
            vh._Correo = (TextView) convertView.findViewById(R.id.txtAdapEmail);
            vh._saldo = (TextView) convertView.findViewById(R.id.txtadapSaldo);
            convertView.setTag(vh);

        }else{
            vh = (AdaptadorClientes.viewHolder) convertView.getTag();
        }
        clsMisClientes cliente = lista.get(position);

        vh._id.setText(_ID+String.valueOf(cliente.getIdCliente()));
        vh._nombre.setText(_Nombre+cliente.getNombre().toString());
        vh._Cedula.setText(_Cedula+cliente.getCedula().toString());
        vh._Telefono.setText(_Telefono+cliente.getTelefonoCelular().toString());
        vh._Correo.setText(_Correo+cliente.getCorreo().toString());
        vh._saldo.setText(_Saldo+String.valueOf(cliente.getSaldo()));
        return convertView;

    }


    public static class viewHolder{
        TextView _id;
        TextView _nombre;
        TextView _Cedula;
        TextView _Telefono;
        TextView _Correo;
        TextView _saldo;
    }
}
