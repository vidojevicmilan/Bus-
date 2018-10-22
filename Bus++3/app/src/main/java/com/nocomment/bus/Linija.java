package com.nocomment.bus;

public class Linija {

    public Integer id;
    public Integer smer;
    public String nazivLinije;
    public Object ruta[];
    public int brRuta;
    public Vreme polasci[];
    public int brP;
    public Vreme nedeljaPolasci[];
    public int brNedeljaP;
    public Vreme subotaPolasci[];
    public int brSubotaP;

    public Linija(){
        brRuta=0;
        brP=0;
        smer=0;
        brNedeljaP=0;
        brSubotaP=0;
        ruta=null;
    }

    public Linija(String ime){
        brRuta=0;
        brP=0;
        smer=0;
        brNedeljaP=0;
        brSubotaP=0;
        ruta=null;
        nazivLinije=ime;
    }

    public Linija(String ime,int brojLinije){
        id=brojLinije;
        brRuta=0;
        brP=0;
        smer=0;
        brNedeljaP=0;
        brSubotaP=0;
        ruta=null;
        nazivLinije=ime;
    }
    public Linija(String ime,int brojLinije,int sm){
        id=brojLinije;
        brRuta=0;
        brP=0;
        smer=0;
        brNedeljaP=0;
        ruta=null;
        smer=sm;
        nazivLinije=ime;
    }

    public void addToRuta(Object x){
        if((x instanceof Edge)||(x instanceof LinkedNode))
        {
            if (ruta==null){
                ruta=new Object[1];
                ruta[brRuta++]=x;
            }
            else
            {
                Object pom[]=new Object[brRuta];
                for(int i =0;i<brRuta;i++)
                    pom[i]=ruta[i];
                ruta=new Object[brRuta+1];
                for(int i =0;i<brRuta;i++)
                    ruta[i]=pom[i];
                ruta[brRuta++]=x;
            }

        }
        else
            System.out.println("Greska. addToRouta");
    }

    public void addToPolasci(Vreme x){

        {
            if (polasci==null){
                polasci=new Vreme[1];
                polasci[brP++]=x;
            }
            else
            {
                Vreme pom[]=new Vreme[brP];
                for(int i =0;i<brP;i++)
                    pom[i]=polasci[i];
                polasci=new Vreme[brP+1];
                for(int i =0;i<brP;i++)
                    polasci[i]=pom[i];
                polasci[brP++]=x;
            }

        }
        //System.out.println("Dodat. addToPolasci"+x.toString());
    }

    public void addToNedeljaPolasci(Vreme x){

        {
            if (nedeljaPolasci==null){
                nedeljaPolasci=new Vreme[1];
                nedeljaPolasci[brNedeljaP++]=x;
            }
            else
            {
                Vreme pom[]=new Vreme[brNedeljaP];
                for(int i =0;i<brNedeljaP;i++)
                    pom[i]=nedeljaPolasci[i];
                nedeljaPolasci=new Vreme[brNedeljaP+1];
                for(int i =0;i<brNedeljaP;i++)
                    nedeljaPolasci[i]=pom[i];
                nedeljaPolasci[brNedeljaP++]=x;
            }

        }
        //System.out.println("Dodat. addToVikendPolasci"+x.toString());
    }


    public void addToSubotaPolasci(Vreme x){

        {
            if (subotaPolasci==null){
                subotaPolasci=new Vreme[1];
                subotaPolasci[brSubotaP++]=x;
            }
            else
            {
                Vreme pom[]=new Vreme[brSubotaP];
                for(int i =0;i<brSubotaP;i++)
                    pom[i]=subotaPolasci[i];
                subotaPolasci=new Vreme[brSubotaP+1];
                for(int i =0;i<brSubotaP;i++)
                    subotaPolasci[i]=pom[i];
                subotaPolasci[brSubotaP++]=x;
            }

        }
        //System.out.println("Dodat. addToSubotaPolasci"+x.toString());
    }

    public Vreme izracunajVremeDolaska(LinkedNode ln1){
        Vreme sum =new Vreme();
        int i =0 ;
        //Object obj=ruta[i];
        boolean naso=false;
        while(naso==false && this.brRuta > i){
            if(ruta[i] instanceof LinkedNode)
            {
                LinkedNode l=(LinkedNode) ruta[i];
                if(l.node == ln1.node)
                    naso=true;
            }


            if(ruta[i] instanceof Edge)
            {
                Edge e=(Edge) ruta[i];
                sum.add(e.vremePuta);
                i++;
            }
            else
                i++;
        }

        return sum;

    }

    public Vreme izracunajVremeIzmedjuStanica(LinkedNode ln1, LinkedNode ln2)
    {
        Vreme sum = new Vreme();
        sum.add(this.izracunajVremeDolaska(ln2));
        sum.sub(this.izracunajVremeDolaska(ln1));
        return sum;
    }

    public Vreme izracunajPrviSledeci(LinkedNode ln1){
        Vreme trenutnovreme=new Vreme();
        Vreme pom=new Vreme(trenutnovreme.vratiCurrentTime());
        trenutnovreme=new Vreme(pom.vratiCurrentTime());
        int i=0;
        Vreme pom2 = new Vreme();
        pom2.add(polasci[i]);
        pom2.add(this.izracunajVremeDolaska(ln1));
        while(pom2.jeManje(trenutnovreme) && i<brP-1)
        {
            i++;
            pom2 = new Vreme();
            pom2.add(polasci[i]);
            pom2.add(this.izracunajVremeDolaska(ln1));


        }
        pom2.sub(trenutnovreme);
        //if(pom2.jeManje(new Vreme(new Vreme().vratiCurrentTime())))
            return pom2;
        //else
          //  return new Vreme("23:59:59");



    }
    public Vreme izracunajPrviSledeciSubota(LinkedNode ln1){
        Vreme trenutnovreme=new Vreme();
        Vreme pom=new Vreme(trenutnovreme.vratiCurrentTime());
        trenutnovreme=new Vreme(pom.vratiCurrentTime());
        int i=0;
        Vreme pom2 = new Vreme();
        pom2.add(subotaPolasci[i]);
        pom2.add(this.izracunajVremeDolaska(ln1));
        while(pom2.jeManje(trenutnovreme) && i<brSubotaP-1)
        {
            i++;
            pom2 = new Vreme();
            pom2.add(subotaPolasci[i]);
            pom2.add(this.izracunajVremeDolaska(ln1));


        }
        pom2.sub(trenutnovreme);
        //if(pom2.jeManje(new Vreme(new Vreme().vratiCurrentTime())))
            return pom2;
        //else
            //return new Vreme("23:59:59");



    }

    public Vreme izracunajPrviSledeciNedelja(LinkedNode ln1){
        Vreme trenutnovreme=new Vreme();
        Vreme pom=new Vreme(trenutnovreme.vratiCurrentTime());
        trenutnovreme=new Vreme(pom.vratiCurrentTime());
        int i=0;
        Vreme pom2 = new Vreme();
        pom2.add(nedeljaPolasci[i]);
        pom2.add(this.izracunajVremeDolaska(ln1));
        while(pom2.jeManje(trenutnovreme) && i<brNedeljaP-1)
        {
            i++;
            pom2 = new Vreme();
            pom2.add(nedeljaPolasci[i]);
            pom2.add(this.izracunajVremeDolaska(ln1));


        }
        pom2.sub(trenutnovreme);
        //if(pom2.jeManje(new Vreme(new Vreme().vratiCurrentTime())))
            return pom2;
        //else
         //   return new Vreme("23:59:59");



    }

    public Vreme izracunajSledeciZaVreme(LinkedNode ln1,Vreme x){
        Vreme trenutnovreme=x;
        int i=0;
        Vreme pom2 = new Vreme();
        pom2.add(polasci[i]);
        pom2.add(this.izracunajVremeDolaska(ln1));
        while(pom2.jeManje(trenutnovreme) && i<brP-1)
        {
            i++;
            pom2 = new Vreme();
            pom2.add(polasci[i]);
            pom2.add(this.izracunajVremeDolaska(ln1));


        }
        pom2.sub(trenutnovreme);
        return pom2;



    }

    public Vreme izracunajSledeciZaVremeSubota(LinkedNode ln1,Vreme x){
        Vreme trenutnovreme=x;
        int i=0;
        Vreme pom2 = new Vreme();
        pom2.add(subotaPolasci[i]);
        pom2.add(this.izracunajVremeDolaska(ln1));
        while(pom2.jeManje(trenutnovreme) && i<brSubotaP-1)
        {
            i++;
            pom2 = new Vreme();
            pom2.add(subotaPolasci[i]);
            pom2.add(this.izracunajVremeDolaska(ln1));


        }
        pom2.sub(trenutnovreme);
        return pom2;



    }

    public Vreme izracunajSledeciZaVremeNedelja(LinkedNode ln1,Vreme x){
        Vreme trenutnovreme=x;
        int i=0;
        Vreme pom2 = new Vreme();
        pom2.add(nedeljaPolasci[i]);
        pom2.add(this.izracunajVremeDolaska(ln1));
        while(pom2.jeManje(trenutnovreme) && i<brNedeljaP-1)
        {
            i++;
            pom2 = new Vreme();
            pom2.add(nedeljaPolasci[i]);
            pom2.add(this.izracunajVremeDolaska(ln1));


        }
        pom2.sub(trenutnovreme);
        return pom2;



    }

    public boolean sadrziAB(LinkedNode a,LinkedNode b){
        int i=0;
        while(i<this.brRuta){
            if(ruta[i] instanceof LinkedNode){
                LinkedNode pom=(LinkedNode) ruta[i];
                if(pom==a)
                    for(int j=i;j<this.brRuta;j++)
                    {
                        if(ruta[j] instanceof LinkedNode)
                        {
                            LinkedNode pom2=(LinkedNode) ruta[j];
                            if(pom2==b)
                                return true;
                        }
                    }
            }
            i++;
        }


        return false;
    }

}
