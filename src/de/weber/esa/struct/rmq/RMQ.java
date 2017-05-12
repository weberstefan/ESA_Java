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

        final short[] a = new short[]{
//                - 1, 0, 1, 2, 1, 2, 1, 0, 1, 2, 3, 2, 3, 2, 3, 2, 1, 2, 1, 0, 1, 2, 1, 2, 3, 2, 3, 2, 3, 2, 1, 2, 1, 0, - 1
                - 1, 0, 2, 1, 3, 1, 2, 0, 2, 0, 1, - 1
//                -1, 0, 1, 1, 2, 0, 2, 3, 1, 0, 3, 1, 0, 1, -1
        };

        System.out.println(a.length + " long");

        RMQ rmq = new RMQ(a);
//        System.out.println(rmq.toString());
//
//        System.out.println("seq min : " + rmq.sequentialMinimum(2, 10));
        System.out.println("query   : " + rmq.query(2, 6));
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

    /**
     * Represents internal datastructure Q' in script
     */
    public byte[][] internalQ;

    public RMQ(final short[] array) {
        this(array, MathUtils.ld(array.length));
        System.out.println(this.blockSize + " = block size");
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
        int maxBlock = to / this.blockSize;

        final int outerLeftPosition = this.sequentialMinimum(from, Math.min(((minBlock + 1) * this.blockSize) - 1, to));
        final int outerRightPosition = this.sequentialMinimum(Math.max(maxBlock * this.blockSize, from), to);

        // blocks between left block and right block
        if (minBlock + 1 < maxBlock) {
            minBlock = minBlock + 1;
            maxBlock = maxBlock - 1;
            final int k = MathUtils.ld(maxBlock - minBlock + 1);
            final int r = this.internalQ[minBlock][k];
            final int s = this.internalQ[maxBlock - MathUtils.pow(2, k) + 1][k];
            final int rAbsolute = this.minPosBlock[r] + r * this.blockSize;
            final int sAbsolute = this.minPosBlock[s] + s * this.blockSize;
            final int minMiddlePosition = this.array[rAbsolute] <= this.array[sAbsolute] ? rAbsolute : sAbsolute;
            int left = this.array[outerLeftPosition] <= this.array[minMiddlePosition] ? outerLeftPosition : minMiddlePosition;
            return this.array[left] <= this.array[outerRightPosition] ? left : outerRightPosition;
        }

        return this.array[outerLeftPosition] <= this.array[outerRightPosition] ? outerLeftPosition : outerRightPosition;
    }

    /**
     * Caclulating the minimum between 2 blocks
     * <p>
     * Representing the Q' in the script (RMQ2 - Abbildung 4.4 Page 144)
     *
     * @return Q'
     */
    private byte[][] calcQ() {
        this.calcMinPosPerBlock();
        final int SIZE = this.minPosBlock.length;
        final int END = MathUtils.ld(SIZE);

        this.internalQ = new byte[SIZE][END + 1];

        for (int i = 0; i < SIZE; i = i + 1) {
            this.internalQ[i][0] = (byte) i;
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

                this.internalQ[i][k + 1] = (this.array[posA] <= this.array[posB]) ? (byte) a : (byte) b;
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
        final int SIZE = this.array.length - 1;
        this.minPosBlock = new byte[(this.blockSize + SIZE - 1) / this.blockSize];
        int curValue = - 1;

        for (int i = 1; i < SIZE; i = i + this.blockSize) {
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
            System.out.print(min + "\t");
        }
        System.out.println();
        return this.minPosBlock;
    }

    @Override
    public String toString() {
        return "RMQ\nA': " + Arrays.toString(this.array)
                + "\nP': " + Arrays.toString(this.minPosBlock)
                + "\n" + ESA_Utils.printArray(this.internalQ);
    }

}
