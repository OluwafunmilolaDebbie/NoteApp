package com.funmilola.mynoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.funmilola.mynoteapp.R.layout.notes_listview;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private DBManager mDBManager;
    private SimpleCursorAdapter mSimpleCursorAdapter;
    final String [] from = new String[] {DatabaseHelper._ID,
                                DatabaseHelper.SUBJECT,
                                DatabaseHelper.DESC};
    final int [] to = new int [] {R.id.id,
            R.id.title,
            R.id.description};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBManager = new DBManager(this);
        mDBManager.open();
        Cursor cursor = mDBManager.fetch();
        mListView = findViewById(R.id.listView);
        mListView.setEmptyView(findViewById(R.id.textView));
        mSimpleCursorAdapter = new SimpleCursorAdapter(this,
                notes_listview,
                cursor,
                from,
                to,
                0);
        mSimpleCursorAdapter.notifyDataSetChanged();
        mListView.setAdapter(mSimpleCursorAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = view.findViewById(R.id.id);
                TextView titleTextView = view.findViewById(R.id.title);
                TextView descTextView = view.findViewById(R.id.description);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent noteEditor_intent = new Intent(getApplicationContext(), NoteEditor.class);
                noteEditor_intent.putExtra("title", title);
                noteEditor_intent.putExtra("desc", desc);
                noteEditor_intent.putExtra("id", id);
                startActivity(noteEditor_intent);
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record){
            Intent add_mem = new Intent(this, AddNewNote.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}