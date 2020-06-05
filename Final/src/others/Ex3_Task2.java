package others;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Date;

public class Ex3_Task2 extends JFrame {
    private JFrame frame = new JFrame("倒计时");
    private JLabel timerLabel = new JLabel("就绪");
    private JLabel timeLabel = new JLabel("当前时间：" + new Date().toLocaleString());
    private JButton startButton = new JButton("开始");
    private JButton stopButton = new JButton("重置");
    private JTextField hTimeField = new JTextField(3);
    private JTextField mTimeField = new JTextField(3);
    private long flag;
    private NumberFormat format = NumberFormat.getNumberInstance();
    private long mark;
    long pauseTime = 0;
    private JPanel comp;

    private Timer timer;

    private Timer time = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLabel.setText("当前时间：" + new Date().toLocaleString());
        }
    });

    public JPanel getComp() {
        return comp;
    }

    public Ex3_Task2() {
        time.start();
        format.setMinimumFractionDigits(0);
        timeLabel.setFont(new Font("黑体", Font.BOLD, 25));
        timerLabel.setFont(new Font("黑体", Font.BOLD, 25));

        comp = new JPanel(new BorderLayout());
        JPanel tempPanel = new JPanel();
        tempPanel.add(timeLabel);
        comp.add(tempPanel, BorderLayout.NORTH);
        JPanel timerSetPanel = new JPanel();
        timerSetPanel.setLayout(new BoxLayout(timerSetPanel, BoxLayout.Y_AXIS));
        mTimeField.setText("5");
        mTimeField.setHorizontalAlignment(JTextField.CENTER);
        mTimeField.setFont(new Font("黑体", Font.BOLD, 25));

        hTimeField.setText("0");
        hTimeField.setHorizontalAlignment(JTextField.CENTER);
        hTimeField.setFont(new Font("黑体", Font.BOLD, 25));

        MouseWheelListener mouseWheelListener = new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JTextField field = (JTextField) e.getSource();
                if (!field.isEnabled())
                    return;
                if (field.getText().equals(""))
                    field.setText("0");
                field.setText("" + (Integer.valueOf(field.getText()) - e.getWheelRotation()));
                if (Integer.valueOf(field.getText()) < 0)
                    field.setText("0");
                else if (Integer.valueOf(field.getText()) > 59)
                    field.setText("59");
                else if (field == hTimeField && Integer.valueOf(field.getText()) > 23)
                    field.setText("23");
                initTimerLabel();
            }
        };

        hTimeField.addMouseWheelListener(mouseWheelListener);
        mTimeField.addMouseWheelListener(mouseWheelListener);
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if ((!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)))
                    e.consume();
            }
        };
        hTimeField.addKeyListener(keyAdapter);
        mTimeField.addKeyListener(keyAdapter);

        CaretListener caretListener = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                initTimerLabel();
            }
        };
        hTimeField.addCaretListener(caretListener);
        mTimeField.addCaretListener(caretListener);

        JPanel tPanel = new JPanel();
        tPanel.add(hTimeField);
        tPanel.add(new JLabel("小时")).setFont(new Font("黑体", Font.BOLD, 25));
        tPanel.add(mTimeField);
        tPanel.add(new JLabel("分钟")).setFont(new Font("黑体", Font.BOLD, 25));
        timerSetPanel.add(tPanel);
        tPanel = new JPanel();
        tPanel.add(timerLabel);
        timerSetPanel.add(tPanel);
        comp.add(timerSetPanel);
        initTimerLabel();

        tempPanel = new JPanel();
        tempPanel.add(startButton);
        tempPanel.add(stopButton);
        comp.add(tempPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JButton) e.getSource()).getText().equals("开始")) {
                    try {
                        timer.restart();
                    } catch (Exception exce) {
                        mark = System.currentTimeMillis();
                        pauseTime = 0;
                        initTimerLabel(1);

                        timer = new Timer(100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                long now = System.currentTimeMillis();
                                long residue = flag - (now - mark);
                                Date date = new Date(residue);
                                int h = date.getHours() - 8;
                                h += (h < 0) ? 24 : 0;
                                timerLabel.setText(format.format(h) + ":" + format.format(date.getMinutes()) + ":"
                                        + format.format(date.getSeconds()));
                            }
                        });
                        timer.start();
                        hTimeField.setEnabled(false);
                        mTimeField.setEnabled(false);
                    }
                    ((JButton) e.getSource()).setText("暂停");
                } else {
                    timer.stop();
                    pauseTime = new Date().getTime();
                    ((JButton) e.getSource()).setText("开始");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timer.stop();
                    timer = null;
                    hTimeField.setEnabled(true);
                    mTimeField.setEnabled(true);
                    startButton.setText("开始");
                    initTimerLabel();
                } catch (Exception exce) {
                }
            }
        });

        comp.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        frame.add(comp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

        startButton.requestFocus();

        flag = (Integer.valueOf(hTimeField.getText()) * 60 + Integer.valueOf(mTimeField.getText())) * 60000;

            new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (!timer.isRunning()) {
                            long now = System.currentTimeMillis();
                            mark += now - pauseTime;
                            pauseTime = new Date().getTime();
                        }
                    } catch (Exception exce) {
                    }
                }
            }).start();
            new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String string = timerLabel.getText();
                        if (string.equals("0:0:0") || string.startsWith("-")) {
                            timer.stop();
                            JOptionPane.showMessageDialog(Ex3_Task2.this.frame, "计时结束");
                            stopButton.doClick();
                        }
                    } catch (Exception exce) {
                    }
                }
            }).start();
        }

    public void initTimerLabel() {
        int h;
        try {
            h = Integer.valueOf(hTimeField.getText());
        } catch (Exception e) {
            h = 0;
        }
        int m;
        try {
            m = Integer.valueOf(mTimeField.getText());
        } catch (Exception e2) {
            m = 0;
        }

        flag = (h * 60 + m) * 60000;
        timerLabel.setText(format.format(h) + ":" + format.format(m) + ":0");
    }

    public void initTimerLabel(int i) {
        int h = Integer.valueOf(hTimeField.getText());
        int m = Integer.valueOf(mTimeField.getText());
        flag = (h * 60 + m) * 60000;
        m--;
        if (m < 0) {
            m = 59;
        }
    }

    public static void main(String[] args) {
        new Ex3_Task2();
    }
}
