package others;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Ex1_Task3 extends JFrame{
    private JPanel contentPane;
    private JTextField Selectfile;
    private JTable table;
    private JTextField Selectfolder;
    private File[] selectFiles;
    private File directory;

    public Ex1_Task3() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,300);
        contentPane = new JPanel();
//        contentPane.setLayout(new BorderLayout(0,0));
//        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.setLayout(new GridLayout(2,1));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel,BorderLayout.NORTH);

        JLabel label = new JLabel("选择源文件:");
        label.setFont(new Font("微软雅黑",Font.PLAIN,15));
        panel.add(label);

        Selectfile = new JTextField();
        Selectfile.setFont(new Font("微软雅黑",Font.PLAIN,15));
        panel.add(Selectfile);
        Selectfile.setColumns(12);

        JButton button = new JButton("选择文件");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_selectfile_actionPerformed(e);           //调用自定义方法
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(button);

        JLabel label2 = new JLabel("选择目标文件夹:");
        label2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(label2);

        Selectfolder = new JTextField();
        Selectfolder.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(Selectfolder);
        Selectfolder.setColumns(11);

        JButton button2 = new JButton("选择文件夹");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_selectfolder_actionPerformed(e);
            }
        });
        button2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(button2);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane,BorderLayout.CENTER);

        table = new JTable();
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        header.setPreferredSize(new Dimension(header.getWidth(),15));
        table.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        table.setRowHeight(25);
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(new String[]{"移动文件名称","目标文件夹"});
        scrollPane.setViewportView(table);

        JButton button3 = new JButton("移动");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_moveButton_actionPerformed(e);
            }
        });
        button3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(button3);

        JButton button4 = new JButton("关闭");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                (e);
                System.exit(0);
            }
        });
        button4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(button4);
    }

    protected void do_selectfile_actionPerformed(ActionEvent e)
    {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        if(model.getRowCount()!=0)                                  //获取行的数量，如果不为0
        {
            model.setRowCount(0);                                   //将行的数量设置为0
        }
        table.setModel(model);                                      //设置表格为默认格式
        JFileChooser chooser = new JFileChooser();                  //文件选择器
        chooser.setMultiSelectionEnabled(true);                     //设置文件可以多选！！！
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);      //设置只能选择文件

        int result = chooser.showOpenDialog(contentPane);           //在当前应用中央弹窗
        if (result == JFileChooser.APPROVE_OPTION) {                //获得选中的文件对象
            selectFiles = chooser.getSelectedFiles();
            if (selectFiles.length!=0) {
                StringBuilder fileNames = new StringBuilder();
                for (File selectFile : selectFiles) {
                    fileNames.append(selectFile.getName()+",");
                }
                fileNames.deleteCharAt(fileNames.length()-1);
                Selectfile.setText(fileNames.toString());
            }
        }
    }

    protected void do_selectfolder_actionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(contentPane);
        if (result == JFileChooser.APPROVE_OPTION) {
            directory = chooser.getSelectedFile();
            Selectfolder.setText(directory.getAbsolutePath());
        }
    }

    protected void do_moveButton_actionPerformed(ActionEvent e)
    {
        //省略选择文件和文件夹的校验代码
        if ((selectFiles == null) || (selectFiles.length == 0)) {
            JOptionPane.showMessageDialog(contentPane, "必须选择要移动的文件!!!", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (directory == null) {
            JOptionPane.showMessageDialog(contentPane, "必须选择要移动到的文件夹!!!", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel)table.getModel();      //获得表格模型
        for(File selectFile:selectFiles)
        {
            //获得新文件名
            String fileName = directory.getAbsolutePath()+File.separator+selectFile.getName();
            selectFile.renameTo(new File(fileName));                        //移动文件
            //向表格模型中增加数据
            model.addRow(new String[]{selectFile.getName(),directory.getAbsolutePath()});
        }
        table.setModel(model);                                              //设置表格模型
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public static void main(String[] args) {            //实现多线程运行
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Ex1_Task3 frame = new Ex1_Task3();
                    frame.setVisible(true);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
