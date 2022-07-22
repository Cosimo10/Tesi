package Tesis.dto;

import com.fasterxml.jackson.annotation.JsonView;

import Tesis.entity.View;
import lombok.Data;


@JsonView({View.Response.class})
@Data
public class ResponseDTO {
	private String resultCode;
	
	private Object[] resultObj;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Object[] getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object[] resultObj) {
		this.resultObj = resultObj;
	}
}
