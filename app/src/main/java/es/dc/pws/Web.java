package es.dc.pws;

import android.util.Log;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Web {

	private String url = "https://www.wunderground.com/dashboard/pws/ISOTOY2";

	public Web()
	{
		Log.d("UNO","kjashfñpklasjdhfpoauihfioadldusdhfoiausdfghyiausdbgfalisoduyfgi");
	}

	public double getTemperature()  {

		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = document.body()
				.select(".main-temp > lib-display-unit:nth-child(1) > span:nth-child(1) > span:nth-child(1)").get(0)
				.text();
		System.out.println(value);


		double faren = Float.parseFloat(value);
		double celsius = (faren - 32) * 5 / 9;
		System.out.println(celsius + " ºC");
		double parteEntera, resultado;
		resultado = celsius;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, 2);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, 2)) + parteEntera;
		System.out.println(resultado);
		return resultado;
	}

	public double getHumidity(){

		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = document.body()
				.select("div.medium-4:nth-child(4) > div:nth-child(1) > div:nth-child(2) > lib-display-unit:nth-child(1) > span:nth-child(1) > span:nth-child(1)").get(0)
				.text();


		return Double.valueOf(value);

	}

	public String getWind()  {

		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = document.body()
				.select(".text-bold").get(0)
				.text();

		return value;

	}

	public float getWindSpeed()  {

		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = document.body()
				.select(".big > lib-display-unit:nth-child(1) > span:nth-child(1) > span:nth-child(1)").get(0)
				.text();

		return Float.valueOf(value) * 3.6f;//Lo pasamos de mps a kh

	}






}
