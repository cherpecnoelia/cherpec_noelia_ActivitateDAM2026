package com.example.sem5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotebookAdapter extends BaseAdapter {
    private List<Notebook> notebooks;
    private Context ctx;
    private int resursaLayout;

    public NotebookAdapter(List<Notebook> notebooks, Context ctx, int resursaLayout) {
        this.notebooks = notebooks;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return notebooks.size();
    }

    @Override
    public Object getItem(int i) {
        return notebooks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        TextView titleTV = v.findViewById(R.id.titleTV);
        TextView categoryTV = v.findViewById(R.id.categoryTV);
        TextView ratingTV = v.findViewById(R.id.ratingTV);
        TextView importantTV = v.findViewById(R.id.importantTV);
        TextView archivedTV = v.findViewById(R.id.archivedTV);

        Notebook notebook = (Notebook) getItem(i);

        titleTV.setText(notebook.getTitle());
        categoryTV.setText("Categorie: " + notebook.getCategory());
        ratingTV.setText("Rating: " + notebook.getPriorityRating());
        importantTV.setText(notebook.isImportant() ? "Important" : "Normal");
        archivedTV.setText(notebook.isArchived() ? "Arhivat" : "Activ");

        return v;
    }
}