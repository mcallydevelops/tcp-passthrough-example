package org.mcallydevelops;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final Context context;
    private boolean running;

    public Server(Context context) {
        this.context = context;
        this.running = true;
    }

    public void run() throws IOException {
        ServerConfig serverConfig = context.getServerConfig();
        ServerSocket serverSocket = new ServerSocket(serverConfig.getServerPort());
        ExecutorService threadPool = Executors.newFixedThreadPool(serverConfig.getNumberOfThreads());
        while (running) {
            Runnable task = new Handler(serverSocket.accept(), serverConfig.getDestinationPort(), serverConfig.getDestinationUrl());
            threadPool.submit(task);
        }
    }
}
