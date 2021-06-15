package com.contactDetails.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.contactDetails.ContactDetailsApplication;
import com.contactDetails.datafixture.ContactDataFixture;
import com.contactDetails.model.ContactDetails;

/**
 * @author angad
 * Date : 14 June,2021
 * This class used for to write repository juint test cases.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ContactDetailsApplication.class)
@ActiveProfiles("Test")
public class ContactDetailsRepositoryTest {

            @Autowired
            ContactDetailsRepository contactDetailsRepository;  
            
            @Before
            @After
            public void clear(){
                contactDetailsRepository.deleteAll();
            }
            
           public  BigInteger integer=BigInteger.valueOf(1);
            
            @Test
            public void getByIdTest()throws Exception{
                   ContactDetails details = contactDetailsRepository.save(new ContactDetails(null,ContactDataFixture.FIRST_NAME_ONE, 
                            ContactDataFixture.LAST_NAME_ONE, ContactDataFixture.EMAIL_ONE, ContactDataFixture.MOBILE_ONE,
                            ContactDataFixture.ISACTIVE, new Date(), new Date()));
                   assertNotNull(details);
            }
            
            @Test
            public void isEmailExistTest()throws Exception{
                   contactDetailsRepository.save(new ContactDetails(null,ContactDataFixture.FIRST_NAME_ONE, 
                            ContactDataFixture.LAST_NAME_ONE, ContactDataFixture.EMAIL_ONE, ContactDataFixture.MOBILE_ONE,
                            ContactDataFixture.ISACTIVE, new Date(), new Date()));
                   BigInteger bigInteger=contactDetailsRepository.isEmailExist("angad.raut@gmail.com");
                   assertEquals(integer, bigInteger);
            }
            
            @Test
            public void isMobileExistTest()throws Exception{
                   contactDetailsRepository.save(new ContactDetails(null,ContactDataFixture.FIRST_NAME_ONE, 
                            ContactDataFixture.LAST_NAME_ONE, ContactDataFixture.EMAIL_ONE, ContactDataFixture.MOBILE_ONE,
                            ContactDataFixture.ISACTIVE, new Date(), new Date()));
                   BigInteger bigInteger=contactDetailsRepository.isMobileExist(9766945760L);
                   assertEquals(integer, bigInteger);
            }
            
            @Test
            public void isEnabledTest()throws Exception{
                  ContactDetails details=contactDetailsRepository.save(new ContactDetails(null,ContactDataFixture.FIRST_NAME_ONE, 
                            ContactDataFixture.LAST_NAME_ONE, ContactDataFixture.EMAIL_ONE, ContactDataFixture.MOBILE_ONE,
                            ContactDataFixture.ISACTIVE, new Date(), new Date()));
                   BigInteger bigInteger=contactDetailsRepository.isEnabled(details.getCId(),details.getStatus());
                   assertEquals(integer, bigInteger);
            }
            
            @Test
            public void updateStatusTest()throws Exception{
                  ContactDetails details=contactDetailsRepository.save(new ContactDetails(null,ContactDataFixture.FIRST_NAME_ONE, 
                            ContactDataFixture.LAST_NAME_ONE, ContactDataFixture.EMAIL_ONE, ContactDataFixture.MOBILE_ONE,
                            ContactDataFixture.ISACTIVE, new Date(), new Date()));
                   Integer bigInteger=contactDetailsRepository.updateStatus(details.getCId(),ContactDataFixture.IS_INACTIVE,new Date());
                   assertEquals(new Integer(1), bigInteger);
            }
            
            @Test
            public void getAllContactDetailsTest()throws Exception{
                contactDetailsRepository.save(new ContactDetails(null,ContactDataFixture.FIRST_NAME_ONE, 
                        ContactDataFixture.LAST_NAME_ONE, ContactDataFixture.EMAIL_ONE, ContactDataFixture.MOBILE_ONE,
                        ContactDataFixture.ISACTIVE, new Date(), new Date()));
                PageRequest pageRequest=PageRequest.of(1, 10, Direction.ASC, "update_time");
                Page<ContactDetails> pageList=contactDetailsRepository.getAllContactDetails(false, null, pageRequest);
                assertNotNull(pageList);
            }
}
