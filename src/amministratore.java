import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class amministratore {
    protected ArrayList<Fonte> fonti ;
    protected ArrayList<Notizia> notizie;
    protected File folder;
    protected ArrayList<File> allFiles;
    protected String fileFonti;

    public amministratore() {
        this.fonti = new ArrayList<>();
        this.notizie = new ArrayList<>();
        this.folder = new File("/Users/fabio/Desktop/Universita/Progetto Java/src/fileTesto/");
        this.allFiles = new ArrayList<>();
        this.fileFonti = "/Users/fabio/Desktop/Universita/Progetto Java/src/fileTesto/nomeFonti.txt";

    }


    public void startNotizia(){
            System.out.println("digita nome notizia da visualizzare");
            Scanner tastiera=new Scanner(System.in);
            String nomeFonte1= tastiera.nextLine();

        for(int i = 0; i< this.notizie.size(); i++){
                if(this.notizie.get(i).getNomeFonte().equals(nomeFonte1)) {
                    this.notizie.get(i).avviaUltimaNotizia();
                    //HashMap<String, String> capitalCities = new HashMap<String, String>();
                }
            }


        }


    public void startFonte() throws IOException {
        System.out.println("digita fonte da visualizzare");
        Scanner tastiera=new Scanner(System.in);
        String nomeFonte1 = tastiera.nextLine();


        File fold = new File(fileFonti);
        fonti=leggiFilePresenti(fold,fonti);

        for(int i = 0; i< this.fonti.size(); i++){
            if(this.fonti.get(i).getNomeFonte().equals(nomeFonte1)) {
                System.out.println("\n cosa vuoi vedere?");
                System.out.println("Digita 1: tutte le notizie della fonte");
                System.out.println("Digita 2: notizia scelta della fonte");
                System.out.println("Digita 3: ultima notizia della fonte");
                int menu = tastiera.nextInt();
                switch(menu) {
                    case 1:
                      this.fonti.get(i).avviaFonte();
                        break;
                    case 2:
                      System.out.println("scegli il numero di notizia da visualizzare");
                      int m= tastiera.nextInt();
                      fonti.get(i).arrayNotizie();
                      fonti.get(i).scegliNotizia(m-1);
                        break;
                    case 3:
                        fonti.get(i).arrayNotizie();
                        fonti.get(i).scegliNotizia(0);
                        break;
                    default:
                        System.out.println("Digita meglio!");
                        break;
                }
            }
       }


    }

    public static ArrayList<Fonte> leggiFilePresenti(File fonti,ArrayList<Fonte> allFiles){
        System.out.println("Recupero fonti e notizie gia salvate...");
        try {
             BufferedReader br2 = new BufferedReader(new FileReader(fonti));
             String line;
          while ((line = br2.readLine()) != null) {
              if(line.contains(",")) {
                  String[] righe = line.split(",");
                  String nomeFonte1 = righe[0];
                  String sourceURL1 = righe[1];
                  Fonte font = new Fonte(nomeFonte1, sourceURL1);
                  allFiles.add(font);
              }else {
                  throw new IllegalArgumentException("problemi:"+line+ " riprovare!");
              }


             }
            br2.close();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return allFiles;
    }


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





    protected void rimuoviFonte(String c) throws IOException {

        System.out.println("digita nome della fonte da eliminare:");

        for(int i = 0; i< this.fonti.size(); i++){
            System.out.println("nome fonte: "+this.fonti.get(i).getNomeFonte());
        }
        
        Scanner tastiera1=new Scanner(System.in);
        String nom=tastiera1.nextLine();
        String success = null;



                 //success = this.fonti.get(k).nomeFonte;
                  File newF= new File("/Users/fabio/Desktop/Universita/Progetto Java/src/fileTesto/"+c+".txt");
              boolean result= newF.delete();
                  if(result){
                      System.out.println("file eliminato");
                  }else {
                      System.out.println("file non eliminato");
                   }

             //     this.fonti.remove(k);

                 // Files.deleteIfExists(Path.of("C:/Progetti/untitled/src/fileTesto/"+success + ".txt"));


            }





        // Se si è verificato un errore...
        /*boolean isDeleted = Files.deleteIfExists(Paths.get("C:/Progetti/"+success+".txt"));
        if (!isDeleted)
            throw new IllegalArgumentException("Cancellazione fallita");
       */



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

           // FileReader leggiFileDaScriv= new FileReader("C:/Progetti/untitled/src/fileTesto/"+nomeFonte+".txt");
           // BufferedReader br= new BufferedReader(leggiFileDaScriv);
            String line;

            fileDaScriv.write(fonteNuova+","+nomeFonte);
            fileDaScriv.append("\n");
            fileDaScriv.close();
           // leggiFileDaScriv.close();
            //br.close();

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

        //if( (NotiziaNew.getEntry().getTitle() != null) && (NotiziaNew.getEntry().getPublishedDate() != null) && (NotiziaNew.getEntry().getDescription() != null) && (NotiziaNew.getEntry().getAuthor() != null) && (NotiziaNew.getEntry().getSource() != null)){
            this.notizie.add(NotiziaNew);
      //  }


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
