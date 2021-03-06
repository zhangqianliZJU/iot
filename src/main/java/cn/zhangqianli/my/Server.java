package cn.zhangqianli.my;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server{
    private static final int PORT = 8080;
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(PORT)){
//            while (true){
            try(Socket connection  = server.accept()){
                System.out.println("Remote address is: " + connection.getRemoteSocketAddress());
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                String inputs = "";
                String line;//Fetch the random number generated by the client side.
                while ((line = reader.readLine()) != null){
                    inputs+= line;
                }
                System.out.println(inputs);
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                Date now = new Date();
                out.write(now.toString() + "\r\n");
                out.flush();
            }
//            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}