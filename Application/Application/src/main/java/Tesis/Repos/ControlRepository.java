package Tesis.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Tesis.entity.Control;


public interface ControlRepository extends CrudRepository<Control,Long>{

	List<Control> findAll();
	
	List<Control> findByIdFamilyIn(List<Long> idFamilyList);
	
	List<Control> findByIdFamilyInAndIdControlNotIn(List<Long> idFamilyList,List<Long> idControl);
}
