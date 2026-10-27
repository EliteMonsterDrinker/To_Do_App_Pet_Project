package com.storage;

import com.model.Task;

import java.util.List;
import java.io.IOException;

public interface TaskStorage
{
    List<Task> load() throws IOException, ClassNotFoundException;
    boolean save(List<Task> tasks) throws IOException;
}
