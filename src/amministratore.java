import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class amministratore {
    protected ArrayList<Fonte> fonti ;
    protected ArrayList<Notizia> notizie;

    protected File file;
    protected File folder;
    protected ArrayList<File> allFiles;
    protected String fileFonti;

    public amministratore(){
        this.fonti =new ArrayList<>();
        this.notizie=new ArrayList<>();
        this.folder = new File("C:/Progetti/untitled/src/fileTesto/");
        this.allFiles=new ArrayList<>();
        this.fileFonti = "C:/Progetti/untitled/src/fileTesto/nomeFonti.txt";
    }



    public void startNotizia(){
            System.out.println("digita notizia da visualizzare");
            Scanner tastiera=new Scanner(System.in);
            String nomeFonte1= tastiera.nextLine();

        for(int i = 0; i< this.notizie.size(); i++){
                if(this.notizie.get(i).getNomeFonte().equals(nomeFonte1)) {
                    this.notizie.get(i).avviaNotizia();
                    //HashMap<String, String> capitalCities = new HashMap<String, String>();
                }
            }


        }


    public void startFonte() throws IOException {
        System.out.println("digita fonte da visualizzare");
        Scanner tastiera=new Scanner(System.in);
        String nomeFonte1 = tastiera.nextLine();
       for(int i = 0; i< this.fonti.size(); i++){
            if(this.fonti.get(i).getNomeFonte().equals(nomeFonte1)) {
               this.fonti.get(i).avviaFonte();
               System.out.println("scegli il numero di notizia da visualizzare");
                //Scanner tastiera=new Scanner(System.in);
                int m=tastiera.nextInt();
                fonti.get(i).arrayNotizie();
                fonti.get(i).scegliNotizia(m-1);
            }
        }


        }





   /* public int exists(String name){
        int result=-1;
        for(int i = 0; i<this.fonti.size(); i++){
            if(name.equals(this.fonti.get(i).getNomeFonte())){
                result = i;
            }
        }
        return result;
    }
*/

    protected void stampaFonti(){
        for(int i = 0; i< this.fonti.size(); i++){
            System.out.println(this.fonti.get(i).getNomeFonte()+ "|posizione :"+(i+1)+"\n");
        }
    }
    protected void categoria(){
        System.out.println("digita categoria da visualizzare");
        Scanner tastiera=new Scanner(System.in);
        String categoria = tastiera.nextLine();

        for(int k=0; k<fonti.size();k++){
            this.fonti.get(k).notiziaFiltrataCategoria(categoria);

                  }
        }

    protected void autore(){


        for(int k=0; k<fonti.size();k++) {
            System.out.println("autori disponibili per fonte:"+this.fonti.get(k).getNomeFonte()+"\n");
            this.fonti.get(k).nomeAutori();
        }
        System.out.println("digita autore da visualizzare");
        Scanner tastiera=new Scanner(System.in);
        String categoria = tastiera.nextLine();
        for(int k=0; k<fonti.size();k++){

            this.fonti.get(k).notiziaFiltrataAutore(categoria);

        }
    }


    protected void importareFileNotizie(){
        System.out.println("digita il nome del file da cui vuoi importare le fonti");
        Scanner tastiera1=new Scanner(System.in);
        String i=tastiera1.nextLine();
        for(int k=0;k<this.fonti.size();k++) {
            if (this.fonti.get(k).nomeFonte.equals(i))
                this.fonti.get(k).importaDaFileLaFonte();
        }
  }

    protected ArrayList<File> leggiFilePresenti(){
        for (File file : this.folder.listFiles()) {
            String fileName=file.getName().replace(".txt","");
            System.out.println(fileName+".txt");
            this.allFiles.add(file);
        }
        return this.allFiles;
    }





    protected void rimuoviFonte() throws IOException {

        System.out.println("digita nome della fonte da eliminare:");

        for(int i = 0; i< this.fonti.size(); i++){
            System.out.println("nome fonte: "+this.fonti.get(i).getNomeFonte());
        }
        
        Scanner tastiera1=new Scanner(System.in);
        String numero=tastiera1.nextLine();
        String success = null;
       for(int k=0;k<this.fonti.size();k++) {
           if (this.fonti.get(k).nomeFonte.equals(numero)) {
               this.fonti.remove(k);
               success = this.fonti.get(k).nomeFonte;
           }
       }
        Files.delete(Path.of("C:/Progetti/" + success + ".txt"));
        // Se si è verificato un errore...
        /*boolean isDeleted = Files.deleteIfExists(Paths.get("C:/Progetti/"+success+".txt"));
        if (!isDeleted)
            throw new IllegalArgumentException("Cancellazione fallita");
       */
    }


    protected void aggiungiFonte(){

        System.out.println("digita url fonti");
        Scanner tastiera = new Scanner(System.in);
        String fonteNuova = tastiera.nextLine();
        System.out.println("digita nome fonte");
        String nomeFonte = tastiera.nextLine();
        Fonte fonteNew = new Fonte(fonteNuova, nomeFonte);
        this.fonti.add(fonteNew);

        try {
            FileWriter fileDaScriv = new FileWriter(this.fileFonti, true);
            Scanner leggiFileDaScriv= new Scanner(new File(this.fileFonti));

            while(leggiFileDaScriv.nextLine()==null){

                System.out.println("ciaoo");
                fileDaScriv.write(fonteNuova);
            }
            fileDaScriv.close();
            leggiFileDaScriv.close();

        }  catch (IllegalArgumentException | IOException e) {

        e.printStackTrace();
      }
    }


    protected void aggiungiNotizia(){

        System.out.println("digita url notizia");
        Scanner tastiera = new Scanner(System.in);
        String fonteNuova=tastiera.nextLine();
        System.out.println("digita nome notizia");
        String nomeFonte=tastiera.nextLine();
        Notizia NotiziaNew=new Notizia(fonteNuova,nomeFonte);
        if((NotiziaNew.getEntry().getTitle() != null) && (NotiziaNew.getEntry().getPublishedDate() != null) && (NotiziaNew.getEntry().getDescription() != null) && (NotiziaNew.getEntry().getAuthor() != null) && (NotiziaNew.getEntry().getSource() != null)){
            this.notizie.add(NotiziaNew);
        }


    }


    protected void inserisciCommento(Notizia nota){
      for(int i = 0; i< this.fonti.size(); i++) {
          if(this.fonti.get(i).getNomeFonte()==nota.getNomeFonte())
          this.fonti.get(i).aggiungiCommenti();
      }
   }


    public void inserisciVotazioni(Notizia nota){
        for(int i = 0; i< this.fonti.size(); i++) {
            if(this.fonti.get(i).getNomeFonte()==nota.getNomeFonte())
                this.fonti.get(i).aggiungiVotazione();
        }
    }
}
