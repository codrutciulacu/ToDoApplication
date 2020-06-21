package com.codrut.todoapplication.data.source;


import com.codrut.todoapplication.data.Task;
import com.codrut.todoapplication.data.source.mockTasks.AddTaskSuccessTask;
import com.codrut.todoapplication.data.source.mockTasks.GetTaskSuccessTask;
import com.codrut.todoapplication.data.source.mockTasks.GetTasksSuccessTask;
import com.codrut.todoapplication.data.source.mockTasks.TaskFailTask;
import com.codrut.todoapplication.data.source.mockTasks.VoidTask;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskRepositoryTest {

    private TaskRepository mTaskRepository;

    @Mock private FirebaseFirestore mFirestore;
    @Mock private CollectionReference collectionReference;
    @Mock private DocumentReference documentReference;
    @Mock private OnResponseListener<Void> mVoidOnResponseListener;
    @Mock private OnResponseListener<Task> mTaskOnResponseListener;
    @Mock private OnResponseListener<List<Task>> mListOfTasksOnResponseListener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mFirestore.collection(any())).thenReturn(collectionReference);
        when(mFirestore.collection(any()).document(any())).thenReturn(documentReference);

        mTaskRepository = new TaskRepository(mFirestore);
    }

    @Test
    public void test_addTask_success() {
        Task mockTask = new Task("test", "make that app", "bla", false, LocalDate.now());
        com.google.android.gms.tasks.Task<DocumentReference> addTaskSuccessTask = new AddTaskSuccessTask();
        when(mFirestore.collection(any()).add(any())).thenReturn(addTaskSuccessTask);

        mTaskRepository.addTask(mockTask, mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onSuccess(null);
    }

    @Test
    public void test_addTask_fail() {
        Task mockTask = new Task("test", "make that app", "bla", false, LocalDate.now());
        com.google.android.gms.tasks.Task<DocumentReference> addTaskFailTask =
                new TaskFailTask<>();
        when(mFirestore.collection(any()).add(any())).thenReturn(addTaskFailTask);

        mTaskRepository.addTask(mockTask, mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onFailure(any());
    }

    @Test
    public void test_getTask_success() {
        Task mockTask = new Task("test", "make that app", "bla", false, LocalDate.now());
        DocumentSnapshot documentSnapshot = mock(DocumentSnapshot.class);
        when(documentSnapshot.toObject(any())).thenReturn(mockTask);

        com.google.android.gms.tasks.Task<DocumentSnapshot> getTaskSuccessTask =
                new GetTaskSuccessTask(documentSnapshot);
        when(mFirestore.collection(any()).document(any()).get()).thenReturn(getTaskSuccessTask);

        mTaskRepository.getTask("test", mTaskOnResponseListener);

        verify(mTaskOnResponseListener, times(1)).onSuccess(mockTask);
    }

    @Test
    public void test_getTask_fail() {
        com.google.android.gms.tasks.Task<DocumentSnapshot> getTaskFailTask =
                new TaskFailTask<>();
        when(mFirestore.collection(any()).document(any()).get()).thenReturn(getTaskFailTask);

        mTaskRepository.getTask("test", mTaskOnResponseListener);

        verify(mTaskOnResponseListener, times(1)).onFailure(any());
    }

    @Test
    public void test_getTasks_success() {
        Task mockTask = new Task("test", "make that app", "bla", false, LocalDate.now());
        QuerySnapshot querySnapshot = mock(QuerySnapshot.class);
        when(querySnapshot.toObjects(any())).thenReturn(singletonList(mockTask));

        com.google.android.gms.tasks.Task<QuerySnapshot> getTasksSuccessTask =
                new GetTasksSuccessTask(querySnapshot);
        when(mFirestore.collection(any()).get()).thenReturn(getTasksSuccessTask);

        mTaskRepository.getTasks(mListOfTasksOnResponseListener);

        verify(mListOfTasksOnResponseListener, times(1)).onSuccess(singletonList(mockTask));
    }

    @Test
    public void test_getTasks_fail() {
        com.google.android.gms.tasks.Task<QuerySnapshot> getTaskFailTask =
                new TaskFailTask<>();
        when(mFirestore.collection(any()).get()).thenReturn(getTaskFailTask);

        mTaskRepository.getTasks(mListOfTasksOnResponseListener);

        verify(mListOfTasksOnResponseListener, times(1)).onFailure(any());
    }

    @Test
    public void test_completeTask_success() {
        Task mockTask = new Task("test", "make that app", "bla", false, LocalDate.now());
        com.google.android.gms.tasks.Task<Void> completeTaskSuccessTask = new VoidTask();
        when(mFirestore.collection(any()).document(any()).update("complete", true)).thenReturn(completeTaskSuccessTask);

        mTaskRepository.completeTask("test", mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onSuccess(null);
    }

    @Test
    public void test_completeTask_fail() {
        com.google.android.gms.tasks.Task<Void> getTaskFailTask =
                new TaskFailTask<>();
        when(mFirestore.collection(any()).document(any()).update("complete", true)).thenReturn(getTaskFailTask);

        mTaskRepository.completeTask("test", mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onFailure(any());
    }

    @Test
    public void test_activateTask_success() {
        Task mockTask = new Task("test", "make that app", "bla", false, LocalDate.now());
        com.google.android.gms.tasks.Task<Void> activateTaskSuccessTask = new VoidTask();
        when(mFirestore.collection(any()).document(any()).update("complete", false))
                .thenReturn(activateTaskSuccessTask);

        mTaskRepository.activateTask("test", mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onSuccess(null);
    }

    @Test
    public void test_activateTask_fail() {
        com.google.android.gms.tasks.Task<Void> getTaskFailTask =
                new TaskFailTask<>();
        when(mFirestore.collection(any()).document(any()).update("complete", false))
                .thenReturn(getTaskFailTask);

        mTaskRepository.activateTask("test", mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onFailure(any());
    }

    @Test
    public void test_deleteTask_success() {
        Task mockTask = new Task("test", "make that app", "bla", false, LocalDate.now());
        com.google.android.gms.tasks.Task<Void> activateTaskSuccessTask = new VoidTask();
        when(mFirestore.collection(any()).document(any()).delete()).thenReturn(activateTaskSuccessTask);

        mTaskRepository.deleteTask("test", mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onSuccess(null);
    }

    @Test
    public void test_deleteTask_fail() {
        com.google.android.gms.tasks.Task<Void> getTaskFailTask =
                new TaskFailTask<>();
        when(mFirestore.collection(any()).document(any()).delete()).thenReturn(getTaskFailTask);

        mTaskRepository.deleteTask("test", mVoidOnResponseListener);

        verify(mVoidOnResponseListener, times(1)).onFailure(any());
    }
}