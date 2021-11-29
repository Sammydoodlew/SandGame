package sand.controller;

import java.awt.*;
import java.util.*;
import sand.view.SandDisplay;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[4];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    
    //1. Add code to initialize the data member grid with same dimensions
    
    this.grid = new int[numRows][numCols];
    this.display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
	  grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
	  for(int row = 0; row < grid.length; row++)
	  {
		  for(int col = 0; col < grid[row].length; col++)
		  {
			  int currentTool = grid[row][col];
			  
			  if(currentTool == EMPTY)
			  {
				  display.setColor(row, col, Color.BLACK);
			  } 
			  else if(currentTool == METAL)
			  {
				  display.setColor(row, col, Color.GRAY);
			  } 
			  else if(currentTool == SAND)
			  {
				  display.setColor(row, col, Color.YELLOW);
			  }
			  else if(currentTool == WATER)
			  {
				  display.setColor(row, col, Color.BLUE);
			  }
		  }
	  }
    
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
	  int randomRow = (int)(Math.random() * grid.length);
	  int randomCol = (int)(Math.random() * grid[0].length);
	  
	  int currentTool = grid[randomRow][randomCol];
	  
	  if(currentTool == SAND && randomRow < grid.length - 1)
	  {
		  if(grid[randomRow + 1][randomCol] == EMPTY)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol] = SAND;
		  }
		  else if(grid[randomRow + 1][randomCol] == WATER)
		  {
			  grid[randomRow][randomCol] = WATER;
			  grid[randomRow + 1][randomCol] = SAND;
		  }
	  }
	  else if(currentTool == WATER)
	  {
		  int randomDir = (int)(Math.random() * 3);
		  if(randomDir == 0 && randomRow < grid.length - 1)
		  {
			  if(grid[randomRow + 1][randomCol] == EMPTY)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow + 1][randomCol] = WATER;
			  }
		  }
		  else if(randomDir == 1 && randomCol < grid[randomCol].length - 1)
		  {
			  if(grid[randomRow][randomCol + 1] == EMPTY)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow][randomCol + 1] = WATER;
			  }
		  }
		  else if(randomDir == 2 && randomCol > 0)
		  {
			  if(grid[randomRow][randomCol - 1] == EMPTY)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow][randomCol - 1] = WATER;
			  }
		  }
	  }
	  else if(currentTool == METAL)
	  {
		  
	  }
	  else if(currentTool == EMPTY)
	  {
		  
	  }
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
