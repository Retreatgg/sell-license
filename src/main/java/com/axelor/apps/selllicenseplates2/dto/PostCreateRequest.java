package com.axelor.apps.selllicenseplates2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

    private String title;
    private String description;
    private MultipartFile image;

}
