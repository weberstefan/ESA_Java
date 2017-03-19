package de.weber.esa.searching.wrapper;

import java.util.Objects;

/**
 * Created by Stefan on 13.03.2017.
 * <p>
 * Helper class for pattern matching
 * <p>
 * <ul>
 * <li>c : length of matching pattern</li>
 * <li>i : target positions SA[i]</li>
 * <li>j : target positions SA[j]</li>
 * </ul>
 */
public class PatternMatchingWrapper {

    /**
     * Represents the length of matching pattern's prefix
     */
    public final int c;

    /**
     * Represents target's most left position in SA
     */
    public final int i;

    /**
     * Represents target's most right position in SA
     */
    public final int j;

    public PatternMatchingWrapper(final int c,
                                  final int i,
                                  final int j) {
        this.c = c;
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof PatternMatchingWrapper)) {
            return false;
        }

        final PatternMatchingWrapper pmw = (PatternMatchingWrapper) o;
        return (pmw.c == this.c &&
                pmw.i == this.i &&
                pmw.j == this.j);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.c, this.i, this.j);
    }

    @Override
    public String toString() {
        return "(" + this.c + ", " + this.i + ", " + this.j + ")";
    }
}
