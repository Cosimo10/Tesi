package Tesis.dto;

import com.fasterxml.jackson.annotation.JsonView;

import Tesis.entity.View;
import lombok.Data;

@Data
@JsonView({View.Response.class})
public class InputDTO {

	private Long idControl;
	
	private String nameControl;
	
	private String descControl;
	
	private Long idFamily;
	
	private Long idFramework;
	
	private String descFramework;
	
	private String descFamily;
	
	private String nameFamily;

	public Long getIdControl() {
		return idControl;
	}

	public void setIdControl(Long idControl) {
		this.idControl = idControl;
	}

	public String getNameControl() {
		return nameControl;
	}

	public void setNameControl(String nameControl) {
		this.nameControl = nameControl;
	}

	public String getDescControl() {
		return descControl;
	}

	public void setDescControl(String descControl) {
		this.descControl = descControl;
	}

	public Long getIdFamily() {
		return idFamily;
	}

	public void setIdFamily(Long idFamily) {
		this.idFamily = idFamily;
	}

	public String getDescFamily() {
		return descFamily;
	}

	public void setDescFamily(String descFamily) {
		this.descFamily = descFamily;
	}

	public String getNameFamily() {
		return nameFamily;
	}

	public void setNameFamily(String nameFamily) {
		this.nameFamily = nameFamily;
	}
	
	
	
}
