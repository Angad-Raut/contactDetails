package com.contactDetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.contactDetails.dto.ContactDto;
import com.contactDetails.dto.CustomPageRequest;
import com.contactDetails.dto.EntityIdDto;
import com.contactDetails.dto.ResponseDto;
import com.contactDetails.dto.ViewContactDto;
import com.contactDetails.exception.AlreadyExistException;
import com.contactDetails.exception.InvalidDataException;
import com.contactDetails.exception.ResourceNotFoundException;
import com.contactDetails.model.ContactDetails;
import com.contactDetails.service.ContactDetailsService;
import com.contactDetails.util.ErrorHandler;

/**
 * @author angad
 * Date : 14 June,2021
 * This class used for to create contact details  Rest API.
 */
@RestController
@RequestMapping(value="/contactDetails")
public class ContactDetailsController {

          /**
           * This is used for to access contact details service methods.
           */
          @Autowired
          private ContactDetailsService contactDetailsService;
          
          /**
           * This is used for to convert system error messages into custom error message format.
           */
          @Autowired
          private ErrorHandler errorHandler;
          
          /**
           * This Rest API used for to add/update contact details.
           * @param requestDto
           * @param result
           * @return
           */
          @RequestMapping(value="/saveContactDetails",method=RequestMethod.POST)
          public ResponseEntity<ResponseDto<Boolean>> addDetails(@Validated 
                  @RequestBody ContactDto requestDto,BindingResult result) {
                  if(result.hasErrors()){
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(null,null,
                               errorHandler.getErrorMessageMap(result)),HttpStatus.NOT_ACCEPTABLE);
                  }
                  try{
                       Boolean flag=contactDetailsService.saveOrupdate(requestDto);
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(flag,null),HttpStatus.CREATED); 
                  }catch(AlreadyExistException | InvalidDataException e) {
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(null,
                               errorHandler.getMessageLocal(e.getMessage())),HttpStatus.OK);  
                  }
          }
          
          /**
           * This Rest API used for to fetch contact details by Id.
           * @param requestDto
           * @param result
           * @return
           */
          @RequestMapping(value="/getContactDetailsById",method=RequestMethod.POST)
          public ResponseEntity<ResponseDto<ContactDetails>> getContactDetailsById(@Validated 
                  @RequestBody EntityIdDto requestDto,BindingResult result) {
                  if(result.hasErrors()){
                       return new ResponseEntity<ResponseDto<ContactDetails>>(new ResponseDto<ContactDetails>(null,null,
                               errorHandler.getErrorMessageMap(result)),HttpStatus.NOT_ACCEPTABLE);
                  }
                  try{
                      ContactDetails details=contactDetailsService.getById(requestDto);
                       return new ResponseEntity<ResponseDto<ContactDetails>>(new ResponseDto<ContactDetails>(details,null),HttpStatus.OK); 
                  }catch(ResourceNotFoundException e) {
                       return new ResponseEntity<ResponseDto<ContactDetails>>(new ResponseDto<ContactDetails>(null,
                               errorHandler.getMessageLocal(e.getMessage())),HttpStatus.OK);  
                  }
          }
          
          /**
           * This Rest API used for to detelet contact details by id.
           * @param requestDto
           * @param result
           * @return
           */
          @RequestMapping(value="/deleteContactById",method=RequestMethod.POST)
          public ResponseEntity<ResponseDto<Boolean>> deleteContactById(@Validated 
                  @RequestBody EntityIdDto requestDto,BindingResult result) {
                  if(result.hasErrors()){
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(null,null,
                               errorHandler.getErrorMessageMap(result)),HttpStatus.NOT_ACCEPTABLE);
                  }
                  try{
                       Boolean flag=contactDetailsService.deleteById(requestDto);
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(flag,null),HttpStatus.OK); 
                  }catch(ResourceNotFoundException e) {
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(null,
                               errorHandler.getMessageLocal(e.getMessage())),HttpStatus.OK);  
                  }
          }
          
          /**
           * This Rest API used for to update contact details status by id.
           * @param requestDto
           * @param result
           * @return
           */
          @RequestMapping(value="/updateContactStatus",method=RequestMethod.POST)
          public ResponseEntity<ResponseDto<Boolean>> updateContactStatus(@Validated 
                  @RequestBody EntityIdDto requestDto,BindingResult result) {
                  if(result.hasErrors()){
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(null,null,
                               errorHandler.getErrorMessageMap(result)),HttpStatus.NOT_ACCEPTABLE);
                  }
                  try{
                       Boolean flag=contactDetailsService.updateStatus(requestDto);
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(flag,null),HttpStatus.OK); 
                  }catch(ResourceNotFoundException e) {
                       return new ResponseEntity<ResponseDto<Boolean>>(new ResponseDto<Boolean>(null,
                               errorHandler.getMessageLocal(e.getMessage())),HttpStatus.OK);  
                  }
          }
          
          /**
           * This Rest API used for to fetch all contact details.
           * @param requestDto
           * @param result
           * @return
           */
          @RequestMapping(value="/getAllContactDetails",method=RequestMethod.POST)
          public ResponseEntity<ResponseDto<Page<ViewContactDto>>> getAllContactDetails(@Validated 
                  @RequestBody CustomPageRequest requestDto,BindingResult result) {
                  if(result.hasErrors()){
                       return new ResponseEntity<ResponseDto<Page<ViewContactDto>>>(new 
                               ResponseDto<Page<ViewContactDto>>(null,null,
                               errorHandler.getErrorMessageMap(result)),HttpStatus.NOT_ACCEPTABLE);
                  }
                  try{
                       Page<ViewContactDto> pageList=contactDetailsService.getAllContactDetails(requestDto);
                       return new ResponseEntity<ResponseDto<Page<ViewContactDto>>>(new 
                               ResponseDto<Page<ViewContactDto>>(pageList,null),HttpStatus.OK); 
                  }catch(Exception e) {
                       return new ResponseEntity<ResponseDto<Page<ViewContactDto>>>(new 
                               ResponseDto<Page<ViewContactDto>>(null,
                               errorHandler.getMessageLocal(e.getMessage())),HttpStatus.OK);  
                  }
          }
}
