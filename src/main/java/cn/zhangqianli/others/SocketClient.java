package cn.zhangqianli.others;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String args[]) throws Exception {
        // 要连接的服务端IP地址和端口
        String host = "www.tbm.cloud";
        int port = 8000;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        Scanner scanner = new Scanner(System.in);
        String input = null;
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        System.out.print("请输入第一个数：");
        while (scanner.hasNext()){
            input = scanner.next();
            String message = "来自客户端的输入：" + input;
            socket.getOutputStream().write(message.getBytes("UTF-8"));
            //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
//            socket.shutdownOutput();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len,"UTF-8"));
            }
            System.out.println("来自客户端的输出: " + sb);
            System.out.print("请输入下一个数：");
        }
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}