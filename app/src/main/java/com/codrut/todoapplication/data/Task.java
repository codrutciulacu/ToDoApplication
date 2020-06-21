package com.codrut.todoapplication.data;

import com.google.firebase.firestore.DocumentId;

import java.time.LocalDate;
import java.util.Date;

public class Task {
    @DocumentId private String id;
    private String title;
    private String content;
    private Boolean complete;
    private Date date;

    Task() {
    }

    public Task(String id, String title, String content, boolean complete, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.complete = complete;
        this.date = date;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Boolean isComplete() { return complete; }
    public void setComplete(Boolean complete) { this.complete = complete; }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
