package com.contactDetails.datafixture;

import com.contactDetails.dto.ContactDto;
import com.google.gson.Gson;

/**
 * @author angad
 * Date : 14 June,2021
 * This class used for to add common data for junit test cases.
 */
public class ContactDataFixture {

         public static String FIRST_NAME_ONE="Angad";
         public static String FIRST_NAME_TWO="Amol";
         public static String LAST_NAME_ONE="Raut";
         public static String LAST_NAME_TWO="Pawar";
         public static String EMAIL_ONE="angad.raut@gmail.com";
         public static String EMAIL_TWO="amol.pawar@gmail.com";
         public static Long MOBILE_ONE=9766945760L;
         public static Long MOBILE_TWO=9766945761L;
         public static Boolean ISACTIVE=true;
         public static Boolean IS_INACTIVE=false;
         public static Gson gson=new Gson();
         
         public static ContactDto standardOneDto(){
             return new ContactDto(null, FIRST_NAME_ONE, LAST_NAME_ONE, EMAIL_ONE, MOBILE_ONE);
         }
         
         public static ContactDto standardTwoDto(){
             return new ContactDto(null, FIRST_NAME_TWO, LAST_NAME_TWO, EMAIL_TWO, MOBILE_TWO);
         }
         
         public static String standardJsonOneDto(){
             return gson.toJson(standardOneDto());
         }
         
         public static String standardJsonTwoDto(){
             return gson.toJson(standardTwoDto());
         }
}
