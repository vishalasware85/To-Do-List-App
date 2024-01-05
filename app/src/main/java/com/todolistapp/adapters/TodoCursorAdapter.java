package com.todolistapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.todolistapp.R;
import com.todolistapp.activities.TodoDetailsActivity;
import com.todolistapp.database.Todo;
import com.todolistapp.utils.Constants;
import com.todolistapp.utils.CursorRecyclerAdapter;

import java.text.SimpleDateFormat;

/**
 * Created by hitanshu on 17/7/17.
 */

public class TodoCursorAdapter extends CursorRecyclerAdapter<TodoCursorAdapter.TodoViewHolder> {

    private Context context;

    public TodoCursorAdapter(Context context) {
        super(null);
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolderCursor(TodoViewHolder holder, Cursor cursor) {
        final Todo todo = Todo.fromCursor(cursor);
        holder.todoTitleTextView.setText(todo.getTitle().replace("\n", " "));
        holder.todoDateTimeTextView.setText(todo.getDateTime().getTimeInMillis() == 0 ? "" : DateFormat.is24HourFormat(context) ? new SimpleDateFormat("MMMM dd, yyyy  h:mm").format(todo.getDateTime().getTime()) : new SimpleDateFormat("MMMM dd, yyyy  h:mm a").format(todo.getDateTime().getTime()));
        holder.todoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TodoDetailsActivity.class);
                intent.putExtra(Constants.TODO_ID, todo.getId());
                context.startActivity(intent);
            }
        });
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout todoLayout;
        public TextView todoTitleTextView;
        public TextView todoDateTimeTextView;

        public TodoViewHolder(View itemView) {
            super(itemView);
            todoLayout = itemView.findViewById(R.id.todo_layout_item);
            todoTitleTextView = itemView.findViewById(R.id.todo_title_text_view_item);
            todoDateTimeTextView = itemView.findViewById(R.id.todo_date_time_text_view_item);
        }
    }

}
