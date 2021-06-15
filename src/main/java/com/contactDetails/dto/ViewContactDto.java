package com.contactDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author angad
 * Date : 14 June 2021
 * This class used for paginition api  response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViewContactDto {

        private Integer srNo;
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private Long mobile;
        private Boolean status;
        private String isActive;
}
