package com.project.eazy_school.service;

import com.project.eazy_school.constants.EazySchoolConstants;
import com.project.eazy_school.model.Person;
import com.project.eazy_school.model.Roles;
import com.project.eazy_school.repository.PersonRepository;
import com.project.eazy_school.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person) {
        Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        return person.getPersonId() > 0;
    }
}
