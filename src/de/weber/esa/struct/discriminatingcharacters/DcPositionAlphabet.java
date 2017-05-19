package de.weber.esa.struct.discriminatingcharacters;

import de.weber.esa.struct.EnhancedSuffixArray;

/**
 * Created by Stefan on 18.05.2017.
 */
public class DcPositionAlphabet {

    public final short[] dcs;

    public DcPositionAlphabet(final EnhancedSuffixArray esa) {
        this.dcs = new short[esa.length - 1];

        for (int i = 1; i < esa.lcp.length; i = i + 1) {
            final char one = esa.sequence[esa.suffices[i - 1] + esa.lcp.getCurrentLcpValue(i)];
            final char two = esa.sequence[esa.suffices[i] + esa.lcp.getCurrentLcpValue(i)];

            this.dcs[i - 1] = this.getCurrent(one, two);
        }
    }

    private short getCurrent(final char one, final char two) {
        if (one == '$' && two == 'A') {
            return (short) 0;
        } else if (one == '$' && two == 'B') {
            return (short) 1;
        } else if (one == '$' && two == 'C') {
            return (short) 2;
        } else if (one == '$' && two == 'D') {
            return (short) 3;
        } else if (one == '$' && two == 'E') {
            return (short) 4;
        } else if (one == '$' && two == 'F') {
            return (short) 5;
        } else if (one == '$' && two == 'G') {
            return (short) 6;
        } else if (one == '$' && two == 'H') {
            return (short) 7;
        } else if (one == '$' && two == 'I') {
            return (short) 8;
        } else if (one == '$' && two == 'J') {
            return (short) 9;
        } else if (one == '$' && two == 'K') {
            return (short) 10;
        } else if (one == '$' && two == 'L') {
            return (short) 11;
        } else if (one == '$' && two == 'M') {
            return (short) 12;
        } else if (one == '$' && two == 'N') {
            return (short) 13;
        } else if (one == '$' && two == 'O') {
            return (short) 14;
        } else if (one == '$' && two == 'P') {
            return (short) 15;
        } else if (one == '$' && two == 'Q') {
            return (short) 16;
        } else if (one == '$' && two == 'R') {
            return (short) 17;
        } else if (one == '$' && two == 'S') {
            return (short) 18;
        } else if (one == '$' && two == 'T') {
            return (short) 19;
        } else if (one == '$' && two == 'U') {
            return (short) 20;
        } else if (one == '$' && two == 'V') {
            return (short) 21;
        } else if (one == '$' && two == 'W') {
            return (short) 22;
        } else if (one == '$' && two == 'X') {
            return (short) 23;
        } else if (one == '$' && two == 'Y') {
            return (short) 24;
        } else if (one == '$' && two == 'Z') {
            return (short) 25;
        } else if (one == 'A' && two == 'B') {
            return (short) 26;
        } else if (one == 'A' && two == 'C') {
            return (short) 27;
        } else if (one == 'A' && two == 'D') {
            return (short) 28;
        } else if (one == 'A' && two == 'E') {
            return (short) 29;
        } else if (one == 'A' && two == 'F') {
            return (short) 30;
        } else if (one == 'A' && two == 'G') {
            return (short) 31;
        } else if (one == 'A' && two == 'H') {
            return (short) 32;
        } else if (one == 'A' && two == 'I') {
            return (short) 33;
        } else if (one == 'A' && two == 'J') {
            return (short) 34;
        } else if (one == 'A' && two == 'K') {
            return (short) 35;
        } else if (one == 'A' && two == 'L') {
            return (short) 36;
        } else if (one == 'A' && two == 'M') {
            return (short) 37;
        } else if (one == 'A' && two == 'N') {
            return (short) 38;
        } else if (one == 'A' && two == 'O') {
            return (short) 39;
        } else if (one == 'A' && two == 'P') {
            return (short) 40;
        } else if (one == 'A' && two == 'Q') {
            return (short) 41;
        } else if (one == 'A' && two == 'R') {
            return (short) 42;
        } else if (one == 'A' && two == 'S') {
            return (short) 43;
        } else if (one == 'A' && two == 'T') {
            return (short) 44;
        } else if (one == 'A' && two == 'U') {
            return (short) 45;
        } else if (one == 'A' && two == 'V') {
            return (short) 46;
        } else if (one == 'A' && two == 'W') {
            return (short) 47;
        } else if (one == 'A' && two == 'X') {
            return (short) 48;
        } else if (one == 'A' && two == 'Y') {
            return (short) 49;
        } else if (one == 'A' && two == 'Z') {
            return (short) 50;
        } else if (one == 'B' && two == 'C') {
            return (short) 51;
        } else if (one == 'B' && two == 'D') {
            return (short) 52;
        } else if (one == 'B' && two == 'E') {
            return (short) 53;
        } else if (one == 'B' && two == 'F') {
            return (short) 54;
        } else if (one == 'B' && two == 'G') {
            return (short) 55;
        } else if (one == 'B' && two == 'H') {
            return (short) 56;
        } else if (one == 'B' && two == 'I') {
            return (short) 57;
        } else if (one == 'B' && two == 'J') {
            return (short) 58;
        } else if (one == 'B' && two == 'K') {
            return (short) 59;
        } else if (one == 'B' && two == 'L') {
            return (short) 60;
        } else if (one == 'B' && two == 'M') {
            return (short) 61;
        } else if (one == 'B' && two == 'N') {
            return (short) 62;
        } else if (one == 'B' && two == 'O') {
            return (short) 63;
        } else if (one == 'B' && two == 'P') {
            return (short) 64;
        } else if (one == 'B' && two == 'Q') {
            return (short) 65;
        } else if (one == 'B' && two == 'R') {
            return (short) 66;
        } else if (one == 'B' && two == 'S') {
            return (short) 67;
        } else if (one == 'B' && two == 'T') {
            return (short) 68;
        } else if (one == 'B' && two == 'U') {
            return (short) 69;
        } else if (one == 'B' && two == 'V') {
            return (short) 70;
        } else if (one == 'B' && two == 'W') {
            return (short) 71;
        } else if (one == 'B' && two == 'X') {
            return (short) 72;
        } else if (one == 'B' && two == 'Y') {
            return (short) 73;
        } else if (one == 'B' && two == 'Z') {
            return (short) 74;
        } else if (one == 'C' && two == 'D') {
            return (short) 75;
        } else if (one == 'C' && two == 'E') {
            return (short) 76;
        } else if (one == 'C' && two == 'F') {
            return (short) 77;
        } else if (one == 'C' && two == 'G') {
            return (short) 78;
        } else if (one == 'C' && two == 'H') {
            return (short) 79;
        } else if (one == 'C' && two == 'I') {
            return (short) 80;
        } else if (one == 'C' && two == 'J') {
            return (short) 81;
        } else if (one == 'C' && two == 'K') {
            return (short) 82;
        } else if (one == 'C' && two == 'L') {
            return (short) 83;
        } else if (one == 'C' && two == 'M') {
            return (short) 84;
        } else if (one == 'C' && two == 'N') {
            return (short) 85;
        } else if (one == 'C' && two == 'O') {
            return (short) 86;
        } else if (one == 'C' && two == 'P') {
            return (short) 87;
        } else if (one == 'C' && two == 'Q') {
            return (short) 88;
        } else if (one == 'C' && two == 'R') {
            return (short) 89;
        } else if (one == 'C' && two == 'S') {
            return (short) 90;
        } else if (one == 'C' && two == 'T') {
            return (short) 91;
        } else if (one == 'C' && two == 'U') {
            return (short) 92;
        } else if (one == 'C' && two == 'V') {
            return (short) 93;
        } else if (one == 'C' && two == 'W') {
            return (short) 94;
        } else if (one == 'C' && two == 'X') {
            return (short) 95;
        } else if (one == 'C' && two == 'Y') {
            return (short) 96;
        } else if (one == 'C' && two == 'Z') {
            return (short) 97;
        } else if (one == 'D' && two == 'E') {
            return (short) 98;
        } else if (one == 'D' && two == 'F') {
            return (short) 99;
        } else if (one == 'D' && two == 'G') {
            return (short) 100;
        } else if (one == 'D' && two == 'H') {
            return (short) 101;
        } else if (one == 'D' && two == 'I') {
            return (short) 102;
        } else if (one == 'D' && two == 'J') {
            return (short) 103;
        } else if (one == 'D' && two == 'K') {
            return (short) 104;
        } else if (one == 'D' && two == 'L') {
            return (short) 105;
        } else if (one == 'D' && two == 'M') {
            return (short) 106;
        } else if (one == 'D' && two == 'N') {
            return (short) 107;
        } else if (one == 'D' && two == 'O') {
            return (short) 108;
        } else if (one == 'D' && two == 'P') {
            return (short) 109;
        } else if (one == 'D' && two == 'Q') {
            return (short) 110;
        } else if (one == 'D' && two == 'R') {
            return (short) 111;
        } else if (one == 'D' && two == 'S') {
            return (short) 112;
        } else if (one == 'D' && two == 'T') {
            return (short) 113;
        } else if (one == 'D' && two == 'U') {
            return (short) 114;
        } else if (one == 'D' && two == 'V') {
            return (short) 115;
        } else if (one == 'D' && two == 'W') {
            return (short) 116;
        } else if (one == 'D' && two == 'X') {
            return (short) 117;
        } else if (one == 'D' && two == 'Y') {
            return (short) 118;
        } else if (one == 'D' && two == 'Z') {
            return (short) 119;
        } else if (one == 'E' && two == 'F') {
            return (short) 120;
        } else if (one == 'E' && two == 'G') {
            return (short) 121;
        } else if (one == 'E' && two == 'H') {
            return (short) 122;
        } else if (one == 'E' && two == 'I') {
            return (short) 123;
        } else if (one == 'E' && two == 'J') {
            return (short) 124;
        } else if (one == 'E' && two == 'K') {
            return (short) 125;
        } else if (one == 'E' && two == 'L') {
            return (short) 126;
        } else if (one == 'E' && two == 'M') {
            return (short) 127;
        } else if (one == 'E' && two == 'N') {
            return (short) 128;
        } else if (one == 'E' && two == 'O') {
            return (short) 129;
        } else if (one == 'E' && two == 'P') {
            return (short) 130;
        } else if (one == 'E' && two == 'Q') {
            return (short) 131;
        } else if (one == 'E' && two == 'R') {
            return (short) 132;
        } else if (one == 'E' && two == 'S') {
            return (short) 133;
        } else if (one == 'E' && two == 'T') {
            return (short) 134;
        } else if (one == 'E' && two == 'U') {
            return (short) 135;
        } else if (one == 'E' && two == 'V') {
            return (short) 136;
        } else if (one == 'E' && two == 'W') {
            return (short) 137;
        } else if (one == 'E' && two == 'X') {
            return (short) 138;
        } else if (one == 'E' && two == 'Y') {
            return (short) 139;
        } else if (one == 'E' && two == 'Z') {
            return (short) 140;
        } else if (one == 'F' && two == 'G') {
            return (short) 141;
        } else if (one == 'F' && two == 'H') {
            return (short) 142;
        } else if (one == 'F' && two == 'I') {
            return (short) 143;
        } else if (one == 'F' && two == 'J') {
            return (short) 144;
        } else if (one == 'F' && two == 'K') {
            return (short) 145;
        } else if (one == 'F' && two == 'L') {
            return (short) 146;
        } else if (one == 'F' && two == 'M') {
            return (short) 147;
        } else if (one == 'F' && two == 'N') {
            return (short) 148;
        } else if (one == 'F' && two == 'O') {
            return (short) 149;
        } else if (one == 'F' && two == 'P') {
            return (short) 150;
        } else if (one == 'F' && two == 'Q') {
            return (short) 151;
        } else if (one == 'F' && two == 'R') {
            return (short) 152;
        } else if (one == 'F' && two == 'S') {
            return (short) 153;
        } else if (one == 'F' && two == 'T') {
            return (short) 154;
        } else if (one == 'F' && two == 'U') {
            return (short) 155;
        } else if (one == 'F' && two == 'V') {
            return (short) 156;
        } else if (one == 'F' && two == 'W') {
            return (short) 157;
        } else if (one == 'F' && two == 'X') {
            return (short) 158;
        } else if (one == 'F' && two == 'Y') {
            return (short) 159;
        } else if (one == 'F' && two == 'Z') {
            return (short) 160;
        } else if (one == 'G' && two == 'H') {
            return (short) 161;
        } else if (one == 'G' && two == 'I') {
            return (short) 162;
        } else if (one == 'G' && two == 'J') {
            return (short) 163;
        } else if (one == 'G' && two == 'K') {
            return (short) 164;
        } else if (one == 'G' && two == 'L') {
            return (short) 165;
        } else if (one == 'G' && two == 'M') {
            return (short) 166;
        } else if (one == 'G' && two == 'N') {
            return (short) 167;
        } else if (one == 'G' && two == 'O') {
            return (short) 168;
        } else if (one == 'G' && two == 'P') {
            return (short) 169;
        } else if (one == 'G' && two == 'Q') {
            return (short) 170;
        } else if (one == 'G' && two == 'R') {
            return (short) 171;
        } else if (one == 'G' && two == 'S') {
            return (short) 172;
        } else if (one == 'G' && two == 'T') {
            return (short) 173;
        } else if (one == 'G' && two == 'U') {
            return (short) 174;
        } else if (one == 'G' && two == 'V') {
            return (short) 175;
        } else if (one == 'G' && two == 'W') {
            return (short) 176;
        } else if (one == 'G' && two == 'X') {
            return (short) 177;
        } else if (one == 'G' && two == 'Y') {
            return (short) 178;
        } else if (one == 'G' && two == 'Z') {
            return (short) 179;
        } else if (one == 'H' && two == 'I') {
            return (short) 180;
        } else if (one == 'H' && two == 'J') {
            return (short) 181;
        } else if (one == 'H' && two == 'K') {
            return (short) 182;
        } else if (one == 'H' && two == 'L') {
            return (short) 183;
        } else if (one == 'H' && two == 'M') {
            return (short) 184;
        } else if (one == 'H' && two == 'N') {
            return (short) 185;
        } else if (one == 'H' && two == 'O') {
            return (short) 186;
        } else if (one == 'H' && two == 'P') {
            return (short) 187;
        } else if (one == 'H' && two == 'Q') {
            return (short) 188;
        } else if (one == 'H' && two == 'R') {
            return (short) 189;
        } else if (one == 'H' && two == 'S') {
            return (short) 190;
        } else if (one == 'H' && two == 'T') {
            return (short) 191;
        } else if (one == 'H' && two == 'U') {
            return (short) 192;
        } else if (one == 'H' && two == 'V') {
            return (short) 193;
        } else if (one == 'H' && two == 'W') {
            return (short) 194;
        } else if (one == 'H' && two == 'X') {
            return (short) 195;
        } else if (one == 'H' && two == 'Y') {
            return (short) 196;
        } else if (one == 'H' && two == 'Z') {
            return (short) 197;
        } else if (one == 'I' && two == 'J') {
            return (short) 198;
        } else if (one == 'I' && two == 'K') {
            return (short) 199;
        } else if (one == 'I' && two == 'L') {
            return (short) 200;
        } else if (one == 'I' && two == 'M') {
            return (short) 201;
        } else if (one == 'I' && two == 'N') {
            return (short) 202;
        } else if (one == 'I' && two == 'O') {
            return (short) 203;
        } else if (one == 'I' && two == 'P') {
            return (short) 204;
        } else if (one == 'I' && two == 'Q') {
            return (short) 205;
        } else if (one == 'I' && two == 'R') {
            return (short) 206;
        } else if (one == 'I' && two == 'S') {
            return (short) 207;
        } else if (one == 'I' && two == 'T') {
            return (short) 208;
        } else if (one == 'I' && two == 'U') {
            return (short) 209;
        } else if (one == 'I' && two == 'V') {
            return (short) 210;
        } else if (one == 'I' && two == 'W') {
            return (short) 211;
        } else if (one == 'I' && two == 'X') {
            return (short) 212;
        } else if (one == 'I' && two == 'Y') {
            return (short) 213;
        } else if (one == 'I' && two == 'Z') {
            return (short) 214;
        } else if (one == 'J' && two == 'K') {
            return (short) 215;
        } else if (one == 'J' && two == 'L') {
            return (short) 216;
        } else if (one == 'J' && two == 'M') {
            return (short) 217;
        } else if (one == 'J' && two == 'N') {
            return (short) 218;
        } else if (one == 'J' && two == 'O') {
            return (short) 219;
        } else if (one == 'J' && two == 'P') {
            return (short) 220;
        } else if (one == 'J' && two == 'Q') {
            return (short) 221;
        } else if (one == 'J' && two == 'R') {
            return (short) 222;
        } else if (one == 'J' && two == 'S') {
            return (short) 223;
        } else if (one == 'J' && two == 'T') {
            return (short) 224;
        } else if (one == 'J' && two == 'U') {
            return (short) 225;
        } else if (one == 'J' && two == 'V') {
            return (short) 226;
        } else if (one == 'J' && two == 'W') {
            return (short) 227;
        } else if (one == 'J' && two == 'X') {
            return (short) 228;
        } else if (one == 'J' && two == 'Y') {
            return (short) 229;
        } else if (one == 'J' && two == 'Z') {
            return (short) 230;
        } else if (one == 'K' && two == 'L') {
            return (short) 231;
        } else if (one == 'K' && two == 'M') {
            return (short) 232;
        } else if (one == 'K' && two == 'N') {
            return (short) 233;
        } else if (one == 'K' && two == 'O') {
            return (short) 234;
        } else if (one == 'K' && two == 'P') {
            return (short) 235;
        } else if (one == 'K' && two == 'Q') {
            return (short) 236;
        } else if (one == 'K' && two == 'R') {
            return (short) 237;
        } else if (one == 'K' && two == 'S') {
            return (short) 238;
        } else if (one == 'K' && two == 'T') {
            return (short) 239;
        } else if (one == 'K' && two == 'U') {
            return (short) 240;
        } else if (one == 'K' && two == 'V') {
            return (short) 241;
        } else if (one == 'K' && two == 'W') {
            return (short) 242;
        } else if (one == 'K' && two == 'X') {
            return (short) 243;
        } else if (one == 'K' && two == 'Y') {
            return (short) 244;
        } else if (one == 'K' && two == 'Z') {
            return (short) 245;
        } else if (one == 'L' && two == 'M') {
            return (short) 246;
        } else if (one == 'L' && two == 'N') {
            return (short) 247;
        } else if (one == 'L' && two == 'O') {
            return (short) 248;
        } else if (one == 'L' && two == 'P') {
            return (short) 249;
        } else if (one == 'L' && two == 'Q') {
            return (short) 250;
        } else if (one == 'L' && two == 'R') {
            return (short) 251;
        } else if (one == 'L' && two == 'S') {
            return (short) 252;
        } else if (one == 'L' && two == 'T') {
            return (short) 253;
        } else if (one == 'L' && two == 'U') {
            return (short) 254;
        } else if (one == 'L' && two == 'V') {
            return (short) 255;
        } else if (one == 'L' && two == 'W') {
            return (short) 256;
        } else if (one == 'L' && two == 'X') {
            return (short) 257;
        } else if (one == 'L' && two == 'Y') {
            return (short) 258;
        } else if (one == 'L' && two == 'Z') {
            return (short) 259;
        } else if (one == 'M' && two == 'N') {
            return (short) 260;
        } else if (one == 'M' && two == 'O') {
            return (short) 261;
        } else if (one == 'M' && two == 'P') {
            return (short) 262;
        } else if (one == 'M' && two == 'Q') {
            return (short) 263;
        } else if (one == 'M' && two == 'R') {
            return (short) 264;
        } else if (one == 'M' && two == 'S') {
            return (short) 265;
        } else if (one == 'M' && two == 'T') {
            return (short) 266;
        } else if (one == 'M' && two == 'U') {
            return (short) 267;
        } else if (one == 'M' && two == 'V') {
            return (short) 268;
        } else if (one == 'M' && two == 'W') {
            return (short) 269;
        } else if (one == 'M' && two == 'X') {
            return (short) 270;
        } else if (one == 'M' && two == 'Y') {
            return (short) 271;
        } else if (one == 'M' && two == 'Z') {
            return (short) 272;
        } else if (one == 'N' && two == 'O') {
            return (short) 273;
        } else if (one == 'N' && two == 'P') {
            return (short) 274;
        } else if (one == 'N' && two == 'Q') {
            return (short) 275;
        } else if (one == 'N' && two == 'R') {
            return (short) 276;
        } else if (one == 'N' && two == 'S') {
            return (short) 277;
        } else if (one == 'N' && two == 'T') {
            return (short) 278;
        } else if (one == 'N' && two == 'U') {
            return (short) 279;
        } else if (one == 'N' && two == 'V') {
            return (short) 280;
        } else if (one == 'N' && two == 'W') {
            return (short) 281;
        } else if (one == 'N' && two == 'X') {
            return (short) 282;
        } else if (one == 'N' && two == 'Y') {
            return (short) 283;
        } else if (one == 'N' && two == 'Z') {
            return (short) 284;
        } else if (one == 'O' && two == 'P') {
            return (short) 285;
        } else if (one == 'O' && two == 'Q') {
            return (short) 286;
        } else if (one == 'O' && two == 'R') {
            return (short) 287;
        } else if (one == 'O' && two == 'S') {
            return (short) 288;
        } else if (one == 'O' && two == 'T') {
            return (short) 289;
        } else if (one == 'O' && two == 'U') {
            return (short) 290;
        } else if (one == 'O' && two == 'V') {
            return (short) 291;
        } else if (one == 'O' && two == 'W') {
            return (short) 292;
        } else if (one == 'O' && two == 'X') {
            return (short) 293;
        } else if (one == 'O' && two == 'Y') {
            return (short) 294;
        } else if (one == 'O' && two == 'Z') {
            return (short) 295;
        } else if (one == 'P' && two == 'Q') {
            return (short) 296;
        } else if (one == 'P' && two == 'R') {
            return (short) 297;
        } else if (one == 'P' && two == 'S') {
            return (short) 298;
        } else if (one == 'P' && two == 'T') {
            return (short) 299;
        } else if (one == 'P' && two == 'U') {
            return (short) 300;
        } else if (one == 'P' && two == 'V') {
            return (short) 301;
        } else if (one == 'P' && two == 'W') {
            return (short) 302;
        } else if (one == 'P' && two == 'X') {
            return (short) 303;
        } else if (one == 'P' && two == 'Y') {
            return (short) 304;
        } else if (one == 'P' && two == 'Z') {
            return (short) 305;
        } else if (one == 'Q' && two == 'R') {
            return (short) 306;
        } else if (one == 'Q' && two == 'S') {
            return (short) 307;
        } else if (one == 'Q' && two == 'T') {
            return (short) 308;
        } else if (one == 'Q' && two == 'U') {
            return (short) 309;
        } else if (one == 'Q' && two == 'V') {
            return (short) 310;
        } else if (one == 'Q' && two == 'W') {
            return (short) 311;
        } else if (one == 'Q' && two == 'X') {
            return (short) 312;
        } else if (one == 'Q' && two == 'Y') {
            return (short) 313;
        } else if (one == 'Q' && two == 'Z') {
            return (short) 314;
        } else if (one == 'R' && two == 'S') {
            return (short) 315;
        } else if (one == 'R' && two == 'T') {
            return (short) 316;
        } else if (one == 'R' && two == 'U') {
            return (short) 317;
        } else if (one == 'R' && two == 'V') {
            return (short) 318;
        } else if (one == 'R' && two == 'W') {
            return (short) 319;
        } else if (one == 'R' && two == 'X') {
            return (short) 320;
        } else if (one == 'R' && two == 'Y') {
            return (short) 321;
        } else if (one == 'R' && two == 'Z') {
            return (short) 322;
        } else if (one == 'S' && two == 'T') {
            return (short) 323;
        } else if (one == 'S' && two == 'U') {
            return (short) 324;
        } else if (one == 'S' && two == 'V') {
            return (short) 325;
        } else if (one == 'S' && two == 'W') {
            return (short) 326;
        } else if (one == 'S' && two == 'X') {
            return (short) 327;
        } else if (one == 'S' && two == 'Y') {
            return (short) 328;
        } else if (one == 'S' && two == 'Z') {
            return (short) 329;
        } else if (one == 'T' && two == 'U') {
            return (short) 330;
        } else if (one == 'T' && two == 'V') {
            return (short) 331;
        } else if (one == 'T' && two == 'W') {
            return (short) 332;
        } else if (one == 'T' && two == 'X') {
            return (short) 333;
        } else if (one == 'T' && two == 'Y') {
            return (short) 334;
        } else if (one == 'T' && two == 'Z') {
            return (short) 335;
        } else if (one == 'U' && two == 'V') {
            return (short) 336;
        } else if (one == 'U' && two == 'W') {
            return (short) 337;
        } else if (one == 'U' && two == 'X') {
            return (short) 338;
        } else if (one == 'U' && two == 'Y') {
            return (short) 339;
        } else if (one == 'U' && two == 'Z') {
            return (short) 340;
        } else if (one == 'V' && two == 'W') {
            return (short) 341;
        } else if (one == 'V' && two == 'X') {
            return (short) 342;
        } else if (one == 'V' && two == 'Y') {
            return (short) 343;
        } else if (one == 'V' && two == 'Z') {
            return (short) 344;
        } else if (one == 'W' && two == 'X') {
            return (short) 345;
        } else if (one == 'W' && two == 'Y') {
            return (short) 346;
        } else if (one == 'W' && two == 'Z') {
            return (short) 347;
        } else if (one == 'X' && two == 'Y') {
            return (short) 348;
        } else if (one == 'X' && two == 'Z') {
            return (short) 349;
        } else if (one == 'Y' && two == 'Z') {
            return (short) 350;
        }
        throw new RuntimeException("Should not reach here! " + one + " : " + two);
    }

    public final DcLetters getChars(final int i) {
        switch (i) {
            case 0:
                return new DcLetters('$', 'A');
            case 1:
                return new DcLetters('$', 'B');
            case 2:
                return new DcLetters('$', 'C');
            case 3:
                return new DcLetters('$', 'D');
            case 4:
                return new DcLetters('$', 'E');
            case 5:
                return new DcLetters('$', 'F');
            case 6:
                return new DcLetters('$', 'G');
            case 7:
                return new DcLetters('$', 'H');
            case 8:
                return new DcLetters('$', 'I');
            case 9:
                return new DcLetters('$', 'J');
            case 10:
                return new DcLetters('$', 'K');
            case 11:
                return new DcLetters('$', 'L');
            case 12:
                return new DcLetters('$', 'M');
            case 13:
                return new DcLetters('$', 'N');
            case 14:
                return new DcLetters('$', 'O');
            case 15:
                return new DcLetters('$', 'P');
            case 16:
                return new DcLetters('$', 'Q');
            case 17:
                return new DcLetters('$', 'R');
            case 18:
                return new DcLetters('$', 'S');
            case 19:
                return new DcLetters('$', 'T');
            case 20:
                return new DcLetters('$', 'U');
            case 21:
                return new DcLetters('$', 'V');
            case 22:
                return new DcLetters('$', 'W');
            case 23:
                return new DcLetters('$', 'X');
            case 24:
                return new DcLetters('$', 'Y');
            case 25:
                return new DcLetters('$', 'Z');
            case 26:
                return new DcLetters('A', 'B');
            case 27:
                return new DcLetters('A', 'C');
            case 28:
                return new DcLetters('A', 'D');
            case 29:
                return new DcLetters('A', 'E');
            case 30:
                return new DcLetters('A', 'F');
            case 31:
                return new DcLetters('A', 'G');
            case 32:
                return new DcLetters('A', 'H');
            case 33:
                return new DcLetters('A', 'I');
            case 34:
                return new DcLetters('A', 'J');
            case 35:
                return new DcLetters('A', 'K');
            case 36:
                return new DcLetters('A', 'L');
            case 37:
                return new DcLetters('A', 'M');
            case 38:
                return new DcLetters('A', 'N');
            case 39:
                return new DcLetters('A', 'O');
            case 40:
                return new DcLetters('A', 'P');
            case 41:
                return new DcLetters('A', 'Q');
            case 42:
                return new DcLetters('A', 'R');
            case 43:
                return new DcLetters('A', 'S');
            case 44:
                return new DcLetters('A', 'T');
            case 45:
                return new DcLetters('A', 'U');
            case 46:
                return new DcLetters('A', 'V');
            case 47:
                return new DcLetters('A', 'W');
            case 48:
                return new DcLetters('A', 'X');
            case 49:
                return new DcLetters('A', 'Y');
            case 50:
                return new DcLetters('A', 'Z');
            case 51:
                return new DcLetters('B', 'C');
            case 52:
                return new DcLetters('B', 'D');
            case 53:
                return new DcLetters('B', 'E');
            case 54:
                return new DcLetters('B', 'F');
            case 55:
                return new DcLetters('B', 'G');
            case 56:
                return new DcLetters('B', 'H');
            case 57:
                return new DcLetters('B', 'I');
            case 58:
                return new DcLetters('B', 'J');
            case 59:
                return new DcLetters('B', 'K');
            case 60:
                return new DcLetters('B', 'L');
            case 61:
                return new DcLetters('B', 'M');
            case 62:
                return new DcLetters('B', 'N');
            case 63:
                return new DcLetters('B', 'O');
            case 64:
                return new DcLetters('B', 'P');
            case 65:
                return new DcLetters('B', 'Q');
            case 66:
                return new DcLetters('B', 'R');
            case 67:
                return new DcLetters('B', 'S');
            case 68:
                return new DcLetters('B', 'T');
            case 69:
                return new DcLetters('B', 'U');
            case 70:
                return new DcLetters('B', 'V');
            case 71:
                return new DcLetters('B', 'W');
            case 72:
                return new DcLetters('B', 'X');
            case 73:
                return new DcLetters('B', 'Y');
            case 74:
                return new DcLetters('B', 'Z');
            case 75:
                return new DcLetters('C', 'D');
            case 76:
                return new DcLetters('C', 'E');
            case 77:
                return new DcLetters('C', 'F');
            case 78:
                return new DcLetters('C', 'G');
            case 79:
                return new DcLetters('C', 'H');
            case 80:
                return new DcLetters('C', 'I');
            case 81:
                return new DcLetters('C', 'J');
            case 82:
                return new DcLetters('C', 'K');
            case 83:
                return new DcLetters('C', 'L');
            case 84:
                return new DcLetters('C', 'M');
            case 85:
                return new DcLetters('C', 'N');
            case 86:
                return new DcLetters('C', 'O');
            case 87:
                return new DcLetters('C', 'P');
            case 88:
                return new DcLetters('C', 'Q');
            case 89:
                return new DcLetters('C', 'R');
            case 90:
                return new DcLetters('C', 'S');
            case 91:
                return new DcLetters('C', 'T');
            case 92:
                return new DcLetters('C', 'U');
            case 93:
                return new DcLetters('C', 'V');
            case 94:
                return new DcLetters('C', 'W');
            case 95:
                return new DcLetters('C', 'X');
            case 96:
                return new DcLetters('C', 'Y');
            case 97:
                return new DcLetters('C', 'Z');
            case 98:
                return new DcLetters('D', 'E');
            case 99:
                return new DcLetters('D', 'F');
            case 100:
                return new DcLetters('D', 'G');
            case 101:
                return new DcLetters('D', 'H');
            case 102:
                return new DcLetters('D', 'I');
            case 103:
                return new DcLetters('D', 'J');
            case 104:
                return new DcLetters('D', 'K');
            case 105:
                return new DcLetters('D', 'L');
            case 106:
                return new DcLetters('D', 'M');
            case 107:
                return new DcLetters('D', 'N');
            case 108:
                return new DcLetters('D', 'O');
            case 109:
                return new DcLetters('D', 'P');
            case 110:
                return new DcLetters('D', 'Q');
            case 111:
                return new DcLetters('D', 'R');
            case 112:
                return new DcLetters('D', 'S');
            case 113:
                return new DcLetters('D', 'T');
            case 114:
                return new DcLetters('D', 'U');
            case 115:
                return new DcLetters('D', 'V');
            case 116:
                return new DcLetters('D', 'W');
            case 117:
                return new DcLetters('D', 'X');
            case 118:
                return new DcLetters('D', 'Y');
            case 119:
                return new DcLetters('D', 'Z');
            case 120:
                return new DcLetters('E', 'F');
            case 121:
                return new DcLetters('E', 'G');
            case 122:
                return new DcLetters('E', 'H');
            case 123:
                return new DcLetters('E', 'I');
            case 124:
                return new DcLetters('E', 'J');
            case 125:
                return new DcLetters('E', 'K');
            case 126:
                return new DcLetters('E', 'L');
            case 127:
                return new DcLetters('E', 'M');
            case 128:
                return new DcLetters('E', 'N');
            case 129:
                return new DcLetters('E', 'O');
            case 130:
                return new DcLetters('E', 'P');
            case 131:
                return new DcLetters('E', 'Q');
            case 132:
                return new DcLetters('E', 'R');
            case 133:
                return new DcLetters('E', 'S');
            case 134:
                return new DcLetters('E', 'T');
            case 135:
                return new DcLetters('E', 'U');
            case 136:
                return new DcLetters('E', 'V');
            case 137:
                return new DcLetters('E', 'W');
            case 138:
                return new DcLetters('E', 'X');
            case 139:
                return new DcLetters('E', 'Y');
            case 140:
                return new DcLetters('E', 'Z');
            case 141:
                return new DcLetters('F', 'G');
            case 142:
                return new DcLetters('F', 'H');
            case 143:
                return new DcLetters('F', 'I');
            case 144:
                return new DcLetters('F', 'J');
            case 145:
                return new DcLetters('F', 'K');
            case 146:
                return new DcLetters('F', 'L');
            case 147:
                return new DcLetters('F', 'M');
            case 148:
                return new DcLetters('F', 'N');
            case 149:
                return new DcLetters('F', 'O');
            case 150:
                return new DcLetters('F', 'P');
            case 151:
                return new DcLetters('F', 'Q');
            case 152:
                return new DcLetters('F', 'R');
            case 153:
                return new DcLetters('F', 'S');
            case 154:
                return new DcLetters('F', 'T');
            case 155:
                return new DcLetters('F', 'U');
            case 156:
                return new DcLetters('F', 'V');
            case 157:
                return new DcLetters('F', 'W');
            case 158:
                return new DcLetters('F', 'X');
            case 159:
                return new DcLetters('F', 'Y');
            case 160:
                return new DcLetters('F', 'Z');
            case 161:
                return new DcLetters('G', 'H');
            case 162:
                return new DcLetters('G', 'I');
            case 163:
                return new DcLetters('G', 'J');
            case 164:
                return new DcLetters('G', 'K');
            case 165:
                return new DcLetters('G', 'L');
            case 166:
                return new DcLetters('G', 'M');
            case 167:
                return new DcLetters('G', 'N');
            case 168:
                return new DcLetters('G', 'O');
            case 169:
                return new DcLetters('G', 'P');
            case 170:
                return new DcLetters('G', 'Q');
            case 171:
                return new DcLetters('G', 'R');
            case 172:
                return new DcLetters('G', 'S');
            case 173:
                return new DcLetters('G', 'T');
            case 174:
                return new DcLetters('G', 'U');
            case 175:
                return new DcLetters('G', 'V');
            case 176:
                return new DcLetters('G', 'W');
            case 177:
                return new DcLetters('G', 'X');
            case 178:
                return new DcLetters('G', 'Y');
            case 179:
                return new DcLetters('G', 'Z');
            case 180:
                return new DcLetters('H', 'I');
            case 181:
                return new DcLetters('H', 'J');
            case 182:
                return new DcLetters('H', 'K');
            case 183:
                return new DcLetters('H', 'L');
            case 184:
                return new DcLetters('H', 'M');
            case 185:
                return new DcLetters('H', 'N');
            case 186:
                return new DcLetters('H', 'O');
            case 187:
                return new DcLetters('H', 'P');
            case 188:
                return new DcLetters('H', 'Q');
            case 189:
                return new DcLetters('H', 'R');
            case 190:
                return new DcLetters('H', 'S');
            case 191:
                return new DcLetters('H', 'T');
            case 192:
                return new DcLetters('H', 'U');
            case 193:
                return new DcLetters('H', 'V');
            case 194:
                return new DcLetters('H', 'W');
            case 195:
                return new DcLetters('H', 'X');
            case 196:
                return new DcLetters('H', 'Y');
            case 197:
                return new DcLetters('H', 'Z');
            case 198:
                return new DcLetters('I', 'J');
            case 199:
                return new DcLetters('I', 'K');
            case 200:
                return new DcLetters('I', 'L');
            case 201:
                return new DcLetters('I', 'M');
            case 202:
                return new DcLetters('I', 'N');
            case 203:
                return new DcLetters('I', 'O');
            case 204:
                return new DcLetters('I', 'P');
            case 205:
                return new DcLetters('I', 'Q');
            case 206:
                return new DcLetters('I', 'R');
            case 207:
                return new DcLetters('I', 'S');
            case 208:
                return new DcLetters('I', 'T');
            case 209:
                return new DcLetters('I', 'U');
            case 210:
                return new DcLetters('I', 'V');
            case 211:
                return new DcLetters('I', 'W');
            case 212:
                return new DcLetters('I', 'X');
            case 213:
                return new DcLetters('I', 'Y');
            case 214:
                return new DcLetters('I', 'Z');
            case 215:
                return new DcLetters('J', 'K');
            case 216:
                return new DcLetters('J', 'L');
            case 217:
                return new DcLetters('J', 'M');
            case 218:
                return new DcLetters('J', 'N');
            case 219:
                return new DcLetters('J', 'O');
            case 220:
                return new DcLetters('J', 'P');
            case 221:
                return new DcLetters('J', 'Q');
            case 222:
                return new DcLetters('J', 'R');
            case 223:
                return new DcLetters('J', 'S');
            case 224:
                return new DcLetters('J', 'T');
            case 225:
                return new DcLetters('J', 'U');
            case 226:
                return new DcLetters('J', 'V');
            case 227:
                return new DcLetters('J', 'W');
            case 228:
                return new DcLetters('J', 'X');
            case 229:
                return new DcLetters('J', 'Y');
            case 230:
                return new DcLetters('J', 'Z');
            case 231:
                return new DcLetters('K', 'L');
            case 232:
                return new DcLetters('K', 'M');
            case 233:
                return new DcLetters('K', 'N');
            case 234:
                return new DcLetters('K', 'O');
            case 235:
                return new DcLetters('K', 'P');
            case 236:
                return new DcLetters('K', 'Q');
            case 237:
                return new DcLetters('K', 'R');
            case 238:
                return new DcLetters('K', 'S');
            case 239:
                return new DcLetters('K', 'T');
            case 240:
                return new DcLetters('K', 'U');
            case 241:
                return new DcLetters('K', 'V');
            case 242:
                return new DcLetters('K', 'W');
            case 243:
                return new DcLetters('K', 'X');
            case 244:
                return new DcLetters('K', 'Y');
            case 245:
                return new DcLetters('K', 'Z');
            case 246:
                return new DcLetters('L', 'M');
            case 247:
                return new DcLetters('L', 'N');
            case 248:
                return new DcLetters('L', 'O');
            case 249:
                return new DcLetters('L', 'P');
            case 250:
                return new DcLetters('L', 'Q');
            case 251:
                return new DcLetters('L', 'R');
            case 252:
                return new DcLetters('L', 'S');
            case 253:
                return new DcLetters('L', 'T');
            case 254:
                return new DcLetters('L', 'U');
            case 255:
                return new DcLetters('L', 'V');
            case 256:
                return new DcLetters('L', 'W');
            case 257:
                return new DcLetters('L', 'X');
            case 258:
                return new DcLetters('L', 'Y');
            case 259:
                return new DcLetters('L', 'Z');
            case 260:
                return new DcLetters('M', 'N');
            case 261:
                return new DcLetters('M', 'O');
            case 262:
                return new DcLetters('M', 'P');
            case 263:
                return new DcLetters('M', 'Q');
            case 264:
                return new DcLetters('M', 'R');
            case 265:
                return new DcLetters('M', 'S');
            case 266:
                return new DcLetters('M', 'T');
            case 267:
                return new DcLetters('M', 'U');
            case 268:
                return new DcLetters('M', 'V');
            case 269:
                return new DcLetters('M', 'W');
            case 270:
                return new DcLetters('M', 'X');
            case 271:
                return new DcLetters('M', 'Y');
            case 272:
                return new DcLetters('M', 'Z');
            case 273:
                return new DcLetters('N', 'O');
            case 274:
                return new DcLetters('N', 'P');
            case 275:
                return new DcLetters('N', 'Q');
            case 276:
                return new DcLetters('N', 'R');
            case 277:
                return new DcLetters('N', 'S');
            case 278:
                return new DcLetters('N', 'T');
            case 279:
                return new DcLetters('N', 'U');
            case 280:
                return new DcLetters('N', 'V');
            case 281:
                return new DcLetters('N', 'W');
            case 282:
                return new DcLetters('N', 'X');
            case 283:
                return new DcLetters('N', 'Y');
            case 284:
                return new DcLetters('N', 'Z');
            case 285:
                return new DcLetters('O', 'P');
            case 286:
                return new DcLetters('O', 'Q');
            case 287:
                return new DcLetters('O', 'R');
            case 288:
                return new DcLetters('O', 'S');
            case 289:
                return new DcLetters('O', 'T');
            case 290:
                return new DcLetters('O', 'U');
            case 291:
                return new DcLetters('O', 'V');
            case 292:
                return new DcLetters('O', 'W');
            case 293:
                return new DcLetters('O', 'X');
            case 294:
                return new DcLetters('O', 'Y');
            case 295:
                return new DcLetters('O', 'Z');
            case 296:
                return new DcLetters('P', 'Q');
            case 297:
                return new DcLetters('P', 'R');
            case 298:
                return new DcLetters('P', 'S');
            case 299:
                return new DcLetters('P', 'T');
            case 300:
                return new DcLetters('P', 'U');
            case 301:
                return new DcLetters('P', 'V');
            case 302:
                return new DcLetters('P', 'W');
            case 303:
                return new DcLetters('P', 'X');
            case 304:
                return new DcLetters('P', 'Y');
            case 305:
                return new DcLetters('P', 'Z');
            case 306:
                return new DcLetters('Q', 'R');
            case 307:
                return new DcLetters('Q', 'S');
            case 308:
                return new DcLetters('Q', 'T');
            case 309:
                return new DcLetters('Q', 'U');
            case 310:
                return new DcLetters('Q', 'V');
            case 311:
                return new DcLetters('Q', 'W');
            case 312:
                return new DcLetters('Q', 'X');
            case 313:
                return new DcLetters('Q', 'Y');
            case 314:
                return new DcLetters('Q', 'Z');
            case 315:
                return new DcLetters('R', 'S');
            case 316:
                return new DcLetters('R', 'T');
            case 317:
                return new DcLetters('R', 'U');
            case 318:
                return new DcLetters('R', 'V');
            case 319:
                return new DcLetters('R', 'W');
            case 320:
                return new DcLetters('R', 'X');
            case 321:
                return new DcLetters('R', 'Y');
            case 322:
                return new DcLetters('R', 'Z');
            case 323:
                return new DcLetters('S', 'T');
            case 324:
                return new DcLetters('S', 'U');
            case 325:
                return new DcLetters('S', 'V');
            case 326:
                return new DcLetters('S', 'W');
            case 327:
                return new DcLetters('S', 'X');
            case 328:
                return new DcLetters('S', 'Y');
            case 329:
                return new DcLetters('S', 'Z');
            case 330:
                return new DcLetters('T', 'U');
            case 331:
                return new DcLetters('T', 'V');
            case 332:
                return new DcLetters('T', 'W');
            case 333:
                return new DcLetters('T', 'X');
            case 334:
                return new DcLetters('T', 'Y');
            case 335:
                return new DcLetters('T', 'Z');
            case 336:
                return new DcLetters('U', 'V');
            case 337:
                return new DcLetters('U', 'W');
            case 338:
                return new DcLetters('U', 'X');
            case 339:
                return new DcLetters('U', 'Y');
            case 340:
                return new DcLetters('U', 'Z');
            case 341:
                return new DcLetters('V', 'W');
            case 342:
                return new DcLetters('V', 'X');
            case 343:
                return new DcLetters('V', 'Y');
            case 344:
                return new DcLetters('V', 'Z');
            case 345:
                return new DcLetters('W', 'X');
            case 346:
                return new DcLetters('W', 'Y');
            case 347:
                return new DcLetters('W', 'Z');
            case 348:
                return new DcLetters('X', 'Y');
            case 349:
                return new DcLetters('X', 'Z');
            case 350:
                return new DcLetters('Y', 'Z');
            default:
                System.out.println(i);
                throw new RuntimeException("Should not reach here: " + i);
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
