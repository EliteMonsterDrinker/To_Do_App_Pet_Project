package com.test;

import com.model.Task;
import com.storage.WriteAndReadFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileSerializerTest {

    @TempDir
    Path tempDir;

    @Test
    public void testFileSerialization() throws Exception {
        Path targetPath = tempDir.resolve("user_data.ser");
        File targetFile = targetPath.toFile();

        List<Task> tasks = new ArrayList<>();
        Task task = new Task("Тестовая задача");
        tasks.add(task);

        WriteAndReadFile testSerializer = new WriteAndReadFile(targetFile);

        assertTrue(testSerializer.save(tasks), "save() should return true for new data");

        assertTrue(targetFile.exists(), "файл должен быть создан");
        assertTrue(targetFile.length() > 0, "Файл не должен быть пустым");

        List<Task> deserializedTasks = testSerializer.load();

        assertNotNull(deserializedTasks, "Список не должен быть null");
        assertEquals(1, deserializedTasks.size());

        Task deserializedTask = deserializedTasks.get(0);

        assertNotNull(deserializedTask, "Десериализованный объект не должен быть null");
        assertEquals("Тестовая задача", deserializedTask.getTitle());
    }
}
