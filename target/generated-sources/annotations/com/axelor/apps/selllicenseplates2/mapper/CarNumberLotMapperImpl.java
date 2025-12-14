package com.axelor.apps.selllicenseplates2.mapper;

import com.axelor.apps.selllicenseplates2.dto.CarNumberLotDto;
import com.axelor.apps.selllicenseplates2.dto.admin.CarNumberLotAdminDto;
import com.axelor.apps.selllicenseplates2.model.CarNumberLot;
import com.axelor.apps.selllicenseplates2.model.Region;
import com.axelor.apps.selllicenseplates2.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-14T16:49:24+0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class CarNumberLotMapperImpl implements CarNumberLotMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public CarNumberLotDto toDto(CarNumberLot carNumberLot) {
        if ( carNumberLot == null ) {
            return null;
        }

        CarNumberLotDto carNumberLotDto = new CarNumberLotDto();

        carNumberLotDto.setFullName( carNumberLotAuthorFullName( carNumberLot ) );
        carNumberLotDto.setRegionCode( carNumberLotRegionRegionCode( carNumberLot ) );
        carNumberLotDto.setPrice( carNumberLot.getMarkupPrice() );
        carNumberLotDto.setId( carNumberLot.getId() );
        carNumberLotDto.setFullCarNumber( carNumberLot.getFullCarNumber() );
        carNumberLotDto.setFirstLetter( carNumberLot.getFirstLetter() );
        carNumberLotDto.setSecondLetter( carNumberLot.getSecondLetter() );
        carNumberLotDto.setThirdLetter( carNumberLot.getThirdLetter() );
        carNumberLotDto.setFirstDigit( carNumberLot.getFirstDigit() );
        carNumberLotDto.setSecondDigit( carNumberLot.getSecondDigit() );
        carNumberLotDto.setThirdDigit( carNumberLot.getThirdDigit() );
        carNumberLotDto.setPhoneNumber( carNumberLot.getPhoneNumber() );
        carNumberLotDto.setCreatedDate( carNumberLot.getCreatedDate() );
        carNumberLotDto.setUpdatedDate( carNumberLot.getUpdatedDate() );
        carNumberLotDto.setComment( carNumberLot.getComment() );

        return carNumberLotDto;
    }

    @Override
    public CarNumberLotAdminDto toAdminDto(CarNumberLot carNumberLot) {
        if ( carNumberLot == null ) {
            return null;
        }

        CarNumberLotAdminDto carNumberLotAdminDto = new CarNumberLotAdminDto();

        carNumberLotAdminDto.setRegionCode( carNumberLotRegionRegionCode( carNumberLot ) );
        carNumberLotAdminDto.setFullName( carNumberLotAuthorFullName( carNumberLot ) );
        carNumberLotAdminDto.setId( carNumberLot.getId() );
        carNumberLotAdminDto.setFullCarNumber( carNumberLot.getFullCarNumber() );
        carNumberLotAdminDto.setFirstLetter( carNumberLot.getFirstLetter() );
        carNumberLotAdminDto.setSecondLetter( carNumberLot.getSecondLetter() );
        carNumberLotAdminDto.setThirdLetter( carNumberLot.getThirdLetter() );
        carNumberLotAdminDto.setFirstDigit( carNumberLot.getFirstDigit() );
        carNumberLotAdminDto.setSecondDigit( carNumberLot.getSecondDigit() );
        carNumberLotAdminDto.setThirdDigit( carNumberLot.getThirdDigit() );
        carNumberLotAdminDto.setOriginalPrice( carNumberLot.getOriginalPrice() );
        carNumberLotAdminDto.setMarkupPrice( carNumberLot.getMarkupPrice() );
        carNumberLotAdminDto.setPhoneNumber( carNumberLot.getPhoneNumber() );
        carNumberLotAdminDto.setCreatedDate( carNumberLot.getCreatedDate() );
        carNumberLotAdminDto.setUpdatedDate( carNumberLot.getUpdatedDate() );
        carNumberLotAdminDto.setComment( carNumberLot.getComment() );
        carNumberLotAdminDto.setIsConfirm( carNumberLot.getIsConfirm() );
        carNumberLotAdminDto.setAuthor( userMapper.toAdminDto( carNumberLot.getAuthor() ) );

        return carNumberLotAdminDto;
    }

    @Override
    public List<CarNumberLotDto> toListDto(List<CarNumberLot> carNumberLots) {
        if ( carNumberLots == null ) {
            return null;
        }

        List<CarNumberLotDto> list = new ArrayList<CarNumberLotDto>( carNumberLots.size() );
        for ( CarNumberLot carNumberLot : carNumberLots ) {
            list.add( toDto( carNumberLot ) );
        }

        return list;
    }

    @Override
    public List<CarNumberLotAdminDto> toAdminDtoList(List<CarNumberLot> carNumberLots) {
        if ( carNumberLots == null ) {
            return null;
        }

        List<CarNumberLotAdminDto> list = new ArrayList<CarNumberLotAdminDto>( carNumberLots.size() );
        for ( CarNumberLot carNumberLot : carNumberLots ) {
            list.add( toAdminDto( carNumberLot ) );
        }

        return list;
    }

    private String carNumberLotAuthorFullName(CarNumberLot carNumberLot) {
        if ( carNumberLot == null ) {
            return null;
        }
        User author = carNumberLot.getAuthor();
        if ( author == null ) {
            return null;
        }
        String fullName = author.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private String carNumberLotRegionRegionCode(CarNumberLot carNumberLot) {
        if ( carNumberLot == null ) {
            return null;
        }
        Region region = carNumberLot.getRegion();
        if ( region == null ) {
            return null;
        }
        String regionCode = region.getRegionCode();
        if ( regionCode == null ) {
            return null;
        }
        return regionCode;
    }
}
