package com.dagger.todo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Harshit on 21/01/17.
 */

public class AddTodoDialogFragment extends DialogFragment {

    static Note currentNote;

    String[] priorities = {"Low", "Medium", "High"};
    String[] completionStatus = {"ToDo", "Done"};

    public static AddTodoDialogFragment getInstance(@Nullable Note note) {
        if (note != null) {
            currentNote = note;
        }
        return new AddTodoDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, priorities);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, completionStatus);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_note,null);
        EditText title = (EditText) dialogView.findViewById(R.id.todo_title_editText);
        EditText content = (EditText) dialogView.findViewById(R.id.todo_content_edittext);
        Spinner prioritySpinner = (Spinner) dialogView.findViewById(R.id.priority_spinners);
        Spinner statusSpinner = (Spinner) dialogView.findViewById(R.id.status_spinner);
        prioritySpinner.setAdapter(priorityAdapter);
        statusSpinner.setAdapter(statusAdapter);
        if (currentNote != null){
            title.setText(currentNote.getTitle(), TextView.BufferType.EDITABLE);
            content.setText(currentNote.getContent(),TextView.BufferType.EDITABLE);
            prioritySpinner.setSelection(currentNote.getPriority());
            statusSpinner.setSelection(currentNote.isComplete());
        }
        return new AlertDialog.Builder(getContext())
                .setTitle("Add a new Note")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setView(dialogView)
                .create();
    }
}
