package me.amar0908.lipi.ide;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Project {

    private final File lipiProj;

    public Project(File lipiProj) {
        this.lipiProj = lipiProj;
    }

    public String getName() {
        return lipiProj.getName();
    }

    public List<File> getFiles() {
    	return Arrays.stream(lipiProj.listFiles()).filter(file -> file.getName().endsWith(".lip")).collect(Collectors.toList());
       // return Arrays.stream(caffProj.listFiles()).filter(file -> file.getName().endsWith(".lip")).collect(Collectors.toList());
    }

    public File getFile(String name) {
        File file = new File(lipiProj, name + ".lip");

        if (!file.exists()) {
            throw new IDEException("File does not exist.");
        }

        return file;
    }

    public File addFile(String name) {
        try {
            File f = new File(lipiProj, name + ".lip");
            f.createNewFile();
            return f;
        } catch (Exception e) {
            throw new IDEException("Could not create file.");
        }
    }

    public void deleteFile(String name) {
        if (!new File(lipiProj, name + ".lip").delete()) {
            throw new IDEException("Could not delete file.");
        }
    }
}