import com.rometools.rome.feed.synd.SyndEntry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Notizia extends Fonte {

    protected ArrayList <SyndEntry> notizie;
    protected int voto;
    protected SyndEntry entry;
    public Notizia(String sourceURL, String nomeFonte) {
        super(sourceURL, nomeFonte);
        this.notizie = new ArrayList<>();

    }

    public SyndEntry getEntry() {
        return entry;
    }

    public void setEntry(SyndEntry entry) {
        this.entry = entry;
    }
    public void avviaNotizia(/*Utente ut*/) {


            try {



                BufferedWriter bw = new BufferedWriter(super.fileScrit);
                PrintWriter fileTesto = new PrintWriter(bw);



                System.out.println("\n[" + entry.getPublishedDate() + "] ");
                System.out.println(entry.getTitle());
                System.out.println(entry.getLink());
                fileTesto.println("Notizia singola scelta da utente"/*+(String)ut.getName()*/);
                fileTesto.println("[" + entry.getPublishedDate() + "] ");
                fileTesto.println(entry.getTitle());
                fileTesto.println(entry.getLink());


                fileTesto.close();
                bw.close();
                super.fileScrit.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
/*  Non è necessaria,riporta solo l'ultima notizia di una fonte

  public void avviaUltimaNotizia() {

        try {
            URL feedUrl = new URL(sourceURL);

            SyndFeedInput input = new SyndFeedInput();

            try {
                SyndFeed feed = input.build(new InputSource(feedUrl.openStream()));

                List<SyndEntry> entries = feed.getEntries();
                BufferedWriter bw = new BufferedWriter(super.fileScrit);
                PrintWriter fileTesto = new PrintWriter(bw);


                Iterator<SyndEntry> itEntries = entries.iterator();

                entry = itEntries.next();
                System.out.println("\n[" + entry.getPublishedDate() + "] ");
                System.out.println(entry.getTitle());
                System.out.println(entry.getLink());

                fileTesto.println("[" + entry.getPublishedDate() + "] ");
                fileTesto.println(entry.getTitle());
                fileTesto.println(entry.getLink());


                fileTesto.close();
                bw.close();
                super.fileScrit.close();



            } catch (IllegalArgumentException | FeedException | IOException e) {
                // Errore lettura feed
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            // Errore indirizzo e accesso ai feed
            e.printStackTrace();
        }

    }
*/


    public void commentaNotizia(/*Utente ut,*/int i){
        System.out.println("inserisci commento");
        Scanner tastiera = new Scanner(System.in);
        //
        String commento=/*(String)ut.getName()+*/tastiera.nextLine();
        notizie.get(i).setComments(commento);
    }

    public void votaNotizia(/*Utente ut,*/int i){
        System.out.println("inserisci voto da 1 a 5");
        Scanner tastiera = new Scanner(System.in);
        int voto1= tastiera.nextInt();
        if(voto>0 && voto<6){
            this.voto=voto1;
        }

    }

}


