package de.weber.esa.struct.lcp;

import java.util.Objects;

/**
 * Created by Stefan on 27.04.2017.
 */
public class LcpException {

    public final int lcpPosition;
    public final int lcpValue;

    public LcpException(final int lcpPosition,
                        final int lcpValue) {
        this.lcpPosition = lcpPosition;
        this.lcpValue = lcpValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (! (o instanceof LcpException)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        final LcpException e = (LcpException) o;
        return (e.lcpValue == this.lcpValue &&
                e.lcpPosition == this.lcpPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lcpValue, this, lcpPosition);
    }

    @Override
    public String toString() {
        return this.lcpPosition + ":" + this.lcpValue;
    }

}
