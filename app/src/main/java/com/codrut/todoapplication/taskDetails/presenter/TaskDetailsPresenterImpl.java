package com.codrut.todoapplication.taskDetails.presenter;

import com.codrut.todoapplication.data.Task;
import com.codrut.todoapplication.data.source.OnResponseListener;
import com.codrut.todoapplication.data.source.TaskRepository;
import com.codrut.todoapplication.taskDetails.view.TaskDetailsView;

public class TaskDetailsPresenterImpl implements TaskDetailsPresenter{
    private final TaskRepository mTaskRepository;
    private TaskDetailsView mTaskDetailsView;
    private String mTaskId;

    public TaskDetailsPresenterImpl(TaskRepository taskRepository, String taskId) {
        mTaskRepository = taskRepository;
        mTaskId = taskId;
    }

    @Override
    public void attachView(final TaskDetailsView view) {
        mTaskDetailsView = view;

        mTaskDetailsView.showLoadingBar();
        mTaskRepository.getTask(mTaskId, new OnResponseListener<Task>() {
            @Override
            public void onSuccess(Task result) {
                mTaskDetailsView.populateTaskView(result.getTitle(), result.getContent());
            }

            @Override
            public void onFailure(Exception e) {
                //TODO: Show error
            }
        });
    }

    @Override
    public void detachView() {
        mTaskDetailsView = null;
    }

    @Override
    public String getTaskId() {
        return mTaskId;
    }
}
