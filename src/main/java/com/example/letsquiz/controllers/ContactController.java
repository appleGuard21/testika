package com.example.letsquiz.controllers;

import com.example.letsquiz.requests.contact.AddContactRequest;
import com.example.letsquiz.requests.contact.DeleteContactRequest;
import com.example.letsquiz.responses.AddContactResponse;
import com.example.letsquiz.responses.GetContactResponse;
import com.example.letsquiz.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
@CrossOrigin
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/addContact")
    public AddContactResponse addContact(@RequestBody AddContactRequest request){
        return contactService.addContact(request);
    }
    @GetMapping("/getContacts/{username}")
    public List<GetContactResponse> getContacts(@PathVariable String username){
        return contactService.getContacts(username);
    }
    @PostMapping("deleteContact")
    public void deleteContact(@RequestBody DeleteContactRequest request){
        contactService.deleteContact(request);
    }

}
