package com.codrut.todoapplication.taskDetails.view;

public interface TaskDetailsView {
    void populateTaskView(String title, String content);

    void showLoadingBar();
    void hideLoadingBar();
}
