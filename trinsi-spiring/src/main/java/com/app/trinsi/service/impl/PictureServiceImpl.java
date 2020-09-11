package com.app.trinsi.service.impl;

import com.app.trinsi.repository.PictureRepository;
import com.app.trinsi.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void deletePicture(Long id) {
        this.pictureRepository.deleteById(id);
    }
}
