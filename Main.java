import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Greg on 03.02.2017.
 */
public class Main {
    public static void main(String args[]){
        int[] array = new int[100_000_000];
        fillArray(array);
        int n = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[n];
        Counter[] counters = new Counter[n];
        int step = 1;
        long time = System.nanoTime();
        for(int i = 0; i < n; i ++){
            counters[step-1] = new Counter(array,(array.length/n)*(step-1),(array.length/n)*step);
            threads[i] = new Thread(counters[step-1]);
            step++;
            threads[i].start();
        }
        for (int i = 0; i < n; i ++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BigInteger res = new BigInteger("0");
        for (Counter c: counters) {
            res = res.add(c.getRes());
        }
        time = System.nanoTime() - time;
        System.out.println(res);
        System.out.println(time+"nano sec");
        time = System.nanoTime();
        step = 1;
        for(int i = 0; i < n; i ++){
            counters[step-1] = new Counter(array,(array.length/n)*(step-1),(array.length/n)*step);
            threads[i] = new Thread(counters[step-1]);
            step++;
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        res = new BigInteger("0");
        for (Counter c: counters) {
            res = res.add(c.getRes());
        }
        time = System.nanoTime() - time;
        System.out.println(res);
        System.out.println(time+"nano sec");

    }
    private static void fillArray(int[] array){
        Random rm = new Random();
        for (int i = 0; i < array.length; i++){
            array[i] = rm.nextInt(100);
        }
    }
}
