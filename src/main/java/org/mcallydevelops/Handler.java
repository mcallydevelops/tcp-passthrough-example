package org.mcallydevelops;

import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class Handler implements Runnable {

    private final Engine engine;

    public Handler(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        engine.handle();
    }
}
