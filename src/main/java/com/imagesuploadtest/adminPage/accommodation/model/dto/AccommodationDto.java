package com.imagesuploadtest.adminPage.accommodation.model.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccommodationDto {

    private int accommodationNo;
    private List<String> accommodationName;
    private List<String> accommodationType;
    private String accommodationInfo;

    private List<Integer> maxOccupation;
    private List<Integer> standardOccupation;

    private List<Integer> roomCount;

    private int basicDayNo;
    private List<String> roomName;

    private List<Integer> weekdayRate;
    private List<Integer> fridayRate;
    private List<Integer> saturdayRate;
    private List<Integer> sundayRate;
    // 전번
    private String accommodationPhone;

    // 도로명 주소
    private String roadName;


    // 지번 주소
    private String region;


    // 위도
    private String lat;

    // 경도
    private String lon;


    /**
     * 업소 사진
     */

    private String endIndex;
//    private List<MultipartFile> previewFiles;
//    private List<AccommodationImageDto> images;

}
