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
@Table(name = "assessmentcontrol")
@Data
@JsonView({View.Response.class})
public class AssessmentControl implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	@Id
	private Long id;
	
	@Column(name = "idAssessmentPlan")
	private Long idAssessmentPlan;
	
	@Column(name = "idControl")
	private Long idControl;
	
	@Column(name = "stato")
	private String stato;
	
	
	@ManyToOne
	@JoinColumn(name = "idAssessmentPlan",insertable = false,updatable=false,referencedColumnName = "id")
	private AssessmentPlan assessmentPlan;
	
	@ManyToOne
	@JoinColumn(name = "idControl",insertable = false,updatable=false)
	private Control control;
	
	
	
}
