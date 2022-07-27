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

@Entity
@Table(name = "family")
@Data
@JsonView({View.Response.class})
public class Family implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "idFAMILY")
	@Id
	private Long idFamily;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "FRAMEWORK_idFRAMEWORK")
	private Long idFramework;
	
	@OneToMany(mappedBy = "family")
	private List<Control> controls;
	
	@ManyToOne
	@JoinColumn(name = "FRAMEWORK_idFRAMEWORK",insertable = false,updatable=false)
	private Framework framework;
	
	
	
}
