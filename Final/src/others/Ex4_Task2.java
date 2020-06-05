package others;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ex4_Task2 extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTextField textField1;
    private JTextField textField2;
    private  DefaultTableModel model;

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ex4_Task2 frame = new Ex4_Task2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Ex4_Task2() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("查看和修改线程优先级");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        textField1 = new JTextField();
        textField1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(textField1);
        textField1.setColumns(10);

        JButton button1 = new JButton("新建线程");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new_button_actionPerformed(e);
            }
        });
        button1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(button1);

        textField2 = new JTextField();
        textField2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(textField2);
        textField2.setColumns(10);

        JButton button = new JButton("修改名称");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(button);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        table.setRowHeight(30);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        scrollPane.setViewportView(table);
    }

    private void new_button_actionPerformed(ActionEvent e) {
        String text = textField1.getText();
        Thread qa = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        qa.setName(text);
        qa.start();
        model.addRow(new Object[]{qa.getId(), qa.getName(),
                qa.getPriority()});
        repaint();
    }

    protected void do_this_windowActivated(WindowEvent e) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();                // 获得当前线程所在线程组
        Thread[] threads = new Thread[group.activeCount()];                         // 使用数组保存活动状态的线程
        group.enumerate(threads);                                                   // 获得所有线程
        model = (DefaultTableModel) table.getModel();// 获得表格模型
        model.setRowCount(0);                                                       // 清空表格模型中的数据
        // 定义表头
        model.setColumnIdentifiers(new Object[]{"线程ID", "线程名称", "优先级"});
        for (Thread thread : threads) {                                             // 增加行数据
            model.addRow(new Object[]{thread.getId(), thread.getName(),
                    thread.getPriority()});
        }
        table.setModel(model);                                                      // 更新表格模型
    }

    protected void do_button_actionPerformed(ActionEvent e) {
        String text = textField2.getText();                                          //获得用户输入的优先级
        Integer priority = Integer.parseInt(text);                                  //将优先级转换成Integer的对象
        int selectedRow = table.getSelectedRow();                                   //获得用户选择的行
        DefaultTableModel model = (DefaultTableModel) table.getModel();             //获得默认表格模型
        model.setValueAt(priority, selectedRow, 1);                         //重新绘制各个控件
        repaint();
    }
}
