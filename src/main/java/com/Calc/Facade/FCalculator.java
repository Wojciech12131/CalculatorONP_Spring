/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Calc.Facade;

import com.Calc.ICalc.ICalculator;
import com.Calc.Calculator.Calculator;


public class FCalculator {
    ICalculator Class1;
    public FCalculator(String Equation)
    {
        this.Class1 = new Calculator(Equation);
    }
     public String GetResult()
     {return Class1.GetResult();}
    }

