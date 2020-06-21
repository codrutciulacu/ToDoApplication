package com.codrut.todoapplication.tasks.presenter;

import com.codrut.todoapplication.BasePresenter;
import com.codrut.todoapplication.tasks.view.TasksView;

public interface TasksPresenter extends BasePresenter<TasksView> {
    void getTasks();
    void completeTask(String id);
    void activateTask(String id);
}
