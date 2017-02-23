package de.weber.esa.struct.rmq;

import de.weber.esa.utils.ESA_Utils;

import java.util.Arrays;

/**
 * Created by Stefan on 22.01.2017.
 */
public class RMQ {

    private int[] array;

    private final int blockSize;

    /**
     * Represents P' in script
     */
    private byte[] minPosBlock;

    private int[][] Q;

    public RMQ(final int[] array,
               final int blockSize) {
        this.array = array;
        // remove last entry of lcp array (-1 for computing child table)
        this.array = Arrays.copyOf(this.array, this.array.length - 1);
        System.out.println(Arrays.toString(this.array));
        this.blockSize = blockSize;
        this.calcMinBetweenTwoBlocks();
    }

    private void calcMinBetweenTwoBlocks() {
        this.calcMinPosPerBlock();
        final int SIZE = this.minPosBlock.length;
        final int END = ESA_Utils.ld(SIZE);

        this.Q = new int[SIZE][END + 1];

        for (int i = 0; i < SIZE; i = i + 1) {
            this.Q[i][0] = i;
            for (int j = 1; j < END; j = j + 1) {
                this.Q[i][j] = - 1;
            }
        }

        for (int j = 0, l = 1; j < END; j = j + 1, l = l * 2) {
            for (int i = 0; i + l * 2 <= SIZE; i = i + 1) {
                final int a = this.Q[i][j];
                final int b = this.Q[i + l][j];

                final int posA = this.minPosBlock[a] + a * this.blockSize;
                final int posB = this.minPosBlock[b] + b * this.blockSize;

                this.Q[i][j + 1] = this.array[posA] <= this.array[posB] ? a : b;
            }
        }
    }

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
        return "RMQ:\t" + Arrays.toString(this.minPosBlock) + "\n" + ESA_Utils.printArray(this.Q);
    }

}
