package others;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class Ex6_Task2 extends JFrame {                                             //创建类继承JFrame
    private PrintWriter writer;                                                 //声明PrintWriter类对象
    Socket socket;                                                              //声明Socket对象
    private JTextArea ta = new JTextArea();                                     //创建JTextArea对象
    private JTextField tf = new JTextField();                                   //创建JTextField对象
    Container cc;                                                               //声明Container对象



    private JPanel jPanel;
    public JPanel getjPanel() {
        return jPanel;
    }
    public Ex6_Task2(String title) {                                                //构造方法
        super(title);                                                           //调用父类的构造方法
        jPanel = new JPanel(new BorderLayout());
        cc = this.getContentPane();                                             //实例化对象
        cc.add(ta, "North");                                          //将文本域放在窗体的上部
        cc.add(tf, "South");                                          //将文本框放在窗体的下部
        jPanel.add(ta,BorderLayout.NORTH);
        jPanel.add(tf,BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tf.addActionListener(new ActionListener() {                             //绑定事件
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println(tf.getText());                                   //将文本框中信息写入流
                ta.append(tf.getText() + '\n');                                 //将文本框中信息显示在文本域中
                tf.setText("");                                                 //将文本框清空
            }
        });
        connect();
    }

    private void connect() {                                                    //连接套接字方法
        ta.append("尝试连接\n");                                                 //文本域中提示信息
        try {                                                                   //捕捉异常
            socket = new Socket("127.0.0.1", 8998);                   //实例化Socket对象
            writer = new PrintWriter(socket.getOutputStream(), true);  //获得输出流对象
            ta.append("完成连接\n");                                             //文本域中提示信息
            ta.append("你好，我是客户端，现在向您发送消息\n");
        } catch (Exception e) {
            e.printStackTrace();                                                //输出异常信息
        }
    }

    public static void main(String[] args) {                                    //主方法
        Ex6_Task2 clien = new Ex6_Task2("向服务器发送数据");                         //创建本例对象
        clien.setBounds(300, 260, 340, 220);                  //设置窗体位置和大小
        clien.setVisible(true);                                                 //将窗体显示
        clien.connect();                                                        //调用连接方法
    }
}
