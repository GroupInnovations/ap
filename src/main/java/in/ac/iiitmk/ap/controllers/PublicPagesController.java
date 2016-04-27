package in.ac.iiitmk.ap.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import in.ac.iiitmk.ap.controllers.handlers.*;

@Controller
public class PublicPagesController {
	
	@RequestMapping (value = {"/", "/home"}, method = RequestMethod.GET)
	public Page showHomePage () {
		return Page.PAGE_PUBLIC_HOME;
	}
}
