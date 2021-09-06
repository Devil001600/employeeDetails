package com.devil16.demo.employee.config;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * PersistenceConfigH2 class - 
 * 
 * houses methods that produce DataSource and SqlSessionFactory beans
 * these beans are used by the Data Access Object 
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 * 
 * @see com.devil16.demo.employee.persistence.SelectEmployeeById
 */

@Configuration
@MapperScan("com.devil16.demo.employee.dao")
public class PersistenceConfigH2 {
	
	//@Bean annotated functions get executed during java configuration and their returns are registered as beans
	
	/**
	 * dataSourceH2() - 
	 * 
	 * reads the spring.datasource.* properties from the application.properties config-file
	 * creates an H2DataSource object
	 * 
	 * @return DataSource object
	 */
	@Bean (name = "DataSourceH2", destroyMethod = StringUtils.EMPTY)
	public DataSource dataSourceH2() {
		
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
				
	}

	/**
	 * sqlSessionFactoryH2() - 
	 * 
	 * creates an SqlSqssionFactory object out of an H2DataSource object
	 * 
	 * @param dataSourceH2
	 * @return SqlSessionFactory object
	 */
	@Bean (name = "SqlSessionFactoryH2", destroyMethod = StringUtils.EMPTY)
	public SqlSessionFactory sqlSessionFactoryH2(
			@Qualifier ("DataSourceH2") final DataSource dataSourceH2) throws Exception{
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSourceH2);
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return sqlSessionFactoryBean.getObject();
		
	}
}
