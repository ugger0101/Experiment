package others;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Ex1_Task2 extends JFrame{
    private JPanel contentPane;
    private String textFile;
    private JTextField beforeTextField;
    private JTextField afterTextField;

    public Ex1_Task2() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(2,1));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel label = new JLabel("替换前字符串：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(label);

        beforeTextField = new JTextField();
        beforeTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(beforeTextField);
        beforeTextField.setColumns(12);

        JButton button = new JButton("选择文件");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(button);

        JLabel label2 = new JLabel("替换后字符串：");
        label2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(label2);

        afterTextField = new JTextField();
        afterTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(afterTextField);
        afterTextField.setColumns(12);

        JButton button2 = new JButton("开始替换");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_replaceButton_actionPerformed(e);
            }
        });
        button2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        panel.add(button2);
    }

    protected String do_button_actionPerformed(ActionEvent e){
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if(file.isDirectory()){
            System.out.println("文件夹:"+file.getAbsolutePath());
        }else if(file.isFile()){
            System.out.println("文件:"+file.getAbsolutePath());
            textFile = file.getAbsolutePath();
        }
        System.out.println(jfc.getSelectedFile().getName());
        return jfc.getSelectedFile().getName();
    }

    protected void do_replaceButton_actionPerformed(ActionEvent e){
        String before = beforeTextField.getText();
        System.out.println(before);
        String after = afterTextField.getText();
        System.out.println(after);
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        char[] temp = new char[1024];
        try{
            System.out.println(textFile);
            reader = new InputStreamReader(new FileInputStream(textFile), "UTF-8");
            while((flag = reader.read(temp))!=-1)
            {
                sb.append(temp);
            }
            String content = sb.toString().replace(before,after);
            System.out.println(content);
            writer = new OutputStreamWriter(new FileOutputStream(textFile),"UTF-8");
            writer = new FileWriter(textFile);
            System.out.println("写入"+textFile);
            writer.write(content);
            writer.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            //省略释放资源代码

        }
        JOptionPane.showMessageDialog(contentPane,"字符串替换成功！");
        return;
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Ex1_Task2 frame = new Ex1_Task2();
                    frame.setVisible(true);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
