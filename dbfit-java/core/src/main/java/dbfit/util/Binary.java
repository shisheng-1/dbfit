package dbfit.util;

import java.util.ArrayList;
import java.util.List;

public class Binary {
    private byte[] binaryData;

    public Binary(final byte[] byteStream) {
        binaryData = byteStream;
    }

    public int getLength() {
        return binaryData.length;
    }

    private byte getByte(int index) {
        return binaryData[index];
    }

    public byte[] getBytes() {
        return binaryData;
    }

    public static Object parse(String s) throws Exception {
        List<Byte> bytes = new ArrayList<Byte>();
        if (!s.substring(0, 2).equals("0x")) {
            throw new NumberFormatException("Hexadecimal string does not start with 0x");
        }
        String input = s.substring(2);
        for (int i = ((input.length()) / 2); i > 0; i--) {
            int index = (i * 2) - 2;
            int j = Integer.parseInt(input.substring(index, index + 2), 16);
            bytes.add((byte)j);
        }
        byte[] binaryData = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            binaryData[i] = bytes.get(i);
        }
        return new Binary(binaryData);
    }

    @Override
    public String toString() {
        String out = "0x";
        for (int i = binaryData.length - 1; i >= 0; i--) {
            out = out + String.format("%02X", binaryData[i]);
        }
        return out;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null)
            return false;
        if (!(other instanceof Binary)) {
            return false;
        }
        Binary o2 = (Binary) other;
        if (binaryData.length != o2.getLength()) {
            return false;
        }
        for (int i = 0; i < binaryData.length; i++) {
            if (binaryData[i] != o2.getByte(i)) {
                return false;
            }
        }
        return true;
    }
}
