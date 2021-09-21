package telran.java38.student.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.student.dao.StudentMongoRepository;
import telran.java38.student.dto.ScoreExamDto;
import telran.java38.student.dto.StudentBaseDto;
import telran.java38.student.dto.StudentDto;
import telran.java38.student.dto.StudentUpdateDto;
import telran.java38.student.dto.exception.StudentNotFoundException;
import telran.java38.student.model.Student;


@Service
public class StudentServiseImpl implements StudentService {
	
	@Autowired
//	StudentRepository studentRepository;
	StudentMongoRepository studentRepository;

	@Override
	public boolean addStudent(StudentBaseDto studentCreateDto) {
		boolean res = studentRepository.existsById(studentCreateDto.getId());
		if (res) {
			return false;			
		}
		Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(), studentCreateDto.getPassword(), studentCreateDto.getGroup());
		studentRepository.save(student);
		return true;
 	}

	@Override
	public StudentDto findStudentById(Integer id) {
//		try {
//			student = studentRepository.findById(id).get();
//		} catch (NoSuchElementException e) {
//			throw new StudentNotFoundException("student id: " + id + ", not found");
//		}		
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));		
		return convertToStudentDto(student);	
	}

	@Override
	public StudentDto deleteStudentById(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));			
		studentRepository.deleteById(id);
		return convertToStudentDto(student);
	}

	@Override
	public StudentBaseDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));	
				
		if (studentUpdateDto.getName() != null) {
			student.setName(studentUpdateDto.getName());			
		}
		if (studentUpdateDto.getPassword() != null) {
			student.setPassword(studentUpdateDto.getPassword());			
		}	
		studentRepository.save(student);
		return convertToStudentBaseDto(student);
	}

	
	private StudentBaseDto convertToStudentBaseDto(Student student) {
		
		return StudentBaseDto.builder()
				.id(student.getId())
				.name(student.getName())
				.password(student.getPassword())
				.group(student.getGroup())
				.build();
		
	}

	@Override
	public boolean addScore(Integer id, ScoreExamDto scoreExamDto) {
		if (scoreExamDto.getExamName() == null || scoreExamDto.getScore() == null) {
			return false;
			
		}
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));	
				
		student.addScore(scoreExamDto.getExamName(), scoreExamDto.getScore());
		studentRepository.deleteById(id);
		studentRepository.save(student);
		return true;
	}
	
	private StudentDto convertToStudentDto(Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.name(student.getName())
				.scores(student.getScores())
				.group(student.getGroup())
				.build();			
	}

	@Override
	public List<StudentDto> findStudentsByGroup(String group) {
//		List<Student> students = studentRepository.findAll().stream()
//				.filter(s -> group.equals(s.getGroup()))
//				.collect(Collectors.toList());
//		return students.stream()
//				.map(s -> convertToStudentDto(s))
//				.collect(Collectors.toList());
		
		
		return studentRepository.findByGroup(group)
				.map(s -> convertToStudentDto(s))
				.collect(Collectors.toList());
				
	}

	@Override
	public List<StudentDto> findStudentsByParametrs(String group, String name) {
		return studentRepository.findByGroupAndName	(group, name)
				.map(s -> convertToStudentDto(s))
				.collect(Collectors.toList());
	}

	@Override
	public long countStudentsByGroup(String group) {
		return studentRepository.countByGroup(group);
		}

	@Override
	public List<StudentDto> findStudentsByExamScore(String exam, int minScore) {
		return studentRepository.findByExamMinScore("scores." + exam, minScore)
				.map(s -> convertToStudentDto(s))
				.collect(Collectors.toList());
	}

	

}
