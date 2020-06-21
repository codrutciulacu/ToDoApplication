package com.codrut.todoapplication.tasks.view;


import com.codrut.todoapplication.data.Task;

import java.util.List;

public interface TasksView {
    void updateList(List<Task> tasks);
    void showNoTask();
    void showList();

    void showLoadingBar();

    void showErrorSnackBar(Exception e);
}

