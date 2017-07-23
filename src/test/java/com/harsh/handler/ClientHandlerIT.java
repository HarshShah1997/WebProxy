package com.harsh.handler;

import com.harsh.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Harsh on 24-Jul-17.
 */

public class ClientHandlerIT extends AbstractTest {

    @Autowired
    private ClientHandler clientHandler;

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8000;

    private static final int CLIENT_PORT = 5000;

    @Test
    public void testClientHandler() throws IOException {
        ServerSocket host = new ServerSocket(CLIENT_PORT);
        Socket clientSocket = host.accept();
        Socket serverSocket = new Socket(SERVER_HOST, SERVER_PORT);
        clientHandler.handleClient(serverSocket, clientSocket);
        clientSocket.close();
        serverSocket.close();
    }
}
