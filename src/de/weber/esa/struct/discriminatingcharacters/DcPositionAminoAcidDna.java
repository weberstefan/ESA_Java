package de.weber.esa.struct.discriminatingcharacters;


import de.weber.esa.struct.EnhancedSuffixArray;

/**
 * Created by Stefan on 18.05.2017.
 */
public class DcPositionAminoAcidDna {

    public final byte[] dcs;

    public DcPositionAminoAcidDna(final EnhancedSuffixArray esa) {
        this.dcs = new byte[esa.length - 1];

        for (int i = 1; i < esa.lcp.length; i = i + 1) {
            final char one = esa.sequence[esa.suffices[i - 1] + esa.lcp.getCurrentLcpValue(i)];
            final char two = esa.sequence[esa.suffices[i] + esa.lcp.getCurrentLcpValue(i)];

            this.dcs[i - 1] = this.getCurrent(one, two);
        }
    }

    private byte getCurrent(final char one, final char two) {
        if (one == '$' && two == 'A') {
            return (byte) 0;
        } else if (one == '$' && two == 'C') {
            return (byte) 1;
        } else if (one == '$' && two == 'D') {
            return (byte) 2;
        } else if (one == '$' && two == 'E') {
            return (byte) 3;
        } else if (one == '$' && two == 'F') {
            return (byte) 4;
        } else if (one == '$' && two == 'G') {
            return (byte) 5;
        } else if (one == '$' && two == 'H') {
            return (byte) 6;
        } else if (one == '$' && two == 'I') {
            return (byte) 7;
        } else if (one == '$' && two == 'K') {
            return (byte) 8;
        } else if (one == '$' && two == 'L') {
            return (byte) 9;
        } else if (one == '$' && two == 'M') {
            return (byte) 10;
        } else if (one == '$' && two == 'N') {
            return (byte) 11;
        } else if (one == '$' && two == 'P') {
            return (byte) 12;
        } else if (one == '$' && two == 'Q') {
            return (byte) 13;
        } else if (one == '$' && two == 'R') {
            return (byte) 14;
        } else if (one == '$' && two == 'S') {
            return (byte) 15;
        } else if (one == '$' && two == 'T') {
            return (byte) 16;
        } else if (one == '$' && two == 'V') {
            return (byte) 17;
        } else if (one == '$' && two == 'W') {
            return (byte) 18;
        } else if (one == '$' && two == 'X') {
            return (byte) 19;
        } else if (one == '$' && two == 'Y') {
            return (byte) 20;
        } else if (one == 'A' && two == 'C') {
            return (byte) 21;
        } else if (one == 'A' && two == 'D') {
            return (byte) 22;
        } else if (one == 'A' && two == 'E') {
            return (byte) 23;
        } else if (one == 'A' && two == 'F') {
            return (byte) 24;
        } else if (one == 'A' && two == 'G') {
            return (byte) 25;
        } else if (one == 'A' && two == 'H') {
            return (byte) 26;
        } else if (one == 'A' && two == 'I') {
            return (byte) 27;
        } else if (one == 'A' && two == 'K') {
            return (byte) 28;
        } else if (one == 'A' && two == 'L') {
            return (byte) 29;
        } else if (one == 'A' && two == 'M') {
            return (byte) 30;
        } else if (one == 'A' && two == 'N') {
            return (byte) 31;
        } else if (one == 'A' && two == 'P') {
            return (byte) 32;
        } else if (one == 'A' && two == 'Q') {
            return (byte) 33;
        } else if (one == 'A' && two == 'R') {
            return (byte) 34;
        } else if (one == 'A' && two == 'S') {
            return (byte) 35;
        } else if (one == 'A' && two == 'T') {
            return (byte) 36;
        } else if (one == 'A' && two == 'V') {
            return (byte) 37;
        } else if (one == 'A' && two == 'W') {
            return (byte) 38;
        } else if (one == 'A' && two == 'X') {
            return (byte) 39;
        } else if (one == 'A' && two == 'Y') {
            return (byte) 40;
        } else if (one == 'C' && two == 'D') {
            return (byte) 41;
        } else if (one == 'C' && two == 'E') {
            return (byte) 42;
        } else if (one == 'C' && two == 'F') {
            return (byte) 43;
        } else if (one == 'C' && two == 'G') {
            return (byte) 44;
        } else if (one == 'C' && two == 'H') {
            return (byte) 45;
        } else if (one == 'C' && two == 'I') {
            return (byte) 46;
        } else if (one == 'C' && two == 'K') {
            return (byte) 47;
        } else if (one == 'C' && two == 'L') {
            return (byte) 48;
        } else if (one == 'C' && two == 'M') {
            return (byte) 49;
        } else if (one == 'C' && two == 'N') {
            return (byte) 50;
        } else if (one == 'C' && two == 'P') {
            return (byte) 51;
        } else if (one == 'C' && two == 'Q') {
            return (byte) 52;
        } else if (one == 'C' && two == 'R') {
            return (byte) 53;
        } else if (one == 'C' && two == 'S') {
            return (byte) 54;
        } else if (one == 'C' && two == 'T') {
            return (byte) 55;
        } else if (one == 'C' && two == 'V') {
            return (byte) 56;
        } else if (one == 'C' && two == 'W') {
            return (byte) 57;
        } else if (one == 'C' && two == 'X') {
            return (byte) 58;
        } else if (one == 'C' && two == 'Y') {
            return (byte) 59;
        } else if (one == 'D' && two == 'E') {
            return (byte) 60;
        } else if (one == 'D' && two == 'F') {
            return (byte) 61;
        } else if (one == 'D' && two == 'G') {
            return (byte) 62;
        } else if (one == 'D' && two == 'H') {
            return (byte) 63;
        } else if (one == 'D' && two == 'I') {
            return (byte) 64;
        } else if (one == 'D' && two == 'K') {
            return (byte) 65;
        } else if (one == 'D' && two == 'L') {
            return (byte) 66;
        } else if (one == 'D' && two == 'M') {
            return (byte) 67;
        } else if (one == 'D' && two == 'N') {
            return (byte) 68;
        } else if (one == 'D' && two == 'P') {
            return (byte) 69;
        } else if (one == 'D' && two == 'Q') {
            return (byte) 70;
        } else if (one == 'D' && two == 'R') {
            return (byte) 71;
        } else if (one == 'D' && two == 'S') {
            return (byte) 72;
        } else if (one == 'D' && two == 'T') {
            return (byte) 73;
        } else if (one == 'D' && two == 'V') {
            return (byte) 74;
        } else if (one == 'D' && two == 'W') {
            return (byte) 75;
        } else if (one == 'D' && two == 'X') {
            return (byte) 76;
        } else if (one == 'D' && two == 'Y') {
            return (byte) 77;
        } else if (one == 'E' && two == 'F') {
            return (byte) 78;
        } else if (one == 'E' && two == 'G') {
            return (byte) 79;
        } else if (one == 'E' && two == 'H') {
            return (byte) 80;
        } else if (one == 'E' && two == 'I') {
            return (byte) 81;
        } else if (one == 'E' && two == 'K') {
            return (byte) 82;
        } else if (one == 'E' && two == 'L') {
            return (byte) 83;
        } else if (one == 'E' && two == 'M') {
            return (byte) 84;
        } else if (one == 'E' && two == 'N') {
            return (byte) 85;
        } else if (one == 'E' && two == 'P') {
            return (byte) 86;
        } else if (one == 'E' && two == 'Q') {
            return (byte) 87;
        } else if (one == 'E' && two == 'R') {
            return (byte) 88;
        } else if (one == 'E' && two == 'S') {
            return (byte) 89;
        } else if (one == 'E' && two == 'T') {
            return (byte) 90;
        } else if (one == 'E' && two == 'V') {
            return (byte) 91;
        } else if (one == 'E' && two == 'W') {
            return (byte) 92;
        } else if (one == 'E' && two == 'X') {
            return (byte) 93;
        } else if (one == 'E' && two == 'Y') {
            return (byte) 94;
        } else if (one == 'F' && two == 'G') {
            return (byte) 95;
        } else if (one == 'F' && two == 'H') {
            return (byte) 96;
        } else if (one == 'F' && two == 'I') {
            return (byte) 97;
        } else if (one == 'F' && two == 'K') {
            return (byte) 98;
        } else if (one == 'F' && two == 'L') {
            return (byte) 99;
        } else if (one == 'F' && two == 'M') {
            return (byte) 100;
        } else if (one == 'F' && two == 'N') {
            return (byte) 101;
        } else if (one == 'F' && two == 'P') {
            return (byte) 102;
        } else if (one == 'F' && two == 'Q') {
            return (byte) 103;
        } else if (one == 'F' && two == 'R') {
            return (byte) 104;
        } else if (one == 'F' && two == 'S') {
            return (byte) 105;
        } else if (one == 'F' && two == 'T') {
            return (byte) 106;
        } else if (one == 'F' && two == 'V') {
            return (byte) 107;
        } else if (one == 'F' && two == 'W') {
            return (byte) 108;
        } else if (one == 'F' && two == 'X') {
            return (byte) 109;
        } else if (one == 'F' && two == 'Y') {
            return (byte) 110;
        } else if (one == 'G' && two == 'H') {
            return (byte) 111;
        } else if (one == 'G' && two == 'I') {
            return (byte) 112;
        } else if (one == 'G' && two == 'K') {
            return (byte) 113;
        } else if (one == 'G' && two == 'L') {
            return (byte) 114;
        } else if (one == 'G' && two == 'M') {
            return (byte) 115;
        } else if (one == 'G' && two == 'N') {
            return (byte) 116;
        } else if (one == 'G' && two == 'P') {
            return (byte) 117;
        } else if (one == 'G' && two == 'Q') {
            return (byte) 118;
        } else if (one == 'G' && two == 'R') {
            return (byte) 119;
        } else if (one == 'G' && two == 'S') {
            return (byte) 120;
        } else if (one == 'G' && two == 'T') {
            return (byte) 121;
        } else if (one == 'G' && two == 'V') {
            return (byte) 122;
        } else if (one == 'G' && two == 'W') {
            return (byte) 123;
        } else if (one == 'G' && two == 'X') {
            return (byte) 124;
        } else if (one == 'G' && two == 'Y') {
            return (byte) 125;
        } else if (one == 'H' && two == 'I') {
            return (byte) 126;
        } else if (one == 'H' && two == 'K') {
            return (byte) 127;
        } else if (one == 'H' && two == 'L') {
            return (byte) - 1;
        } else if (one == 'H' && two == 'M') {
            return (byte) - 2;
        } else if (one == 'H' && two == 'N') {
            return (byte) - 3;
        } else if (one == 'H' && two == 'P') {
            return (byte) - 4;
        } else if (one == 'H' && two == 'Q') {
            return (byte) - 5;
        } else if (one == 'H' && two == 'R') {
            return (byte) - 6;
        } else if (one == 'H' && two == 'S') {
            return (byte) - 7;
        } else if (one == 'H' && two == 'T') {
            return (byte) - 8;
        } else if (one == 'H' && two == 'V') {
            return (byte) - 9;
        } else if (one == 'H' && two == 'W') {
            return (byte) - 10;
        } else if (one == 'H' && two == 'X') {
            return (byte) - 11;
        } else if (one == 'H' && two == 'Y') {
            return (byte) - 12;
        } else if (one == 'I' && two == 'K') {
            return (byte) - 13;
        } else if (one == 'I' && two == 'L') {
            return (byte) - 14;
        } else if (one == 'I' && two == 'M') {
            return (byte) - 15;
        } else if (one == 'I' && two == 'N') {
            return (byte) - 16;
        } else if (one == 'I' && two == 'P') {
            return (byte) - 17;
        } else if (one == 'I' && two == 'Q') {
            return (byte) - 18;
        } else if (one == 'I' && two == 'R') {
            return (byte) - 19;
        } else if (one == 'I' && two == 'S') {
            return (byte) - 20;
        } else if (one == 'I' && two == 'T') {
            return (byte) - 21;
        } else if (one == 'I' && two == 'V') {
            return (byte) - 22;
        } else if (one == 'I' && two == 'W') {
            return (byte) - 23;
        } else if (one == 'I' && two == 'X') {
            return (byte) - 24;
        } else if (one == 'I' && two == 'Y') {
            return (byte) - 25;
        } else if (one == 'K' && two == 'L') {
            return (byte) - 26;
        } else if (one == 'K' && two == 'M') {
            return (byte) - 27;
        } else if (one == 'K' && two == 'N') {
            return (byte) - 28;
        } else if (one == 'K' && two == 'P') {
            return (byte) - 29;
        } else if (one == 'K' && two == 'Q') {
            return (byte) - 30;
        } else if (one == 'K' && two == 'R') {
            return (byte) - 31;
        } else if (one == 'K' && two == 'S') {
            return (byte) - 32;
        } else if (one == 'K' && two == 'T') {
            return (byte) - 33;
        } else if (one == 'K' && two == 'V') {
            return (byte) - 34;
        } else if (one == 'K' && two == 'W') {
            return (byte) - 35;
        } else if (one == 'K' && two == 'X') {
            return (byte) - 36;
        } else if (one == 'K' && two == 'Y') {
            return (byte) - 37;
        } else if (one == 'L' && two == 'M') {
            return (byte) - 38;
        } else if (one == 'L' && two == 'N') {
            return (byte) - 39;
        } else if (one == 'L' && two == 'P') {
            return (byte) - 40;
        } else if (one == 'L' && two == 'Q') {
            return (byte) - 41;
        } else if (one == 'L' && two == 'R') {
            return (byte) - 42;
        } else if (one == 'L' && two == 'S') {
            return (byte) - 43;
        } else if (one == 'L' && two == 'T') {
            return (byte) - 44;
        } else if (one == 'L' && two == 'V') {
            return (byte) - 45;
        } else if (one == 'L' && two == 'W') {
            return (byte) - 46;
        } else if (one == 'L' && two == 'X') {
            return (byte) - 47;
        } else if (one == 'L' && two == 'Y') {
            return (byte) - 48;
        } else if (one == 'M' && two == 'N') {
            return (byte) - 49;
        } else if (one == 'M' && two == 'P') {
            return (byte) - 50;
        } else if (one == 'M' && two == 'Q') {
            return (byte) - 51;
        } else if (one == 'M' && two == 'R') {
            return (byte) - 52;
        } else if (one == 'M' && two == 'S') {
            return (byte) - 53;
        } else if (one == 'M' && two == 'T') {
            return (byte) - 54;
        } else if (one == 'M' && two == 'V') {
            return (byte) - 55;
        } else if (one == 'M' && two == 'W') {
            return (byte) - 56;
        } else if (one == 'M' && two == 'X') {
            return (byte) - 57;
        } else if (one == 'M' && two == 'Y') {
            return (byte) - 58;
        } else if (one == 'N' && two == 'P') {
            return (byte) - 59;
        } else if (one == 'N' && two == 'Q') {
            return (byte) - 60;
        } else if (one == 'N' && two == 'R') {
            return (byte) - 61;
        } else if (one == 'N' && two == 'S') {
            return (byte) - 62;
        } else if (one == 'N' && two == 'T') {
            return (byte) - 63;
        } else if (one == 'N' && two == 'V') {
            return (byte) - 64;
        } else if (one == 'N' && two == 'W') {
            return (byte) - 65;
        } else if (one == 'N' && two == 'X') {
            return (byte) - 66;
        } else if (one == 'N' && two == 'Y') {
            return (byte) - 67;
        } else if (one == 'P' && two == 'Q') {
            return (byte) - 68;
        } else if (one == 'P' && two == 'R') {
            return (byte) - 69;
        } else if (one == 'P' && two == 'S') {
            return (byte) - 70;
        } else if (one == 'P' && two == 'T') {
            return (byte) - 71;
        } else if (one == 'P' && two == 'V') {
            return (byte) - 72;
        } else if (one == 'P' && two == 'W') {
            return (byte) - 73;
        } else if (one == 'P' && two == 'X') {
            return (byte) - 74;
        } else if (one == 'P' && two == 'Y') {
            return (byte) - 75;
        } else if (one == 'Q' && two == 'R') {
            return (byte) - 76;
        } else if (one == 'Q' && two == 'S') {
            return (byte) - 77;
        } else if (one == 'Q' && two == 'T') {
            return (byte) - 78;
        } else if (one == 'Q' && two == 'V') {
            return (byte) - 79;
        } else if (one == 'Q' && two == 'W') {
            return (byte) - 80;
        } else if (one == 'Q' && two == 'X') {
            return (byte) - 81;
        } else if (one == 'Q' && two == 'Y') {
            return (byte) - 82;
        } else if (one == 'R' && two == 'S') {
            return (byte) - 83;
        } else if (one == 'R' && two == 'T') {
            return (byte) - 84;
        } else if (one == 'R' && two == 'V') {
            return (byte) - 85;
        } else if (one == 'R' && two == 'W') {
            return (byte) - 86;
        } else if (one == 'R' && two == 'X') {
            return (byte) - 87;
        } else if (one == 'R' && two == 'Y') {
            return (byte) - 88;
        } else if (one == 'S' && two == 'T') {
            return (byte) - 89;
        } else if (one == 'S' && two == 'V') {
            return (byte) - 90;
        } else if (one == 'S' && two == 'W') {
            return (byte) - 91;
        } else if (one == 'S' && two == 'X') {
            return (byte) - 92;
        } else if (one == 'S' && two == 'Y') {
            return (byte) - 93;
        } else if (one == 'T' && two == 'V') {
            return (byte) - 94;
        } else if (one == 'T' && two == 'W') {
            return (byte) - 95;
        } else if (one == 'T' && two == 'X') {
            return (byte) - 96;
        } else if (one == 'T' && two == 'Y') {
            return (byte) - 97;
        } else if (one == 'V' && two == 'W') {
            return (byte) - 98;
        } else if (one == 'V' && two == 'X') {
            return (byte) - 99;
        } else if (one == 'V' && two == 'Y') {
            return (byte) - 100;
        } else if (one == 'W' && two == 'X') {
            return (byte) - 101;
        } else if (one == 'W' && two == 'Y') {
            return (byte) - 102;
        } else if (one == 'X' && two == 'Y') {
            return (byte) - 103;
        }
        throw new RuntimeException("Should not reach here! " + one + " : " + two);
    }

    public final DcLetters getChars(final int i) {
        switch (i) {
            case 0:
                return new DcLetters('$', 'A');
            case 1:
                return new DcLetters('$', 'C');
            case 2:
                return new DcLetters('$', 'D');
            case 3:
                return new DcLetters('$', 'E');
            case 4:
                return new DcLetters('$', 'F');
            case 5:
                return new DcLetters('$', 'G');
            case 6:
                return new DcLetters('$', 'H');
            case 7:
                return new DcLetters('$', 'I');
            case 8:
                return new DcLetters('$', 'K');
            case 9:
                return new DcLetters('$', 'L');
            case 10:
                return new DcLetters('$', 'M');
            case 11:
                return new DcLetters('$', 'N');
            case 12:
                return new DcLetters('$', 'P');
            case 13:
                return new DcLetters('$', 'Q');
            case 14:
                return new DcLetters('$', 'R');
            case 15:
                return new DcLetters('$', 'S');
            case 16:
                return new DcLetters('$', 'T');
            case 17:
                return new DcLetters('$', 'V');
            case 18:
                return new DcLetters('$', 'W');
            case 19:
                return new DcLetters('$', 'X');
            case 20:
                return new DcLetters('$', 'Y');
            case 21:
                return new DcLetters('A', 'C');
            case 22:
                return new DcLetters('A', 'D');
            case 23:
                return new DcLetters('A', 'E');
            case 24:
                return new DcLetters('A', 'F');
            case 25:
                return new DcLetters('A', 'G');
            case 26:
                return new DcLetters('A', 'H');
            case 27:
                return new DcLetters('A', 'I');
            case 28:
                return new DcLetters('A', 'K');
            case 29:
                return new DcLetters('A', 'L');
            case 30:
                return new DcLetters('A', 'M');
            case 31:
                return new DcLetters('A', 'N');
            case 32:
                return new DcLetters('A', 'P');
            case 33:
                return new DcLetters('A', 'Q');
            case 34:
                return new DcLetters('A', 'R');
            case 35:
                return new DcLetters('A', 'S');
            case 36:
                return new DcLetters('A', 'T');
            case 37:
                return new DcLetters('A', 'V');
            case 38:
                return new DcLetters('A', 'W');
            case 39:
                return new DcLetters('A', 'X');
            case 40:
                return new DcLetters('A', 'Y');
            case 41:
                return new DcLetters('C', 'D');
            case 42:
                return new DcLetters('C', 'E');
            case 43:
                return new DcLetters('C', 'F');
            case 44:
                return new DcLetters('C', 'G');
            case 45:
                return new DcLetters('C', 'H');
            case 46:
                return new DcLetters('C', 'I');
            case 47:
                return new DcLetters('C', 'K');
            case 48:
                return new DcLetters('C', 'L');
            case 49:
                return new DcLetters('C', 'M');
            case 50:
                return new DcLetters('C', 'N');
            case 51:
                return new DcLetters('C', 'P');
            case 52:
                return new DcLetters('C', 'Q');
            case 53:
                return new DcLetters('C', 'R');
            case 54:
                return new DcLetters('C', 'S');
            case 55:
                return new DcLetters('C', 'T');
            case 56:
                return new DcLetters('C', 'V');
            case 57:
                return new DcLetters('C', 'W');
            case 58:
                return new DcLetters('C', 'X');
            case 59:
                return new DcLetters('C', 'Y');
            case 60:
                return new DcLetters('D', 'E');
            case 61:
                return new DcLetters('D', 'F');
            case 62:
                return new DcLetters('D', 'G');
            case 63:
                return new DcLetters('D', 'H');
            case 64:
                return new DcLetters('D', 'I');
            case 65:
                return new DcLetters('D', 'K');
            case 66:
                return new DcLetters('D', 'L');
            case 67:
                return new DcLetters('D', 'M');
            case 68:
                return new DcLetters('D', 'N');
            case 69:
                return new DcLetters('D', 'P');
            case 70:
                return new DcLetters('D', 'Q');
            case 71:
                return new DcLetters('D', 'R');
            case 72:
                return new DcLetters('D', 'S');
            case 73:
                return new DcLetters('D', 'T');
            case 74:
                return new DcLetters('D', 'V');
            case 75:
                return new DcLetters('D', 'W');
            case 76:
                return new DcLetters('D', 'X');
            case 77:
                return new DcLetters('D', 'Y');
            case 78:
                return new DcLetters('E', 'F');
            case 79:
                return new DcLetters('E', 'G');
            case 80:
                return new DcLetters('E', 'H');
            case 81:
                return new DcLetters('E', 'I');
            case 82:
                return new DcLetters('E', 'K');
            case 83:
                return new DcLetters('E', 'L');
            case 84:
                return new DcLetters('E', 'M');
            case 85:
                return new DcLetters('E', 'N');
            case 86:
                return new DcLetters('E', 'P');
            case 87:
                return new DcLetters('E', 'Q');
            case 88:
                return new DcLetters('E', 'R');
            case 89:
                return new DcLetters('E', 'S');
            case 90:
                return new DcLetters('E', 'T');
            case 91:
                return new DcLetters('E', 'V');
            case 92:
                return new DcLetters('E', 'W');
            case 93:
                return new DcLetters('E', 'X');
            case 94:
                return new DcLetters('E', 'Y');
            case 95:
                return new DcLetters('F', 'G');
            case 96:
                return new DcLetters('F', 'H');
            case 97:
                return new DcLetters('F', 'I');
            case 98:
                return new DcLetters('F', 'K');
            case 99:
                return new DcLetters('F', 'L');
            case 100:
                return new DcLetters('F', 'M');
            case 101:
                return new DcLetters('F', 'N');
            case 102:
                return new DcLetters('F', 'P');
            case 103:
                return new DcLetters('F', 'Q');
            case 104:
                return new DcLetters('F', 'R');
            case 105:
                return new DcLetters('F', 'S');
            case 106:
                return new DcLetters('F', 'T');
            case 107:
                return new DcLetters('F', 'V');
            case 108:
                return new DcLetters('F', 'W');
            case 109:
                return new DcLetters('F', 'X');
            case 110:
                return new DcLetters('F', 'Y');
            case 111:
                return new DcLetters('G', 'H');
            case 112:
                return new DcLetters('G', 'I');
            case 113:
                return new DcLetters('G', 'K');
            case 114:
                return new DcLetters('G', 'L');
            case 115:
                return new DcLetters('G', 'M');
            case 116:
                return new DcLetters('G', 'N');
            case 117:
                return new DcLetters('G', 'P');
            case 118:
                return new DcLetters('G', 'Q');
            case 119:
                return new DcLetters('G', 'R');
            case 120:
                return new DcLetters('G', 'S');
            case 121:
                return new DcLetters('G', 'T');
            case 122:
                return new DcLetters('G', 'V');
            case 123:
                return new DcLetters('G', 'W');
            case 124:
                return new DcLetters('G', 'X');
            case 125:
                return new DcLetters('G', 'Y');
            case 126:
                return new DcLetters('H', 'I');
            case 127:
                return new DcLetters('H', 'K');
            case - 1:
                return new DcLetters('H', 'L');
            case - 2:
                return new DcLetters('H', 'M');
            case - 3:
                return new DcLetters('H', 'N');
            case - 4:
                return new DcLetters('H', 'P');
            case - 5:
                return new DcLetters('H', 'Q');
            case - 6:
                return new DcLetters('H', 'R');
            case - 7:
                return new DcLetters('H', 'S');
            case - 8:
                return new DcLetters('H', 'T');
            case - 9:
                return new DcLetters('H', 'V');
            case - 10:
                return new DcLetters('H', 'W');
            case - 11:
                return new DcLetters('H', 'X');
            case - 12:
                return new DcLetters('H', 'Y');
            case - 13:
                return new DcLetters('I', 'K');
            case - 14:
                return new DcLetters('I', 'L');
            case - 15:
                return new DcLetters('I', 'M');
            case - 16:
                return new DcLetters('I', 'N');
            case - 17:
                return new DcLetters('I', 'P');
            case - 18:
                return new DcLetters('I', 'Q');
            case - 19:
                return new DcLetters('I', 'R');
            case - 20:
                return new DcLetters('I', 'S');
            case - 21:
                return new DcLetters('I', 'T');
            case - 22:
                return new DcLetters('I', 'V');
            case - 23:
                return new DcLetters('I', 'W');
            case - 24:
                return new DcLetters('I', 'X');
            case - 25:
                return new DcLetters('I', 'Y');
            case - 26:
                return new DcLetters('K', 'L');
            case - 27:
                return new DcLetters('K', 'M');
            case - 28:
                return new DcLetters('K', 'N');
            case - 29:
                return new DcLetters('K', 'P');
            case - 30:
                return new DcLetters('K', 'Q');
            case - 31:
                return new DcLetters('K', 'R');
            case - 32:
                return new DcLetters('K', 'S');
            case - 33:
                return new DcLetters('K', 'T');
            case - 34:
                return new DcLetters('K', 'V');
            case - 35:
                return new DcLetters('K', 'W');
            case - 36:
                return new DcLetters('K', 'X');
            case - 37:
                return new DcLetters('K', 'Y');
            case - 38:
                return new DcLetters('L', 'M');
            case - 39:
                return new DcLetters('L', 'N');
            case - 40:
                return new DcLetters('L', 'P');
            case - 41:
                return new DcLetters('L', 'Q');
            case - 42:
                return new DcLetters('L', 'R');
            case - 43:
                return new DcLetters('L', 'S');
            case - 44:
                return new DcLetters('L', 'T');
            case - 45:
                return new DcLetters('L', 'V');
            case - 46:
                return new DcLetters('L', 'W');
            case - 47:
                return new DcLetters('L', 'X');
            case - 48:
                return new DcLetters('L', 'Y');
            case - 49:
                return new DcLetters('M', 'N');
            case - 50:
                return new DcLetters('M', 'P');
            case - 51:
                return new DcLetters('M', 'Q');
            case - 52:
                return new DcLetters('M', 'R');
            case - 53:
                return new DcLetters('M', 'S');
            case - 54:
                return new DcLetters('M', 'T');
            case - 55:
                return new DcLetters('M', 'V');
            case - 56:
                return new DcLetters('M', 'W');
            case - 57:
                return new DcLetters('M', 'X');
            case - 58:
                return new DcLetters('M', 'Y');
            case - 59:
                return new DcLetters('N', 'P');
            case - 60:
                return new DcLetters('N', 'Q');
            case - 61:
                return new DcLetters('N', 'R');
            case - 62:
                return new DcLetters('N', 'S');
            case - 63:
                return new DcLetters('N', 'T');
            case - 64:
                return new DcLetters('N', 'V');
            case - 65:
                return new DcLetters('N', 'W');
            case - 66:
                return new DcLetters('N', 'X');
            case - 67:
                return new DcLetters('N', 'Y');
            case - 68:
                return new DcLetters('P', 'Q');
            case - 69:
                return new DcLetters('P', 'R');
            case - 70:
                return new DcLetters('P', 'S');
            case - 71:
                return new DcLetters('P', 'T');
            case - 72:
                return new DcLetters('P', 'V');
            case - 73:
                return new DcLetters('P', 'W');
            case - 74:
                return new DcLetters('P', 'X');
            case - 75:
                return new DcLetters('P', 'Y');
            case - 76:
                return new DcLetters('Q', 'R');
            case - 77:
                return new DcLetters('Q', 'S');
            case - 78:
                return new DcLetters('Q', 'T');
            case - 79:
                return new DcLetters('Q', 'V');
            case - 80:
                return new DcLetters('Q', 'W');
            case - 81:
                return new DcLetters('Q', 'X');
            case - 82:
                return new DcLetters('Q', 'Y');
            case - 83:
                return new DcLetters('R', 'S');
            case - 84:
                return new DcLetters('R', 'T');
            case - 85:
                return new DcLetters('R', 'V');
            case - 86:
                return new DcLetters('R', 'W');
            case - 87:
                return new DcLetters('R', 'X');
            case - 88:
                return new DcLetters('R', 'Y');
            case - 89:
                return new DcLetters('S', 'T');
            case - 90:
                return new DcLetters('S', 'V');
            case - 91:
                return new DcLetters('S', 'W');
            case - 92:
                return new DcLetters('S', 'X');
            case - 93:
                return new DcLetters('S', 'Y');
            case - 94:
                return new DcLetters('T', 'V');
            case - 95:
                return new DcLetters('T', 'W');
            case - 96:
                return new DcLetters('T', 'X');
            case - 97:
                return new DcLetters('T', 'Y');
            case - 98:
                return new DcLetters('V', 'W');
            case - 99:
                return new DcLetters('V', 'X');
            case - 100:
                return new DcLetters('V', 'Y');
            case - 101:
                return new DcLetters('W', 'X');
            case - 102:
                return new DcLetters('W', 'Y');
            case - 103:
                return new DcLetters('X', 'Y');
            default:
                throw new RuntimeException("Should not reach here");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.dcs.length; i = i + 1) {
            if (i == this.dcs.length - 1) {
                sb.append(this.getChars(this.dcs[i]).one + "" + this.getChars(this.dcs[i]).two);
                sb.append("]");
            } else {
                sb.append(this.getChars(this.dcs[i]).one + "" + this.getChars(this.dcs[i]).two + ", ");
            }
        }

        return sb.toString();
    }

}
