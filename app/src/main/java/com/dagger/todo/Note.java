package com.dagger.todo;

/**
 * Created by Harshit on 05/12/16.
 */

public class Note {

    private String title;
    private String content;
    private int isComplete;
    private String dueDate;
    private int priority;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Note(String title, String content, int isComplete, String date, int priority) {
        this.title = title;
        this.content = content;
        this.isComplete = isComplete;
        this.dueDate = date;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public int isComplete() {
        return isComplete;
    }

    public void setComplete(int complete) {
        isComplete = complete;
    }
}
