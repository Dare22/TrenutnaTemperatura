package uzimanje.temp;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TrTemp {

	public static String uzetiTrTemp(String url) throws IOException { // metoda za uzimanje trenutne temperature

		Document document = Jsoup.connect(url).get();
		String vrijeme = document.body().select("#qlook" /* css selector */).get(0).text();
		System.out.println(vrijeme);

		return vrijeme;
	}

}
