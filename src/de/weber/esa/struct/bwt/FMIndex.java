package de.weber.esa.struct.bwt;

/**
 * Created by Stefan on 20.01.2017.
 *
 * Helper class for getting left and right indices for query in suffix array positions
 */
public class FMIndex {

    /**
     * left index of query in suffix array
     */
    private final int l;

    /**
     * right index of query in suffix array
     */
    private final int r;

    public FMIndex(final int l,
                   final int r) {
        this.l = l;
        this.r = r;
    }

    public int getL() {
        return this.l;
    }

    public int getR() {
        return this.r;
    }

    @Override
    public String toString() {
        if (l >= 0 && r >= 0) {
            return " [" + this.getL() + ", " + this.getR() + "]";
        }
        return " not found";
    }


}
