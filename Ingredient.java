//Ingredient
//Ankita Kapoor 
//06/01/20
//Holds the ingredient name and measurement

import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.*;

public class Ingredient 
{
  private String name; //stores the input name of the ingredient
  private double measurement; //stores the measurement
  private boolean isHealthy = true; //stores a boolean value for whether or not the input ingredient is healthy
  private boolean isAlt = false; //stores a boolean value for whether the ingredient is alternative or not 
  private double calories; //stores the calories in the unhealthy ingredients
  private String description; //stores the description of the unhealthy ingredients 
  
  private String healthyName; //stores the name of the alternative ingredient
  private double healthyMeasure; //stores the measurement of the alternative
  private double healthyCalories; //stores the calories in the  healthy alternative
  private String healthyDescrip; //stores the desription of the healthy alternative

  private Alternative one;
  private Alternative two;
  private Alternative three;
  
  private String unhealthyPics [] = new String [] {"00.jpg", "01.jpg", "02.jpg", "03.jpg", "04.jpg", "05.jpg"};
  private String altPics [] = new String [] {"0.jpg", "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg",
  "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg", "16.jpg", "17.jpg"};
  private ImageIcon pics;
  private ImageIcon healthyPics;
  
  private String [] unhealthyIng = new String [] {"Sugar", "Butter", "All Purpose Flour", "Eggs", "Corn Syrup", "Cream"}; //an array that stores the unhealthy ingredients
  private String [] unhealthyIng2 = new String  [] {"Granulated Sugar", "Margarine", "Flour", "Egg", "Glucose Syrup", "Heavy Cream"}; //synonyms for the unhealhty ingredients
  private String [] unhealthyIng3 = new String  [] {"Caster Sugar", "Unsalted Butter", "AP Flour", "Egg Yolk", "High Fructose Syrup", "Whipping Cream"}; //synonyms for the unhealhty ingredients
  private String [] unhealthyIng4 = new String  [] {"White Sugar", "Salted Butter", "White Flour", "Egg Yolks", "Light Corn Syrup", "Double Cream"}; //synonyms for the unhealhty ingredients
  
  //an array that stores the descriptins of the unhealthy ingredients
  private String [] unhealthyBlurb = new String [] {"- Empty calories \n- Harms metabolism \n- Addictive \n- 104 calories / 100g \n- $0.10 / 100g", 
    "- High in Saturated Fat \n- Very high in calories \n- 717 calories / 100g \n- $1.28 / 100g", 
    "- High Glycemic Index \n- Lacks fibre and nutrients \n- 364 calories / 100g \n- $0.43 / 100g",
    "- High in Cholesterol \n- 143 calories / 100g (2 large eggs) \n- $0.88 /100g (2 large eggs)", 
    "- Very high amount of fructose \n- No nutrients whatsoever \n- 286 calories / 100g \n- $0.65 / 100g", 
    "- High amounts of Saturated Fat \n- 345 calories / 100g \n- $0.75 / 100g"}; 
  
  private double [] unhealthyCals = new double [] {104, 717, 364, 143, 286, 345}; //an array that stores the calories / 100g of the unhealthy ingredients
  
  //an array that stores the names of the alternative ingredients
  private String [] altNames = new String [] {"Date Paste", "Coconut Sugar", "Honey", 
    "Full-Fat Greek Yoghurt", "Unsweetened Applesauce", "Mashed Avocado",
    "Brown Rice Flour", "Oat Flour", "Coconut Flour", 
    "Bananas", "Unsweetened Applesauce", "Silken Tofu", 
    "Honey", "Maple Syrup", "Agave Nectar", 
    "Silken Tofu + Soy Milk", "Greek Yoghurt + Milk", "Cream Cheese"};
  
  //an array that stores the descriptions of all alternative ingredients
  private String [] altDescrip = new String [] {"- Rich in iron \n- Contains vitamins C and D \n- Contains minerals (selenium, magnesium, \n  manganese, copper) \n- Good for bone health \n- High amounts of soluble fibre \n- Low Glycemic Index \n- Can lead to a cakier product \n- 104 calories / 100g \n- $1.14 / 100g",
    "- Unrefined (contains vitamins and minerals) \n- Low Glycemic Index \n- Contains iron, zinc, calcium, potassium, \n  and antioxidants \n- 375 calories / 100g \n- $1.60 / 100g",
    "- For every 227g of honey, reduce liquids in \n  the recipe by 60mL \n- Add 1.25g of baking soda \n- Reduce the oven temperature by 25°F \n- Rich in antioxidants \n- Contains Iron and vitamin B \n- 304 calories / 100g \n- $1.10 / 100g",
    "- High protein \n- 8.75g of protein / 100g \n- 2.6g saturated fat / 100g \n- 1.3g unsaturated fat / 100g \n- Not ideal for > 250g \n- 59 calories / 100g \n- $0.93 / 100g",
    "- Best for quick breads \n- Less calories \n- Lower fat content \n- Fibre \n- Has moisture \n- Produces a slightly more dense dessert \n- 68 calories / 100g \n- $0.45 / 100g",
    "- Monounsaturated fat (healthy) \n- Higher vitamin and fibre content \n  (vitamin K, B, E, potassium) \n- 160 calories / 100g \n- $1.28 / 100g",
    "- High in protein (11g / cup) \n- Gluten Free \n- High in Fibre \n- 340 calories / 100g \n- $0.70 / 100g",
    "- High in fibre (16g / cup) \n- High in protein (26g / cup) \n- Gluten Free \n- 404 calories / 100g \n- $0.70 / 100g",
    "- High in fibre \n- Lower glycemic index \n- Contains digestible carbohydrates \n- Contains healthy fats \n- Add an egg for every 1/4 used for moisture \n- 416 calories / 100g \n- $1.65 / 100g", 
    "- High in potassium \n- High in fibre \n- Vegan \n- Contain vitamin B6, C \n- 89 calories / 100g \n- $0.20 / 100g", 
    "- Contains fibre and vitamin C \n- High in minerals and nutrients \n- Lower fat content \n- 68 calories / 100g \n- $0.45 / 100g", 
    "- Best in quick breads \n- Good binding agent (high moisture) \n- High in protein \n- Contains magnesium, copper, zinc and \n  vitamin B1 \n- Good source of calcium and iron \n- 62 calories / 100g \n- $0.50 / 100g", 
    "- Rich in antioxidants \n- Contains Iron and vitamin B \n- 304 calories / 100g \n- $1.10 / 100g", 
    "- High in antioxidants \n- Contains minerals (Riboflavin, zinc, \n  magnesium, calcium, potassium) \n- 260 calories / 100g \n- $1.49 / 100g",
    "- Low Glycemic index \n- 310 calories / 100g \n- $2.05 / 100g",
    "- Blend equal amounts together \n- Low in saturated fat and contains no \n  cholesterol \n- Good source of protein \n- Vegan \n- Low in calories \n- 58 calories / 100g \n- $0.49 / 100g", 
    "- High in protein \n- Cannot be whipped \n- 51 calories / 100g \n- $0.52 / 100g",
    "- Blend equal amounts together \n- Not ideal for whipping \n- Good source of vitamin A \n- 342 calories / 100g \n- $1.84 / 100g"};
  
  private double [] altRatio = new double [] {1, 1, 1.14, 1.25, 1.1, 1, 1, 1.15, 0.25, 1.3, 1.3, 1.2, 1, 1, 1, 1, 1, 0.94}; //an array that stores the ratio of alt : og ingredients
  private double [] altCals = new double [] {104, 375, 304, 59, 68, 160, 340, 404, 416, 89, 68, 62, 304, 260, 310, 58, 51, 342}; //an array that stores the calories / 100g of the alternative ingredients
  private int index = 0; //stores the index of the array where the specific ingredient is found
  

  public Ingredient (String name, double measurement )
  {
    this.name = name; 
    this.measurement = measurement;
    
    checkIfHealthy();
    checkIfAlt();
    
    //isolates the description and calories of unhealthy ingredients and stores the respective information
    if (!this.getIsHealthy())
    {
      this.description = this.unhealthyBlurb[index];
      this.calories = this.unhealthyCals[index];
      
      this.calories = this.calories/100 * this.measurement;
      
      this.pics =  new ImageIcon (this.unhealthyPics[index]);

      //initialises alternative ingredients 
      one = new Alternative(this. findAlt(1), this.findAltMeasure(1), this.findAltBlurbs(1), this.findAltPics(1));
      two = new Alternative(this.findAlt(2), this.findAltMeasure(2), this.findAltBlurbs(2), this.findAltPics(2));
      three = new Alternative(this.findAlt(3), this.findAltMeasure(3), this.findAltBlurbs(3), this.findAltPics(3));
      
    } //end of if 
    
    //sets the number of calories for the alternative ingredients
    else if (this.getIsAlt())
    {
      this.calories = this.altCals[index];
      
      this.calories = this.calories/100 * this.measurement;
      
      this.pics =  new ImageIcon (this.altPics[index]);
    }
    
  } //end of constructor 
  
  
  public void checkIfHealthy()
  { //checks if ingredient is healthy
    
    for (int j = 0; j<6; j++)
    {
      if (this.name.equalsIgnoreCase(this.unhealthyIng[j]) || this.name.equalsIgnoreCase(this.unhealthyIng2[j]) ||
         this.name.equalsIgnoreCase(this.unhealthyIng3[j]) || this.name.equalsIgnoreCase(this.unhealthyIng4[j])) //checks if the ingredient input is healthy and stores in isHealthy
      {
        this.isHealthy = false; 
        this.index = j;
      } //end of if 
      
    } //end of loop
    
  } //end of checkIfHealthy
  
  public void checkIfAlt()
  { //checks if ingredient is alternative
    
    for (int i = 0; i<18; i++)
    {
      if (this.name.equalsIgnoreCase(this.altNames[i])) //checks if the ingredient input is healthy and stores in isHealthy
      {
        this.index = i;
        this.isAlt = true;
      } //end of if
      
    } //end of for
    
  } //end of checkIfAlt
  
  private String findAlt(int num)
  { 
    //finds the name of the alternative ingredient
    for (int l = 0; l<(3*index+num-1)+1; l++)
    {
      this.healthyName= this.altNames[l];
    } //end of for 
    
    return this.healthyName;
    
  } //end of findAlt
  
  private String findAltBlurbs(int num)
  {
    //find the blurbs of the alternative ingredient
    for (int m = 0; m<(3*index+num-1)+1; m++)
    {
      this.healthyDescrip= this.altDescrip[m];
    } //end of for 
    
    return this.healthyDescrip;
    
  } //end of findAltBlurbs
  
  private double findAltMeasure(int num)
  { //finds the measurement of the alternative ingredient
    
    for (int n = 0; n<(3*index+num-1)+1; n++)
    {
      this.healthyMeasure= this.altRatio[n];
    } //end of for
    
    this.healthyMeasure = Double.parseDouble(String.format("%.2f", this.healthyMeasure * this.measurement));
    
    return this.healthyMeasure;
  
  } //end of findAltMeausre
  
  private ImageIcon findAltPics(int num)
  {
    for (int r = 0; r<(3*index+num-1)+1; r++)
    {
      this.healthyPics = new ImageIcon(this.altPics[r]);
    }
    return this.healthyPics;
  }
  
  //Accessor Methods
  
  public String getName()
  { //accesses the name of the ingredient
    return this.name;
  } //end of getName
  
  public double getMeasurement()
  { //accesses the measurement of the ingredient
    return this.measurement;
  } //end of getMeasurement
  
  public boolean getIsHealthy()
  { //accesses the isHealthy variable
    return this.isHealthy; 
  } //end of getIsHealthy
  
  public boolean getIsAlt()
  { //accesses the isAlt variable
    return this.isAlt; 
  } //end of getIsAlt
  
  public String getDescription()
  { //accesses the description variable
    return this.description;
  } //end of getDescription
  
  public double getCalories()
  { //accesses the calories variable
    return this.calories;
  } //end of getCalories
  
  public int index()
  { //accesses the index variable
    return this.index;
  } //end of index
  
  public Alternative getAlt1()
  { //accesses the first alternative object
    return this.one;
  } //end of getAlt1
  
  public Alternative getAlt2()
  { //accesses the second alternative object
    return this.two;
  } //end of getAlt2
  
  public Alternative getAlt3()
  { //accesses the third alternative object
    return this.three;
  } //end of getAlt3
  
  public ImageIcon getPics()
  { //accesses the array with the file names for ing pics
    return this.pics;
  } //end of getPics
  
  
} //end of Ingredient class
  