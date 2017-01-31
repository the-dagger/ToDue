package com.dagger.todo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.dagger.todo.R;
import com.dagger.todo.adapters.TodoAdapter;
import com.dagger.todo.data.ToDo;
import com.dagger.todo.data.ToDoItemDatabase;
import com.dagger.todo.fragments.AddTodoDialogFragment;
import com.dagger.todo.utils.UpdateItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements UpdateItem {

    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    ArrayList<ToDo> toDoArrayList;
    ToDoItemDatabase toDoItemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toDoArrayList = new ArrayList<>();
        toDoItemDatabase = ToDoItemDatabase.getToDoItemDatabase(this);
        toDoArrayList = toDoItemDatabase.getToDoFromDatabase();
        Collections.reverse(toDoArrayList);
        recyclerView = (RecyclerView) findViewById(R.id.contentRV);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AddTodoDialogFragment.getInstance(null, null)
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
            Log.e("UPDATE","Index isn't null");
            if (toDo.isComplete() == 1) {
                toDoArrayList.remove(index.intValue());
                toDoItemDatabase.deleteToDoFromDatabase(toDo);
            } else {
                toDoArrayList.set(index, toDo);
                toDoItemDatabase.updateToDoItem(toDo);
            }
        } else {
            Log.e("UPDATE","Index is null");
            toDoArrayList.add(0, toDo);
            toDoItemDatabase.addToDoItem(toDo);
        }
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayItem(ToDo toDo, int index) {
        AddTodoDialogFragment.getInstance(toDo, index)
                .show(getSupportFragmentManager(), "addNote");
    }
}
