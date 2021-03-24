package org.openjfx.file_utilities.file_tasks;

import javafx.concurrent.Task;
import org.openjfx.file_utilities.FileWriters;
import java.util.ArrayList;

/** This class is responsible for executing the correct file writers found in file_io directory. */

public class Writer<T> extends Task<Void> {

    private FileWriters fileWriters;
    private String filepath;
    private ArrayList<T> data;

    protected void runFileWriter(){
        fileWriters.write(data, filepath);
    }

    public void setFileWriter(FileWriters fileWriters) {
        this.fileWriters = fileWriters;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    protected Void call() throws Exception {
        for(int i = 0; i < 100; i++) {
            Thread.sleep(20);
            updateProgress(i, 100);
        }
        runFileWriter();
        return null;
    }
}
