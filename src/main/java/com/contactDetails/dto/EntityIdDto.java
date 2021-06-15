
package com.contactDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author angad
 * Date : 14 June 2021
 * This class used for entityId parameters.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntityIdDto {

       @NonNull
       private Long entityId;
}
