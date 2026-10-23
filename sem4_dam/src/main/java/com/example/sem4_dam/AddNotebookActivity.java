package com.example.sem4_dam;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;


public class AddNotebookActivity extends AppCompatActivity {

    private EditText etTitle;
    private CheckBox cbImportant;
    private RadioGroup rgCategory;
    private Spinner spPriority;
    private RatingBar ratingBar;
    private Switch swArchive;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notebook);

        etTitle = findViewById(R.id.etTitle);
        cbImportant = findViewById(R.id.cbImportant);
        rgCategory = findViewById(R.id.rgCategory);
        spPriority = findViewById(R.id.spPriority);
        ratingBar = findViewById(R.id.ratingBar);
        swArchive = findViewById(R.id.swArchive);
        btnSave = findViewById(R.id.btnSave);

        // Populare Spinner
        String[] priorities = {"Scăzută", "Medie", "Ridicată"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        spPriority.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            boolean isImportant = cbImportant.isChecked();

            int selectedRadioId = rgCategory.getCheckedRadioButtonId();
            RadioButton selectedRadio = findViewById(selectedRadioId);
            String category = selectedRadio != null ? selectedRadio.getText().toString() : "Nedefinit";

            float rating = ratingBar.getRating();
            boolean isArchived = swArchive.isChecked();

            Notebook notebook = new Notebook(title, isImportant, category, rating, isArchived);

            // Trimitere obiect prin Bundle înapoi în prima activitate
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("notebook_key", notebook);
            resultIntent.putExtras(bundle);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}