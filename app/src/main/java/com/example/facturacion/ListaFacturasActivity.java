package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facturacion.Modelos.AdaptadorFacturas;
import com.example.facturacion.Modelos.clsMisClientes;
import com.example.facturacion.Modelos.clsMisFacturas;
import com.example.facturacion.Modelos.clsServicioFacturas;
import com.example.facturacion.Modelos.clsServicioCliente;

import java.util.List;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class  ListaFacturasActivity extends AppCompatActivity {
    TextView mSaldoTotal;
    ListView mLista;
    private AdaptadorFacturas adapter;
    private int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_facturas);
        mLista = findViewById(R.id.ListaFacturas);
        mSaldoTotal = findViewById(R.id.SaldoTotalListFactCampo);
        Bundle mibundle = getIntent().getExtras();
        assert mibundle != null;
        idCliente = mibundle.getInt("IdClientes");
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("CXC").schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        getCliente();
        crearListaFacturas();
    }

    @Override
    public void onResume() {
        super.onResume();
        crearListaFacturas();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_regresar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_atras){

            Intent ma = new Intent(this, MiClienteActivity.class);
            ma.putExtra("IdClientes",idCliente);
            startActivity(ma);
        }
        return super.onOptionsItemSelected(item);
    }

    private void crearListaFacturas(){
        clsServicioFacturas mcliente = new clsServicioFacturas(Realm.getDefaultInstance());
        final List<clsMisFacturas> mServCliente = mcliente.ListaFacturasPorId(idCliente);
        adapter = new AdaptadorFacturas(this,mServCliente,R.layout.adaptador_misfacturas);
        mLista.setAdapter(adapter);
        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clsMisFacturas mdato = mServCliente.get(position);
                Intent actual = new Intent(ListaFacturasActivity.this, MisFacturasActivity.class);
                actual.putExtra("IdClienteFac",mdato.getIdClienteFac());
                actual.putExtra("IdClientes",mdato.getIdCliente());
                startActivity(actual);
            }
        });

    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClientes");
            clsServicioCliente saldo = new clsServicioCliente(Realm.getDefaultInstance());
            clsMisClientes clsMisClientes = saldo.obtener(idCliente);
            try {
               mSaldoTotal.setText(clsMisClientes.getSaldo());
            } catch (Exception e) {
                Log.d("error",e.getMessage());
            }
        } else {
            Toast.makeText(ListaFacturasActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }

}


