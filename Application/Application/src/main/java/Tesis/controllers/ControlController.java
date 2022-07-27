package Tesis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Tesis.dto.InputDTO;
import Tesis.dto.ResponseDTO;
import Tesis.services.ControlService;




@RestController
@RequestMapping(value = "/controls",produces = { MediaType.APPLICATION_JSON_VALUE})
public class ControlController {

	@Autowired
	private ControlService controlService;
	
	
	
	@RequestMapping(value = "/getAll",method = RequestMethod.GET)
	public @ResponseBody List<ResponseDTO> getAllControls() {
		return controlService.getAllControls();
	}
	
	@RequestMapping(value = "/upsertControl",method = RequestMethod.POST)
	public @ResponseBody List<ResponseDTO> upsertControl(@RequestBody InputDTO idInsertDTO) {
		return controlService.upsertControl(idInsertDTO);
	}
	
	@RequestMapping(value = "/searchById",method = RequestMethod.POST)
	public @ResponseBody List<ResponseDTO> searchById(@RequestBody InputDTO idSearchByIdDTO) {
		return controlService.getById(idSearchByIdDTO);
	}
	
	@RequestMapping(value = "/deleteControl",method = RequestMethod.POST)
	public @ResponseBody List<ResponseDTO> deleteControl(@RequestBody InputDTO idControlDeleteDTO) {
		return controlService.deleteControl(idControlDeleteDTO);
	}
	
	@RequestMapping(value = "/searchControl",method = RequestMethod.POST)
	public @ResponseBody List<ResponseDTO> searchControl(@RequestBody InputDTO idControlSearchDTO) {
		return controlService.searchControl(idControlSearchDTO);
	}
}
