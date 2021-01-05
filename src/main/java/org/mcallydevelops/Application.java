package org.mcallydevelops;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Context context = Context.createContext();
        new Server(context).run();
    }
}
