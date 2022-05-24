package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.facturacion.Modelos.AdaptadorClientes;
import com.example.facturacion.Modelos.clsMisClientes;
import com.example.facturacion.Modelos.clsServicioCliente;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListaDeClientesActivity extends AppCompatActivity {

    ListView mLista;
    private AdaptadorClientes adapter;
    int mIdClienteSel;

    @Override
    public void onResume() {
        super.onResume();
        clsServicioCliente mcliente = new clsServicioCliente(Realm.getDefaultInstance());
        List<clsMisClientes> mServCliente = mcliente.ListaClientes();

        adapter = new AdaptadorClientes(this,mServCliente, R.layout.adaptador_miscliente);
        mLista.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_clientes);
        mLista = findViewById(R.id.txtListView);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("CXC").schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        clsServicioCliente mcliente = new clsServicioCliente(Realm.getDefaultInstance());
        final List<clsMisClientes> mServCliente = mcliente.ListaClientes();
        adapter = new AdaptadorClientes(this,mServCliente,R.layout.adaptador_miscliente);
        mLista.setAdapter(adapter);

        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mLista.setAdapter(adapter);
                clsMisClientes mdato = mServCliente.get(position);
                 Intent actual = new Intent(ListaDeClientesActivity.this, MiClienteActivity.class);
                actual.putExtra("IdClientes",mdato.getIdCliente());
                startActivity(actual);
                mIdClienteSel = mdato.getIdCliente();

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_regresar, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.menu_atras){
            Intent Mainactivity = new Intent(this,MainActivity.class);
            startActivity(Mainactivity);
        }



        return super.onOptionsItemSelected(item);
    }
}
