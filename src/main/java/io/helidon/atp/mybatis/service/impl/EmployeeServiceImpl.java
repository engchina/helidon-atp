package io.helidon.atp.mybatis.service.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.apache.ibatis.session.SqlSession;

import io.helidon.atp.mybatis.entity.Employee;
import io.helidon.atp.mybatis.entity.EmployeeExample;
import io.helidon.atp.mybatis.mapper.EmployeeMapper;
import io.helidon.atp.mybatis.service.EmployeeService;

@RequestScoped
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeMapper employeeMapper;

	public EmployeeServiceImpl() {

	}

	@Override
	public void setEmployeeMapper(SqlSession sqlSession) {
		this.employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
	}

	@Override
	public long countByExample(EmployeeExample example) {

		return employeeMapper.countByExample(new EmployeeExample());
	}

	@Override
	public int deleteByExample(EmployeeExample example) {

		return 0;
	}

	@Override
	public int insert(Employee record) {

		return 0;
	}

	@Override
	public int insertSelective(Employee record) {

		return 0;
	}

	@Override
	public List<Employee> selectByExample(EmployeeExample example) {

		return employeeMapper.selectByExample(new EmployeeExample());
	}

	@Override
	public int updateByExampleSelective(Employee record, EmployeeExample example) {

		return 0;
	}

	@Override
	public int updateByExample(Employee record, EmployeeExample example) {

		return 0;
	}

}
