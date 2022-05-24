package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facturacion.Modelos.clsMisClientes;
import com.example.facturacion.Modelos.clsServicioCliente;

import io.realm.Realm;

public class ActualizarDatosActivity extends AppCompatActivity {

    EditText mNombreActual,mCedulaActual,mTelefonoActual,mCorreoActual;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        mNombreActual = findViewById(R.id.txtAgregarNombreaPel);
        mCedulaActual = findViewById(R.id.txtAgregaredulas);
        mTelefonoActual = findViewById(R.id.txtAgregarTelefono);
        mCorreoActual = findViewById(R.id.txtAgregarCorreooo);
        getCliente();

    }
    private void getCliente(){
        Bundle mibundle = getIntent().getExtras();
        if(mibundle != null){
            int idCliente = mibundle.getInt("IdClientes");
            id = mibundle.getInt("IdClientes");
            clsServicioCliente mservicio = new clsServicioCliente(Realm.getDefaultInstance());
            clsMisClientes cliente = mservicio.obtener(idCliente);
            try {
                mNombreActual.requestFocus();
                mNombreActual.setText(cliente.getNombre());
                mCedulaActual.setText(cliente.getCedula());
                mTelefonoActual.setText(cliente.getTelefonoCelular());
                mCorreoActual.setText(cliente.getCorreo());

            }catch(Exception e){
                Toast.makeText(ActualizarDatosActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(ActualizarDatosActivity.this,"No hay datos",Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarDatos(View v){
        clsServicioCliente actualizar = new clsServicioCliente(Realm.getDefaultInstance());
        clsMisClientes cliente = actualizar.obtener(id);
        mNombreActual.requestFocus();
        String nombre = mNombreActual.getText().toString();
        String cedula = mCedulaActual.getText().toString();
        String telefono = mTelefonoActual.getText().toString();
        String correo = mCorreoActual.getText().toString();

        actualizar.updatebyId(id,nombre,cedula,telefono,correo);
        Intent mp = new Intent(this, ListaDeClientesActivity.class);
        startActivity(mp);
    }
    public void borrar(View v){
        mNombreActual.setText("");
        mCorreoActual.setText("");
        mCedulaActual.setText("");
        mTelefonoActual.setText("");
    }
}
