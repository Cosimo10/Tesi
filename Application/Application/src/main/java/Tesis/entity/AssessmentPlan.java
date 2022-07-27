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
@Table(name = "assessmentplan")
@Data
@JsonView({View.Response.class})
public class AssessmentPlan implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	@Id
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "assessmentPlan")
	private List<AssessmentControl> assessmentControlList;
	
	
	
	
}
