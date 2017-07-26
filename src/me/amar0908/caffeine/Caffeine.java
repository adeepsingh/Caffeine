package me.amar0908.caffeine;

import javafx.stage.Stage;
import me.amar0908.caffeine.ide.IDE;
import me.amar0908.caffeine.ide.Project;
import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Runtime;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class Caffeine {

    private static IDE ide;

    public void start(Stage stage) {
        (ide = new IDE()).start(stage);
    }

    public static IDE getIDE() {
        return ide;
    }

//    public Caffeine() {
//        final boolean usingConsole = args.length > 0;
//
//        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> {
//            if (usingConsole) {
//                System.err.println("Error: " + e);
//            }
//
//            else {
//                System.out.println("The following stack trace was caught and will be shown to the user:");
//                e.printStackTrace();
//
//                if (ide != null && !(e instanceof Utils.IDEException)) {
//                    ide.getConsole().write("Error: " + e, Console.MessageType.ERROR);
//                }
//
//                else {
//                    JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//
//        if (usingConsole) {
//            File projectPath = new File(args[0]);
//            if (!projectPath.exists()) {
//                throw new Utils.ConsoleException("Could not find project at path.");
//            }
//
//            try {
//                Instance.run(new Project(projectPath), new Utils.Writable() {
//                    @Override
//                    public void write(String text, Console.MessageType messageType) {
//                        if (messageType == Console.MessageType.ERROR) System.err.println(text);
//                        else System.out.println(text);
//                    }
//                });
//            } catch (Exception e) {
//                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
//            }
//        } else {
//            launch(args);
//        }
//    }

    public static void main(String[] args) throws InvalidCodeException, IOException {
//        launch(args);

        Runtime.run(new Project(new File(FileSystemView.getFileSystemView().getHomeDirectory(), "C:/Users/amar0_000/EclipseWorkspace/Caffeine/src/me/amar0908/caffeine/Examples")));
    }
}
