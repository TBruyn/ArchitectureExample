package com.example.architectureexample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Tutorial: https://www.youtube.com/watch?v=0cg09tlAAQ0&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118&index=3

// DAO have to be interfaces or abstract classes, because Room generates the methods

@Dao
public interface NoteDao {

    //The annotation is the db operation
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    //This is a custom operation, so we need to define the query in SQL
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
