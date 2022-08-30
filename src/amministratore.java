import readers.Fonte;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class amministratore {
    protected ArrayList<Fonte> fonti;
    protected ArrayList<Notizia> notizie;

    protected File file;

    public amministratore(){
        this.fonti =new ArrayList<>();
        this.notizie=new ArrayList<>();

    }

    public void startNotizia(){
            System.out.println("digita notizia da visualizzare");
            Scanner tastiera=new Scanner(System.in);
            String nomeFonte1= tastiera.nextLine();

        for(int i = 0; i< this.notizie.size(); i++){
                if(this.notizie.get(i).getNomeFonte().equals(nomeFonte1)) {
                    this.notizie.get(i).avviaNotizia();

                }
            }


        }

    public void startFonte(){
        System.out.println("digita fonte da visualizzare");
        Scanner tastiera=new Scanner(System.in);
        String nomeFonte1= tastiera.nextLine();
        for(int i = 0; i< this.fonti.size(); i++){
            if(this.fonti.get(i).getNomeFonte().equals(nomeFonte1)) {
               this.fonti.get(i).avviaFonte();
            }
        }


    }


    public int exists(String name){
        int result=-1;
        for(int i = 0; i<this.fonti.size(); i++){
            if(name.equals(this.fonti.get(i).getNomeFonte())){
                result = i;
            }
        }
        return result;
    }
    public void stampaFonti(){
        for(int i = 0; i< this.fonti.size(); i++){
            System.out.println(this.fonti.get(i).getNomeFonte()+ "|posizione :"+i+"\n");
        }
    }


    protected void importaDaFile(File file,Notizia not){
         try{
             Scanner importa = new Scanner (file);
             PrintWriter fileDaScrivere = new PrintWriter(not.getFile());
             while(importa.hasNextLine()) {
                 fileDaScrivere.println(importa.nextLine());
              }
             importa.close();
             fileDaScrivere.close();
             } catch (IllegalArgumentException |  IOException e) {
              e.printStackTrace();
         }
    }
   /* protected static void rimuoviFonte() {

        System.out.println("digita numero della fonte da eliminare:");
        for(int i=0;i<fonti.size();i++){
            System.out.println("fonte numero: "+(i+1)+", nome fonte: "+fonti.get(i).getNomeFonte());
        }
        Scanner tastiera1=new Scanner(System.in);
        int numero=tastiera1.nextInt();

        fonti.remove(numero-1);

        boolean success = fonti.get(numero-1).this.file.delete();
        // Se si è verificato un errore...
        if (!success)
            throw new IllegalArgumentException("Cancellazione fallita");

    }


        tastiera1.close();
    }

    */
    protected void aggiungiFonte(){

        System.out.println("digita url fonti");
        Scanner tastiera = new Scanner(System.in);
        String fonteNuova=tastiera.nextLine();
        System.out.println("digita nome fonte");
        String nomeFonte=tastiera.nextLine();
        Fonte fonteNew=new Fonte(fonteNuova,nomeFonte);
        this.fonti.add(fonteNew);


    }
    protected void aggiungiNotizia(){

        System.out.println("digita url notizia");
        Scanner tastiera = new Scanner(System.in);
        String fonteNuova=tastiera.nextLine();
        System.out.println("digita nome notizia");
        String nomeFonte=tastiera.nextLine();
        Notizia NotiziaNew=new Notizia(fonteNuova,nomeFonte);
        this.notizie.add(NotiziaNew);


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
