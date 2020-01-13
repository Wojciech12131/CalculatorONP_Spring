/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Calc.ICalc;


import java.lang.reflect.Method;
import java.util.ArrayList;


public interface ICalculator       
{
    public boolean cyfra(char c);
    public ArrayList<String> doONP(String dzial);
    public  String zONP(ArrayList<String> onp);
    public  String oblicz();
    public  int priorytet(char c);
}
