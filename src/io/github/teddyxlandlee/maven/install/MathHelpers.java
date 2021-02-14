package io.github.teddyxlandlee.maven.install;

public class MathHelpers {
    static byte[] fromInt(int i) {
        return new byte[]{
                (byte) (i >> 24),
                (byte) (i >> 16),
                (byte) (i >> 8),
                (byte) i
        };
    }

    static int toInt(byte[] b) {
        if (b.length < 4) return -1;
        short[] shorts = new short[4];
        for (int i = 0; i < 4; ++i) {
            shorts[i] = b[i] < 0 ? (short) (256 + b[i]) : b[i];
        }
        return (shorts[0] << 24) + (shorts[1] << 16) + (shorts[2] << 8) + shorts[3];
    }
}
