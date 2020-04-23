package com.example.projectdve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projectdve.AlertDialog.AddDialog;
import com.example.projectdve.Data.DatabaseHandler;
import com.example.projectdve.Model.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton floatingActionButton;
    
    public static final String TAG = "MainActivity";
    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fab_mainActivity);
        floatingActionButton.setOnClickListener(this);
        

        //database
        databaseHandler = new DatabaseHandler(MainActivity.this);

//        databaseHandler.addItem(new Book("Finish","Jon Acuff",699.99));
//        databaseHandler.addItem(new Book("The Four Agreements","Don Miguel Ruiz",249.99));
//        databaseHandler.addItem(new Book("What To Say When You Talk To Your Self","Shad Helmstetter, Ph.D",399.99));

//        databaseHandler.deleteAllItems();

        List<Book> bookList = databaseHandler.getAllItems();
        for (Book book : bookList){
            Log.d(TAG, "onCreate: "+"ID:: "+book.getId()+", Name:: "+book.getBookName()+", Author:: "+book.getAuthorName()+", Price:: "+book.getBookPrice()+", Date:: "+book.getDate()+", Time:: "+book.getTime());
            //bookList.add(book);
        }

        //count total entries
        databaseHandler.getCount();
        Log.d(TAG, "onCreate: "+databaseHandler.getCount());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_mainActivity:
                AddDialog addDialog = new AddDialog();
                addDialog.show(getSupportFragmentManager(),"Add Dialog Popup");
                Log.d(TAG, "onClick: fab main clicked");

                break;
        }
    }
}
