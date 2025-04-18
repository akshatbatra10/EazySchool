package com.project.eazy_school.service;

import com.project.eazy_school.model.Contact;
import com.project.eazy_school.constants.EazySchoolConstants;
import com.project.eazy_school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sortDir.equals("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return contactRepository.findByStatusWithQuery(EazySchoolConstants.OPEN, pageable);
    }

    // Use Optional when data can be null
    public void updateMsgStatus(int contactId) {
        contactRepository.updateStatusById(EazySchoolConstants.CLOSE, contactId);
    }
}
