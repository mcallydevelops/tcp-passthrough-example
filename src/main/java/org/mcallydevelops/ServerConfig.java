package org.mcallydevelops;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerConfig {

    private final int proxyPort;

    private final int destinationPort;

    private final String destinationUrl;

    private final int numberOfThreads;

    private ServerConfig(int proxyPort, int destinationPort, String destinationUrl, int numberOfThreads) {
        this.proxyPort = proxyPort;
        this.destinationPort = destinationPort;
        this.destinationUrl = destinationUrl;
        this.numberOfThreads = numberOfThreads;
    }


    public int getProxyPort() {
        return proxyPort;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public static ServerConfig createProxyContext() throws IOException {
        InputStream inputStream = ServerConfig.class.getClassLoader().getResourceAsStream("server.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        int sPort = Integer.valueOf(properties.getProperty("server.port"));
        int dPort = Integer.valueOf(properties.getProperty("destination.port"));
        int nThreads = Integer.valueOf(properties.getProperty("server.threads"));
        String dUrl = properties.getProperty("destination.url");
        return new ServerConfig(sPort, dPort, dUrl, nThreads);
    }

    public static ServerConfig defaultProxyContext() {
        return new ServerConfig(3000, 8080, "localhost", 5);
    }
}
