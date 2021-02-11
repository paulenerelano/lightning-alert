package com.example.repository;

import com.example.dto.Lightning;
import com.example.utl.Validator;
import com.example.utl.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * LightningManager class reads lightning data from a file
 *
 * @author Julianne Paulene Relano
 */
public class LightningManager{
    // Key strings for lightning properties
    private static final String KEY_FLASH_TYPE = "flashType";
    private static final String KEY_STRIKE_TIME = "strikeTime";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_PEAK_AMPS = "peakAmps";
    private static final String KEY_RESERVED = "reserved";
    private static final String KEY_ICHEIGHT = "icHeight";
    private static final String KEY_RECEIVED_TIME = "receivedTime";
    private static final String KEY_NUM_SENSORS = "numberOfSensors";
    private static final String KEY_MULTIPLICITY = "multiplicity";

    private final JSONParser jsonParser;
    private final BufferedReader reader;
    private int lineNumber = 1;


    /**
     * Constructor
     * @param path Path to the JSON file containing the lightning data
     */
    public LightningManager(String path) {
        BufferedReader tmpReader;
        try {
            FileReader file = new FileReader(path);
            tmpReader = new BufferedReader(file);
        } catch (FileNotFoundException e) {
            Logger.error("Source file for lightning data not found!");
            tmpReader = null;
        }

        reader = tmpReader;
        jsonParser = new JSONParser();
    }

    /**
     * Reads one lightning data
     * @return Lightning data
     */
    public Lightning readLightning() {
        if (reader == null) {
            return null;
        }

        try {
            String lightningData = reader.readLine();
            if (lightningData == null) {
                closeReader();
                return null;
            }

            JSONObject lightningJSON = (JSONObject) jsonParser.parse(lightningData);
            Lightning lightning = convertData(lightningJSON);

            lineNumber++;
            return lightning;
        } catch (IOException e) {
            Logger.error("Error in reading lightning data. Line: " + lineNumber);
        } catch (ParseException e) {
            Logger.error("Incorrect JSON data format. Line: " + lineNumber);
        } catch (NumberFormatException e) {
            Logger.error("Incorrect data type. Line: " + lineNumber);
        }

        closeReader();
        return null;
    }

    /**
     * Closes the buffered reader
     */
    private void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            Logger.error("Error in closing lightning data file");
        }
    }

    /**
     * Converts and validates the lightning data to the expected type
     * @param lightningJSON JSON object containing lightning data
     * @return Lightning data
     */
    private Lightning convertData(JSONObject lightningJSON) {
        int flashType = Validator.getInt(lightningJSON.get(KEY_FLASH_TYPE));
        Date strikeTime = Validator.getDate(lightningJSON.get(KEY_STRIKE_TIME));
        double latitude = Validator.getDouble(lightningJSON.get(KEY_LATITUDE));
        double longitude = Validator.getDouble(lightningJSON.get(KEY_LONGITUDE));
        int peakAmps = Validator.getInt(lightningJSON.get(KEY_PEAK_AMPS));
        String reserved = Validator.getString(lightningJSON.get(KEY_RESERVED));
        int icHeight = Validator.getInt(lightningJSON.get(KEY_ICHEIGHT));
        Date receivedTime = Validator.getDate(lightningJSON.get(KEY_RECEIVED_TIME));
        int numberOfSensors = Validator.getInt(lightningJSON.get(KEY_NUM_SENSORS));
        int multiplicity = Validator.getInt(lightningJSON.get(KEY_MULTIPLICITY));

        return new Lightning(flashType, strikeTime, latitude,
            longitude, peakAmps, reserved, icHeight,
            receivedTime, numberOfSensors, multiplicity);
    }
}
