package it.objectmethod.springjdbc.main.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import it.objectmethod.springjdbc.main.dao.ICityDao;
import it.objectmethod.springjdbc.main.model.City;
import it.objectmethod.springjdbc.main.model.mapper.CityMapper;

public class CityDaoImp implements ICityDao{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	@Override 
	public List<City> getNameCitybyNation(String parNation) {
		List<City> CityDao = null;
		String sql = "select t1.ID, t1.Name, t1.CountryCode, t1.Population from city t1 JOIN country t2 ON t1.countrycode=t2.Code  where t1.CountryCode=?";
		CityDao = this.jdbcTemplateObject.query(sql, new Object[]{parNation},new CityMapper());	
		return CityDao;
}   @Override 
	public void deleteCity (int id) {
		String sql= "delete from city where id=?";
		jdbcTemplateObject.update(sql, new Object[]{id});	
	}
@Override 
	public City cityById(int id) {
		City CityDao=null;
		String sql = "select*from city where id=?";
		CityDao = this.jdbcTemplateObject.queryForObject(sql, new Object[]{id},new CityMapper());	
		return CityDao;
	}
@Override 
	public void ModCity(String newName, String newPopulation, String newCodNation, String idCity) {
	String sql="UPDATE city SET city.Name=?, city.population=?, city.countryCode=? where city.ID=?";
	jdbcTemplateObject.update(sql, new Object[]{newName,newPopulation,newCodNation,idCity});	
	}
@Override 
	public void AddCity(String newName, String newPopulation, String newCodNation) {
		String sql="INSERT INTO city(Name, population, CountryCode) VALUES (?,?,?)";
		jdbcTemplateObject.update(sql, new Object[]{newName,newPopulation,newCodNation});	
		}
@Override 
	public List<City> getNameCitybyNationOrd(String codNazione, String ord) {
		List<City> CityDao = null;
        String parametro=null;
		String qry ="select t1.ID, t1.Name, t1.CountryCode, t1.Population from city t1 JOIN country t2 ON t1.countrycode=t2.Code  where t1.CountryCode=? ";
		if (ord.equals("Alfabetico")){
			parametro="order by t1.name ASC";
			qry= qry+parametro;
		}
		else if (ord.equals("Alfabetico Decrescente")) {
			parametro="order by t1.name DESC";
			qry=qry+parametro;
		}
		else if (ord.equals("Popolazione Crescente")) {
			parametro= " order by t1.population ASC";
			qry=qry+parametro;
		}
		else if (ord.equals("Popolazione Decrescente")) {
			parametro=" order by t1.population DESC";
			qry=qry+parametro;
		}
		CityDao = this.jdbcTemplateObject.query(qry, new Object[]{codNazione},new CityMapper());	
		return CityDao;
}
}
