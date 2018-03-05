package com.smith.elo;

public class MatchOdds {
	String t1, t2;
	int odds1, oddsT, odds2;

	public MatchOdds(String t1, String t2, int odds1, int oddsT, int odds2) {
		this.t1 = t1;
		this.t2 = t2;
		this.odds1 = odds1;
		this.odds2 = odds2;
		this.oddsT = oddsT;
	}
	public String toString() {
		return t1 + " " + t2 + " " + odds1 + " " + oddsT + " " + odds2;
	}
	
	
}
