package readers;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.xml.sax.InputSource;

import java.io.*;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Fonte {

   public String sourceURL;
   public String nomeFonte;
   public File file;
   public  FileWriter fileScrit;
   public ArrayList <String> commenti;
   public ArrayList<Integer> votazioni;
    public Fonte(String sourceURL,String nomeFonte) {
        this.sourceURL = sourceURL;
        this.nomeFonte = nomeFonte;

        //this.file = new File("C:/Progetti/" + nomeFonte + ".txt");
        try {
            this.fileScrit = new FileWriter("C:/Progetti/" + nomeFonte + ".txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public File getFile() {
        return file;
    }

    public String getNomeFonte() {
        return nomeFonte;
    }

    public void avviaFonte() {
        try {
            URL feedUrl = new URL(sourceURL);

            SyndFeedInput input = new SyndFeedInput();

            try {
                SyndFeed feed1 = input.build(new InputSource(feedUrl.openStream()));

                List<SyndEntry> entries1 = feed1.getEntries();
                BufferedWriter bw1 = new BufferedWriter(fileScrit);
                PrintWriter fileTesto1 = new PrintWriter(bw1);
                Iterator<SyndEntry> itEntries = entries1.iterator();

                Scanner fileDaLeggere = new Scanner("C:/Progetti/" + nomeFonte + ".txt");

                while (itEntries.hasNext()) {
                    SyndEntry entry1 = itEntries.next();
                    //      while (fileDaLeggere.hasNextLine()) {
                    //     if (!fileDaLeggere.nextLine().equals(entry.getPublishedDate()) || !fileDaLeggere.equals(entry.getTitle()) || !fileDaLeggere.equals(entry.getLink())) {

                                fileTesto1.println("[" + entry1.getPublishedDate() + "] ");
                                fileTesto1.println(entry1.getTitle());
                                fileTesto1.println(entry1.getLink());
                            }
                fileDaLeggere.close();
                fileTesto1.close();
                this.fileScrit.close();

            } catch (IllegalArgumentException | FeedException | IOException e) {
                // Errore lettura feed
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            // Errore indirizzo e accesso ai feed
            e.printStackTrace();
        }
    }

    public void notiziaFiltrataAutore(String autore){
        try {
            URL feedUrl1 = new URL(sourceURL);

            SyndFeedInput input1 = new SyndFeedInput();

            try {
                SyndFeed feed = input1.build(new InputSource(feedUrl1.openStream()));

                List<SyndEntry> entries = feed.getEntries();

                Iterator<SyndEntry> itEntries = entries.iterator();

                while (itEntries.hasNext()) {
                    SyndEntry entry = itEntries.next();
                    while(entry.getAuthor().equals(autore)){
                        System.out.println("\n[" + entry.getPublishedDate() + "] ");
                        System.out.println(entry.getTitle());
                        System.out.println(entry.getLink());

                    }

                }

            } catch (IllegalArgumentException | FeedException | IOException e) {
                // Errore lettura feed
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            // Errore indirizzo e accesso ai feed
            e.printStackTrace();
        }
    }







    //deve farlo il client
    public void aggiungiVotazione(){
        System.out.println("inserisci voto");
        Scanner tastiera2=new Scanner(System.in);
        int voto=tastiera2.nextInt();
        if(voto>0 && voto<=10){
        votazioni.add(voto);
        }else{
            System.out.println("inserisci voto da 1 a 10");
        }
    }

    public void aggiungiCommenti(){
        System.out.println("inserisci commento");
        Scanner tastiera1=new Scanner(System.in);
        String stringa=tastiera1.nextLine();
        commenti.add(stringa);
    }


}

