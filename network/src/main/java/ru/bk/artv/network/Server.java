package ru.bk.artv.network;


import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Server.main коннектнулся");
            handleRequest(socket);

        }
    }

    private static void handleRequest(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        System.out.println("Создаем строку");
        StringBuilder sb = new StringBuilder("Hello ");
        String userName = br.readLine();

        System.out.println("получили + " + userName);
        sb.append(userName);
        bw.write(sb.toString());
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
        socket.close();
    }
}
