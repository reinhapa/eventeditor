package io.hackergarten.eventeditor.model;

import static java.nio.file.Files.exists;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;


import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests the basic functionality of the {@link EventList} implementation.
 */
class EventListTest {
    @TempDir
    Path tempFolder;

    EventList eventList;

    @BeforeEach
    void setUp() {
        eventList = new EventList();
    }

    @Test
    void testLoadPath() throws Exception {
        Path eventFile = tempFolder.resolve("events.json");
        try (InputStream in = getClass().getResourceAsStream("events.json")) {
            Files.copy(in, eventFile);
        }

        eventList.load(eventFile);
    }

    @Test
    void testLoadPath_withParseFailure() throws Exception {
        Path eventFile = tempFolder.getRoot().resolve("events.json");
        Files.write(eventFile, Collections.singleton("illegal content"));

        assertThatExceptionOfType(JsonParseException.class).isThrownBy(() -> eventList.load(eventFile));
    }

    @Test
    void testLoadInputStream() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("events.json")) {
            eventList.load(in);
            assertThat(eventList).hasSize(2);
        }
    }

    @Test
    void testLoadInputStream_withParseFailure() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("illegal content".getBytes());
        assertThatExceptionOfType(JsonParseException.class).isThrownBy(() -> eventList.load(in));
    }

    @Test
    void testAddGet() {
        Event event1 = new Event();
        Event event2 = new Event();
        assertThat(eventList.add(event1)).isTrue();
        assertThat(eventList.add(event2)).isTrue();
        assertThat(eventList).containsExactly(event1,event2);
    }

    @Test
    void testSetRemove() {
        Event event1 = new Event();
        Event event2 = new Event();
        eventList.add(event1);
        assertThat(eventList.set(0, event2)).isEqualTo(event1);
        assertThat(eventList.remove(0)).isEqualTo(event2);
    }

    @Test
    void testLoadHackergartenEvents() throws Exception {
        Path eventFile = Paths.get(System.getProperty("user.home"))
                .resolve("git/hackergarten.github.io/events.json");
        if (exists(eventFile)) {
            eventList.load(eventFile);
        }
    }
}
