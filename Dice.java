
public class Dice {
	private int value;
	
	// initializing value with -1
	public Dice() {
		this.value = -1;
	}
	// setting amount of value to variable value.
	public Dice(int value) {
		this.value = value;
	}
	// using the given RandomNumber class for rolling the dice and getting a random number between min=1 and max=6
	public void roll() {
		RandomNumber number = new RandomNumber();
		this.value = number.getRandomNumber(1,6);
	}
	//returning the value from rolling the dice
	public int getValue() {
		return value;
	}
}
