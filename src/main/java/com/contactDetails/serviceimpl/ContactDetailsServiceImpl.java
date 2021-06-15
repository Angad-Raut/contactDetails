package com.contactDetails.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.contactDetails.dto.ContactDto;
import com.contactDetails.dto.CustomPageRequest;
import com.contactDetails.dto.EntityIdDto;
import com.contactDetails.dto.ViewContactDto;
import com.contactDetails.exception.AlreadyExistException;
import com.contactDetails.exception.InvalidDataException;
import com.contactDetails.exception.ResourceNotFoundException;
import com.contactDetails.model.ContactDetails;
import com.contactDetails.repository.ContactDetailsRepository;
import com.contactDetails.service.ContactDetailsService;
import com.contactDetails.util.BasicConversion;
import com.contactDetails.util.MessageHandler;
import com.contactDetails.util.PageConverter;

/**
 * @author angad
 * Date : 14 June,2021
 * This class used for to add the service implementation of contact details service.
 */
@Component
public class ContactDetailsServiceImpl implements ContactDetailsService {
    
    /**
     * This is used for to access the repository queries.
     */
    @Autowired
    ContactDetailsRepository contactRepository;
    
    /**
     * This is used for to access the custom error messages.
     */
    @Autowired
    MessageHandler messageHandler;
    
    @Value("${spring.profiles.active}")
    private String ACTIVE_PROFILE;

    /**
     * This method is  used for to add/update contact details.
     */
    @Override
    public Boolean saveOrupdate(ContactDto requestDto) throws AlreadyExistException, InvalidDataException {
        ContactDetails details=null;
        Boolean result=false;
        if(requestDto.getId()==null){//insert operation
                 isMobileExist(requestDto.getMobile());
                 isEmailExist(requestDto.getEmail());
                details=new ContactDetails(null, requestDto.getFirstName(), requestDto.getLastName(), requestDto.getEmail(),
                        requestDto.getMobile(), true, new Date(), new Date());
        }else{//update operation
                details=getById(new EntityIdDto(requestDto.getId()));
                if(!requestDto.getFirstName().equals(details.getFirstName())){
                    details.setFirstName(requestDto.getFirstName());
                }
                if(!requestDto.getLastName().equals(details.getLastName())){
                    details.setLastName(requestDto.getLastName());
                }
                if(!requestDto.getEmail().equals(details.getEmail())){
                    isEmailExist(requestDto.getEmail());
                    details.setEmail(requestDto.getEmail());
                }
                if(!requestDto.getMobile().equals(details.getMobile())){
                    isMobileExist(requestDto.getMobile());
                    details.setMobile(requestDto.getMobile());
                }
                details.setUpdateTime(new Date());
        }
        try{
               if(contactRepository.save(details)!=null){
                    result=true;
               }
        }catch(DataIntegrityViolationException e){
               throw new InvalidDataException(e.getMessage());
        }catch(Exception e){
              throw new InvalidDataException(e.getMessage());
        }
        return result;
    }

    /**
     * This method used for to fetch contact details by contact details id.
     */
    @Override
    public ContactDetails getById(EntityIdDto requestDto) throws ResourceNotFoundException {
            ContactDetails details=contactRepository.getById(requestDto.getEntityId());
            if(details==null){
                    throw new ResourceNotFoundException(messageHandler.CONTACT_DETAILS_NOT_FOUND_BY_ID);
            }else{
                    return details;
            }
    }

    /**
     * This method used for to detele contact details by contact details id.
     */
    @Override
    public Boolean deleteById(EntityIdDto requestDto) throws ResourceNotFoundException {
            ContactDetails details=contactRepository.getById(requestDto.getEntityId());
            if(details==null){
                    throw new ResourceNotFoundException(messageHandler.CONTACT_DETAILS_NOT_FOUND_BY_ID);
            }else{
                     contactRepository.deleteById(requestDto.getEntityId());
                     if(contactRepository.getById(requestDto.getEntityId())==null){
                           return true;
                     }else{
                           return false;
                     }
            }
    }

    /**
     * This method used for to fetch all contact details with page response.
     */
    @Override
    public Page<ViewContactDto> getAllContactDetails(CustomPageRequest request) {
        Boolean isSearchParam = true;
        Direction direction = Direction.ASC;
        Page<ContactDetails> pageReq = null;
        if (request.getSortNamedir() != null) {
            if (request.getSort().trim().equals("srNo")){
                request.setSort("update_time");
            }else if(request.getSort().trim().equals("firstName")){
                request.setSort("first_name");
            }else if(request.getSort().trim().equals("lastName")){
                request.setSort("last_name");
            }else if(request.getSort().trim().equals("mobile")){
                request.setSort("mobile");
            }else if(request.getSort().trim().equals("email")){
                request.setSort("email");
            }else {
                request.setSort("account_no");
            }
            if (request.getSortNamedir().trim().equalsIgnoreCase("desc"))
                direction = Direction.DESC;
        } else {
            request.setSort("update_time");
        }
        if(request.getSearchParam() != null) {
            isSearchParam=false;
        }
        PageRequest pageRequest=PageRequest.of(request.getPage() - 1, request.getSize(), direction, request.getSort());
        pageReq=contactRepository.getAllContactDetails(isSearchParam, request.getSearchParam(), pageRequest);
        return PageConverter.map(pageReq, toList(pageReq));
    }
    
    /**
     * This method used for to collect contact details and store into list.
     * @param pageRequest
     * @return
     */
    public List<ViewContactDto> toList(Page<ContactDetails> pageRequest) {
            List<ViewContactDto> list=new ArrayList<ViewContactDto>();
            Integer index=1;
            for(ContactDetails details:pageRequest.getContent()){
                     ViewContactDto dto=new ViewContactDto();
                     dto.setSrNo(index);
                     dto.setId(details.getCId());
                     dto.setFirstName(details.getFirstName());
                     dto.setLastName(details.getLastName());
                     dto.setEmail(details.getEmail());
                     dto.setMobile(details.getMobile());
                     dto.setStatus(details.getStatus());
                     dto.setIsActive(BasicConversion.getStatus(details.getStatus()));
                     list.add(dto);
                     index++;
            }
            return list;
    }

    /**
     * This method used for to update contact details  status by contact details id.
     */
    @Override
    public Boolean updateStatus(EntityIdDto requestDto) throws ResourceNotFoundException {
           getById(requestDto);
           Boolean status=true;
           if(BasicConversion.isRecordExist(contactRepository.isEnabled(requestDto.getEntityId(), true))){
                 status=false;
           }
           try{
                   Integer count= contactRepository.updateStatus(requestDto.getEntityId(), status, new Date());
                   if(count==1){
                       return true;
                   }else{
                       return false;
                   }
           }catch(Exception e){
                  throw new InvalidDataException(e.getMessage());
           }
    }
    
    /**
     * This method used for to check email exists or not in database.
     * @param email
     * @return
     * @throws AlreadyExistException
     */
    public Boolean isEmailExist(String email) throws AlreadyExistException {
             if(BasicConversion.isRecordExist(contactRepository.isEmailExist(email))){
                   throw new AlreadyExistException(messageHandler.EMAIL_ID_ALREADY_PRESENT);
             }else {
                   return false;
             }
    }
    
    /**
     * This method used for to check mobile exists or not in database.
     * @param mobile
     * @return
     * @throws AlreadyExistException
     */
    public Boolean isMobileExist(Long mobile) throws AlreadyExistException {
            if(BasicConversion.isRecordExist(contactRepository.isMobileExist(mobile))){
                   throw new AlreadyExistException(messageHandler.MOBILE_NUMBER_ALREADY_PRESENT);
            }else {
                   return false;
            }
    }

    /**
     * This method used for to delete all data.
     */
    @Override
    public void deleteAll() {
          if(ACTIVE_PROFILE.equals("Test")){
                contactRepository.deleteAll();
          }
    }
}
