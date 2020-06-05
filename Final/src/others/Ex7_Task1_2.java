package others;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Ex7_Task1_2 extends JFrame implements Runnable, ActionListener {
    int port;                                                               //定义int型变量
    InetAddress group = null;                                               //声明InetAdress对象
    MulticastSocket socket = null;                                          //创建多点广播套接字
    JButton ince = new JButton("开始接受");                             //创建按钮对象
    JButton stop = new JButton("停止接受");
    JTextArea inceAr = new JTextArea(10, 10);                  //显示接收广播的文本域
    JTextArea inced = new JTextArea(10, 10);
    Thread thread;                                                          //创建Thread对象
    boolean b = false;                                                      //创建boolean型变量
    private JPanel jPanel;

    public JPanel getjPanel() {
        return jPanel;
    }

    public Ex7_Task1_2() {                                                      //构造方法
        super("广播数据报");                                             //调用父类方法
        jPanel = new JPanel(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        thread = new Thread(this);                                    //创建线程对象
        ince.addActionListener(this);                                     //绑定按钮ince的单击事件
        stop.addActionListener(this);                                     //绑定按钮stop的单击事件
        inceAr.setForeground(Color.blue);                                   //指定文本域中文字颜色
        JPanel north = new JPanel();                                        //创建JPanel对象
        north.add(ince);                                                    //将按钮添加到面板north上
        north.add(stop);
        add(north, BorderLayout.NORTH);                                     //将north放置在窗体的上部
        jPanel.add(north,BorderLayout.NORTH);
        JPanel center = new JPanel();                                       //创建面板对象center
        center.setLayout(new GridLayout(1, 2));                   //设置面板布局
        center.add(inceAr);                                                 //将文本域添加到面板上
        center.add(inced);
        add(center, BorderLayout.CENTER);                                   //设置面板布局
        jPanel.add(center,BorderLayout.CENTER);
        validate();                                                         //刷新
        port = 9898;                                                        //设置端口号
        try {
            group = InetAddress.getByName("225.225.225.1");                  //指定接受地址
            socket = new MulticastSocket(port);                             //绑定多点广播套接字
            socket.joinGroup(group);                                        //加入广播组
        } catch (Exception e) {
            e.printStackTrace();                                            //输出异常信息
        }
        setBounds(100, 50, 360, 380);                      //设置布局
//        setVisible(true);                                                   //将窗体设置为显示状态
    }

    @Override
    public void run() {                                                     //run()方法
        while (true) {
            byte data[] = new byte[1024];                                   //创建byte数组
            DatagramPacket packet = null;                                   //创建DatagramPacket对象
            packet = new DatagramPacket(data, data.length, group, port);    //待接收的数据包
            try {
                socket.receive(packet);                                     //接受数据包
                String message = new String(packet.getData(), 0, packet
                        .getLength());                                      //获取数据包中内容
                inceAr.setText("正在接收的内容：\n" + message);               //将接收内容显示在文本域中
                inced.append(message + "\n");                               //每条信息为一行
            } catch (Exception e) {
                e.printStackTrace();                                        //输出异常信息
            }
            if (b == true) {                                                //当变量等于true时，退出循环
                break;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {                            //单击事件
        if (e.getSource() == ince) {                                        //单击按钮ince触发的事件
            ince.setBackground(Color.red);                                  //设置按钮颜色
            stop.setBackground(Color.yellow);
            if (!(thread.isAlive())) {                                      //如线程不处于新建状态
                thread = new Thread(this);                            //实例化Thread对象
            }
            thread.start();                                                 //启动线程
            b = false;                                                      //设置变量值
        }
        if (e.getSource() == stop) {                                        //单击按钮stop触发的事件
            ince.setBackground(Color.yellow);                               //设置按钮颜色
            stop.setBackground(Color.red);
            b = true;                                                       //设置变量值
        }
    }

    public static void main(String[] args) {                                //主方法
        Ex7_Task1_2 rec = new Ex7_Task1_2();                                        //创建本类对象
        rec.setSize(460, 200);                                  //设置窗体大小
    }
}
