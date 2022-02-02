package com.k3ewee.number;
import java.text.*;

public class RadixComplement {
	/*I want to implement radix complement to perform operations on binary values and turn them to 
	 * integers -8 to 7. It's the concept that binary values can represent both nonnegative and negative values.
	 * Basically, binary values that start with 0 are positive values, otherwise (1) negative values. 
	 * I will only use 16 bits, and use only 4 binary digits.
	 * It's a great concept because it gives you to the ability to perform subtraction with only 
	 * binary adding function */
	
	static int signCheck(int x) {
		//Having only 4 digits gives me the luxury to easily find the most significant bit 
		return x/1000; 
	}
	
	static String radIntToBinary(int i) {
		int bVal = 0, temp = i, r=0, m=1; 
		if (i > 7 || i < -8) 
			return "null"; 
		if (temp < 0) {
			temp = 16 + i; 
			while (temp!=0) {
				r = temp % 2; 
				bVal += r*m; 
				m*=10; 
				temp/=2; 
			}
		} else {
			while (temp!=0) {
				r = temp%2; 
				bVal += r*m; 
				m*=10; 
				temp/=2; 
			}
		}
		DecimalFormat df = new DecimalFormat("0000"); 
		String result = df.format(bVal); 
		return result; 
	}
	
	static int radBinaryToInt(String b) {
		int bVal = Integer.parseInt(b); 
		int temp = bVal, iVal=0; 
		if (signCheck(temp) == 1) {
			for (int i=0; i<4; i++) {
				if (temp%10 == 1) 
					iVal += Math.pow(2, i);
				temp/=10; 
			}
			iVal = -1 * (16 - iVal); 
		} else {
			for (int i=0; i<4; i++) {
				if (temp%10 == 1) 
					iVal += Math.pow(2, i);
				temp/=10; 
			}
		}
		return iVal; 
	}
	
	/*What makes radix complement so special is that it ignores bit overflow for a fixed word size
	  And even if you ignore the overflow, you will still get the correct results!
	  1111 + 1111 is the same as (-1) + (-1) = -2, add 1111 and 1111 added together 
	  would produce 1|1110, ignore overflow so it would 1110 to integer value is 14
	  The radix complement shows in this case that 14 - 16 = -2 which is true for -1-1 = -2*/
	static int radAdd(int n1, int n2) {
		//I could have just add n1 and n2 naturally but that would ruin the purpose of learning the topic
		int num1 = Integer.parseInt(radIntToBinary(n1)), num2 = Integer.parseInt(radIntToBinary(n2));
		int binaryVal=0, m=1, carry=0; 
		for (int i=1; i<=4; i++) {
			if (carry>0) 
				carry = 1; 
			int r1 = num1%10; 
			int r2 = num2%10; 
			if (r1+r2+carry == 3) {
				carry++; 
			} else if (r1+r2+carry == 2) {
				carry++; 
				binaryVal += 1*m; 
			} else if (r1+r2+carry == 1) {
				carry = 0; 
			} else {
				carry = 0; 
			}
			m*=10; 
			num1/= 10; 
			num2/= 10; 
		}		
		int result = radBinaryToInt(String.valueOf(binaryVal));
		return result; 
	}
	
	public static void main(String[] args) {
		System.out.println(radBinaryToInt("1111"));
		System.out.println(radIntToBinary(-1));
		System.out.println(radAdd(7, -4));
		System.out.println(radAdd(0, -3));
		System.out.println(radAdd(5, 2));
		System.out.println(radAdd(-2, -4));
		//The only problem with this method is we can't have sums beyond the range (-8,7) 
		System.out.println(radAdd(-8,-8));
	}
}







