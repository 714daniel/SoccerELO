package com.smith.elo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class KellyCriterion {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("C:\\Users\\714da\\git\\SoccerELO\\ELO\\src\\com\\smith\\elo\\input.txt"));
		PrintStream pr = new PrintStream(
				new File("C:\\Users\\714da\\git\\SoccerELO\\ELO\\src\\com\\smith\\elo\\output.txt"));

		double i = sc.nextInt();
		while (sc.hasNextInt()) {
			double p = (sc.nextInt()) / 100.0;
			double q = 1 - p;
			int american = sc.nextInt();
			double b = 0.0;
			if (american > 0) {
				b = ((american) / 100.0);
			} else {
				b = (100.0 / (-1*american));
			}
			double amount = i * ((b * p - q) / b);
			i+=i * amount;	
			 System.out.println(i);
		}
	}

}
