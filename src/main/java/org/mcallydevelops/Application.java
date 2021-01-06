package org.mcallydevelops;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Context context = Context.createProxyContext();
        Thread proxyThread  = new Thread(new Server(context, true));
        Thread serverThread = new Thread(new Server(context, false));
        proxyThread.run();
        serverThread.run();
    }
}
