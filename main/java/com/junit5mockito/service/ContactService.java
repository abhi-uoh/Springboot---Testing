package com.junit5mockito.service;

import com.junit5mockito.entity.Contact;
import com.junit5mockito.exception.ContactNotFoundException;
import com.junit5mockito.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ContactService  {
    @Autowired
    ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }


    public List<Contact> getContactList(){
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id){
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact Not Found"+id));

    }

/*    public List<Contact>getByName(String name){
        return contactRepository.findByName(name);
    }*/


    public void deleteContact(Contact contact){
              contactRepository.delete(contact);
    }

}
