package com.example.utl;

/**
 * Logger utility.
 * Uses standard output stream and standard error output stream to display logs.
 *
 * @author Julianne Paulene Relano
 */
public class Logger {

    /**
     * Prints error message to the standard error output stream.
     * @param msg Log message
     */
    public static void error(String msg) {
        System.err.println(msg);
    }

    /**
     * Prints info message to the standard output stream.
     * @param msg Log message
     */
    public static void info(String msg) {
        System.out.println(msg);
    }

}
