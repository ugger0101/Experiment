package others;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Ex7_Task1_1 extends Thread {                                           //创建类，该类为多线程执行程序
    String weather = "节目预报：八点有大型晚会，请收听";                            //需要发出的广播信息
    int port = 9898;                                                            //定义端口
    InetAddress iaddress = null;                                                //创建InetAddress对象
    MulticastSocket socket = null;                                              //声明多点广播套接字

    public Ex7_Task1_1() {                                                                 //构造方法
        try {
            iaddress = InetAddress.getByName("225.225.225.1");                   //实例化InetAddress，指定地址
            socket = new MulticastSocket();                                     //实例化多点广播套接字
            socket.setTimeToLive(1);                                            //指定发送范围是本地网络
            socket.joinGroup(iaddress);                                         //加入广播组
        } catch (Exception e) {
            e.printStackTrace();                                                //输出异常信息
        }
    }

    public void run() {                                                         //run()方法
        while (true) {
            DatagramPacket packet = null;                                       //声明DatagramPacket对象
            byte data[] = weather.getBytes();                                   //声明字节数组
            packet = new DatagramPacket(data, data.length, iaddress, port);     //将数据打包
            System.out.println(new String(data));                               //将广播信息播出
            try {
                socket.send(packet);                                            //发送线程
                sleep(3000);                                               //线程休眠
            } catch (Exception e) {
                e.printStackTrace();                                            //输出异常信息
            }
        }
    }

    public static void main(String[] args) {                                    //主方法
        Ex7_Task1_1 w = new Ex7_Task1_1();                                              //创建本类对象
        w.start();                                                              //启动线程
    }
}
