package Tesis.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Tesis.entity.Family;


public interface FamilyRepository extends CrudRepository<Family,Long>{
	List<Family> findByIdFramework(Long id);
}
