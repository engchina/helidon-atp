package io.helidon.atp.mybatis.common.util;

import javax.enterprise.context.ApplicationScoped;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import io.helidon.atp.mybatis.common.config.AppConfig;

@ApplicationScoped
public class JdbcUtil {

	private final static Class<JdbcUtil> LOCK = JdbcUtil.class;

	private static SqlSessionFactory sqlSessionFactory = null;

	private JdbcUtil() {

	}

	private static SqlSessionFactory getSqlSessionFactory() {

		synchronized (LOCK) {

			if (sqlSessionFactory != null) {
				return sqlSessionFactory;
			}

			try {

				TransactionFactory transactionFactory = new JdbcTransactionFactory();
				Environment environment = new Environment("product", transactionFactory, AppConfig.ds);
				Configuration configuration = new Configuration(environment);
				configuration.getTypeAliasRegistry().registerAliases("io.helidon.atp.mybatis.entity");
				configuration.addMappers("io.helidon.atp.mybatis.mapper");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			return sqlSessionFactory;
		}
	}

	public static SqlSession openSqlSession() {

		if (sqlSessionFactory == null) {
			getSqlSessionFactory();
		}

		return sqlSessionFactory.openSession();
	}

}
