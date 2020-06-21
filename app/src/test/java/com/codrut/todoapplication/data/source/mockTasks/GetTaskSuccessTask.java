package com.codrut.todoapplication.data.source.mockTasks;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.concurrent.Executor;

public class GetTaskSuccessTask extends com.google.android.gms.tasks.Task<DocumentSnapshot> {

    private final DocumentSnapshot mDocumentSnapshot;

    public GetTaskSuccessTask(DocumentSnapshot documentSnapshot) {
        mDocumentSnapshot = documentSnapshot;
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
    public DocumentSnapshot getResult() {
        return mDocumentSnapshot;
    }

    @Nullable
    @Override
    public <X extends Throwable> DocumentSnapshot getResult(@NonNull Class<X> aClass) throws X {
        return null;
    }

    @Nullable
    @Override
    public Exception getException() {
        return null;
    }

    @NonNull
    @Override
    public Task<DocumentSnapshot> addOnCompleteListener(@NonNull OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        onCompleteListener.onComplete(this);
        return this;
    }

    @NonNull
    @Override
    public Task<DocumentSnapshot> addOnSuccessListener(@NonNull OnSuccessListener<? super DocumentSnapshot> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<DocumentSnapshot> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super DocumentSnapshot> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<DocumentSnapshot> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super DocumentSnapshot> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<DocumentSnapshot> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<DocumentSnapshot> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<DocumentSnapshot> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        return null;
    }
}
