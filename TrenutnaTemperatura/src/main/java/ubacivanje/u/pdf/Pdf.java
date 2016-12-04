package ubacivanje.u.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import uzimanje.temp.TrTemp;

public class Pdf {

	private Document document = new Document();

	// metoda za paragrafe sa imenom grada i url-om
	public Paragraph paragrafTemp(String imeGradaIurl) throws DocumentException {
		// ovdje dodati da se font i njegova velicina uzimaju iz properties fajla, na taj nacin izgled PDF dokumenta moze da se konfigurise bez mjenjanja koda
		Paragraph paragraph = new Paragraph(imeGradaIurl, FontFactory.getFont("Times-Roman", 14));
		document.add(paragraph);
		return paragraph;

	}

	// metoda za naslov
	public Paragraph paragrafNaslov(String naslov) throws DocumentException {
		// isto kao i gore 
		Paragraph paragraph = new Paragraph(naslov, FontFactory.getFont("Times-Roman", 18, Font.BOLD));
		// dodati alignment u properties fajl
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		return paragraph;

	}

	// pravljenje pdf dokumenta
	public void napravitiPdf(String ime) throws DocumentException, IOException {

		// dodaje elemente u pdf dokument
		PdfWriter.getInstance(document, new FileOutputStream(ime));

		document.open();

		paragrafNaslov("Trenutne temperature u gradovima");

		paragrafTemp("\n 1. Beograd: " + TrTemp.uzetiTrTemp("https://www.timeanddate.com/weather/serbia/belgrade"));

		paragrafTemp("2. BanjaLuka: " + TrTemp.uzetiTrTemp("https://www.timeanddate.com/weather/bosnia-herzegovina/banja-luka"));

		paragrafTemp("3. Sarajevo: " + TrTemp.uzetiTrTemp("https://www.timeanddate.com/weather/bosnia-herzegovina/sarajevo"));

		document.close();
	}

}
