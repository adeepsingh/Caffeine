package me.amar0908.caffeine.ide;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Project {

    private final File caffProj;

    public Project(File caffProj) {
        this.caffProj = caffProj;
    }

    public String getName() {
        return caffProj.getName();
    }

    public List<File> getFiles() {
        return Arrays.stream(caffProj.listFiles()).filter(file -> file.getName().endsWith(".caff")).collect(Collectors.toList());
    }

    public File getFile(String name) {
        File file = new File(caffProj, name + ".caff");

        if (!file.exists()) {
            throw new IDEException("File does not exist.");
        }

        return file;
    }

    public File addFile(String name) {
        try {
            File f = new File(caffProj, name + ".caff");
            f.createNewFile();
            return f;
        } catch (Exception e) {
            throw new IDEException("Could not create file.");
        }
    }

    public void deleteFile(String name) {
        if (!new File(caffProj, name + ".caff").delete()) {
            throw new IDEException("Could not delete file.");
        }
    }
}