package com.samatya.yusuf.usernote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.samatya.yusuf.DB.NoteOperations;
import com.samatya.yusuf.DB.UserOperations;
import com.samatya.yusuf.models.Notes;
import com.samatya.yusuf.models.User;

public class Register extends Activity {
    private User newUser;
    private UserOperations usOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText newUsername = (EditText)findViewById(R.id.editText3);
        final EditText newPassword = (EditText)findViewById(R.id.editText4);
        final EditText newPassword2 = (EditText)findViewById(R.id.editText5);
        Button btn_register = (Button)findViewById(R.id.button3);

        newUser = new User();
        usOp = new UserOperations(this);
        usOp.open();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = newUsername.getText().toString();
                String pass     = newPassword.getText().toString();
                String pass2    = newPassword2.getText().toString();


                if (!pass.equals(pass2)) {
                    Toast.makeText(getApplicationContext(),"Girilen Şifreler Aynı Değil !", Toast.LENGTH_LONG).show();
                    newPassword.setText("");
                    newPassword2.setText("");
                    return;
                }

                if (usOp.checkUser(username)) {
                    Toast.makeText(getApplicationContext(),"Bu Kullanıcı Adı Daha Önce Alınmış !", Toast.LENGTH_LONG).show();
                    newUsername.setText("");
                    newPassword.setText("");
                    newPassword2.setText("");

                    return;
                }

                if (username.equals("")||pass.equals("")||pass2.equals("")){

                    Toast.makeText(getApplicationContext(),"Girdiler Boş Olamaz !", Toast.LENGTH_LONG).show();
                    newUsername.setText("");
                    newPassword.setText("");
                    newPassword2.setText("");
                    return;
                }else{
                    newUser.setUserName(username);
                    newUser.setPassWord(pass);
                    usOp.addUser(newUser);
                    Toast.makeText(getApplicationContext(),"Kullanıcı Oluşturuldu :)", Toast.LENGTH_LONG).show();
                    usOp.close();
                    newUsername.setText("");
                    newPassword.setText("");
                    newPassword2.setText("");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i =new Intent(getBaseContext(),MainActivity.class);
                            startActivity(i);
                            //Do something after 100ms
                        }
                    }, 500);
                }
            }
        });



    }
}
