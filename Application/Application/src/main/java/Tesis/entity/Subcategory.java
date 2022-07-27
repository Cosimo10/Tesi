package Tesis.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "subcategory")
@JsonView({View.Response.class})
@Data
@Getter
@Setter
public class Subcategory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "idSUBCATEGORY")
	@Id
	private Long idSUBCATEGORY;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	
	@Column(name = "CATEGORY_idCATEGORY")
	private Long idCATEGORY;
	
	@OneToMany(mappedBy = "subcategory")
	private List<Contextualization> contextualizations;
	

}
