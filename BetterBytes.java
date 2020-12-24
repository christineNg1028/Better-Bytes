//BetterBytes
//Veronica Starr
//December 26, 2019
//Startup for the final program

import javax.swing.*;

//start of class
public class BetterBytes
{
  //start of main method
  public static void main (String [] args)
  {    
    //initialization of model and view
    ByteModel model = new ByteModel();
    ByteView byteGUI = new ByteView(model);
   
    //initialization of frame
    JFrame f = new JFrame("Better Bytes");
    f.setSize(1000, 750);
    f.setLocation(100,0);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setContentPane(byteGUI);
    f.setVisible(true);
  }//end of main method
}//end of class
