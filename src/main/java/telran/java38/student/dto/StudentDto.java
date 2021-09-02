package telran.java38.student.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentDto {
	Integer id;
	String name;
	String group;
	Map<String, Integer> scores;
	

}
