package com.example.sem5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayListActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT_NOTEBOOK = 209;

    private ListView listViewNotebooks;
    private ArrayList<Notebook> notebookList;
    private NotebookAdapter adapter;
    private int idModificat = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        listViewNotebooks = findViewById(R.id.listViewNotebooks);

        notebookList = getIntent().getParcelableArrayListExtra("notebook_list_key");

        if (notebookList == null) {
            notebookList = new ArrayList<>();
        }

        adapter = new NotebookAdapter(notebookList, this, R.layout.row_notebook);
        listViewNotebooks.setAdapter(adapter);

        listViewNotebooks.setOnItemClickListener((parent, view, position, id) -> {
            idModificat = position;

            Intent intent = new Intent(DisplayListActivity.this, AddNotebookActivity.class);
            intent.putExtra("notebook_key", notebookList.get(position));
            startActivityForResult(intent, REQUEST_EDIT_NOTEBOOK);
        });

        listViewNotebooks.setOnItemLongClickListener((parent, view, position, id) -> {
            Notebook removedNotebook = notebookList.remove(position);
            adapter.notifyDataSetChanged();
            trimiteListaInapoi();

            Toast.makeText(DisplayListActivity.this, "Șters: " + removedNotebook.toString(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EDIT_NOTEBOOK && resultCode == RESULT_OK && data != null) {
            Notebook notebookModificat = data.getParcelableExtra("notebook_key");

            if (notebookModificat != null && idModificat != -1) {
                notebookList.set(idModificat, notebookModificat);
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
            }
        }
    }

    private void trimiteListaInapoi() {
        Intent resultIntent = new Intent();
        resultIntent.putParcelableArrayListExtra("notebook_list_key", notebookList);
        setResult(RESULT_OK, resultIntent);
    }
}