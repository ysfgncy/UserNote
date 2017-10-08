package com.samatya.yusuf.usernote;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import com.samatya.yusuf.DB.NoteOperations;

public class UserNotes extends AppCompatActivity {


    private NoteOperations noOp;
    ArrayList<HashMap<String, String>> noteList;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_notes);
        Bundle extra =getIntent().getExtras();
        final String note_user_id = extra.getString("userId");

        Button newNote = (Button)findViewById(R.id.button4);
        ListView lv = (ListView)findViewById(R.id.listView);

        newNote.setText(note_user_id);
        noOp = new NoteOperations(this);
        noOp.open();

        noteList= noOp.getAllNotes(Integer.parseInt(note_user_id));
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,noteList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        noOp.close();

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mode ="newNote";
                Intent i =new Intent(getBaseContext(),Note.class);
                i.putExtra("newNote",mode);
                i.putExtra("userId",note_user_id);
                startActivity(i);
            }
        });

    }


}
