package team1.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team1.model.Agent;
import team1.model.Estate;
import team1.repository.AgentRepository;
import team1.repository.EstateRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private EstateRepository estateRepo;

	@GetMapping
	public String index(Model model) {
		return "/home/home";
	}

	@GetMapping("/admin")
	public String adminHome(Model model) {
		List<Agent> agentList = (List<Agent>) agentRepo.findAll();
		Period diff; //creo una variabile periodo
		Integer daysDiff = 0; //variabile di controllo della differenza di giorni
		List<Estate> estateList = (List<Estate>) estateRepo.findAll();
		List<Estate> estateListForAdminHome = new ArrayList<Estate>();
		for(Estate e: estateList)
		{
			diff=e.getInsertionDate().until(LocalDate.now()); //differenza di periodo
			daysDiff = diff.getDays();
			
			if(daysDiff<=7)
			{
				estateListForAdminHome.add(e);
			}
		}
		
		
		model.addAttribute("estateList", estateListForAdminHome);
		model.addAttribute("agentList", agentList);
		return "/admin/adminHome";
	}
	
	@GetMapping("/search")
	public String searchPage()
	{
		return "/home/search";
	}
	
	@GetMapping("/estate/search")
	public String search(
			@RequestParam(name="queryAddress", required = false) String queryAddress,
			@RequestParam(name="queryZipCode", required = false) String queryZipCode,
			@RequestParam(name="queryCity", required = false) String queryCity,
			@RequestParam(name="queryProvince", required = false) String queryProvince,
			@RequestParam(name="queryPriceHigher", required = false) Double queryPriceHigher,
			@RequestParam(name="queryPriceLower", required = false) Double queryPriceLower,
			@RequestParam(name="queryEnergyClass", required = false) String queryEnergyClass,
			@RequestParam(name="queryNumSpaces", required = false) Integer queryNumSpaces,
			@RequestParam(name="queryNumBathrooms", required = false) Integer queryNumBathrooms,
			@RequestParam(name="queryNumBalconies", required = false) Integer queryNumBalconies,
			@RequestParam(name="queryHasCarSpot", required = false) Boolean queryHasCarSpot,
			@RequestParam(name="queryHasGarden", required = false) Boolean queryHasGarden,
			@RequestParam(name="queryAreaHigher", required = false) Integer queryAreaHigher,
			@RequestParam(name="queryAreaLower", required = false) Integer queryAreaLower,
			Model model) 
	{
		 if (queryAddress != null && queryAddress.isEmpty()) {
			 queryAddress = null;
		    }
		 if (queryZipCode != null && queryZipCode.isEmpty()) {
			 queryZipCode = null;
		    }
		 if (queryCity != null && queryCity.isEmpty()) {
			 queryCity = null;
		    }
		 if (queryProvince != null && queryProvince.isEmpty()) {
			 queryProvince = null;
		    }
		 if (queryPriceHigher != null && queryPriceHigher.isNaN()) {
			 queryPriceHigher = null;
		    }
		 if (queryPriceLower != null && queryPriceLower.isNaN()) {
			 queryPriceLower = null;
		    }
		 if (queryEnergyClass != null && queryEnergyClass.isEmpty()) {
			 queryEnergyClass = null;
		    }
		 if (queryNumBathrooms != null) {
			 queryNumBathrooms = null;
		 	}
		 if (queryNumSpaces != null) {
			 queryNumSpaces = null;
			 }
		 if (queryNumBalconies != null) {
			 queryNumBalconies = null;
			 }
		 if (queryHasCarSpot != null) {
			 queryHasCarSpot = null;
		    }
		 if (queryHasGarden != null) {
			 queryHasGarden = null;
		    }
		 if (queryAreaHigher != null) {
			 queryAreaHigher = null;
			 }
		 if (queryAreaLower != null) {
			 queryAreaLower = null;
			 }
		 
		 List<Estate> result =  estateRepo.findByAddressContainingIgnoreCaseOrZipCodeOrCityContainingIgnoreCaseOrProvinceIgnoreCaseOrPriceGreaterThanEqualOrPriceLessThanEqualOrEnergyClassIgnoreCaseOrNumSpacesOrNumBathroomsOrNumBalconiesOrHasCarSpotOrHasGardenOrAreaGreaterThanEqualOrAreaLessThanEqualOrderByAddressAsc
				 (
						 queryAddress, 
						 queryZipCode, 
						 queryCity, 
						 queryProvince, 
						 queryPriceHigher, 
						 queryPriceLower, 
						 queryEnergyClass, 
						 queryNumSpaces, 
						 queryNumBathrooms, 
						 queryNumBalconies, 
						 queryHasCarSpot, 
						 queryHasGarden, 
						 queryAreaHigher, 
						 queryAreaLower
				);
		 model.addAttribute("estateList", result);
		 return "/estate/estateList";
	}
}
