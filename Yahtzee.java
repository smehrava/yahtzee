
public class Yahtzee {
	private Dice[] dice ;
	
	// dice constructor, giving the dice 5 arrays because we want to roll the dices 5 times.
	//using a for loop because we want to roll the dice for 5 times.
	public Yahtzee() {
		dice = new Dice[5];
		// using dice.length because we need to roll as long as we still have indexes available in our dice variable.
		//we should indicate i is an integer because its going to be like this: 1,2,3,4,5,...
		for(int i=0; i<dice.length; i++) {
			dice[i] = new Dice();
			dice[i].roll();
		}
	}
	//constructor: setting the array of dice with 5 indexes to dice.
	public Yahtzee(Dice[] dice) {
		this.dice = dice;
	}
	// this one is for further implementations (when we want to sum all of the values of 5 dices in getScoreOptions)
	//using the getValue() method from dice class to get value of each dice being rolled.
	public int SumOfDices() {
		return dice[0].getValue() + dice[1].getValue() + dice[2].getValue() + dice[3].getValue() + dice[4].getValue();
	}
	// figuring how many times each number has been the number on top of the dice (numbers 1 to 6)
	public int[] getValueCount() {
		//making an integer array named counter because its job is to count how many of each number have been called in these 5 dices.
		int[] counter = new int [6];
		int i;
		//using a for loop because we should iterate over 5 dices so we need to do this implementation for 5 times(i=0 to i<dice.length) (dice.length = 5)
		for (i =0; i < dice.length; i++) {
			// whenever our loops reaches number 1, it will +1 counter[0]
			//by default all indexes in counter have the value zero(0) before our iteration starts.
			if (dice[i].getValue()==1){
				counter[0] ++;
			}
			// whenever our loop reaches number 2, it will +1 counter[1]
			if(dice[i].getValue()==2) {
				counter[1]++;
			}
			//whenever our loop reaches number3, it will +1 counter[2]
			if(dice[i].getValue()==3) {
				counter[2]++;
			}
			//whenever our loop reaches number4, it will +1 counter[3]
			if(dice[i].getValue()==4) {
				counter[3]++;
			}
			//whenever our loop reaches number5, it will +1 counter[4]
			if(dice[i].getValue()==5) {
				counter[4]++;
			}
			//whenever our loop reaches number 6, it will +1 counter[5]
			if(dice[i].getValue()==6) {
				counter[5]++;
			}
			
		}
		//returning counter is mandatory fr this method
		return counter;
	}
	// now we should get the scores which were indicated on the Yahtzee score sheet.
	public int[] getScoreOptions() {
		int i;
		// making a list with 13 arrays for the scores which are indicated in the Yahtzee score sheet.
		int[] allPossibleScores = new int[13];
		//calling getValuecount() because we need the list counter that we made previously
		int[] counter = getValueCount();
		// indexes 0-6 are just gotten by multiplying the number on the dice * number of times it has been on the 5 dices.(this is exactly the counter list that we made)
		allPossibleScores[0]=counter[0]*1;
		allPossibleScores[1] = counter[1]*2;
		allPossibleScores[2] = counter[2]*3;
		allPossibleScores[3] = counter[3]*4;
		allPossibleScores[4] = counter[4]*5;
		allPossibleScores[5] = counter[5]*6;
		// the last score is summing all the numbers of the dices. 
		allPossibleScores[12] = SumOfDices();
		
		//for 3 of a kind we have to have at least 3 dice showing the same value
		// so we can have 3 dices showing the same value/4 dices showing the same value and 5 dices showing the same value.
		//so the counter[i] has to be greater than or equal to 3 to satisfy this equation.
		for (i = 0; i < counter.length; i++) {
			if (counter[i] >= 3) {
				allPossibleScores[6] = SumOfDices();
			}
			// for full house we have to have a 3 of a kind and a pair so out counter has to have one index which its value is 3 and another one which its value is 2.
			// so we have 2 for loops(one is above and one is below)
			// first loop determines if we have any index in counter that has the value 3(meaning that its been called three times)
			//second loop which is a loop inside the first loop(nested loop) determines if we have an index in counter which has value 2(meaning that its been called twice)
			if (counter[i]==3) {
				for(int y=0; y<counter.length; y++) {
					if (counter[y] ==2){
						allPossibleScores[8] = 25;
					}
				}
			}
			//4 of a kind which means we can have 4 dices with the same value or 5 dices with the same value.
			if (counter[i] >= 4) {
				allPossibleScores[7] = SumOfDices();
			}
		}
		//large straight: we should make sure that each dice has been called just once because the values are different and consecutive
		//the possibilities that we have are {1,2,3,4,5} (the order can be different) / {2,3,4,5,6}(again the order can be different)
		if ((counter[0] == 1 && counter[1] == 1 && counter[2] == 1 && counter[3] == 1 && counter[4] == 1) ||
				(counter[1] == 1 && counter[2] == 1 && counter[3] ==1 && counter[4] == 1 && counter[5] == 1)) {
			allPossibleScores[10] = 40;
		}
		//small straight: we have to make sure that we have at least 4 different values for the dices(thats why we use and &&)
		//we use and(&&) instead of or(||) because it has to be 4 different values at least
		//counter shows us how many times each number has been called so we use counter
		//the possibilities we have are:{1,2,3,4,whatever},{2,3,4,5,whatever},{3,4,5,6,whatever}
		if ((counter[0]>=1 && counter[1]>=1 && counter[2]>=1 && counter[3]>=1) ||
				(counter[1]>=1 && counter[2]>=1 && counter[3]>=1 && counter[4]>=1) ||
				(counter[2]>=1 && counter[3]>=1 && counter[4]>=1 && counter[5]>=1)){
			allPossibleScores[9] = 30;
		}
		//Yahtzee: all 5 dices are showing the same value
		//we use getValue method because we want to check if the values are the same
		if (dice[0].getValue() == dice[1].getValue() && 
				dice[2].getValue() == dice[1].getValue() &&
				dice[3].getValue() == dice[2].getValue() &&
				dice[4].getValue() == dice[3].getValue()) {
			allPossibleScores[11] = 50;
		}
		return allPossibleScores;
	}
	//we need to find the maximum value from previous array(allPossibleScores array)
	public int[] score() {
		//making a new list for two values we want to put in there.
		int[] twoValueArray = new int[2];
		//calling getScoreOptions()
		int[] allPossibleScores = getScoreOptions();
		// initializing the max value to be the first value in the list(allPossibleScores)
		int max = allPossibleScores[0];
		int i;
		// initializing the index of the max value to be the first index in the list(allPossibleScores)
		int maxIndex = 0;
		// making a loop which iterates over all values in the list(allPossibleScores) and gives us the maximum value and its index.
		for (i = 0; i < allPossibleScores.length; i++) {
			if (allPossibleScores[i]>max) {
				max = allPossibleScores[i];
				maxIndex = i;
			}
		}
		//setting max value to the first index of our new list
		twoValueArray[0] = max;
		// setting the index of the max value to the second index of our new list.
		twoValueArray[1] = maxIndex;
		return twoValueArray;
	}
	public boolean equals(Yahtzee other) {
		int i;
		int[] counter = getValueCount();
		// made this boolean variable because we have to return true or false in the loop and then once more for our method
		boolean state = true;
		//we use counter because we want to see how many times each number has been called in this and other.
		//with this for loop, we will find how many times each number has been called for this and other(1,2,3,4,5,6)
		// the method of implementation is that for example we will see how many times number 1 has been called in this and how many times, 1 has been called in other.
		// the we do the same thing for 2,3,4,5,6 respectively.
		for(i =0;i<counter.length; i++) {
			if (this.getValueCount()[i] == other.getValueCount()[i]) {
				// if the vales are the same in this and other, it will return true
				return state = true;
			}else {
				// if the values are not the same in this and other, it should return false
				return state = false;
			}
		}
		return state;
	}
	// toString method returns "Dice: { numbers of the five dices}"
	public String toString() {
		return "Dice: {" + dice[0].getValue() + ", " + dice[1].getValue() + ", " + dice[2].getValue() + ", " + dice[3].getValue() + ", " + dice[4].getValue() + "}";
	}
	
}
