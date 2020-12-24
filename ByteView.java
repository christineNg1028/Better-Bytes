//ByteView
//Veronica Starr & Christine Ng
//January 6, 2020
//View for the better bytes program

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

//start of class
public class ByteView extends JPanel
{
  //instance variables - Veronica
  private ByteModel model;
  
  //opening screen JComponents - Veronica 
  private JLabel logo = new JLabel (new ImageIcon("logo.jpg"));
  private JButton newRecipe = new JButton ("New Recipe");
  private JButton recipeBook = new JButton ("Recipe Book");
  
  //message screen JComponents - Ankita
  private JPanel messagePane = new JPanel();
  private JLabel message = new JLabel();
  private JFrame messageF = new JFrame ("Message");
  
  //new recipe screen JComponents - Veronica
  private JLabel recipeNameLabel = new JLabel("Recipe Name    ");
  private JTextField recipeName  = new JTextField (25);
  private JButton saveName = new JButton ("  Save Name   ");
  private JLabel ingNameLabel = new JLabel("Ingredient Name");
  private JTextField ingName = new JTextField (25);
  private JButton add = new JButton ("Add Ingredient");
  private JLabel ingMeasurementLabel = new JLabel("Measurement (g)");
  private JTextField ingMeasurement = new JTextField (25);
  private JButton done = new JButton ("Done");
  private JPanel ingList = new JPanel();
  private JPanel altList = new JPanel();
  private ArrayList <JButton> ingButtons = new ArrayList <JButton>();
  private JButton select = new JButton ("Select");
  private ButtonGroup ingSelection = new ButtonGroup();
  private JLabel pics;
  private JLabel altPics1;
  private JLabel altPics2;
  private JLabel altPics3;
  
  //comparison screen JComponents - Veronica 
  private JLabel recipeTitle = new JLabel();
  private JTextArea ogRecipe = new JTextArea();
  private JTextArea modifiedRecipe = new JTextArea();
  private JLabel calorieDifference = new JLabel();
  private JButton saveRecipe = new JButton ("Save to Recipe Book");
  
  //display recipe book JComponents - Veronica
  private ArrayList <JButton> recipeButtons = new ArrayList<JButton>();
  private JLabel tableOfContents = new JLabel ("Table of Contents");
  private JButton back = new JButton ("Back"); //also in display recipe screen
  private JButton clear = new JButton ("Clear All Recipes");
  
  //display recipe JComponents - Veronica
  private JTextArea value = new JTextArea();
  private JLabel key = new JLabel();
  private JButton saveToFile = new JButton ("Save to File");
  private JButton remove = new JButton ("Remove from Recipe Book");
  
  //file chooser instance - Veronica
  private JFileChooser dialog = new JFileChooser();
  
  //controller instance - Veronica
  private ButtonController controller;
  
  //start of the constructor - Veronica
  public ByteView (ByteModel newModel)
  {
    super();
    this.model = newModel;
    this.messageScreen();
    this.controller = new ButtonController(this.model, this.ingName, this.ingMeasurement, this.recipeName, this.ingSelection, this.key, this.value, this.message, this.messageF);
    this.model.setGUI(this);
    this.registerControllers();
    this.openingScreen();
  }
  
  //messageScreen method - Ankita
  private void messageScreen()
  {
    //sets attributes for a JFrame that is used to output messages
    this.messageF.setSize(350, 100);
    this.messageF.setLocation(450, 375);
    this.messageF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.messageF.setContentPane(messagePane);
    this.messageF.setVisible(false);
    
    messagePane.add(message);//adds the message JLabel
  }//end of message screen
  
  //registerControllers method - Christine
  private void registerControllers()
  {
    //Set controller as ActionListener for all JButtons & set ActionCommands to distinguish between buttons
    this.newRecipe.addActionListener(this.controller);
    this.newRecipe.setActionCommand("New Recipe");
    this.saveName.addActionListener(this.controller);
    this.saveName.setActionCommand("Save Name");
    this.add.addActionListener(this.controller);
    this.add.setActionCommand("Add Ingredient");
    this.done.addActionListener(this.controller);
    this.done.setActionCommand("Done");
    this.select.addActionListener(this.controller);
    this.select.setActionCommand("Select");
    this.saveRecipe.addActionListener(this.controller);
    this.saveRecipe.setActionCommand("Save to Recipe Book");
    this.recipeBook.addActionListener(this.controller);
    this.recipeBook.setActionCommand("Recipe Book");
    this.saveToFile.addActionListener(this.controller);
    this.clear.addActionListener(this.controller);
    this.clear.setActionCommand("Clear All Recipes");
    this.saveToFile.setActionCommand("Save to File");
    this.remove.addActionListener(this.controller);
    this.remove.setActionCommand("Remove from Recipe Book");
    this.back.addActionListener(this.controller);
  }
  
  //updateNewRecipeScreen method - Christine
  public void updateNewRecipeScreen()
  {
    //Everytime an ing is added, create a disabled JButton with the inputted name and measurement
    JButton anIngButton = new JButton(this.model.getAnIng().getMeasurement()+"g "+this.model.getAnIng().getName());
    anIngButton.setEnabled(false);
    anIngButton.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    anIngButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, anIngButton.getMinimumSize().height));
    this.ingButtons.add(anIngButton); //Add button to ArrayList ingButtons
    this.ingList.add(anIngButton); //Display button at end of JPanel ingList
    this.updateUI();
  }
  
  //enableAndDisable method - Christine
  public void enableAndDisable(boolean b)
  {
    //"Save Name" button pressed - cannot change name, proceed to entering ings
    if (b)
    {
      this.recipeName.setEnabled(false);
      this.saveName.setEnabled(false);
      this.ingName.setEnabled(true);
      this.ingMeasurement.setEnabled(true);
      this.add.setEnabled(true);
      this.done.setEnabled(true);
    }
    //"Done" button pressed - clear text fields and cannot add any more ings
    else
    {
      this.ingName.setText("");
      this.ingName.setEnabled(false);
      this.ingMeasurement.setText("");
      this.ingMeasurement.setEnabled(false);
      this.add.setEnabled(false);
      this.done.setEnabled(false);
    }
  }
  
  //enableIngButtons method - Christine
  public void enableIngButtons(ArrayList<Integer> unhealthyIndexes)
  {
    JButton aButton;
    int idx;
    
    //Loop through unhealthyIndexes and disable buttons corresponding to unhealthy ings
    for (int ing = 0; ing < unhealthyIndexes.size(); ing++)
    {
      idx = unhealthyIndexes.get(ing).intValue(); //Retrieve int representing idx of an unhealthy ing in ingButtons
      aButton = this.ingButtons.get(idx);
      
      aButton.setEnabled(true);
      aButton.setBackground(new Color(250, 250, 250));
      aButton.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
      aButton.setForeground(Color.red);
      aButton.addActionListener(this.controller);
      aButton.setActionCommand(Integer.toString(idx)); //Set actionCommand to index of button in ingButtons
    }
  }
  
  //replaceWithAlt method - Christine
  public void replaceWithAlt(Ingredient alt, int idx)
  {
    this.ingButtons.get(idx).setText(alt.getMeasurement() + "g " + alt.getName()); //Set text of targeted button to alt info
    this.ingButtons.get(idx).setEnabled(false); //Disable button after replacing unhealthy with alt
    this.altList.removeAll(); //Clear altList panel of all alternatives for unhealthy alt that was replaced
    this.altList.repaint();
    this.updateUI();
  }
  
  //saveToFile method - Christine
  public void saveToFile(String n, String r) throws IOException
  {
    //Declarations
    File aFile;
    int fileSelected;
    PrintWriter out;
    FileWriter fileWriter;
    
    fileSelected = dialog.showSaveDialog(null);
    
    if (fileSelected == JFileChooser.APPROVE_OPTION)
    {
      aFile = dialog.getSelectedFile(); //create File object with selected file 
      
      try
      {
        /*Use aFile pathname to instantiate a FileWriter (allows for appending the new recipe to existing recipes in the
        selected file), which is passed to PrintWriter*/
        fileWriter = new FileWriter(aFile.getName(), true);
        out = new PrintWriter(fileWriter);
        
        //Output recipe to file
        out.println("----------------------------------------------------------------------------------------------------");
        out.println(n + "\n");
        out.println(r);
        
        message.setText("Recipe successfully saved.");
        messageF.setVisible(true);
        out.close();
      }
      catch (FileNotFoundException e)
      {
        message.setText(e.getMessage());
        messageF.setVisible(true);
        System.exit(1);
      }
    }
    else if (fileSelected == JFileChooser.ERROR_OPTION)
    {
      message.setText("An error occurred.");
      messageF.setVisible(true);
    }
  }
  
  //opening screen - Veronica
  public void openingScreen()
  {
    //clear previous panel
    this.removeAll();
    this.repaint();
    
    //panel for the opening screen
    JPanel opening = new JPanel();
    opening.setLayout(new BorderLayout());
    opening.setBackground(new Color (237, 157, 206));//pink
    opening.setPreferredSize(new Dimension (1000,750));
    
    //panel for the buttons
    JPanel openingButtons = new JPanel();
    newRecipe.setBackground(new Color (250, 250, 250));
    recipeBook.setBackground(new Color (250, 250, 250));
    newRecipe.setPreferredSize(new Dimension (450,50));
    recipeBook.setPreferredSize(new Dimension (450, 50));
    
    //format opening screen components
    newRecipe.setFont(new Font ("MonoSpaced", Font.BOLD, 30));
    recipeBook.setFont(new Font ("MonoSpaced", Font.BOLD, 30));
    
    //add components to the opening buttons panel
    openingButtons.add(newRecipe);
    openingButtons.add(recipeBook);
    openingButtons.setPreferredSize(new Dimension (1000,250));
    openingButtons.setBackground(new Color (237, 157, 206));
    
    //adding the components to the panel
    opening.add(logo, BorderLayout.CENTER);
    opening.add(openingButtons, BorderLayout.SOUTH);
    this.add(opening);
    
    this.updateUI();
  }//end of the opening screen
  
  //new recipe screen - Veronica
  public void newRecipeScreen()
  {
    /*clear previous panel contents, clear ingList and recipeName, re-enable recipeName and saveName, 
     *clear ingButtons ArrayList (from previous recipe)*/
    this.removeAll();
    this.repaint();
    this.ingList.removeAll();
    this.recipeName.setText("");
    this.recipeName.setEnabled(true);
    this.saveName.setEnabled(true);
    this.ingButtons.clear();
    this.setBackground(new Color (237, 157, 206));
    
    //formatting for the new recipe screen jcomponents
    recipeNameLabel.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    saveName.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    ingNameLabel.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    ingMeasurementLabel.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    add.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    done.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    
    //panel for  new recipe screen
    JPanel base = new JPanel();
    base.setLayout(new BorderLayout());
    base.setBackground(new Color (237, 157, 206));
    
    //panel for the input components
    JPanel combine = new JPanel();
    combine.setLayout(new BorderLayout());
    combine.setBackground(new Color (237, 157, 206));
    
    //panel for the recipe name input components
    JPanel first = new JPanel();
    first.setLayout(new BoxLayout(first, BoxLayout.X_AXIS));
    first.add(this.recipeNameLabel);
    first.add(this.recipeName);
    recipeName.setBackground(new Color (250, 250, 250));
    first.add(this.saveName);
    saveName.setBackground(new Color (250, 230, 230));
    first.setBackground(new Color (237, 157, 206));
    
    //ing name panel
    JPanel ingNamePanel = new JPanel();
    ingNamePanel.setLayout(new BoxLayout(ingNamePanel, BoxLayout.X_AXIS));
    ingNamePanel.add(this.ingNameLabel);
    ingNamePanel.add(this.ingName);
    ingName.setBackground(new Color (250, 250, 250));
    ingNamePanel.setBackground(new Color (237, 157, 206));
    
    //ing measurement panel
    JPanel ingMeasurementPanel = new JPanel();
    ingMeasurementPanel.setLayout(new BoxLayout(ingMeasurementPanel, BoxLayout.X_AXIS));
    ingMeasurementPanel.add(this.ingMeasurementLabel);
    ingMeasurementPanel.add(this.ingMeasurement);
    ingMeasurement.setBackground(new Color (250, 250, 250));
    ingMeasurementPanel.setBackground(new Color (237, 157, 206));
    
    //add components to input panel and set not recipe name components to disabled
    combine.add(first, BorderLayout.NORTH);
    first.setEnabled(true);
    combine.add(ingNamePanel, BorderLayout.WEST);
    this.ingName.setEnabled(false);
    combine.add(ingMeasurementPanel, BorderLayout.CENTER);
    this.ingMeasurement.setEnabled(false);
    combine.add(this.add, BorderLayout.EAST);
    add.setBackground(new Color(250, 230, 230));
    this.add.setEnabled(false);
    combine.add(this.done, BorderLayout.SOUTH);
    done.setBackground(new Color(250, 230, 230));
    this.done.setEnabled(false);
    
    //add components to overall panel
    base.add(combine, BorderLayout.NORTH);
    
    //format ing list and add to base panel
    ingList.setLayout(new BoxLayout(ingList, BoxLayout.Y_AXIS));
    ingList.setBorder(BorderFactory.createTitledBorder("Ingredient List"));
    ingList.setPreferredSize(new Dimension(400,600));
    ingList.setBackground(new Color(245, 236, 164));
    base.add(ingList, BorderLayout.WEST);
    
    //format alt list and add to base panel
    altList.setLayout(new BoxLayout(this.altList, BoxLayout.Y_AXIS));
    altList.setBorder(BorderFactory.createTitledBorder("Alternatives List"));
    altList.setPreferredSize(new Dimension(400,600));
    altList.setBackground(new Color(245, 236, 164));
    base.add(altList, BorderLayout.EAST);
    this.add(base);
    
    this.updateUI();    
  }//end of the new recipe screen
  
  //comparison screen - Veronica
  public void comparisonScreen()
  {
    //set the values of the original recipe from the recipe class
    ArrayList <Ingredient> og = this.model.getARecipe().getOgList();
    
    //set the values of the modified recipe from the recipe class
    ArrayList <Ingredient> modified = this.model.getARecipe().getModifiedList();
    
    //clear previous panel contents, clear ogRecipe and modifiedRecipe JTextAreas (from previous Recipe)
    this.removeAll();
    this.repaint();
    this.ogRecipe.setText("");
    this.modifiedRecipe.setText("");
    
    //panel for the comparison screen
    JPanel comp = new JPanel();
    comp.setLayout(new BorderLayout());
    
    //panel for the calories and save to file button
    JPanel rec = new JPanel();
    this.recipeTitle.setText(this.model.getARecipe().getName());//set text to recipe title
    calorieDifference.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    saveRecipe.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    rec.setBackground(new Color(237, 157, 206));
    saveRecipe.setBackground(new Color(250, 250, 250));
    this.back.setActionCommand("Back to Opening");
    back.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    back.setBackground(new Color(250, 250, 250));
    rec.add(this.back);
    rec.add(calorieDifference);
    rec.add(saveRecipe);
    
    //set the text of the original recipe to the text area
    String o;
    this.ogRecipe.setEditable(false);
    ogRecipe.setPreferredSize(new Dimension (400, 600));
    ogRecipe.setBorder(BorderFactory.createTitledBorder("Original Recipe"));
    ogRecipe.setBackground(new Color(245, 236, 164));
    ogRecipe.setFont(new Font ("MonoSpaced", Font.BOLD, 15));
    for(int i = 0; i < og.size(); i++)
    {
      o = og.get(i).getMeasurement() + "g " + og.get(i).getName();
      this.ogRecipe.setText(this.ogRecipe.getText() + o + "\n");
    }
    //set the text of the modified recipe to the text area
    String mo;
    this.modifiedRecipe.setEditable(false);
    modifiedRecipe.setPreferredSize(new Dimension (400, 600));
    modifiedRecipe.setBorder(BorderFactory.createTitledBorder("Modified Recipe"));
    modifiedRecipe.setBackground(new Color(245, 236, 164));
    modifiedRecipe.setFont(new Font ("MonoSpaced", Font.BOLD, 15));
    for(int i = 0; i < modified.size(); i++)
    {
      mo = modified.get(i).getMeasurement() + "g " + modified.get(i).getName();
      this.modifiedRecipe.setText(this.modifiedRecipe.getText() + mo + "\n");
    }
    
    //set calorie difference text
    if (this.model.getARecipe().calDifference() > 0)
      calorieDifference.setText("There are " + this.model.getARecipe().calDifference() + " less calories in the modified recipe.");
    else if (this.model.getARecipe().calDifference() < 0)
      calorieDifference.setText("There are " + -this.model.getARecipe().calDifference() + " less calories in the original recipe.");
    else
      calorieDifference.setText("There are the same number of calories in both recipes.");
    
    JPanel recipeTitle1 = new JPanel();
    recipeTitle1.add(recipeTitle);
    recipeTitle.setFont(new Font ("MonoSpaced", Font.BOLD, 30));
    recipeTitle1.setBackground(new Color(237, 157, 206));
    //add components to main panel
    comp.add(recipeTitle1, BorderLayout.NORTH);
    comp.add(ogRecipe, BorderLayout.WEST);
    comp.add(modifiedRecipe, BorderLayout.EAST);
    comp.add(rec, BorderLayout.SOUTH);
    this.add(comp);
    
    this.updateUI();
  }//end of comparison screen
  
  //display recipe book screen - Veronica
  public void displayRecipeBook()
  {
    //instantiate tree map for recipes
    TreeMap <String, ArrayList> recipes = this.model.getRecipeBook();
    
    //clear the previous panel
    this.removeAll();
    this.repaint();
    
    //panel for the table of contents
    JPanel tableCont = new JPanel();
    tableCont.setLayout(new BorderLayout());
    tableCont.setBackground(new Color(237, 157, 206));
    
    //panel for table of contents label
    JPanel contents = new JPanel();
    contents.add(tableOfContents);
    tableOfContents.setFont(new Font ("MonoSpaced", Font.BOLD, 30));
    contents.setBackground(new Color(237, 157, 206));
    
    //panel for the back button
    JPanel labels = new JPanel();
    labels.add(back);
    labels.add(clear);
    clear.setBackground(new Color(250, 250, 250));
    clear.setFont(new Font ("MonoSpaced", Font.BOLD, 20));
    back.setBackground(new Color(250, 250, 250));
    back.setFont(new Font ("MonoSpaced", Font.BOLD, 20));
    labels.setBackground(new Color(237, 157, 206));
    this.back.setActionCommand("Back to Opening");
    
    //panel for the buttons for the user's recipes
    JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
    buttons.setBackground(new Color(237, 157, 206));
    
    //clear elements in recipe buttons to avoid repeat buttons
    this.recipeButtons.clear();
    
    //add the recipes from the tree map to the panel
    for (String keys: recipes.keySet())
    {
      recipeButtons.add(new JButton(keys));
    }
    
    for (int i = 0; i < recipeButtons.size(); i++)
    {
      recipeButtons.get(i).setBackground(new Color(250, 250, 250));
      recipeButtons.get(i).setMaximumSize(new Dimension(Integer.MAX_VALUE, recipeButtons.get(i).getMinimumSize().height));
      recipeButtons.get(i).setFont(new Font ("MonoSpaced", Font.BOLD, 20));
    }
    
    //set action listener for the recipe buttons
    for( int i = 0; i < recipeButtons.size(); i++)
    {
      JButton temp = recipeButtons.get(i);
      temp.setActionCommand(temp.getText());
      temp.addActionListener(this.controller);
    }
    
    //add the buttons to the panel
    for(int i = 0; i < recipeButtons.size(); i++)
    {
      buttons.add(recipeButtons.get(i));
    }
    
    //panel to center the buttons
    JPanel buttons1 = new JPanel();
    buttons1.add(buttons);
    buttons1.setBackground(new Color(237, 157, 206));
    
    //add components to the main panel
    tableCont.add(contents, BorderLayout.NORTH);
    tableCont.add(buttons1, BorderLayout.CENTER);
    tableCont.add(labels, BorderLayout.SOUTH);
    tableCont.setPreferredSize(new Dimension(1000, 650));
    this.add(tableCont);
    this.setBackground(new Color(237, 157, 206));
    
    this.updateUI();
  }//end of the recipe book screen
  
  //display recipe screen - Veronica
  public void displayRecipe(String recipe, ArrayList <Ingredient> ings)
  {
    //clear the previous panel, clear value JTextArea (from previous Recipe viewed)
    this.removeAll();
    this.repaint();
    this.value.setText("");
    this.value.setEditable(false);
    
    //panel for the recipe
    JPanel show = new JPanel();
    show.setLayout(new BorderLayout());
    show.setBackground(new Color(237, 157, 206));
    
    //panel for the title
    JPanel recipeKey = new JPanel();
    recipeKey.setBackground(new Color(237, 157, 206));
    
    //set title of recipe
    key.setText(recipe);
    key.setFont(new Font ("MonoSpaced", Font.BOLD, 30));
    JPanel key1 = new JPanel();
    key1.setBackground(new Color(237, 157, 206));
    key1.add(key);
    
    //add back button to panel of buttons
    recipeKey.add(back);
    this.back.setActionCommand("Back to Recipes");
    
    //modify the choices
    saveToFile.setFont(new Font ("MonoSpaced", Font.BOLD, 20));
    saveToFile.setBackground(new Color(250, 250, 250));
    remove.setFont(new Font ("MonoSpaced", Font.BOLD, 20));
    remove.setBackground(new Color(250, 250, 250));
    
    //panel for all buttons
    JPanel allButtons = new JPanel();
    allButtons.setBackground(new Color(237, 157, 206));
    
    //add buttons to panel for buttons
    allButtons.add(recipeKey);
    allButtons.add(saveToFile);
    allButtons.add(remove);
    
    //set recipe text to the value text area
    //temp variables to hold the names and measurements
    String n;
    String t;
    Double m;
    
    //loop to set the value text area
    for (int i = 0; i < ings.size(); i++)
    {
       //get the measurement value for the ingredient
       m = ings.get(i).getMeasurement();
       
       // get the name of the ingredient
       n = ings.get(i).getName();
       
       //put the measurement and name together in one line
       t = m + "g " + n;
       
       //add to the text area
       value.setText(value.getText() + t + "\n");
    }
    
    //panel for recipe list
    JPanel value1 = new JPanel();
    value1.setBackground(new Color(237, 157, 206));
    value1.add(value);
    value1.setPreferredSize(new Dimension(600, 500));
    value.setPreferredSize(new Dimension(600, 500));
    
    //add components to the main panel
    show.add(key1, BorderLayout.NORTH);
    show.add(value1, BorderLayout.CENTER);
    value.setBackground(new Color(245, 236, 164));
    value.setFont(new Font ("MonoSpaced", Font.BOLD, 15));
    show.add(allButtons, BorderLayout.SOUTH);
    show.setPreferredSize(new Dimension(1000, 650));
    this.add(show);
    
    this.updateUI();
  }//end of the display recipe screen
  
  //display selection panel method - Veronica
  public void displaySelectionPanel(Ingredient anIng)
  {
    //clear altList of previous alt info
    this.altList.removeAll();
    this.altList.repaint();
    
    //getting the name of the original ingredient and making a radio button and the blurb
    String ingOgText = anIng.getMeasurement() + "g " + anIng.getName();
    JRadioButton ingOg = new JRadioButton(String.format("%-2000s", ingOgText));
    ingOg.setActionCommand(ingOgText);
    JTextArea ingOgB = new JTextArea(anIng.getDescription());
    ingOgB.setEditable(false);
    
    /*getting the names of the alternative ingredients and making radio buttons*/
    
    //alt1
    String alt1Text = anIng.getAlt1().getHealthyMeasure() + "g " + anIng.getAlt1().getHealthyName();
    JRadioButton alt1 = new JRadioButton(String.format("%-2000s", alt1Text));
    alt1.setActionCommand(alt1Text);
    JTextArea alt1B = new JTextArea(anIng.getAlt1().getHealthyDescrip());
    alt1B.setEditable(false);
    
    //alt2
    String alt2Text = anIng.getAlt2().getHealthyMeasure() + "g " + anIng.getAlt2().getHealthyName();
    JRadioButton alt2 = new JRadioButton(String.format("%-2000s", alt2Text));
    alt2.setActionCommand(alt2Text);
    JTextArea alt2B = new JTextArea (anIng.getAlt2().getHealthyDescrip());
    alt2B.setEditable(false);
    
    //alt3
    String alt3Text = anIng.getAlt3().getHealthyMeasure() + "g " + anIng.getAlt3().getHealthyName();
    JRadioButton alt3 = new JRadioButton(String.format("%-2000s", alt3Text));
    alt3.setActionCommand(alt3Text);
    JTextArea alt3B = new JTextArea (anIng.getAlt3().getHealthyDescrip());
    alt3B.setEditable(false);
    
    //add the radio buttons to the button group
    this.ingSelection.add(ingOg);
    this.ingSelection.add(alt1);
    this.ingSelection.add(alt2);
    this.ingSelection.add(alt3);
    
    //preselect ing og
    ingOg.setSelected(true);
    
    //get image of unhealthy ing - Ankita
    this.pics = new JLabel(anIng.getPics());
    
    //get image of alternative ing - Ankita
    this.altPics1 = new JLabel(anIng.getAlt1().getHealthyPics());
    this.altPics2 = new JLabel(anIng.getAlt2().getHealthyPics());
    this.altPics3 = new JLabel(anIng.getAlt3().getHealthyPics());
    
    //panel to store all pics - Ankita
    JPanel picPane = new JPanel();
    picPane.setLayout(new GridLayout(4,1));
    picPane.add(pics);
    picPane.add(altPics1);
    picPane.add(altPics2);
    picPane.add(altPics3);
    
    
    //add the buttons and the select button the the alt list panel
    JPanel altList1 = new JPanel();
    altList1.setLayout(new BoxLayout(altList1, BoxLayout.Y_AXIS));
    altList1.add(ingOg);
    ingOg.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    altList1.add(ingOgB);
    ingOgB.setFont(new Font ("MonoSpaced", Font.BOLD, 10));
    altList1.add(alt1);
    alt1.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    altList1.add(alt1B);
    alt1B.setFont(new Font ("MonoSpaced", Font.BOLD, 10));
    altList1.add(alt2);
    alt2.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    altList1.add(alt2B);
    alt2B.setFont(new Font ("MonoSpaced", Font.BOLD, 10));
    altList1.add(alt3);
    alt3.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    altList1.add(alt3B);
    alt3B.setFont(new Font ("MonoSpaced", Font.BOLD, 10));
    this.altList.setLayout(new BorderLayout());
    this.altList.add(altList1, BorderLayout.CENTER);
    this.altList.add(picPane, BorderLayout.EAST);
    select.setFont(new Font ("MonoSpaced", Font.BOLD, 12));
    this.altList.add(select, BorderLayout.SOUTH);
    select.setBackground(new Color(250, 230, 230));
    
    this.updateUI();
  }//end of display selection panel
}//end of class