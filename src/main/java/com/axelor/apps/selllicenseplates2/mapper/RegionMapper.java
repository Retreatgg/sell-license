package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.RegionDto;
import com.axelor.apps.selllicenseplates2.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegionMapper {

    RegionDto toDto(Region region);
    List<RegionDto> toListDto(List<Region> regions);
}
