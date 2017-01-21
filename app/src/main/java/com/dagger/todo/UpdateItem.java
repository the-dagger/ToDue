package com.dagger.todo;

import android.support.annotation.Nullable;

/**
 * Created by Harshit on 21/01/17.
 */

public interface UpdateItem {

    void updateItem(Note note, @Nullable Integer index);

    void displayItem(Note note, int index);
}
