package Tesis.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Tesis.entity.AssessmentControl;


public interface AssessmentControlRepository extends CrudRepository<AssessmentControl,Long>{
	
	List<AssessmentControl> findAll();
	
}
