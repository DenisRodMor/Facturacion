package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facturacion.Modelos.clsServicioFacturas;
import com.example.facturacion.Modelos.clsMisFacturas;

import io.realm.Realm;

public class MisFacturasActivity extends AppCompatActivity {
    TextView mFactNumeroFact,mFactFecha,mFactFechaVence,mFactMonto,mFactPago,mFactSaldo,mFactEstado;

    private int idCliente,mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_facturas);
        mFactNumeroFact = findViewById(R.id.txtCampoFactNumeroFact);
        mFactFecha = findViewById(R.id.txtCampoMosFactFecha);
        mFactFechaVence = findViewById(R.id.txtCampoMosFactFechaVence);
        mFactMonto = findViewById(R.id.txtCampoMosFactMonto);
        mFactPago = findViewById(R.id.txtCampoMosFactPago);
        mFactSaldo = findViewById(R.id.txtCampoMosFactSaldo);
        mFactEstado = findViewById(R.id.txtCampoFactEstado);
        getCliente();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pagar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_pantalladepago) {
            Intent pantalla = new Intent(this, PagoDeFacturasActivity.class);
            pantalla.putExtra("IdClienteFac", idCliente);
            pantalla.putExtra("IdClientes", mid);
            startActivity(pantalla);
        }
        return super.onOptionsItemSelected(item);
    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClienteFac");
            mid = mibundle.getInt("IdClientes");
            clsServicioFacturas mservicio = new clsServicioFacturas(Realm.getDefaultInstance());
             clsMisFacturas facturas = mservicio.obtener(idCliente);
            try {
                mFactNumeroFact.requestFocus();
                mFactNumeroFact.setText(facturas.getNumFactura());
                mFactFecha.setText(facturas.getFechaFactura());
                mFactFechaVence.setText(facturas.getFechaVencimineto());
                mFactMonto.setText(facturas.getMontoFactura());
                mFactPago.setText(facturas.getPagosFactura());
                mFactSaldo.setText(facturas.getSaldoFactura());
                mFactEstado.setText(facturas.getEstadoFactura());


            } catch (Exception e) {
                Toast.makeText(MisFacturasActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(MisFacturasActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }

}
