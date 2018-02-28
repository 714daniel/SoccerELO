package com.smith.elo;

public class MatchOdds {
	String t1, t2;
	int odds1, oddsT, odds2;

	public MatchOdds(String t1, String t2, String odds1, String oddsT, String odds2) {
		this.t1 = t1;
		this.t2 = t2;
		this.odds1 = Integer.parseInt(odds1.substring(0, 2));
		this.odds2 = Integer.parseInt(odds2.substring(0, 2));
		this.oddsT = Integer.parseInt(oddsT.substring(0, 2));
	}
	
	
}
