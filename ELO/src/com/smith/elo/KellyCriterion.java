package com.smith.elo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class KellyCriterion {
	double br;
	public KellyCriterion(double br) {
		this.br = br;
	}
	public double evaluate(MatchOdds real, MatchOdds fake) {
		return br;
		
	}
}
