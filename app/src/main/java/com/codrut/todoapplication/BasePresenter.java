package com.codrut.todoapplication;

public interface BasePresenter<V> {
    void attachView(V view);
    void detachView();
}
