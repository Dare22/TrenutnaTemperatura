package ubacivanje.u.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import uzimanje.temp.TrTemp;

public class Pdf extends TrTemp {

	public static final String nazivFajla = "pdf/TrenutnaTemperatura.pdf"; // dodjeljivanje imena generisanom fajlu i smjestanje u pdf folder

	// pravljenje pdf dokumenta
	public void napravitiPdf(String ime) throws DocumentException, IOException {

		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream(ime)); // dodaje elemente u pdf dokument

		document.open();

		Font fontNaslov = FontFactory.getFont("Times-Roman", 18, Font.BOLD); // font za ispis temperatura
		Font font = FontFactory.getFont("Times-Roman", 14); // font za naslov

		Paragraph paragraph = new Paragraph();

		paragraph = new Paragraph(" Trenutna temperatura u gradovima ", fontNaslov);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);

		paragraph = new Paragraph("\n");
		document.add(paragraph);

		paragraph = new Paragraph("1. Beograd: \n" + uzetiTrTemp("https://www.timeanddate.com/weather/serbia/belgrade"),
				font);
		document.add(paragraph); // pozivanje metode sa url-om za beograd

		paragraph = new Paragraph(
				"2. Banja Luka: \n" + uzetiTrTemp("https://www.timeanddate.com/weather/bosnia-herzegovina/banja-luka"),
				font);
		document.add(paragraph); // pozivanje metode sa url-om za banjaluku

		paragraph = new Paragraph(
				"3. Sarajevo: \n" + uzetiTrTemp("https://www.timeanddate.com/weather/bosnia-herzegovina/sarajevo"),
				font);
		document.add(paragraph); // pozivanje metode sa url-om za sarajevo

		document.close();
	}

}
