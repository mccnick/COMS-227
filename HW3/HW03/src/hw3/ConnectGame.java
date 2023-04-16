package hw3;

import java.util.Random;
import java.util.*;

import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game similar to 2048.
 * 
 * @author Nick McCullough - COMS 227 - HW3
 */
public class ConnectGame
{
	/**
	 * Game's Grid
	 */
	private Grid gameGrid;

	/**
	 * Width of grid
	 */
	private int gridWidth;

	/**
	 * Height of grid
	 */
	private int gridHeight;

	/**
	 * Minimum Tile Level
	 */
	private int minimumTileLevel;

	/**
	 * Maximum Tile Level
	 */
	private int maximumTileLevel;

	/**
	 * Previous level
	 */
	private int previousTileLevel;

	/**
	 * Actual Score
	 */
	private long score = 0;

	/**
	 * Temporary Score
	 */
	private long tempScore = 0;

	/**
	 * Tiles are indeed being selected
	 */
	private boolean selectingTiles = false;

	/**
	 * Initial tile being selected
	 */
	private boolean startingTile = false;

	/**
	 * Drop tile level
	 */
	private boolean drop = false;

	/**
	 * List of tiles being selected
	 */
	private List<Tile> selectedTiles;

	/**
	 * Random
	 */
	private Random rand;

	/*
	 * Given Dialog Listener
	 */
	private ShowDialogListener dialogListener;

	/*
	 * Given Score Listener
	 */
	private ScoreUpdateListener scoreListener;

	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand)
	{
		// initialize constructor parameters
		this.gridWidth = width;
		this.gridHeight = height;
		setMinTileLevel(min);
		setMaxTileLevel(max);
		this.rand = rand;

		selectedTiles = new ArrayList<Tile>(); // list of tiles being selected
		gameGrid = new Grid(width, height); // new grid created
		radomizeTiles(); // randomize the tiles for new game
	} // done

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile()
	{
		Tile newRandomLevelTile = new Tile(rand.nextInt(maximumTileLevel - minimumTileLevel) + minimumTileLevel);
		return newRandomLevelTile; // new random level tile
	} // done

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles()
	{
		for (int i = 0; i < gridHeight; i++) // iterate thru height
		{
			for (int j = 0; j < gridWidth; j++) // iterate thru width
			{
				gameGrid.setTile(getRandomTile(), j, i); // sets new grid with getRandomTile()
			}
		}
	} // done

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2)
	{
		int dx = Math.abs((t1.getX()) - (t2.getX())); // x difference between tile 1 & tile 2
		int dy = Math.abs((t1.getY()) - (t2.getY())); // y difference between tile 1 & tile 2

		if ((dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1))
		{
			return true; // true if --> adjacent tiles are horizontal OR vertical OR diagonal
		}
		else
			if (dx == 0 && dy == 0)
			{
				return false; // not adjacent
			}
			else
			{
				return false; // false
			}
	} // done

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y)
	{
		if (selectingTiles) // are we ALREADY selecting tiles?
		{
			return false; // can't already be selecting tiles at this point.
		}

		selectingTiles = true; // let the selecting begin.
		Tile tile = gameGrid.getTile(x, y); // new Tile to be selected
		tile.setSelect(true); // true, tile being selected
		selectedTiles.add(tile); // add this tile to our list of selected tiles
		tempScore += tile.getValue(); // increment score for the value in current tile
		previousTileLevel = tile.getLevel(); // ensures level remains the same
		startingTile = true; // this is our starting tile.
		return true;
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y)
	{
		if (selectingTiles) // are we selecting Tiles?
		{
			Tile tile = gameGrid.getTile(x, y); // new adjacent tile that is NOT already selected

			if ((isAdjacent(selectedTiles.get(selectedTiles.size() - 1), tile)) && (!tile.isSelected()))
			{
				if (startingTile) // identify tile to start selecting
				{
					if (tile.getLevel() == previousTileLevel) // making sure continue select is of same level
					{
						startingTile = false;
						tile.setSelect(true);
						selectedTiles.add(tile);
						tempScore += tile.getValue();
					}
				}
				else // still valid if same level or level + 1
				{
					if ((tile.getLevel() == previousTileLevel) || (tile.getLevel() == previousTileLevel + 1))
					{
						previousTileLevel = tile.getLevel();
						tile.setSelect(true);
						selectedTiles.add(tile);
						tempScore += tile.getValue();
					}
				}
			}
			else // else if the current tile is the second-to-last selected tile
			{
				if (selectedTiles.size() >= 2 && selectedTiles.get(selectedTiles.size() - 2) == tile)
				{
					Tile lastTile = selectedTiles.get(selectedTiles.size() - 1);
					unselect(lastTile.getX(), lastTile.getY()); // Unselect the last selected tile
				}
			}
		}
	} // done

	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */

	public boolean tryFinishSelection(int x, int y)
	{
		if (!selectingTiles) // if no selection in progress,
		{
			return false; // immediate false
		}

		Tile lastSelectedTile = selectedTiles.get(selectedTiles.size() - 1);
		if ((x != lastSelectedTile.getX()) || (y != lastSelectedTile.getY()))
		{
			return false; // if tile not selected, false
		}

		int numberOfSelectedTiles = selectedTiles.size();
		if (numberOfSelectedTiles == 1)
		{
			selectedTiles.get(0).setSelect(false);
			selectedTiles.clear(); // reset & clear if only 1 tile selected
		}
		else
		{
			upgradeLastSelectedTile(); // ^^
			dropSelected();

			if (drop) // dropping level
			{
				dropLevel(minimumTileLevel - 1); // drop all levels below min - 1
			}

			score += tempScore; // add temp score to score
			scoreListener.updateScore(score); // update score

			for (Tile tile : selectedTiles)
			{
				tile.setSelect(false); // reset
			}
			selectedTiles.clear(); // clear current selection
			tempScore = 0;
		}

		selectingTiles = false; // finished selecting
		return true;
	} // done

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile()
	{
		Tile tile = selectedTiles.remove(selectedTiles.size() - 1); // last selected tile
		int nextLevel = tile.getLevel() + 1; // increment tile level to nextlevel
		tile.setLevel(nextLevel);
		tile.setSelect(false);

		if (nextLevel > maximumTileLevel) // confirming next level
		{
			int oldTile = (int) Math.pow(2, minimumTileLevel); // dialog displaying new & old tiles
			dialogListener.showDialog("New block " + tile.getValue() + ", removing blocks " + oldTile);

			minimumTileLevel++; // increment min level
			maximumTileLevel++; // increment max level
			drop = true; // drop the tiles
		}
	} // done

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray()
	{
		Tile[] selectedTilesArray = new Tile[selectedTiles.size()]; // new array same size as list of selectedTiles
		int i = 0;
		for (Tile tile : selectedTiles)
		{
			selectedTilesArray[i++] = tile; // increment the array, equal to tile
		}
		return selectedTilesArray;
	} // done

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level)
	{
		Tile currentTile, aboveTile;

		for (int x = 0; x < gridWidth; x++) // iterate
		{
			for (int y = 0; y < gridHeight; y++) // iterate
			{
				currentTile = gameGrid.getTile(x, y); // new instance
				if (currentTile.getLevel() == level)
				{
					for (int j = currentTile.getY(); j > 0; j--) // iterate vertically to drop level
					{
						aboveTile = gameGrid.getTile(currentTile.getX(), j - 1); // get tile
						gameGrid.setTile(aboveTile, currentTile.getX(), j); // set tile
						currentTile = aboveTile; // replace
					}
					gameGrid.setTile(getRandomTile(), currentTile.getX(), 0); // new random tile at the top
				}
			}
		}
	} // done

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected()
	{
		for (Tile selectedTile : selectedTiles) // iterate selected tiles
		{
			int x = selectedTile.getX();
			for (int y = selectedTile.getY(); y > 0; y--)
			{
				Tile tileAbove = gameGrid.getTile(x, y - 1);
				gameGrid.setTile(tileAbove, x, y); // move each selected tile down
			}
			gameGrid.setTile(getRandomTile(), x, 0); // new random tile at the top
		}
	} // done

	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y)
	{
		if (selectingTiles) // are we selecting tiles?
		{
			Tile tile;

			for (int i = 0; i < selectedTiles.size(); i++) // iterate arraylist size
			{
				tile = selectedTiles.get(i); // set new tile = index

				if ((tile.getX() == x) && (tile.getY() == y)) // if tile matches
				{
					tempScore -= tile.getValue(); // subtract tile from temp score
					selectedTiles.remove(i); // remove
					tile.setSelect(false); // unselect
					break; // only one tile to unselect at a time
				}
			}
		}
	} // done

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore()
	{
		return score;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid()
	{
		return gameGrid;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel()
	{
		return minimumTileLevel;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel()
	{
		return maximumTileLevel;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score)
	{
		this.score = score;
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid)
	{
		this.gameGrid = grid;
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel)
	{
		minimumTileLevel = minTileLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel)
	{
		maximumTileLevel = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener)
	{
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath)
	{
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath)
	{
		GameFileUtil.load(filePath, this);
	}
}