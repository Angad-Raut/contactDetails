package com.contactDetails.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author angad
 * Date : 14 June 2021
 * This is contact details entity class.
 */
@Entity
@Table(name="contact_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails {
    
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="c_id")
        private Long cId;
        private String firstName;
        private String lastName;
        private String email;
        private Long mobile;
        private Boolean status;
        private Date insertTime;
        private Date updateTime;
}
