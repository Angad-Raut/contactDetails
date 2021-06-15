package com.contactDetails.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author angad
 * Date : 14 June 2021
 * This class used for page request parameters.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageRequest {

    @NotNull(message="page.null")
    private Integer page;	
    @NotNull(message="page.size")
    private Integer size;
    private String sort;
    private String sortNamedir;
    private String searchParam;
}
