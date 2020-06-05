package others;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.Socket;

public class Ex7_Task2_2 extends JFrame {                               //创建类继承JFrame类
    private Socket socket;                                          //声明Socket对象
    private JTextArea ta = new JTextArea();                         //创建JTextArea对象
    private JPanel jPanel;

    public JPanel getjPanel() {
        return jPanel;
    }

    public Ex7_Task2_2() {                                              //构造方法
        super();                                                    //调用父类的构造方法
        setTitle("获取Socket信息");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 351, 257);
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(ta);
        jPanel = new JPanel(new BorderLayout());
        jPanel.add(scrollPane,BorderLayout.CENTER);
        connect();
    }

    private void connect() {                                        //连接套接字方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                ta.append("尝试连接......\n");                               //文本域中输出信息
                try {                                                       //捕捉异常
                    socket = new Socket("127.0.0.1", 1978);       //实例化Socket对象
                    ta.append("完成连接。\n");                               //文本域中提示信息
                    InetAddress netAddress = socket.getInetAddress();       //获得远程服务器的地址
                    String netIp = netAddress.getHostAddress();             //获得远程服务器的IP地址
                    int netPort = socket.getPort();                         //获得远程服务器的端口号
                    InetAddress localAddress = socket.getLocalAddress();    //获得客户端的地址
                    String localIp = localAddress.getHostAddress();         //获得客户端的IP地址
                    int localPort = socket.getLocalPort();                  //获得客户端的端口号
                    ta.append("远程服务器的IP地址：" + netIp + "\n");
                    ta.append("远程服务器的端口号：" + netPort + "\n");
                    ta.append("客户机本地的IP地址：" + localIp + "\n");
                    ta.append("客户机本地的端口号：" + localPort + "\n");
                } catch (Exception e) {
                    e.printStackTrace();                                    //输出异常信息
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        Ex7_Task2_2 clien = new Ex7_Task2_2();
        clien.setVisible(true);
        clien.connect();
    }
}
