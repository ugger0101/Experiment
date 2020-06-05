import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ex5_Task3 {                                      //创建类
    public static void main(String[] args) {
        InetAddress ip;                                     //声明InetAddress对象
        try {                                               //try语句块捕捉可能出现的异常
            ip = InetAddress.getLocalHost();                //实例化InetAddress对象
            String localname = ip.getHostName();            //获取本地主机名
            String localip = ip.getHostAddress();           //获取本地主机的IP地址
            System.out.println("本机名" + localname);        //输出本地主机
            System.out.println("本地IP地址" + localip);      //输出本地主机
        } catch (UnknownHostException e) {
            e.printStackTrace();                            //输出异常信息
        }
    }
}
