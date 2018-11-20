package com.datingapp.globalsettings;
/**
 * The purpose of this class is to load a global settings object for each type of settings file.
 *
 * @author Jonathan Cooper
 * @version nov-19-2018
 */

import com.datingapp.json.Json;
import com.datingapp.utility.IOUtility;

import java.io.IOException;
import java.io.InputStream;

public class GlobalDatingAppSettings {
    /**
     * The name of the file containing shared settings.
     */
    private static final String SHARED_SETTINGS_FILE = "shared.json";

    /**
     * The name of the file containing server settings.
     */
    private static final String SERVER_SETTINGS_FILE = "server.json";

    /**
     * Shared settings instance.
     */
    private static SharedSettings sharedSettings = null;

    /**
     * Server settings instance.
     */
    private static ServerSettings serverSettings = null;

    /**
     * Gets the shared settings object.
     * @return Returns the shared settings object.
     * @throws IOException If there was an issue loading the settings.
     */
    public static SharedSettings getSharedSettings() throws IOException {
        if (GlobalDatingAppSettings.sharedSettings == null) {
            GlobalDatingAppSettings.sharedSettings = GlobalDatingAppSettings.loadSettings(SHARED_SETTINGS_FILE, SharedSettings.class);
        }
        return GlobalDatingAppSettings.sharedSettings;
    }

    /**
     * Gets the server settings object.
     * @return Returns the server settings object.
     * @throws IOException If there was an issue loading the settings.
     */
    public static ServerSettings getServerSettings() throws IOException {
        if (GlobalDatingAppSettings.serverSettings == null) {
            GlobalDatingAppSettings.serverSettings = GlobalDatingAppSettings.loadSettings(SERVER_SETTINGS_FILE, ServerSettings.class);
        }
        return null;
    }

    /**
     * Loads any of the settings files.
     * @param _fileName This is the file name to load settings from.
     * @param _classType This is the type of settings to load.
     * @param <T> This is the type of settings to load.
     * @return The newly loaded settings object.
     * @throws IOException This is thrown if there was an issue loading the settings.
     */
    private static <T> T loadSettings(String _fileName, Class<T> _classType) throws IOException {
        InputStream settingsDataStream = GlobalDatingAppSettings.class.getResourceAsStream(_fileName);
        String settingsDataString = IOUtility.readStreamIntoString(settingsDataStream);
        return Json.deserialize(settingsDataString, _classType);
    }
}
