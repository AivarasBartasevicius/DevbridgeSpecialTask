package Task;

import java.util.Scanner;

public class MagicDetector {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input a number: ");
		if (isMagicalNumber(input.nextInt())) {
		    System.out.println("It's magic!"); 
		}
		else {
		    System.out.println("It's not magic..."); 
		}
		input.close();
	}
	
	static public boolean isMagicalNumber(int number) {
		int count = 0;
		boolean hasSameDigits = true;
		int lastDigit = number % 10;
		
				int num = number;
		while(num != 0) {
			num = num / 10;
			if(num % 10 != lastDigit) {
				hasSameDigits = false;
			}
			count++;
		}
		
		//Single digits, e.g.: 5 are not magical
		if(count < 1) {
			return false;
		}
		
		//repeated digits, e.g.: 555 are not magical
		if(hasSameDigits) {
			return false;
		}
		
		//Repeated cyclic numbers, e.g.: 142857142857 are not magical
		if(count % 2 == 0) {
			int firstHalf = number / (int)(Math.pow(10, (count/2)));
			int secondHalf = number - firstHalf * (int)(Math.pow(10, (count/2)));
			if(firstHalf == secondHalf && isMagicalNumber(firstHalf)) {
				return false;
			}
		}
		
		//Check if the number is magical by going through all of it's variations and seeing 
		//and checking if all it's variations have a multiplier without a remainder
		num = number;
		for(int i = 0; i < count-1; i++) {
			lastDigit = num % 10;
			int firstDigits = num / 10;
			//Move the last symbol to the beginning of the number
			num = (int)(Math.pow(10, count-1) * lastDigit + firstDigits );
			
			if(num % number != 0) {
				return false;
			}
		}
		
		return true;
	}
}

