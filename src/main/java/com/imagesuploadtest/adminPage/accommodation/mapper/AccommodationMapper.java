package com.imagesuploadtest.adminPage.accommodation.mapper;

import com.imagesuploadtest.adminPage.accommodation.model.dto.AccommodationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccommodationMapper {

    void enrollAccommodation(AccommodationDto accommodationDto);
}
