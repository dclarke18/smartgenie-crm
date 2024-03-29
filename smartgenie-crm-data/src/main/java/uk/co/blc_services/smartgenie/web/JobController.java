package uk.co.blc_services.smartgenie.web;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;

import uk.co.blc_services.smartgenie.domain.Job;
import uk.co.blc_services.smartgenie.io.HeadOfficeEmailParser;
import uk.co.blc_services.smartgenie.rest.JobRepository;

@Controller
@SessionAttributes("job")
@RequestMapping("/web/jobs")
public class JobController implements ServletContextAware {
	
	@Autowired
	private JobRepository repo;
	
	public static final String ATTRIB_RELEASE_VERSION = "releaseVersion";
	public static final String DEFAULT_VERSION = "UNRELEASED";

	/* (non-Javadoc)
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext ctx) {
		String version = null;
	        Package aPackage = getClass().getPackage();
	        if (aPackage != null) {
	            version = aPackage.getImplementationVersion();
	            if (version == null) {
	                version = aPackage.getSpecificationVersion();
	            }
	        }
	    if (version == null) {
	        // we could not compute the version so use a blank
	        version = DEFAULT_VERSION;
	    }
	    
		ctx.setAttribute(ATTRIB_RELEASE_VERSION, version);

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String jobList(Model model) {
		Iterable<Job> jobs = repo.findAll();
		model.addAttribute("jobs", jobs);
		return "job-list";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String jobEditForm(@RequestParam(value = "id", required = true) long id, Model model) {
		model.addAttribute("editing", true);
		model.addAttribute("job", repo.findOne(id));
		return "job-detail";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String jobAddForm(Model model) {
		model.addAttribute("job", new Job());
		return "job-detail";
	}
	
	@RequestMapping(value = {"/add", "/edit"}, method = RequestMethod.POST)
	public String jobSubmit(@ModelAttribute Job toAdd, Model model) {
		Job saved = repo.save(toAdd);
		model.addAttribute("job", toAdd);
		model.addAttribute("message", "Saved Job : "+saved.getId());
		return jobList(model);//TODO is this the right way to redirect?
	}
	
	@RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
	public String jobDelete(long id, Model model) {
		repo.delete(id);
		model.addAttribute("message", "Deleted Job : "+id);
		return jobList(model);
	}
	
	@RequestMapping(value = "/parse", method = RequestMethod.GET)
	public String jobEmailForm(Model model) {
		model.addAttribute("job", new Job());
		return "email-to-job";
	}
	
	@RequestMapping(value = {"/parse"}, method = RequestMethod.POST)
	public String jobEmailSubmit(@RequestParam String orderEmail, Model model) {
		HeadOfficeEmailParser parser = new HeadOfficeEmailParser();
		Job toAdd = parser.parseEmail(orderEmail);
		model.addAttribute("job", toAdd);
		return "job-detail";
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
