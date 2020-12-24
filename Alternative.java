//Alternative
//Ankita Kapoor
//07/01/20
//Holds the alternative ingreient name and measurement

import java.util.*;
import java.io.*;
import javax.swing.*;

public class Alternative 
{
  private String healthyName; //stores the input name of the ingredient
  private double healthyMeasure; //stores the measurement
  private String healthyDescrip; //stores the description of the unhealthy ingredients 
  private ImageIcon healthyPics; //stores the pictures of the alternative ingredients
  
  public Alternative (String healthyName, double healthyMeasure, String healthyDescrip, ImageIcon healthyPics)
  {
    this.healthyName = healthyName;
    this.healthyMeasure = healthyMeasure;
    this.healthyDescrip = healthyDescrip;   
    this.healthyPics = healthyPics;
  } //end of constructor
   
  
  //Accessor Methods
  public String getHealthyName()
  { //accesses the name of the alternative ingredient
    return this.healthyName;
  } //end of getHealthyName
  
  public double getHealthyMeasure()
  { //accesses the measurement of the alternative ingredient
    return this.healthyMeasure;
  } //end of getHealthyMeasure
  
  public String getHealthyDescrip()
  {  //accesses the description of the alternative ingredient
    return this.healthyDescrip;
  } //end of getHealthyDescrip
  
  public ImageIcon getHealthyPics()
  {  //accesses the pictures of the alternative ingredient
    return this.healthyPics;
  } //end of getHealthyPics
  
  
} //end of Alternatives class
