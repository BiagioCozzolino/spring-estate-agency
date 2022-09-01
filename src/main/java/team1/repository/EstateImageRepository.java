package team1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team1.model.Estate;
import team1.model.EstateImage;

public interface EstateImageRepository extends CrudRepository<EstateImage, Integer>{

	public List<EstateImage> findByEstate(Estate estate);
}
