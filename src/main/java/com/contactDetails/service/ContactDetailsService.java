package com.contactDetails.service;

import org.springframework.data.domain.Page;

import com.contactDetails.dto.ContactDto;
import com.contactDetails.dto.CustomPageRequest;
import com.contactDetails.dto.EntityIdDto;
import com.contactDetails.dto.ViewContactDto;
import com.contactDetails.exception.AlreadyExistException;
import com.contactDetails.exception.InvalidDataException;
import com.contactDetails.exception.ResourceNotFoundException;
import com.contactDetails.model.ContactDetails;

/**
 * @author angad
 * Date : 14 June,2021
 * This interface service used for to add contact details signature methods.
 */
public interface ContactDetailsService {

          /**
           * This method used for to save/update contact details.
           * @param requestDto
           * @return
           * @throws AlreadyExistException
           * @throws InvalidDataException
           */
          Boolean saveOrupdate(ContactDto requestDto)throws AlreadyExistException,InvalidDataException;
          
          /**
           * This method used for to fetch contact details by id.
           * @param requestDto
           * @return
           * @throws ResourceNotFoundException
           */
          ContactDetails getById(EntityIdDto requestDto)throws ResourceNotFoundException;
          
          /**
           * This method used for to delete contact details by id.
           * @param requestDto
           * @return
           * @throws ResourceNotFoundException
           */
          Boolean deleteById(EntityIdDto requestDto)throws ResourceNotFoundException;
          
          /**
           * This method used for to fetch all contact details with page request.
           * @param request
           * @return
           */
          Page<ViewContactDto> getAllContactDetails(CustomPageRequest request);
          
          /**
           * This method used for to update contact details status (active or inactive).
           * @param requestDto
           * @return
           * @throws ResourceNotFoundException
           */
          Boolean updateStatus(EntityIdDto requestDto)throws ResourceNotFoundException;
          
          /**
           * This method used for to delete all data.
           */
          void deleteAll();
}
