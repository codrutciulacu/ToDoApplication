package com.codrut.todoapplication.data.source.mockTasks;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.Executor;

public class GetTasksSuccessTask extends Task<QuerySnapshot> {
    private QuerySnapshot mQuerySnapshot;

    public GetTasksSuccessTask(QuerySnapshot querySnapshot) {
        mQuerySnapshot = querySnapshot;
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Nullable
    @Override
    public QuerySnapshot getResult() {
        return mQuerySnapshot;
    }

    @Nullable
    @Override
    public <X extends Throwable> QuerySnapshot getResult(@NonNull Class<X> aClass) throws X {
        return null;
    }

    @Nullable
    @Override
    public Exception getException() {
        return null;
    }

    @NonNull
    @Override
    public Task<QuerySnapshot> addOnCompleteListener(@NonNull OnCompleteListener<QuerySnapshot> onCompleteListener) {
        onCompleteListener.onComplete(this);
        return this;
    }

    @NonNull
    @Override
    public Task<QuerySnapshot> addOnSuccessListener(@NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<QuerySnapshot> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<QuerySnapshot> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<QuerySnapshot> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<QuerySnapshot> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<QuerySnapshot> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        return null;
    }
}
