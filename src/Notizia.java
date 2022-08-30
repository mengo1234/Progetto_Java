import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.xml.sax.InputSource;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Notizia extends readers.Fonte {
    public Notizia(String sourceURL, String nomeFonte) {

        super(sourceURL, nomeFonte);
    }

    public void avviaNotizia() {
        try {
            URL feedUrl = new URL(sourceURL);

            SyndFeedInput input = new SyndFeedInput();

            try {
                SyndFeed feed = input.build(new InputSource(feedUrl.openStream()));

                List<SyndEntry> entries = feed.getEntries();
                BufferedWriter bw = new BufferedWriter(super.fileScrit);
                PrintWriter fileTesto = new PrintWriter(bw);


                Iterator<SyndEntry> itEntries = entries.iterator();


                SyndEntry entry = itEntries.next();

                System.out.println("\n[" + entry.getPublishedDate() + "] ");
                System.out.println(entry.getTitle());
                System.out.println(entry.getLink());

                System.out.println("oooo");

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


}
