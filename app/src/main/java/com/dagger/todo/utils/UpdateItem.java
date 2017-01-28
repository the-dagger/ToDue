package com.dagger.todo.utils;

import android.support.annotation.Nullable;

import com.dagger.todo.data.ToDo;

/**
 * Created by Harshit on 21/01/17
 */

public interface UpdateItem {

    void updateItem(ToDo toDo, @Nullable Integer index);

    void displayItem(ToDo toDo, int index);
}
