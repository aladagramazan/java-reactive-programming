package com.rem.reactive_programming_playground.sec04;

import com.rem.reactive_programming_playground.common.Util;
import com.rem.reactive_programming_playground.sec04.assignment.FileReaderServiceImpl;
import reactor.core.publisher.Flux;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Lec09Assignment {

    public static void main(String[] args) {
       var filePath = Path.of("/Users/ramazanaladag/IdeaProjects/reactive-programming-playground/src/main/resources/sec04/file.txt");
        var readerService = new FileReaderServiceImpl();
        readerService.read(filePath)
                .takeUntil(line -> line.equalsIgnoreCase("line20"))
                .subscribe(Util.subscriber());
    }
}

       /* String filePath = "/Users/ramazanaladag/IdeaProjects/reactive-programming-playground/src/main/resources/sec04/file.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 1; i <= 10000; i++) {
                writer.write("line" + i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    } */
