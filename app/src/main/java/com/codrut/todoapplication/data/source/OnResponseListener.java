package com.codrut.todoapplication.data.source;

public interface OnResponseListener<T> {
    void onSuccess(T result);
    void onFailure(Exception e);
}
