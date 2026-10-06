package com.example.webservicessencillo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.webservicessencillo.datos.Conexion;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText etRut, etPassword;
    Button btnAcceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relacionamosVistas();
    }

    public void relacionamosVistas() {
        etRut = findViewById(R.id.rut);
        etPassword = findViewById(R.id.password);
        btnAcceso = findViewById(R.id.acceso);
    }

    public void validar(View v) {
        final String rut = etRut.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (rut.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Complete el RUT y la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rut.length() < 8 || rut.length() > 10) {
            Toast.makeText(this, "El RUT debe tener entre 8 y 10 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String url = Conexion.URL_WEB_SERVICES + "ingreso.php?rut=" 
                    + URLEncoder.encode(rut, "UTF-8") 
                    + "&password=" + URLEncoder.encode(password, "UTF-8");

            RequestQueue servicio = Volley.newRequestQueue(this);
            StringRequest respuesta = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            
                            if (response.contains("exitoso") || response.contains("concedido")) {
                                etRut.setText("");
                                etPassword.setText("");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String detalle = "Error de comunicación";
                            if (error.networkResponse != null) {
                                detalle += " (HTTP " + error.networkResponse.statusCode + ")";
                            } else if (error.getMessage() != null && !error.getMessage().isEmpty()) {
                                detalle += ": " + error.getMessage();
                            } else {
                                detalle += " (" + error.getClass().getSimpleName() + ")";
                            }
                            Toast.makeText(getApplicationContext(), detalle, Toast.LENGTH_LONG).show();
                        }
                    }
            );
            servicio.add(respuesta);
        } catch (Exception e) {
            Toast.makeText(this, "Error al procesar petición: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
