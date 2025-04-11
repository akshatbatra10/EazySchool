package com.project.eazy_school.service;

import com.project.eazy_school.model.Contact;
import com.project.eazy_school.constants.EazySchoolConstants;
import com.project.eazy_school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveMessageDetails(Contact contact) {
        contact.setStatus(EazySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
    }

    public List<Contact> findMsgsWithOpenStatus() {
        return contactRepository.findByStatus(EazySchoolConstants.OPEN);
    }

    // Use Optional when data can be null
    public void updateMsgStatus(int contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent((contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        }));
    }
}
