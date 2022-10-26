import java.io.IOException;

public class main {


    public static void main(String[] args) throws IOException {

        amministratore amm=new amministratore();
        amm.aggiungiFonte();

        amm.startFonte();
        amm.leggiFilePresenti();
        amm.importareFileNotizie();
        amm.autore();
        amm.categoria();
        //non va   amm.rimuoviFonte();
        amm.aggiungiNotizia();
        //amm.aggiungiFonte();
        amm.stampaFonti();
        //amm.aggiungiNotizia();
        amm.startNotizia();
        //amm.startNotizia();
        amm.startFonte();
       // amm.startFonte();

    }
}
