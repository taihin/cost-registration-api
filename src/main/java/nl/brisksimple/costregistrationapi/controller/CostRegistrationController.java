package nl.brisksimple.costregistrationapi.controller;

import nl.brisksimple.costregistrationapi.model.CostRegistration;
import nl.brisksimple.costregistrationapi.repository.CostRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CostRegistrationController {

    @Autowired
    CostRegistrationRepository costRegistrationRepository;

    @GetMapping("/cost-registrations")
    public ResponseEntity<List<CostRegistration>> getAllCostsRegistrations(@RequestParam(required = false) String item) {
        try {
            List<CostRegistration> costRegistrations = new ArrayList<>();

            if (item == null) {
                costRegistrationRepository.findAll().forEach(costRegistrations::add);
            } else
                costRegistrationRepository.findByItemNameContaining(item).forEach(costRegistrations::add);
            if (costRegistrations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(costRegistrations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cost-registrations/{id}")
    public ResponseEntity<CostRegistration> getCostsRegistrationById(@PathVariable("id") long id) {
        Optional<CostRegistration> costRegistrationData = costRegistrationRepository.findById(id);

        if (costRegistrationData.isPresent()) {
            return new ResponseEntity<>(costRegistrationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cost-registrations")
    public ResponseEntity<CostRegistration> createCostsRegistration(@RequestBody CostRegistration costRegistration) {
        try {
            CostRegistration _costRegistration = costRegistrationRepository.save(new CostRegistration(
                    costRegistration.getItemName(),
                    costRegistration.getDescription(),
                    costRegistration.getPrice(),
                    costRegistration.getNumber()));
            return new ResponseEntity<>(_costRegistration, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/cost-registrations/{id}")
//    public ResponseEntity<CostRegistration> updateCostsRegistration(@PathVariable("id") long id, @RequestBody CostRegistration costsRegistration) {
//        Optional<CostRegistration> costRegistrationData = costRegistrationRepository.findById(id);
//
//        if (costRegistrationData.isPresent()) {
//            CostRegistration _costRegistration = costRegistrationData.get();
//            _costRegistration.setItemName(costsRegistration.getItemName());
//            _costRegistration.setDescription(costsRegistration.getDescription());
//            _costRegistration.setPrice(costsRegistration.getPrice());
//            _costRegistration.setNumber(costsRegistration.getNumber());
//            return new ResponseEntity<CostRegistration>(costRegistrationRepository.save(_costRegistration, HttpStatus.OK));
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/cost-registrations/{id}")
    public ResponseEntity<HttpStatus> deleteCostsRegistration(@PathVariable("id") long id) {
        try {
            costRegistrationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cost-registrations")
    public ResponseEntity<HttpStatus> deleteAllCostsRegistrations() {
        try {
            costRegistrationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
