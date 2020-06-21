package com.codrut.todoapplication.addedittask.presenter;

import com.codrut.todoapplication.addedittask.view.AddEditTaskView;
import com.codrut.todoapplication.data.Task;
import com.codrut.todoapplication.data.source.OnResponseListener;
import com.codrut.todoapplication.data.source.TaskRepository;

import java.util.Date;

public class AddEditTaskPresenterImpl implements AddEditTaskPresenter {

    private final String taskId;
    private final TaskRepository mTaskRepository;
    private AddEditTaskView mTaskView;

    public AddEditTaskPresenterImpl(String taskId, TaskRepository taskRepository) {
        this.taskId = taskId;
        mTaskRepository = taskRepository;
    }

    @Override
    public void save(String title, String content) {
        Task task = new Task(taskId, title, content, false, new Date());
        if (isTaskNew()) {
            mTaskRepository.addTask(task, new OnResponseListener<Void>() {
                @Override
                public void onSuccess(Void result) {
                    //TODO: go back to task list
                    mTaskView.goToTaskListActivity();
                }

                @Override
                public void onFailure(Exception e) {
                    //TODO: SHOW ERRORS
                }
            });
        } else {
            mTaskRepository.updateTask(taskId, task, new OnResponseListener<Void>() {
                @Override
                public void onSuccess(Void result) {
                    //TODO: go back to the details view
                    mTaskView.goToTaskDetailsActivity();
                }

                @Override
                public void onFailure(Exception e) {
                    //TODO: SHOW ERRORS
                }
            });
        }
    }

    @Override
    public void populateTaskView() {
        if (isTaskNew()) {
            throw new RuntimeException("populateTask() was called but task is new.");
        }

        mTaskRepository.getTask(taskId, new OnResponseListener<Task>() {
            @Override
            public void onSuccess(Task result) {
                if (mTaskView != null) {
                    //TODO: populate title field
                    mTaskView.populateView(result.getTitle(), result.getContent());
                    //TODO: populate content field
                }
            }

            @Override
            public void onFailure(Exception e) {
                //TODO: SHOW ERRORS
            }
        });
    }

    @Override
    public boolean isTaskNew() {
        return taskId == null;
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    @Override
    public void attachView(AddEditTaskView view) {
        mTaskView = view;

        if (!isTaskNew())
            populateTaskView();
    }

    @Override
    public void detachView() {
        mTaskView = null;
    }
}
