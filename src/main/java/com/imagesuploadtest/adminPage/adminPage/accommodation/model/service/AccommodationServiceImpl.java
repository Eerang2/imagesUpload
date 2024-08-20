package com.imagesuploadtest.adminPage.adminPage.accommodation.model.service;


import kr.co.swm.adminPage.accommodation.mapper.AccommodationMapper;
import kr.co.swm.adminPage.accommodation.model.dto.AccommodationDto;
import kr.co.swm.adminPage.accommodation.model.dto.AccommodationImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationMapper mapper;

    @Autowired
    public AccommodationServiceImpl(AccommodationMapper mapper) {
        this.mapper = mapper;
    }


    // 업소 인입
    // 업소 메인 사진 인입
    // 객실 인입 ( 1 )
    // 객실 사진 인입 ( 1, 1 )
//                 ( 1, 2 )
    @Override
    public int saveAccommodation(AccommodationDto accommodationDto, AccommodationImageDto mainImage) {

        int no = accommodationDto.getAcAdminNo();
        no++;

        int result = mapper.enrollAccommodation(accommodationDto, no) ;
        if (result == 1) {
            System.out.println("origin : " + mainImage.getUploadOriginName());
            System.out.println("unique : " + mainImage.getUploadUniqueName());
            System.out.println("path : " + mainImage.getUploadImagePath());
            for (int i = 0; i < accommodationDto.getAccommodationType().size(); i++) {
                String facility = accommodationDto.getAccommodationType().get(i);
                System.out.println(accommodationDto.getAccommodationType().get(i));
                mapper.enrollFacilities(facility,no);
            }
            return mapper.enrollMainImage(mainImage, no);
        } else {
            return 0;
        }
    }

    @Override
    public int enrollRooms(AccommodationDto  accommodationDto, String roomCategory, String roomName, String checkIn, String checkOut, int roomValue, AccommodationDto roomRate) {
        int no = accommodationDto.getAcAdminNo();
        int categoryNo = 0;
        System.out.println("service : " + roomCategory);
        if ("오션뷰".equals(roomCategory)) {
            categoryNo = 1;
        } else if ("리버뷰".equals(roomCategory)) {
            categoryNo = 2;
        } else if ("시티뷰".equals(roomCategory)) {
            categoryNo = 3;
        } else if ("마운틴뷰".equals(roomCategory)) {
            categoryNo = 4;
        }
        System.out.println("category NO : " + categoryNo);
        System.out.println("v : " + accommodationDto.getRoomValues());

        int result1 = 0;
        for (int i = 1; i <= roomValue; i++) {
            int result = mapper.enrollRoom(accommodationDto, no, categoryNo, roomName, checkIn, checkOut);
            System.out.println("roomNo : " +accommodationDto.getRoomNo());
            ArrayList<Integer> roomNos = new ArrayList<>();

            int roomNo = accommodationDto.getRoomNo();
            roomNos.add(roomNo);

            if (result == 1) {
                mapper.enrollWeekdayRate(roomRate, roomNo, no);
                mapper.enrollFridayRate(roomRate, roomNo, no);
                mapper.enrollSaturdayRate(roomRate, roomNo, no);
                result1 = mapper.enrollSundayRate(roomRate, roomNo, no);
            }else {
                return result1;
            }
        }


        System.out.println("NO : " + categoryNo);
        System.out.println("nameService : " + roomName);
        System.out.println("standard : " + accommodationDto.getStandardOccupation());

        return result1;
    }


    @Override
    public void enrollSubImages(AccommodationImageDto roomImages, AccommodationDto accommodationDto) {
        mapper.enrollRoomImages(roomImages);
    }

}

