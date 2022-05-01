package com.calegario.filedbgen;

import filedbgen.ListOfPathsGen;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ListOfFilesGen {
  private String directory;

  private List<String> listOfPaths;

  private List<String> listOfFileNames;

  private List<String> listOfLastMod;

  public ListOfFilesGen(String paramString, List<String> paramList) {
    this.directory = paramString;
    this
      .listOfPaths = (new ListOfPathsGen(paramString, paramList)).getListOfPaths();
    this.listOfFileNames = getListOfFileNames();
    this.listOfLastMod = getListOfLastMod();
  }

  public ListOfFilesGen(String paramString) {
    this.directory = paramString;
    this
      .listOfPaths = (new ListOfPathsGen(paramString)).getListOfPaths();
    this.listOfFileNames = getListOfFileNames();
    this.listOfLastMod = getListOfLastMod();
  }

  public List<String> getListOfFileNames() {
    ArrayList<String> arrayList = new ArrayList();
    for (byte b = 0; b < this.listOfPaths.size(); b++) {
      Path path = Paths.get(this.listOfPaths.get(b), new String[0]);
      String str = path.getFileName().toString();
      arrayList.add(str);
    }
    return arrayList;
  }

  public List<String> getListOfLastMod() {
    ArrayList<String> arrayList = new ArrayList();
    try {
      for (byte b = 0; b < this.listOfPaths.size(); b++) {
        Path path = Paths.get(this.listOfPaths.get(b), new String[0]);
        BasicFileAttributes basicFileAttributes = (BasicFileAttributes)Files.readAttributes(path, (Class)BasicFileAttributes.class, new java.nio.file.LinkOption[0]);
        arrayList.add(basicFileAttributes.lastModifiedTime().toString());
      }
    } catch (IOException iOException) {
      iOException.printStackTrace();
    }
    return arrayList;
  }

  public List<String[]> getListOfFiles() {
    ArrayList<String[]> arrayList = new ArrayList();
    for (byte b = 0; b < this.listOfPaths.size(); b++) {
      String str1 = this.listOfFileNames.get(b);
      String str2 = getFileExtension(this.listOfFileNames.get(b));
      String str3 = this.listOfPaths.get(b);
      String str4 = this.listOfLastMod.get(b);
      arrayList.add(new String[] { str1, str2, str3, str4 });
    }
    return (List<String[]>)arrayList;
  }

  public void printListOfFiles() {
    List<String[]> list = getListOfFiles();
    System.out.println("file_name; extension; path; last_modification");
    for (byte b = 0; b < list.size(); b++) {
      String str1 = ((String[])list.get(b))[0];
      String str2 = ((String[])list.get(b))[1];
      String str3 = ((String[])list.get(b))[2];
      String str4 = ((String[])list.get(b))[3];
      System.out.println(str1 + "; " + str1 + "; " + str2 + "; " + str3);
    }
  }

  public void setDirectory(String paramString) {
    this.directory = paramString;
  }

  public String getDirectory() {
    return this.directory;
  }

  public static String getFileExtension(String paramString) {
    int i = paramString.lastIndexOf(".");
    String str = "";
    if (i > 0)
      str = paramString.substring(i + 1);
    return str;
  }
}
