package pl.edu.pjatk.s14310.mas.ooops.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.s14310.mas.ooops.forms.ParcelForm;
import pl.edu.pjatk.s14310.mas.ooops.models.Customer;
import pl.edu.pjatk.s14310.mas.ooops.models.Individual;
import pl.edu.pjatk.s14310.mas.ooops.models.Parcel;
import pl.edu.pjatk.s14310.mas.ooops.repositories.CustomerRepository;
import pl.edu.pjatk.s14310.mas.ooops.repositories.ParcelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ParcelController {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private CustomerRepository customerRepository;


    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;


    @RequestMapping(value = {"/addParcel"}, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("parcelForm") ParcelForm parcelForm) {
        int individualId = parcelForm.getIndividualId();
        Optional<Individual> optionalIndividual = customerRepository.findById(individualId);
        if (!optionalIndividual.isPresent()) {
            model.addAttribute("errorMessage", "Cant find such customer");
            return "addParcel";
        }

        String deliveryAddress = parcelForm.getDeliveryAddress();
        int weight = parcelForm.getWeight();

        if (!StringUtils.isEmpty(deliveryAddress) && weight > 0) {
            Parcel parcel = new Parcel(deliveryAddress, weight, parcelForm.isUrgent(), optionalIndividual.get(), parcelForm.isWrapping() + "");
            parcelRepository.save(parcel);
            return "redirect:/";
        }
        model.addAttribute("errorMessage", "Delivery address can't be empty and weight cant be < 1");
        return "addParcel";
    }


    @RequestMapping(value = {"/addParcel"}, method = RequestMethod.GET)
    public String showAddParcelPage(Model model) {

        ParcelForm parcelForm = new ParcelForm();
        model.addAttribute("parcelForm", parcelForm);
        Iterable<Individual> all = customerRepository.findAll();
        model.addAttribute("individuals", all);

        return "addParcel";
    }

    @GetMapping("/parcel/{customerId}")
    public String showParcelsForCustomer(Model model, @PathVariable("customerId") int id) {
        Optional<Individual> byId = customerRepository.findById(id);
        List<Parcel> parcels = parcelRepository.findByGivenBy(byId.get());
        model.addAttribute("parcels", parcels);
        return "results :: resultsList";
    }
}
