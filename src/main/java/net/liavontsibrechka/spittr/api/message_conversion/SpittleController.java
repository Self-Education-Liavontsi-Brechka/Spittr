package net.liavontsibrechka.spittr.api.message_conversion;

import net.liavontsibrechka.spittr.Spittle;
import net.liavontsibrechka.spittr.data.SpittleRepository;
import net.liavontsibrechka.spittr.web.exception.SpittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/spittles")
public class SpittleController {
    private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    // produces attribute to declare that this method will only handle requests where JSON output is expected
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Spittle> spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleRepository.findSpittles(max, count);
    }

//    Withoud headers
//    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Spittle saveSpittle(@RequestBody Spittle spittle) {
//        return spittleRepository.save(spittle);
//    }

    //    With headers
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
        Spittle finalSpittle = spittleRepository.save(spittle);
        HttpHeaders headers = new HttpHeaders();
//        URI location = new URI("http://localhost:8080/spittr/spittles/" + finalSpittle.getId());
        URI location = ucb.path("/spittles/").path(String.valueOf(finalSpittle.getId())).build().toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(finalSpittle, headers, HttpStatus.CREATED);
    }

//    Handling exceptions with ResponseEntity and
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> spittleById(@PathVariable("id") long id) {
//        Spittle spittle = spittleRepository.findOne(id);
//        if (spittle == null)
//            return new ResponseEntity<>(new Error(404, "Spittle [" + id + "] not found."), HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(spittle, HttpStatus.OK);
//    }

    //Handling exceptions with ExceptionHandler and @ResponseStatus
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Spittle spittleById(@PathVariable("id") long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (spittle == null) throw new SpittleNotFoundException(id);
        return spittle;
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error spittleNotFound(SpittleNotFoundException e) {
        return new Error(404, "Spittle [" + e.getSpittleId() + "] not found");
    }
}
