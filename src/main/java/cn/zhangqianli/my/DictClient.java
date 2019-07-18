package cn.zhangqianli.my;

import java.io.*;
import java.net.Socket;

public class DictClient {
    private static final String SERVER = "dict.org";
    private static final int PORT = 2628;
    private static final int TIMEOUT = 150000;
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket(SERVER,PORT);
            socket.setSoTimeout(TIMEOUT);
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out,"UTF-8");
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            for (String word:args){
                define(word,writer,reader);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (socket != null){
                try {
                    socket.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private static void define(String word, Writer writer, BufferedReader reader)
            throws IOException {
        writer.write("Define eng-lat " + word + "\r\n");
        writer.flush();
        for (String line = reader.readLine();line != null;line = reader.readLine()){
            if (line.startsWith("250 ")){
                return;
            }
            else if(line.startsWith("552 ")){
                System.out.println("No definition found for " + word);
            }
            else if(line.matches("\\d\\d\\d .*")){
                continue;
            }
            else if (line.trim().equals(".")){
                continue;
            }
            else {
                System.out.println(line);
            }
        }
    }
}
