package com.smith.elo;

import java.util.ArrayList;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class ScrapeOdds {

	public static void main(String[] args) {
		try {
			/*
			 * Document d = Jsoup.connect(
			 * "https://projects.fivethirtyeight.com/soccer-predictions/premier-league/").
			 * get(); //System.out.println(d); Element tournTable =
			 * d.getElementById("matches"); Elements els =
			 * tournTable.getElementsByClass("team"); ArrayList<String> teams = new
			 * ArrayList<String>(); ArrayList<String> odds = new ArrayList<String>();
			 * for(Element x : els) { // System.out.println(x.attr("data-str")); } Elements
			 * els2 = tournTable.getElementsByClass("prob"); for(Element x : els2) {
			 * System.out.println(x.text()); }
			 */
			Document d = Jsoup.connect("http://www.oddsportal.com/soccer/england/premier-league/results/").get();
			Elements temp = d.getElementsByClass("result-ok odds-nowrp in-coupon");
			System.out.println(temp);
		} catch (Exception e) {
			System.out.println("dnmsaiopdnasm");
		}

	}

}
