package com.smith.elo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.*;

public class Test {

	public static void main(String[] args) {
		URL url;
		HttpURLConnection conn;
		int responseCode;
		try {
			url = new URL("https://www.football-data.org/v1/competitions/451/fixtures");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responseCode = conn.getResponseCode();
			Scanner sc = new Scanner(conn.getInputStream());e
			if(responseCode != 200)
				throw new RuntimeException("HttpResponseCode: " +responseCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
