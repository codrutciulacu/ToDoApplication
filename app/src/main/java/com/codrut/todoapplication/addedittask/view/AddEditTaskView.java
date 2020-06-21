package com.codrut.todoapplication.addedittask.view;

public interface AddEditTaskView {
    void populateView(String title, String content);
    void goToTaskListActivity();
    void goToTaskDetailsActivity();
    void showError(String text);
}
