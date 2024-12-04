package com.ium.example.listview;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        lista = new ArrayList<>();
        inizializza(10);

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lista);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "posizione: " + i + " nome: " + lista.get(i), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inizializza(int numero){
        for(int i=0; i <=numero; i++){
            lista.add("Persona: "+i);
        }
    }

}