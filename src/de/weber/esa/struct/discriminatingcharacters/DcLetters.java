package de.weber.esa.struct.discriminatingcharacters;

/**
 * Created by Stefan on 19.05.2017.
 */
public class DcLetters {

    public final char one;
    public final char two;

    public DcLetters(final char one,
                        final char two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public String toString() {
        return this.one + this.two + "";
    }

}
