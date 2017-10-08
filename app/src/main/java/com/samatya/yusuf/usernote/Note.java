package com.samatya.yusuf.usernote;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samatya.yusuf.DB.NoteOperations;
import com.samatya.yusuf.models.Notes;

public class Note extends AppCompatActivity {

    private Notes note ;
    private NoteOperations noOp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Bundle extra =getIntent().getExtras();
        final String note_user_id = extra.getString("userId");

        final EditText title = (EditText)findViewById(R.id.editText8);
        final EditText content = (EditText)findViewById(R.id.editText9);
        Button saveNote = (Button)findViewById(R.id.button5);

        noOp = new NoteOperations(Note.this);
        noOp.open();

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String baslik = title.getText().toString();
                String icerik = content.getText().toString();

                note = new Notes();
                note.setNote_user_id(Integer.parseInt(note_user_id));
                note.setTitle(baslik);
                note.setContent(icerik);
                noOp.addNote(note);
                noOp.close();
                Toast.makeText(getApplicationContext(),"Not Oluşturuldu :)", Toast.LENGTH_LONG).show();
                title.setText("");
                content.setText("");
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i =new Intent(getBaseContext(),UserNotes.class);
                        i.putExtra("userId",note_user_id);
                        startActivity(i);
                        //Do something after 500ms
                    }
                }, 980);





            }
        });





    }
}
