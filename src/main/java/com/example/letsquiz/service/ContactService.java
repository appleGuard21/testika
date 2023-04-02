package com.example.letsquiz.service;

import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.requests.contact.AddContactRequest;
import com.example.letsquiz.responses.AddContactResponse;
import com.example.letsquiz.responses.GetContactResponse;
import com.example.letsquiz.requests.contact.DeleteContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final AppUserService userService;

    public AddContactResponse addContact(AddContactRequest request){
        AppUser user = userService.findUserByUsername(request.getUsername());
        AppUser contact = userService.findUserByUsername(request.getContact());
        user.getContacts().add(contact);
        contact.getContacts().add(user);
        userService.save(user);
        userService.save(contact);
        if(Objects.nonNull(contact.getAvatar())){
            return AddContactResponse.builder().username(contact.getUsername()).avatar(contact.getAvatar().getName()).build();
        } else {
            return AddContactResponse.builder().username(contact.getUsername()).build();
        }
    }

    public List<GetContactResponse> getContacts(String username){
        AppUser user = userService.findUserByUsername(username);
        return user.getContacts().stream().map(contact->{
            if(Objects.nonNull(contact.getAvatar())){
                return GetContactResponse.builder()
                        .username(contact.getUsername())
                        .avatar(contact.getAvatar().getName())
                        .build();
            } else {
                return GetContactResponse.builder()
                        .username(contact.getUsername())
                        .build();
            }
        }).collect(Collectors.toList());
    }

    public void deleteContact(DeleteContactRequest request){
        AppUser user = userService.findUserByUsername(request.getUsername());
        AppUser contact = userService.findUserByUsername(request.getContact());
        user.setContacts(
                user.getContacts().stream().filter(c->
                        !c.getUsername().equals(contact.getUsername())).collect(Collectors.toList())
        );
        contact.setContacts(
                contact.getContacts().stream().filter(c->
                        !c.getUsername().equals(user.getUsername())).collect(Collectors.toList())
        );
        userService.save(user);
        userService.save(contact);
    }
}
