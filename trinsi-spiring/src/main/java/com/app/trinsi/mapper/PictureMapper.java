package com.app.trinsi.mapper;

import com.app.trinsi.dto.PictureDTO;
import com.app.trinsi.model.Picture;

public class PictureMapper {

    public static PictureDTO toDTO(Picture picture) {
        return new PictureDTO(picture.getId(), picture.getPath(), picture.getName());
    }

    public static Picture toPicture(PictureDTO pictureDTO) {
        return new Picture(pictureDTO.getId(), pictureDTO.getPath(), pictureDTO.getName());
    }
}
