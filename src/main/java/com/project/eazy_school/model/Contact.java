package com.project.eazy_school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "contact_msg")
@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMsgs",
                query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMsgStatus",
                query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQuery(name = "Contact.updateMsgStatusNative",
query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;

    private String status;
}
