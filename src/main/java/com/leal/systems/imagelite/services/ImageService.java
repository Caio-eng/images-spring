package com.leal.systems.imagelite.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leal.systems.imagelite.model.Image;
import com.leal.systems.imagelite.model.enums.ImageExtension;
import com.leal.systems.imagelite.repository.ImageRepository;

import jakarta.transaction.Transactional;

@Service
public class ImageService {

	@Autowired
	private ImageRepository repository;
	
	@Transactional
	public Image save(MultipartFile file, String name, List<String> tags) throws IOException {
		Image image = new Image(
				name, 
				file.getSize(), 
				ImageExtension.valueof(MediaType.valueOf(file.getContentType())), 
				String.join(",", tags), 
				file.getBytes()
				);
		
		return repository.save(image);
	}
	
	@Transactional
	public Optional<Image> getById(String id) {
		 return repository.findById(id);
	}
	
}
