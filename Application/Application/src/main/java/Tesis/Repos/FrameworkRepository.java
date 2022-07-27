package Tesis.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Tesis.entity.Framework;


public interface FrameworkRepository extends CrudRepository<Framework,Long>{
	List<Framework> findAll();
}
