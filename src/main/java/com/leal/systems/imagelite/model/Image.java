package com.leal.systems.imagelite.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.leal.systems.imagelite.model.enums.ImageExtension;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "tb_image")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column
	private String name;
	@Column
	private Long size;
	@Column
	@Enumerated(EnumType.STRING)
	private ImageExtension extension;
	@Column
	@CreatedDate
	private LocalDateTime updateDate;
	@Column
	private String tabs;
	@Column
	@Lob
	private byte[] file;
	

	public Image() {
	}
	
	public Image(String name, Long size, ImageExtension extension, String tabs, byte[] file) {
		this.name = name;
		this.size = size;
		this.extension = extension;
		this.tabs = tabs;
		this.file = file;
	}

	public String getFileName() {
		return getName().concat(".").concat(getExtension().name());
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public ImageExtension getExtension() {
		return extension;
	}

	public void setExtension(ImageExtension extension) {
		this.extension = extension;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getTabs() {
		return tabs;
	}

	public void setTabs(String tabs) {
		this.tabs = tabs;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(file);
		result = prime * result + Objects.hash(extension, id, name, size, tabs, updateDate);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return extension == other.extension && Arrays.equals(file, other.file) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(size, other.size)
				&& Objects.equals(tabs, other.tabs) && Objects.equals(updateDate, other.updateDate);
	}

}
