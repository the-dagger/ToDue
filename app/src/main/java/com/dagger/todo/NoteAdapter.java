package com.dagger.todo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Harshit on 05/12/16.
 */

class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private int currentPosition;
    private ArrayList<Note> arrayList = new ArrayList<>();
    private Context context;
    private ArrayAdapter<String> priorityAdapter, completedAdapter;
    String[] priorities = {"Low", "Medium", "High"};
    String[] completionStatus = {"ToDo", "Done"};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note, parent, false);
        return new ViewHolder(view);
    }

    public NoteAdapter(ArrayList<Note> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        priorityAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, priorities);
        completedAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, completionStatus);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        currentPosition = holder.getAdapterPosition();
        holder.todoTitle.setText(arrayList.get(position).getTitle());
        holder.todoContent.setText(arrayList.get(position).getContent());
        holder.dueDate.setText(arrayList.get(position).getDueDate());
        holder.priority.setText(priorities[arrayList.get(position).getPriority()]);
        final View view = LayoutInflater.from(context).inflate(R.layout.activity_add_note,null);
        final Spinner prioritySpinner = (Spinner) view.findViewById(R.id.priority_spinners);
        final Spinner completionSpinner = (Spinner) view.findViewById(R.id.status_spinner);
        prioritySpinner.setAdapter(priorityAdapter);
        completionSpinner.setAdapter(completedAdapter);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodoDialogFragment addTodoDialogFragment = AddTodoDialogFragment.getInstance(arrayList.get(currentPosition));
//                addTodoDialogFragment.show()
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView dueDate;
        TextView priority;
        TextView todoTitle;
        TextView todoContent;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            todoContent = (TextView) itemView.findViewById(R.id.todo_content);
            todoTitle = (TextView) itemView.findViewById(R.id.todo_title);
            priority = (TextView) itemView.findViewById(R.id.priority);
            dueDate = (TextView) itemView.findViewById(R.id.due_date);
        }
    }
}
