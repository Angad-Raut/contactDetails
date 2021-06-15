package com.contactDetails.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.contactDetails.ContactDetailsApplication;
import com.contactDetails.datafixture.ContactDataFixture;
import com.contactDetails.dto.CustomPageRequest;
import com.contactDetails.dto.EntityIdDto;
import com.contactDetails.dto.ViewContactDto;
import com.contactDetails.service.ContactDetailsService;
import com.google.gson.Gson;

/**
 * @author angad
 * Date : 14 June,2021
 * This is controller test class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ContactDetailsApplication.class)
@WebAppConfiguration
@ActiveProfiles("Test")
public class ContactDetailsControllerTest {

              MockMvc mvc;
              
             @Autowired
             WebApplicationContext webApplicationContext;
             
             @Autowired
             private ContactDetailsService contactDetailsService;
             
             Gson gson=new Gson();
             
             @Before
             public void setUp() {
                 mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
                 contactDetailsService.deleteAll();
              }
             
             @After
              public void clear(){
                 contactDetailsService.deleteAll();
              }
             
             @Test
             public void addTest() throws Exception {
                     mvc.perform(post("/contactDetails/saveContactDetails")
                             .content(ContactDataFixture.standardJsonOneDto())
                             .contentType(MediaType.APPLICATION_JSON)
                             .accept(MediaType.APPLICATION_JSON))
                            .andDo(print())
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.result").value(true));
             }
             
             @Test
             public void getByIdTest()throws Exception {
                 contactDetailsService.saveOrupdate(ContactDataFixture.standardOneDto());
                 Page<ViewContactDto> pageList=contactDetailsService.getAllContactDetails(new CustomPageRequest(1, 1, null, null,null));
                 mvc.perform(post("/contactDetails/getContactDetailsById")
                         .content(gson.toJson(new EntityIdDto(pageList.getContent().get(0).getId())))
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.result.firstName").value("Angad"))
                        .andExpect(jsonPath("$.result.lastName").value("Raut"))
                        .andExpect(jsonPath("$.result.email").value("angad.raut@gmail.com"))
                        .andExpect(jsonPath("$.result.mobile").value(new Long(9766945760L).intValue()));
             }
             
             @Test
             public void deleteByIdTest()throws Exception {
                 contactDetailsService.saveOrupdate(ContactDataFixture.standardOneDto());
                 Page<ViewContactDto> pageList=contactDetailsService.getAllContactDetails(new CustomPageRequest(1, 1, null, null,null));
                 mvc.perform(post("/contactDetails/deleteContactById")
                         .content(gson.toJson(new EntityIdDto(pageList.getContent().get(0).getId())))
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.result").value(true));
             }
             
             @Test
             public void updateStatusTest()throws Exception {
                 contactDetailsService.saveOrupdate(ContactDataFixture.standardOneDto());
                 Page<ViewContactDto> pageList=contactDetailsService.getAllContactDetails(new CustomPageRequest(1, 1, null, null,null));
                 mvc.perform(post("/contactDetails/updateContactStatus")
                         .content(gson.toJson(new EntityIdDto(pageList.getContent().get(0).getId())))
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.result").value(true));
             }
             
             @Test
             public void getAllTest()throws Exception {
                 contactDetailsService.saveOrupdate(ContactDataFixture.standardOneDto());
                 contactDetailsService.saveOrupdate(ContactDataFixture.standardTwoDto());
                 CustomPageRequest request=new CustomPageRequest(1, 1, null, null,null);
                 mvc.perform(post("/contactDetails/getAllContactDetails")
                         .content(gson.toJson(request))
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.result.content[0].firstName").value("Angad"))
                        .andExpect(jsonPath("$.result.content[0].lastName").value("Raut"))
                        .andExpect(jsonPath("$.result.content[0].email").value("angad.raut@gmail.com"))
                        .andExpect(jsonPath("$.result.content[0].mobile").value(new Long(9766945760L).intValue()))
                        .andExpect(jsonPath("$.result.content[1].firstName").value("Amol"))
                        .andExpect(jsonPath("$.result.content[1].lastName").value("Pawar"))
                        .andExpect(jsonPath("$.result.content[1].email").value("amol.pawar@gmail.com"))
                        .andExpect(jsonPath("$.result.content[1].mobile").value(new Long(9766945761L).intValue()));
             }
}
