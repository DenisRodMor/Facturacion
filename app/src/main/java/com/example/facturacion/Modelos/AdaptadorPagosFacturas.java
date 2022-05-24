package com.example.facturacion.Modelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.facturacion.R;

import java.util.List;

public class AdaptadorPagosFacturas extends BaseAdapter {


    private Context context;
    private List<clsPagosFacturasCliente> lista;
    private int layout;

    public AdaptadorPagosFacturas(Context context, List<clsPagosFacturasCliente> lista, int layout) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public clsPagosFacturasCliente getItem(int position) {
        return lista.get(position);

    }


    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String _NumPago = "Numero de pago: ";
        String _NumCliente = "Cliente: ";
        String _NumFact = "Numero de factura: ";
        String _Fecha = "Fecha de pago: : ";
        String _Abono = "Abono: ";
        String _Saldo = "Saldo total: ";
        String Monto = "Monto total: ";
        AdaptadorPagosFacturas.viewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new AdaptadorPagosFacturas.viewHolder();
            vh._NumFact = (TextView) convertView.findViewById(R.id.txtAdapPagoNumFact);
            vh._Abono = (TextView) convertView.findViewById(R.id.txtadapSPagoAbono);
            vh._mFecha = (TextView) convertView.findViewById(R.id.txtAdapPagoFecha);
            vh._mMonto = (TextView) convertView.findViewById(R.id.txtAdapPagoMonto);
            vh._msaldo = (TextView) convertView.findViewById(R.id.txtadapSPagosaldo);
            vh._NumCliente = (TextView) convertView.findViewById(R.id.txtAdapPagoNumCliente);
            vh._NumPago = (TextView) convertView.findViewById(R.id.txtAdappagoNumPago);
            convertView.setTag(vh);

        }else{
            vh = (AdaptadorPagosFacturas.viewHolder) convertView.getTag();
        }

        clsPagosFacturasCliente cliente = lista.get(position);

        vh._NumFact.setText(_NumFact+cliente.getNumFact().toString());
        vh._mFecha.setText(_Fecha+cliente.getFechaPago().toString());
        vh._Abono.setText(_Abono+cliente.getAbono().toString());
        vh._mMonto.setText(Monto+cliente.getMontoFacturaPago().toString());
        vh._NumCliente.setText(_NumCliente+(cliente.getIdClientePago()));
        vh._NumPago.setText(_NumPago+cliente.getSaldoFact().toString());
        vh._msaldo.setText(_Saldo+cliente.getSaldoFact());
        return convertView;

    }


    public class viewHolder{

        TextView _NumPago;
        TextView _NumCliente;
        TextView _mFecha;
        TextView _mMonto;
        TextView _Abono;
        TextView _msaldo;
        TextView _NumFact;

    }
}
