package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facturacion.Modelos.clsMisClientes;
import com.example.facturacion.Modelos.clsServicioFacturas;
import com.example.facturacion.Modelos.clsServicioCliente;

import io.realm.Realm;

public class MiClienteActivity extends AppCompatActivity {

    TextView mCedula,mNombreMos,mTelefono,mCorreo;

    int id;
    int idCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cliente);

        mTelefono = findViewById(R.id.txtMostrarTelefono);
        mNombreMos = findViewById(R.id.txvMostrarNombre);
        mCedula = findViewById(R.id.txtMostrarCed);
        mCorreo = findViewById(R.id.txtCampoCorreo);
        getCliente();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_funciones, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.menu_eliminarClientefuncion){
            clsServicioFacturas fac = new clsServicioFacturas(Realm.getDefaultInstance());
            clsServicioCliente borrar = new clsServicioCliente(Realm.getDefaultInstance());
            fac.ListaDeFacturasEliminar();
            borrar.eliminar(idCliente);
            Toast.makeText(MiClienteActivity.this,"Se ha borrado con exito",Toast.LENGTH_LONG).show();
            Intent mpa = new Intent(this, ListaDeClientesActivity.class);
            startActivity(mpa);
        }
        if(id==R.id.menu_retrocederfuncion){
            Intent mpa = new Intent(this, ListaDeClientesActivity.class);
            startActivity(mpa);
        }
        if(id==R.id.menu_agregarfactfuncion){
            Intent ac = new Intent(this,AgregarFacturasActivity.class);
            ac.putExtra("IdClientes",idCliente);
            startActivity(ac);
        }
        if(id==R.id.menu_verfacturasfuncion){
            Intent ac = new Intent(this,ListaFacturasActivity.class);
            ac.putExtra("IdClientes",idCliente);
            startActivity(ac);
        }
        if(id==R.id.menu_editarClientefuncion){
            Intent ac = new Intent(this,ActualizarDatosActivity.class);
            ac.putExtra("IdClientes",idCliente);
            startActivity(ac);
        }


        return super.onOptionsItemSelected(item);
    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClientes");
            id = mibundle.getInt("IdClientes");
            clsServicioCliente mservicio = new clsServicioCliente(Realm.getDefaultInstance());
            clsMisClientes cliente = mservicio.obtener(idCliente);
            try {
                mNombreMos.requestFocus();
                mNombreMos.setText(cliente.getNombre());
                mCedula.setText(cliente.getCedula());
                mTelefono.setText(cliente.getTelefonoCelular());
                mCorreo.setText(cliente.getCorreo());


            } catch (Exception e) {
                Toast.makeText(MiClienteActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(MiClienteActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }

}


