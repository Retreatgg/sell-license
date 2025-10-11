package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.dto.RegionDto;
import com.axelor.apps.selllicenseplates2.exception.RegionNotFoundException;
import com.axelor.apps.selllicenseplates2.mapper.RegionMapper;
import com.axelor.apps.selllicenseplates2.model.Region;
import com.axelor.apps.selllicenseplates2.repository.RegionRepository;
import com.axelor.apps.selllicenseplates2.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public Region getRegionById(Long regionId) {
        return regionRepository
                .findById(regionId).orElseThrow(() -> new RegionNotFoundException("Регион не найден с ID: " + regionId));
    }

    @Override
    public List<RegionDto> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return regionMapper.toListDto(regions);
    }
}
