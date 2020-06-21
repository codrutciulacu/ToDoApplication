package com.codrut.todoapplication.addedittask.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.codrut.todoapplication.R;
import com.codrut.todoapplication.addedittask.presenter.AddEditTaskPresenter;
import com.codrut.todoapplication.taskDetails.view.TaskDetailsActivity;
import com.codrut.todoapplication.tasks.view.TasksActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class AddEditTaskFragment extends Fragment implements AddEditTaskView {

    private AddEditTaskPresenter mTaskPresenter;
    private EditText mTitleEditText;
    private EditText mContentEditText;

    public AddEditTaskFragment() {
    }

    public void setTaskPresenter(AddEditTaskPresenter taskPresenter) {
        mTaskPresenter = taskPresenter;
    }

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mTaskPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        mTitleEditText = view.findViewById(R.id.add_task_title_edit_text);
        mContentEditText = view.findViewById(R.id.add_task_description_edit_text);
        view.findViewById(R.id.done_button).setOnClickListener(__ -> {
            mTaskPresenter.save(mTitleEditText.getText().toString(),
                    mContentEditText.getText().toString());
            view.findViewById(R.id.done_button).setEnabled(false);
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTaskPresenter.detachView();
    }

    @Override
    public void populateView(String title, String content) {
        mTitleEditText.setText(title);
        mContentEditText.setText(content);
    }

    @Override
    public void goToTaskListActivity() {
        startActivity(new Intent(getActivity(), TasksActivity.class));
    }

    @Override
    public void goToTaskDetailsActivity() {
        //TODO: Implement
        Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
        intent.putExtra(TaskDetailsActivity.TASK_ID_TAG, mTaskPresenter.getTaskId());
        startActivity(intent);
    }

    @Override
    public void showError(String text) {
        Snackbar.make(Objects.requireNonNull(getView()), text, Snackbar.LENGTH_LONG).show();
    }
}
