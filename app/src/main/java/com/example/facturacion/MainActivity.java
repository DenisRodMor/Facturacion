package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_regresar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_atras){
            Intent start = new Intent(this, InicioDeSesionActivity.class);
            startActivity(start);
        }
        return super.onOptionsItemSelected(item);
    }

    public void Agregar(View v){
        Intent start = new Intent(this,AgregarClienteActivity.class);
        startActivity(start);
    }
    public void miLista(View v){
        Intent start = new Intent(this, ListaDeClientesActivity.class);
        startActivity(start);
    }
    public void miListaPagos(View v){

        Intent start = new Intent(this, ListaDePagosMIsClientesActivity.class);
        start.putExtra("IdClienteFac",idCliente);
        startActivity(start);
    }
}
