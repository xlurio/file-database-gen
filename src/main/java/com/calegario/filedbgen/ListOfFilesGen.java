package com.calegario.filedbgen;

import com.calegario.filedbgen.ListOfPathsGen;
import java.io.*;
import java.lang.*;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.time.format.*;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoField;

public class ListOfFilesGen {
  private String directory;
  private List<String> listOfPaths;
  private List<String> listOfFileNames;
  private List<String> listOfLastMod;

    public ListOfFilesGen(String paramString, List<String> paramList)
        throws IOException, FileNotFoundException
    {
        this.directory = paramString;
        this.listOfPaths =
            (new ListOfPathsGen(paramString, paramList)).getListOfPaths();
        this.listOfFileNames = getListOfFileNames();
        this.listOfLastMod = getListOfLastMod();
    }

    public ListOfFilesGen(String paramString)
        throws IOException, FileNotFoundException
    {
        this.directory = paramString;
        this
        .listOfPaths = (new ListOfPathsGen(paramString)).getListOfPaths();
        this.listOfFileNames = getListOfFileNames();
        this.listOfLastMod = getListOfLastMod();
    }

    public List<String> getListOfFileNames() {
        List<String> arrayList = new ArrayList<String>();
        for (int b = 0; b < this.listOfPaths.size(); b++) {
            Path path = Paths.get(this.listOfPaths.get(b), new String[0]);
            String str = path.getFileName().toString();
            arrayList.add(str);
        }
        return arrayList;
    }

    public List<String> getListOfLastMod()
        throws IOException
    {
        List<String> arrayList = new ArrayList<String>();
        for (int b = 0; b < this.listOfPaths.size(); b++) {
            Path path = Paths.get(this.listOfPaths.get(b), new String[0]);
            BasicFileAttributes basicFileAttributes =
                (BasicFileAttributes)Files.readAttributes(
                    path,
                    BasicFileAttributes.class,
                    new java.nio.file.LinkOption[0]
                );
            String lastModifiedTime = basicFileAttributes
                                      .lastModifiedTime()
                                      .toString();
            String lastModifiedFormatted = formatDate(lastModifiedTime);
            arrayList.add(basicFileAttributes.lastModifiedTime().toString());
        }
        return arrayList;
    }

    private static String formatDate(String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                                     .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                                     .optionalStart()
                                     .appendPattern(".")
                                     .appendFraction(
                                        ChronoField.MICRO_OF_SECOND,
                                        1, 7, false
                                     )
                                     .optionalEnd()
                                     .appendPattern("'Z'")
                                     .toFormatter();
        SimpleDateFormat finalFormatter =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);
        String finalDate = finalFormatter.format(formattedDate);
        return finalDate;
    }

    public List<String[]> getListOfFiles() {
        List<String[]> arrayList = new ArrayList<String[]>();
        for (int b = 0; b < this.listOfPaths.size(); b++) {
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
        for (int b = 0; b < list.size(); b++) {
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

    public static String getFileExtension(String fileName) {
        /**
         * Static method the extract the extension from a file name.
        **/
        fileName = fileName.toLowerCase();
        int i = fileName.lastIndexOf(".");
        String str = "";
        if (i > 0)
            str = fileName.substring(i + 1);
        return str;
    }

    public static String[] getInfoFromPath(String filePath)
        throws IOException, FileNotFoundException
    {
        /**
         * Returns an array of informations of the specified path
        **/
        Path path = Paths.get(filePath, new String[0]);
        BasicFileAttributes basicFileAttributes =
            (BasicFileAttributes)Files.readAttributes(
                path,
                BasicFileAttributes.class,
                new java.nio.file.LinkOption[0]
            );

        String fileName = path.getFileName().toString();
        String fileExt = getFileExtension(fileName);
        String fileLastMod = basicFileAttributes.lastModifiedTime().toString();
        return new String[]{
            fileName,
            fileExt,
            ListOfPathsGen.validateAbsPath(new File(filePath)),
            fileLastMod
        };
    }
}
