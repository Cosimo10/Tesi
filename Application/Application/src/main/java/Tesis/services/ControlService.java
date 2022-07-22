package Tesis.services;

import java.util.List;

import Tesis.dto.InputDTO;
import Tesis.dto.ResponseDTO;

public interface ControlService {
	List<ResponseDTO> getAllControls();
	
	List<ResponseDTO> upsertControl(InputDTO idInsertDTO);
	
	List<ResponseDTO> getById(InputDTO idGetByIdDTO);
	
	List<ResponseDTO> deleteControl(InputDTO idDeleteDTO);
	
	List<ResponseDTO> searchControl(InputDTO idSearchDTO);
}
