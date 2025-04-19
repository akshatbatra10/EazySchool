package com.project.eazy_school.service;

import com.project.eazy_school.config.EazySchoolProps;
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

    private final EazySchoolProps eazySchoolProps;

    @Autowired
    public ContactService(ContactRepository contactRepository, EazySchoolProps eazySchoolProps) {
        this.contactRepository = contactRepository;
        this.eazySchoolProps = eazySchoolProps;
    }

    public void saveMessageDetails(Contact contact) {
        contact.setStatus(EazySchoolConstants.OPEN);
        contactRepository.save(contact);
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = eazySchoolProps.getDefaultPageSize();
        if (eazySchoolProps.getContact() != null && eazySchoolProps.getContact().get("pageSize") != null) {
            pageSize = Integer.parseInt(eazySchoolProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sortDir.equals("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return contactRepository.findByStatusWithQuery(EazySchoolConstants.OPEN, pageable);
    }

    // Use Optional when data can be null
    public void updateMsgStatus(int contactId) {
        contactRepository.updateStatusById(EazySchoolConstants.CLOSE, contactId);
    }
}
