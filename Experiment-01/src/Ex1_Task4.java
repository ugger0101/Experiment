import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex1_Task4 extends JFrame{
    private JPanel contentPane;
    private JTextField chooseTextField;
    private JTable table;

    public Ex1_Task4() throws HeadlessException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel,BorderLayout.NORTH);

        JLabel label = new JLabel("选择文件夹:");
        label.setFont(new Font("微软雅黑",Font.PLAIN,15));
        panel.add(label);

        chooseTextField = new JTextField();
        chooseTextField.setFont(new Font("微软雅黑",Font.PLAIN,15));
        panel.add(chooseTextField);
        chooseTextField.setColumns(12);

        JButton button = new JButton("选择");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_chooseButton_actionPerformed(e);
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
    protected void do_chooseButton_actionPerformed(ActionEvent e){
        JFileChooser chooser = new JFileChooser();                          //创建文件选择器
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);        //限制仅能选择文件夹
        chooser.setMultiSelectionEnabled(false);                            //禁止多重选择
        int result = chooser.showOpenDialog(this);                   //打开文件选择器
        if(result == JFileChooser.APPROVE_OPTION){
            File selectDirectory = chooser.getSelectedFile();               //获得选择的文件夹
            chooseTextField.setText(selectDirectory.getAbsolutePath());     //显示文件夹位置
            final File[] files = selectDirectory.listFiles();               //获得文件夹中文件及子目录
            //获得表格模型
            final DefaultTableModel model = (DefaultTableModel)table.getModel();
            //创建时间格式化方式
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while(model.getRowCount()>0){                                   //当按钮事件再次触发，也就是选择了新的文件夹时
                model.removeRow(model.getRowCount()-1);                     //原表格模型中的数据被清空
            }

            new Thread(){
                public void run(){
                    for(File file:files){
                        if(file.isFile()){                                  //如果是文件则向表格模型中增加数据
                            model.addRow(new Object[]{file.getName(),file.length(),format.format(new Date(file.lastModified()))});
                            try{
                                Thread.sleep(1000);                   //当前线程休眠1秒钟实现动态加载
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }.start();
        }
    }

    public static void main(String[] args) {            //实现多线程运行
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Ex1_Task4 frame = new Ex1_Task4();
                    frame.setVisible(true);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
