package com.calegario.filedbgen;

import com.calegario.filedbgen.ListOfFilesGen;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

public class ListOfPathsGen {
  private String directory;
  private List<String> listOfPaths;

  public ListOfPathsGen(String directory, List<String> fileEnds) {
    this.directory = directory;
    this.listOfPaths = getListOfPaths(this.directory, fileEnds);
  }

  public ListOfPathsGen(String directory) {
    this.directory = directory;
    this.listOfPaths = getListOfPaths(this.directory);
  }

    public static List<String> getListOfPaths(
        String dirPath, List<String> fileEnds
    ) {
        /**
         * Returns a list of the path of all files inside a directory and it's
         sub diretories, filtering it by it's extensions.
        **/
        List<String> list = new ArrayList<String>();
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    list.addAll(
                        getListOfPaths(
                            f.getAbsolutePath(),
                            fileEnds
                    ));
                } else {
                    list.add(String.valueOf(f.getAbsolutePath()));
                }
            }
            return filter(list, fileEnds);
        }
        return list;
    }

    private static List<String> filter(List<String> list,
                                       List<String> fileEnds) {
        for (int i = list.size() - 1; i >= 0; i = i - 1) {
            if (!fileEnds.contains(ListOfFilesGen
                                   .getFileExtension(list.get(i)))){
                list.remove(i);
            }
        }
        return list;
    }

    public static List<String> getListOfPaths(String dirPath){
        /**
         * Returns a list of the path of all files inside a directory and it's
         sub diretories, filtering it by it's extensions.
        **/
        List<String> list = new ArrayList<String>();
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    list.addAll(
                        getListOfPaths(
                            f.getAbsolutePath()
                    ));
                } else {
                    list.add(String.valueOf(f.getAbsolutePath()));
                }
            }
            return list;
        }
        return list;
    }

    public List<String> getListOfPaths() {
        return this.listOfPaths;
    }
}
