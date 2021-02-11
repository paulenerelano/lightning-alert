package com.example.service;

import com.example.dto.Asset;
import com.example.dto.Lightning;
import com.example.repository.AssetManager;
import com.example.utl.Logger;
import org.geotools.tile.impl.bing.BingTileUtil;

/**
 * Handles lightning and notifies alerts
 *
 * @author Julianne Paulene Relano
 */
public class LightningAlertService {
    /**
     * Default zoom level for computing the quad key
     */
    private static final int ZOOM_LVL = 12;

    /**
     * Checks and notifies if there is an asset in the area where a lightning struck
     * @param lightning Lightning data
     * @param assetMgr Asset manager
     */
    public void handleLightning(Lightning lightning, AssetManager assetMgr) {
        if (lightning == null ||
            assetMgr == null || assetMgr.getAssetList().size() == 0) {
            // Invalid lightning, invalid assetManager or no assets, do nothing
            return;
        }

        if (lightning.getFlashType() == Lightning.FT_HEARTBEAT) {
            // No lightning struck, heartbeat only
            return;
        }

        String quadKey = BingTileUtil.lonLatToQuadKey(lightning.getLongitude(),
                            lightning.getLatitude(), ZOOM_LVL);

        Asset strikedAsset = assetMgr.findByQuadKey(quadKey);
        if (strikedAsset != null && !strikedAsset.isAlerted()) {
            notifyAlert(strikedAsset.getAssetOwner(), strikedAsset.getAssetName());

            // Mark asset as alerted, to prevent sending of multiple notifications
            strikedAsset.setAlerted(true);
        }
    }

    /**
     * Notifies an alert
     * @param assetOwner Asset Owner
     * @param assetName Asset Name
     */
    private void notifyAlert(String assetOwner, String assetName) {
        Logger.info("lightning alert for " +
            assetOwner + ":" + assetName);
    }
}
