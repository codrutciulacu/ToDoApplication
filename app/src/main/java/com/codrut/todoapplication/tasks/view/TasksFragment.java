package com.codrut.todoapplication.tasks.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.codrut.todoapplication.R;
import com.codrut.todoapplication.addedittask.view.AddEditTaskActivity;
import com.codrut.todoapplication.data.Task;
import com.codrut.todoapplication.data.source.TaskRepository;
import com.codrut.todoapplication.taskDetails.view.TaskDetailsActivity;
import com.codrut.todoapplication.tasks.presenter.TasksPresenter;
import com.codrut.todoapplication.tasks.presenter.TasksPresenterImpl;
import com.codrut.todoapplication.util.Logger;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TasksFragment extends Fragment implements TasksView, TaskItemListener {


    private TasksPresenter mTasksPresenter;
    private TasksAdapter mTasksAdapter;
    private LinearLayout mNoTaskLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddTaskButton;
    private LinearLayout mProgressLinearLayout;

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    public TasksFragment() {
        mTasksPresenter = new TasksPresenterImpl(new TaskRepository(FirebaseFirestore.getInstance()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTasksAdapter = new TasksAdapter(Collections.emptyList(), this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        setupRecyclerView(view);
        mNoTaskLayout = view.findViewById(R.id.noTasks);
        mRecyclerView = view.findViewById(R.id.tasks_recyclerView);
        mProgressLinearLayout = view.findViewById(R.id.progress_bar);
        mAddTaskButton = view.findViewById(R.id.add_button);
        mAddTaskButton.setOnClickListener(this::goToAddTaskActivity);

        return view;
    }

    private void setupRecyclerView(final View view) {
        RecyclerView recyclerView = view.findViewById(R.id.tasks_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mTasksAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTasksPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTasksPresenter.detachView();
    }

    @Override
    public void updateList(final List<Task> tasks) {
        mTasksAdapter.replaceData(tasks);
    }

    @Override
    public void showNoTask() {
        if (mRecyclerView.getVisibility() == View.VISIBLE)
            mRecyclerView.setVisibility(View.INVISIBLE);

        if (mProgressLinearLayout.getVisibility() == View.VISIBLE)
            mProgressLinearLayout.setVisibility(View.INVISIBLE);

        if (mNoTaskLayout.getVisibility() == View.INVISIBLE)
            mNoTaskLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList() {
        if (mRecyclerView.getVisibility() == View.INVISIBLE)
            mRecyclerView.setVisibility(View.VISIBLE);

        if (mProgressLinearLayout.getVisibility() == View.VISIBLE)
            mProgressLinearLayout.setVisibility(View.INVISIBLE);

        if (mNoTaskLayout.getVisibility() == View.VISIBLE)
            mNoTaskLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingBar() {
        if (mRecyclerView.getVisibility() == View.VISIBLE)
            mRecyclerView.setVisibility(View.INVISIBLE);

        if (mNoTaskLayout.getVisibility() == View.VISIBLE)
            mNoTaskLayout.setVisibility(View.INVISIBLE);

        if (mProgressLinearLayout.getVisibility() == View.INVISIBLE)
            mProgressLinearLayout.setVisibility(View.VISIBLE);
    }

    public void goToAddTaskActivity(View view) {
        mAddTaskButton.setEnabled(false);
        Intent intent = new Intent(getActivity(), AddEditTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorSnackBar(final Exception e) {
        if (getView() != null && e != null && e.getMessage() != null) {
            Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTaskClick(Task clickedTask) {
        goToTaskDetailsActivity(clickedTask.getId());
    }

    private void goToTaskDetailsActivity(String id) {
        Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
        intent.putExtra(TaskDetailsActivity.TASK_ID_TAG, id);
        startActivity(intent);
    }

    @Override
    public void onCompleteTaskClick(Task completedTask) {
        mTasksPresenter.completeTask(completedTask.getId());
    }

    @Override
    public void onActivateTaskClick(Task activatedTask) {
        Logger.printMessage(activatedTask.getTitle());
        mTasksPresenter.activateTask(activatedTask.getId());
    }

}


