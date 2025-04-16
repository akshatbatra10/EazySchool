package com.project.eazy_school.rest;

import com.project.eazy_school.constants.EazySchoolConstants;
import com.project.eazy_school.model.Contact;
import com.project.eazy_school.model.Response;
import com.project.eazy_school.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact")
public class ContactRestController {

    private final ContactRepository contactRepository;

    @Autowired
    ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/getMessageByStatus")
    public List<Contact> getMessageByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact) {
        if (contact != null && contact.getStatus() != null) {
            return contactRepository.findByStatus(contact.getStatus());
        }
        return List.of();
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom, @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom: %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info(String.format("Header key: %s value: %s", key, String.join("|", value)));
        });
        Contact contact = requestEntity.getBody();
        assert contact != null;
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq) {
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        if (contact.isPresent()) {
            contact.get().setStatus(EazySchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Invalid contact id received");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message closed successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
