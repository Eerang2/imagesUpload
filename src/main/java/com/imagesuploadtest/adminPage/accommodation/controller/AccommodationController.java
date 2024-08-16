package com.imagesuploadtest.adminPage.accommodation.controller;


import com.imagesuploadtest.adminPage.accommodation.model.dto.AccommodationDto;
import com.imagesuploadtest.adminPage.accommodation.model.dto.RoomForm;
import com.imagesuploadtest.adminPage.accommodation.model.service.AccommodationServiceImpl;
import com.imagesuploadtest.adminPage.accommodation.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccommodationController {

    private final UploadFile uploadFile;
    private final AccommodationServiceImpl accommodationService;


    @Autowired
    public AccommodationController(UploadFile uploadFile, AccommodationServiceImpl accommodationService) {
        this.uploadFile = uploadFile;
        this.accommodationService = accommodationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/enroll1")
    public String enroll(Model model) {
        System.out.println("in");

        model.addAttribute("location", new AccommodationDto());
        return "accommodation/enroll";
    }


    @PostMapping("/save-location")
    public ResponseEntity<?> saveLocation(@RequestParam("previewFiles") List<MultipartFile> files,
//                                           @RequestParam("location") AccommodationDto accommodationDto,
                                          @RequestParam(value = "roomImages[]", required = false) List<MultipartFile> roomImages,
                                          @RequestParam MultiValueMap<String, String> rooms,
                                          @ModelAttribute RoomForm roomForm
                                           ) {

        List<AccommodationDto> room1 = roomForm.getRooms();


        System.out.println("name1 " + rooms.get("roomName"));
        System.out.println("aaaa " + rooms.get("maxOccupation"));
        System.out.println("cccc " + rooms.get("standardOccupation"));


        for (MultipartFile file : files) {
            System.out.println("name : " + file.getOriginalFilename() );
            for (AccommodationDto room : room1) {
                System.out.println("end : " + room.getEndIndex());
            }

        }
//        // endIndex 값들을 처리
//        allParams.forEach((key, value) -> {
//            if (key.contains("endIndex")) {
//                System.out.println("Key: " + key + ", Value: " + value);
//                // key 예시: rooms[1].endIndex
//                // value 예시: 2, 5 등
//            }
//        });
//
//        // 업로드된 파일들을 처리
//        previewFiles.forEach((key, files) -> {
//            System.out.println("Room Files Key: " + key);
//            for (MultipartFile file : files) {
//                System.out.println("File Name: " + file.getOriginalFilename());
//                // 파일 저장 로직 (예: file.transferTo(new File(...)))
//            }
//        });


//        System.out.println("=================================");
//
//        System.out.println("숙소 이름 : " + accommodationDto.getAccommodationName());
//
//        System.out.println("지번 : " + accommodationDto.getRegion());
//        System.out.println("도로명 : " + accommodationDto.getRoadName());
//        System.out.println("위도 : " + accommodationDto.getLat());
//        System.out.println("경도 : " + accommodationDto.getLon());
//
//
//        for (String item : accommodationDto.getAccommodationType()) {
//            System.out.println("부대시설 : " + item);
//        }
////
////        for (int i = 0; i < accommodationDto.getRoomCount().size(); i++) {
////            System.out.println(data.get("[i]"));
////        }
//
//        for (int i = 0; i < accommodationDto.getRoomCount().size() - 1; i++) {
//            System.out.println("방 이름 : " + accommodationDto.getRoomName().get(i) + "-" + accommodationDto.getRoomCount().get(i) + " 의 기준 : " + accommodationDto.getStandardOccupation().get(i) + " - 최대 : " + accommodationDto.getMaxOccupation().get(i));
//            System.out.println("주중 : " + accommodationDto.getWeekdayRate().get(i));
//            System.out.println("금 : " + accommodationDto.getFridayRate().get(i));
//            System.out.println("토 : " + accommodationDto.getSaturdayRate().get(i));
//            System.out.println("일 : " + accommodationDto.getSundayRate().get(i));
//            System.out.println();
//        }
//
//        // 받은 endIndex 값을 사용해 필요한 로직을 처리
////        System.out.println("Room 1 End Index: " + room1EndIndex);
////        System.out.println("Room 2 End Index: " + room2EndIndex);
////        System.out.println("Room 3 End Index: " + room0EndIndex);
//
//
//
//
//
//
//
////        for (int i = 0; i < 2; i++) {
//////            System.out.println("image : " + image.get(i));
////            System.out.println("file : " + imageFile.get(i));
////
////        }
//
//        for (int i = 0; i < accommodationDto.getRoomCount().size()-1  ; i++) {
//
//            System.out.println("방 number : "  + accommodationDto.getRoomCount().get(i));
//        }



//        System.out.println("=================================");





//
//        for(MultipartFile item : files) {
//            System.out.println(item.getResource().toString());
//
//            System.out.println(item.getName());
//            System.out.println(item.getContentType());
//            System.out.println(item.toString());
//        }
//


        Map<String, String> response = new HashMap<>();
        try {
//            List<AccommodationImageDto> images = uploadFile.upload(files);


//            int result = accommodationService.saveAccommodation(accommodationDto);
//            System.out.println( "result = " + result);

            response.put("message", "Upload successful!");
            // 데이터 저장 로직 수행
            response.put("success", "true");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();

            response.put("success", "false");
            response.put("message", "Error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

//    @PostMapping("/boardEnroll")
//    @ResponseBody
//    public ResponseEntity<Map<String, String>> enrollUsedBoard(@RequestParam("mainFile") MultipartFile mainFile,
//                                                               @RequestParam("previewFiles") List<MultipartFile> previewFiles,
//                                                               @ModelAttribute("usedBoard") UsedBoardDto usedBoard,
//                                                               Model model){
//        Map<String, String> response = new HashMap<>();
//        try {
//            model.addAttribute("kakaoMap", kakaoMap);
//
//            List<MultipartFile> mainImages = List.of(mainFile);
//            List<UsedBoardImageDto> images = uploadFile.upload(mainImages, previewFiles);
//            usedBoard.setImages(images);
//
//            int result = usedBoardService.enrollUsedBoard(usedBoard);
//
//            response.put("message", "Upload successful!");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.put("message", "Upload failed!");
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//                <div class="row mb-3">
//                    <label class="col-sm-2 col-form-label">이미지 업로드</label>
//                    <div class="col-sm-10">
//                        <input type="file" class="form-control image-upload" id="imageUpload-${roomCount}" multiple data-room-id="${roomCount}">
//                        <div class="image-preview" id="imagePreview-${roomCount}"></div>
//                    </div>
//                </div>
