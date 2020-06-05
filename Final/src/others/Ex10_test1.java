package others;

import javax.swing.*;
import java.awt.*;

public class Ex10_test1 extends JFrame {
    private JPanel jPanel;

    public JPanel getjPanel() {
        return jPanel;
    }

    public static void main(String[] args) {
        Ex10_test1 frame = new Ex10_test1();
        frame.setVisible(true);
    }
    public Ex10_test1(){
        super();
        setTitle("创建可以滚动的表格");
        setBounds(100,100,240,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames={"学号","姓名","年龄"};
        String[][] tableValues={{"2015001","李明","19"},{"2015002","刘力","20"},{"2015003","张离","20"},{"2015004","方芳","19"},{"2015005","今东","21"},{"2015001","李明","19"}};
        JTable table = new JTable(tableValues,columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        jPanel = new JPanel(new BorderLayout());
        jPanel.add(scrollPane,BorderLayout.CENTER);
    }
}
