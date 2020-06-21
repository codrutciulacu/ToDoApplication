package com.codrut.todoapplication.data.source.mockTasks;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.Objects;
import java.util.concurrent.Executor;

public class TaskFailTask<T> extends com.google.android.gms.tasks.Task<T> {

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Nullable
    @Override
    public T getResult() {
        return null;
    }

    @Nullable
    @Override
    public <X extends Throwable> T getResult(@NonNull Class<X> aClass) throws X {
        return null;
    }

    @Nullable
    @Override
    public Exception getException() {
        return new RuntimeException("Some problem with firebase!");
    }

    @NonNull
    @Override
    public Task<T> addOnCompleteListener(@NonNull OnCompleteListener<T> onCompleteListener) {
        return this;
    }

    @NonNull
    @Override
    public Task<T> addOnSuccessListener(@NonNull OnSuccessListener<? super T> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<T> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super T> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<T> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super T> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<T> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        onFailureListener.onFailure(getException());
        return this;
    }

    @NonNull
    @Override
    public Task<T> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<T> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        return null;
    }
}
