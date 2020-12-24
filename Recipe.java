//Recipe
//Ankita Kapoor
//10/01/20
//gets the original recipe along with the possible modifications to get a new recipe

import java.util.*;

public class Recipe
{
  private String name; //stores the name of the input ingredient
  private ArrayList <Ingredient> ogList; //stores the list of original ingredients
  private ArrayList <Ingredient> modifiedList; //stores the modified list of ingredients
  private int numUnhealthy; //stores the number of unhealthy ingredients 
  private double unhealthyCals; //stores total number of calories from unhealthy ingredients
  private double altCals; //stores total number of calories from alternative ingredients
  private double calDiff; //stores caloric difference between og and modified list
  private ArrayList <Integer> index; //stores index of the unhealthy ingredients used
  
  public Recipe(String n)
  {
    this.name = n;
    this.numUnhealthy = 0;
    this.unhealthyCals = 0;
    this.altCals = 0;
    this.calDiff = 0;
    this.index = new ArrayList <Integer>();
    this.ogList = new ArrayList <Ingredient>();
    this.modifiedList = new ArrayList <Ingredient>();
    
  } //end of constructor 
  
  
  //Accessor Methods
  
  public String getName()
  { //accesses the name of the ingredient
    return this.name;
  } //end of getName
    
  public ArrayList<Ingredient> getOgList()
  { //accesses the ogList 
    return this.ogList;
  } //end of getOgList
  
  public ArrayList<Ingredient> getModifiedList()
  { //accesses the modifiedList 
    return this.modifiedList;
  } //end of getModifiedList
  
  public int getNumUnhealthy()
  { //accesses the numUnhealthy variable
    return this.numUnhealthy;
  } //end of getNumUnhealthy
  
  
  public void setNumUnhealthy(int num)
  { //sets the value of the number of unhealthy ingredients 
    this.numUnhealthy = num;
  } //end of setNumUnhealthy
  
  public void addIngredient(String name, double measurement)
  { //enables the addition of ingredients to the list 
    Ingredient ing = new Ingredient (name, measurement);
    
    ogList.add(ing);
  } //end of addIngredient
 
  private double findUnhealthyCals()
  { //calculates total number of calories from unhealthy ingredients 
    for (int k = 0; k<index.size(); k++)
      this.unhealthyCals = this.unhealthyCals + this.ogList.get(k).getCalories();
    
    return this.unhealthyCals;
  } //end of findUnehalthyCals()
  
  private double findAltCals()
  { //calculates total number of calories from healthy alternatives
    for (int k = 0; k<index.size(); k++)
      this.altCals = this.altCals + this.modifiedList.get(k).getCalories();
    
    return this.altCals;
  } //end of findAltCals
  
  public double calDifference()
  { //calculates the difference in calories between both recipes 
    this.unhealthyCals = this.findUnhealthyCals();
    this.altCals = this.findAltCals();
    
    this.calDiff = Double.parseDouble(String.format("%.2f",this.unhealthyCals - this.altCals)); //rounds the value 
                                 
    return this.calDiff;
  } //end of calDiference
  
  public void setUnhealthyIndexes(ArrayList <Integer> unhealthyIndexes)
  { //finds the index of the unhealthy ingredients 
    this.index = unhealthyIndexes;  
  } //end of setUnhealthyIndex
  
}//end of Recipe class
  