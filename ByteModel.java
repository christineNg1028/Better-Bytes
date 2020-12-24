//ByteModel
//Christine Ng & Veronica Starr
//Last Modified: Jan 17 2020
//Contains data regarding recipes and ingredients, which is manipulated in response to user interraction

import java.util.*;
import java.io.*;

public class ByteModel extends Object
{
  //Attributes - Christine
  ByteView byteView;
  TreeMap<String, ArrayList> recipeBook; //Key: recipe names, Value: modified lists
  Recipe aRecipe; //Current recipe
  Ingredient anIng; //Recently added ing OR target ing to be changed
  
  //Constructor - Christine
  public ByteModel()
  {
    super();
    this.recipeBook = new TreeMap<String, ArrayList>();
  }
  
  //setGUI method - Christine
  public void setGUI(ByteView currentGUI)
  {
    this.byteView = currentGUI;
  }
  
  //startRecipe method - Christine
  public void startRecipe(String recipeName)
  {
    this.aRecipe = new Recipe(recipeName); 
    this.byteView.enableAndDisable(true); //Enable ing entry components, disable name entry components
  }
  
  //addIngToRecipe method - Christine
  public void addIngToRecipe(String ingName, double ingMeasurement)
  {
    this.anIng = new Ingredient(ingName, ingMeasurement);
    this.aRecipe.getOgList().add(this.anIng);
    this.byteView.updateNewRecipeScreen(); //Added ing appears as button on screen
  }
  
  //doneRecipe method - Christine
  public void doneRecipe()
  {
    this.byteView.enableAndDisable(false); //Disable ing entry components
    
    ArrayList<Integer> unhealthyIndexes = new ArrayList<Integer>(); //Holds indexes of all unhealthy ings in ogList
    ArrayList<Ingredient> ingList = this.aRecipe.getOgList();
    
    //Loop through aRecipe’s ogList to determine unhealthy
    for (int idx = 0; idx < ingList.size(); idx++)
    {
      if (!ingList.get(idx).getIsHealthy())
      {
        unhealthyIndexes.add(Integer.valueOf(idx)); //Add index of current ing
        this.aRecipe.setNumUnhealthy(this.aRecipe.getNumUnhealthy()+1); //Increment numUnhealthy by 1
      }
    }
    this.aRecipe.setUnhealthyIndexes(unhealthyIndexes); //Set aRecipe's instance variable index in order to later determine caloric difference
    this.byteView.enableIngButtons(unhealthyIndexes);
    
    //Copy ings from ogList to modifiedList
    for (int idx = 0; idx < ingList.size(); idx++)
    {
      this.aRecipe.getModifiedList().add(ingList.get(idx));
    }
    
    //If none of the ings entered are unhealthy, call comparison screen
    if (this.aRecipe.getNumUnhealthy() == 0)
    {
      this.byteView.comparisonScreen();
    }
  }
  
  //ingSelection method - Christine
  public void ingSelection(int buttonIdx)
  {
    ArrayList<Ingredient> ingList = this.aRecipe.getOgList();
    this.anIng = ingList.get(buttonIdx); //anIng set to unhealthy target in ogList
    this.byteView.displaySelectionPanel(this.anIng); //Display all alts for this ing
  }
  
  //modifyRecipe method - Christine
  public void modifyRecipe(String altName, double altMeasurement)
  {
    Ingredient chosenAlt = new Ingredient(altName, altMeasurement); //Construct new ing using info from selection
    ArrayList<Ingredient> newIngList = this.aRecipe.getModifiedList();
    
    int altIdx = newIngList.indexOf(this.anIng); //Index of anIng (unhealthy target) in modifiedList
    newIngList.set(altIdx, chosenAlt); //Replace unhealthy with healthy chosenAlt in modifiedList
    this.aRecipe.setNumUnhealthy(this.aRecipe.getNumUnhealthy()-1); //Decrement aRecipe's numUnhealthy by 1
    
    this.byteView.replaceWithAlt(chosenAlt, altIdx); //Update screen after selection
    
    //If no more remaining unhealthy ings, call comparison screen
    if (this.aRecipe.getNumUnhealthy() == 0)
    {
      this.byteView.comparisonScreen();
    }
  }
  
  //saveRecipe method - Christine
  public void saveRecipe()
  {
    this.recipeBook.put(this.aRecipe.getName(), this.aRecipe.getModifiedList()); //Add aRecipe key and value to recipeBook
    this.aRecipe = null; //Reset instance variables
    this.anIng = null;
    this.byteView.openingScreen();
  }
  
  //saveToFile method - Christine
  public void saveToFile(String n, String r) throws IOException
  {
    this.byteView.saveToFile(n, r);    
  }
  
  //newRecipe method - Veronica
  public void newRecipe()
  {
    this.byteView.newRecipeScreen();
  }
  
  //getAnIng accessor method - Veronica
  public Ingredient getAnIng()
  {
    return this.anIng;
  }
  
  //getARecipe accessor method - Veronica
  public Recipe getARecipe()
  {
    return this.aRecipe;
  }
  
  //getRecipeBook accessor method - Veronica
  public TreeMap<String, ArrayList> getRecipeBook()
  {
    return this.recipeBook;
  }
  
  //recipeBook method - Veronica
  public void recipeBook()
  {
    this.byteView.displayRecipeBook();
  }
  
  //clearRecipes method - Christine
  public void clearRecipes()
  {
    this.recipeBook.clear();
  }
  
  //theRecipe method - Veronica
  public void theRecipe (String recipeKey)
  {
    this.byteView.displayRecipe(recipeKey, this.recipeBook.get(recipeKey));
  }
  
  //removeRecipe method - Veronica
  public void removeRecipe(String n)
  {
    this.recipeBook.remove(n);
  }
  
  //backToOpening method - Veronica
  public void backToOpening()
  {
    this.byteView.openingScreen();
  }
}