package telran.java38.student.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"id"})
@Document (collection =  "student2021")
public class Student {
	@Id
	Integer id;
	@Setter
	String name;
	@Setter
	String password;
	String group;
	@Setter
	Map<String, Integer> scores;
	
	public Student(Integer id, String name, String password, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
		this.password = password;
		scores = new HashMap<>();
	}
	
	public boolean addScore(String exam, int score) {
		return scores.put(exam, score) == null;		
	}
	

}
