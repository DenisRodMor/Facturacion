package com.example.facturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InicioDeSesionActivity extends AppCompatActivity {

    EditText mUsuario,mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);
        mPassword = findViewById(R.id.txtEditPassword);
        mUsuario = findViewById(R.id.txtEditUsuario);
    }

    public void Ingresar(View v){
        String _Usuario = "Admin";
        String _Password = "1234";
        mUsuario.requestFocus();
        if(mUsuario.getText().toString().trim().equals(_Usuario)
                &&mPassword.getText().toString().trim().equals(_Password))
        {
            Intent start = new Intent(this,MainActivity.class);
            Toast.makeText(this,"Iniciaste Sesion!",Toast.LENGTH_LONG).show();
            startActivity(start);
        }
        if(!mUsuario.getText().toString().trim().equals(_Usuario)){
            mUsuario.setError("Usuario no valido");
        }
        if(!mPassword.getText().toString().trim().equals(_Password)){
            mPassword.setError("clave no valida");
        }
    }

    public void corregirDatos(View view){
        mPassword.setText("");
        mUsuario.setText("");
        mUsuario.requestFocus();
    }
}
