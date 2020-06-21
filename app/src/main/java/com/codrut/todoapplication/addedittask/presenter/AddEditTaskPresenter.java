package com.codrut.todoapplication.addedittask.presenter;

import com.codrut.todoapplication.BasePresenter;
import com.codrut.todoapplication.addedittask.view.AddEditTaskView;

public interface AddEditTaskPresenter extends BasePresenter<AddEditTaskView> {
    void save(String title, String content);
    void populateTaskView();
    boolean isTaskNew();

    String getTaskId();
}
