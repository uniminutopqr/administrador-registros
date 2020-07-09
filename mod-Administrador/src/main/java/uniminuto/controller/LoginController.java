	package uniminuto.controller;

import javax.sound.midi.Soundbank;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniminuto.model.UserCredentials;
import uniminuto.service.loginService;

@Controller
public class LoginController {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private loginService loginService;
	
	@GetMapping("/")
	public String redirectLogin() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String verLogin(org.springframework.ui.Model model, 
							@RequestParam(name = "error", required = false) String error) {
		model.addAttribute("error", error);
		model.addAttribute("userCredentials", new UserCredentials());
		return "login";
	}
	
	@GetMapping("/loginOK")
	public String loginOK(@ModelAttribute(name = "UserCredentials") UserCredentials userCred) {
		boolean existUser;
		existUser = Boolean.parseBoolean(loginService.sendCredentials(userCred));
		
		if (existUser) {
			log.info("Usuario valido ingreso exitoso");
			return "redirect:/admin/reporte";
		}else {
			log.info("Usuario o password invalido");
			return "redirect:/login?error";
		}
		
	}
	
}
