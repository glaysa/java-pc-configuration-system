package org.openjfx.file_utilities.file_io;

import org.openjfx.file_utilities.FileReaders;
import org.openjfx.file_utilities.FileWriters;
import org.openjfx.file_utilities.FileParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Formatter;

public class IO_txt implements FileWriters, FileReaders {

    @Override
    public <T> void write(ArrayList<T> data, String filepath) {
        try {
            Formatter x = new Formatter(filepath);
            for(T line : data){
                x.format("%s",line.toString());
            }
            x.close();
        } catch (Exception e) {
            System.err.println("\nSystem error: IO_txt.write()");
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public <T> ArrayList<T> read(String filepath) {
        ArrayList<T> data = new ArrayList<>();
        String lines;
        try {
            File file = new File(filepath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while((lines = bufferedReader.readLine()) != null) {
                T obj = FileParser.setParser(lines);
                data.add(obj);
            }
        } catch (Exception e) {
            System.err.println("\nSystem error: IO_txt.read()\n");
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
        return data;
    }
}