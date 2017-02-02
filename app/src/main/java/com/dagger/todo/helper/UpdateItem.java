package com.dagger.todo.helper;

import android.support.annotation.Nullable;

import com.dagger.todo.data.ToDo;

import java.util.ArrayList;

/**
 * Created by Harshit on 21/01/17
 */

public interface UpdateItem {

    void updateItem(ToDo toDo, @Nullable Integer index);

    void displayItem(ToDo toDo, int index);

    void itemDeleted(ArrayList<ToDo> toDos);

    void displayUndoSnackbar(int position, ToDo removed);
}
