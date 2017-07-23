package com.harsh.util;

import com.harsh.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by Harsh on 22-Jul-17.
 */

public class SocketUtilsTest extends AbstractTest {

    @Autowired
    private SocketUtils socketUtils;

    @Test
    public void testGetRequest() throws IOException {
        try {
            socketUtils.receive(null);
            fail("IllegalArgumentException did not occur");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }
    }

    @Test
    public void testSendResponse() throws IOException {
        try {
            socketUtils.send(null, null);
            fail("IllegalArgumentException did not occur");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }

        Socket mockSocket = new Socket();
        try {
            socketUtils.send(mockSocket, null);
            fail("IllegalArgumentException did not occur");
        } catch (IllegalArgumentException e) {
            // Do nothing
        }
    }
}