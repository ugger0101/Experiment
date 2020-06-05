package others;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.table.TableRowSorter;

//维护表格
public class Ex11_test3 extends JFrame {

    private DefaultTableModel tableModel;   //表格模型对象
    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;

    public JPanel getjPanel() {
        return jPanel;
    }

    private JPanel jPanel;

    public Ex11_test3() {
        super();
        jPanel = new JPanel(new BorderLayout());
        setTitle("提供行标题栏表格");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"书名"};   //列名
        String[][] tableVales = {{"《Java从入门到精通(第2版)》"},{"《Java编程词典》"},{"《PHP从入门到精通(第2版)》"},{"《Visual Basic从入门到精通(第2版)》"}}; //数据
        tableModel = new DefaultTableModel(tableVales, columnNames);
        table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter(tableModel));
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
        jPanel.add(scrollPane,BorderLayout.CENTER);

//        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
//        table.setRowSorter(sorter);


        scrollPane.setViewportView(table);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex11_test3 jTableDefaultTableModelTest = new Ex11_test3();
        jTableDefaultTableModelTest.setVisible(true);
    }

}
