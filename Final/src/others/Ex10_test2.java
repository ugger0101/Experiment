package others;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Vector;

public class Ex10_test2 extends JFrame {
    private JPanel jPanel;

    public JPanel getjPanel() {
        return jPanel;
    }

    public static void main(String[] args) {
        Ex10_test2 frame = new Ex10_test2();
        frame.setVisible(true);
    }
    public Ex10_test2(){
        super();
        jPanel = new JPanel(new BorderLayout());
        setTitle("定义表格");
        setBounds(100,100,500,375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        jPanel.add(scrollPane,BorderLayout.CENTER);
        String[] columnNames = {"学号","姓名","性别","年龄","身高","体重","血型"};
        Vector columnNameV = new Vector();
        for (int column=0;column<columnNames.length;column++){
            columnNameV.add(columnNames[column]);
        }
        Vector tableValueV = new Vector();
        for(int row=1;row<21;row++){
            Vector rowV = new Vector();
            for(int column=0;column<columnNames.length;column++){
                rowV.add(columnNames[column]+row);
            }
            tableValueV.add(rowV);
        }
        JTable table = new MTable(tableValueV,columnNameV);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(Color.CYAN);
        table.setSelectionForeground(Color.MAGENTA);
        table.setRowHeight(30);
        scrollPane.setViewportView(table);
    }
    private class MTable extends JTable{
        public MTable(Vector rowData,Vector columnNames){
            super(rowData,columnNames);
        }
        public JTableHeader getTableHeader(){
            JTableHeader tableHeader = super.getTableHeader();
            tableHeader.setReorderingAllowed(false);
            DefaultTableCellRenderer hr = (DefaultTableCellRenderer)tableHeader.getDefaultRenderer();
            hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            return tableHeader;
        }
        public TableCellRenderer getDefaultRenderer(Class<?>columnClass){
            DefaultTableCellRenderer cr = (DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);
            cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            return cr;
        }
        public boolean isCellEditable(int row,int column){
            return false;
        }
    }
}
