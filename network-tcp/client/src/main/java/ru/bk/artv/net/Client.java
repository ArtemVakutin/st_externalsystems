package ru.bk.artv.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 12; i++) {
            SimpleClient simpleClient = new SimpleClient(i);
            simpleClient.start();
        }

    }

}

class SimpleClient extends Thread {

    int cmdNumber;
    public static final String[] COMMAND = new String[]{
            "HELLO", "MORNING", "DAY", "EVENING"
    };

    public SimpleClient(int cmdNumber){
        this.cmdNumber = cmdNumber;

    }

    @Override
    public void run(){
        try {
            Socket socket = new Socket("127.0.0.1",1000);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            String command = COMMAND[cmdNumber % COMMAND.length];
            String sb = command + " Artv";
            bw.write(sb);
            bw.newLine();
            bw.flush();
            System.out.println("Передаем строку");

            String s = br.readLine();
            System.out.println(s);
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
