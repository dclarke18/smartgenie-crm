package uk.co.blc_services.smartgenie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.blc_services.smartgenie.domain.Job;
import uk.co.blc_services.smartgenie.rest.JobRepository;

@Controller
public class JobController {
	@Autowired
	private JobRepository repo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		Iterable<Job> jobs = repo.findAll(); //TODO add paging
		model.addAttribute("jobs", jobs);
		return "job-list";
	}
}
