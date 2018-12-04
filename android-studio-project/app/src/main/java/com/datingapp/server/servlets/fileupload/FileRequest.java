package com.datingapp.server.servlets.fileupload;
/**
 * The purpose of this class is to handle file uploading for http servlets.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FileRequest {
    /**
     * This is how big before a file is before it is saved to a file.
     */
    private static final int MAX_FILE_SIZE_BEFORE_SAVING_TO_FILE = 30*1024*1024;

    /**
     * This is the original request to read the data from.
     */
    private HttpServletRequest request;

    /**
     * This is the callback for each file being uploaded.
     */
    private FileRequestCallback requestCallback;

    /**
     * This contains the parameters.
     */
    private Map<String, String> parameters;


    public FileRequest(HttpServletRequest _request, FileRequestCallback _requestCallback) throws IOException {
        this.request = _request;
        this.requestCallback = _requestCallback;
        this.handleFileUpload();
    }

    public String getParameter(String _key) {
        return parameters.get(_key);
    }

    /**
     * This handles file upload by calling the callback when a file is encountered in the parameter
     * map and saves the value to the parameter map if it is not a file. Non file params are parsed
     * first.
     * @throws IOException IOException occurs when a file was unable to be uploaded.
     */
    private void handleFileUpload() throws IOException {
        try {
            parameters = new HashMap<String, String>();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(MAX_FILE_SIZE_BEFORE_SAVING_TO_FILE);
            List<FileItem> items = upload.parseRequest(this.request);
            // Handle form fields first.
            for (FileItem fileItem : items) {
                if (fileItem.isFormField()) {
                    parameters.put(fileItem.getFieldName(), fileItem.getString());
                }
            }
            // Handle file uploads.
            for (FileItem fileItem : items) {
                if (!fileItem.isFormField()) {
                    requestCallback.onFileUploaded(fileItem.getFieldName(), fileItem.getInputStream());
                }
            }
        } catch (FileUploadException fileUploadEx) {
            throw new IOException(fileUploadEx);
        }
    }

}
