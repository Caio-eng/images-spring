package com.leal.systems.imagelite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leal.systems.imagelite.model.Image;

public interface ImageRepository extends JpaRepository<Image, String>{

}
