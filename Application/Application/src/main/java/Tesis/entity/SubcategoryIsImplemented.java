package Tesis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Entity
@Table(name = "subcategory_is_implemented")
@Data
@JsonView({View.Response.class})
public class SubcategoryIsImplemented implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CONTROL_idCONTROL")
	@Id
	private Long idControl;
	
	@Column(name = "SUBCATEGORY_idSUBCATEGORY")
	@Id
	private Long idSubcategory;
	
	
	@ManyToOne
	@JoinColumn(name = "CONTROL_idCONTROL",insertable = false,updatable=false)
	private Control control;
	
	@ManyToOne
	@JoinColumn(name = "SUBCATEGORY_idSUBCATEGORY",insertable = false,updatable=false)
	private Subcategory subcategory;
	
	
	
}
