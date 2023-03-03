package bank.nexa.com.bankservice.transaction.controller;

import bank.nexa.com.bankservice.transaction.model.Client;
import bank.nexa.com.bankservice.transaction.model.ClientRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;


@RestController
@RequestMapping("/transaction")
public class Transaction {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;


    @GetMapping
    public String testDatabase(){
        //User n = new User();
        //n.setName(name);
        //n.setEmail(email);
        //userRepository.save(n);

        //generate for Client Entity
        Client c = new Client();
        c.setFirstName("test");
        c.setLastName("test");
        c.setBirthday(Instant.now());
        c.setDpi("3003297920101");
        c.setGender((byte)1);
        c.setCountryBirthday("Guatemala");
        c.setSecondLastName("Martinez");
        c.setMiddleName("Jonathan");

        clientRepository.save(c);

        entityManager.merge(c);
        entityManager.flush();

        return "test";
    }


    
    
}
