package com.project.eazy_school.repository;

import com.project.eazy_school.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    // @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true)
    @Query("SELECT c FROM Contact c WHERE c.status = :status")
    Page<Contact> findByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c Set c.status = ?1 WHERE c.contactId = ?2")
    void updateStatusById(String status, int contactId);
//
//    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);
//
//    @Transactional
//    @Modifying
//    void updateMsgStatus(String status, int id);
//
//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true)
//    int updateMsgStatusNative(String status, int id);
}
