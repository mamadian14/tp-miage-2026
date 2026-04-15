package com.acme.todolist;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.acme.todolist.domain.TodoItem;
import org.junit.jupiter.api.Test;

class TodoItemTest {

    @Test
    void should_not_add_late_when_item_is_recent() {
        TodoItem item = new TodoItem(
                "1",
                Instant.now(),
                "Faire les courses"
        );

        String result = item.finalContent();

        assertEquals("Faire les courses", result);
    }
// commentaire test
    @Test
    void should_add_late_when_item_is_older_than_24h() {
        TodoItem item = new TodoItem(
                "1",
                Instant.now().minus(2, ChronoUnit.DAYS),
                "Faire les courses"
        );

        String result = item.finalContent();

        assertTrue(result.startsWith("[LATE!]"));
    }
}