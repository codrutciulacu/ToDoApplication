package com.codrut.todoapplication.taskDetails.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.codrut.todoapplication.R;
import com.codrut.todoapplication.addedittask.view.AddEditTaskActivity;
import com.codrut.todoapplication.addedittask.view.AddEditTaskFragment;
import com.codrut.todoapplication.taskDetails.presenter.TaskDetailsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class TaskDetailsFragment extends Fragment implements TaskDetailsView {

    private TaskDetailsPresenter mTaskPresenter;
    private EditText mTitleEditText;
    private EditText mContentEditText;
    private LinearLayout mLoadingBar;
    private FloatingActionButton mFloatingActionButton;

    public TaskDetailsFragment() {
    }

    public static TaskDetailsFragment newInstance() {
        return new TaskDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_details, container, false);

        mTitleEditText = view.findViewById(R.id.task_details_title_edit_text);
        mContentEditText = view.findViewById(R.id.task_details_description_edit_text);
        mLoadingBar = view.findViewById(R.id.loadingView);
        mFloatingActionButton = view.findViewById(R.id.edit_button);
        mFloatingActionButton.setOnClickListener(__ -> {
            goToEditTaskActivity(mTaskPresenter.getTaskId());
            disableButton();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTaskPresenter.attachView(this);
    }

    private void disableButton() {
        mFloatingActionButton.setEnabled(false);
    }

    public void goToEditTaskActivity(String taskId){
        Intent intent = new Intent(getActivity(), AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskActivity.TASK_ID, taskId);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    public void setTasksPresenter(final TaskDetailsPresenter tasksPresenter) {
        mTaskPresenter = tasksPresenter;
    }

    @Override
    public void populateTaskView(String title, String content) {
        hideLoadingBar();
        mTitleEditText.setText(title);
        mContentEditText.setText(content);
    }

    @Override
    public void showLoadingBar() {
        mTitleEditText.setVisibility(View.GONE);
        mContentEditText.setVisibility(View.GONE);
        mFloatingActionButton.setVisibility(View.GONE);
        mLoadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingBar() {
        mTitleEditText.setVisibility(View.VISIBLE);
        mContentEditText.setVisibility(View.VISIBLE);
        mFloatingActionButton.setVisibility(View.VISIBLE);
        mLoadingBar.setVisibility(View.GONE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mTaskPresenter.detachView();
    }
}
