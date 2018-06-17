package pl.edu.pjatk.s14310.mas.ooops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.pjatk.s14310.mas.ooops.forms.CustomerForm;
import pl.edu.pjatk.s14310.mas.ooops.models.Individual;
import pl.edu.pjatk.s14310.mas.ooops.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    // Inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;


    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/customerList" }, method = RequestMethod.GET)
    public String personList(Model model) {
        Iterable<Individual> all = customerRepository.findAll();
        model.addAttribute("individuals", all);

        return "customerList";
    }



    @RequestMapping(value = { "/addCustomer" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("customerForm") CustomerForm customerForm) {

        String address = customerForm.getAddress();
        String phone = customerForm.getPhone();

        if (address != null && address.length() > 0 //
                && phone != null && phone.length() > 0) {
            Individual i = new Individual();
            i.setAddress(address);
            i.addPhone(phone);
            customerRepository.save(i);
            return "redirect:/customerList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addCustomer";
    }



    @RequestMapping(value = { "/addCustomer" }, method = RequestMethod.GET)
    public String showAddCustomerPage(Model model) {

        CustomerForm customerForm = new CustomerForm();
        model.addAttribute("customerForm", customerForm);

        return "addCustomer";
    }
}
