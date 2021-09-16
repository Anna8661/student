package telran.java38.student.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import telran.java38.student.dto.ScoreExamDto;
import telran.java38.student.dto.StudentBaseDto;
import telran.java38.student.dto.StudentDto;
import telran.java38.student.dto.StudentUpdateDto;

public interface StudentService {
	boolean addStudent (StudentBaseDto studentCreateDto);
	StudentDto findStudentById (Integer id);
	StudentDto deleteStudentById (Integer id);
	StudentBaseDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto, String token) throws UnsupportedEncodingException;
	boolean addScore(Integer id, ScoreExamDto scoreExamDto);
	List<StudentDto> findStudentsByGroup(String group);
	List<StudentDto> findStudentsByParametrs(String group, String name);
	long countStudentsByGroup(String group);
	List<StudentDto> findStudentsByExamScore(String exam, int score);
	
	
}
