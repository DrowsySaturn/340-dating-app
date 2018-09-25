package com.datingapp.shared.datapersistence;
/*
 * This class "scapes" private fields from other classes. This is typically used for serialization
 * or data persistence.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 * @see ClassScrapingException
 */

import java.lang.reflect.Field;

public class ClassScraper<C> {

    private Class<C> classType;

    public ClassScraper(Class<C> _classType) {
        this.classType = _classType;
    }

    /**
     * Reads a private field from the current Object being "scraped".
     * @param _instance Instance of the object type to read.
     * @param _variableName Name of the variable to read from the object.
     * @return A variable read from the current Object being "scraped".
     */
    public <T> Object read(Object _instance, String _variableName, Class<T> _variableType) {
        try {
            Field field = classType.getDeclaredField(_variableName);
            field.setAccessible(true);
            if (!field.getType().equals(_variableType) || _variableType.isPrimitive()) {
                throw new ClassScrapingException("Field is not correct type " + _variableName);
            }
            return field.get(_instance);
        } catch (NoSuchFieldException noSuchFieldException) {
            throw new ClassScrapingException("Field does not exist " + _variableName, noSuchFieldException);
        } catch (IllegalAccessException illegalAccessException) {
            throw new ClassScrapingException("Cannot access " + _variableName + ", security policy is too strict.", illegalAccessException);
        }
    }

    /**
     * Writes to a private field from the current Object being "scraped".
     * @param _instance Instance of the object type to write to.
     * @param _variableName Name of the variable to read from the object.
     * @param _value The value to write to the object's field.
     */
    public <T> void write(Object _instance, String _variableName, T _value) {
        try {
            Field field = this.classType.getField(_variableName);
            field.setAccessible(true);
            field.set(_instance, _value);
        } catch (NoSuchFieldException noSuchFieldException) {
            throw new ClassScrapingException("Field does not exist " + _variableName, noSuchFieldException);
        } catch (IllegalAccessException illegalAccessException) {
            throw new ClassScrapingException("Cannot access " + _variableName + ", security policy is too strict", illegalAccessException);
        }
    }
}
