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
        final short[] shorts = new short[]{ - 1, 12, 2, 3, 4, 5, 1, 2, 3, 0, 1, 2, 3, 4 };
        RMQ rmq = new RMQ(shorts);

        final int l = 2;
        final int r = 10;

        System.out.println(rmq.query(l, r));
        System.out.println(rmq.sequentialMin(l, r));
    }

    /**
     * Represents the array, which will be used for calculating RMQ
     */
    private short[] array;

    /**
     * Represents the block size for RMQ
     */
    public final int blockSize;

    /**
     * Represents P' in script
     */
    private byte[] minPosBlock;

//    private byte[] F;

    /**
     * Represents internal datastructure Q' in script
     */
    public int[][] internalQ;

    public RMQ(final short[] array) {
        this(array, MathUtils.ld(array.length));
        System.out.println("block size: " + this.blockSize);
    }

    public RMQ(final short[] array,
               final int blockSize) {
        this.array = array;
        this.blockSize = blockSize;
        this.calcQ();
    }

    /**
     * Calculate the minimum position between from and to
     * <p>
     * This method is symmetrical
     *
     * @param from : starting position
     * @param to   : ending position
     * @return position of minimum entry in array
     */
    public int sequentialMin(int from,
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
        int minPos = - 1;

        for (int i = from; i <= to; i = i + 1) {
            if (this.array[i] < min) {
                min = this.array[i];
                minPos = i;
            }
        }

        return minPos;
    }

    /**
     * Calculate the minimum position between from and to
     * <p>
     * This method is symmetrical
     *
     * @param from : starting position
     * @param to   : ending position
     * @return position of minimum entry in array
     */
    public int query(int from, int to) {
        if (from == to) {
            return from;
        }

        if (to < from) {
            int swap = from;
            from = to;
            to = swap;
        }

        int minBlock = from / this.blockSize;
        int maxBlock = to  / this.blockSize;

        final int outerLeftPosition = this.sequentialMin(from, Math.min(((minBlock + 1) * this.blockSize) - 1, to));
        final int outerRightPosition = this.sequentialMin(Math.max(maxBlock * this.blockSize, from), to);

        // If true there are blocks between left and right block
        if (minBlock + 1 < maxBlock) {
            minBlock = minBlock + 1;
            maxBlock = maxBlock - 1;
            final int k = MathUtils.ld(maxBlock - minBlock + 1);
            final int r = this.internalQ[minBlock][k];
            final int s = this.internalQ[maxBlock - MathUtils.pow(2, k) + 1][k];
            final int rAbs = this.minPosBlock[r] + r * this.blockSize;
            final int sAbs = this.minPosBlock[s] + s * this.blockSize;
            final int minPositionMid =  this.array[rAbs] <= this.array[sAbs] ? rAbs : sAbs;
            int left = this.array[outerLeftPosition] <= this.array[minPositionMid] ? outerLeftPosition : minPositionMid;
            return this.array[left] <= this.array[outerRightPosition] ? left : outerRightPosition;
        }

        return this.array[outerLeftPosition] <= this.array[outerRightPosition] ? outerLeftPosition : outerRightPosition;
    }

    /**
     * Caclulating the minimum between 2 blocks
     * <p>
     * Representing the Q' in the script (RMQ - Abbildung 4.4 Page 144)
     *
     * @return Q'
     */
    private int[][] calcQ() {
        this.calcMinPosPerBlock();
        final int SIZE = this.minPosBlock.length;
        final int END = MathUtils.ld(SIZE);
        this.internalQ = new int[SIZE][END + 1];

        for (int i = 0; i < SIZE; i++) {
            this.internalQ[i][0] = i;
            for (int k = 1; k < END; k++) {
                this.internalQ[i][k] = -1;
            }
        }

        for (int k = 0, l = 1; k < END; k++, l *= 2) {
            for (int i = 0; i + l*2 <= SIZE; i++) {
                final int a = this.internalQ[i][k];
                final int b = this.internalQ[i + l][k];

                final int posA = this.minPosBlock[a] + a * this.blockSize;
                final int posB = this.minPosBlock[b] + b * this.blockSize;

                this.internalQ[i][k + 1] = (this.array[posA] <= this.array[posB]) ? a : b;
            }
        }

        return this.internalQ;
    }

    /**
     * Calculating the minimum positions per block
     * <p>
     * Representing the P' in the script
     *
     * @return minimum positions per block
     */
    private byte[] calcMinPosPerBlock() {
        final int SIZE = this.array.length;

        // Round to the next integer
        this.minPosBlock = new byte[(SIZE + this.blockSize - 1) / this.blockSize];

        for (int i = 0; i < SIZE; i += this.blockSize) {
            final int end = Math.min(i + this.blockSize, SIZE);

            int min = Integer.MAX_VALUE;
            int minPosition = -1;

            for (int j = i; j < end; j++) {
                int value = this.array[j];
                if (value < min) {
                    min = value;
                    minPosition = j - i;
                }
            }
            this.minPosBlock[i/this.blockSize] = (byte) minPosition;
        }

        return this.minPosBlock;
    }

    @Override
    public String toString() {
        return "P': " + Arrays.toString(this.minPosBlock)
//                + "\nF': " + Arrays.toString(this.F)
                + "\n" + ESA_Utils.printArray(this.internalQ);
//                + "\nArray" +
//                Arrays.toString(this.array);
    }

}
