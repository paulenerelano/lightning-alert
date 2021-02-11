package com.example.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Asset Data
 *
 * @author Julianne Paulene Relano
 */
@RequiredArgsConstructor
@Getter
public class Asset {
    @NonNull
    private final String assetName;
    @NonNull
    private final String quadKey;
    @NonNull
    private final String assetOwner;
    @Setter
    private boolean isAlerted;
}
