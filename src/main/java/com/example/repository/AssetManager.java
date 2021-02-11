package com.example.repository;

import com.example.dto.Asset;
import com.example.utl.Logger;
import com.example.utl.Validator;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AssetManager class reads the asset data from a file and
 * maintains it.
 *
 * @author Julianne Paulene Relano
 */
public class AssetManager {
    // Key strings for Asset properties
    private static final String KEY_ASSET_NAME = "assetName";
    private static final String KEY_QUAD_KEY = "quadKey";
    private static final String KEY_ASSET_OWNER = "assetOwner";

    @Getter
    private final Map<String, Asset> assetList;

    /**
     * Constructor
     * @param path path to the JSON file containing the assets
     */
    public AssetManager(String path) {
        assetList = load(path);
    }

    /**
     * Find an asset using a quad key
     * @param quadKey Quad key of asset to find
     * @return Asset with matching Quad Key, null if no asset with Quad key
     */
    public Asset findByQuadKey(String quadKey) {
        return assetList.get(quadKey);
    }

    /**
     * Loads asset data from a JSON file
     * @param path Path to JSON file
     * @return Map of assets
     */
    private Map<String, Asset> load(String path) {
        Map<String, Asset> assetMap = new HashMap<String, Asset>();
        JSONParser parser = new JSONParser();
        int lineNumber = 1;
        JSONArray data;
        try {
            data = (JSONArray) parser.parse(new FileReader(path));
        } catch (FileNotFoundException e) {
            Logger.error("Source file for asset data not found.");
            return assetMap;
        } catch (ParseException e) {
            Logger.error("Incorrect asset data file content format.");
            return assetMap;
        } catch (IOException e) {
            Logger.error("I/O error in reading asset file.");
            return assetMap;
        }

        for (Object elem : data) {
            JSONObject assetJSONObj = (JSONObject) elem;
            String assetName;
            String quadKey;
            String assetOwner;

            try {
                assetName = Validator.getString(assetJSONObj.get(KEY_ASSET_NAME));
                quadKey = Validator.getString(assetJSONObj.get(KEY_QUAD_KEY));
                assetOwner = Validator.getString(assetJSONObj.get(KEY_ASSET_OWNER));
            } catch (NumberFormatException e){
                Logger.error("Incorrect data type. Line: " + lineNumber);

                // Skip asset
                lineNumber++;
                continue;
            }
            Asset asset = new Asset(assetName, quadKey, assetOwner);

            assetMap.put(quadKey, asset);
            lineNumber++;
        }

        return assetMap;
    }
}
