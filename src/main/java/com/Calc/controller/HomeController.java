
package com.Calc.controller;

import com.Calc.Facade.FCalculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("view", "views/calculatorForm");
		return "base-layout";
	}

	@PostMapping("/")
	public String index(
			@RequestParam String leftOperand,
			Model model
	) {
                String leftNumber;
			leftNumber = leftOperand;
                        

		FCalculator calculator = new FCalculator(leftOperand);

		String result = calculator.GetResult();

		model.addAttribute("leftOperand", leftNumber);
		model.addAttribute("result", result);
		model.addAttribute("view", "views/calculatorForm");
		return "base-layout";
	}
}
    