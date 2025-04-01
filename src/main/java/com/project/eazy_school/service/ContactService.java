package com.project.eazy_school.service;

import com.project.eazy_school.model.Contact;
import com.project.eazy_school.model.EazySchoolConstants;
import com.project.eazy_school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact) {
        contact.setStatus(EazySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        return savedContact.getContactId() > 0;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent((contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
        }));
        Contact updatedContact = contactRepository.save(contact.get());
        return updatedContact.getUpdatedBy() != null;
    }
}
