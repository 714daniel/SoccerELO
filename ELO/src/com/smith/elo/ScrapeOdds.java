package com.smith.elo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class ScrapeOdds {

	public static void main(String[] args) throws IOException {
		ArrayList<MatchOdds> m = scrape538();
		System.out.println("scraped 538");
		// scrapeOddsPortal();
		ArrayList<Double> d = readOddsPortalFromFile();
		System.out.println("scraped file");
		KellyCriterion kc = new KellyCriterion(5000);

		for (MatchOdds i : m) { // System.out.println("started loop"); //
			 System.out.println(kc.br);
			if (d.size() > 0) {
				System.out.printf("%d %d %d %f %f %f",i.odds1,i.odds2,i.oddsT, d.remove(0), d.remove(0), d.remove(0));
				// kc.evaluate(i, d.remove(0), d.remove(0), d.remove(0));
			}
		}
		// }

		// System.out.println(100.0 / (670 + 100));
	}

	public static ArrayList<MatchOdds> scrape538() throws IOException {
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(new File("E:\\output.txt"))));
		Document d = Jsoup.connect("https://projects.fivethirtyeight.com/soccer-predictions/premier-league/").get();
		// System.out.println(d);
		Element tournTable = d.getElementById("matches");
		Elements els = tournTable.getElementsByClass("team");
		ArrayList<String> teams = new ArrayList<String>();
		ArrayList<Integer> odds = new ArrayList<Integer>();
		ArrayList<MatchOdds> games = new ArrayList<MatchOdds>();
		ArrayList<String> didTeamWin = new ArrayList<String>();
		for (Element x : els) {
			// System.out.println(x.attr("data-str"));
			teams.add(x.attr("data-str"));
			didTeamWin.add((x.attr("style")));
			// System.out.println(x.attr("style"));
		}
		Elements els2 = tournTable.getElementsByClass("prob");
		for (Element x : els2) {
			odds.add(Integer.parseInt(x.text().substring(0, x.text().indexOf("%"))));
		}
		p.close();
		while (teams.size() > 0) {
			//System.out.println(didTeamWin.get(0));
			if (!didTeamWin.get(0).equals("")) {
			//	System.out.println("pregame");
				games.add(new MatchOdds(teams.remove(0), teams.remove(0), odds.remove(0), odds.remove(0),
						odds.remove(0), didTeamWin.remove(0), didTeamWin.remove(0)));
			//	System.out.println(games.get(games.size() - 1));
			} else {
				teams.remove(0);
				didTeamWin.remove(0);
			}
		}
		return new ArrayList<MatchOdds>(games.subList(13, games.size()));
	}

	public static void scrapeOddsPortal() throws IOException {
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(new File("E:\\output.txt"))));
		Document e = Jsoup.connect("http://www.oddsportal.com/soccer/england/premier-league/results/").maxBodySize(0)
				.timeout(600000)
				.userAgent(
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
				.get();
		System.out.println(e.getElementById("tournamentTable").children());
		// p.write("test");
		p.write(e.toString());

		p.close();
		Elements temp = e.getElementsByClass("result-ok odds-nowrp in-coupon");
		// System.out.println(temp);
	}

	public static ArrayList<Double> readOddsPortalFromFile() {
		File input = new File("E:\\Python Stuff\\output.html");
		try {
			Document doc = Jsoup.parse(input, "UTF-8",
					"http://www.oddsportal.com/soccer/england/premier-league/results/");
			// System.out.println(doc.getElementById("tournamentTable").children());
			String[] odds = (doc.getElementsByAttribute("xparam").text()).split(" ");
			ArrayList<Double> oddsP = new ArrayList<Double>();
			// System.out.println(Arrays.toString(odds));

			for (int i = 0; i < odds.length; i++) {
				if (!odds[i].toLowerCase().contains("s")) {
					// System.out.println(100.0 / (Integer.parseInt(odds[i]) + 100));
					double t = Integer.parseInt(odds[i]);
					// System.out.println(t);
					if (t > 0) {
						oddsP.add(100.0 / (t + 100.0));
					} else {
						oddsP.add(-t / (-t + 100.0));
					}
					// System.out.println(odds[i]);

				}
			}
			ArrayList<Double> a = new ArrayList<Double>();
			a.add(0.0);
			oddsP.removeAll(a);
			/*
			 * for(double d:oddsP) { System.out.println(d); }
			 */
			return oddsP;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Double>();
		}
	}
}
