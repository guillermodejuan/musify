package com.gudejuan.musify.controladores;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gudejuan.musify.entity.User;
import com.gudejuan.musify.service.PeopleService;
import com.gudejuan.musify.util.Cifrado;
import com.gudejuan.musify.util.Constantes;

@Controller
public class RegisterController {

	@Autowired
	@Qualifier("userServiceImpl")
	PeopleService userService;

	@GetMapping("/register")
	public ModelAndView registro() {
		ModelAndView mav = new ModelAndView(Constantes.REGISTER_VIEW);
		User user = new User();
		user.setYear(Constantes.YEAR);
		mav.addObject("user", new User());
		return mav;
	}

	@PostMapping("/registersubmit")
	public ModelAndView validarRegistro(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		
		List<User> users = userService.listUsers(user.getName());
		for (User u : users) {
			if (u.getName().equals(user.getName())) {
				bindingResult.rejectValue("name", "error.name", "Ya existe un usuario con ese email");
			}
		}
		if (bindingResult.hasErrors()) {
			mav.setViewName(Constantes.REGISTER_VIEW);
		} else {
			user.setRol(Constantes.ROL_USUARIO);
			user.setPassword(Cifrado.Encriptar(user.getPassword()));
						
			userService.addUser(user);
			mav.setViewName(Constantes.LOGIN_VIEW);
			mav.addObject("newuser","El usuario ha sido creado con éxito");
		}
		return mav;
	}

}
