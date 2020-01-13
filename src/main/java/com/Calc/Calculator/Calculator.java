
package com.Calc.Calculator;

import com.Calc.ICalc.ICalculator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Calculator implements ICalculator{
    private  loaderClass klasa = loaderClass.getInstance();
    String zapis;
    String result;
    public Calculator(String leftOperand)
    {
    this.zapis = leftOperand;}
    public void loading()
    {}
    public  boolean cyfra(char c) {
        return c >= 48 && c <= 57;
    }
    public  ArrayList<String> doONP(String dzial) {
        dzial+="=";
        ArrayList<String> wyjscie = new ArrayList<>();
        Stack<Character> stos = new Stack<>();
        for (int i = 0; dzial.charAt(i) != '='; i++) 
        {
            if (cyfra(dzial.charAt(i))) 
            {
                int poczatek = i;
                //System.out.println("Poczatek:"+poczatek);
                while (cyfra(dzial.charAt(i))) 
                {
                    i++;
                }
                //System.out.println(dzial.substring(poczatek,i));
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
        while (stos.empty() == false) {
            wyjscie.add(String.valueOf(stos.pop()));
        }
        return wyjscie;
    }
    public String zONP(ArrayList<String> onp)
    {
        Integer num1,num2;
        Stack<Integer> stos=new Stack<>();
        for(int i=0;i<onp.size();i++)
        {
            if(cyfra((onp.get(i)).charAt(0)))
            {
                stos.add(Integer.valueOf(onp.get(i)));
            }
            else
            {
                switch(onp.get(i).charAt(0))
                {
                    case '+':
                        stos.add(stos.pop()+stos.pop());
                    break;
                    case '-':
                        num2=stos.pop();
                        num1=stos.pop();
                        stos.add(num1-num2);
                    break;
                    case '*':
                        stos.add(stos.pop()*stos.pop());
                    break;
                    case '/':
                    {
                        num2=stos.pop();
                        num1=stos.pop();
                        
                       if(num2==0)return "dzielenie przez zero";
                        stos.add(num1/num2);    
                      
                        break;
                    }
                    case '^':
                        num2=stos.pop();
                        num1=stos.pop();
                        stos.add((int)klasa.potega(num1, num2));
                    break;
                    case 's':
                        stos.add((int)klasa.sqrt(stos.pop()));
                    break; 
                }
            }
        }
        result=stos.toString();
        return result.substring(1, result.length()-1);
    } 
    public String oblicz()
   {    
        return zONP(doONP(zapis));
   } 

    public  int priorytet(char c) {
        switch (c) {
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
