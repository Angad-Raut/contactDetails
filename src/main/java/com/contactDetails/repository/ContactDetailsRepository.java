package com.contactDetails.repository;

import java.math.BigInteger;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contactDetails.model.ContactDetails;

/**
 * @author angad
 * Date : 14 June,2021
 * This is ContactDetailsRepository class.
 */
@Repository
public interface ContactDetailsRepository extends CrudRepository<ContactDetails, Long> {
    
       /**
        *  This custom query used for to fetch contact details by contact id.
        * @param entityId
        * @return
        */
        @Query(value="select * from contact_details where c_id=:entityId ",nativeQuery=true)
        ContactDetails  getById(@Param("entityId")Long entityId);

        /**
         * This custom query used for to check requested email exists in database or not.
         * @param email
         * @return
         */
        @Query(value="select exists(select * from contact_details  where email=:email)as isExist",nativeQuery=true)
        BigInteger isEmailExist(@Param("email")String email);
        
        /**
         * This custom query used for to check requested mobile exists in database or not.
         * @param mobile
         * @return
         */
        @Query(value="select exists(select * from contact_details  where mobile=:mobile)as isExist",nativeQuery=true)
        BigInteger isMobileExist(@Param("mobile")Long mobile);
        
        /**
         * This custom query used for to check status active or not.
         * @param entityId
         * @param status
         * @return
         */
        @Query(value="select exists(select * from contact_details  where c_id=:entityId  and  status=:status)as isExist",nativeQuery=true)
        BigInteger isEnabled(@Param("entityId")Long entityId,@Param("status")Boolean status);
        
        /**
         * This custom query used for to update status active or inactive by contact details id.
         * @param entityId
         * @param status
         * @param updatetime
         * @return
         */
        @Modifying
        @Transactional
        @Query(value="update contact_details set status=:status,update_time=:updatetime  "
                          + "where c_id=:entityId",nativeQuery=true)
        Integer updateStatus(@Param("entityId")Long entityId,@Param("status")Boolean status,@Param("updatetime")Date updatetime);
        
        /**
         * This custom query used for to fetch all contact details with page request.
         * @param isSearchParam
         * @param searchParam
         * @param pageable
         * @return
         */
        @Query(value="(select * from contact_details c  "
                +"where  ((:isSearchParam or c.first_name like %:searchParam%)  "
                +"or (:isSearchParam or c.last_name like %:searchParam%) "
                +"or (:isSearchParam or c.mobile like %:searchParam%) "
                +"or (:isSearchParam or c.email like %:searchParam%))) \n#pageable\n ",
        countQuery="(select count(*) from contact_details c  "
                +"where ((:isSearchParam or c.first_name like %:searchParam%)  "
                +"or (:isSearchParam or c.last_name like %:searchParam%) "
                +"or (:isSearchParam or c.mobile like %:searchParam%) "
                +"or (:isSearchParam or c.email like %:searchParam%)))",nativeQuery=true)
       Page<ContactDetails> getAllContactDetails(@Param("isSearchParam")Boolean isSearchParam,
                 @Param("searchParam")String searchParam,Pageable pageable);
}
