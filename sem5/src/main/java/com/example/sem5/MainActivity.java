package com.example.sem5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnAdd;
    private Button btnAddAi;
    private Button btnViewList;


    private ArrayList<Notebook> notebookList = new ArrayList<>();

    private final ActivityResultLauncher<Intent> addNotebookLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Notebook notebook = (Notebook) bundle.getSerializable("notebook_key");
                        if (notebook != null) {
                            notebookList.add(notebook);
                            tvResult.setText("Total notițe salvate: " + notebookList.size());
                            Toast.makeText(MainActivity.this, "Notiță adăugată în listă!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnAddAi = findViewById(R.id.btnAddAi);
        btnViewList = findViewById(R.id.btnViewList);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNotebookActivity.class);
            addNotebookLauncher.launch(intent);
        });

        btnAddAi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNotebookAiActivity.class);
            addNotebookLauncher.launch(intent);
        });


        btnViewList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DisplayListActivity.class);
            intent.putExtra("notebook_list_key", notebookList);
            startActivity(intent);
        });
    }
}