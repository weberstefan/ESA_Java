package de.weber.esa.struct.rmq;

import de.weber.esa.utils.ESA_Utils;
import de.weber.esa.utils.MathUtils;

import java.util.Arrays;

/**
 * Created by Stefan on 22.01.2017.
 * <p>
 * Class for calculating RMQ values for a given array in O(n log(n))
 * <p>Algorithm 4.17 in Scriptum: Algorithmns and Sequences by Prof. Heun</p>
 */
public class RMQ {

    public static void main(String[] args) {
        final int[] a = new int[]{
                0, 1, 2, 1, 2, 1, 0, 1, 2, 3, 2, 3, 2, 3, 2, 1, 2, 1, 0, 1, 2, 1, 2, 3, 2, 3, 2, 3, 2, 1, 2, 1, 0
        };

//        final int[] a = new int[] {
//                0, 2, 1, 3, 1, 2, 0, 2, 0, 1
//        };

        System.out.println(a.length + " long");

        RMQ rmq = new RMQ(a, 5, false);
        System.out.println(rmq.toString());

        System.out.println(rmq.sequentialMinimum(16, 30));
        System.out.println(rmq.rmqQuery(0 , 3));
    }

    /**
     * Represents the array, which will be used for calculating RMQ
     */
    private int[] array;

    /**
     * Represents the block size for RMQ
     */
    private final int blockSize;

    /**
     * Represents P' in script
     */
    private byte[] minPosBlock;

    /**
     * Represents F' in script
     */
    private byte[] F;

    /**
     * Represents internal datastructure Q' in script
     */
    private int[][] internalQ;

    public RMQ(final int[] array,
               final int blockSize,
               final boolean isLCP) {
        this.array = array;
        this.blockSize = blockSize;
        if (isLCP) {
            // remove last entry of lcp array (-1 for computing child table)
            this.array = Arrays.copyOf(this.array, this.array.length - 1);
        }
        this.calcQ(isLCP);
    }

    public int rmqQuery(final int i,
                        final int j) {
        final int k = MathUtils.ld(j - i + 1);
        /* 2^k <= j - i + 1 < 2^(k+1) */

        System.out.println("k=" + k);

        int r = this.internalQ[i][k];
        int s = this.internalQ[j - MathUtils.pow(2, k) + 1][k];

        return this.F[r] <= this.F[s] ? r : s;
    }

    /**
     * Calculate the minimum position between from and to
     *
     * @param from : starting position
     * @param to : ending position
     * @return position of minimum entry in array
     */
    public int sequentialMinimum(int from,
                                 int to) {
        if (to == from) {
            return to;
        }

        if (from > to) {
            int swap = to;
            to = from;
            from = swap;
        }

        int min = Integer.MAX_VALUE;
        int minPos= - 1;

        for (int i = from; i <= to; i = i + 1) {
            if (this.array[i] < min) {
                min = this.array[i];
                minPos = i;
            }
        }

        return minPos;
    }

    /**
     * Caclulating the minimum between 2 blocks
     * <p>
     * Representing the Q' in the script (RMQ2 - Abbildung 4.4 Page 144)
     *
     * @param isLCP : necessary for calculating minimal positions per block
     */
    private void calcQ(final boolean isLCP) {
        this.calcMinPosPerBlock(isLCP);
        final int SIZE = this.minPosBlock.length;
        final int END = MathUtils.ld(SIZE);

        this.internalQ = new int[SIZE][END + 1];

        for (int i = 0; i < SIZE; i = i + 1) {
            this.internalQ[i][0] = i;
            // init all other entries with -1 due to LCA not found there
            for (int j = 1; j <= END; j = j + 1) {
                this.internalQ[i][j] = - 1;
            }
        }

        for (int k = 0, l = 1; k < END; l = l * 2, k = k + 1) {
            for (int i = 0; i + l * 2 <= SIZE; i = i + 1) {
                final int a = this.internalQ[i][k];
                final int b = this.internalQ[i + l][k];

                final int posA = this.minPosBlock[a] + a * this.blockSize;
                final int posB = this.minPosBlock[b] + b * this.blockSize;

                this.internalQ[i][k + 1] = (this.array[posA] <= this.array[posB]) ? a : b;
            }
        }
    }

    /**
     * Calculating the minimum positions per block
     * <p>
     * Representing the P' in the script
     *
     * @param isLCP : if RMQ with LCP, do not take first lcp-value into account
     */
    private void calcMinPosPerBlock(final boolean isLCP) {
        final int SIZE = this.array.length;
        int i = 0;

        if (isLCP) {
            // - 1 due to lcp[0] = -1 = $
            // set starting point i = 1
            this.minPosBlock = new byte[(this.blockSize + SIZE - 1) / this.blockSize];
            i = 1;
        } else {
            this.minPosBlock = new byte[(this.blockSize + SIZE) / this.blockSize];
        }

        this.F = new byte[this.minPosBlock.length];

        int curValue = - 1;

        for (; i < SIZE; i = i + this.blockSize) {
            // are we out of array length?
            final int END = Math.min(i + this.blockSize, SIZE);

            int min = Integer.MAX_VALUE;
            int minPos = - 1;

            for (int j = i; j < END; j = j + 1) {
                curValue = this.array[j];
                if (curValue < min) {
                    min = curValue;
                    minPos = j - i;
                }
            }

            this.minPosBlock[i / this.blockSize] = (byte) minPos;
            this.F[i / this.blockSize] = (byte) min;
        }
    }

    @Override
    public String toString() {
        return "RMQ\nP': " + Arrays.toString(this.minPosBlock) + "\nF': " + Arrays.toString(this.F) + "\n" + ESA_Utils.printArray(this.internalQ);
    }

}
