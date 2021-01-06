package org.mcallydevelops;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

    private final Context context;
    private boolean running;
    private boolean proxy;

    public Server(Context context, boolean proxy) {
        this.context = context;
        this.running = true;
        this.proxy = proxy;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(retrievePort());
            ExecutorService threadPool = Executors.newFixedThreadPool(context.getServerConfig().getNumberOfThreads());
            while (running) {
                Runnable task = new Handler(retrieveEngine(serverSocket.accept()));
                threadPool.submit(task);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private Engine retrieveEngine(Socket socket) throws IOException {
        if(this.proxy) {
            return new ProxyEngine(socket, context.getServerConfig().getDestinationPort(), context.getServerConfig().getDestinationUrl());
        }
        return new ServerEngine(socket);
    }

    private int retrievePort() {
        if(this.proxy) {
            return context.getServerConfig().getProxyPort();
        }
        return context.getServerConfig().getDestinationPort();
    }
}
