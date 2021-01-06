package org.mcallydevelops;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    static Thread proxyThread;
    static Thread serverThread;
    TestClient testClient;

    @BeforeAll
    static void beforeAll() throws IOException {
        proxyThread = new Thread(new Server(Context.createDefaultContext(), true));
        serverThread = new Thread(new Server(Context.createProxyContext(), false));
        proxyThread.start();
        serverThread.start();
    }

    @BeforeEach
    void setup() throws IOException {
        testClient = new TestClient();
        testClient.startConnection("localhost", 8080);
    }

    @Test
    void run() throws IOException {
        String result = testClient.sendMessage("Hello World!");
        testClient.stopConnection();
        assertEquals("Goodbye World!", result);
    }

    @AfterEach
    void tearDown() throws IOException {
        testClient.stopConnection();
    }

    @AfterAll
    static void afterAll() {
        proxyThread.stop();
        serverThread.stop();
    }

}