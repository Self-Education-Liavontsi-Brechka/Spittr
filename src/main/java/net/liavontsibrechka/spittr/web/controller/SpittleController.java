package net.liavontsibrechka.spittr.web.controller;

import net.liavontsibrechka.spittr.Spittle;
import net.liavontsibrechka.spittr.data.SpittleRepository;
import net.liavontsibrechka.spittr.web.exception.SpittleNotFoundException;
import net.liavontsibrechka.spittr.web.form.SpittleForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

    private SpittleRepository spittleRepository;

    //    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String spittles(Model model) {
//        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//        return "spittles";
//    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                                  @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(SpittleForm spittleForm, Model model) {
//        Not very good way of handling an exception:
//          Two paths can be taken, each with a different outcome.
//          It’d be simpler if saveSpittle() could focus on the happy path and
//          let some other method deal with the exception
//        try {
//            spittleRepository.save(
//                    new Spittle(spittleForm.getMessage(),
//                            new Date(), spittleForm.getLatitude(), spittleForm.getLongitude()));
//            return "redirect:/spittles";
//        } catch (DuplicateSpittleException e) {
//            return "error/duplicate";
//        }
        spittleRepository.save(
                new Spittle(spittleForm.getMessage(),
                        new Date(), spittleForm.getLatitude(), spittleForm.getLongitude()));
        return "redirect:/spittles";
    }


    // BAD WAY of identifying an object
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showSpittle(@RequestParam("spittle_id") long spittleId, Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }

    // GOOD WAY of identifying an object
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
        Spittle spittle = spittleRepository.findOne(spittleId);

        if (spittle == null) throw new SpittleNotFoundException(spittleId);

        model.addAttribute(spittle);
        return "spittle";
    }
}
