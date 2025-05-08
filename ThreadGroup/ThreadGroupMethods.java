package org.example.ThreadGroup;

public class ThreadGroupMethods {

    public static void main(String[] args) {
        /*
        1. String getName()
        2. int getMaxPriority()
        3. void setMAxPriority()
        4. ThreadGroup getParent()
        5. void list()
        6. int activeCount()
        7. int activeGroupCount()
        8. int enumerate(Thread[] t)
        9. int enumerate(ThreadGroup[] g)
        10. boolean isDemon()
        11. void setDemon()
        12. void interupt()
        13. void destroy()
         */

        ThreadGroup pg = new ThreadGroup("Parent-Group");
        System.out.println(pg.getName()); // Parent-Group
        System.out.println(pg.getMaxPriority()); // 10 - MaxPriority of group

        Thread t1 = new Thread(pg, ()->
            System.out.println("Running"),"First Thread");
        t1.start();
        System.out.println(t1.getState());
        System.out.println("Thread Belongs to : "+t1.getThreadGroup().getName()); // Thread Belongs to : Parent-Group

        Thread t2 = new Thread(pg,()->
                System.out.println("Running"),"thread 2");
        t2.setPriority(10);
        t2.start();
        System.out.println(t2.getPriority()); // 10

        // setting Parent Group priority
        pg.setMaxPriority(3);
        System.out.println(t1.getPriority());  // 5
        System.out.println(t2.getPriority()); // 10
        /*
        Note - Already created Thread won't get affected by setting Parent group priority
        It's loophole in setMaxPriority for ThreadGroup
        But freshly created thread will have  same priority as Parent Group priority
         */

        Thread t3 = new Thread(pg,()->
                System.out.println("Running"),"Thread 3");

        System.out.println(t3.getPriority()); // 3
        t3.setPriority(5); // Even after setting it will not get changed, Parent Group priority will be given precedence
        System.out.println(t3.getPriority()); // 3
        t3.start();


        System.out.println(t3.getThreadGroup().getName()); // Parent-Group
        System.out.println(pg.getParent()); // java.lang.ThreadGroup[name=main,maxpri=10] -- Main is parent of Parent Group

        ThreadGroup cg = new ThreadGroup(pg, "Child-Group"); // Creating child group in Parent group
        Thread t4 = new Thread(cg,()-> System.out.println("running"),"Thread 4");
        t4.start();
        System.out.println(cg.getParent().getName());  // Parent-Group
        System.out.println(t4.getThreadGroup().getName()); // Child-Group


        System.out.println("Running thread in Parent Group : " + pg.activeCount());
        /*
        List Running thread Groups only that is running in current Thread group
         */
        Thread.currentThread().getThreadGroup().list();  // printing what is running in main thread group
        /*
        java.lang.ThreadGroup[name=main,maxpri=10]
        Thread[main,5,main]
        Thread[Monitor Ctrl-Break,5,main]
        java.lang.ThreadGroup[name=Parent-Group,maxpri=3]
            java.lang.ThreadGroup[name=Child-Group,maxpri=3]

         */


        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        pg.list();
        /*
        java.lang.ThreadGroup[name=Parent-Group,maxpri=3]
    java.lang.ThreadGroup[name=Child-Group,maxpri=3]
         */
        Thread[] list = new Thread[5];
        System.out.println(pg.enumerate(list));

    }
}
