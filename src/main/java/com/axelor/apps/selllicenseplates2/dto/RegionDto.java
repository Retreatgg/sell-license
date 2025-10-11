package com.axelor.apps.selllicenseplates2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDto {

    private Long id;
    private String regionName;
    private String regionCode;

}
