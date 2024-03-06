package ru.crestwavetech.crestwave.util;

import ru.crestwavetech.crestwave.exception.ProcessException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class Packager {
    private static final ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
    private static final Charset charset = StandardCharsets.UTF_16LE;
    private static final int capacity = 4;
    private static final String headerHEX = "FFBBCCDD";

    public static byte[] getPackage(String json) {
        try {
            byte[] jsonBytes = json.getBytes(charset);
            byte[] header = HexFormat.of().parseHex(headerHEX);
            byte[] lengthBytes = ByteBuffer.allocate(capacity).order(byteOrder).putInt(jsonBytes.length).array();
            byte[] dataToSend = new byte[header.length + lengthBytes.length + jsonBytes.length];
            System.arraycopy(header, 0, dataToSend, 0, header.length);
            System.arraycopy(lengthBytes, 0, dataToSend, header.length, lengthBytes.length);
            System.arraycopy(jsonBytes, 0, dataToSend, header.length + lengthBytes.length, jsonBytes.length);
            return dataToSend;
        } catch (RuntimeException e) {
            throw new ProcessException("Error while preparing byte package for sending in socket");
        }
    }
}