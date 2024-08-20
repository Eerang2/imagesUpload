package com.imagesuploadtest.adminPage.adminPage.accommodation.model.service;

import kr.co.swm.adminPage.accommodation.model.dto.AccommodationDto;
import kr.co.swm.adminPage.accommodation.model.dto.AccommodationImageDto;

public interface AccommodationService {

    int saveAccommodation(AccommodationDto accommodationDto, AccommodationImageDto mainImage);
    int enrollRooms(AccommodationDto  accommodationDto, String roomCategory, String roomName, String checkIn, String checkOut, int roomValue, AccommodationDto roomRate);
    void enrollSubImages(AccommodationImageDto roomImages, AccommodationDto accommodationDto);
}
