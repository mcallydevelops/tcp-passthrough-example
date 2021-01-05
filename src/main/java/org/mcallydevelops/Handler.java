package org.mcallydevelops;

import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class Handler implements Runnable {

    private Socket clientSocket;
    private final int destinationPort;
    private final String destinationUrl;
    private boolean running;

    public Handler(Socket clientSocket, int destinationPort, String destinationUrl) {
        this.clientSocket = clientSocket;
        this.destinationPort = destinationPort;
        this.destinationUrl = destinationUrl;
        this.running = true;
    }

    @Override
    public void run() {
        try {
            while(running) {
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String input = clientIn.readLine();
                out.println(discernResponse(input));
                this.running = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String discernResponse(String input) {
        if("Hello World".equals(input)) {
            return "Goodbye World";
        }
        return input;
    }
}
