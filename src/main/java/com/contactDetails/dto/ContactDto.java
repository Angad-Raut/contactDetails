package com.contactDetails.dto;

import com.contactDetails.annotation.ContactValid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author angad
 * Date : 14 June 2021
 * This class used for contact request parameters.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ContactValid
public class ContactDto {
 
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private Long mobile;
}
