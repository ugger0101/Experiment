package others;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Ex10_test3 extends JFrame {
    private JPanel jPanel;
    private JTable table;

    public JPanel getjPanel() {
        return jPanel;
    }

    public static void main(String[] args) {
        Ex10_test3 frame = new Ex10_test3();
        frame.setVisible(true);
    }
    public Ex10_test3() {
        super();
        jPanel = new JPanel(new BorderLayout());
        setTitle("操纵表格");
        setBounds(100, 100, 500, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        jPanel.add(scrollPane,BorderLayout.CENTER);

        String[] columnNames = {"学号","姓名","性别","年龄","身高","体重","血型"};
        Vector columnNameV = new Vector();
        for (int column = 0; column < columnNames.length; column++) {
            columnNameV.add(columnNames[column]);
        }
        Vector tableValueV = new Vector();
        for (int row = 1; row < 21; row++) {
            Vector rowV = new Vector();
            for (int column = 0; column < columnNames.length; column++) {
                rowV.add(columnNames[column] + row);
            }
            tableValueV.add(rowV);
        }
        table = new JTable(tableValueV,columnNameV);
        table.setRowSelectionInterval(1,3);
        table.addRowSelectionInterval(5,5);
        scrollPane.setViewportView(table);
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        jPanel.add(buttonPanel,BorderLayout.SOUTH);
        JButton selectAllButton = new JButton("全部选择");
        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.selectAll();
            }
        });
        buttonPanel.add(selectAllButton);
        JButton clearSelectionButton = new JButton("取消选择");
        clearSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.clearSelection();
            }
        });
        buttonPanel.add(clearSelectionButton);
        System.out.println("表格共有"+table.getRowCount()+"行"+table.getColumnCount()+"列");
        System.out.println("共有"+table.getSelectedRowCount()+"行被选中");
        System.out.println("第3行的选择状态为："+table.isRowSelected(2));
        System.out.println("第5行的选择状态为："+table.isRowSelected(4));
        System.out.println("被选中的第一行的索引是："+table.getSelectedRow());
        int[] selectedRows = table.getSelectedRows();
        System.out.println("所有被选中行的索引是：");
        for(int row = 0;row<selectedRows.length;row++){
            System.out.println(selectedRows[row]+"  ");
        }
        System.out.println();
        System.out.println("列移动前第2列的名称是："+table.getColumnName(1));
        System.out.println("列移动前第2行第2列的值是："+table.getValueAt(1,1));
        table.moveColumn(1,5);
        System.out.println("列移动后第2列的名称是："+table.getColumnName(1));
        System.out.println("列移动的第2行第2列的值是："+table.getValueAt(1,1));
    }
}
