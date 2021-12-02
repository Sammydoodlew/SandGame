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
  public static final int WOOD = 4;
  public static final int FIRE = 5;
  public static final int ICE = 6;
  public static final int DIRT = 7;
  public static final int SEED = 8;
  public static final int GRASS = 9;
  public static final int ASH = 10;
  public static final int WOOD_FLAMING = 11;
  public static final int LEAVES = 12;
  public static final int LEAVES_FLAMING = 13;
  
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
    names = new String[9];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[WOOD] = "Wood";
    names[FIRE] = "Fire";
    names[ICE] = "Ice";
    names[DIRT] = "Dirt";
    names[SEED] = "Seed";
    
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
				  display.setColor(row, col, new Color(220, 210, 120));
			  }
			  else if(currentTool == WATER)
			  {
				  display.setColor(row, col, new Color(70, 100, 200));
			  }
			  else if(currentTool == WOOD)
			  {
				  display.setColor(row, col, new Color(205, 130, 65));
			  }
			  else if(currentTool == FIRE)
			  {
				  display.setColor(row, col, new Color(220, 85, 0));
			  }
			  else if(currentTool == ASH)
			  {
				  display.setColor(row, col, Color.DARK_GRAY);
			  }
			  else if(currentTool == WOOD_FLAMING)
			  {
				  display.setColor(row, col, Color.ORANGE);
			  }
			  else if(currentTool == ICE)
			  {
				  display.setColor(row, col, new Color(125, 185, 220));
			  }
			  else if(currentTool == DIRT)
			  {
				  display.setColor(row, col, new Color(150, 75, 25));
			  }
			  else if(currentTool == GRASS)
			  {
				  display.setColor(row, col, new Color(80, 230, 45));
			  }
			  else if(currentTool == SEED)
			  {
				  display.setColor(row, col, new Color(130, 210, 45));
			  }
			  else if(currentTool == LEAVES)
			  {
				  display.setColor(row, col, new Color(85, 195, 80));
			  }
			  else if(currentTool == LEAVES_FLAMING)
			  {
				  display.setColor(row, col, new Color(245, 255, 70));
			  }
		  }
	  }
    
  }
  
  public void gravity(int type, int randomRow, int randomCol)
  {	  
	  int randomDir = (int)(Math.random() * 2);
	  
	  if(grid[randomRow + 1][randomCol] == EMPTY)
	  {
		  grid[randomRow][randomCol] = EMPTY;
		  grid[randomRow + 1][randomCol] = type;
		  return;
	  }
	  if(randomCol < grid[randomRow].length - 1 && randomDir == 0)
	  {
		  if(grid[randomRow + 1][randomCol + 1] == EMPTY && grid[randomRow][randomCol + 1] == EMPTY)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol + 1] = type;
		  }
	  }
	  else if(randomCol > 0 && randomDir == 1)
	  {
		  if(grid[randomRow + 1][randomCol - 1] == EMPTY && grid[randomRow][randomCol - 1] == EMPTY)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol - 1] = type;
		  }
	  }
	  
	  if(grid[randomRow + 1][randomCol] == WATER)
	  {
		  grid[randomRow][randomCol] = WATER;
		  grid[randomRow + 1][randomCol] = type;
		  return;
	  }
	  if(randomCol < grid[randomRow].length - 1 && randomDir == 0)
	  {
		  if(grid[randomRow + 1][randomCol + 1] == WATER && grid[randomRow][randomCol + 1] == WATER)
		  {
			  grid[randomRow][randomCol] = WATER;
			  grid[randomRow + 1][randomCol + 1] = type;
		  }
	  }
	  else if(randomCol > 0 && randomDir == 1)
	  {
		  if(grid[randomRow + 1][randomCol - 1] == WATER && grid[randomRow][randomCol - 1] == WATER)
		  {
			  grid[randomRow][randomCol] = WATER;
			  grid[randomRow + 1][randomCol - 1] = type;
		  }
	  }
  }
  
  public void gravityIce(int type, int randomRow, int randomCol)
  {	  
	  int randomDir = (int)(Math.random() * 2);
	  int movement = (int)(Math.random() * 200);
	  
	  if(grid[randomRow + 1][randomCol] == EMPTY)
	  {
		  grid[randomRow][randomCol] = EMPTY;
		  grid[randomRow + 1][randomCol] = type;
		  return;
	  }
	  
	  if(movement == 1)
	  {
		  if(randomCol < grid[randomRow].length - 1 && randomDir == 0)
		  {
			  if(grid[randomRow + 1][randomCol + 1] == EMPTY && grid[randomRow][randomCol + 1] == EMPTY)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow + 1][randomCol + 1] = type;
			  }
		  }
		  else if(randomCol > 0 && randomDir == 1)
		  {
			  if(grid[randomRow + 1][randomCol - 1] == EMPTY && grid[randomRow][randomCol - 1] == EMPTY)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow + 1][randomCol - 1] = type;
			  }
		  }
	  }
  }
  
  public void gravityDirt(int type, int randomRow, int randomCol)
  {
	  int randomDir = (int)(Math.random() * 2);
	  int movement = (int)(Math.random() * 100);
	  int grass = (int)(Math.random() * 2000);
	  int dirt = (int)(Math.random() * 100);
	  
	  if(grid[randomRow + 1][randomCol] == EMPTY)
	  {
		  grid[randomRow][randomCol] = EMPTY;
		  grid[randomRow + 1][randomCol] = type;
		  return;
	  }
	  
	  if(movement == 1)
	  {
		  if(randomCol < grid[randomRow].length - 1 && randomDir == 0)
		  {
			  if(grid[randomRow + 1][randomCol + 1] == EMPTY && grid[randomRow][randomCol + 1] == EMPTY)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow + 1][randomCol + 1] = type;
			  }
		  }
		  else if(randomCol > 0 && randomDir == 1)
		  {
			  if(grid[randomRow + 1][randomCol - 1] == EMPTY && grid[randomRow][randomCol - 1] == EMPTY)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow + 1][randomCol - 1] = type;
			  }
		  }
	  }
	  
	  if(grass == 1 && grid[randomRow][randomCol] > 0)
	  {
		  if(grid[randomRow - 1][randomCol] == EMPTY)
		  {
			  grid[randomRow][randomCol] = GRASS;
		  }
	  }
	  
	  if(dirt == 1 && grid[randomRow][randomCol] < grid[randomRow].length)
	  {
		  if(grid[randomRow - 1][randomCol] != EMPTY)
		  {
			  grid[randomRow][randomCol] = DIRT;
		  }
	  }
  }
  
  public void buildTree(int randomRow, int randomCol)
  {
	  int treeHeight = (int)(Math.random() * 4) + 5;
	  int bushRadius = (int)(Math.random() * 4) + 4; 
	  System.out.println(randomRow + ", " + randomCol);
	  
	  if(randomRow - treeHeight < 1)
	  {
		  return;
	  }
	  
	  for(int i = randomRow; i > randomRow - treeHeight; i--)
	  {
		  grid[i][randomCol] = WOOD;
	  }
	  
	  for(int i = 0; i < 30; i++)
	  {
		  int randomRangeRow = (randomRow - treeHeight) + (int)(Math.random() * bushRadius);
		  int randomRangeCol = randomCol - (bushRadius / 2) + (int)(Math.random() * bushRadius);
		  
		  if(randomRangeRow > 0 && randomRangeRow < grid.length)
		  {
			  if(randomRangeCol > 0 && randomRangeCol < grid[randomRow].length)
			  {
				  grid[randomRangeRow][randomRangeCol] = LEAVES;
			  }
		  }
	  }
	  
//	  for(int row = randomRow - treeHeight + bushRadius; randomRow > randomRow - treeHeight; row--)
//	  {
//		  for(int col = randomCol - bushRadius; col < randomCol + bushRadius; col++)
//		  {
//			  grid[row][col] = LEAVES;
//		  }
//	  }
 
  }

  public void fire(int type, int randomRow, int randomCol)
  {
	  int randomDir = (int)(Math.random() * 3);
	  int dissapate = (int)(Math.random() * 50);
	  int flammability = 20;
	  int flameChance = (int)(Math.random() * flammability);
	  
	  if(dissapate == 1)
	  {
		  grid[randomRow][randomCol] = EMPTY;
		  return;
	  }
	  
	  if(randomDir == 0 && randomRow > 0)
	  {
		  if(grid[randomRow - 1][randomCol] == EMPTY)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow - 1][randomCol] = type;
		  }
		  else if(grid[randomRow - 1][randomCol] == WOOD && flameChance == 1)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow - 1][randomCol] = WOOD_FLAMING;
		  }
		  else if(grid[randomRow - 1][randomCol] == LEAVES && flameChance == 1)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow - 1][randomCol] = LEAVES_FLAMING;
		  }
	  }
	  else if(randomDir == 1 && randomCol < grid[randomCol].length - 1)
	  {
		  if(grid[randomRow][randomCol + 1] == EMPTY)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol + 1] = type;
		  }
		  else if(grid[randomRow][randomCol + 1] == WOOD && flameChance == 1)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol + 1] = WOOD_FLAMING;
		  }
		  else if(grid[randomRow][randomCol + 1] == LEAVES && flameChance == 1)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol + 1] = LEAVES_FLAMING;
		  }
	  }
	  else if(randomDir == 2 && randomCol > 0)
	  {
		  if(grid[randomRow][randomCol - 1] == EMPTY)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol - 1] = type;
		  }
		  else if(grid[randomRow][randomCol - 1] == WOOD && flameChance == 1)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol - 1] = WOOD_FLAMING;
		  }
		  else if(grid[randomRow][randomCol - 1] == LEAVES && flameChance == 1)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol - 1] = LEAVES_FLAMING;
		  }
	  }
  }

  public void flaming(int randomRow, int randomCol)
  {
	  int randomDir = (int)(Math.random() * 3);
	  int ash = (int)(Math.random() * 1000);
	  
	  if(ash == 1)
	  {
		  grid[randomRow][randomCol] = ASH;
		  return;
	  }
			  
	  if(randomDir == 0 && randomRow > 0)
	  {
		  if(grid[randomRow - 1][randomCol] == EMPTY)
		  {
			  grid[randomRow - 1][randomCol] = FIRE;
		  }
	  }
	  else if(randomDir == 1 && randomCol < grid[randomCol].length - 1)
	  {
		  if(grid[randomRow][randomCol + 1] == EMPTY)
		  {
			  grid[randomRow][randomCol + 1] = FIRE;
		  }
	  }
	  else if(randomDir == 2 && randomCol > 0)
	  {
		  if(grid[randomRow][randomCol - 1] == EMPTY)
		  {
			  grid[randomRow][randomCol - 1] = FIRE;
		  }
	  }
  }

  public void water(int type, int randomRow, int randomCol)
  {
	  int randomDir = (int)(Math.random() * 3);
	  if(randomDir == 0 && randomRow < grid.length - 1)
	  {
		  if(grid[randomRow + 1][randomCol] == EMPTY || grid[randomRow + 1][randomCol] == FIRE)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol] = type;
		  }
		  else if(grid[randomRow + 1][randomCol] == WOOD_FLAMING)
		  {
			  grid[randomRow + 1][randomCol] = WOOD;
		  }
		  else if(grid[randomRow + 1][randomCol] == LEAVES_FLAMING)
		  {
			  grid[randomRow + 1][randomCol] = LEAVES;
		  }
	  }
	  else if(randomDir == 1 && randomCol < grid[randomCol].length - 1)
	  {
		  if(grid[randomRow][randomCol + 1] == EMPTY || grid[randomRow][randomCol + 1] == FIRE)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol + 1] = type;
		  }
		  else if(grid[randomRow][randomCol + 1] == WOOD_FLAMING)
		  {
			  grid[randomRow][randomCol + 1] = WOOD;
		  }
		  else if(grid[randomRow][randomCol + 1] == LEAVES_FLAMING)
		  {
			  grid[randomRow][randomCol + 1] = LEAVES;
		  }
	  }
	  else if(randomDir == 2 && randomCol > 0)
	  {
		  if(grid[randomRow][randomCol - 1] == EMPTY || grid[randomRow][randomCol - 1] == FIRE)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow][randomCol - 1] = type;
		  }
		  else if(grid[randomRow][randomCol - 1] == WOOD_FLAMING)
		  {
			  grid[randomRow][randomCol - 1] = WOOD;
		  }
		  else if(grid[randomRow][randomCol - 1] == LEAVES_FLAMING)
		  {
			  grid[randomRow][randomCol - 1] = LEAVES;
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
		  gravity(SAND, randomRow, randomCol);		  
	  }
	  else if(currentTool == WATER)
	  {
		  water(WATER, randomRow, randomCol);
	  }
	  else if(currentTool == FIRE)
	  {
		  fire(FIRE, randomRow, randomCol);
	  }
	  else if(currentTool == WOOD_FLAMING)
	  {
		  flaming(randomRow, randomCol);
	  }
	  else if(currentTool == ASH && randomRow < grid.length - 1)
	  {
		  gravity(ASH, randomRow, randomCol);
	  }
	  else if(currentTool == ICE && randomRow < grid.length - 1)
	  {
		  gravityIce(ICE, randomRow, randomCol);
	  }
	  else if(currentTool == DIRT && randomRow < grid.length - 1)
	  {
		  gravityDirt(DIRT, randomRow, randomCol);
	  }
	  else if(currentTool == GRASS && randomRow < grid.length - 1)
	  {
		  gravityDirt(GRASS, randomRow, randomCol);
	  }
	  else if(currentTool == SEED && randomRow < grid.length - 1)
	  {
		  if(grid[randomRow + 1][randomCol] == EMPTY)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol] = SEED;
		  }
		  else if(grid[randomRow + 1][randomCol] == GRASS)
		  {
			  grid[randomRow][randomCol] = EMPTY;
			  buildTree(randomRow, randomCol);
		  }
		  else
		  {
			  grid[randomRow][randomCol] = EMPTY;
		  }
	  }
	  else if(currentTool == LEAVES_FLAMING)
	  {
		  flaming(randomRow, randomCol);
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
