package com.imagesuploadtest.adminPage.accommodation.model.service;


import com.imagesuploadtest.adminPage.accommodation.model.dto.AccommodationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl {

//    private final AccommodationMapper mapper;

//    @Autowired
//    public AccommodationServiceImpl(AccommodationMapper mapper) {
//        this.mapper = mapper;
//    }


    // 업소 인입
    // 업소 메인 사진 인입
    // 객실 인입 ( 1 )
    // 객실 사진 인입 ( 1, 1 )
//                 ( 1, 2 )

    public int saveAccommodation(AccommodationDto accommodationDto, List<AccommodationDto> roomCount) {


//        mapper.enrollAccommodation(accommodationDto);

        for (AccommodationDto room : roomCount) {
            room.setAccommodationNo(accommodationDto.getAccommodationNo());
//            mapper.insertRoom(room);
//            for (AccommodationImageDto accommodation : accommodationDto.) {}

        }
        return 0;
    }
}


////        public int enrollUsedBoard (UsedBoardDto usedBoard){
////
////            // 보드 인입
////            int result = usedBoardMapper.usedBoardEnrollXML(usedBoard);
////            // ?
////            usedBoardMapper.usedBoardProductEnrollXML(usedBoard);
////
////            if (result > 0) {
////                // 갯수만큼 이미지 인입
////                for (UsedBoardImageDto image : usedBoard.getImages()) {
////                    // 새로 생성된 게시물 ID 설정
////                    image.setUsedBoardId(usedBoard.getUsedBoardId());
////                    imageMapper.usedBoardEnrollImageXML(image);
////                }
////            }
////            return result > 0 ? 1 : 0; // 성공하면 1, 실패하면 0
////        }
//    }

//}
















//    public int enrollUsedBoard(AccommodationDto usedBoard){
//
//        int result = mapper.usedBoardEnrollXML(usedBoard);
//        mapper.usedBoardProductEnrollXML(usedBoard);
//        if (result > 0) {
//            for (AccommodationDto image : usedBoard.getImages()) {
////                image.setUsedBoardId(usedBoard.getUsedBoardId()); // 새로 생성된 게시물 ID 설정
//                imageMapper.usedBoardEnrollImageXML(image);
//            }
//        }
//        return result > 0 ? 1 : 0; // 성공하면 1, 실패하면 0
//}

