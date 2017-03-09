package de.weber.esa.struct.discriminatingCharacters;

import java.util.Objects;

/**
 * Created by Stefan on 04.03.2017.
 * <p>
 * Wrapper class for the first discriminating characters of Suffix[SA[k]] and Suffix[SA[k - 1]]
 */
public class DiscriminatingCharacters {

    /**
     * first discriminating character of Suffix[SA[k - 1]]
     */
    public final char first;

    /**
     * first discriminating character of Suffix[SA[k]]
     */
    public final char second;

    public DiscriminatingCharacters(final char first,
                                    final char second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof DiscriminatingCharacters)) {
            return false;
        }

        final DiscriminatingCharacters dc = (DiscriminatingCharacters) o;

        return this.first == dc.first &&
                this.second == dc.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }

    @Override
    public String toString() {
        return this.first + "" + this.second;
    }

}
