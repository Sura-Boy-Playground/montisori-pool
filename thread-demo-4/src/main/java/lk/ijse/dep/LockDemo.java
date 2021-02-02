package lk.ijse.dep;

public class LockDemo {
                                                                
    public static void printSomething(){
        synchronized (String.class) {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " " + Math.round(Math.random() * 1000));
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(LockDemo::printSomething);
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printSomething();
    }
}
