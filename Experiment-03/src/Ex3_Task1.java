import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Ex3_Task1 extends JFrame {
    JPanel jPanel = new JPanel();
    JLabel label = new JLabel("");
    JButton startButton = new JButton();
    JButton stopButton = new JButton();
    MyThread thread = null;

    public Ex3_Task1() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        setSize(400, 280);
        startButton.setText("Start");
        startButton.setFont(new Font("黑体", Font.PLAIN, 25));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (thread != null) {
                    thread.stop();
                }
                thread = new MyThread();
                thread.start();
            }
        });
        stopButton.setText("Stop");
        stopButton.setFont(new Font("黑体", Font.PLAIN, 25));
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (thread != null) {
                    thread.stop();
                }
                thread = null;
            }
        });
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(jPanel, BorderLayout.CENTER);
        jPanel.add(startButton);
        stopButton.setBounds(36, 105, 82, 30);
        jPanel.add(stopButton);
        stopButton.setBounds(160, 108, 100, 31);
        jPanel.add(label);
        label.setBounds(40, 120, 200, 80);
        label.setFont(new Font("黑体", Font.PLAIN, 25));
    }


    private class MyThread extends Thread {
        public MyThread() {
        }

        public void run() {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {

                }
                String s = "";
                Date now = new Date();
                int hour = now.getHours();
                int minute = now.getMinutes();
                int second = now.getSeconds();
                s = hour + ":" + minute + ":" + second;
                label.setText(s);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ex3_Task1 window = new Ex3_Task1();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}