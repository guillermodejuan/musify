package com.gudejuan.musify.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gudejuan.musify.entity.Artist;
import com.gudejuan.musify.entity.ArtistStyle;
import com.gudejuan.musify.entity.Style;
import com.gudejuan.musify.entity.User;
import com.gudejuan.musify.repository.ArtistJpaRepository;
import com.gudejuan.musify.service.ArtistService;
import com.gudejuan.musify.service.ArtistStyleService;
import com.gudejuan.musify.service.PeopleService;
import com.gudejuan.musify.service.StylesService;
import com.gudejuan.musify.util.Cifrado;
import com.gudejuan.musify.util.Constantes;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("userServiceImpl")
	private PeopleService peopleService;

	@Autowired
	@Qualifier("artistServiceImpl")
	private ArtistService artistService;

	@Autowired
	@Qualifier("artistStylesServiceImpl")
	private ArtistStyleService artistStyleService;

	@Autowired
	@Qualifier("stylesServiceImpl")
	private StylesService stylesService;
	
	private User u;

	@GetMapping("/")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView(Constantes.LOGIN_VIEW);
		u = null;
		mav.addObject("user", new User());
		return mav;
	}

	
	@PostMapping("/login")
	public ModelAndView helloWorld(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		u = null;
		ModelAndView mav = new ModelAndView();
		List<User> users = peopleService.listUsers(user.getName());
		if (!bindingResult.hasErrors()) {
			if (users.size() > 0) {
				u = users.get(0);

				try {
					if (!user.getPassword().equals(Cifrado.Desencriptar(u.getPassword()))) {
						bindingResult.rejectValue("password", "error.password", "La clave introducida no es correcta");
						u = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
					u = null;
				}

			} else {
				bindingResult.rejectValue("name", "error.name", "No existe ningún usuario con ese email");

			}
		}

		if (bindingResult.hasErrors()) {
			mav.setViewName(Constantes.LOGIN_VIEW);
		} else {
			mav.setViewName(Constantes.GESTION_USUARIOS);
			mav.addObject("user", u);
			List<Artist> artists = artistService.listAllArtists(Constantes.STYLE_NULL);
			mav.addObject("artists", artists);
			List<Style> styles = stylesService.listStyles();
			styles.add(0, new Style(0, Constantes.STYLE_NULL));
			mav.addObject("styles", styles);
			mav.addObject("artist", new Artist());
		}
		return mav;
	}

	@PostMapping("/login/style")
	public ModelAndView styleSearch(@ModelAttribute("style") String style) {
		ModelAndView mav = new ModelAndView(Constantes.GESTION_USUARIOS);

		List<Artist> artists = artistService.listAllArtists(style);
		mav.addObject("artists", artists);

		List<Style> styles = stylesService.listStyles();
		styles.add(0, new Style(0, Constantes.STYLE_NULL));
		mav.addObject("styles", styles);

		mav.addObject("user", u);
		mav.addObject("artist", new Artist());

		return mav;
	}

	@PostMapping("/login/addartist")
	public ModelAndView addArtist(@ModelAttribute("artist") Artist artist, @RequestParam("style") int[] artistStyles) {
		ModelAndView mav = new ModelAndView(Constantes.GESTION_USUARIOS);

		Artist a = artistService.addArtist(artist);
		for (int style : artistStyles) {
			System.out.println("********** Añadido artista con id " + a.getId() + " añadimos estilo con id : " + style);
			artistStyleService.addArtistStyle(new ArtistStyle(a.getId(), style));
		}

		mav.addObject("artist", new Artist());

		List<Artist> artists = artistService.listAllArtists(Constantes.STYLE_NULL);
		mav.addObject("artists", artists);

		List<Style> styles = stylesService.listStyles();
		styles.add(0, new Style(0, Constantes.STYLE_NULL));
		mav.addObject("styles", styles);

		mav.addObject("user", u);

		return mav;
	}

	@GetMapping("/login/artistdetail")
	public ModelAndView artistDetail(@RequestParam(name = "id") String artistId) {
		ModelAndView mav = new ModelAndView(Constantes.GESTION_ARTISTA);

		Artist artist = artistService.getArtistById(Integer.parseInt(artistId));
		mav.addObject(artist);

		List<Artist> collaborators = artistService.getCollaborators(artist);
		mav.addObject("collaborators", collaborators);

		List<Artist> noncollaborators = artistService.getNonCollaborators(collaborators, Integer.parseInt(artistId));
		mav.addObject("noncollaborators", noncollaborators);
		mav.addObject("user", u);
		return mav;

	}

	@GetMapping("/login/artistdetail/addcollab")
	public ModelAndView addCollab(@RequestParam(name = "id") String artistId,
			@RequestParam(name = "collabid") String collabId) {
		ModelAndView mav = new ModelAndView(Constantes.GESTION_ARTISTA);
		System.out.println("Id de artista " + artistId + " e Id de colaborador " + collabId);

		Artist artist = artistService.getArtistById(Integer.parseInt(artistId));
		mav.addObject(artist);

		artistService.addCollab(artist.getId(), Integer.parseInt(collabId));

		List<Artist> collaborators = artistService.getCollaborators(artist);
		mav.addObject("collaborators", collaborators);

		List<Artist> noncollaborators = artistService.getNonCollaborators(collaborators, Integer.parseInt(artistId));
		mav.addObject("noncollaborators", noncollaborators);
		mav.addObject("user", u);

		return mav;
	}

	@GetMapping("/login/artistdetail/removecollab")
	public ModelAndView removeCollab(@RequestParam(name = "id") String artistId,
			@RequestParam(name = "collabid") String collabId) {
		ModelAndView mav = new ModelAndView(Constantes.GESTION_ARTISTA);
		System.out.println("Id de artista " + artistId + " e Id de colaborador " + collabId);

		Artist artist = artistService.getArtistById(Integer.parseInt(artistId));
		mav.addObject(artist);

		artistService.removeCollab(artist.getId(), Integer.parseInt(collabId));

		List<Artist> collaborators = artistService.getCollaborators(artist);
		mav.addObject("collaborators", collaborators);

		List<Artist> noncollaborators = artistService.getNonCollaborators(collaborators, Integer.parseInt(artistId));
		mav.addObject("noncollaborators", noncollaborators);
		mav.addObject("user", u);

		return mav;
	}

	@GetMapping("/login/artistdetail/volver")
	public ModelAndView volverArtistas() {
		ModelAndView mav = new ModelAndView(Constantes.GESTION_USUARIOS);

		List<Artist> artists = artistService.listAllArtists(Constantes.STYLE_NULL);
		mav.addObject("artists", artists);

		List<Style> styles = stylesService.listStyles();
		styles.add(0, new Style(0, Constantes.STYLE_NULL));
		mav.addObject("styles", styles);

		mav.addObject("user", u);
		mav.addObject("artist", new Artist());

		return mav;
	}
	

}
