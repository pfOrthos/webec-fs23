package ch.fhnw.webec.contactlist.controller;

import ch.fhnw.webec.contactlist.model.ContactListEntry;
import ch.fhnw.webec.contactlist.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ContactsController {

    private final ContactService service;

    public ContactsController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("contactList", service.getContactList());
        return "contacts";
    }

    @GetMapping("/contacts/search")
    public String searchContacts(@RequestParam String query, Model model) {
        List<ContactListEntry> searchResult = service.getContactList().stream()
                .filter(entry -> entry.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("contactList", searchResult);
        model.addAttribute("query", query);
        return "contacts";
    }

    @GetMapping("/contacts/clear")
    public String clearSearch(Model model) {
        model.addAttribute("contactList", service.getContactList());
        return "contacts";
    }

    @GetMapping("/contacts/{id}")
    public String showContact(@PathVariable int id, Model model) {
        var contact = service.findContact(id).orElseThrow(ContactNotFound::new);
        model.addAttribute("contactList", service.getContactList());
        model.addAttribute("contact", contact);
        return "contacts";
    }

    @ExceptionHandler(ContactNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("contactList", service.getContactList());
        return "contacts";
    }

    private static class ContactNotFound extends RuntimeException {}
}
