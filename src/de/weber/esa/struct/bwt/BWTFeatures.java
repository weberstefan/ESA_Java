package de.weber.esa.struct.bwt;

import java.util.Objects;

/**
 * Created by Stefan on 14.01.2017.
 * <p>
 * Helper class to create the BWT
 */
public class BWTFeatures {

    /**
     * Represents the first appearance position of the character in the suffix array
     */
    public final int posSequence;

    /**
     * Represents the position in the map
     */
    public final int posMap;

    public BWTFeatures(final int posSequence,
                       final int posMap) {
        this.posSequence = posSequence;
        this.posMap = posMap;
    }

    public int getPosSequence() {
        return this.posSequence;
    }

    public int getPosMap() {
        return this.posMap;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof BWTFeatures)) {
            return false;
        }

        final BWTFeatures f = (BWTFeatures) o;

        return (f.posSequence == this.posSequence &&
                f.posMap == this.posMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.posSequence, this.posMap);
    }

    @Override
    public String toString() {
        return this.getPosSequence() + " - " + this.getPosMap();
    }

}
