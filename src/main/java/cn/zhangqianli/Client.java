package cn.zhangqianli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class Client{
    public static void main(String[] args) {
        String hostName = "time.nist.gov";
        Socket socket = null;
        try{
            socket = new Socket(hostName,13);
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in,"ASCII");
            BufferedReader bfReader = new BufferedReader(reader);
            String line;
//            while ((line = bfReader.readLine()) != null){
//                time.append(line);
//            }
            for(int c = reader.read();c!=-1;c = reader.read()){// 逐个字符的读取
                time.append((char)c);
            }
            System.out.println(time);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}