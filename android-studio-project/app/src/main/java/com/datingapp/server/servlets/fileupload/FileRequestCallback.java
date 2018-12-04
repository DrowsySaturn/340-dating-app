package com.datingapp.server.servlets.fileupload;
/**
 * The purpose of this interface is to add a callback method to a class. Used for file uploading.
 *
 * @author Jonathan Cooper
 * @version nov-4-2018
 */

import java.io.IOException;
import java.io.InputStream;

public interface FileRequestCallback {
    /**
     * Invoked when a file is being read from the request.
     * @param _fileName This is the name of the file being uploaded.
     * @param _fileData This is a stream object that can be read from to obtain the files data.
     * @throws IOException This is the exception thrown on failure.
     */
    public void onFileUploaded(String _fileName, InputStream _fileData) throws IOException;
}
