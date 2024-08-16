package com.imagesuploadtest.adminPage.accommodation.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomForm {
    private List<AccommodationDto> rooms;
}
