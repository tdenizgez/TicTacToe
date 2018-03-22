
package prolabyedek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



class oyuncu
{

    String kullaniciAdi;
    boolean insan_mi;
    char harf;


    public oyuncu()
    {
        Scanner scan = new Scanner(System.in);

        insan_mi = true;
        harf = 'X';
        if(insan_mi == true)
        {
            System.out.println("Kullanýcý Adýnýzý Giriniz.");
            this.kullaniciAdi=scan.nextLine();
        }
    }

    public oyuncu(boolean insanKontrol)
    {

        Scanner scan = new Scanner(System.in);

        if(insanKontrol == true)
        {
            System.out.println("Kullanýcý Adýnýzý Giriniz.");
            this.kullaniciAdi=scan.nextLine();
        }

        insan_mi = insanKontrol;
        if(insan_mi == true)
            harf = 'X';
        else
            harf = 'O';

    }

    public oyuncu(boolean insanKontrol,char harf)
    {

        Scanner scan = new Scanner(System.in);
        if(insanKontrol == true)
        {
            System.out.println("Kullanýcý Adýnýzý Giriniz.");
            this.kullaniciAdi=scan.nextLine();
        }

        insan_mi=insanKontrol;
        this.harf=harf;
    }

    char KarakteriAl() //Nesnenin Karakterini Aldýðýmýz Fonksiyon 3. Contructor Ýçin Gerekli
    {
        return this.harf;
    }

    boolean oyuncuTurunuAl()
    {
        return insan_mi==true; //insansa evet degilse hayir
    }

    Integer [] bilgisayarHamlesiUret(int boyut) //Bilgisayar Hamlesi Ureten Fonksiyon
    {
        Integer [] koordinatB = new Integer[2];

        koordinatB[0]=((int) (Math.random() * boyut));
        koordinatB[1]=((int) (Math.random() * boyut));

        return koordinatB;
    }

    Integer [] oyuncuHamlesi() //Oyuncunun Hamlesini Alan Fonksiyon
    {
        Integer [] koordinatO = new Integer[2];
        Scanner scan1 = new Scanner(System.in);
        System.out.print("X Koordinatý:");
        koordinatO[0]=scan1.nextInt();
        System.out.print("Y Koordinatý:");
        koordinatO[1]=scan1.nextInt();
        return koordinatO;
    }

}


 class oyunTahtasi
{
    int boyut;
    char oyunTahtasi[][];
    Scanner scan = new Scanner(System.in);

    public oyunTahtasi()
    {
        this.boyut=boyutSec();
        this.oyunTahtasi = new char[this.boyut][this.boyut];
        tahtaOlustur();
    }

    public oyunTahtasi(char [][]oyunTahtasi,int boyut)
    {
        this.oyunTahtasi = oyunTahtasi.clone();
        this.boyut=boyut;
    }



    char [][] oyunTahtasiniAl()
    {
        return this.oyunTahtasi;
    }

    void oyunTahtasiniYazdir()
    {
        for (int i = 0; i <this.boyut; i++)
        {
            System.out.print(" "+i);
        }
        System.out.println("");
        for (int i = 0; i < this.boyut; i++)
        {
            System.out.print(""+i);
            for (int j = 0; j < this.boyut; j++)
            {

                System.out.print(this.oyunTahtasi[i][j]+" ");

            }
            System.out.println("");
        }
    }

    void tahtaOlustur()
    {
        for (char[] oyunTahtasi1 : this.oyunTahtasi)
        {
            for (int j = 0; j < this.boyut; j++)
            {
                oyunTahtasi1[j] = '.';
            }
        }
    }


    boolean hamleyiYaz(int x1, int y1,char harf)
    {

        if(this.oyunTahtasi[x1][y1]=='.' )
        {
            this.oyunTahtasi[x1][y1]=harf;
            return true;
        }
        else
        {
            System.out.println("Girdiðiniz Koordinatlar Doludur Tekrar Giriniz.");
            return false;
        }
    }
    
    
     int boyutSec()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Matrisin Boyutunu Giriniz:");
        int secim = scan.nextInt();
        while(secim!=3 && secim!=5 && secim!=7)
        {
            System.out.println("Yanlis Deger Girdiniz Tekrar Giris Yapiniz.");
            secim = scan.nextInt();
        }
        return secim;
    }

     
     ///KARÞILAÞTIRMA KONTROLÜ
    boolean kazananOyuncu(char harf,int x,int y)  //Oyuncu Oyunu Kazandýysa True Aksi Durumda False Döndürür
    {
        int MatrisBoyutu;
        int TekrarSayisi;
        int x1,y1;
        
        if(boyut == 3)
            MatrisBoyutu = 3;
        else MatrisBoyutu = 4;
        
        TekrarSayisi=1;
        for(x1=x,y1=y;y1+1<this.boyut && TekrarSayisi!=MatrisBoyutu && oyunTahtasi[x1][y1+1]==harf;TekrarSayisi++,y1++){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        for(x1=x,y1=y;y1-1>=0 && TekrarSayisi!=MatrisBoyutu && oyunTahtasi[x1][y1-1]==harf;TekrarSayisi++,y1--){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        TekrarSayisi=1;
        for(x1=x,y1=y;x1+1<this.boyut && TekrarSayisi!=MatrisBoyutu&& oyunTahtasi[x1+1][y1]==harf;TekrarSayisi++,x1++){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        for(x1=x,y1=y;x1-1>=0 && TekrarSayisi!=MatrisBoyutu&& oyunTahtasi[x1-1][y1]==harf;TekrarSayisi++,x1--){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        TekrarSayisi=1;
        for(x1=x,y1=y;x1-1>=0 && y1-1>=0 && TekrarSayisi!=MatrisBoyutu&& oyunTahtasi[x1-1][y1-1]==harf;TekrarSayisi++,x1--,y1--){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        for(x1=x,y1=y;x1+1<this.boyut && y1+1<this.boyut && TekrarSayisi!=MatrisBoyutu&& oyunTahtasi[x1+1][y1+1]==harf;TekrarSayisi++,x1++,y1++){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        TekrarSayisi=1;
        for(x1=x,y1=y;x1-1>=0 && y1+1<this.boyut && TekrarSayisi!=MatrisBoyutu&& oyunTahtasi[x1-1][y1+1]==harf;TekrarSayisi++,x1--,y1++){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        for(x1=x,y1=y;x1+1<this.boyut && y1-1>=0 && TekrarSayisi!=MatrisBoyutu&& oyunTahtasi[x1+1][y1-1]==harf;TekrarSayisi++,x1++,y1--){}
        if(TekrarSayisi==MatrisBoyutu)
            return true;
        
        return false;
    }


    public boolean beraberlikKontrol(){
        int i,j;
        int say=0;
        for(i=0;i<this.boyut;i++)
        {
            for(j=0;j<this.boyut;j++)
            {
                if(oyunTahtasi[i][j]=='.')
                {
                    say++;
                }
            }
        }
        if(say==0)
            return false;
        
        return true;
    }
}





class TicTacToe
{


    static int boyutSec()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Matrisin Boyutunu Giriniz:");
        int secim = scan.nextInt();
        while(secim!=3 && secim!=5 && secim!=7)
        {
            System.out.println("Yanlis Deger Girdiniz Tekrar Giris Yapiniz.");
            secim = scan.nextInt();
        }
        return secim;
    }

    public static void main(String[] args) throws IOException
    {


        Scanner scan = new Scanner(System.in);
        System.out.println("Yeni oyuna baþlamak için 1'i Kayitli Oyundan Devam Etmek Ýçin 2'yi Seciniz:");


        File file = new File("dosya.txt");
        if (!file.exists())
        {
            file.createNewFile();
        }

        switch (scan.nextInt())
        {
        case 1:
            
            oyunTahtasi tahta = new oyunTahtasi();
            oyuncu insan;
            oyuncu bilgisayar = null;
            System.out.println("Harf Secmek icin 1 giriniz.(aksi durumda kullanici X bilgisayar O koyacaktir.");
            if(scan.nextInt()==1){
                System.out.println("Harf Seciniz X/O");
                String secimHarf = scan.next();
                char harff = secimHarf.charAt(0);
                 insan = new oyuncu(true, harff);
                
                if(insan.harf=='X'){
                     bilgisayar = new oyuncu(false,'O');
            }
                else if(insan.harf == 'O'){
                     bilgisayar = new oyuncu(false,'X');
                }
            
            }
            else{
             insan = new oyuncu(true);//X koyar
             bilgisayar = new oyuncu(false);//O koyar
            }
            tahta.oyunTahtasiniYazdir();
            
            Integer dizi[] = new Integer[2];
            int i = 0;
            while(i!=tahta.boyut*tahta.boyut)
            {


                while(true)
                {
                    dizi = insan.oyuncuHamlesi().clone();
                    if(tahta.hamleyiYaz(dizi[0], dizi[1], insan.harf))
                        break;
                    
                }
                
                
                System.out.println("Ýnsan Hamlesi Sonrasý:");
                tahta.oyunTahtasiniYazdir();
                
                if(tahta.kazananOyuncu(insan.harf,dizi[0],dizi[1])==true)
                {
                    System.out.println("Oyuncu Kazandi");
                    break;
                }
                
                if(!tahta.beraberlikKontrol()){
                    System.out.println("Oyun Berabere.");
                    break;
                }

                while(true)
                {
                    dizi = bilgisayar.bilgisayarHamlesiUret(tahta.boyut).clone();
                    if(tahta.hamleyiYaz(dizi[0], dizi[1], bilgisayar.harf))
                        break;
                    
                }
                
                System.out.println("Bilgisayar Hamlesi Sonrasi:");
                tahta.oyunTahtasiniYazdir();
                
                if(tahta.kazananOyuncu(bilgisayar.harf,dizi[0],dizi[1])==true)
                {
                    System.out.println("Bilgisayar Kazandi");
                    break;
                }
                
                
                if(!tahta.beraberlikKontrol()){
                    System.out.println("Oyun Berabere.");
                    break;
                }

                System.out.println("Oyunu Kaydedip çýkmak için 9 giriniz:");

                if(scan.nextInt()==9)
                {
                    FileWriter fileWriter = new FileWriter(file, false);
                    BufferedWriter bWriter = new BufferedWriter(fileWriter);
                    
                    bWriter.write(insan.harf);
                    char ch = Character.forDigit(tahta.boyut, 10);
                    bWriter.write(ch);
                    
                    for (int j = 0; j < tahta.boyut; j++)
                        for (int k = 0; k < tahta.boyut; k++)
                         bWriter.write(tahta.oyunTahtasi[j][k]);

                    bWriter.close();
                    
                    System.out.println("Program Sonlandýrýldý.");
                    System.exit(1);
                }

            }
            break;///Oyunu Kaydedip Çýktýðýmýz Switch Döngüsünün Breaki.///Oyunu Kaydedip Çýktýðýmýz Switch Döngüsünün Breaki.

        case 2://KAYITLI OYUNDAN BASLARSA
            
            

            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();
            int boyutTut=Character.digit(line.charAt(1), 10);
            
            char oku [][] = new char[boyutTut][boyutTut];

            int t=2;
            for (int j = 0; j < oku.length; j++)
            {
                for (int k = 0; k < oku.length; k++)
                {
                    oku[j][k]=line.charAt(t);
                    t++;
                }
            }

            br.close();

            oyunTahtasi tahtaKayitli = new oyunTahtasi(oku,boyutTut);

            oyuncu insanKayitli = new oyuncu(true,line.charAt(0));
            
            oyuncu bilgisayarKayitli = null;
            
            if(line.charAt(0)=='X'){
             bilgisayarKayitli = new oyuncu(false,'O');
            }
            else if(line.charAt(0)=='O'){
                
             bilgisayarKayitli = new oyuncu(false,'X');
            }
            
            tahtaKayitli.oyunTahtasiniYazdir();
            Integer dizi1[] = new Integer[2];
            i = 0;
            while(i!=tahtaKayitli.boyut*tahtaKayitli.boyut)
            {


                while(true)
                {
                    dizi1 = insanKayitli.oyuncuHamlesi().clone();
                    if(tahtaKayitli.hamleyiYaz(dizi1[0], dizi1[1], insanKayitli.harf))
                    {
                        tahtaKayitli.oyunTahtasiniYazdir();
                        break;
                    }
                }

                if(tahtaKayitli.kazananOyuncu(insanKayitli.harf,dizi1[0],dizi1[1])==true)
                {
                    System.out.println("Oyuncu Kazandi");
                    break;
                }

                System.out.println("Ýnsan Hamlesi Sonrasý:");
                tahtaKayitli.oyunTahtasiniYazdir();
                
                
                if(!tahtaKayitli.beraberlikKontrol()){
                    System.out.println("Oyun Berabere.");
                    break;
                }

                while(true)
                {
                    dizi1 = bilgisayarKayitli.bilgisayarHamlesiUret(tahtaKayitli.boyut).clone();
                    if(tahtaKayitli.hamleyiYaz(dizi1[0], dizi1[1], bilgisayarKayitli.harf))
                    {
                        tahtaKayitli.oyunTahtasiniYazdir();
                        break;
                    }
                }
                if(tahtaKayitli.kazananOyuncu(bilgisayarKayitli.harf,dizi1[0], dizi1[1])==true)
                {
                    System.out.println("Bilgisayar Kazandi");
                    break;
                }

                System.out.println("Bilgisayar Hamlesi Sonrasi:");
                tahtaKayitli.oyunTahtasiniYazdir();

                
                    if(!tahtaKayitli.beraberlikKontrol()){
                    System.out.println("Oyun Berabere.");
                    break;
                }

                
                System.out.println("Oyunu Kaydedip çýkmak için 9 giriniz:");

                if(scan.nextInt()==9)
                {
                    FileWriter fileWriter = new FileWriter(file, false);
                    BufferedWriter bWriter = new BufferedWriter(fileWriter);
                    
                    bWriter.write(insanKayitli.harf);
                    char ch = Character.forDigit(tahtaKayitli.boyut, 10);
                    bWriter.write(ch);
                    
                    for (int j = 0; j < tahtaKayitli.boyut; j++)
                        for (int k = 0; k < tahtaKayitli.boyut; k++)
                         bWriter.write(tahtaKayitli.oyunTahtasi[j][k]);

                    bWriter.close();
                    
                    System.out.println("Program Sonlandýrýldý.");
                    System.exit(1);
                }

            }


            break;
        default:
            System.out.println("Hatali Giris Yaptiniz.");
            break;
        }

    }
}