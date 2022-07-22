package Tesis.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "control")
@JsonView({View.Response.class})
@Data
@Getter
@Setter
public class Control implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "idCONTROL")
	@Id
	private Long idControl;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "maturity_level")
	private String maturityLevel;
	
	@Column(name = "FAMILY_idFAMILY")
	private Long idFamily;
	
	@ManyToOne
	@JoinColumn(name = "FAMILY_idFAMILY",insertable = false,updatable=false)
	private Family family;
	
	@OneToMany(mappedBy = "control")
	private List<SubcategoryIsImplemented> subcatIsImplList;
	
	@OneToMany(mappedBy = "control")
	private List<AssessmentControl> assessmentControlList;
	
	
}
