package de.weber.esa.searching.wrapper;

import java.util.Objects;

/**
 * Created by Stefan on 13.03.2017.
 * <p>
 * Helper class for intervals
 * <p>
 * <ul>
 * <li>i : left position of interval</li>
 * <li>j : right position of interval</li>
 * </ul>
 */
public class IntervalWrapper {

    public final int i;
    public final int j;

    public IntervalWrapper(final int i,
                           final int j) {
        this.i = i;
        this.j = j;
    }

    public boolean isNotNullInterval(final int length) {
        return (this.i >= 0 &&
                this.i <= this.j &&
                this.j <= length);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof IntervalWrapper)) {
            return false;
        }

        final IntervalWrapper iw = (IntervalWrapper) o;
        return (iw.i == this.i &&
                iw.j == this.j);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.i, this.j);
    }

    @Override
    public String toString() {
        return "(" + i + ", " + j + ")";
    }

}
