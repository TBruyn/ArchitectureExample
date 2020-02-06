package com.example.architectureexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// tutorial: https://www.youtube.com/watch?v=0cg09tlAAQ0&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118&index=3

// Database annotation needs an entity and a version. If we want to make changes to the database, we
// increase the version number to not pollute our database

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    // CallBack has the 'onCreate' and 'onUpdate' methods that will be called every time the db is
    // created/updated
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Hello world!", "My first note", 1));
            noteDao.insert(new Note("Hello second", "More notes at the ready", 2));
            noteDao.insert(new Note("To be", "...or not to be", 3));
            noteDao.insert(new Note("A priest",
                    "A priest grands absolution\n" +
                            "to a son that isn't his\n" +
                            "but the promise of salvation\n" +
                            "was traded for a kiss", 4));

            return null;
        }
    }

}
