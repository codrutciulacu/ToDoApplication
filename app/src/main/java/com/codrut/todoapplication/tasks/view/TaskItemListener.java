package com.codrut.todoapplication.tasks.view;

import com.codrut.todoapplication.data.Task;

public interface TaskItemListener {
    void onTaskClick(Task clickedTask);

    void onCompleteTaskClick(Task completedTask);
    void onActivateTaskClick(Task activatedTask);
}
