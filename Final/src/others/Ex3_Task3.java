package others;

import javax.swing.*;
import java.awt.*;

public class Ex3_Task3 extends JFrame {
    private Thread threadA;
    private Thread threadB;
    final JProgressBar progressBar = new JProgressBar();
    final JProgressBar progressBar2 = new JProgressBar();
    int count = 0;
    private JPanel jPanel;

    public JPanel getjPanel() {
        return jPanel;
    }

    public Ex3_Task3() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 100);
//        setVisible(true);
        jPanel = new JPanel();
        jPanel.add(progressBar,BorderLayout.NORTH);
        jPanel.add(progressBar2,BorderLayout.SOUTH);
        setContentPane(jPanel);
//        getContentPane().add(progressBar2, BorderLayout.NORTH);
//        getContentPane().add(progressBar2, BorderLayout.SOUTH);
        progressBar.setStringPainted(true);     //设置进度条显示数字
        progressBar2.setStringPainted(true);

        threadA = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            progressBar.setValue(++count);
                            try {
                                threadA.sleep(100);
                                threadB.join();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        threadA.start();

        threadB = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (true) {
                    progressBar2.setValue(++count);
                    try {
                        threadB.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (count == 100) {
                        threadB.stop();
                    }
                }
            }
        });
        threadB.start();
    }

    public static void main(String[] args) {
        new Ex3_Task3().show();
    }
}
