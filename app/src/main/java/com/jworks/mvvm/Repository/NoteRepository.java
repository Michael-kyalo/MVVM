package com.jworks.mvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.jworks.mvvm.DataAccess.NoteDao;
import com.jworks.mvvm.DataAccess.NoteDatabase;
import com.jworks.mvvm.Models.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new insertAsycTask(noteDao).execute(note);
    }
    public void update(Note note){
        new updateAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new deleteAsycTask(noteDao).execute(note);
    }
    public void clearAll(){
        new clearAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class insertAsycTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private insertAsycTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private updateAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class deleteAsycTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private deleteAsycTask(NoteDao noteDao){
            this.noteDao= noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class clearAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private clearAllAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.clearAll();
            return null;
        }
    }

}
