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

@Entity
@Table(name = "framework")
@Data
@JsonView({View.Response.class})
public class Framework implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "idFRAMEWORK")
	@Id
	private Long idFramework;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "version")
	private String version;
	
	@OneToMany(mappedBy = "framework")
	private List<Family> familyList;
	
}
