package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facturacion.Modelos.clsServicioFacturas;
import com.example.facturacion.Modelos.clsMisFacturas;
import com.example.facturacion.Modelos.clsServicioPagosFacturas;
import com.example.facturacion.Modelos.clsPagosFacturasCliente;

import io.realm.Realm;

public class PagoDeFacturasActivity extends AppCompatActivity {
    private int idCliente,mid;
    TextView mNumeroPago,mNumeroCliente,mNumerofactura,mSaldo,mMonto;
    EditText mFecha,mAbono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pago_de_facturas);
        mNumeroPago = findViewById(R.id.txtfactNumerodePagoActivity);
        mNumeroCliente = findViewById(R.id.txtNumClienteActivity);
        mNumerofactura = findViewById(R.id.txtFactIdNumFactActivity);
        mFecha = findViewById(R.id.txtFactFechaActivity);
        mMonto = findViewById(R.id.txtFactMontoFactActivity);
        mAbono = findViewById(R.id.txtFactPagofactActivity);
        mSaldo = findViewById(R.id.txtFactSaldoActivity);
        getCliente();
    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClienteFac");
            mid = mibundle.getInt("IdClienteFac");
            clsServicioPagosFacturas pago1 = new clsServicioPagosFacturas(Realm.getDefaultInstance());
            clsPagosFacturasCliente pago2 = pago1.obtener(idCliente);
            clsServicioFacturas pago = new clsServicioFacturas(Realm.getDefaultInstance());
            clsMisFacturas facturas = pago.obtener(idCliente);
            try {
                mNumeroPago.setText(Integer.toString(pago1.CalcularNumeroPago()));
                mNumeroCliente.setText(Integer.toString(facturas.getIdCliente()));
                mNumerofactura.setText(facturas.getNumFactura());
                mSaldo.setText(facturas.getSaldoFactura());
                mMonto.setText(facturas.getMontoFactura());

            } catch (Exception e) {
                Log.d("error",e.getMessage());
            }
        } else {
            Toast.makeText(PagoDeFacturasActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
    public void cancelarSaldo(View V){

        Bundle mibundle = getIntent().getExtras();
        assert mibundle != null;
        idCliente = mibundle.getInt("IdClienteFac");
        mid = mibundle.getInt("IdClientes");
        try {
            int _NumCliente = Integer.parseInt(mNumeroCliente.getText().toString());
            String _NumFact = mNumerofactura.getText().toString();
            String _Fecha = mFecha.getText().toString();
            int _Monto = Integer.parseInt(mMonto.getText().toString());
            int _Abono = Integer.parseInt(mAbono.getText().toString());
            int _Saldo = Integer.parseInt(mSaldo.getText().toString());
            clsServicioPagosFacturas crear = new clsServicioPagosFacturas(Realm.getDefaultInstance());
            crear.CrearPago(mid,idCliente, _NumCliente, _NumFact, _Fecha, _Monto, _Abono, _Saldo);
            crear.eliminarFact(idCliente);
            Intent pagos = new Intent(this, ListaDePagosMIsClientesActivity.class);
            pagos.putExtra("IdClienteFac",idCliente);
            startActivity(pagos);
        }catch (Exception e){
            Log.d("error",e.getMessage());
        }
    }
}
