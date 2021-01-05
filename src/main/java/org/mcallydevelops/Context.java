package org.mcallydevelops;

import java.io.IOException;

/**
 * If dependencies are required, we will wire them up here
 */
public class Context {

    private final ServerConfig serverConfig;

    private Context(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public static Context createContext() throws IOException {
        ServerConfig config = ServerConfig.createServerConfig();
        return new Context(config);
    }

    public static Context defaultContext() throws IOException {
        ServerConfig config = ServerConfig.defaultContext();
        return new Context(config);
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }
}
