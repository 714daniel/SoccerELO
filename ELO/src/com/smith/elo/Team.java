package com.smith.elo;

public class Team {
	private String name;
	private double rating = 2000;

	public Team(String name) {
		// TODO Auto-generated constructor stub
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double d) {
		this.rating += d;
	}

	public static double getExpectedOutcome(Team a, Team b) {
		double A = b.getRating() - a.getRating();
		A = A / 400.0;
		A = Math.pow(10, A);
		A = 1 + A;
		A = 1 / A;

		return A;
	}

	public static void win(Team a, Team b) {
		double dr = 30 * (1 - getExpectedOutcome(a, b));
		a.setRating(dr);
		b.setRating(-1 * dr);
	}

	public static void tie(Team a, Team b) {
		double dr = 30 * (0.5 - getExpectedOutcome(a, b));
		a.setRating(dr);
		b.setRating(-1 * dr);
	}

}
