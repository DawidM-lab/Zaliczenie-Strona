package com.example.StronaJava1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class ConversionController {

	private static final double KM_TO_MILES_CONVERSION_RATE = 0.621371;
	private static final double MILES_TO_KM_CONVERSION_RATE = 1 / KM_TO_MILES_CONVERSION_RATE;

	@GetMapping("/")
	public String showForm() {
		return "index";
	}

	@PostMapping("/convert")
	public String convert(@RequestParam("wartosc") double wartosc, @RequestParam("typKonwersji") String typKonwersji, Model model) {
		double wynik;
		String wynikTyp;

		if ("kmNaMile".equals(typKonwersji)) {
			wynik = wartosc * KM_TO_MILES_CONVERSION_RATE;
			wynikTyp = "mile";
		} else {
			wynik = wartosc * MILES_TO_KM_CONVERSION_RATE;
			wynikTyp = "kilometry";
		}

		// Formatowanie wyniku do 3 miejsc po przecinku
		DecimalFormat df = new DecimalFormat("#.###");
		String wynikSformatowany = df.format(wynik);

		model.addAttribute("wartoscPoczatkowa", wartosc);
		model.addAttribute("typKonwersji", typKonwersji);
		model.addAttribute("wynik", wynikSformatowany);
		model.addAttribute("wynikTyp", wynikTyp);

		return "result";
	}
}
