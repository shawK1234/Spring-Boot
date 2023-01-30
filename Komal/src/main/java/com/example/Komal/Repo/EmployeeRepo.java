package com.example.Komal.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Komal.Model.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Integer> {
	
	List<Employee> findByName(String name);

	@Query(value="SELECT * FROM Employee",nativeQuery = true)
	List<Employee> findallfromEmployee();
	

}
