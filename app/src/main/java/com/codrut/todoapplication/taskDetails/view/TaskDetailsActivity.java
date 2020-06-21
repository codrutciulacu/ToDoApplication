package com.codrut.todoapplication.taskDetails.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.codrut.todoapplication.R;
import com.codrut.todoapplication.data.source.TaskRepository;
import com.codrut.todoapplication.taskDetails.presenter.TaskDetailsPresenter;
import com.codrut.todoapplication.taskDetails.presenter.TaskDetailsPresenterImpl;
import com.google.firebase.firestore.FirebaseFirestore;

public class TaskDetailsActivity extends AppCompatActivity {

    public static final String TASK_ID_TAG = "task_id";

    private TaskDetailsPresenter mTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        setTitle("Task Details");

        String taskId = getId();

        if(taskId != null){
            mTaskPresenter =
                    new TaskDetailsPresenterImpl(new TaskRepository(FirebaseFirestore.getInstance()), taskId);
        }

        showDetailsFragment();
    }


    private void showDetailsFragment() {
        TaskDetailsFragment fragment = TaskDetailsFragment.newInstance();
        fragment.setTasksPresenter(mTaskPresenter);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.task_details_container, fragment)
                .commit();
    }

    private String getId(){
        if(getIntent() != null && getIntent().getExtras() != null)
            return getIntent().getExtras().getString(TASK_ID_TAG);
        return null;
    }
}
