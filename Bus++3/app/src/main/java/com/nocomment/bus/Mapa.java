package com.nocomment.bus;

import android.app.Application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Bog klasa
public class Mapa extends Application {
    public GraphAsLists Nis;

    public LinkedNode[] Stanice;
    public int brStanica;
    public Linija[] Linije;
    public int brLinija;
    //public Context context;
    public Mapa(){

        //this.context = context;
        Nis=new GraphAsLists();
        Stanice=null;
        brStanica=0;
        Linije=null;
        brLinija=0;
       // this.fajlUnosSvihStanica("stanice.txt");
       // this.ucitajStanice();
       // this.ucitajPuteve("putevi.txt");
        //this.unosSvihLinija("linije.txt");
        //this.unosPodatakaOLinijama();

    }

    public void ucitajStanice(){
      //  int i=0;
     //   while(i++<brStanica)
     //   {
     //       Nis.InsertNode(i,Stanice[i-1].naziv);//voditi racuna da Node dobijaju indexe od 1 a ne od 0!!!
     //   }
    }


    public void ucitajPuteve(String ime) {
        //graf.InsertEdgeSVremenom(1, 2,new Vreme(0,5,0));
        //format stanica1,stanica2,vreme:sat minut sekund
            /*
             String pom=b.readLine();
            String[] s=pom.split(" ");
            ime= s[0];
            godina=Integer.parseInt(s[1]);
            ocena=Integer.parseInt(s[2]);
             */
/*
        try{

            BufferedReader b=new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open(ime)));

            String ucitan = b.readLine();
            while(ucitan!=null)
            {
                String[] pom=ucitan.split("	");
                this.Nis.InsertEdgeSVremenom(Integer.parseInt(pom[0]),
                        Integer.parseInt(pom[1]),
                        new Vreme(Integer.parseInt(pom[2]),
                                Integer.parseInt(pom[3]),
                                Integer.parseInt(pom[4])));
                ucitan=b.readLine();

            }
            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }*/

    }

    protected void fajlUnesiStanicu(String ime){
       /* if (Stanice==null){
            Stanice=new LinkedNode[1];
            Stanice[brStanica++]=new LinkedNode(brStanica,ime);
            //Stanice[brStanica-1].naziv=ime;
        }
        else
        {
            LinkedNode pom[]=new LinkedNode[brStanica];
            for(int i =0;i<brStanica;i++)
                pom[i]=Stanice[i];
            Stanice=new LinkedNode[brStanica+1];
            for(int i =0;i<brStanica;i++)
                Stanice[i]=pom[i];
            Stanice[brStanica++]=new LinkedNode(brStanica);
            Stanice[brStanica-1].naziv=ime;
        }*/
    }


    public void fajlUnosSvihStanica(String ime){
    /*    try{

            InputStreamReader input = new InputStreamReader(getAssets().open(ime));
            BufferedReader b=new BufferedReader(input);

            String imeStanice = b.readLine();
            while(imeStanice!=null)
            {
                this.fajlUnesiStanicu(imeStanice);
                imeStanice=b.readLine();
            }
            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }*/
    }

    protected void unesiLiniju(String ime){
     /*   if (Linije==null){
            Linije=new Linija[1];
            Linije[brLinija++]=new Linija(ime);
        }
        else
        {
            Linija pom[]=new Linija[brLinija];
            for(int i =0;i<brLinija;i++)
                pom[i]=Linije[i];
            Linije=new Linija[brLinija+1];
            for(int i =0;i<brLinija;i++)
                Linije[i]=pom[i];
            Linije[brLinija++]=new Linija(ime);
        }*/
    }

    public void unosSvihLinija(String ime){
       /* try{

            BufferedReader b=new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open(ime)));

            String imeLinije = b.readLine();
            while(imeLinije!=null)
            {
                this.unesiLiniju(imeLinije);
                imeLinije=b.readLine();
            }
            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }*/
    }

    public void unosLinije(String ime,int i)
    {
       /* try{

            BufferedReader b=new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open(ime)));
            String pom = b.readLine();
            int br=0;
            this.Linije[i].id=Integer.parseInt(pom);
            pom=b.readLine();
            this.Linije[i].smer=Integer.parseInt(pom);
            pom=b.readLine();
            while(!pom.equals("vremepolazaka"))
            {
                if(br==0){
                    this.Linije[i].addToRuta(Nis.findNode(Integer.parseInt(pom)));
                    br=(br+1)%2;
                    System.out.println(br);
                    pom=b.readLine();
                }
                else
                {
                    String[] s=pom.split(" ");
                    this.Linije[i].addToRuta(Nis.findEdge(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
                    br=(br+1)%2;
                    System.out.println(br);
                    pom=b.readLine();

                }
            }
            pom=b.readLine();


            while(!pom.equals("subotnjiredvoznje"))
            {
                this.Linije[i].addToPolasci(new Vreme(pom));
                pom=b.readLine();
            }
            pom=b.readLine();

            while(!pom.equals("nedeljniredvoznje"))
            {
                this.Linije[i].addToSubotaPolasci(new Vreme(pom));
                pom=b.readLine();
            }
            pom=b.readLine();


            while(pom!=null)
            {
                this.Linije[i].addToNedeljaPolasci(new Vreme(pom));
                pom=b.readLine();
            }

            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }*/
    }

    public void unosPodatakaOLinijama(){
      /*  System.out.println("broj linija je:"+this.brLinija);
        for(int i=0;i<this.brLinija;i++)
            this.unosLinije(this.Linije[i].nazivLinije+".txt",i);*/
    }


    public boolean imaLiJeStanica(Linija lin, LinkedNode stanica)
    {


        for(int j=0;j<lin.brRuta;j++)

            if(lin.ruta[j] instanceof LinkedNode)
            {
                LinkedNode pom;
                pom=(LinkedNode)lin.ruta[j];
                if(pom.naziv.equals(stanica.naziv))
                    return true;
            }



        return false;
    }

    public int kojaJePoRedu(Linija lin, LinkedNode stanica)
    {


        for(int j=0;j<lin.brRuta;j++)

            if(lin.ruta[j] instanceof LinkedNode)
            {
                LinkedNode pom;
                pom=(LinkedNode)lin.ruta[j];
                if(pom.naziv.equals(stanica.naziv))
                    //return (j-(j/2));
                    return j;
            }



        return -1;
    }

    public String imajuIstuStanicu(Linija a,int indexA,Linija b,int IndexB)
    {
        LinkedNode pom1,pom2;
        for(int i=indexA;i<a.brRuta;i++)
        {
            if(a.ruta[i] instanceof LinkedNode)
            {
                pom1=(LinkedNode) a.ruta[i];
                for(int j=0;j<IndexB;j++)
                {

                    if(b.ruta[j] instanceof LinkedNode)
                    {
                        pom2=(LinkedNode) b.ruta[j];
                        if(pom1==pom2)
                            return pom1.naziv;
                    }
                }

            }
        }

        return "Nema";
    }



    public String putAB(LinkedNode a,LinkedNode b)
    {
        String ret="";
        if(a.equals(b))
            return "Ista";
        for(int i=0;i<this.brLinija;i++){
            if(this.Linije[i].sadrziAB(a, b))
                switch(new Vreme().vratiCurrentDate())
                {
                    case "Sunday":
                        System.out.println("Sunday");
                        ret= this.prvaLinijaZaKojuStizeBusKojiNajbrzeStizeNaOdredisteNedelja(a, b);

                        break;
                    case "Saturday":
                        System.out.println("Saturday");
                        ret= this.prvaLinijaZaKojuStizeBusKojiNajbrzeStizeNaOdredisteSubota(a, b);
                        break;
                    default:
                        System.out.println("Default");
                        ret= this.prvaLinijaZaKojuStizeBusKojiNajbrzeStizeNaOdrediste(a, b);
                        break;
                }
        }
        if(ret.equals(""))
        {
            switch(new Vreme().vratiCurrentDate())
            {
                case "Sunday":
                    System.out.println("Sunday");
                    ret= nadjiPutOdAdoBNedelja(a, b);

                    break;
                case "Saturday":
                    System.out.println("Saturday");
                    ret= nadjiPutOdAdoBSubota(a, b);
                    break;
                default:
                    System.out.println("Default");
                    ret=nadjiPutOdAdoB(a, b);
                    break;
            }

        }

        return ret;
    }

    public String prvaLinijaZaKojuStizeBusKojiNajbrzeStizeNaOdrediste(LinkedNode a,LinkedNode b){
        Vreme min=new Vreme(23, 59, 59);
        Linija lin=new Linija();
        for(int i=0;i<this.brLinija;i++){
            if(this.Linije[i].sadrziAB(a, b)){
                Vreme pom=new Vreme(this.Linije[i].izracunajPrviSledeci(a).toString());
                pom.add(this.Linije[i].izracunajVremeIzmedjuStanica(a, b));
                if(pom.jeManje(min))
                {
                    min=pom;
                    lin=Linije[i];
                }
            }

        }
        Vreme tren=new Vreme(new Vreme().vratiCurrentTime());
        tren.add(lin.izracunajPrviSledeci(a));
        tren.add(lin.izracunajVremeIzmedjuStanica(a,b));
        //return "Linija"+"\n"+lin.izracunajPrviSledeci(MainActivity.mapa.Nis.findNode(lin.nazivLinije))+lin.nazivLinije;
        return "Linija"+"\n"+tren.toString()+"\n"+lin.nazivLinije;
    }

    public String prvaLinijaZaKojuStizeBusKojiNajbrzeStizeNaOdredisteSubota(LinkedNode a,LinkedNode b){
        Vreme min=new Vreme(23, 59, 59);
        Linija lin=new Linija();
        for(int i=0;i<this.brLinija;i++){
            if(this.Linije[i].sadrziAB(a, b)){
                Vreme pom=new Vreme(this.Linije[i].izracunajPrviSledeciSubota(a).toString());
                pom.add(this.Linije[i].izracunajVremeIzmedjuStanica(a, b));
                if(pom.jeManje(min))
                {
                    min=pom;
                    lin=Linije[i];
                }
            }

        }
        Vreme tren=new Vreme(new Vreme().vratiCurrentTime());
        tren.add(lin.izracunajPrviSledeciSubota(a));
        tren.add(lin.izracunajVremeIzmedjuStanica(a,b));
        return "Linija"+"\n"+tren.toString()+"\n"+lin.nazivLinije;
    }

    public String prvaLinijaZaKojuStizeBusKojiNajbrzeStizeNaOdredisteNedelja(LinkedNode a,LinkedNode b){
        Vreme min=new Vreme(23, 59, 59);
        Linija lin=new Linija();
        for(int i=0;i<this.brLinija;i++){
            if(this.Linije[i].sadrziAB(a, b)){
                Vreme pom=new Vreme(this.Linije[i].izracunajPrviSledeciNedelja(a).toString());
                pom.add(this.Linije[i].izracunajVremeIzmedjuStanica(a, b));
                if(pom.jeManje(min))
                {
                    min=pom;
                    lin=Linije[i];
                }
            }

        }
        Vreme tren=new Vreme(new Vreme().vratiCurrentTime());
        tren.add(lin.izracunajPrviSledeciNedelja(a));
        tren.add(lin.izracunajVremeIzmedjuStanica(a,b));
        return "Linija"+"\n"+tren.toString()+"\n"+lin.nazivLinije;
    }

    public String nadjiPutOdAdoB(LinkedNode a,LinkedNode b)
    {
        String povratnaVrednost="Nema";
        Vreme min=new Vreme("23:59:59");
        String minS;
        Linija[] LinijeA,LinijeB;
        int[] indexiA,indexiB;
        indexiA=new int[brLinija];
        indexiB=new int[brLinija];
        LinijeA=new Linija[brLinija];
        LinijeB=new Linija[brLinija];
        int BrA=0,BrB=0;
        for(int i=0;i<this.brLinija;i++)
            if(this.imaLiJeStanica(this.Linije[i], a))
            {
                LinijeA[BrA++]=this.Linije[i];
                indexiA[BrA-1]=this.kojaJePoRedu(this.Linije[i], a);
                //System.out.println("A: "+this.Linije[i].nazivLinije);
                //System.out.println(this.kojaJePoRedu(this.Linije[i], a));
            }

        for(int i=0;i<this.brLinija;i++)
            if(this.imaLiJeStanica(this.Linije[i], b))
            {
                LinijeB[BrB++]=this.Linije[i];
                indexiB[BrB-1]=this.kojaJePoRedu(this.Linije[i], b);
                //System.out.println("B: "+this.Linije[i].nazivLinije);
                //System.out.println(this.kojaJePoRedu(this.Linije[i], b));
            }
        for(int i=0;i<BrA;i++)
            for(int j=0;j<BrB;j++){
                //System.out.println("Stanica: "+this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j]));
                if(!this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j]).equals("Nema"))
                {
                    String zajednickastanica=this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j]);
                    //System.out.println("===================================================");
                    //System.out.println(LinijeA[i].nazivLinije+" do "+this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])+ " zatim "+LinijeB[j].nazivLinije);
                    Vreme v1=LinijeA[i].izracunajVremeIzmedjuStanica(a, Nis.findNode(this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])));

                    Vreme v2=LinijeB[j].izracunajVremeIzmedjuStanica(Nis.findNode(this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])),b);

                    Vreme d1=new Vreme(LinijeA[i].izracunajPrviSledeci(a).toString());

                    Vreme vremeDolaskaBusaNaZajednickuStanicu=new Vreme(new Vreme().vratiCurrentTime());
                    vremeDolaskaBusaNaZajednickuStanicu.add(d1);
                    vremeDolaskaBusaNaZajednickuStanicu.add(v1);
                    //System.out.println("vremeDolaskaBusaNaZajednickuStanicu: "+vremeDolaskaBusaNaZajednickuStanicu.toString());
                    Vreme d2=new Vreme(LinijeB[j].izracunajSledeciZaVreme(Nis.findNode(zajednickastanica),vremeDolaskaBusaNaZajednickuStanicu).toString());

                    Vreme ukk=new Vreme(new Vreme().vratiCurrentTime());
                    ukk.add(d1);
                    //System.out.println("d1: "+d1.toString());
                    ukk.add(v1);
                    //System.out.println("v1: "+v1.toString());
                    ukk.add(d2);
                    //	System.out.println("d2: "+d2.toString());
                    //System.out.println("Vreme polaska sa stanice za menjanje: "+ukk.toString());
                    ukk.add(v2);
                    //System.out.println("v2: "+v2.toString());

                    //	System.out.println("Trenutno vreme: "+new Vreme().vratiCurrentTime());
                    //	System.out.println("Prvi sledeci: "+LinijeA[i].izracunajPrviSledeci(a));

                    //	System.out.println("Vreme dolaska na poslednju stanicu: "+ukk.toString());
                    if(ukk.jeManje(min))
                    {
                        min=ukk;
                        minS=LinijeA[i].nazivLinije+"\n"+this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])+ "\n"+LinijeB[j].nazivLinije;
                        //	System.out.println("-------------------------------------------");
                        //	System.out.println("Ovo je pripremljeno za povratnu vrednost: ");
                        povratnaVrednost="AB"+"\n"+min.toString()+"\n"+minS;
                        //	System.out.println(povratnaVrednost);
                        //	System.out.println("-------------------------------------------");
                    }

				/*Vreme uk=new Vreme();
				uk.add(v1);
				Vreme vremedolaskanazajednickustanicu=new Vreme();
				vremedolaskanazajednickustanicu.add(v1);
				Vreme prvisledeci = new Vreme(LinijeA[i].izracunajPrviSledeci(a).toString());
				vremedolaskanazajednickustanicu.add(prvisledeci);
				Vreme trenutnovreme = new Vreme(prvisledeci.vratiCurrentTime());
				vremedolaskanazajednickustanicu.add(trenutnovreme);
				System.out.println("Vreme dolaska na stanicu za menjanje: "+vremedolaskanazajednickustanicu.toString());
				uk.add(v2);
				System.out.println("s1: "+v1.toString());
				System.out.println("s2: "+v2.toString());
				System.out.println("Ukupno vreme za putovanje: "+uk.toString());

				*/
                    //System.out.println("Trenutno vreme: "+new Vreme().vratiCurrentTime());
                    //System.out.println("Prvi sledeci: "+LinijeA[i].izracunajPrviSledeci(a));
                    //System.out.println(LinijeA[i].nazivLinije);
                    //	System.out.println("===================================================");
                }
            }
        return povratnaVrednost;
    }

    public String nadjiPutOdAdoBSubota(LinkedNode a,LinkedNode b)
    {
        String povratnaVrednost="Nema";
        Vreme min=new Vreme("23:59:59");
        String minS;
        Linija[] LinijeA,LinijeB;
        int[] indexiA,indexiB;
        indexiA=new int[brLinija];
        indexiB=new int[brLinija];
        LinijeA=new Linija[brLinija];
        LinijeB=new Linija[brLinija];
        int BrA=0,BrB=0;
        for(int i=0;i<this.brLinija;i++)
            if(this.imaLiJeStanica(this.Linije[i], a))
            {
                LinijeA[BrA++]=this.Linije[i];
                indexiA[BrA-1]=this.kojaJePoRedu(this.Linije[i], a);
            }

        for(int i=0;i<this.brLinija;i++)
            if(this.imaLiJeStanica(this.Linije[i], b))
            {
                LinijeB[BrB++]=this.Linije[i];
                indexiB[BrB-1]=this.kojaJePoRedu(this.Linije[i], b);
            }
        for(int i=0;i<BrA;i++)
            for(int j=0;j<BrB;j++){
                if(!this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j]).equals("Nema"))
                {
                    String zajednickastanica=this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j]);
                    Vreme v1=LinijeA[i].izracunajVremeIzmedjuStanica(a, Nis.findNode(this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])));
                    Vreme v2=LinijeB[j].izracunajVremeIzmedjuStanica(Nis.findNode(this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])),b);

                    Vreme d1=new Vreme(LinijeA[i].izracunajPrviSledeciSubota(a).toString());

                    Vreme vremeDolaskaBusaNaZajednickuStanicu=new Vreme(new Vreme().vratiCurrentTime());
                    vremeDolaskaBusaNaZajednickuStanicu.add(d1);
                    vremeDolaskaBusaNaZajednickuStanicu.add(v1);
                    Vreme d2=new Vreme(LinijeB[j].izracunajSledeciZaVremeSubota(Nis.findNode(zajednickastanica),vremeDolaskaBusaNaZajednickuStanicu).toString());

                    Vreme ukk=new Vreme(new Vreme().vratiCurrentTime());
                    ukk.add(d1);
                    ukk.add(v1);
                    ukk.add(d2);
                    ukk.add(v2);
                    if(ukk.jeManje(min))
                    {
                        min=ukk;
                        minS=LinijeA[i].nazivLinije+"\n"+this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])+ "\n"+LinijeB[j].nazivLinije;
                        povratnaVrednost="AB"+"\n"+min.toString()+"\n"+minS;
                    }

                }
            }
        return povratnaVrednost;
    }

    public String nadjiPutOdAdoBNedelja(LinkedNode a,LinkedNode b)
    {
        String povratnaVrednost="Nema";
        Vreme min=new Vreme("23:59:59");
        String minS;
        Linija[] LinijeA,LinijeB;
        int[] indexiA,indexiB;
        indexiA=new int[brLinija];
        indexiB=new int[brLinija];
        LinijeA=new Linija[brLinija];
        LinijeB=new Linija[brLinija];
        int BrA=0,BrB=0;
        for(int i=0;i<this.brLinija;i++)
            if(this.imaLiJeStanica(this.Linije[i], a))
            {
                LinijeA[BrA++]=this.Linije[i];
                indexiA[BrA-1]=this.kojaJePoRedu(this.Linije[i], a);
            }

        for(int i=0;i<this.brLinija;i++)
            if(this.imaLiJeStanica(this.Linije[i], b))
            {
                LinijeB[BrB++]=this.Linije[i];
                indexiB[BrB-1]=this.kojaJePoRedu(this.Linije[i], b);
            }
        for(int i=0;i<BrA;i++)
            for(int j=0;j<BrB;j++){
                if(!this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j]).equals("Nema"))
                {
                    String zajednickastanica=this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j]);
                    Vreme v1=LinijeA[i].izracunajVremeIzmedjuStanica(a, Nis.findNode(this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])));
                    Vreme v2=LinijeB[j].izracunajVremeIzmedjuStanica(Nis.findNode(this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])),b);

                    Vreme d1=new Vreme(LinijeA[i].izracunajPrviSledeciNedelja(a).toString());

                    Vreme vremeDolaskaBusaNaZajednickuStanicu=new Vreme(new Vreme().vratiCurrentTime());
                    vremeDolaskaBusaNaZajednickuStanicu.add(d1);
                    vremeDolaskaBusaNaZajednickuStanicu.add(v1);
                    Vreme d2=new Vreme(LinijeB[j].izracunajSledeciZaVremeNedelja(Nis.findNode(zajednickastanica),vremeDolaskaBusaNaZajednickuStanicu).toString());

                    Vreme ukk=new Vreme(new Vreme().vratiCurrentTime());
                    ukk.add(d1);
                    ukk.add(v1);
                    ukk.add(d2);
                    ukk.add(v2);
                    if(ukk.jeManje(min))
                    {
                        min=ukk;
                        minS=LinijeA[i].nazivLinije+"\n"+this.imajuIstuStanicu(LinijeA[i], indexiA[i],LinijeB[j], indexiB[j])+ "\n"+LinijeB[j].nazivLinije;
                        povratnaVrednost="AB"+"\n"+min.toString()+"\n"+minS;
                    }

                }
            }
        return povratnaVrednost;
    }
}