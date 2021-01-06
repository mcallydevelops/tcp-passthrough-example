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

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public static Context createProxyContext() throws IOException {
        ServerConfig config = ServerConfig.createProxyContext();
        return new Context(config);
    }

    public static Context createDefaultContext() throws IOException {
        ServerConfig config = ServerConfig.defaultProxyContext();
        return new Context(config);
    }

}
