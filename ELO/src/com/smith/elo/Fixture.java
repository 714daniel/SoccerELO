package com.smith.elo;

public class Fixture {

	public static void sim(Team home, Team away, int homeScore, int awayScore ) {
		if(homeScore == awayScore) {
			Team.tie(home, away);
		}
		else if(homeScore > awayScore) {
			Team.win(home, away);
		}
		else if(awayScore > homeScore ) {
			Team.win(away, home);
		}
	}

}
