package me.amar0908.caffeine.languageMain;

import me.amar0908.caffeine.Caffeine;
import me.amar0908.caffeine.ide.Console;
import me.amar0908.caffeine.ide.Project;
import me.amar0908.caffeine.languageMain.block.*;
import me.amar0908.caffeine.languageMain.block.Class;
import me.amar0908.caffeine.languageMain.parser.*;
import me.amar0908.caffeine.languageMain.tokenizer.CaffeineTokenizer;

import java.io.IOException;
import java.util.Scanner;

public class Runtime {

    public static Runtime RUNTIME;

    private final Project project;
    private final RootBlock[] classes;

    private Runtime(Project project) {
        this.project = project;
        this.classes = new RootBlock[project.getFiles().size()];
    }

    public static void run(Project project) throws InvalidCodeException, IOException {
        RUNTIME = new Runtime(project);
        RUNTIME.run();
    }

    private void run() throws InvalidCodeException, IOException {
        Parser[] parsers = {
                new ClassParser(),
                new ConstructorParser(),
                new ForParser(),
                new IfParser(),
                new MethodInvocationParser(),
                new MethodParser(),
                new PropertyParser(),
                new ReturnParser(),
                new VariableDeclarationParser(),
                new VariableReassignmentParser(),
                new WhileParser()
        };

        me.amar0908.caffeine.languageMain.block.Class mainClass = null;

        for (int i = 0; i < classes.length; i++) {
            Block block = null;

            for (String line : Utils.readFile(project.getFiles().get(i), false)) {
                line = line.trim();
                CaffeineTokenizer tokenizer = new CaffeineTokenizer(line);

                String firstToken = tokenizer.nextToken().getToken();
                tokenizer.pushBack();

                if (firstToken == null) {
                    continue;
                }

                if (firstToken.startsWith("//")) {
                    continue;
                }

                if (firstToken.equals("end")) {
                    if (block == null) {
                        throw new InvalidCodeException("Attempted to end non-existent block.");
                    }

                    if (!(block instanceof Endable)) {
                        throw new InvalidCodeException("Attempted to end non-endable block.");
                    }

                    block = block.getSuperBlock();

                    continue;
                }

                boolean success = false;

                for (Parser parser : parsers) {
                    if (parser.shouldParseLine(line)) {
                        if (parser.getType() == Constructor.class || parser.getType() == Method.class && block != null) {
                            block = block.getBlockTree()[0];
                        }

                        Block newBlock = parser.parse(block, tokenizer);

                        if (block == null) {
                            if (!(newBlock instanceof RootBlock)) {
                                throw new InvalidCodeException("File does not begin with root block declaration.");
                            }
                        }

                        else {
                            if (newBlock instanceof Method || newBlock instanceof Constructor) { // If it is a method or constructor, we add the method to the class.
                                block.getBlockTree()[0].add(newBlock);
                            }

                            else {
                                block.add(newBlock);
                            }
                        }

                        if (!(newBlock instanceof ReadOnlyBlock)) { // If it is a read only block, we don't want that to be the new superblock.
                            block = newBlock;
                        }

                        success = true;
                        break;
                    }
                }

                if (!success) {
                    throw new InvalidCodeException("Could not parse line " + line);
                }
            }

            if (block != null) {
                classes[i] = (RootBlock) block.getBlockTree()[0];
            }

            else {
                throw new InvalidCodeException("Empty file.");
            }

            if (classes[i] instanceof Class && classes[i].hasMethod("main")) {
                mainClass = (Class) classes[i];
            }
        }

        if (mainClass == null) {
            throw new InvalidCodeException("No main method in project.");
        }

        mainClass.run();
    }

    public <T extends Block> T getPogoClass(String name) {
        for (RootBlock c : classes) {
            if (c.getName().equals(name)) {
                return (T) c;
            }
        }

        return null;
    }

    public void print(String msg, Console.MessageType type) {
        if (Caffeine.getIDE() != null) {
            Caffeine.getIDE().getConsole().write(msg, type);
        }

        else {
            if (type == Console.MessageType.ERROR) {
                System.err.println(msg);
            }

            else {
                System.out.println(msg);
            }
        }
    }

    public String prompt() {
        if (Caffeine.getIDE() != null) {
            return Caffeine.getIDE().getConsole().prompt();
        }

        else {
            return new Scanner(System.in).nextLine();
        }
    }
}