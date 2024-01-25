package com.example.contentprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class book_recycle_adapter  extends RecyclerView.Adapter<book_recycle_adapter.MyViewHolder>{
    Context context;
    ArrayList<books> book = new ArrayList<>();

    private recyclerInterface recyclerInterface;

    public book_recycle_adapter(Context context, ArrayList<books> book, recyclerInterface recyclerInterface) {
        this.context = context;
        this.book = book;
        this.recyclerInterface=recyclerInterface;
    }

    @NonNull
    @Override
    public book_recycle_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardlayout,parent,false);
        return new book_recycle_adapter.MyViewHolder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull book_recycle_adapter.MyViewHolder holder, int position) {

        holder.title.setText(book.get(position).getName());
        holder.id.setText(book.get(position).getId());
        holder.Author.setText(book.get(position).getAuthor());
        holder.year.setText(book.get(position).getYear());
        holder.image.setImageResource(book.get(position).getImage());

        //below method is for setting on click response of the view
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerInterface.onItemClick(book.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView id,title,Author,year;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            image=itemView.findViewById(R.id.bookImage);
            id = itemView.findViewById(R.id.bookID);
            title = itemView.findViewById(R.id.bookTitle);
            Author = itemView.findViewById(R.id.bookAuthor);
            year = itemView.findViewById(R.id.bookYear);
            cardView=itemView.findViewById(R.id.Cardlayout);
        }
    }
}
