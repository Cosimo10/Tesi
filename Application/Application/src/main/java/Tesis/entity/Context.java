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
@Table(name = "context")
@Data
@JsonView({View.Response.class})
public class Context implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "idContext")
	@Id
	private Long idContext;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "context")
	private List<Contextualization> contextualizations;
	
	
	
	
}
