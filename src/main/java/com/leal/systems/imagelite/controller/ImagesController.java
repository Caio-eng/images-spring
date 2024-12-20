package com.leal.systems.imagelite.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leal.systems.imagelite.model.Image;
import com.leal.systems.imagelite.services.ImageService;

@RestController
@RequestMapping("/images")
public class ImagesController {

	@Autowired
	private ImageService service;
	
	@PostMapping
	public ResponseEntity<?> save(
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("tags") List<String> tags 
			) throws IOException {
		
		System.out.println("Imagem recebida: name:  " + file.getOriginalFilename() + " size: " + file.getSize());
		
		Image savedImage = service.save(file, name, tags);
		URI imageUri = buildImageURL(savedImage);
		
		return ResponseEntity.created(imageUri).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
		var possibleImages = service.getById(id);
		if(possibleImages.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		var image = possibleImages.get();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(image.getExtension().getMediaType());
		headers.setContentLength(image.getSize());
		headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\"", image.getFileName());
		return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
	}
	
	private URI buildImageURL(Image image) {
		String imagePath = "/" + image.getId();
		return ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path(imagePath).build().toUri();
		}
}
