package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.RegionDto;
import com.axelor.apps.selllicenseplates2.model.Region;

import java.util.List;

public interface RegionService {
    Region getRegionById(Long regionId);
    List<RegionDto> getAllRegions();
}
