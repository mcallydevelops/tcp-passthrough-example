package org.mcallydevelops;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    static Thread thread;

    @BeforeAll
    static void setup() {
        thread = new Thread(() -> {
            try {
                new Server(Context.defaultContext()).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Test
    void run() throws IOException {
        TestClient testClient = new TestClient();
        testClient.startConnection("localhost", 3000);
        String result = testClient.sendMessage("Hello World");
        testClient.stopConnection();
        assertEquals("Goodbye World", result);
    }

    @AfterAll
    static void tearDown() {
        thread.stop();
    }

}