package com.harsh.handler;

import com.harsh.util.SocketUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

/**
 * Gets request from the client and sends it to the server and provides it response back to the client
 * Created by Harsh on 24-Jul-17.
 */
@Component
public class ClientHandler {

    @Autowired
    private SocketUtils socketUtils;

    private static final Log LOG = LogFactory.getLog(ClientHandler.class);

    /**
     * Gets request from client and sends the request to the server,
     * returns the response from server to the client
     *
     * @param serverSocket - Socket of the server
     * @param clientSocket - Socket of the client
     */
    public void handleClient(Socket serverSocket, Socket clientSocket) {
        if (serverSocket == null) {
            throw new IllegalArgumentException("Server socket is null");
        }
        if (clientSocket == null) {
            throw new IllegalArgumentException("Client socket is null");
        }
        try {
            socketUtils.send(serverSocket, socketUtils.receive(clientSocket));
            socketUtils.send(clientSocket, socketUtils.receive(serverSocket));
        } catch (IOException e) {
            LOG.error(String.format("For Server socket:%s and Client socket:%s Error Details:%s",
                    serverSocket, clientSocket, e.getMessage()));
        }
    }
}
