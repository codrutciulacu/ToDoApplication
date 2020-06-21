package com.codrut.todoapplication.taskDetails.presenter;

import com.codrut.todoapplication.BasePresenter;
import com.codrut.todoapplication.taskDetails.view.TaskDetailsView;

public interface TaskDetailsPresenter extends BasePresenter<TaskDetailsView> {
    String getTaskId();
}
