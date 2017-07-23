package com.harsh.util;

import com.harsh.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Harsh on 23-Jul-17.
 */
public class SocketUtilsIT extends AbstractTest {

    @Autowired
    private SocketUtils socketUtils;

    private static final int PORT = 5000;

    @Test
    public void testRequestAndResponseFromClient() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket clientSocket = serverSocket.accept();
        InputStream incomingRequest = socketUtils.receive(clientSocket);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(incomingRequest));
        String line = null;
        while ((line = bufferedReader.readLine()) != null && line.length() > 0) {
            System.out.println(line);
        }

        String testResponse = "HTTP/1.1 200 OK\n";
        testResponse += "Content-Type: text/html\n\n";
        testResponse += "Hello, world!";

        InputStream stream = new ByteArrayInputStream(testResponse.getBytes("UTF-8"));
        socketUtils.send(clientSocket, stream);
        clientSocket.close();
        incomingRequest.close();
    }
}
