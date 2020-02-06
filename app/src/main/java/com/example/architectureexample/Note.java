package com.example.architectureexample;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Tutorial: https://www.youtube.com/watch?v=Jwdty9jQN0E&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118&index=2

// @Entity creates a Room entity. The parentheses are optional and are now used to set the table name
@Entity(tableName = "note_table")
public class Note {

    //Every entity needs a PrimaryKey. AutoGenerate means it will automatically increase.
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int priority;

    //Id is automatically generated, so not in the constructor
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //There needs to be a setId to allow Room to autoincrease the key
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
}
