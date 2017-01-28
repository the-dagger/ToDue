package com.dagger.todo.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dagger.todo.data.ToDo;
import com.dagger.todo.R;
import com.dagger.todo.utils.UpdateItem;

import java.util.ArrayList;

/**
 * Created by Harshit on 05/12/16
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private ArrayList<ToDo> arrayList = new ArrayList<>();
    private UpdateItem updateItem;
    private String[] priorities = {"Low", "Medium", "High"};
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note, parent, false);
        return new ViewHolder(view);
    }

    public TodoAdapter(ArrayList<ToDo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        updateItem = (UpdateItem) context;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.todoTitle.setText(arrayList.get(position).getTitle());
        holder.todoContent.setText(arrayList.get(position).getContent());
        holder.dueDate.setText(arrayList.get(position).getDueDate());
        holder.priority.setText(priorities[arrayList.get(position).getPriority()]);
        holder.dueDate.setText(arrayList.get(position).getDueDate());
        switch (arrayList.get(position).getPriority()){
            case 0 :
                holder.cardView.setBackgroundColor(ContextCompat.getColor(context,R.color.priorityLow));
                break;
            case 1 :
                holder.cardView.setBackgroundColor(ContextCompat.getColor(context,R.color.priorityMedium));
                break;
            case 2 :
                holder.cardView.setBackgroundColor(ContextCompat.getColor(context,R.color.priorityHigh));
                break;
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem.displayItem(arrayList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
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
            cardView = (CardView) itemView.findViewById(R.id.single_item_linear_layout);
            todoContent = (TextView) itemView.findViewById(R.id.todo_content);
            todoTitle = (TextView) itemView.findViewById(R.id.todo_title);
            priority = (TextView) itemView.findViewById(R.id.priority);
            dueDate = (TextView) itemView.findViewById(R.id.due_date);
        }
    }
}
