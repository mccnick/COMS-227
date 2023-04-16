package hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import api.Tile;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Utility class with static methods for saving and loading game files.
 */
public class GameFileUtil
{

	/**
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
		{
			Grid grid = game.getGrid(); // new grid instance
			int width = grid.getWidth(); // width
			int height = grid.getHeight(); // height
			int minTileLevel = game.getMinTileLevel();
			int maxTileLevel = game.getMaxTileLevel();
			int score = (int) game.getScore();
			String saveString = String.format("%d %d %d %d %d\n", width, height, minTileLevel, maxTileLevel, score);
			writer.write(saveString);

			for (int i = 0; i < height; i++) // iterate height
			{
				String str = ""; // create new string
				for (int j = 0; j < width; j++) // iterate width
				{
					Tile tile = grid.getTile(j, i); // new tile
					int level = tile.getLevel(); // level variable
					str += level + (j == width - 1 ? "" : " "); // set str
				}
				writer.write(str); // write str

				if (i < height - 1)
				{
					writer.write("\n"); // new line
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * <p>
	 * See the save() method for the specification of the file format.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 */
	public static void load(String filePath, ConnectGame game)
	{
		try
		{
			File file = new File(filePath); // file
			Scanner scanner = new Scanner(file); // scanner

			int width = scanner.nextInt(); // scan width
			int height = scanner.nextInt(); // scan height
			Grid grid = new Grid(width, height); // new grid created
			game.setMinTileLevel(scanner.nextInt()); // scan minLevel
			game.setMaxTileLevel(scanner.nextInt()); // scan maxLevel
			game.setScore(scanner.nextInt()); // setting score

			for (int i = 0; i < height; i++) // iterate height
			{
				for (int j = 0; j < width; j++) // iterate width
				{
					int level = scanner.nextInt(); // scan level
					Tile tile = new Tile(level); // set the levels
					grid.setTile(tile, j, i); // set the saved tiles
				}
			}

			game.setGrid(grid); // set a new grid
			scanner.close(); // close scanner
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}