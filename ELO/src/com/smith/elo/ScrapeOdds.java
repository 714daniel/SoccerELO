package com.smith.elo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class ScrapeOdds {

	public static void main(String[] args) {
		try {
			PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(new File("E:\\output.txt"))));
			Document e = Jsoup.connect("http://www.oddsportal.com/soccer/england/premier-league/results/")
					.maxBodySize(0).timeout(600000).header("Accept-Encoding", "gzip, deflate")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36").get();
		//	System.out.println(e.getElementById("tournamentTable"));
			Elements temp = e.getElementsByClass("result-ok odds-nowrp in-coupon");
			//System.out.println(temp);
			scrape538();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void scrape538() throws IOException {
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(new File("E:\\output.txt"))));
		Document d = Jsoup.connect("https://projects.fivethirtyeight.com/soccer-predictions/premier-league/").get();
		// System.out.println(d);
		Element tournTable = d.getElementById("matches");
		Elements els = tournTable.getElementsByClass("team");
		ArrayList<String> teams = new ArrayList<String>();
		ArrayList<Integer> odds = new ArrayList<Integer>();
		ArrayList<MatchOdds> games = new ArrayList<MatchOdds>();
		for (Element x : els) {
			teams.add(x.attr("data-str"));
		}
		Elements els2 = tournTable.getElementsByClass("prob");
		for (Element x : els2) {
			odds.add(Integer.parseInt(x.text().substring(0, x.text().indexOf("%"))));
		}
		p.close();
		while(teams.size() > 0) {
			games.add(new MatchOdds(teams.remove(0), teams.remove(0), odds.remove(0), odds.remove(0), odds.remove(0)));
		}
	}
}
