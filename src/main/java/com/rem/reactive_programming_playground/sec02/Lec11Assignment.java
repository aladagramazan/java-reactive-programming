package com.rem.reactive_programming_playground.sec02;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec02.assignment.FileServiceImpl;

public class Lec11Assignment {

    public static void main(String[] args) {
        // create a FileService implementation
        // read the file "test.txt"
        // if the file exists, delete it
        // write a new content to the file
        // read the file again
        // print the content

        var fileService = new FileServiceImpl();

        fileService.write("test.txt", "This is a test file")
                        .subscribe(Util.subscriber());

        fileService.read("test.txt")
                .subscribe(Util.subscriber());

        fileService.delete("test.txt")
                .subscribe(Util.subscriber());
    }

}
