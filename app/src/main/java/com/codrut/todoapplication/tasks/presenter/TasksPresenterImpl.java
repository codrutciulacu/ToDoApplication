package com.codrut.todoapplication.tasks.presenter;

import com.codrut.todoapplication.data.Task;
import com.codrut.todoapplication.data.source.OnResponseListener;
import com.codrut.todoapplication.data.source.TaskRepository;
import com.codrut.todoapplication.tasks.view.TasksView;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class TasksPresenterImpl implements TasksPresenter {
    private final TaskRepository mTaskRepository;
    private TasksView mTasksView;

    @Inject
    public TasksPresenterImpl(TaskRepository repository) {
        mTaskRepository = repository;
    }

    @Override
    public void getTasks() {
        mTasksView.showLoadingBar();
        mTaskRepository.getTasks(new OnResponseListener<List<Task>>() {
            @Override
            public void onSuccess(List<Task> result) {
                if (result.size() == 0)
                    mTasksView.showNoTask();
                else {
                    mTasksView.showList();
                    mTasksView.updateList(result);
                }
            }

            @Override
            public void onFailure(Exception e) {
                mTasksView.showErrorSnackBar(e);
            }
        });
    }

    @Override
    public void completeTask(final String id) {
        mTaskRepository.completeTask(id, new OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void result) {
                //update view
                reloadTasks(); //TODO: Needs optimization
            }

            @Override
            public void onFailure(Exception e) {
                //show error
                //recover from error accordingly
                mTasksView.showErrorSnackBar(e);
            }
        });
    }

    private void reloadTasks() {
        mTaskRepository.getTasks(new OnResponseListener<List<Task>>() {
            @Override
            public void onSuccess(List<Task> result) {
                mTasksView.updateList(result);
            }

            @Override
            public void onFailure(Exception e) {
                mTasksView.showErrorSnackBar(e);
            }
        });
    }

    @Override
    public void activateTask(final String id) {
        mTaskRepository.activateTask(id, new OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void result) {
                //update view
                reloadTasks(); //TODO: Needs optimization
            }

            @Override
            public void onFailure(Exception e) {
                //show error
                //recover from error accordingly
                mTasksView.showErrorSnackBar(e);
            }
        });
    }

    @Override
    public void attachView(final TasksView view) {
        mTasksView = view;
        getTasks();
    }

    @Override
    public void detachView() {
        mTasksView = null;
    }
}
