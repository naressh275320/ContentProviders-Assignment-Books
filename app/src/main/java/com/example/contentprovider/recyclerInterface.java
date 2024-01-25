package com.example.contentprovider;

//creating an interface for clicking the details in recycler View
public interface recyclerInterface {
    //creating an abstract class for clicking the card view
    void onItemClick(books book, int position);
}
