package com.example.sem5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class DisplayListActivity extends AppCompatActivity {

   private ListView listViewNotebooks;
   private ArrayList<Notebook> notebookList;
   private ArrayAdapter<Notebook> adapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_display_list);

       listViewNotebooks = findViewById(R.id.listViewNotebooks);


       notebookList = (ArrayList<Notebook>) getIntent().getSerializableExtra("notebook_list_key");

       if (notebookList == null) {
           notebookList = new ArrayList<>();
       }


       adapter = new ArrayAdapter<>(
               this,
               android.R.layout.simple_list_item_1,
               notebookList
       );
       listViewNotebooks.setAdapter(adapter);


       listViewNotebooks.setOnItemClickListener((parent, view, position, id) -> {
           Notebook selectedNotebook = notebookList.get(position);
           Toast.makeText(DisplayListActivity.this, "Selectat: " + selectedNotebook.toString(), Toast.LENGTH_LONG).show();
       });


       listViewNotebooks.setOnItemLongClickListener((parent, view, position, id) -> {
           Notebook removedNotebook = notebookList.remove(position);
           adapter.notifyDataSetChanged();

           Toast.makeText(DisplayListActivity.this, "Șters: " + removedNotebook.toString(), Toast.LENGTH_SHORT).show();
           return true;
       });
   }
}

