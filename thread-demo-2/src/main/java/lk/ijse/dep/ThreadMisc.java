package lk.ijse.dep;

public class ThreadMisc {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("My Thread: " + i);
            }
        });

        t1.setName("my-thread");
        t1.start();
        t1.setPriority(Thread.MAX_PRIORITY);

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main Thread: " + i);
        }

    }
}
