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

	Document document = new Document();

	// metoda za paragrafe sa imenom grada i url-om
	public Paragraph paragrafTemp(String imeGradaIurl) throws DocumentException {

		// font za paragrafe sa ispisom trenutnog vremena
		Font font = FontFactory.getFont("Times-Roman", 14);
		Paragraph paragraph = new Paragraph(imeGradaIurl, font);
		document.add(paragraph);
		return paragraph;

	}

	// metoda za naslov
	public Paragraph paragrafNaslov(String naslov) throws DocumentException {

		// font za naslov
		Font fontNaslov = FontFactory.getFont("Times-Roman", 18, Font.BOLD);
		Paragraph paragraph = new Paragraph(naslov, fontNaslov);
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
