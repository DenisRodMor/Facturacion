package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.facturacion.Modelos.AdaptadorPagosFacturas;
import com.example.facturacion.Modelos.clsServicioPagosFacturas;
import com.example.facturacion.Modelos.clsPagosFacturasCliente;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListaDePagosMIsClientesActivity extends AppCompatActivity {

    ListView mListaPagos;
    private AdaptadorPagosFacturas adapter;
    private int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pagos_mis_clientes);
        mListaPagos = findViewById(R.id.ListViewPagos);
        Bundle mibundle = getIntent().getExtras();
        assert mibundle != null;
        idCliente = mibundle.getInt("IdClienteFac");
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("CXC").schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        crearListaFacturas();
    }

    @Override
    public void onResume() {
        super.onResume();
        crearListaFacturas();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pagos, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_volverAtras){

            Intent ma = new Intent(this, MainActivity.class);
            startActivity(ma);
        }
        if(id==R.id.menu_eliminarPago){
            clsServicioPagosFacturas funcionesPagos = new clsServicioPagosFacturas(Realm.getDefaultInstance());
            funcionesPagos.ListaDePagosEliminar();

            onResume();

        }

        return super.onOptionsItemSelected(item);
    }

    private void crearListaFacturas(){
        clsServicioPagosFacturas funcionesPagos = new clsServicioPagosFacturas(Realm.getDefaultInstance());
        final List<clsPagosFacturasCliente> mServCliente = funcionesPagos.ListaDePagos();
        adapter = new AdaptadorPagosFacturas(this,mServCliente,R.layout.adaptador_mispagos);
        mListaPagos.setAdapter(adapter);
    }
}
