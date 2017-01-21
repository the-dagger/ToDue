package com.dagger.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 766;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    ArrayList<Note> noteArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noteArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.contentRV);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
            AddTodoDialogFragment.getInstance(null)
                    .show(getSupportFragmentManager(),"addNote");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        noteAdapter = new NoteAdapter(noteArrayList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(noteAdapter);
    }
}
