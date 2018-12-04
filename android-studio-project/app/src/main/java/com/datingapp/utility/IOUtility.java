package com.datingapp.utility;
/**
 * The purpose of this class is to help with dealing with standard Java IO.
 *
 * @author Jonathan Cooper
 * @version nov-19-2018
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtility {
    /**
     * Size to use for buffering throughout this utility.
     */
    private static final int BUFFER_SIZE = 512;


    /**
     * Standard encoding to use if one is not specified.
     */
    private static final String DEFAULT_ENCODING = "US-ASCII";


    /**
     * Copies an input stream into a string.
     * @param _inputStream Stream to copy to a string.
     * @return String containing the stream's data.
     * @throws IOException If there was a problem reading the stream.
     */
    public static String readStreamIntoString(InputStream _inputStream) throws IOException {
        return IOUtility.readStreamIntoString(_inputStream, IOUtility.DEFAULT_ENCODING);
    }


    /**
     * Copies an input stream into a string with the specified encoding.
     * @param _inputStream Stream to copy to a string.
     * @param _encoding Encoding to use for the string.
     * @return String containing the stream's data.
     * @throws IOException If there was a problem reading the stream.
     */
    public static String readStreamIntoString(InputStream _inputStream, String _encoding) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtility.copyInputToOutputStream(_inputStream, outputStream);
        return new String(outputStream.toByteArray(), _encoding);
    }


    /**
     * Copies the input stream's data to the specified output stream.
     * @param _input Input stream to copy data from.
     * @param _output Output stream to copy data to.
     * @throws IOException If there was a problem copying stream data.
     */
    public static int copyInputToOutputStream(InputStream _input, OutputStream _output) throws IOException {
       byte buff[] = new byte[IOUtility.BUFFER_SIZE];
       int totalAmount = 0;
       int readAmount;
       while ((readAmount = _input.read(buff)) > 0) {
           _output.write(buff, 0, readAmount);
           totalAmount += readAmount;
       }
       return totalAmount;
    }
}
