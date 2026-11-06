package com.example.sem5;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Notebook implements Parcelable {
    private String title;
    private boolean isImportant;
    private String category;
    private float priorityRating;
    private boolean isArchived;

    public Notebook(String title, boolean isImportant, String category, float priorityRating, boolean isArchived) {
        this.title = title;
        this.isImportant = isImportant;
        this.category = category;
        this.priorityRating = priorityRating;
        this.isArchived = isArchived;
    }

    protected Notebook(Parcel in) {
        title = in.readString();
        isImportant = in.readByte() != 0;
        category = in.readString();
        priorityRating = in.readFloat();
        isArchived = in.readByte() != 0;
    }

    public static final Creator<Notebook> CREATOR = new Creator<Notebook>() {
        @Override
        public Notebook createFromParcel(Parcel in) {
            return new Notebook(in);
        }

        @Override
        public Notebook[] newArray(int size) {
            return new Notebook[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public String getCategory() {
        return category;
    }

    public float getPriorityRating() {
        return priorityRating;
    }

    public boolean isArchived() {
        return isArchived;
    }

    @Override
    public String toString() {
        return "Caiet: " + title +
                " | Important: " + (isImportant ? "Da" : "Nu") +
                " | Categorie: " + category +
                " | Rating: " + priorityRating +
                " | Arhivat: " + (isArchived ? "Da" : "Nu");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeByte((byte) (isImportant ? 1 : 0));
        dest.writeString(category);
        dest.writeFloat(priorityRating);
        dest.writeByte((byte) (isArchived ? 1 : 0));
    }
}