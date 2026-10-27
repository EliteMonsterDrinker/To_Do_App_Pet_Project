package com.model;

import java.io.Serializable;

public class Task implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final String title;
    private boolean done;
    public Task(String title)
    {
        this.title = title;
        this.done = false;
    }

    public String getTitle()
    {
        return title;
    }
    public boolean isDone()
    {
        return done;
    }
    public void changeDoneStatus()
    {
        this.done = !this.done;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Title=" +this.title);
        sb.append("Done=" + (this.done ? "true" : "false"));
        return sb.toString();
    }
}
