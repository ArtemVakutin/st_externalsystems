package ru.bk.artv.net;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String, Greet> handlers = loadHandlers();
        ServerSocket serverSocket = new ServerSocket(1000, 1000); //second is limit of request
        while (true){

            Socket socket = serverSocket.accept();
            System.out.println("Server.main коннектнулся");
            new SimpleServer(socket, handlers).start();
        }
    }

    private static Map<String, Greet> loadHandlers() {
        Map<String,Greet> result = new HashMap<>();
        try(InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")){
            Properties properties = new Properties();
            properties.load(is);
            for(Object name : properties.keySet()){
                String className = properties.getProperty(name.toString());
                Class<Greet> greetClass = (Class<Greet>)Class.forName(className);
                Greet handler = greetClass.newInstance();
                result.put(name.toString(), handler);
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        return result;
    }
}

class SimpleServer extends Thread{
    private Socket socket;
    Map<String, Greet> handlers;

    public SimpleServer(Socket socket, Map<String, Greet> handlers) {
        this.socket = socket;
        this.handlers = handlers;
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
        Greet greet = handlers.get(command);
        if (greet != null) {
            return greet.buildResponse(userName);
        }
        return "nothing";
    }
}