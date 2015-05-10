package uk.co.blc_services.smartgenie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import uk.co.blc_services.smartgenie.domain.Job;
import uk.co.blc_services.smartgenie.rest.JobRepository;

@Controller
@SessionAttributes("job")
public class JobController{
	
	@Autowired
	private JobRepository repo;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value = "/web/jobs/list", method = RequestMethod.GET)
	public String jobList(Model model) {
		Iterable<Job> jobs = repo.findAll();
		model.addAttribute("jobs", jobs);
		return "job-list";
	}

	@RequestMapping(value = "/web/jobs/edit", method = RequestMethod.GET)
	public String jobEditForm(@RequestParam(value = "id", required = true) long id, Model model) {
		model.addAttribute("editing", true);
		model.addAttribute("job", repo.findOne(id));
		return "job-detail";
	}
	
	@RequestMapping(value = "/web/jobs/add", method = RequestMethod.GET)
	public String jobAddForm(Model model) {
		model.addAttribute("job", new Job());
		return "job-detail";
	}
	
	@RequestMapping(value = {"/web/jobs/add", "/web/jobs/edit"}, method = RequestMethod.POST)
	public String jobSubmit(@ModelAttribute Job toAdd, Model model) {
		Job saved = repo.save(toAdd);
		model.addAttribute("job", toAdd);
		model.addAttribute("message", "Saved Job : "+saved.getId());
		return jobList(model);//TODO is this the right way to redirect?
	}
	
	@RequestMapping(value = {"/web/jobs/delete"}, method = RequestMethod.GET)
	public String jobDelete(long id, Model model) {
		repo.delete(id);
		model.addAttribute("message", "Deleted Job : "+id);
		return jobList(model);
	}

/*    @RequestMapping(value = PATH)
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }*/
	
	
}
