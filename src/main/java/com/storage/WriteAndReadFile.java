package com.storage;

import com.model.Task;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.ObjectOutputStream;

public class WriteAndReadFile implements TaskStorage {
    private final File saveFile;

    public WriteAndReadFile() {
        this(new File("saves", "save.txt"));
    }

    public WriteAndReadFile(File saveFile) {
        this.saveFile = saveFile;

        File parent = saveFile.getAbsoluteFile().getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException(
                "Не получилось создать директорию для сейвов: " + parent);
        }
    }

    @Override
    public boolean save(List<Task> tasks) throws IOException {
        byte[] newData;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            for (Task task : tasks) {
                oos.writeObject(task);
            }
            newData = baos.toByteArray();
        }

        if (saveFile.exists()) {
            byte[] oldData = Files.readAllBytes(saveFile.toPath());
            if (Arrays.equals(oldData, newData)) {
                return false;
            }
        }

        Files.write(saveFile.toPath(), newData);
        return true;
    }

    @Override
    public List<Task> load() throws IOException, ClassNotFoundException {
        if (!saveFile.exists()) {
            throw new FileNotFoundException("Файл не был найден");
        }

        List<Task> buffer = new ArrayList<>();

        if (saveFile.length() == 0) {
            return buffer;
        }

        try (ObjectInputStream in =
        new ObjectInputStream(new FileInputStream(saveFile))) {
            while (true) {
                try {
                    Task task = (Task) in.readObject();
                    buffer.add(task);
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return buffer;
    }
}
