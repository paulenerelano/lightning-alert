package com.example;

import com.example.dto.Lightning;
import com.example.repository.AssetManager;
import com.example.service.LightningAlertService;
import com.example.repository.LightningManager;

/**
 * Main function for the Lightning alert application
 *
 * @author Julianne Paulene Relano
 */
public class LightningAlertMain {
    public static void main(String[] args) {
        LightningManager lightningManager = new LightningManager(args[0]);
        AssetManager assetManager = new AssetManager(args[1]);

        LightningAlertService alertSvc = new LightningAlertService();

        Lightning lightningStrike;
        while ((lightningStrike = lightningManager.readLightning()) != null) {
            alertSvc.handleLightning(lightningStrike, assetManager);
        }
    }
}
