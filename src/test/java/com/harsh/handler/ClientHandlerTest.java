package com.harsh.handler;

import com.harsh.AbstractTest;
import com.harsh.util.SocketUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ClientHandlerTest extends AbstractTest {

    @Autowired
    @InjectMocks
    private ClientHandler clientHandler;

    @Mock
    private SocketUtils socketUtils;

    @Test
    public void testHandleClient() throws IOException {
        setupMocks();
        Socket mockSocket = new Socket();
        clientHandler.handleClient(mockSocket, mockSocket);
    }

    @Test
    public void testExceptions() throws IOException {
        setupExceptionMocks();
        Socket mockSocket = new Socket();
        clientHandler.handleClient(mockSocket, mockSocket);

    }

    @Test
    public void testEmptyParameters() {
        try {
            clientHandler.handleClient(null, null);
            fail("Illegal Argument Exception did not occur");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }

        try {
            clientHandler.handleClient(new Socket(), null);
            fail("Illegal Argument Exception did not occur");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }
    }

    private void setupMocks() throws IOException {
        doNothing().when(socketUtils).send(any(Socket.class), any(InputStream.class));
        when(socketUtils.receive(any(Socket.class))).thenReturn(null);
    }

    private void setupExceptionMocks() throws IOException {
        doThrow(new IOException()).when(socketUtils).send(any(Socket.class), any(InputStream.class));
        doThrow(new IOException()).when(socketUtils).receive(any(Socket.class));
    }
}