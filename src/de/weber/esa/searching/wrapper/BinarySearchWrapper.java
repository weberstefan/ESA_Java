package de.weber.esa.searching.wrapper;

import java.util.Objects;

/**
 * Created by Stefan on 16.03.2017.
 * <p>
 * Wrapper class for the output of the binary search
 */
public class BinarySearchWrapper {

    public final String query;
    public final int positionSuffixArrayLeft;
    public final int positionSuffixArrayRight;

    public BinarySearchWrapper(final String query,
                               final int positionSuffixArrayLeft,
                               final int positionSuffixArrayRight) {
        this.query = query;
        this.positionSuffixArrayLeft = positionSuffixArrayLeft;
        this.positionSuffixArrayRight = positionSuffixArrayRight;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof BinarySearchWrapper)) {
            return false;
        }

        final BinarySearchWrapper bsw = (BinarySearchWrapper) o;
        return (bsw.query.equals(this.query) &&
                bsw.positionSuffixArrayLeft == this.positionSuffixArrayLeft &&
                bsw.positionSuffixArrayRight == this.positionSuffixArrayRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.query, this.positionSuffixArrayLeft, this.positionSuffixArrayRight);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.query.length() + 18);
        sb.append(this.query + "");
        if (this.positionSuffixArrayLeft < 0 || this.positionSuffixArrayRight < 0) {
            sb.append(" not found.");
        } else {
            sb.append(" found at SA[" + this.positionSuffixArrayLeft + ".." + this.positionSuffixArrayRight + "]");
        }
        return sb.toString();
//        return this.query + "\t" + this.positionSuffixArrayLeft + "\t" + this.positionSuffixArrayRight;
    }

}
