package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.RegionDto;
import com.axelor.apps.selllicenseplates2.model.Region;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-14T16:49:24+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Override
    public RegionDto toDto(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionDto regionDto = new RegionDto();

        regionDto.setId( region.getId() );
        regionDto.setRegionName( region.getRegionName() );
        regionDto.setRegionCode( region.getRegionCode() );

        return regionDto;
    }

    @Override
    public List<RegionDto> toListDto(List<Region> regions) {
        if ( regions == null ) {
            return null;
        }

        List<RegionDto> list = new ArrayList<RegionDto>( regions.size() );
        for ( Region region : regions ) {
            list.add( toDto( region ) );
        }

        return list;
    }
}
