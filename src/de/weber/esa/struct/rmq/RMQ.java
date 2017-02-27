package de.weber.esa.struct.rmq;

import de.weber.esa.utils.ESA_Utils;

import java.util.Arrays;

/**
 * Created by Stefan on 22.01.2017.
 *  * <p>
 * Class for calculating RMQ values for a given array in O(n log(n))
 * <p>Algorithm 4.4 in Scriptum: Algorithmns and Sequences by Prof. Heun</p>
 */
public class RMQ {

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
     * Represents internal datastructure Q' in script
     */
    private int[][] internalQ;

    public RMQ(final int[] array,
               final int blockSize) {
        this.array = array;
        // remove last entry of lcp array (-1 for computing child table)
        this.array = Arrays.copyOf(this.array, this.array.length - 1);
        System.out.println(Arrays.toString(this.array));
        this.blockSize = blockSize;
        this.calcMinBetweenTwoBlocks();
    }

    /**
     * Caclulating the minimum between 2 blocks
     * <p>
     * Representing the Q' in the script (RMQ2 - Abbildung 4.4 Page 144)
     */
    private void calcMinBetweenTwoBlocks() {
        this.calcMinPosPerBlock();
        final int SIZE = this.minPosBlock.length;
        final int END = ESA_Utils.ld(SIZE);

        this.internalQ = new int[SIZE][END + 1];

        for (int i = 0; i < SIZE; i = i + 1) {
            this.internalQ[i][0] = i;
        }

        for (int k = 0; k <= END; k = k + 1) {
            for (int i = 1; i + ESA_Utils.pow(2, k) < SIZE; i = i + 1) {
                if (this.minPosBlock[this.internalQ[i][k]] <= this.minPosBlock[this.internalQ[i + ESA_Utils.pow(2, k)][k]]) {
                    this.internalQ[i][k + 1] = this.internalQ[i][k];
                } else {
                    this.internalQ[i][k + 1] = this.internalQ[i + ESA_Utils.pow(2, k)][k];
                }
            }
        }

        System.out.println("SIZE=" + SIZE + "\tEND=" + END);
    }

    /**
     * Calculating the minimum positions per block
     * <p>
     * Representing the P' in the script
     */
    private void calcMinPosPerBlock() {
        final int SIZE = this.array.length;

        // - 1 due to lcp[0] = -1 = $
        this.minPosBlock = new byte[(this.blockSize + SIZE - 1) / this.blockSize];
        System.out.println(this.minPosBlock.length);

        int curValue = - 1;

        // start at 1, since lcp[0] = -1 = $
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
        }
    }

    @Override
    public String toString() {
        return "RMQ:\t" + Arrays.toString(this.minPosBlock) + "\n" + ESA_Utils.printArray(this.internalQ);
    }

}
