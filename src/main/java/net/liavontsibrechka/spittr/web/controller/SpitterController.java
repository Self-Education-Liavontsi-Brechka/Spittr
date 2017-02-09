package net.liavontsibrechka.spittr.web.controller;

import net.liavontsibrechka.spittr.Spitter;
import net.liavontsibrechka.spittr.data.SpitterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.Part;
import javax.validation.Valid;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRepository spitterRepository;

    //@Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    /*
    * Ways to accept requestPart argument:
    *   byte[] profilePicture
    *   MultipartFile profilePicture - requires multipart configuration
    *   Part profilePicture - doesn't require multipart configuration
    * */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestPart(name = "profilePicture") Part profilePicture,
                                      @Valid Spitter spitter,
                                      Errors errors) {
        if (errors.hasErrors()) return "registerForm";

        spitterRepository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable("username") String username, Model model) {
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }
}
