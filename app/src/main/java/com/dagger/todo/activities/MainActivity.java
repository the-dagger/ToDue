package com.dagger.todo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dagger.todo.data.ToDo;
import com.dagger.todo.adapters.TodoAdapter;
import com.dagger.todo.R;
import com.dagger.todo.utils.UpdateItem;
import com.dagger.todo.fragments.AddTodoDialogFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UpdateItem {

    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    ArrayList<ToDo> toDoArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toDoArrayList = new ArrayList<>();
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
        todoAdapter = new TodoAdapter(toDoArrayList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(todoAdapter);
    }

    @Override
    public void updateItem(ToDo toDo, Integer index) {
        if (index != null) {
            toDoArrayList.set(index, toDo);
        } else
            toDoArrayList.add(0, toDo);
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayItem(ToDo toDo, int index) {
        AddTodoDialogFragment.getInstance(toDo,index)
                .show(getSupportFragmentManager(), "addNote");
    }
}
