package lk.ijse.dep;

public class LetMeDieDemo2 {

    public static void main(String[] args) {
        System.out.println("I am the main thread...!");
        System.out.println("Let me start a new thread");
        Thread t1 = new Thread(() -> {
            System.out.println("New thread has been started...");
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread() + " " + i);
            }
        }, "my-thread");
        t1.start();
        System.out.println("Main: Damn it, I have nothing to do...!");
        System.out.println("MyThread: Wait...! You gonna need to stay..!");
        System.out.println("Main: Save me... I am going to die soon...!");
        System.out.println("MyThread: Oh... no...!");
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: Finally, I can die peacefully...!");
        System.out.println("RIP");
    }
}
