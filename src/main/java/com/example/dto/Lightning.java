package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * Lightning Data
 *
 * @author Julianne Paulene Relano
 */
@AllArgsConstructor
@Getter
public class Lightning {
    private final int flashType;
    private final Date strikeTime;
    private final double latitude;
    private final double longitude;
    private final int peakAmps;
    private final String reserved;
    private final int icHeight;
    private final Date receivedTime;
    private final int numberOfSensors;
    private final int multiplicity;

    public static final int FT_CLOUD_TO_GROUND = 0;
    public static final int FT_CLOUD_TO_CLOUD = 1;
    public static final int FT_HEARTBEAT = 9;
}
