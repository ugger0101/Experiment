package others;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.BorderLayout;

//维护表格
public class Ex11_test4 extends JFrame {

    private DefaultTableModel tableModel;   //表格模型对象
    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;
    private JPanel jPanel;

    public JPanel getjPanel() {
        return jPanel;
    }

    public Ex11_test4() {
        super();
        jPanel = new JPanel(new BorderLayout());
        setTitle("提供行标题栏表格");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"日期", "商品1", "商品2", "商品3", "商品4", "商品5"};   //列名
        String[][] tableVales = {{"1", "698", "33", "47", "196", "51"}}; //数据
        tableModel = new DefaultTableModel(tableVales, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        jPanel.add(scrollPane,BorderLayout.CENTER);
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
        //table.setRowSorter(new TableRowSorter(tableModel));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                aTextField.setText(oa.toString());  //给文本框赋值
                bTextField.setText(ob.toString());
            }
        });
        scrollPane.setViewportView(table);
        setContentPane(jPanel);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex11_test4 jTableDefaultTableModelTest = new Ex11_test4();
        jTableDefaultTableModelTest.setVisible(true);
    }

}
