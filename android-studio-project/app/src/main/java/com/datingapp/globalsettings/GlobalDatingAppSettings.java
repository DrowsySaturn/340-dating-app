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
     * This is the file that contains language settings.
     */
    private static final String LANGUAGE_SETTINGS_FILE = "language.json";


    /**
     * This is the shared settings instance.
     */
    private static SharedSettings sharedSettings = null;


    /**
     * This is the server settings instance.
     */
    private static ServerSettings serverSettings = null;


    /**
     * This is the language settings instance to use.
     */
    private static LanguageSettings languageSettings = null;


    /**
     * This gets the global language settings object.
     * @return A language settings object.
     * @throws IOException This throws an IO exception when it was unable to load language settings.
     */
    public static LanguageSettings getLanguageSettings() throws IOException {
        if (GlobalDatingAppSettings.languageSettings == null) {
            GlobalDatingAppSettings.languageSettings = GlobalDatingAppSettings.loadSettings(LANGUAGE_SETTINGS_FILE, LanguageSettings.class);
        }
        return GlobalDatingAppSettings.languageSettings;
    }


    /**
     * Gets the shared settings object.
     * @return Returns the shared settings object.
     * @throws IOException Throws exception when the settings file could not be loaded.
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
        return GlobalDatingAppSettings.serverSettings;
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
        System.out.println(settingsDataStream);
        String settingsDataString = IOUtility.readStreamIntoString(settingsDataStream);
        return Json.deserialize(settingsDataString, _classType);
    }
}
