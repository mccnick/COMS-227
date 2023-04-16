package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Nick McCullough
 */

/*
 * COM S 227 - ASSIGNMENT 2
 */

public class ThreeCushion
{
	/*
	 * lagWinner
	 */
	private PlayerPosition lagWinner = null;

	/*
	 * player A
	 */
	private PlayerPosition playerA = null;

	/*
	 * player B
	 */
	private PlayerPosition playerB = null;

	/*
	 * current inning player
	 */
	private PlayerPosition inningPlayer = null;

	/*
	 * current cueball
	 */
	private BallType cueBall = null;

	/*
	 * player A cueball
	 */
	private BallType cueBallA = null;

	/*
	 * player B cueball
	 */
	private BallType cueBallB = null;

	/*
	 * inning
	 */
	private int inning;

	/*
	 * player A score (lagWinner)
	 */
	private int playerAScore = 0;

	/*
	 * player B score
	 */

	private int playerBScore = 0;

	/*
	 * points to win
	 */
	private int pointsToWin;

	/*
	 * keeps track of cushion impacts
	 */
	private int cushionImpacts = 0;

	/*
	 * foul
	 */
	private boolean foul = false;

	/*
	 * game begins
	 */
	private boolean gameBegin = false;

	/*
	 * bank shot
	 */
	private boolean bankShot = false;

	/*
	 * cue stick strikes cue ball
	 */
	private boolean cueStickStrikesCueBall = false;

	/*
	 * white ball is struck
	 */
	private boolean whiteBall = false;

	/*
	 * yellow ball is struck
	 */
	private boolean yellowBall = false;

	/*
	 * red ball is struck
	 */
	private boolean redBall = false;

	/*
	 * break shot
	 */
	private boolean isBreakShot = true;

	/*
	 * valid shot
	 */
	private boolean isShotValid = false;

	/*
	 * shot started
	 */
	private boolean isShotStarted = false;

	/*
	 * inning begins
	 */
	private boolean isInningStarted = false;

	/**
	 * CONSTRUCTOR * Creates a new game of three-cushion billiards with a given lag
	 * winner and the predetermined number of points required to win the game. The
	 * inning count starts at 1.
	 * 
	 * @param lagWinner   - either player A or B
	 * @param pointsToWin - the number of points a player needs to reach for the
	 *                    game to end
	 **/
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin)
	{
		inning = 1; // inning starts at 1
		this.pointsToWin = pointsToWin; // points to win passed thru test
		this.lagWinner = lagWinner; // first player or current player
		playerA = PlayerPosition.PLAYER_A; // player A placeholder
		playerB = PlayerPosition.PLAYER_B; // player B placeholder

	}

	/**
	 * Sets whether the player that won the lag chooses to break (take first shot),
	 * or chooses the other player to break.
	 * 
	 * @param selfBreak
	 * @param cueBall
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall)
	{
		// start the game
		gameBegin = true;

		if (gameBegin == true)
		{

			// player A is lagwinner
			if (lagWinner == playerA)
			{
				// player A's cueball is now the cueball
				cueBallA = cueBall;
				this.cueBall = cueBall;
				inningPlayer = lagWinner;

				// problem 6 specChecker - I can't figure out here....

			}

			if (lagWinner == playerB)
			{
				// player B's cueball is now the cueball
				cueBallB = cueBall;
				this.cueBall = cueBall;
				inningPlayer = lagWinner;
			}
			else
			{
				// else player B's cueball is now the cueball
				cueBallA = cueBall;
				cueBallA = BallType.WHITE;
			}

			// self break
			if (selfBreak == true)
			{
				// lagWinner
				cueBallA = cueBall;
			}
			else
			{
				cueBallA = BallType.WHITE; // player A concedes but keeps white ball?
				// lagwinner concedes inning start
				inningPlayer = playerB;
				cueBallB = cueBall;
			}

		}
	}

	/**
	 * Indicates the cue stick has struck the given ball. If a shot has not already
	 * begun, indicates the start of a new shot. If this method is called while a
	 * shot is still in progress (i.e., endShot() has not been called for the
	 * previous shot), the player has committed a foul (see the method foul()).
	 * Also, if the player strikes anything other than their own cue ball, they
	 * committed a foul. Calling this method signifies both the start of a shot and
	 * the start of an inning, assuming a shot or inning has not already begun,
	 * respectively.
	 * 
	 * Even if a foul has been committed, calling this method is considered the
	 * start of a shot. That includes even the case when the player strikes a ball
	 * other than their own cue ball. It is expected that the endShot() method will
	 * be called in any case to indicate the end of the shot.
	 * 
	 * No play can begin until the lag player has chosen who will break (see
	 * lagWinnderChooses). If this method is called before the break is chosen, it
	 * should do nothing.
	 * 
	 * If this method is called after the game has ended, it should do nothing.
	 * 
	 * @param ball
	 */
	public void cueStickStrike(BallType ball)
	{
		if (gameBegin == true)
		{
			// cue stick strike starts the inning
			bankShot = false;
			isInningStarted = true;

			// a foul can happen prior to the cue stick strike process
			if (foul == false && isShotStarted == false)
			{
				isShotStarted = true;
				cueStickStrikesCueBall = true;

				// if its valid, and you hit a ball that's not the cueball, foul.
				if (ball != cueBall)
				{
					foul();
				}
			}
			else
			{
				foul = false;
				foul();
			}
		}
	}

	/**
	 * Indicates the player's cue ball has struck the given ball. A ball strike
	 * cannot happen before a stick strike. If this method is called before the
	 * start of a shot (i.e., cueStickStrike() is called), it should do nothing.
	 * 
	 * If this method is called after the game has ended, it should do nothing.
	 * 
	 * @param ball
	 */
	public void cueBallStrike(BallType ball)
	{
		// strike with cueStick first
		if (cueStickStrikesCueBall == true)
		{
			// if cueball striking is white (before the hit)
			if (cueBall == BallType.WHITE)
			{
				// identifying ball to be hit (yellow)
				if (ball == BallType.YELLOW)
				{
					// before ball hit, is it a red Ball breakshot? T/F
					if ((isBreakShot == true) && (redBall == false))
					{
						// redBall false, foul.
						foul();

					}
					else
					{
						// yellow ball was struck by white cueball
						yellowBall = true;
					}

				}
				else
				{
					// identifying other ball to be hit (red)
					if (ball == BallType.RED)
					{
						// red ball was struck by white cueball
						redBall = true;
					}
				}
			}
			else
			{
				// else the cueball striking is yellow
				if (cueBall == BallType.YELLOW)
				{
					// identifying other ball to be hit (yellow)
					if (ball == BallType.WHITE)
					{
						// before ball hit, is it a red Ball breakshot? T/F
						if ((isBreakShot == true) && (redBall == false))
						{

							foul();
						}
						else
						{
							// white ball was struck by yellow cueball
							whiteBall = true;
						}

					}
					else
					{
						// identifying other ball to be hit (red)
						if (ball == BallType.RED)
						{
							// red ball was struck by yellow cueball
							redBall = true;
						}
					}
				}
			}
		}

	}

	/**
	 * Indicates the given ball has impacted the given cushion. A cushion impact
	 * cannot happen before a stick strike. If this method is called before the
	 * start of a shot (i.e., cueStickStrike() is called), it should do nothing.
	 * 
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void cueBallImpactCushion()
	{

		if (cueStickStrikesCueBall)
		{
			// first shot must be breakshot and red ball
			if ((isBreakShot == true) && (redBall == false))
			{

				// foul stub
				foul();
			}
			else
			{
				// successful cushion impact, increment
				cushionImpacts = cushionImpacts + 1;

				if (cueBall == BallType.WHITE)
				{
					// bank shot logic confirmation
					if ((cushionImpacts == 3) && (yellowBall == false) && (redBall == false))
					{
						// successful bank shot logic, return true
						bankShot = true;
					}
				}

				else
				{
					// if cueball is yellow
					if (cueBall == BallType.YELLOW)
					{
						// bank shot logic confirmation
						if ((cushionImpacts == 3) && (whiteBall == false) && (redBall == false))
						{
							// successful bank shot logic, return true
							bankShot = true;
						}
					}
				}
			}
		}

	}

	/**
	 * Indicates that all balls have stopped motion. If the shot was valid and no
	 * foul was committed, the player scores 1 point. The shot cannot end before it
	 * has started with a call to cueStickStrike. If this method is called before
	 * cueStickStrike, it should be ignored.
	 * 
	 * A shot cannot end before the start of a shot. If this method is called before
	 * the start of a shot (i.e., cueStickStrike() is called), it should do nothing.
	 * 
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void endShot()
	{

		// below is only called if game already started
		if (gameBegin == true)
		{
			// shot is started and cue stick strikes
			if ((isShotStarted == true) && (cueStickStrikesCueBall == true))
			{
				// what is needed to correctly score? 2 balls must hit, >= 3 impacts, logic
				if ((isShotValid == true) && (whiteBall == true && yellowBall == true) // white yellow combo or
						|| (whiteBall == true && redBall == true)// white red combo or
						|| (yellowBall == true && redBall == true) // yellow red combo and
								&& (cushionImpacts >= 3)) // 3+ cushion impacts
				{
					if (inningPlayer == playerA)
					{
						playerAScore = playerAScore + 1; // player A scores a point
					}
					else
					{
						playerBScore = playerBScore + 1; // player B scores a point
					}
				}

				// anything other than those combinations results in a foul
				else
				{
					foul();
					bankShot = false;
				}
			}
			if ((playerAScore == pointsToWin) || (playerBScore == pointsToWin))
			{
				// game over
				gameBegin = false;
				isInningStarted = false;
			}

			// upon calling endShot method, updates all variables, all balls stopped motion
			whiteBall = false;
			yellowBall = false;
			redBall = false;
			foul = false;
			isBreakShot = false;
			isShotValid = false;
			isShotStarted = false;
			isInningStarted = false;
			cueStickStrikesCueBall = false;
		}
	}

	/**
	 * A foul immediately ends the player's inning, even if the current shot has not
	 * yet ended. When a foul is called, the player does not score a point for the
	 * shot. A foul may also be called before the inning has started. In that case
	 * the player whose turn it was to shot has their inning forfeited and the
	 * inning count is increased by one.
	 * 
	 * No foul can be called until the lag player has chosen who will break (see
	 * lagWinnerChooses()). If this method is called before the break is chosen, it
	 * should do nothing.
	 * 
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void foul()
	{
		// if foul method is called while game has begun and foul already false
		if ((gameBegin == true) && (foul == false))
		{
			// next inning procedures after legitimate foul
			isInningStarted = false; // inning over immediately, rule 3a
			foul = true;
			inning += 1; // next inning
			cushionImpacts = 0;
			isShotValid = false;

			// all ball hits are false
			whiteBall = false;
			yellowBall = false;
			redBall = false;

			// foul changes the inning player & cueball to next player
			if (inningPlayer == PlayerPosition.PLAYER_A)
			{
				if (cueBall == cueBallA)
				{
					// if player A, cueball is now player B cueball
					cueBall = cueBallB;
				}
				inningPlayer = PlayerPosition.PLAYER_B;
			}
			else
			{
				cueBall = cueBallA;
				inningPlayer = PlayerPosition.PLAYER_A;

			}
		}
	}

	/**
	 * Gets the inning number.
	 * 
	 * @return
	 */
	public int getInning()
	{
		return inning;
	}

	/**
	 * Gets the cue ball of the current player. If this method is called in between
	 * innings, the cue ball should be the for the player of the upcoming inning. If
	 * this method is called before the lag winner has chosen a cue ball, the cue
	 * ball is undefined (this method may return anything).
	 * 
	 * @return
	 */
	public BallType getCueBall()
	{
		// current cueball
		return cueBall;
	}

	/**
	 * Gets the current player.
	 * 
	 * @return
	 */
	public PlayerPosition getInningPlayer()
	{
		return inningPlayer; // current player
	}

	/**
	 * Gets the number of points scored by Player A.
	 * 
	 * @return
	 */
	public int getPlayerAScore()
	{
		return playerAScore;
	}

	/**
	 * Gets the number of points scored by Player B.
	 * 
	 * @return
	 */
	public int getPlayerBScore()
	{
		return playerBScore;
	}

	/**
	 * Returns true if and only if the most recently completed shot was a bank shot.
	 * 
	 * @return
	 */
	public boolean isBankShot()
	{
		return bankShot;
	}

	/**
	 * Returns true if and only if this is the break shot (i.e., the first shot of
	 * the game).
	 * 
	 * @return
	 */
	public boolean isBreakShot()
	{
		return isBreakShot;
	}

	/**
	 * Returns true if the game is over (i.e., one of the players has reached the
	 * designated number of points to win).
	 * 
	 * @return
	 */
	public boolean isGameOver()
	{
		// game over
		return (gameBegin == false);
	}

	/**
	 * Returns true if the shooting player has taken their first shot of the inning.
	 * 
	 * @return
	 */
	public boolean isInningStarted()
	{
		return isInningStarted;
	}

	/**
	 * 
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended
	 * (see endShot()).
	 * 
	 * @return
	 */
	public boolean isShotStarted()
	{
		return isShotStarted;
	}

	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.

	/**
	 * /** Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString()
	{
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A)
		{
			playerATurn = "*";
		}
		else
			if (getInningPlayer() == PLAYER_B)
			{
				playerBTurn = "*";
			}
		if (isInningStarted())
		{
			inningStatus = "started";
		}
		else
		{
			inningStatus = "not started";
		}
		if (isGameOver())
		{
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}

}
