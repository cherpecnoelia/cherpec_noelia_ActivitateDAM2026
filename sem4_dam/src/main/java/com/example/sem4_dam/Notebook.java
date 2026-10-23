package com.example.sem4_dam;

import java.io.Serializable;

public class Notebook implements Serializable {
    private String title;          // EditText
    private boolean isImportant;   // CheckBox
    private String category;       // RadioButton / Spinner
    private float priorityRating;  // RatingBar
    private boolean isArchived;    // Switch

    public Notebook(String title, boolean isImportant, String category, float priorityRating, boolean isArchived) {
        this.title = title;
        this.isImportant = isImportant;
        this.category = category;
        this.priorityRating = priorityRating;
        this.isArchived = isArchived;
    }

    @Override
    public String toString() {
        return "Caiet: " + title +
                " | Important: " + (isImportant ? "Da" : "Nu") +
                " | Categorie: " + category +
                " | Rating: " + priorityRating +
                " | Arhivat: " + (isArchived ? "Da" : "Nu");
    }
}