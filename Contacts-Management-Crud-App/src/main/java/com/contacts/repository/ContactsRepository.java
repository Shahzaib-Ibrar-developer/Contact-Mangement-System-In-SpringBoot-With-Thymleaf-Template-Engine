package com.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.contacts.entity.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {

}
