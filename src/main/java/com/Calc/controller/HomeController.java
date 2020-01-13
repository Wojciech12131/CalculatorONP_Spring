
package com.Calc.controller;

import com.Calc.Calculator.Calculator;
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
                        

		Calculator calculator = new Calculator(leftOperand);

		String result = calculator.oblicz();

		model.addAttribute("leftOperand", leftNumber);
		model.addAttribute("result", result);
		model.addAttribute("view", "views/calculatorForm");
		return "base-layout";
	}
}
    