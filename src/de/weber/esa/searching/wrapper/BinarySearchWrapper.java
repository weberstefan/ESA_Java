package de.weber.esa.searching.wrapper;

import java.util.Objects;

/**
 * Created by Stefan on 16.03.2017.
 * <p>
 * Wrapper class for the output of the binary search
 */
public class BinarySearchWrapper {

    public final String query;
    public final int positionSuffixArray;
    public final int positionSequence;

    public BinarySearchWrapper(final String query,
                               final int positionSuffixArray,
                               final int positionSequence) {
        this.query = query;
        this.positionSuffixArray = positionSuffixArray;
        this.positionSequence = positionSequence;
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
                bsw.positionSuffixArray == this.positionSuffixArray &&
                bsw.positionSequence == this.positionSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.query, this.positionSuffixArray, this.positionSequence);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.positionSuffixArray < 0) {
            sb.append("Not found query: \"");
        } else {
            sb.append("Found at SA[" + this.positionSuffixArray + "] -> Position in Sequence: (" + this.positionSequence + "); query:\"");
        }
        sb.append(this.query + "\"");
        return sb.toString();
    }

}
