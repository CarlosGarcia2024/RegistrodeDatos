package com.example.registrodedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.registrodedatos.entity.User;
import com.example.registrodedatos.model.UserDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etDocumento;
    private EditText etUsuario;
    private EditText etNombres;
    private EditText etApelIidos;
    private EditText etContra;
    private ListView listUsers;
    SQLiteDatabase sqLiteDatabase;
    private int documento;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String contra;
    private Context context;

    private Button btnSave;
    private Button btnListUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
context = this;
        initObject();
        btnSave.setOnClickListener(this::createUser);
        btnListUsers.setOnClickListener(this::listUserShow);
    }

    private void createUser(View view) {
getData();
User user = new User(documento,nombres,apellidos,usuario,contra);
UserDao dao = new UserDao(context,view);
dao.insertUser(user);
listUserShow(view);
    }
    private void listUserShow(View view) {
        UserDao dao = new UserDao(context,findViewById(R.id.lvLista));
        ArrayList<User> users = dao.getUserList();
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,users);
        listUsers.setAdapter(adapter);
    }

    private void getData(){
        documento = Integer.parseInt( etDocumento.getText().toString());
        usuario = etUsuario.getText().toString();
        nombres = etNombres.getText().toString();
        apellidos = etApelIidos.getText().toString();
        contra = etContra.getText().toString();

        //validacion de datos con expresiones regulares
    }

    private void initObject(){

        btnSave = findViewById(R.id.btnRegistrar);
        btnListUsers = findViewById(R.id.btnListar);
        etNombres = findViewById(R.id.etNombres);
        etApelIidos = findViewById(R.id.etApellidos);
        etDocumento = findViewById(R.id.etDocumento);
        etContra = findViewById(R.id.etContra);
        etUsuario = findViewById(R.id.etUsuario);
        listUsers = findViewById(R.id.lvLista);

    }
}