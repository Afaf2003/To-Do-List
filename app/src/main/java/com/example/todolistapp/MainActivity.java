package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemAdapter;
    Button btn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item has been Removed", Toast.LENGTH_LONG).show();
                items.remove(i);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem(View view) {
        EditText editText = findViewById(R.id.editTextTextPersonName);
        String itemString = editText.getText().toString();

        if(!(itemString.equals(""))){
            itemAdapter.add(itemString);
            Toast.makeText(getApplicationContext(), "Inserted to To-do List", Toast.LENGTH_LONG).show();
            editText.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Enter the Text", Toast.LENGTH_LONG).show();
        }
    }
}