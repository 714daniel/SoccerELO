package com.smith.elo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class KellyCriterion {
	double br;

	public KellyCriterion(double br) {
		this.br = br;
	}

	public Double evaluate(MatchOdds m, double bet1, double betT, double bet2) {
		System.out.printf("percentages: %f %f %f \n", bet1, betT, bet2);
		if (m.t1c.equals("#98d398")) {
			evaluate(m, bet1, betT, bet2, 1);
		} else if (m.t2c.equals("#98d398")) {
			evaluate(m, bet1, betT, bet2, 2);
		} else {
			evaluate(m, bet1, betT, bet2, 3);
		}
		return bet2;

	}

	public void evaluate(MatchOdds real, double d1, double dt, double d2, double outcome) {
		System.out.println(br);
		double p1 = (1 / d1) - 1;
		double r1 = (double) real.odds1 / 100.0;
		double p2 = (1 / d2) - 1;
		double r2 = (double) real.odds2 / 100.0;
		double pt = (1 / dt) - 1;
		double rt = (double) real.oddsT/ 100.0;
		double bet1 = br * (p1 * (r1) - (1 - r1)) / p1;
		double betT = br * (pt * (rt) - (1 - rt)) / pt;
		double bet2 = br * (p2 * (r2) - (1 - r2)) / p2;
		System.out.printf("real odds: %f %f %f \n", (double) real.odds1/ 100.0, (double) real.oddsT/ 100.0, (double) real.odds2/ 100.0);
		System.out.printf("bets: %f %f %f \n", bet1, betT, bet2);
		if (bet1 > betT && bet1 > bet2 && d1 < r1) {
			System.out.printf("Suggested bet: %f on %s", bet1, real.t1);
			if (outcome == 1) {
				br += bet1;
			} else {
				br -= bet1;
			}
		} else if (betT > bet1 && betT > bet2 && dt < rt) {
			System.out.printf("Suggested bet: %f on %s", betT, "tie");
			if (outcome == 2) {
				br += bet2;
			} else {
				br -= bet2;
			}
		} else if (bet2 > bet1 && bet2 > betT && d2 < r2) {
			System.out.printf("Nums: predicted: %f, real: %f", p2, r2);
			System.out.printf("Suggested bet: %f on %s \n", bet2, real.t2);
			if (outcome == 3) {
				br += betT;
			} else {
				br -= betT;
			}
		}else {
			System.out.println("No bet to be made");
		}
		System.out.println("New Bankroll:" + br);

	}
}
