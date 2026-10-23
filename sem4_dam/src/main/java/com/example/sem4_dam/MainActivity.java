package com.example.sem4_dam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnAdd;

    private Button btnAddAi;
    private final ActivityResultLauncher<Intent> addNotebookLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Notebook notebook = (Notebook) bundle.getSerializable("notebook_key");
                        if (notebook != null) {
                            // Afișare prin TextView și Toast
                            tvResult.setText(notebook.toString());
                            Toast.makeText(MainActivity.this, "Obiect primit: " + notebook.toString(), Toast.LENGTH_LONG).show();
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

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNotebookActivity.class);
            addNotebookLauncher.launch(intent);
        });
        btnAddAi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNotebookAiActivity.class);
            addNotebookLauncher.launch(intent);
        });
    }
}