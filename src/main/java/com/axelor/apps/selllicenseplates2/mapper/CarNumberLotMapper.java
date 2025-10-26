package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotAdminDto;
import com.axelor.apps.selllicenseplates2.model.CarNumberLot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, RegionMapper.class})
public interface CarNumberLotMapper {

    @Mapping(target = "fullName", source = "author.fullName")
    @Mapping(target = "regionCode", source = "region.regionCode")
    @Mapping(target = "price", source = "markupPrice")
    CarNumberLotDto toDto(CarNumberLot carNumberLot);

    @Mapping(target = "regionCode", source = "region.regionCode")
    @Mapping(target = "fullName", source = "author.fullName")
    CarNumberLotAdminDto toAdminDto(CarNumberLot carNumberLot);

    List<CarNumberLotDto> toListDto(List<CarNumberLot> carNumberLots);
    List<CarNumberLotAdminDto> toAdminDtoList(List<CarNumberLot> carNumberLots);


}
