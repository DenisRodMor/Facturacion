package com.example.facturacion.Modelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.facturacion.R;

import java.util.List;

public class AdaptadorFacturas extends BaseAdapter {

    private Context context;
    private List<clsMisFacturas> lista;
    private List <clsMisClientes> list;
    private int layout;

    public AdaptadorFacturas(Context context, List<clsMisFacturas> lista, int layout) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public clsMisFacturas getItem(int position) {
        return lista.get(position);

    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String _NumFact = "Numero de Factura: ";
        String _Fecha = "Fecha de inicio: ";
        String _FechaVence = "Fecha de vencimiento: ";
        String _Monto = "Monto total: : ";
        String _Pago = "Pagos: ";
        String _Saldo = "Saldo total: ";
        String _Estado = "Estado: ";
        AdaptadorFacturas.viewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new AdaptadorFacturas.viewHolder();
            vh._NumFact = (TextView) convertView.findViewById(R.id.txtAdapFactNumfact);
            vh._Fecha = (TextView) convertView.findViewById(R.id.txtAdapFactFecha);
            vh._FechaVence = (TextView) convertView.findViewById(R.id.txtAdapFactFechaVence);
            vh._Monto = (TextView) convertView.findViewById(R.id.txtAdapFactMonto);
            vh._Pago = (TextView) convertView.findViewById(R.id.txtadapSFactPago);
            vh._saldo = (TextView) convertView.findViewById(R.id.txtadapSFactsaldo);
            vh._Estado = (TextView) convertView.findViewById(R.id.txtAdapfactEstado);

            convertView.setTag(vh);

        }else{
            vh = (AdaptadorFacturas.viewHolder) convertView.getTag();
        }

        clsMisFacturas cliente = lista.get(position);
        vh._NumFact.setText(_NumFact+cliente.getNumFactura().toString());
        vh._Fecha.setText(_Fecha+cliente.getFechaFactura().toString());
        vh._FechaVence.setText(_FechaVence+cliente.getFechaVencimineto().toString());
        vh._Monto.setText(_Monto+cliente.getMontoFactura().toString());
        vh._Pago.setText(_Pago+(cliente.getPagosFactura().toString()));
        vh._saldo.setText(_Saldo+cliente.getSaldoFactura().toString());
        vh._Estado.setText(_Estado+cliente.getEstadoFactura());
        return convertView;

    }


    public class viewHolder{

        TextView _NumFact;
        TextView _Fecha;
        TextView _FechaVence;
        TextView _Monto;
        TextView _Pago;
        TextView _saldo;
        TextView _Estado;
    }
}

