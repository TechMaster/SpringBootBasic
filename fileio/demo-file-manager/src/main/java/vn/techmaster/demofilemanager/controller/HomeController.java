package vn.techmaster.demofilemanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.techmaster.demofilemanager.service.FileService;

@Controller
public class HomeController {
	@Autowired
	FileService fileService;
	
	@GetMapping("/")
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("rootDirName", FileService.DIR_NAME);
		if (request.isUserInRole("ROLE_ADMIN"))
			model.addAttribute("rootSafeDirName", FileService.SAFE_DIR_NAME);
		else
			model.addAttribute("rootSafeDirName", null);
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
