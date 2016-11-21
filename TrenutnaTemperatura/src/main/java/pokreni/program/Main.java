package pokreni.program;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import stavi.u.email.i.posalji.Email;
import ubacivanje.u.pdf.Pdf;

public class Main extends Pdf {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws DocumentException, IOException {

		Pdf pdf = new Pdf();

		pdf.napravitiPdf(nazivFajla);// pozivanje metode za pravljenje pdf fajla

		Email email = new Email();
		// argumenti za slanje e-maila
		String host = "smtp.gmail.com"; // (SMTP) Server za slanje maila
		String port = "587"; 
		String userName = "daregm.dk@gmail.com"; // unijeti gmail korisnicko ime posiljaoca
		String password = "lozinka"; 

		String toAddress = "darko_ki@hotmail.com"; // email primaoca
		String subject = "Naslov ovog email-a";
		String message = "Stigla je poruka..";
		// dodavanje fajla za slanje
		String[] attachFiles = new String[1];
		attachFiles[0] = "C:/Users/Darko/EclipseWork/TrenutnaTemperatura/pdf/TrenutnaTemperatura.pdf";

		try {
			email.sendEmailWithAttachments(host, port, userName, password, toAddress, subject, message, attachFiles);
			System.out.println("Email uspjesno poslat..");
		} catch (Exception e) {
			System.out.println("Nije moguce poslati e-mail..");
			e.printStackTrace();
		}

	}

}
