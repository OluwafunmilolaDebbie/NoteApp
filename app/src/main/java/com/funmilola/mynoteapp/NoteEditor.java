package com.funmilola.mynoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteEditor extends Activity implements View.OnClickListener {
    EditText subjectEditText, descriptionEditText;
    Button addButton, deleteButton;
    long _id;
    private DBManager mDBManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit note");
        setContentView(R.layout.activity_note_editor);
        mDBManager = new DBManager(this);
        mDBManager.open();
        subjectEditText = findViewById(R.id.subject_edittext);
        descriptionEditText = findViewById(R.id.description_editext);
        addButton = findViewById(R.id.btn_update);
        deleteButton = findViewById(R.id.btn_update);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);
        subjectEditText.setText(name);
        descriptionEditText.setText(desc);
        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case
            R.id.btn_update:
            String title = subjectEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            mDBManager.update(_id, title, description);
            this.returnHome();
            break;
            case
                R.id.btn_delete:
                mDBManager.delete(_id);
            this.returnHome();
            break;
        }


    }

    private void returnHome() {
        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainActivityIntent);
    }
}