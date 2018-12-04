package com.datingapp.server.servlets.fileupload;

import java.io.IOException;
import java.io.InputStream;

public interface FileRequestCallback {
    public void onFileUploaded(String _fileName, InputStream _fileData) throws IOException;
}
