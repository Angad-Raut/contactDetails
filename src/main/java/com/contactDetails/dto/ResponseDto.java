package com.contactDetails.dto;

import java.util.Map;

/**
 * @author angad
 * Date : 14 June 2021
 * This class used for api  response.
 */
public class ResponseDto<T> {
       private T result;
       private String errorMessage;
       private Map<String, String> errorValidations;
       
       public ResponseDto(){
    	   
       }

		public ResponseDto(T result, String errorMessage) {
			super();
			this.result = result;
			this.errorMessage = errorMessage;
		}

		public ResponseDto(T result, String errorMessage, Map<String, String> errorValidations) {
			super();
			this.result = result;
			this.errorMessage = errorMessage;
			this.errorValidations = errorValidations;
		}

		public T getResult() {
			return result;
		}

		public void setResult(T result) {
			this.result = result;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public Map<String, String> getErrorValidations() {
			return errorValidations;
		}

		public void setErrorValidations(Map<String, String> errorValidations) {
			this.errorValidations = errorValidations;
		}
		
		
       
}
