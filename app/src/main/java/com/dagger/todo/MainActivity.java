package com.dagger.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UpdateItem {

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
                AddTodoDialogFragment.getInstance(null,null)
                        .show(getSupportFragmentManager(), "addNote");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        noteAdapter = new NoteAdapter(noteArrayList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void updateItem(Note note, Integer index) {
        if (index != null) {
            noteArrayList.set(index,note);
        } else
            noteArrayList.add(0,note);
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayItem(Note note, int index) {
        AddTodoDialogFragment.getInstance(note,index)
                .show(getSupportFragmentManager(), "addNote");
    }
}
