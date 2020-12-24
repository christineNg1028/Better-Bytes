//ButtonController
//Christine Ng
//Last Modified: Jan 17 2020
//Detects a button press and calls associated Model method

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ButtonController extends Object implements ActionListener
{
  //Attributes
  private ByteModel model; //The Model
  private JTextField ingredientName, measurement, recipeName; //JComponents to receive data from
  private ButtonGroup selection;
  private JLabel recipeKey;
  private JTextArea recipeValue;
  private JLabel message;
  private JFrame messageF;
  
       
  //Constructor - initializes attributes
  public ButtonController(ByteModel model, JTextField ingName, JTextField ingMeasurement, JTextField recName, ButtonGroup ingSelection, JLabel key, JTextArea value,  JLabel message, JFrame messageF)
  {
    this.model = model;
    this.ingredientName = ingName;
    this.measurement = ingMeasurement;
    this.recipeName = recName;
    this.selection = ingSelection;
    this.recipeKey = key;
    this.recipeValue = value;
    this.message = message;
    this.messageF = messageF;
  }
  
  //actionPerformed - calls Model methods
  public void actionPerformed(ActionEvent e)
  {
    String classifyButton = e.getActionCommand(); //Determine which button was clicked
    
    //openingScreen New Recipe button
    if (classifyButton.equals("New Recipe"))
    {
      this.model.newRecipe();
    }
    //openingScreen Recipe Book button
    else if (classifyButton.equals("Recipe Book"))
    {
      this.model.recipeBook();
    }
    //newRecipeScreen Save Name button
    else if (classifyButton.equals("Save Name")) 
    {
      String rec = this.recipeName.getText(); //Receive input from JTextField
      
      if (rec.equals("")){
        this.message.setText("Please enter a name for this recipe."); //If user didn't input a name
        this.messageF.setVisible(true);
      }
      else{
        this.model.startRecipe(this.capitalizeFirstLetters(rec));
      }
    }
    //newRecipeScreen Add Ingredient button
    else if (classifyButton.equals("Add Ingredient")) 
    {
      String ing = this.ingredientName.getText(); //Receive input from JTextFields
      String ingMeasure = this.measurement.getText();
      ingMeasure = ingMeasure.trim(); //Ensure no extra whitespace added
        
      if (ing.equals("") && !ingMeasure.equals("")){
        this.message.setText("Enter an ingredient name.");  //If user only entered a measurement
        this.messageF.setVisible(true); 
      }
      else if (!ing.equals("") && ingMeasure.equals("")){
        this.message.setText("Enter a measurement."); //If user only entered a name
        this.messageF.setVisible(true); 
      }
      else if (ing.equals("") && ingMeasure.equals("")){
        this.message.setText("Enter an ingredient name and a measurement."); //If user didn't input anything
        this.messageF.setVisible(true);
      }
      else{        
        try{
          double measure = Double.parseDouble(ingMeasure);
          this.measurement.setText(""); //Clear JTextFields
          
          if (measure <= 0){
            this.message.setText("Not a valid measurement. Must be greater than 0."); //Ensure measurement is > 0 
            this.messageF.setVisible(true);
          }
          else{
            this.ingredientName.setText(""); 
            this.model.addIngToRecipe(this.capitalizeFirstLetters(ing), measure);
          }
        }
        catch (NumberFormatException ex){
          this.measurement.setText("");
          this.message.setText("Not a valid measurement. Must be a number."); //If user didn't enter a number
          this.messageF.setVisible(true);
        }
      }
    }
    //newRecipeScreen Done button
    else if (classifyButton.equals("Done")) 
    {
      this.model.doneRecipe();
    }
    //newRecipeScreen displaySelectionPanel Select button
    else if (classifyButton.equals("Select")) 
    {
      //Determine selected JRadioButton, extract name and measurement of chosen alt
      String alt = this.selection.getSelection().getActionCommand();
      double altMeasure = Double.parseDouble(alt.substring(0, alt.indexOf("g")));
      String altName = alt.substring(alt.indexOf(" ")+1);
      this.model.modifyRecipe(altName, altMeasure);      
    }
    //comparisonScreen Save to Recipe Book button
    else if (classifyButton.equals("Save to Recipe Book")) 
    {
      this.model.saveRecipe();
    }
    //displayRecipeBook any recipe button
    else if (this.model.getRecipeBook().containsKey(classifyButton))
    {
      this.model.theRecipe(classifyButton);
    }
    //displayRecipeBook OR comparisonScreen Back button
    else if (classifyButton.equals("Back to Opening"))
    {
      this.model.backToOpening();
    }
    //displayRecipeBook Clear All Recipes button
    else if (classifyButton.equals("Clear All Recipes"))
    {
      this.model.clearRecipes();
      this.model.recipeBook();
    }
    //displayRecipe Save to File button
    else if (classifyButton.equals("Save to File"))
    {
      try{
      this.model.saveToFile(this.recipeKey.getText(), this.recipeValue.getText());
      }
      catch (IOException ex){
      }
    }
    //displayRecipe Remove from Recipe Book button
    else if (classifyButton.equals("Remove from Recipe Book"))
    {
      this.model.removeRecipe(this.recipeKey.getText());
      this.model.recipeBook();
    }
    //displayRecipe Back button
    else if (classifyButton.equals("Back to Recipes"))
    {
      this.model.recipeBook();
    }
    //newRecipeScreen any ing button
    else
    {
      this.model.ingSelection(Integer.parseInt(classifyButton)); //actionCommand is an index
    }
  }
  
  //capitalizeFirstLetters - takes user input and capitalizes the first letter of every token
  private String capitalizeFirstLetters(String input)
  {
    char firstChar; 
    String token; 
    String name = ""; //Recipe name or ingredient name
    input = input.toLowerCase();
    Scanner sc = new Scanner(input);
    
    while (sc.hasNext()) 
    {
      token = sc.next();
      firstChar = token.charAt(0);
      name = name + Character.toUpperCase(firstChar) + token.substring(1) + " ";
    }
    name = name.trim();
    
    return name;
  }
}