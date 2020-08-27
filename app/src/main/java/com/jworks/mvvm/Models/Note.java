package com.jworks.mvvm.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes_table")
public class Note implements Serializable {
  private String title;
  private String description;
  private int priority;


  @PrimaryKey(autoGenerate = true)
  private int id;

    public Note(String title, String description, int priority ) {
        this.title = title;
        this.description = description;
        this.priority = priority;


    }



    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

}
