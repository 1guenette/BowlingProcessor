package hw1;

/**
 * @author Samuel Guenette
 * @date 2/19/2016 Bowling game with a number of frames specified by the user.
 *       Game accounts for strikes and spares, while also updating and having
 *       the ability to return the score, roll number, frame number, and whether
 *       the game is over.
 */
public class TenPinBowling {
	/**
	 * Number of Pins to begin with
	 */
	public static final int PINS = 10;

	/**
	 * The Current frame number of the game
	 */
	private int frame;

	/**
	 * The current number of pins still standing
	 */
	private int numPins;

	/**
	 * Tracks how many times the user can roll a bowling ball per frame
	 */
	private int rollNum;

	/**
	 * Current bowling score
	 */
	private int score;

	/**
	 * Tracks if user receives bonus points from getting a strike in their last
	 * roll
	 */
	private boolean strikeBonus;

	/**
	 * Tracks if user receives bonus points from getting a spare in their last
	 * roll
	 */
	private boolean spareBonus;

	/**
	 * The number of frames in the bowling game
	 */
	private int finalFrame;

	/**
	 * Tracks if user gets an extra roll for the final frame
	 */
	private boolean addedRoll;

	/**
	 * Constructs the beginning of a bowling game at frame 1, the number of pins
	 * are 10, the roll number at 1, and the score 0,
	 * 
	 * @param howManyFrame
	 *            the number of frames for the game
	 */
	public TenPinBowling(int howManyFrames) {
		frame = 1;
		numPins = PINS;
		rollNum = 1;
		score = 0;
		strikeBonus = false;
		spareBonus = false;
		addedRoll = false;
		finalFrame = howManyFrames;
	}

	/**
	 * Returns what frame the user is on or a -1 if the game is over
	 * 
	 * @return The current frame number or -1
	 */
	public int getFrameNumber() {
		return frame;
	}

	/**
	 * Returns what roll the user is currently on in the current frame or a -1
	 * if the game is over
	 * 
	 * @return Current Roll Number
	 */
	public int getRollNumber() {
		return rollNum;
	}

	/**
	 * Returns the number of pins currently up or -1 if the game is over
	 * 
	 * @return The number of pins up or -1 if the game is over
	 */
	public int getPinsUp() {
		return numPins;
	}

	/**
	 * Returns true if the game is over or false if the game is the user still
	 * has to roll.
	 * 
	 * @return true or false based on whether the game is over
	 */
	public boolean isOver() {
		if (frame == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the current score
	 * 
	 * @return Bowling Score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Represents a roll and the knocking over of pins
	 * 
	 * @param pins
	 *            the number of pins knocked over
	 * @return void
	 */
	public void roll(int pins) {
		if (pins <= numPins) // Makes sure number of pins knocked down is valid
		{
			if (frame != finalFrame) // Conditions for all frames of game except
										// the last one
			{
				score += pins;
				numPins -= pins;

				if (spareBonus == true) // Adds spare bonus pts. from past
										// spares
				{
					score += pins;
					spareBonus = false;
				}

				if (strikeBonus == true) // Adds first of the strike bonuses
											// from past strikes
				{
					spareBonus = true; // Makes spare bonus true to add the
										// second of the two strike bonuses
					score += pins;
					strikeBonus = false;
				}

				if (numPins == 0 && rollNum == 2) // If the current roll is a
													// spare, the spare bonus is
													// updated to true
				{
					spareBonus = true;
				}

				if (numPins == 0 && rollNum == 1) // If the current roll is a
													// strike, the spare bonus
													// is updated to true
				{
					strikeBonus = true;
				}
				rollNum++; // Updates number of rolls per frame

				if (rollNum == 3 || numPins == 0) // condition that moves to
													// next frame
				{
					rollNum = 1;
					numPins = PINS;
					frame++;
				}

			}

			else if (frame == finalFrame) // FINAL FRAME CONDITIONS
			{
				score += pins;
				numPins -= pins;

				if (spareBonus == true) {
					score += pins;
					spareBonus = false;
				} // Updates bonus to score from past spares and strikes

				if (strikeBonus == true) {
					score += pins;
					spareBonus = true;
					strikeBonus = false;
				}

				if (numPins == 0 && addedRoll == false) // Makes addedRoll
														// boolean true if the
														// current roll is a
														// strike or spare
				{
					addedRoll = true;

				}

				rollNum++; // Updates roll number

				if (addedRoll == false) // Conditions if final frame without
										// bonus roll
				{
					if (rollNum == 3) {
						rollNum = -1;
						numPins = -1;
						frame = -1;
					} else if (numPins == 0) {
						numPins = PINS;
					}
				}

				if (addedRoll == true) // Conditions for final frame with bonus
										// roll
				{
					if (rollNum == 4) {
						rollNum = -1;
						numPins = -1;
						frame = -1;
					} else if (numPins == 0) {
						numPins = PINS;
					}
				}
			}
		}
	}

	/**
	 * Resets the game to the beginning
	 * 
	 * @return void
	 */
	public void reset() {
		frame = 1;
		numPins = PINS;
		rollNum = 1;
		score = 0;
	}
}