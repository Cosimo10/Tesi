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
@Table(name = "contextualization")
@Data
@JsonView({View.Response.class})
public class Contextualization implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CONTEXT_idCONTEXT")
	@Id
	private Long idContext;
	
	@Column(name = "SUBCATEGORY_idSUBCATEGORY")
	@Id
	private Long idSubcategory;
	
	
	@ManyToOne
	@JoinColumn(name = "CONTEXT_idCONTEXT",insertable = false,updatable=false)
	private Context context;
	
	@ManyToOne
	@JoinColumn(name = "SUBCATEGORY_idSUBCATEGORY",insertable = false,updatable=false)
	private Subcategory subcategory;
	
	
	
}
