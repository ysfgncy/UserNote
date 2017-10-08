package com.samatya.yusuf.usernote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samatya.yusuf.DB.DbHandler;

import static com.samatya.yusuf.DB.DbHandler.KEY_PASSWORD;
import static com.samatya.yusuf.DB.DbHandler.KEY_USERNAME;
import static com.samatya.yusuf.DB.DbHandler.KEY_USER_ID;
import static com.samatya.yusuf.DB.DbHandler.TABLE_USER;


public class MainActivity extends Activity {
    Cursor cursor;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase  database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button)findViewById(R.id.button);
        Button register = (Button)findViewById(R.id.button2);
        final EditText userName = (EditText)findViewById(R.id.editText);
        final EditText passWord = (EditText)findViewById(R.id.editText2);

        dbhelper = new DbHandler(this);
        database = dbhelper.getReadableDatabase();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                cursor = database.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE "+KEY_USERNAME+"=? AND "+KEY_PASSWORD+"=?",new String[]{userName.getText().toString(),passWord.getText().toString()});

                if (cursor != null) {
                    if(cursor.getCount() > 0) {

                        cursor.moveToFirst();
                        String _userId= cursor.getString(cursor.getColumnIndex(KEY_USER_ID));
                        Toast.makeText(MainActivity.this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show();
                        userName.setText("");
                        passWord.setText("");
                        Intent intent = new Intent(MainActivity.this,UserNotes.class);
                        intent.putExtra("userId",_userId);
                        startActivity(intent);

                        finish();
                    }
                    else {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("HATA");
                        builder.setMessage("Kullanıcı Adı yada Şifre Hatalı.");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                userName.setText("");
                                passWord.setText("");

                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);

            }
        });


    }
}
