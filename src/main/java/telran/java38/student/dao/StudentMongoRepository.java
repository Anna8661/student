package telran.java38.student.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java38.student.model.Student;

public interface StudentMongoRepository extends MongoRepository<Student, Integer> {
	Stream <Student> findByGroup(String group);
	
	Stream<Student> findByGroupAndName(String group, String name);
	
	long countByGroup(String group);
	
//	@Query("{'scores.Math': {'$gte':4}}")
	@Query ("{'?0':{'gte':?1}}")
	Stream<Student> findByExamMinScore(String exam, int score);
	

}
