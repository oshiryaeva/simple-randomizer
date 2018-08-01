package com.otus.scops;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Randomizer {

    private static final String ASK_PATH = "Enter the path to the files: ";
    private static final String EMPTY = "The folder is empty";
    private static final String CONFIRM_RENAME = " files will be renamed. Continue? Y/n";
    private static final String RENAMED = " files were randomized and renamed.";

    protected void go() {
        System.out.println(ASK_PATH);
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null && listOfFiles.length > 0) {
            ArrayList<File> files = new ArrayList<>(Arrays.asList(listOfFiles));
            Collections.shuffle(files);
            int size = files.size();
            System.out.println(size + CONFIRM_RENAME);
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("y") || input.equals("")) {
                for (int i = 0; i < size; i++) {
                    if (files.get(i).isFile()) {
                        File file = new File(path, files.get(i).getName());
                        String name = file.getName();
                        System.out.println("Iteration " + (i + 1) + ". Current name: " + name);
                        while (Character.isDigit(name.charAt(0)) || Character.isSpaceChar(name.charAt(0))) {
                            name = name.substring(1);
                        }
                        int iLength = String.valueOf(size).length() - String.valueOf(i + 1).length();
                        String zeros = "";
                        if (iLength != 0)
                            zeros = new String(new char[iLength]).replace("\0", "0");
                        String newName = zeros + (i + 1) + " " + name;
                        File newFile = new File(path + "\\" + newName);
                        file.renameTo(newFile);
                        System.out.println("New name: " + newName);
                    }
                }
                System.out.println(files.size() + RENAMED);
                scanner.close();
                System.exit(0);
            }
        } else {
            System.out.println(EMPTY);
            go();
        }
    }
}
