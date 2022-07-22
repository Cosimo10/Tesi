package Tesis.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Tesis.entity.AssessmentPlan;


public interface AssessmentPlanRepository extends CrudRepository<AssessmentPlan,Long>{
	List<AssessmentPlan> findAll();
	
	
}
