package others;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex1_Task1 extends JFrame{
    //定义属性
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;

    public Ex1_Task1() throws HeadlessException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel,BorderLayout.NORTH);

        JLabel label = new JLabel("输入文件扩展名:");
        label.setFont(new Font("微软雅黑",Font.PLAIN,15));
        panel.add(label);

        textField = new JTextField();
        textField.setFont(new Font("微软雅黑",Font.PLAIN,15));
        panel.add(textField);
        textField.setColumns(12);

        JButton button = new JButton("选择文件夹");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);           //调用自定义方法
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(button);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane,BorderLayout.CENTER);

        table  = new JTable();
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        header.setPreferredSize(new Dimension(header.getWidth(),15));
        table.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        table.setRowHeight(25);
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(new String[]{"文件名","文件大小","修改时间"});
        scrollPane.setViewportView(table);
    }

    //自定义方法--单击按钮的方法
    protected void do_button_actionPerformed(ActionEvent e){
        final String fileType = textField.getText();
        if(fileType.isEmpty()){
            JOptionPane.showMessageDialog(contentPane,"请输入文件类型！","",JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(contentPane);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File[] listFiles = chooser.getSelectedFile().listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if(pathname.getName().endsWith(fileType))
                    {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            });
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            for (File file:listFiles)
            {
                String name = file.getName();
                long size = file.length();
                String modifyDate = format.format(new Date(file.lastModified()));
                model.addRow(new String[]{name,""+size,modifyDate});
            }
            table.setModel(model);
        }
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public static void main(String[] args) {            //实现多线程运行
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Ex1_Task1 frame = new Ex1_Task1();
                    frame.setVisible(true);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

}
