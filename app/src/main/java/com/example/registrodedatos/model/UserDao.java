package com.example.registrodedatos.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.example.registrodedatos.entity.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UserDao {
    private ManagerDataBase managerDataBase;
    Context context;
    View view;
    private User user;

    public UserDao(Context context, View view) {
        this.context = context;
        this.view = view;
        managerDataBase = new ManagerDataBase(this.context);


    }
/*
    public class EntidadDAO {
        // ... (código existente)

        public Map<String, Integer> contarRegistrosPorEstado() {
            // ... (código de la consulta para contar registros por estado)
        }

        public Entidad obtenerRegistroMasReciente() {
            // ... (código de la consulta para obtener el registro más reciente)
        }
    }
    */

    public void insertUser(User user) {

        try {
            SQLiteDatabase db = managerDataBase.getReadableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("use_document", user.getDocument());
                values.put("use_user", user.getUser());
                values.put("use_names", user.getNames());
                values.put("use_lastNames", user.getLastNames());
                values.put("use_password", user.getPassword());
                values.put("use_status", "1");

                long cod = db.insert("users", null, values);
                Snackbar.make(this.view, "Se ha registrado satisfactoriamente" + cod, Snackbar.LENGTH_LONG).show();

            } else {
                Snackbar.make(this.view, " No se registro inetnte nuevamente ", Snackbar.LENGTH_LONG).show();
            }

        } catch (SQLException e) {
            Log.i("DB", "" + e);
        }
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> listUser = new ArrayList<>();
        try {
            SQLiteDatabase db = managerDataBase.getReadableDatabase();
            String query = "select * from users where  use_status = 1;";
            Cursor cursor = db.rawQuery(query,null);
            if (cursor.moveToFirst()){
                do {
                    User user1 = new User();
                    user1.setDocument(cursor.getInt(0));
                    user1.setUser(cursor.getString(1));
                    user1.setNames(cursor.getString(2));
                    user1.setLastNames(cursor.getString(3));
                    user1.setPassword(cursor.getString(4));
                listUser.add(user1);
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Log.i("DB", "" + e);
        }
        return listUser;
    }
}


