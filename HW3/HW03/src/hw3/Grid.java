package hw3;

import api.Tile;

/**
 * Represents the game's grid.
 */
public class Grid
{
	/**
	 * 2D Tile array
	 */
	private Tile[][] tiles;

	/**
	 * Grid width
	 */
	private int width;
	/**
	 * Grid height
	 */
	private int height;

	/**
	 * Creates a new grid.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public Grid(int width, int height)
	{
		// initialize grid
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public Tile getTile(int x, int y)
	{
		return tiles[x][y];
	}

	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(Tile tile, int x, int y)
	{
		tiles[x][y] = tile;
		tile.setLocation(x, y);
	}

	@Override
	public String toString()
	{
		String str = "";
		for (int y = 0; y < getHeight(); y++)
		{
			if (y > 0)
			{
				str += "\n";
			}
			str += "[";
			for (int x = 0; x < getWidth(); x++)
			{
				if (x > 0)
				{
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
}