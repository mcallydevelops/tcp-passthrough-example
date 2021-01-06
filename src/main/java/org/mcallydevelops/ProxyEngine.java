package org.mcallydevelops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProxyEngine implements Engine {


    private Socket clientSocket;
    private Socket serverSocket;
    private PrintWriter outputStream;
    private BufferedReader in;

    private boolean running;

    public ProxyEngine(Socket clientSocket, int destinationPort, String destinationUrl) throws IOException {
        this.clientSocket = clientSocket;
        this.serverSocket = new Socket(destinationUrl, destinationPort);
        this.outputStream = new PrintWriter(serverSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.running = true;
    }

    @Override
    public void handle() {
        try {
            while(running) {
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String input = clientIn.readLine();
                outputStream.write(input);
                String response = in.readLine();
                out.println(response);
                this.running = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
