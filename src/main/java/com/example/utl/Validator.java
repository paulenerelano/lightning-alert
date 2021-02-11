package com.example.utl;

import java.util.Date;

/**
 * Converts and validates data to a specific format
 *
 * @author Julianne Paulene Relano
 */
public class Validator {
    /**
     * Parses the data to an int
     * @param obj Object to parse
     * @return int value
     */
    public static int getInt(Object obj) {
        if (obj instanceof Long) {
            return ((Long) obj).intValue();
        }

        throw new NumberFormatException();
    }

    /**
     * Parses the date in unix epoch time to Date
     * @param obj Object to parse
     * @return Date
     */
    public static Date getDate(Object obj) {
        if (obj instanceof Long) {
            return new Date((Long) obj);
        }

        throw new NumberFormatException();
    }

    /**
     * Parses data to a double
     * @param obj Object to parse
     * @return double value
     */
    public static double getDouble(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }

        throw new NumberFormatException();
    }

    /**
     * Parses data to a String
     * @param obj Object to parse
     * @return String value
     */
    public static String getString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }

        throw new NumberFormatException();
    }
}
