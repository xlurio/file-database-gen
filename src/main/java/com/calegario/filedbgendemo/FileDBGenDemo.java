package com.calegario.filedbgendemo;

import com.calegario.filedbgen.ListOfFilesGen;

public class FileDBGenDemo {
    public static void main(String[] args) {
        ListOfFilesGen generator = new ListOfFilesGen(
            "C:\Users\lucas.correa\Pictures"
        );
        generator.printListOfFiles();
    }
}
