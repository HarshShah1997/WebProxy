package com.harsh.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Utils to handle requests and responses from the socket
 * Created by Harsh on 22-Jul-17.
 */
@Component
public class SocketUtils {

    private static final int BUFFER_SIZE = 1024;

    private static final Log LOG = LogFactory.getLog(SocketUtils.class);

    /**
     * Returns the input stream of the message from the socket
     * @param clientSocket - Socket of the client
     * @throws IOException - Throws IOException if there is any error in getting the stream from socket
     * @return InputStream to read the request of the client
     */
    public InputStream receive(Socket clientSocket) throws IOException {
        if (clientSocket == null) {
            throw new IllegalArgumentException("Socket of client is null");
        }
        InputStream requestFromClient = clientSocket.getInputStream();
        LOG.info(String.format("Getting Input Stream from Socket: %s", clientSocket));
        return requestFromClient;
    }

    /**
     * Sends the data to the socket
     * @param clientSocket - Socket of the client
     * @param response - InputStream of the data
     * @throws IOException - Throws IOException if there is any error in getting outputstream
     */
    public void send(Socket clientSocket, InputStream response) throws IOException {
        if (clientSocket == null) {
            throw new IllegalArgumentException("Client socket is null");
        } else if (response == null) {
            throw new IllegalArgumentException("InputStream of response is null");
        }
        OutputStream clientOutputStream = null;
        clientOutputStream = clientSocket.getOutputStream();
        LOG.info(String.format("Getting Output Stream from socket: %s", clientSocket));
        copy(response, clientOutputStream);
        LOG.info(String.format("Successfully sent data to socket: %s", clientSocket));
    }

    // Reads from input stream and writes to output stream
    private void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = inputStream.read(buffer);
        int offset = 0;
        while (bytesRead != -1) {
            outputStream.write(buffer, offset, bytesRead);
            if (inputStream.available() > 0) {
                bytesRead = inputStream.read(buffer);
            } else {
                break;
            }
        }
    }

    // For debugging purposes
    private void printBuffer(byte[] buffer, int bytesRead) {
        for (int i = 0; i < bytesRead; i++) {
            System.out.print((char)buffer[i]);
        }
        System.out.println("");
    }
}
