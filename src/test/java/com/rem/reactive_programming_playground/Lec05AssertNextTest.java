package com.rem.reactive_programming_playground;

import com.rem.reactive_programming_playground.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lec05AssertNextTest {

    record Book(int id, String title, String author) {
    }

    private Flux<Book> getBooks() {
        return Flux.range(1, 5)
                .map(i -> new Book(i, Util.faker().book().title(), Util.faker().book().author()));
    }

    @Test
    public void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(book -> assertEquals(1, book.id()))
                .thenConsumeWhile(book -> Objects.nonNull(book.title()))
                .expectComplete()
                .verify();
    }

    @Test
    public void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> {
                    assertEquals(5, list.size());
                    assertEquals(1, list.get(0).id());
                })
                .expectComplete()
                .verify();
    }
}
