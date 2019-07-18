package cn.zhangqianli.my;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("请输入第一个数字：");
        while (scanner.hasNext()){
            input = scanner.next();
            System.out.println("输入是：" +input);
            System.out.print("请输入下一个数字：");
        }
    }

}
