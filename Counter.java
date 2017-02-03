import java.math.BigInteger;

/**
 * Created by Greg on 03.02.2017.
 */
public class Counter implements Runnable {
    private int[] n;
    private int start;
    private int end;
    private BigInteger res;



    public Counter(int[] n, int start, int end) {
        this.n = n;
        this.start = start;
        this.end = end;
    }

    public Counter() {
    }

    @Override
    public void run() {
        res = count();
    }
    public BigInteger getRes() {
        return res;
    }
    private BigInteger count(){
        BigInteger res = new BigInteger("0");
        for (int i = start; i <end; i++) {
            res =res.add(new BigInteger(""+n[i]));
        }
        return res;
    }
}
