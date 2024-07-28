package com.rem.reactive_programming_playground.sec02.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final Path PATH = Path.of("/Users/ramazanaladag/IdeaProjects/reactive-programming-playground/src/main/resources/sec02");

    @Override
    public Mono<String> read(String filename) {
        return Mono.fromCallable(() -> Files.readString(PATH.resolve(filename)));
    }

    @Override
    public Mono<Void> write(String filename, String content) {
        return Mono.fromRunnable(() -> writeFile(filename, content));
    }

    @Override
    public Mono<Void> delete(String filename) {
        return Mono.fromRunnable(() -> deleteFile(filename));
    }

    private void writeFile(String filename, String content) {
        try {
            Files.writeString(PATH.resolve(filename), content);
            logger.info("File has been written successfully {} ", filename);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    private void deleteFile(String filename) {
        try {
            Files.delete(PATH.resolve(filename));
            logger.info("File has been deleted successfully {} ", filename);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }
}
