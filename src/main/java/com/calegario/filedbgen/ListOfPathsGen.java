package com.calegario.filedbgen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListOfPathsGen {
  private String directory;

  private List<String> listOfPaths;

  public ListOfPathsGen(String paramString, List<String> paramList) {
    this.directory = paramString;
    this.listOfPaths = getListOfPaths(true, paramList);
  }

  public ListOfPathsGen(String paramString) {
    this.directory = paramString;
    this.listOfPaths = getListOfPaths(true);
  }

  public List<String> getListOfPaths(boolean paramBoolean, List<String> paramList) {
    if (paramBoolean) {
      try {
        Stream<Path> stream = Files.walk(Paths.get(this.directory, new String[0]), new java.nio.file.FileVisitOption[0]);
        try {
          List<String> list = (List)stream.filter(paramPath -> endFilter(paramPath, paramList)).map(paramPath -> paramPath.toString()).collect(Collectors.toList());
          if (stream != null)
            stream.close();
          return list;
        } catch (Throwable throwable) {
          if (stream != null)
            try {
              stream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }
          throw throwable;
        }
      } catch (IOException iOException) {
        iOException.printStackTrace();
      }
    } else if (!paramBoolean) {
      getListOfPaths();
    }
    ArrayList<String> arrayList = new ArrayList();
    try {
      arrayList.add("Empty");
    } catch (ClassCastException classCastException) {
      classCastException.printStackTrace();
    }
    return arrayList;
  }

  public List<String> getListOfPaths(boolean paramBoolean) {
    if (paramBoolean) {
      try {
        Stream<Path> stream = Files.walk(Paths.get(this.directory, new String[0]), new java.nio.file.FileVisitOption[0]);
        try {
          List<String> list = (List)stream.map(paramPath -> paramPath.toString()).collect(Collectors.toList());
          if (stream != null)
            stream.close();
          return list;
        } catch (Throwable throwable) {
          if (stream != null)
            try {
              stream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }
          throw throwable;
        }
      } catch (IOException iOException) {
        iOException.printStackTrace();
      }
    } else if (!paramBoolean) {
      getListOfPaths();
    }
    ArrayList<String> arrayList = new ArrayList();
    try {
      arrayList.add("Empty");
    } catch (ClassCastException classCastException) {
      classCastException.printStackTrace();
    }
    return arrayList;
  }

  public List<String> getListOfPaths() {
    return this.listOfPaths;
  }

  public static boolean endFilter(Path paramPath, List<String> paramList) {
    String str = paramPath.getFileName().toString().toLowerCase();
    for (byte b = 0; b < paramList.size(); b++) {
      if (str.endsWith(paramList.get(b)))
        return true;
    }
    return false;
  }
}
