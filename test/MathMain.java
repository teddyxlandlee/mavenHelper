import java.util.Arrays;

public class MathMain {
    static byte[] fromInt(int i) {
        return new byte[] {
                (byte) (i >> 24 & 255),
                (byte) (i >> 16 & 255),
                (byte) (i >> 8 & 255),
                (byte) (i & 255)
        };
    }
    static int toInt(byte[] bs) {
        if (bs.length < 4) return -1;
        int[] intArray = new int[4];
        for (int i = 0; i < 4; ++i) {
            if (bs[i] < 0)
                intArray[i] = 256 + bs[i];
            else
                intArray[i] = bs[i];
        }

        System.out.println(Arrays.toString(intArray));
        return  (intArray[0] << 24) +
                (intArray[1] << 16) +
                (intArray[2] << 8)  +
                intArray[3];
    }

    public static void main(String[] args) {
        int i = 0x49A780FA;
        byte[] b = fromInt(i);
        System.out.println(Arrays.toString(b));
        System.out.println(toInt(b));
    }
}
