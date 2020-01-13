/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Calc.Calculator;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import static java.util.logging.Level.INFO;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
 
 


public class loaderClass {
    private static final loaderClass INSTANCE = new loaderClass();
    private  URL[] classLoaderUrls;
    private Class beanClass;
    private Constructor constructor;
    private Object beanObj;
    private Method method;
    private Method method2;
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh; 
    private loaderClass() {
        try {  

        // This block configure the logger with handler and formatter  
        fh = new FileHandler("MyLogFile.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);
        logger.setUseParentHandlers(false);

        // the following statement is used to log any messages  
        logger.log(Level.INFO, "Test numer jeden");  

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    logger.info("loger sie odzywa");  


       
        try {        
          classLoaderUrls = new URL[]{new URL("file:///C:/Users/Wojtass/Documents/NetBeansProjects/Calculator/src/main/java/com/Calc/plugin/potega.jar")};
        } catch (MalformedURLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);       
        try {
            beanClass = urlClassLoader.loadClass("com.calculator.potega.potega");
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        try {
            this.constructor = beanClass.getConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        try {
            beanObj = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        try {
            method = beanClass.getMethod("pow",double.class,double.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        try {
            method2 = beanClass.getMethod("sqrt",double.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public static loaderClass getInstance() {
        return INSTANCE;
    }
     public double potega(double a,double b){
        Field abc;
        double wynik=0;
         try {
            
             method.invoke(beanObj,a,b);         
             abc = beanClass.getField("wynik");
             wynik= abc.getDouble(beanObj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
return wynik;
    }
          public double sqrt(double a){
        Field abc;
        double wynik=0;
         try {
            
             method2.invoke(beanObj,a);         
             abc = beanClass.getField("wynik");
             wynik= abc.getDouble(beanObj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
return wynik;
    }
}
