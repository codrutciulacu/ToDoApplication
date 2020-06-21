package com.codrut.todoapplication.data.source;

import androidx.annotation.NonNull;

import com.codrut.todoapplication.data.Task;
import com.codrut.todoapplication.util.CollectionsNames;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

public class TaskRepository {

    private final CollectionReference mCollectionReference;

    @Inject
    public TaskRepository(FirebaseFirestore firestore) {
        mCollectionReference = firestore.collection(CollectionsNames.tasks);
    }

    public void addTask(Task task, final OnResponseListener<Void> listener) {
        mCollectionReference.add(task)
                .addOnCompleteListener(documentReferenceTask -> {
                    if (documentReferenceTask.isSuccessful()) {
                        listener.onSuccess(null);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    public void getTask(final String id, final OnResponseListener<Task> listener) {
        mCollectionReference.document(id)
                .get()
                .addOnCompleteListener(documentSnapshotTask -> {
                    if (documentSnapshotTask.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = Objects.requireNonNull(documentSnapshotTask.getResult());
                        Task task = documentSnapshot.toObject(Task.class);

                        listener.onSuccess(task);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    public void getTasks(OnResponseListener<List<Task>> listener) {
        mCollectionReference.get()
                .addOnCompleteListener(documentSnapshotTask -> {
                    if (documentSnapshotTask.isSuccessful()) {
                        QuerySnapshot snapshots = Objects.requireNonNull(documentSnapshotTask.getResult());
                        List<Task> tasks = snapshots.toObjects(Task.class);

                        listener.onSuccess(tasks);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    public void completeTask(final String id, final OnResponseListener<Void> listener) {
        mCollectionReference.document(id)
                .update("complete", true)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess(null);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    public void updateTask(final String id, final Task task,
                           final OnResponseListener<Void> listener) {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("title", task.getTitle());
                put("content", task.getContent());
                put("date", task.getDate());
            }
        };

        mCollectionReference.document(id)
                .update(map)
                .addOnCompleteListener(result -> {
                    if (result.isSuccessful()) {
                        listener.onSuccess(null);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    public void activateTask(final String id, final OnResponseListener<Void> listener) {
        mCollectionReference.document(id)
                .update("complete", false)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess(null);
                    }
                })
                .addOnFailureListener(listener::onFailure);

    }

    public void deleteTask(final String id, final OnResponseListener<Void> listener) {
        mCollectionReference.document(id)
                .delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess(null);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }
}
