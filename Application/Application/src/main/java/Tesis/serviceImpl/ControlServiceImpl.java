package Tesis.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Tesis.Repos.ControlRepository;
import Tesis.Repos.FamilyRepository;
import Tesis.dto.InputDTO;
import Tesis.dto.ResponseDTO;
import Tesis.entity.Control;
import Tesis.entity.Control_;
import Tesis.entity.Family;
import Tesis.services.ControlService;

@Transactional
@Service
public class ControlServiceImpl implements ControlService{
	
	@Autowired
	private ControlRepository controlRepo;
	
	@Autowired
	private FamilyRepository familyRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	@Override
	public List<ResponseDTO> getAllControls() {
		List<ResponseDTO> responseList = new ArrayList<>();
		ResponseDTO responseGetAll = new ResponseDTO();
		try {
		List<InputDTO> getAllList = new ArrayList<>();
		List<Control> controlsList = controlRepo.findAll();
		for(int i = 0 ; i < controlsList.size(); i++) {
			InputDTO tmpGetAll = new InputDTO();
			tmpGetAll.setIdControl(controlsList.get(i).getIdControl());
			tmpGetAll.setDescControl(controlsList.get(i).getDescription());
			tmpGetAll.setNameControl(controlsList.get(i).getName());
			if(controlsList.get(i).getFamily() != null) {
			tmpGetAll.setIdFamily(controlsList.get(i).getFamily().getIdFamily());
			tmpGetAll.setNameFamily(controlsList.get(i).getFamily().getName());
			tmpGetAll.setDescFamily(controlsList.get(i).getFamily().getDescription());
			}
			getAllList.add(tmpGetAll);
		}
		InputDTO[] getAllArr = new InputDTO[getAllList.size()];
		responseGetAll.setResultCode("ok");
		responseGetAll.setResultObj(getAllList.toArray(getAllArr));
		}
		catch(Exception e) {
			responseGetAll.setResultCode("ko");
		}
		responseList.add(responseGetAll);
		return responseList;
	}
	
	@Override
	public List<ResponseDTO> upsertControl(InputDTO idInsertDTO) {
		List<ResponseDTO> responseList = new ArrayList<>();
		ResponseDTO responseInsert = new ResponseDTO();
		try {
		if(idInsertDTO.getIdFamily() != null) {
			Family familyUpsert = new Family();
			familyUpsert.setIdFamily(idInsertDTO.getIdFamily());
			familyUpsert.setName(idInsertDTO.getNameFamily());
			familyUpsert.setDescription(idInsertDTO.getDescFamily());
			familyUpsert.setIdFramework(idInsertDTO.getIdFramework());
			familyRepo.save(familyUpsert);
		}
		Control controlUpsert = new Control();
		controlUpsert.setIdControl(idInsertDTO.getIdControl());
		controlUpsert.setIdFamily(idInsertDTO.getIdFamily());
		controlUpsert.setName(idInsertDTO.getNameControl());
		controlUpsert.setDescription(idInsertDTO.getDescControl());
		controlRepo.save(controlUpsert);
		responseInsert.setResultCode("ok");
		}
		catch(Exception e) {
			responseInsert.setResultCode("ko");
		}
		responseList.add(responseInsert);
		return responseList;
	}
	
	
	@Override
	public List<ResponseDTO> getById(InputDTO idGetByIdDTO) {
		List<ResponseDTO> responseList = new ArrayList<>();
		ResponseDTO responseGetById = new ResponseDTO();
		try {
			if(idGetByIdDTO.getIdControl() != null && controlRepo.findById(idGetByIdDTO.getIdControl()).isPresent()) {
				Control controlDb = controlRepo.findById(idGetByIdDTO.getIdControl()).get();
				InputDTO getByIdResp = new InputDTO();
				getByIdResp.setIdControl(controlDb.getIdControl());
				getByIdResp.setDescControl(controlDb.getDescription());
				getByIdResp.setNameControl(controlDb.getName());
				if(controlDb.getFamily()!= null) {
				getByIdResp.setIdFamily(controlDb.getFamily().getIdFamily());
				getByIdResp.setDescFamily(controlDb.getFamily().getDescription());
				getByIdResp.setNameFamily(controlDb.getFamily().getName());
				if(controlDb.getFamily().getFramework() != null)
				getByIdResp.setIdFramework(controlDb.getFamily().getFramework().getIdFramework());
				getByIdResp.setDescFramework(controlDb.getFamily().getFramework().getName());
				}
				InputDTO[] getByIdRespArr = new InputDTO[1];
				getByIdRespArr[0] = getByIdResp;
				responseGetById.setResultCode("ok");
				responseGetById.setResultObj(getByIdRespArr);
			}
			else {
				responseGetById.setResultCode("ok");
				responseGetById.setResultObj(null);
			}
		}
		catch(Exception e) {
			responseGetById.setResultCode("ko");
		}
		responseList.add(responseGetById);
		return responseList;
	}
	
	
	@Override
	public List<ResponseDTO> deleteControl(InputDTO idDeleteDTO) {
		List<ResponseDTO> responseList = new ArrayList<>();
		ResponseDTO responseGetById = new ResponseDTO();
		try {
			if(idDeleteDTO.getIdControl() != null) {
				Optional<Control> controlOpt = controlRepo.findById(idDeleteDTO.getIdControl());
				if(controlOpt.isPresent()) {
					controlRepo.delete(controlOpt.get());
				}
			}
			responseGetById.setResultCode("ok");
		}
		catch(Exception e) {
			responseGetById.setResultCode("ko");
		}
		responseList.add(responseGetById);
		return responseList;
	}
	
	
	@Override
	public List<ResponseDTO> searchControl(InputDTO idSearchDTO) {
		List<ResponseDTO> responseList = new ArrayList<>();
		ResponseDTO responseSearchId = new ResponseDTO();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Control> query = builder.createQuery(Control.class);
		Root<Control> root = query.from(Control.class);
		List<Predicate> predicates = new ArrayList<>();
		try {
			if(idSearchDTO.getIdFamily() != null) {
				predicates.add(builder.equal(root.get(Control_.FAMILY_idFAMILY),idSearchDTO.getIdFamily()));
			}
			if(idSearchDTO.getDescControl() != null) {
				predicates.add(builder.like(builder.upper(root.get(Control_.DESCRIPTION)),"%"+idSearchDTO.getDescControl().toUpperCase()+"%"));
			}
			if(idSearchDTO.getNameControl() != null) {
				predicates.add(builder.like(builder.upper(root.get(Control_.NAME)),"%"+idSearchDTO.getNameControl().toUpperCase()+"%"));
			}
			predicates.add(builder.notEqual(root.get(Control_.FAMILY_idFAMILY),-1l));
			query = query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			entityManager.createQuery(query).getResultList();
			List<Control> controlList = entityManager.createQuery(query).getResultList();
			responseSearchId = responseListSearch(controlList, responseSearchId);
		}
		catch(Exception e) {
			responseSearchId.setResultCode("ko");
		}
		responseList.add(responseSearchId);
		return responseList;
	}
	
	private ResponseDTO responseListSearch(List<Control> controlsList,ResponseDTO response){
		List<InputDTO> getAllList = new ArrayList<>();
		for(int i = 0 ; i < controlsList.size(); i++) {
			InputDTO tmpGetAll = new InputDTO();
			tmpGetAll.setIdControl(controlsList.get(i).getIdControl());
			tmpGetAll.setDescControl(controlsList.get(i).getDescription());
			tmpGetAll.setNameControl(controlsList.get(i).getName());
			if(controlsList.get(i).getFamily() != null) {
			tmpGetAll.setIdFamily(controlsList.get(i).getFamily().getIdFamily());
			tmpGetAll.setNameFamily(controlsList.get(i).getFamily().getName());
			tmpGetAll.setDescFamily(controlsList.get(i).getFamily().getDescription());
			}
			getAllList.add(tmpGetAll);
		}
		InputDTO[] getAllArr = new InputDTO[getAllList.size()];
		response.setResultCode("ok");
		response.setResultObj(getAllList.toArray(getAllArr));
		return response;
	}
	
	
	
}
