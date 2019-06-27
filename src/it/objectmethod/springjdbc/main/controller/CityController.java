package it.objectmethod.springjdbc.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.springjdbc.main.dao.ICityDao;
import it.objectmethod.springjdbc.main.model.City;

@Controller
public class CityController {
	@Autowired
	private ICityDao CityDaoImp;

	@RequestMapping("/{nazione}/citta")
	public String NazioniByContinent(@PathVariable("nazione") String nazione, ModelMap model) {
		String AZ = "Alfabetico";
		String POPA = "Popolazione Crescente";
		List<City> cityList = CityDaoImp.getNameCitybyNation(nazione);
		model.addAttribute("citta", cityList);
		model.addAttribute("AZ", AZ);
		model.addAttribute("POPA", POPA);
		return "cityList";
	}

	@RequestMapping("/delete")
	public String Delete(@RequestParam("cityid") int cityid, @RequestParam("codNazione") String codNazione) {
		CityDaoImp.deleteCity(cityid);
		return "redirect:" + codNazione + "/citta";
	}

	@RequestMapping("/cittabyid")
	public String cittaById(@RequestParam("id") int id, ModelMap model) {
		City cittabyid = CityDaoImp.cityById(id);
		model.addAttribute("citta", cittabyid);
		return "menuModCity";

	}
	@RequestMapping("/modifica-aggiungi")
	public String modifica(@RequestParam("id") String id, @RequestParam("newPopulation") String newPopulation,
			@RequestParam("newCodNation") String newCodNation, @RequestParam("newCity") String newCity){
	   
		if(id!="") {
		CityDaoImp.ModCity(newCity, newPopulation, newCodNation, id);
		}else if(id==""){
			CityDaoImp.AddCity(newCity, newPopulation, newCodNation);
		}
		return"redirect:" +newCodNation+ "/citta";
}      
	@RequestMapping ("/citta/ordina")
public String ordina (@RequestParam ("codNazione") String codNazione, @RequestParam("ord")String ord,ModelMap model) {
	String AZ="Alfabetico";
	String POPA=null;
	if (ord.equals("Alfabetico")){
	 AZ="Alfabetico Decrescente";
	}else {
		AZ="Alfabetico";
	}
	if (ord.equals("Popolazione Crescente")) {
		POPA="Popolazione Decrescente";
	}else {
		POPA="Popolazione Crescente";
	}
	List<City> cityList = CityDaoImp.getNameCitybyNationOrd(codNazione, ord);
	model.addAttribute("citta", cityList);
	model.addAttribute("AZ", AZ);
	model.addAttribute("POPA",POPA);
	return"cityList";
}
}