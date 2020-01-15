
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
    private String Equation;
    private String result;
    public Calculator(String leftOperand)
    {
    this.Equation = leftOperand;
    ONP ONP = new ONP();
    }
    private String Calc(ArrayList<String> onp)
    {
        Integer num1,num2;
        Stack<Integer> stos=new Stack<>();
        for(int i=0;i<onp.size();i++)
        {
            if(Character.isDigit((onp.get(i)).charAt(0)))
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
                    {
                        num1=stos.pop();
                        if(num1<0)return "nie mozna pierwiastkowac liczb ujemnych";
                        stos.add((int)klasa.sqrt(num1));
                    break;
                    }
                }
            }
        }
        result=stos.toString();
        return result.substring(1, result.length()-1);
    } 
    public String GetResult()
   {    
        return Calc(ONP.toONP(Equation));
   } 
}
