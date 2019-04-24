package io.helidon.atp.mybatis.facade;

import java.util.List;

import io.helidon.atp.mybatis.entity.Employee;

public interface EmployeeFacade {

	List<Employee> selectEmployeeByExample();
}
