package com.leetcode.solution;

import java.util.*;

class _588_Design_In_Memory_File_System {
    abstract class AbstractFile {
        String name;

        AbstractFile(String name) {
            this.name = name;
        }

    }

    class Directory extends AbstractFile {
        Map<String, AbstractFile> children; //Polymorphism

        public Directory(String name) {
            super(name);
            this.children = new HashMap<>();
        }

    }

    class File extends AbstractFile {
        String content;

        public File(String name) {
            super(name);
            this.content = "";
        }
    }

    private final Directory root;

    public _588_Design_In_Memory_File_System() {
        root = new Directory("root");
    }

    public List<String> ls(String path) {
        String[] strs = getDirectories(path);

        Directory curFolder = root;
        AbstractFile child = null;

        for (int i = 1; i < strs.length; i++) {
            child = curFolder.children.get(strs[i]);
            if (child instanceof Directory) {
                curFolder = (Directory) child;
            }
        }

        // if the last filename represents a file
        if (child instanceof File) {
            return new ArrayList<>(Collections.singletonList(child.name));
        }

        // Otherwise it is a combination of files and directories.

        List<String> res = new ArrayList<>(curFolder.children.keySet());
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] strs = getDirectories(path);

        Directory curFolder = root;
        AbstractFile child = null;

        for (int i = 1; i < strs.length; i++) {

            if (!curFolder.children.containsKey(strs[i])) {
                Directory newFolder = new Directory(strs[i]);
                curFolder.children.put(strs[i], newFolder);
            }

            child = curFolder.children.get(strs[i]);

            if (child instanceof Directory) curFolder = (Directory) child;
        }
    }


    public void addContentToFile(String filePath, String content) {
        String[] strs = getDirectories(filePath);

        Directory curFolder = root;
        AbstractFile child = null;

        for (int i = 1; i < strs.length - 1; i++) {
            child = curFolder.children.get(strs[i]);
            if (child instanceof Directory) curFolder = (Directory) child;
        }

        String name = strs[strs.length - 1];
        // create a new file if it does not exist
        if (!curFolder.children.containsKey(name)) {
            File file = new File(name);
            curFolder.children.put(name, file);
        }

        File targetFile = (File) curFolder.children.get(name);
        targetFile.content += content;
    }

    public String readContentFromFile(String filePath) {
        String[] strs = filePath.split("/");

        Directory curFolder = root;
        AbstractFile child = null;

        // operations will be passed valid parameters
        for (int i = 1; i < strs.length - 1; i++) {

            child = curFolder.children.get(strs[i]);
            if (child instanceof Directory) curFolder = (Directory) child;
        }

        String name = strs[strs.length - 1];

        File targetFile = (File) curFolder.children.get(name);
        return targetFile.content;
    }

    /*
     * Get the directories by parsing the given path.
     * if the path is just "/", return an Array with just an empty String
     */
    private String[] getDirectories(String path) {
        String[] strs = null;
        if (path.equals("/")) strs = new String[]{""};
        else strs = path.split("/");
        return strs;
    }
}
