package ru.bk.artv.network;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",1000);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        String command = "Artv";
        bw.write(command);
        bw.newLine();
        bw.flush();
        System.out.println("Передаем строку");
        String s = br.readLine();
        System.out.println(s);

        inputStream.close();
        outputStream.close();

        socket.close();

    }

}
