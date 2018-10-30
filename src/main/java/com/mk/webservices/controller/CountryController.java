package com.mk.webservices.controller;


import com.google.gson.Gson;

import com.mk.webservices.service.CitiesService;
import com.mk.webservices.service.CountriesService;
import com.mk.webservices.data.entity.City;
import com.mk.webservices.data.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountriesService countriesService;

	@Autowired
	private CitiesService citiesService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	Country selectedCountry;

	@GetMapping("/list")
	public String listCountries(Model theModel) {

		List<Country> theCountries = this.countriesService.getCountries();

		theModel.addAttribute("countries", theCountries);

		return "listcountries";
	}

	@RequestMapping(method= RequestMethod.GET, value="/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Country theCountry = new Country();

		theModel.addAttribute("country", theCountry);

		return "countryform";
	}

	@RequestMapping(method= RequestMethod.GET, value="/showFormForUpdate/{countryId}")
	public String showFormForUpdate(@PathVariable(value="countryId") Integer theId, Model theModel) {

		Country theCountry = this.countriesService.getCountry(theId);

		selectedCountry = theCountry;

		theModel.addAttribute("country", theCountry);

		return "countryform";
	}

	@RequestMapping(method= RequestMethod.POST, value="/saveCountry")
	public String saveCountry(@ModelAttribute("country") Country theCountry) {

		this.countriesService.saveCountry(theCountry);

		return "redirect:/country/list";
	}

	@RequestMapping(method= RequestMethod.GET, value="/showCityFormForAdd/{countryId}")
	public String showCityFormForAdd(Model theModel, @PathVariable(value="countryId") Integer theId) {

		selectedCountry = this.countriesService.getCountry(theId);

		City theCity = new City();

		theModel.addAttribute("city", theCity);

		return "cityform";
	}

	@RequestMapping(method= RequestMethod.POST, value="/saveCity")
	public String saveCity(@ModelAttribute("city") City theCity) {

		selectedCountry.addCity(theCity);

		this.countriesService.saveCountry(selectedCountry);

		return "redirect:/country/list";
	}

	@GetMapping(value="/cities", produces = "text/plain; charset = UTF-8")
	@ResponseBody
	public String test(@RequestParam("name") int theId) {

		Country theCountry = this.countriesService.getCountry(theId);

		List<City> cities = theCountry.getCities();

		Gson gson = new Gson();
		String json = gson.toJson(cities);
		//System.out.println(json);

		return json;
	}

	@GetMapping(value="/matching", produces = "text/plain; charset = UTF-8")
	@ResponseBody
	public String matchCities(@RequestParam("name") String letters) {

		List<City> cities = this.citiesService.matchCities(letters);

		Gson gson = new Gson();
		String json = gson.toJson(cities);
		//System.out.println(json);

		return json;
	}

	@RequestMapping(method= RequestMethod.GET, value="/delete/{countryId}")
	public String deleteCountry(@PathVariable(value="countryId") Integer theId) {

		countriesService.deleteCountry(theId);

		return "redirect:/country/list";
	}

}
