package com.jworks.mvvm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.jworks.mvvm.Models.Note;
import com.jworks.mvvm.R;
import com.jworks.mvvm.Views.ReadNoteActivity;



public class CustomAdapter extends ListAdapter<Note,CustomAdapter.ViewHolder>{

    Context context;

    public CustomAdapter(Context context) {
        super(DIF_CALLBACK);
        this.context = context;


    }
    private static final DiffUtil.ItemCallback<Note> DIF_CALLBACK  = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getDescription().equals(newItem.getDescription())
                    &&oldItem.getPriority()==newItem.getPriority();
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Note note = getItem(position);
        holder.textView.setText(note.getTitle());

        switch (note.getPriority()){
            case 1:
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.colorOne));
                break;
            case 2:
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.colorTwo));
                break;
            case 3:
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.colorThree));
                break;
            case 4:
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.colorFour));
                break;
            default:
                holder.cardView.setCardBackgroundColor(context.getColor(R.color.colorFive));
                break;
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("note", note);

                Intent Readnote = new Intent(context, ReadNoteActivity.class);
                Readnote.putExtras(bundle);
                context.startActivity(Readnote);
            }
        });


    }
    public Note getSwippedNote(int position){
        return getItem(position);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.tile);
        }
    }
}
