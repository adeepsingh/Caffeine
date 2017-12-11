package me.amar0908.lipi.language;

import java.io.File;

import me.amar0908.lipi.ide.Console;
import me.amar0908.lipi.ide.Project;
import me.amar0908.lipi.language.Utils.Writable;

public class IDEInstance {

    public static IDEInstance CURRENT_INSTANCE;
    private final Writable writable;
    private final Project project;
    private final Class[] classes;

    private IDEInstance(Project project, Writable writable) {
        this.writable = writable;
        this.project = project;
        this.classes = new Class[project.getFiles().size()];
    }

    public static void createInstance(Project project, Utils.Writable writable) throws Utils.InvalidCodeException {
        CURRENT_INSTANCE = new IDEInstance(project, writable);
        CURRENT_INSTANCE.run();
    }

    public Writable getWritable() {
        return writable;
    }

    private void run() throws Utils.InvalidCodeException {
        Class mainClass = null;

        for (int i = 0; i < classes.length; i++) {
            File f = project.getFiles().get(i);

            me.amar0908.lipi.language.Class c = new Class(Utils.readFile(f));
            classes[i] = c;
            c.parseClass();

            if (c.hasMain()) mainClass = c;
        }

        if (mainClass == null) throw new Utils.InvalidCodeException("No main method in project.");

        mainClass.getMethod("main").run();
        writable.write("--Terminated.", Console.MessageType.OUTPUT);
    }

    public Class getPogoClass(String name) {
        for (Class c : classes) {
            if (c.name().equals(name)) return c;
        }

        return null;
    }
}
