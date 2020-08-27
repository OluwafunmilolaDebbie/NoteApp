package com.funmilola.mynoteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewNote extends Activity implements View.OnClickListener {

    private Button addNote;
    private EditText noteSubject;
    private EditText noteDescription;
    DBManager mDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add_note);
        setContentView(R.layout.activity_add_new_note);
        addNote = findViewById(R.id.add_note);
        noteSubject = findViewById(R.id.noteSubject_editText);
        noteDescription = findViewById(R.id.noteDescription_editText);
        mDBManager = new DBManager(this);
        mDBManager.open();
        addNote.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case
                R.id.add_note:
                final  String noteTitle = noteSubject.getText().toString();
                final  String noteDesc = noteDescription.getText().toString();
                mDBManager.insert(noteTitle, noteDesc);
                Intent main = new Intent(AddNewNote.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

        }
    }
}