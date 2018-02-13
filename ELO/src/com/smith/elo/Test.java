package com.smith.elo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.*;

public class Test {

	public static void main(String[] args) {
		Map<String, Team> teams = new HashMap<String, Team>();
		String s = "https://www.football-data.org/v1/competitions/451/fixtures?season=2016";
		addURLToData(s, teams);
		s = "https://www.football-data.org/v1/competitions/451/fixtures?season=2017";
		addURLToData(s, teams);
		s = "https://www.football-data.org/v1/competitions/451/fixtures?season=2018";
		addURLToData(s, teams);
		
		for(String name : teams.keySet()) {
			System.out.println(teams.get(name));
		}
		
	}
	public static void addURLToData(String s, Map<String, Team> teams) {
		JSONObject o = null;
		URL url;
		HttpURLConnection conn;
		int responseCode;
		try {
			url = new URL(s);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responseCode = conn.getResponseCode();
			InputStream i = conn.getInputStream();
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();

			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);
			o = new JSONObject(responseStrBuilder.toString());

			if (responseCode != 200)
				throw new RuntimeException("HttpResponseCode: " + responseCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(o.get("fixtures"));
			JSONArray j = o.getJSONArray("fixtures");
			for (int i = 0; i < j.length(); i++) {
				String t1 = j.getJSONObject(i).getString("homeTeamName");
				String t2 = j.getJSONObject(i).getString("awayTeamName");
				teams.putIfAbsent(t1, new Team(t1));
				teams.putIfAbsent(t2, new Team(t2));
			}
			// System.out.println(j.getJSONObject(0).getJSONObject("result"));
			System.out.println(teams);
			for (int i = 0; i < j.length(); i++) {
				String tn1 = j.getJSONObject(i).getString("homeTeamName");
				String tn2 = j.getJSONObject(i).getString("awayTeamName");
				Team t1 = teams.get(tn1);
				Team t2 = teams.get(tn2);
				// if(j.get)
				if (j.getJSONObject(i).getString("status").equals("FINISHED")) {
					Object ji1 = j.getJSONObject(i).getJSONObject("result").get("goalsHomeTeam");
					Object ji2 = j.getJSONObject(i).getJSONObject("result").get("goalsAwayTeam");
					int g1 = Integer.parseInt(ji1.toString());
					int g2 = Integer.parseInt(ji2.toString());
					Fixture.sim(t1, t2, g1, g2);

				}
				// System.out.println(Integer.parseInt(ji.toString()));
				// int g2 = j.getJSONObject(i).getJSONObject("result").getInt("goalsAwayTeam");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
