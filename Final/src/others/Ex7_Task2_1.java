package others;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;

public class Ex7_Task2_1 extends JFrame {
    private JTextArea ta_info;
    private ServerSocket server;
    private JPanel jPanel;

    public JPanel getjPanel() {
        return jPanel;
    }

    public void getsever() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new ServerSocket(1978);                //实例化Socket对象
                    ta_info.append("服务器套接字已经创建成功\n");           //输出信息
                    while (true) {                                       //如果套接字是连接状态
                        ta_info.append("等待客户机的连接......\n");        //输出信息
                        server.accept();                                 //等待客户机连接
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        Ex7_Task2_1 frame = new Ex7_Task2_1();
        frame.setVisible(true);
        frame.getsever();
    }

    public Ex7_Task2_1() {
        super();
        setTitle("设置等待连接的超时时间");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 336, 257);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        jPanel = new JPanel(new BorderLayout());
        jPanel.add(scrollPane,BorderLayout.CENTER);
        getsever();
    }
}
