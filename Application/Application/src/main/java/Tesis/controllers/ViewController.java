package Tesis.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Tesis.Repos.AssessmentControlRepository;
import Tesis.Repos.AssessmentPlanRepository;
import Tesis.Repos.ControlRepository;
import Tesis.Repos.FamilyRepository;
import Tesis.Repos.FrameworkRepository;
import Tesis.entity.AssessmentControl;
import Tesis.entity.AssessmentPlan;
import Tesis.entity.Control;
import Tesis.entity.Family;
import Tesis.entity.Framework;

@Controller
public class ViewController {
	
	@Value("${msg.title}")
	private String title;
	
	@Autowired
	private FrameworkRepository frameRepo;
	
	@Autowired
	private ControlRepository controlRepos;
	
	@Autowired
	private AssessmentPlanRepository assessmentPlanRepo;
	
	@Autowired
	private AssessmentControlRepository assessmentControlRepo;
	
	@Autowired
	private FamilyRepository familyRepos;
	
	 
	 
	@GetMapping(value = {"/","/index"})
	public String index(Model model) {
		List<AssessmentPlan> assessmentList = assessmentPlanRepo.findAll();
		model.addAttribute("assessmentList",assessmentList);
	    return "listaAssessment";
	}
	
	@GetMapping(value = {"/menuFramework/{id}"})
	public String menuFramework(Model model,@PathVariable Long id) {
		List<Framework> frameworkList = frameRepo.findAll();
		model.addAttribute("frameworks",frameworkList);
		model.addAttribute("idAssessment",id);
	    return "menuFramework";
	}
	
	@GetMapping(value = {"/listaAssessmentPlan"})
	public String listaAssessmentPlan(Model model) {
		List<AssessmentPlan> assessmentList = assessmentPlanRepo.findAll();
		model.addAttribute("assessmentList",assessmentList);
	    return "listaAssessment";
	}
	
	
	
	@GetMapping(value = {"/assessmentPlan/add"})
	public String showAddContact(Model model) {
	    AssessmentPlan contact = new AssessmentPlan();
	    model.addAttribute("assessmentPlan", contact);
	 
	    return "addAssessment";
	}
	
	@GetMapping(value = {"/control/add/{id}/{idAssessment}"})
	public String showAddControl(Model model,@PathVariable("id") Long id,@PathVariable("idAssessment") Long idAssessment) {
		AssessmentPlan assessment = assessmentPlanRepo.findById(Long.valueOf(idAssessment)).get();
		List<AssessmentControl> controlImplemented = new ArrayList<>();
		List<Long> idControlImplemented = new ArrayList<>();
		if(assessment.getAssessmentControlList() != null && !assessment.getAssessmentControlList().isEmpty()) {
			assessment.getAssessmentControlList().forEach(assessmentcontrol ->{
				controlImplemented.add(assessmentcontrol);
				assessmentcontrol.setControl(controlRepos.findById(assessmentcontrol.getIdControl()).get());
				idControlImplemented.add(assessmentcontrol.getIdControl());
			});
		}
		List<Family> familyListFamily = familyRepos.findByIdFramework(id);
		List<Long> familyList = new ArrayList<>();
		familyListFamily.forEach(fam ->{
			familyList.add(fam.getIdFamily());
		});
	    List<Control> controlList = new ArrayList<>();
	    if(!idControlImplemented.isEmpty()) {
	    	controlList = controlRepos.findByIdFamilyInAndIdControlNotIn(familyList, idControlImplemented);
	    }
	    else
	    	controlList = controlRepos.findByIdFamilyIn(familyList);
	    model.addAttribute("controlImplemented", controlImplemented);
	    model.addAttribute("controlList", controlList);
	    model.addAttribute("idAssessment", Long.valueOf(idAssessment));
	    model.addAttribute("idFramework", Long.valueOf(id));
	    return "addControl";
	}
	
	@GetMapping(value = {"/control/aggiungi/{idControl}/{idAssessment}/{idFramework}"})
	public String addControl(Model model,@PathVariable("idControl") Long idControl,@PathVariable("idAssessment") Long idAssessment,@PathVariable("idFramework") Long idFramework) {
		AssessmentControl assessmentControlSave = new AssessmentControl();
		Long id = 0l;
		for(int i = 0 ; i < assessmentControlRepo.findAll().size() ; i++) {
			if(id < assessmentControlRepo.findAll().get(i).getId()) {
				id = assessmentControlRepo.findAll().get(i).getId();
			}
			id++;
		}
		if(id == 0l) {
			id++;
		}
		assessmentControlSave.setId(id);
		assessmentControlSave.setIdControl(idControl);
		assessmentControlSave.setIdAssessmentPlan(idAssessment);
		assessmentControlSave.setStato("Implemented");
		assessmentControlRepo.save(assessmentControlSave);
		return showAddControl(model, idFramework, idAssessment);
	}
	
	@GetMapping(value = {"/control/pianifica/{idControl}/{idAssessment}/{idFramework}"})
	public String plannedControl(Model model,@PathVariable("idControl") Long idControl,@PathVariable("idAssessment") Long idAssessment,@PathVariable("idFramework") Long idFramework) {
		AssessmentControl assessmentControlSave = new AssessmentControl();
		Long id = 0l;
		for(int i = 0 ; i < assessmentControlRepo.findAll().size() ; i++) {
			if(id < assessmentControlRepo.findAll().get(i).getId()) {
				id = assessmentControlRepo.findAll().get(i).getId();
			}
			id++;
		}
		if(id == 0l) {
			id++;
		}
		assessmentControlSave.setId(id);
		assessmentControlSave.setIdControl(idControl);
		assessmentControlSave.setIdAssessmentPlan(idAssessment);
		assessmentControlSave.setStato("Planned");
		assessmentControlRepo.save(assessmentControlSave);
		return showAddControl(model, idFramework, idAssessment);
	}
	 
	
	
	@PostMapping(value = "/assessmentPlan/add")
	public String addAssessmentPlan(Model model,
	        @ModelAttribute("assessmentPlan") AssessmentPlan assessmentPlan) throws Exception {        
	    try {
	    	if(!assessmentPlanRepo.findAll().isEmpty()){
	    		Long id = 0l;
	    		for(int i = 0 ; i < assessmentPlanRepo.findAll().size(); i++) {
	    			if(id < assessmentPlanRepo.findAll().get(i).getId()) {
	    				id = assessmentPlanRepo.findAll().get(i).getId();
	    			}
	    		}
	    		assessmentPlan.setId(id+1);
	    	}
	    	else {
	    		assessmentPlan.setId(1l);
	    	}
	    	assessmentPlanRepo.save(assessmentPlan);
	        return "redirect:/listaAssessmentPlan/";
	    } catch (Exception ex) {
	        throw new Exception(ex.getMessage());
	    }    
	    
	}
	
	@GetMapping(value = {"/deleteAssessmentPlan/{id}"})
	public String deleteAssessmentPlan(Model model,@PathVariable Long id) {
		Optional<AssessmentPlan> assessmentOpt = assessmentPlanRepo.findById(id);
		
		if(assessmentOpt.isPresent()){
			if(!assessmentOpt.get().getAssessmentControlList().isEmpty()) {
				assessmentOpt.get().getAssessmentControlList().forEach(assessmentControl ->{
					assessmentControlRepo.delete(assessmentControl);
				});
			}
			assessmentPlanRepo.delete(assessmentOpt.get());
		}
		
	    return listaAssessmentPlan(model);
	}
	
	@GetMapping(value = {"/detailAssessmentPlan/{id}"})
	public String detailAssessmentPlan(Model model,@PathVariable Long id) {
		Optional<AssessmentPlan> assessmentOpt = assessmentPlanRepo.findById(id);
		model.addAttribute("assessmentPlan",assessmentOpt.get());
		model.addAttribute("assessmentControls",assessmentOpt.get().getAssessmentControlList());
	    return "assessmentById";
	}
	
	@GetMapping(value = {"/getControlsByIdFramework/{id}"})
	public String getControlsByIdFramework(Model model,@PathVariable Long id) {
		Optional<Framework> frameworkOpt = frameRepo.findById(id);
		List<Control> controlList = new ArrayList<>();
		if(frameworkOpt.isPresent()){
			List<Long> idList = new ArrayList<>();
			for(int i = 0 ; i < frameworkOpt.get().getFamilyList().size(); i++) {
				idList.add(frameworkOpt.get().getFamilyList().get(i).getIdFamily());
			}
			controlList = controlRepos.findByIdFamilyIn(idList);
		}
		model.addAttribute("controls",controlList);
		model.addAttribute("framework",frameworkOpt.get());
	    return "listaControlli";
	}

	
	@GetMapping(value = {"/control/rimuovi/{idAssessmentControl}/{idFramework}"})
	public String removeControl(Model model,@PathVariable Long idAssessmentControl,@PathVariable Long idFramework) {
		Optional<AssessmentControl> assessmControl = assessmentControlRepo.findById(idAssessmentControl);
		Long idAssessment = 0l;
		if(assessmControl.isPresent()) {
			idAssessment = assessmControl.get().getIdAssessmentPlan();
			assessmentControlRepo.delete(assessmControl.get());
		}
	    return showAddControl(model, idFramework, idAssessment);
	}
	
	@GetMapping(value = {"/control/cambiaStato/{idAssessmentControl}/{idFramework}"})
	public String cambiaStatoControl(Model model,@PathVariable Long idAssessmentControl,@PathVariable Long idFramework) {
		Optional<AssessmentControl> assessmControl = assessmentControlRepo.findById(idAssessmentControl);
		Long idAssessment = 0l;
		if(assessmControl.isPresent()) {
			idAssessment = assessmControl.get().getIdAssessmentPlan();
			if(assessmControl.get().getStato().equals("Planned")) {
			assessmControl.get().setStato("Implemented");
			}
			else {
				assessmControl.get().setStato("Planned");
			}
			assessmentControlRepo.save(assessmControl.get());
		}
	    return showAddControl(model, idFramework, idAssessment);
	}
	
}
