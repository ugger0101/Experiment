public class Ex4_Task1 implements Runnable {
    int num = 10;

    public void run() {
        while (true) {
            synchronized (this) {
                if (num > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("车票还有 " + num-- + " 张");
                }
            }
        }
    }

    public static void main(String[] args) {
        Ex4_Task1 t = new Ex4_Task1();
        Thread tA = new Thread(t);
        Thread tB = new Thread(t);
        Thread tC = new Thread(t);
        Thread tD = new Thread(t);
        tA.start();
        tB.start();
        tC.start();
        tD.start();
    }
}
