package ru.bk.artv.cityregister.net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleSocket {
    @Test
    public void simpleSocket() throws IOException {
     Socket socket = new Socket("localhost",1000);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        String command = "GET /sitemap.xml HTTP/1.1\r\nHost:java-course.ru\r\n\r\n";
        outputStream.write(command.getBytes());
        outputStream.flush();

        int c = 0;
        while ( (c = inputStream.read()) != -1) {
            System.out.print((char)c);
        }
        inputStream.close();
        outputStream.close();

        socket.close();

    }
}
