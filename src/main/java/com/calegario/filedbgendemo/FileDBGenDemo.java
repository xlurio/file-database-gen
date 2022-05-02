package com.calegario.filedbgendemo;

public class FileDBGenDemo {
    public static void main(String[] args) {
        ListOfFilesGen generator = new ListOfFilesGen(
            "C:\Users\lucas.correa\Pictures"
        );
        System.out.println("Testing output of database!")
        generator.printListOfFiles();
    }
}
