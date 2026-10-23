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
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNotebookAiActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_notebook_ai);

        etTitle = findViewById(R.id.etTitleAi);
        cbImportant = findViewById(R.id.cbImportantAi);
        rgCategory = findViewById(R.id.rgCategoryAi);
        spPriority = findViewById(R.id.spPriorityAi);
        ratingBar = findViewById(R.id.ratingBarAi);
        swArchive = findViewById(R.id.swArchiveAi);
        btnSave = findViewById(R.id.btnSaveAi);


        String[] priorityOptions = {"Scăzută", "Medie", "Ridicată"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorityOptions);
        spPriority.setAdapter(adapter);


        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "Vă rugăm să introduceți un titlu!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isImportant = cbImportant.isChecked();

            int selectedRadioId = rgCategory.getCheckedRadioButtonId();
            RadioButton selectedRadio = findViewById(selectedRadioId);
            String category = selectedRadio != null ? selectedRadio.getText().toString() : "Nedefinit";

            float rating = ratingBar.getRating();
            boolean isArchived = swArchive.isChecked();


            Notebook notebook = new Notebook(title, isImportant, category, rating, isArchived);


            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("notebook_key", notebook);
            resultIntent.putExtras(bundle);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}