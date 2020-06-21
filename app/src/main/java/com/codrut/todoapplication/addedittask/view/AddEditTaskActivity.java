package com.codrut.todoapplication.addedittask.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codrut.todoapplication.R;
import com.codrut.todoapplication.addedittask.presenter.AddEditTaskPresenter;
import com.codrut.todoapplication.addedittask.presenter.AddEditTaskPresenterImpl;
import com.codrut.todoapplication.data.source.TaskRepository;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final String TASK_ID = "task_id";
    private AddEditTaskPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTitle(R.string.add_new_task);

        if(getId() != null) {
            mPresenter = new AddEditTaskPresenterImpl(getId(),
                    new TaskRepository(FirebaseFirestore.getInstance()));
        } else {
            mPresenter = new AddEditTaskPresenterImpl(null,
                    new TaskRepository(FirebaseFirestore.getInstance()));
        }
        showAddEditTaskFragment();
    }

    private String getId(){
        if(getIntent() != null && getIntent().getExtras() != null)
            return getIntent().getExtras().getString(TASK_ID);
        return null;
    }

    public void showAddEditTaskFragment(){
        AddEditTaskFragment fragment = AddEditTaskFragment.newInstance();
        fragment.setTaskPresenter(mPresenter);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.add_task_fragment_container, fragment)
                .commit();
    }
}
