package others;

import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;

public class Ex4_Task1 extends JFrame implements Runnable {
    int num = 10;
    private JPanel jPanel;
    private JTextArea textArea;

    public JPanel getjPanel() {
        return jPanel;
    }

    public Ex4_Task1()  {
        jPanel = new JPanel();
        textArea = new JTextArea();
        jPanel.add(textArea);
        Thread tA = new Thread(this);
        Thread tB = new Thread(this);
        Thread tC = new Thread(this);
        Thread tD = new Thread(this);
        tA.start();
        tB.start();
        tC.start();
        tD.start();

    }

    public void run() {
        while (true) {
            synchronized (this) {
                if (num > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    textArea.append("车票还有 " + num-- + " 张\n");
//                    System.out.println("车票还有 " + num-- + " 张");
                }else {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Ex4_Task1 t = new Ex4_Task1();
        Thread tA = new Thread(t);
        Thread tB = new Thread(t);
        Thread tC = new Thread(t);
        Thread tD = new Thread(t);
        tA.start();
        tB.start();
        tC.start();
        tD.start();
    }
}
