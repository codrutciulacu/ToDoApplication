package com.codrut.todoapplication.tasks.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codrut.todoapplication.R;
import com.codrut.todoapplication.data.Task;
import com.codrut.todoapplication.util.Logger;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private List<Task> mTaskList;
    private TaskItemListener mTaskItemListener;

    TasksAdapter(List<Task> taskList, TaskItemListener taskItemListener) {
        mTaskList = taskList;
        mTaskItemListener = taskItemListener;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TasksViewHolder(view);
    }

    public void replaceData(List<Task> tasks) {
        mTaskList = tasks;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Task currentTask = mTaskList.get(position);

        holder.getCompleteCheckBox().setChecked(currentTask.isComplete());
        holder.getCompleteCheckBox().setOnClickListener(v -> {
            if (!currentTask.isComplete())
                mTaskItemListener.onCompleteTaskClick(currentTask);
            else
                mTaskItemListener.onActivateTaskClick(currentTask);
        });

        holder.getItemView().setOnClickListener(v -> mTaskItemListener.onTaskClick(currentTask));

        holder.getTitleTextView().setText(currentTask.getTitle());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    static class TasksViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox mCompleteCheckBox;
        private final TextView mTitleTextView;
        private final View mItemView;

        TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mCompleteCheckBox = itemView.findViewById(R.id.complete);
            mTitleTextView = itemView.findViewById(R.id.title);
        }

        CheckBox getCompleteCheckBox() {
            return mCompleteCheckBox;
        }
        TextView getTitleTextView() {
            return mTitleTextView;
        }
        public View getItemView() { return mItemView; }
    }
}
