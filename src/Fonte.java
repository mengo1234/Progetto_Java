import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Fonte{
    public String path;
   public String sourceURL;
   public String nomeFonte;
   public  FileWriter fileScrit;
   public ArrayList <String> commenti;
   public ArrayList<Integer> votazioni;
   public ArrayList<Notizia> notizieTot;


   public Notizia notiziaScelta;
    public Fonte(String sourceURL,String nomeFonte) {
        this.sourceURL = sourceURL;
        this.nomeFonte = nomeFonte;
        this.path="C:/Progetti/untitled/src/fileTesto/" + nomeFonte + ".txt";
        this.notizieTot=new ArrayList<>();

        try {
            this.fileScrit = new FileWriter(path, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                Scanner fileDaLeggere = new Scanner(this.path);

                while (itEntries.hasNext()) {
                    SyndEntry entry = itEntries.next();

                    fileTesto1.println("[" + entry.getPublishedDate() + "] ");
                    fileTesto1.println(entry.getTitle());
                    fileTesto1.println(entry.getLink());
                    fileTesto1.println(entry.getCategories());
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
    protected void nomeAutori(){
        try {
            URL feedUrl = new URL(sourceURL);
            SyndFeedInput input = new SyndFeedInput();
            try {
                SyndFeed feed2 = input.build(new InputSource(feedUrl.openStream()));
                List<SyndEntry> entries2 = feed2.getEntries();
                Iterator<SyndEntry> itEntries = entries2.iterator();

                ArrayList<String> autori= new ArrayList<>();
            int i=0;
            while (itEntries.hasNext()) {
            SyndEntry entry = itEntries.next();
            autori.add((String)entry.getAuthor());
            System.out.println("nome autore:"+autori.get(i));
            i++;
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
    public void notiziaFiltrataCategoria(String categoria){
        try {
            URL feedUrl1 = new URL(sourceURL);
            SyndFeedInput input1 = new SyndFeedInput();
            try {
                SyndFeed feed = input1.build(new InputSource(feedUrl1.openStream()));
                List<SyndEntry> entries = feed.getEntries();
                Iterator<SyndEntry> itEntries = entries.iterator();

                while (itEntries.hasNext()) {
                    SyndEntry entry = itEntries.next();

                    while(entry.getCategories().equals(categoria)){
                        System.out.println("\n[" + entry.getPublishedDate() + "] ");
                        System.out.println(entry.getTitle());
                        System.out.println(entry.getLink());
                        //("[SyndCategoryImpl.taxonomyUri=null\nSyndCategoryImpl.name="+categoria+"\nSyndCategoryImpl.interface=interface com.rometools.rome.feed.synd.SyndCategory\n]")
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

                    if(entry.getAuthor().equals(autore)){
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

    protected void importaDaFileLaFonte(){
        try {
            System.out.println("digita il nome del file in cui importare");
            Scanner tastiera = new Scanner(System.in);
            String fileScritto = tastiera.nextLine();
            if (Files.exists(Path.of("C:/Progetti/untitled/src/fileTesto/" + fileScritto + ".txt"))) {
                Scanner importa = new Scanner(new File(this.path));


                PrintWriter fileDaScrivere = new PrintWriter("C:/Progetti/untitled/src/fileTesto/" + fileScritto + ".txt");
                while (importa.hasNextLine()) {
                    fileDaScrivere.println(importa.nextLine());
                }
                importa.close();
                fileDaScrivere.close();
            }else{
                System.out.println("digita il nome del file corretto!");
            }
        } catch(IllegalArgumentException | IOException e){
            e.printStackTrace();
        }

    }


    public void scegliNotizia(/* Utente ut,*/int i) throws IOException {
        BufferedWriter bw = new BufferedWriter(this.fileScrit);
        PrintWriter fileTesto = new PrintWriter(bw);
        this.notiziaScelta= notizieTot.get(i);
        notiziaScelta.avviaNotizia(/* Utente ut*/);
        fileTesto.close();
        bw.close();
        this.fileScrit.close();
    }
    public void arrayNotizie() {
        try {
            URL feedUrl = new URL(sourceURL);
            SyndFeedInput input = new SyndFeedInput();

            try {
                SyndFeed feed = input.build(new InputSource(feedUrl.openStream()));
                List<SyndEntry> entries = feed.getEntries();
                Iterator<SyndEntry> itEntries = entries.iterator();
                for (int i = 0; i <= notizieTot.size(); i++) {
                        if (itEntries.hasNext()) {
                        SyndEntry entry1 = itEntries.next();
                        notizieTot.add(new Notizia(this.sourceURL,this.nomeFonte));
                        notizieTot.get(i).setEntry(entry1);
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



    //fallo in notizia qua non centra un cazz
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
//commenta la fonte
    public void aggiungiCommenti(){
        System.out.println("inserisci commento");
        Scanner tastiera1=new Scanner(System.in);
        String stringa=tastiera1.nextLine();
        commenti.add(stringa);
    }


}

