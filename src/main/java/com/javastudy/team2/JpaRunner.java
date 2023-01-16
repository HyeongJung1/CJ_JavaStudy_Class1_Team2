package com.javastudy.team2;


import com.javastudy.team2.model.Account;
import com.javastudy.team2.repository.AccountRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = new Account();
        account.setUsername("hjkim10");

        account.setPassword("pass");

        //entityManager.persist(account);

        Session seesion = entityManager.unwrap(Session.class);
        seesion.save(account);

        account.setUsername("hjkim11");
        account.setUsername("hjkim12");
        account.setUsername("hjkim13");
        account.setUsername("hjkim14");
        account.setUsername("hjkim15");

        account.setUsername("hjkim10");

    }


}
