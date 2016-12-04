package pokreni.program;

import java.util.ArrayList;

import stavi.u.email.i.posalji.Email;
import ubacivanje.u.pdf.Pdf;

public class Main {

	// dodjeljivanje imena generisanom fajlu i smjestanje u pdf folder
	private static final String NAZIV_FAJLA = "pdf/TrenutnaTemperatura.pdf";

	public static void main(String[] args) throws Exception {

		Pdf pdf = new Pdf();
		// pozivanje metode za pravljenje pdf fajla
		pdf.napravitiPdf(NAZIV_FAJLA);

		// email primaoca
		//dodao sam ArrayList i za slanje mail-a na vise email adresa
		ArrayList<String> toAddress = new ArrayList<String>();
		toAddress.add("darko_ki@hotmail.com");
		toAddress.add("stana.kitic@gmail.com");
		
		// naslov emaila
		String subject = "Naslov ovog email-a";
		// poruka emaila
		String message = "Stigla je poruka..";
	
		// dodavanje fajlova za slanje
		ArrayList<String> attachFiles = new ArrayList<String>();
		attachFiles.add("pdf/TrenutnaTemperatura.pdf");

		try {
			Email.sendEmailWithAttachments(message, subject, toAddress, attachFiles);
			// umjesto System.out. koristiti Log4J, dakle sve ispise zamjeniti sa logovanjem u neki fajl
			System.out.println("Email uspjesno poslat..");
		} catch (Exception e) {
			System.out.println("Nije moguce poslati e-mail..");
			e.printStackTrace();
		}

	}

}
