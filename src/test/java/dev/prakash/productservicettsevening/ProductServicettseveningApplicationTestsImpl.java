package dev.prakash.productservicettsevening;

import dev.prakash.productservicettsevening.inheritanceexample.joinedtable.JTMentorRepository;
import dev.prakash.productservicettsevening.inheritanceexample.joinedtable.User;
import dev.prakash.productservicettsevening.inheritanceexample.joinedtable.Mentor;


import dev.prakash.productservicettsevening.inheritanceexample.joinedtable.JTUserRepository;
import dev.prakash.productservicettsevening.inheritanceexample.mappedsuperclass.MSMentorRepository;
import dev.prakash.productservicettsevening.inheritanceexample.singleclass.STMentorRepository;
import dev.prakash.productservicettsevening.inheritanceexample.singleclass.STUserRepository;
import dev.prakash.productservicettsevening.inheritanceexample.tableperclass.TBCMentorRepository;
import dev.prakash.productservicettsevening.inheritanceexample.tableperclass.TBCUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServicettseveningApplicationTestsImpl {
    @Autowired
    private JTUserRepository JTUserRepository;
    @Autowired
    private JTMentorRepository JTMentorRepository;
    @Autowired
    private MSMentorRepository MSMentorRepository;
    @Autowired
    private STUserRepository STUserRepository;
    @Autowired
    private STMentorRepository STMentorRepository;
    @Autowired
    private TBCUserRepository TBCUserRepository;
    @Autowired
    private TBCMentorRepository TBCMentorRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void testDifferentRepositories() {
        User user = new User();
        user.setEmail("Prakash@gmail.com");
        user.setPassword("Password");
        JTUserRepository.save(user);
        Mentor mentor = new Mentor();
        mentor.setEmail("prakasht@scaler.com");
        mentor.setPassword("12345");
        mentor.setNumberOfMentees(4);
        mentor.setNumberOfSessions(50);
        JTMentorRepository.save(mentor);


        dev.prakash.productservicettsevening.inheritanceexample.mappedsuperclass.Mentor mentor1 = new dev.prakash.productservicettsevening.inheritanceexample.mappedsuperclass.Mentor();
        mentor1.setEmail("prakash@mapped.com");
        mentor1.setPassword("12345");
        mentor1.setNumberOfMentees(4);
        mentor1.setNumberOfSessions(50);
        MSMentorRepository.save(mentor1);
        dev.prakash.productservicettsevening.inheritanceexample.singleclass.User user2 = new dev.prakash.productservicettsevening.inheritanceexample.singleclass.User();
        user2.setEmail("prakasSingel@gmail.com");
        user2.setPassword("Password");
        STUserRepository.save(user2);
        dev.prakash.productservicettsevening.inheritanceexample.singleclass.Mentor mentor2 = new dev.prakash.productservicettsevening.inheritanceexample.singleclass.Mentor();
        mentor2.setEmail("prakash@single.com");
        mentor2.setPassword("12345");
        mentor2.setNumberOfMentees(4);
        mentor2.setNumberOfSessions(50);
        STMentorRepository.save(mentor2);

        dev.prakash.productservicettsevening.inheritanceexample.tableperclass.User user3 = new dev.prakash.productservicettsevening.inheritanceexample.tableperclass.User();
        user3.setEmail("praksh@TPC@gmail.com");
        user3.setPassword("Password");
        TBCUserRepository.save(user3);
        dev.prakash.productservicettsevening.inheritanceexample.tableperclass.Mentor mentor3 = new dev.prakash.productservicettsevening.inheritanceexample.tableperclass.Mentor();
        mentor3.setEmail("prakash@tpc.com");
        mentor3.setPassword("12345");
        mentor3.setNumberOfMentees(4);
        mentor3.setNumberOfSessions(50);
        TBCMentorRepository.save(mentor3);


    }

}
