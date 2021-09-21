package telran.java38.student.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
