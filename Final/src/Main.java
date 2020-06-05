
import others.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main extends JFrame {


    private JFrame frame;// 窗体
    private JMenuBar mBar;// 菜单
    private JPanel panel;// 面板

    // 构造函数
    public Main() {
        panel = new JPanel();
        mBar = new JMenuBar();

        this.setTitle("Java进阶");
        this.setJMenuBar(mBar);
        this.setResizable(false);

        JMenu menu1, menu3, menu4, menu5, menu6,menu7,menu8,menu10,menu11;
        menu1 = new JMenu("实验一");
        menu3 = new JMenu("实验三");
        menu4 = new JMenu("实验四");
        menu5 = new JMenu("实验五");
        menu6 = new JMenu("实验六");
        menu7 = new JMenu("实验七");
        menu8 = new JMenu("实验八");
        menu10 = new JMenu("实验十");
        menu11 = new JMenu("实验十一");
        mBar.add(menu1);
        mBar.add(menu3);
        mBar.add(menu4);
        mBar.add(menu5);
        mBar.add(menu6);
        mBar.add(menu7);
        mBar.add(menu8);
        mBar.add(menu10);
        mBar.add(menu11);

        JMenuItem Item_1_1, Item_1_2, Item_1_3,Item_1_4;
        Item_1_1 = new JMenuItem("任务一");
        Item_1_2 = new JMenuItem("任务二");
        Item_1_3 = new JMenuItem("任务三");
        Item_1_4 = new JMenuItem("任务四");
        menu1.add(Item_1_1);
        menu1.addSeparator();
        menu1.add(Item_1_2);
        menu1.addSeparator();
        menu1.add(Item_1_3);
        menu1.addSeparator();
        menu1.add(Item_1_4);

        JMenuItem Item_3_1, Item_3_2, Item_3_3;
        Item_3_1 = new JMenuItem("任务一");
        Item_3_2 = new JMenuItem("任务二");
        Item_3_3 = new JMenuItem("任务三");
        menu3.add(Item_3_1);
        menu3.addSeparator();
        menu3.add(Item_3_2);
        menu3.addSeparator();
        menu3.add(Item_3_3);

        JMenuItem Item_4_1, Item_4_2;
        Item_4_1 = new JMenuItem("任务一");
        Item_4_2 = new JMenuItem("任务二");
        menu4.add(Item_4_1);
        menu4.addSeparator();
        menu4.add(Item_4_2);

        JMenuItem Item_5_1, Item_5_2, Item_5_3;
        Item_5_1 = new JMenuItem("任务一");
        Item_5_2 = new JMenuItem("任务二");
        Item_5_3 = new JMenuItem("任务三");
        menu5.add(Item_5_1);
        menu5.addSeparator();
        menu5.add(Item_5_2);
        menu5.addSeparator();
        menu5.add(Item_5_3);

        JMenuItem Item_6_1, Item_6_2, Item_6_3,Item_6_4;
        Item_6_1 = new JMenuItem("任务一");
        Item_6_2 = new JMenuItem("任务二");
        Item_6_3 = new JMenuItem("任务三");
        Item_6_4 = new JMenuItem("任务三(1)");
        menu6.add(Item_6_1);
        menu6.addSeparator();
        menu6.add(Item_6_2);
        menu6.addSeparator();
        menu6.add(Item_6_3);
        menu6.addSeparator();
        menu6.add(Item_6_4);

        JMenuItem Item_7_1, Item_7_2, Item_7_3,Item_7_4,Item_7_5,Item_7_6;
        Item_7_1 = new JMenuItem("任务一");
        Item_7_2 = new JMenuItem("任务一(1)");
        Item_7_3 = new JMenuItem("任务二");
        Item_7_4 = new JMenuItem("任务二(1)");
        Item_7_5 = new JMenuItem("任务三");
        Item_7_6 = new JMenuItem("任务三(1)");
        menu7.add(Item_7_1);
        menu7.addSeparator();
        menu7.add(Item_7_2);
        menu7.addSeparator();
        menu7.add(Item_7_3);
        menu7.addSeparator();
        menu7.add(Item_7_4);
        menu7.addSeparator();
        menu7.add(Item_7_5);
        menu7.addSeparator();
        menu7.add(Item_7_6);

        JMenuItem Item_8_1;
        Item_8_1 = new JMenuItem("任务一");
        menu8.add(Item_8_1);

        JMenuItem Item_10_1, Item_10_2, Item_10_3;
        Item_10_1 = new JMenuItem("任务一");
        Item_10_2 = new JMenuItem("任务二");
        Item_10_3 = new JMenuItem("任务三");
        menu10.add(Item_10_1);
        menu10.addSeparator();
        menu10.add(Item_10_2);
        menu10.addSeparator();
        menu10.add(Item_10_3);

        JMenuItem Item_11_1, Item_11_2, Item_11_3, Item_11_4;
        Item_11_1 = new JMenuItem("任务一");
        Item_11_2 = new JMenuItem("任务二");
        Item_11_3 = new JMenuItem("任务三");
        Item_11_4 = new JMenuItem("任务四");
        menu11.add(Item_11_1);
        menu11.addSeparator();
        menu11.add(Item_11_2);
        menu11.addSeparator();
        menu11.add(Item_11_3);
        menu11.addSeparator();
        menu11.add(Item_11_4);


        Item_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               change(new Ex1_Task1().getContentPane());
            }
        });

        Item_1_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex1_Task2().getContentPane());
            }
        });

        Item_1_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex1_Task3().getContentPane());
            }
        });

        Item_1_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex1_Task4().getContentPane());
            }
        });

        Item_3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex3_Task1().getjPanel());
            }
        });

        Item_3_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex3_Task2().getComp());
            }
        });

        Item_3_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex3_Task3().getjPanel());
            }
        });

        Item_4_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex4_Task1().getjPanel());
            }
        });

        Item_4_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex4_Task2().getContentPane());
            }
        });

        Item_5_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex5_Task1().getjPanel());
            }
        });

        Item_5_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex5_Task2().getjPanel());
            }
        });

        Item_5_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex5_Task3().getPanel());
            }
        });

        Item_6_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex6_Task1().getjPanel());
            }
        });

        Item_6_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex6_Task2("").getjPanel());
            }
        });

        Item_6_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex6_Task3().getjPanel());
            }
        });

        Item_6_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex6_test3_2().getjPanel());
            }
        });

        Item_7_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Ex7_Task1_1().start();
            }
        });

        Item_7_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex7_Task1_2().getjPanel());
            }
        });

        Item_7_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex7_Task2_1().getjPanel());
            }
        });

        Item_7_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex7_Task2_2().getjPanel());
            }
        });

        Item_7_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex7_Task3_1().getjPanel());
            }
        });

        Item_7_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex7_Task3_2().getjPanel());
            }
        });

        Item_8_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Ex8_test1();
            }
        });

        Item_10_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex10_test1().getjPanel());
            }
        });

        Item_10_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex10_test2().getjPanel());
            }
        });

        Item_10_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex10_test3().getjPanel());
            }
        });

        Item_11_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex11_test1().getjPanel());
            }
        });

        Item_11_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex11_test2().getjPanel());
            }
        });

        Item_11_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex11_test3().getjPanel());
            }
        });

        Item_11_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                change(new Ex11_test4().getjPanel());
            }
        });

        this.add(panel);
        panel.setBackground(Color.green);
        this.setBounds(180, 10, 1024, 680);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void change(JPanel panel){
        getContentPane().removeAll();
        add(panel);
        repaint();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}


