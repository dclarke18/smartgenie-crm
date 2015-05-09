package uk.co.blc_services.smartgenie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.blc_services.smartgenie.domain.Job;
import uk.co.blc_services.smartgenie.rest.JobRepository;

@Controller
public class JobController{
	
	@Autowired
	private JobRepository repo;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value = "/web/jobs/list", method = RequestMethod.GET)
	public String jobList(Model model) {
		Iterable<Job> jobs = repo.findAll(); //TODO add paging
		model.addAttribute("jobs", jobs);
		return "job-list";
	}
	
	@RequestMapping(value = "/web/jobs/add", method = RequestMethod.GET)
	public String jobForm(Model model) {
		model.addAttribute("job", new Job());
		return "job-add";
	}
	
	@RequestMapping(value = "/web/jobs/add", method = RequestMethod.POST)
	public String jobSubmit(Job toAdd, Model model) {
		Job saved = repo.save(toAdd);
		model.addAttribute("message", "Saved Job : "+saved.getId()+" - "+saved.getOrderId());
		return jobList(model);//TODO is this the right way to redirect?
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
