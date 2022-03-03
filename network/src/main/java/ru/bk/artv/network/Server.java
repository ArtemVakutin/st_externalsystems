package ru.bk.artv.network;


import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(1000, 1000); //second is limit of request
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Server.main коннектнулся");
            new SimpleServer(socket).start();
        }
    }
}

class SimpleServer extends Thread{
    private Socket socket;

    public SimpleServer(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        handleRequest();
    }

    private void handleRequest(){

        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];
            System.out.println("Server got string + " + command);
            System.out.println("Server got string + " + userName);

            String response = buildResponse(command, userName);

            Thread.sleep(2000);
            bw.write(response);
            bw.newLine();
            bw.flush();
            bw.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String buildResponse(String command, String userName) {
        if(command.equals("HELLO")){
            return ("Hello " + userName);
        } if (command.equals("MORNING")){
            return ("Good morning " + userName);
        }if (command.equals("DAY")){
            return ("Good day " + userName);
        } if (command.equals("EVENING")) {
            return ("Good evening " + userName);
        } else {
            return "Nothing";
        }

    }
}