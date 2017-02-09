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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                                      Errors errors,
                                      RedirectAttributes model) {
        if (errors.hasErrors()) return "registerForm";

        spitterRepository.save(spitter);
        model.addAttribute("username", spitter.getUsername());
        model.addFlashAttribute("spitter", spitter);

//        model.addAttribute("spitterId", spitter.getId());
//        because the spitterId attribute from the model doesn’t map to any URL placeholders in the redirect,
//        it’s tacked on to the redirect automatically as a query parameter.
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable("username") String username, Model model) {
        if (!model.containsAttribute("sptter"))
            model.addAttribute("spitter", spitterRepository.findByUsername(username));
        return "profile";
    }
}
