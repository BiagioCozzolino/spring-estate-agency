package team1.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team1.model.Agent;
import team1.model.Appointment;
import team1.model.Estate;
import team1.repository.AgentRepository;
import team1.repository.AppointmentRepository;
import team1.repository.EstateRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AgentRepository agentRepo;

	@Autowired
	private EstateRepository estateRepo;

	@Autowired
	private AppointmentRepository appRepo;

	@GetMapping
	public String index(Model model) {
		return "/home/home";
	}

	@GetMapping("/admin")
	public String adminHome(Model model) {
		List<Agent> agentList = (List<Agent>) agentRepo.findAll();

		long daysDiff = 8;
		long daysDiffApp = 15;
		List<Estate> estateList = (List<Estate>) estateRepo.findAll();
		List<Estate> estateListForAdminHome = new ArrayList<Estate>();

		List<Appointment> appList = (List<Appointment>) appRepo.findAllByOrderByDateAscHourAsc();
		List<Appointment> appListForAdminHome = new ArrayList<Appointment>();

		List<Estate> estateListTopTen = estateRepo.findTop10ByOrderByNumViewsDesc();

		for (Estate e : estateList) {
			daysDiff = Duration.between(e.getInsertionDate().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();

			if (daysDiff <= 7) {

				estateListForAdminHome.add(e);
			}
		}
		for (Appointment a : appList) {
			daysDiffApp = Duration.between(LocalDate.now().atStartOfDay(), a.getDate().atStartOfDay()).toDays();

			if (daysDiffApp <= 14 && a.getStatus().equalsIgnoreCase("Da effettuare")) {
				appListForAdminHome.add(a);
			}
		}
		model.addAttribute("topTen", estateListTopTen);
		model.addAttribute("appList", appListForAdminHome);
		model.addAttribute("estateList", estateListForAdminHome);
		model.addAttribute("agentList", agentList);
		return "/admin/adminHome";

	}

	@GetMapping("/search")
	public String searchPage() {
		return "/home/search";
	}

	@GetMapping("/estate/search")
	public String search(@RequestParam(name = "queryAddress", required = false) String queryAddress,
			@RequestParam(name = "queryZipCode", required = false) String queryZipCode,
			@RequestParam(name = "queryCity", required = false) String queryCity,
			@RequestParam(name = "queryProvince", required = false) String queryProvince,
			@RequestParam(name = "queryPriceHigher", required = false) Double queryPriceHigher,
			@RequestParam(name = "queryPriceLower", required = false) Double queryPriceLower,
			@RequestParam(name = "queryEnergyClass", required = false) String queryEnergyClass,
			@RequestParam(name = "queryNumSpaces", required = false) Integer queryNumSpaces,
			@RequestParam(name = "queryNumBathrooms", required = false) Integer queryNumBathrooms,
			@RequestParam(name = "queryNumBalconies", required = false) Integer queryNumBalconies,
			@RequestParam(name = "queryHasCarSpot", required = false) Boolean queryHasCarSpot,
			@RequestParam(name = "queryHasGarden", required = false) Boolean queryHasGarden,
			@RequestParam(name = "queryAreaHigher", required = false) Integer queryAreaHigher,
			@RequestParam(name = "queryAreaLower", required = false) Integer queryAreaLower,
			@RequestParam(name = "queryStatus", required = false) String queryStatus,
			@RequestParam(name = "queryType", required = false) String queryType, Model model) {
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
		if (queryStatus != null && queryStatus.isEmpty()) {
			queryStatus = null;
		}
		if (queryType != null && queryType.isEmpty()) {
			queryType = null;
		}
		List<Estate> result = estateRepo
				.findByAddressContainingIgnoreCaseOrZipCodeOrCityContainingIgnoreCaseOrProvinceIgnoreCaseOrPriceGreaterThanEqualOrPriceLessThanEqualOrEnergyClassIgnoreCaseOrNumSpacesOrNumBathroomsOrNumBalconiesOrHasCarSpotOrHasGardenOrAreaGreaterThanEqualOrAreaLessThanEqualOrStatusIgnoreCaseOrTypeIgnoreCaseOrderByAddressAsc(
						queryAddress, queryZipCode, queryCity, queryProvince, queryPriceHigher, queryPriceLower,
						queryEnergyClass, queryNumSpaces, queryNumBathrooms, queryNumBalconies, queryHasCarSpot,
						queryHasGarden, queryAreaHigher, queryAreaLower, queryStatus, queryType);

		model.addAttribute("estateList", result);
		return "/estate/estateList";
	}
}