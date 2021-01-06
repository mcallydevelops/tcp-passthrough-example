package org.mcallydevelops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerEngine implements Engine {

    private Socket clientSocket;
    private boolean running;

    public ServerEngine(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.running = true;
    }

    @Override
    public void handle() {
        try {
            while (this.running) {
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String input  = clientIn.readLine();
                out.println(discernOutput(input));
                this.running = false;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private String discernOutput(String input) {
        if("Hello World!".equals(input)) {
            return "Goodbye World!";
        }
        return input;
    }
}
