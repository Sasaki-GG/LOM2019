package lily.tool.parallelcompute;

import java.util.concurrent.atomic.AtomicInteger;
// 王文宇改进的能够并行计算的一个模块，提高Lily的匹配速度
/**
 * Abstraction of a parallel for loop. For example
 *
 * for (int i = 0; i < 3; i++) {
 * 	System.out.println(i);
 * }
 *
 * becomes
 *
 * new parfor(_num_threads) {
 * 	public void iter(int thread_idx, int i) {
 * 		System.out.println(i);
 * 	}
 * }.execute(0, 3);
 *
 * @author Gunnar Selke
 *
 */

public abstract class parfor {
    /** Number of threads used */
    private final int num_threads;
    /** */
    private AtomicInteger done_cnt = new AtomicInteger(0);

    /** Method to be overwritten (body of the for loop) **/
    abstract public void iter(int thread_idx, int i);

    /**
     * Standard constructor. Takes the number of threads to use
     *
     * @param num_threads
     *            the number of threads
     */
    public parfor(int num_threads) {
        this.num_threads = num_threads;
    }

    /**
     * Alternative constructor. Determines the number of threads from the number
     * of availabe processors
     */
    public parfor() {
        this(Integer.min(Runtime.getRuntime().availableProcessors(), 4));
    }

    /**
     * Executes the loop
     *
     * @param i0
     *            start point
     * @param i1
     *            end point
     */
    public void execute(final int i0, final int i1) {
        final parfor parent = this;
        for (int i = 0; i < num_threads; ++i) {
            final int thread_idx = i;
            new Thread() {
                public void run() {
                    for (int i = i0 + thread_idx; i < i1; i += num_threads)
                        parent.iter(thread_idx, i);
                    synchronized (parent) {
                        parent.done_cnt.incrementAndGet();
                        parent.notify();
                    }
                }
            }.start();
        }
        while (true) {
            synchronized (this) {
                if (done_cnt.get() == num_threads)
                    break;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
