package com.k3ewee.number;
import java.text.*;

public class RadixComplement {
	
	static int signCheck(int x) {
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
	
	static int radAdd(int n1, int n2) {
		int num1 = Integer.parseInt(radIntToBinary(n1)), num2 = Integer.parseInt(radIntToBinary(n2));
		int binaryVal=0, m=1, carry=0; 
		for (int i=1; i<=4; i++) {
			if (carry>0) {
				carry = 1;
			}
			int r1 = num1%10; 
			int r2 = num2%10; 
			if (r1+r2+carry == 3) {
				binaryVal += 1*m; 
				carry++; 
			} else if (r1+r2+carry == 2) {
				carry++; 
			} else if (r1+r2+carry == 1) {
				binaryVal += 1*m; 
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
		System.out.println("Radix complement makes binary adding very important in every computing systems, because we can perform"
				+ " both addition and subtraction\nat the same time for n-Bits. This is for 16 bits, only accurate to sums within "
				+ "the range (-8, 7)");
		System.out.println("");
		for (int i=-8; i<=7; i+=15) {
			for (int j=-8; j<8; j++) {
				System.out.println(i + " + " + j + " or " + radIntToBinary(i) + " + " + radIntToBinary(j) + " = " + radAdd(i, j) + 
						" correct answer: " + (i+j));
			}
			System.out.println(); 
		}
	}
}
