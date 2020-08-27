package com.jworks.mvvm.DataAccess;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jworks.mvvm.Models.Note;
import com.jworks.mvvm.R;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Database(entities = Note.class, version = 2)
public abstract class NoteDatabase extends RoomDatabase {
    private static final String TAG = "NoteDatabase";
   private static NoteDatabase instance;

   public abstract NoteDao noteDao();

   public static synchronized NoteDatabase getInstance(Context context){
       if(instance== null){
           instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class,"note_database")
                   .fallbackToDestructiveMigration()
                   .addCallback(callback).build();

       }
       return instance;
   }
   private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
       @Override
       public void onCreate(@NonNull SupportSQLiteDatabase db) {
           super.onCreate(db);
           new initialnoteAsyncTask(instance).execute();
       }
   };
   private static  class initialnoteAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private initialnoteAsyncTask(NoteDatabase database){
            noteDao = database.noteDao();
        }

       @Override
       protected Void doInBackground(Void... voids) {

           noteDao.insert(new Note("Welcome to Short Notes \nStart adding notes" ,"You can edit this note",1));
           Log.d(TAG, "doInBackground: here");
           return null;
       }
   }


}
