package it.objectmethod.springjdbc.main.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import it.objectmethod.springjdbc.main.dao.ICountryDao;
import it.objectmethod.springjdbc.main.model.Country;
import it.objectmethod.springjdbc.main.model.mapper.CountryMapper;

public class CountryDaoImp  implements ICountryDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	@Override
	public List <String> getNameContinent()  {
		List<String> country = null;
		String sql = "select distinct continent from country";
		country = this.jdbcTemplateObject.queryForList(sql, String.class);	
		return country;
	}
	@Override
	public List <Country> getNazioniByContinent(String ParContinent) { 
			List<Country> country = null;
			String sql = "select name, Code, Continent, population from country where continent=?";
			country = this.jdbcTemplateObject.query(sql, new Object[]{ParContinent},new CountryMapper());	
			return country;
	}
	@Override
	public List <Country> getAllNazioni(){
		
		String sql = "select * from country";
		List<Country> country = null;
		country=this.jdbcTemplateObject.query(sql,new CountryMapper());
		return country;
	}
}