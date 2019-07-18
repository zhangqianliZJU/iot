package cn.zhangqianli;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

class Client{
    public static void main(String[] args) {
//        String hostName = "time.nist.gov";
        String hostName = "www.tbm.cloud";
        Socket socket = null;
        try{
            socket = new Socket(hostName,8080);
            System.out.println("Remote IP address is: "+ socket.getInetAddress());
//            socket.setSoTimeout(15000);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
//            Random rand = new Random(123L);
//            int randomNumber = rand.nextInt(5000);
//                writer.write("The random number generate by the client is: " + randomNumber);
//                writer.flush();
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入第一个变量：");
//            while (scanner.hasNext()){
            String input = scanner.next();
            System.out.println("输入变量是：" + input);
            writer.write("User input by the client side is: " + input);
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in,"UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);
            String line = null;
                while ((line = bfReader.readLine()) != null) {
                    System.out.println("The date generated by server is: " + line);
                }
//                System.out.print("请输入一个新的变量：");
//            }
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