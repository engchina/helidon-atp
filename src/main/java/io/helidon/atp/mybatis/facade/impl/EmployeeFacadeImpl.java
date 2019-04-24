package io.helidon.atp.mybatis.facade.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import io.helidon.atp.mybatis.common.util.JdbcUtil;
import io.helidon.atp.mybatis.entity.Employee;
import io.helidon.atp.mybatis.entity.EmployeeExample;
import io.helidon.atp.mybatis.facade.EmployeeFacade;
import io.helidon.atp.mybatis.service.EmployeeService;

@RequestScoped
public class EmployeeFacadeImpl implements EmployeeFacade {

	@Inject
	EmployeeService employeeService;

	public EmployeeFacadeImpl() {

	}

	@Override
	public List<Employee> selectEmployeeByExample() {

		SqlSession sqlSession = JdbcUtil.openSqlSession();
		employeeService.setEmployeeMapper(sqlSession);

		List<Employee> employees = null;
		try {

			employees = employeeService.selectByExample(new EmployeeExample());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}

			employeeService = null;
		}

		return employees;
	}
}
