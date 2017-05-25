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

        int minBlock = (from) / this.blockSize;
        int maxBlock = (to % this.blockSize == 0 ? to - 1 : to) / this.blockSize;

        final int outerPosLeft = this.sequentialMinimum(from, Math.min(((minBlock + 1) * this.blockSize) - 1, to));
        final int outerPosRight = this.sequentialMinimum(Math.max(maxBlock * this.blockSize, from), to);

        // blocks between left block and right block
        if (minBlock + 1 < maxBlock) {
            maxBlock = maxBlock - 1;
            minBlock = minBlock + 1;
            final int k = MathUtils.ld(maxBlock - minBlock + 1);
            final int r = this.internalQ[minBlock][k];
            final int s = this.internalQ[maxBlock - MathUtils.pow(2, k) + 1][k];
            final int rAbs = this.minPosBlock[r] + r * this.blockSize;
            final int sAbs = this.minPosBlock[s] + s * this.blockSize;
            final int minPosMid = this.array[rAbs] <= this.array[sAbs] ? rAbs : sAbs;
            final int left = this.array[outerPosLeft] <= this.array[minPosMid] ? outerPosLeft : minPosMid;
            final int right = this.array[left] <= this.array[outerPosRight] ? left : outerPosRight;
            // due to lcp[0] = - 1, check "right" to be leftest minimum in block
            final int rightBlock = (right % this.blockSize == 0 ? right - 1 : right) / this.blockSize;
            if (rightBlock <= to && rightBlock >= from) {
                final int testMinRightBlock = (rightBlock * this.blockSize + this.minPosBlock[rightBlock] + 1);
                return this.array[testMinRightBlock] <= this.array[right] ? testMinRightBlock : right;
            } else {
                return right;
            }
        }

        return this.array[outerPosLeft] <= this.array[outerPosRight] ? outerPosLeft : outerPosRight;
    }

    /**
     * Caclulating the minimum between 2 blocks
     * <p>
     * Representing the Q' in the script (RMQ2 - Abbildung 4.4 Page 144)
     *
     * @return Q'
     */
    private int[][] calcQ() {
        this.calcMinPosPerBlock();
        final int SIZE = this.minPosBlock.length;
        final int END = MathUtils.ld(SIZE);

        this.internalQ = new int[SIZE][END + 1];

        for (int i = 0; i < SIZE; i = i + 1) {
            this.internalQ[i][0] = i;
        }

        for (int k = 0; k < MathUtils.ld(SIZE); k = k + 1) {
            for (int i = 1; i + MathUtils.pow(2, k) < SIZE; i = i + 1) {
                final int a = this.minPosBlock[this.internalQ[i][k]];
                final int b = this.internalQ[i + MathUtils.pow(2, k)][k];
                if (a <= this.minPosBlock[b]) {
                    this.internalQ[i][k + 1] = this.internalQ[i][k];
                } else {
                    this.internalQ[i][k + 1] = b;
                }
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
//        this.F = new byte[(this.blockSize + SIZE - 1) / this.blockSize];
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
//            this.F[i / this.blockSize] = (byte) min;
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
