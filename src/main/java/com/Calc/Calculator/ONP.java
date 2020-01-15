/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Calc.Calculator;

import java.util.ArrayList;
import java.util.Stack;

public class ONP {
    public static  ArrayList<String> toONP(String dzial) {
        dzial+="=";
        ArrayList<String> wyjscie = new ArrayList<>();
        Stack<Character> stos = new Stack<>();
        for (int i = 0; dzial.charAt(i) != '='; i++) 
        {
            if(!Character.isWhitespace(dzial.charAt(i)))
            {   
                if (Character.isDigit(dzial.charAt(i))) 
                        {
                            int poczatek = i;
                            while (Character.isDigit(dzial.charAt(i))) 
                            {
                                i++;
                            }
                            wyjscie.add(dzial.substring(poczatek, i));
                            i--;
                        } 
                 else 
                    {
                        if(dzial.charAt(i)=='(')
                            {
                                    stos.add('(');
                            }
                        else if(dzial.charAt(i)==')')
                            {
                                while(stos.peek()!='(')
                                    {
                                        wyjscie.add(String.valueOf(stos.pop()));
                                    }
                                stos.pop();
                            }
                        else
                            {
                                if(stos.empty()||priorytet(dzial.charAt(i))>priorytet(stos.peek()))
                                    {
                                        stos.add(dzial.charAt(i));
                                    }
                                else if(dzial.charAt(i)=='q'||dzial.charAt(i)=='r'||dzial.charAt(i)=='t')
                                    {
                                    }
                                else
                                    {
                                        wyjscie.add(String.valueOf(stos.pop()));
                                        stos.add(dzial.charAt(i));
                                    }
                            }
                }
            }
        }
        while (stos.empty() == false) 
            {
                wyjscie.add(String.valueOf(stos.pop()));
            }
        return wyjscie;
    }
        private static int priorytet(char c) 
        {
        switch (c) 
            {
                case '+': ;
                case '-':
                    return 1;
                case '*': ;
                case '/':
                    return 2;
                case '^': ;
                case 's':
                    return 3;
            }
        return 0;
        }
}
