package com.example.spacetrader.viewmodel;

import com.example.spacetrader.viewmodel.representation.SolarSystemRepresentation;

import java.util.List;

public interface ISpaceMapViewModel {
    List<SolarSystemRepresentation> getSystems();
    List<SolarSystemRepresentation> getSystems(int boundsX, int boundsY);
}
