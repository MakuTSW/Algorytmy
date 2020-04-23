// Piotr Makosiej-1

//package source;
import java.util.Scanner;
public class Source {
    public static int RekPier(int w,long tab[][], int low, int upp,int key)
    {
        if(low+1<upp)
        {
            int curr=(low+upp)/2;
            if(tab[w][curr]>=key)
                upp=curr;
            else
                low=curr;
            return RekPier(w,tab,low,upp,key);
        }
        else
        {
            if(tab[w][low]==key) return low;
            else if(tab[w][upp]==key) return upp;
            else return -1906;
                      
        }
    }
    public static int RekOst(int w,long tab[][], int low, int upp,int key)
    {
        if(low+1<upp)
        {
            int curr=(low+upp)/2;
            if(tab[w][curr]>key)
                upp=curr;
            else
                low=curr;
            return RekOst(w,tab,low,upp,key);
        }
        if(tab[w][upp]==key) return upp;
        else if(tab[w][low]==key) return low;
        else return -1906;
    }
    public static int IterPier(int w,long tab[][], int low, int upp,int key)
    {
        while(low+1<upp)
        {
            int curr=(low+upp)/2;
            if(tab[w][curr]>=key)
                upp=curr;
            else
                low=curr;
        }
        if(tab[w][low]==key) return low;
        else if(tab[w][upp]==key) return upp; 
        else return -1906;
        
    }
    public static int IterOst(int w,long tab[][], int low, int upp,int key)
    {
        while(low+1<upp)
        {
            int curr=(low+upp)/2;
            if(tab[w][curr]>key)
                upp=curr;
            else
                low=curr;
        }
        if(tab[w][upp]==key) return upp;
        else if(tab[w][low]==key) return low;
        else return -1906;
    }
    
    
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
       int test= scan.nextInt();
       for(int i=0;i<test;i++)
       {
           
           int wiersz= scan.nextInt();
           int kolumna= scan.nextInt();
           long[][] tab= new long[wiersz][kolumna]; 
           for(int k=0;k<wiersz;k++)
               for(int j=0;j<kolumna;j++)
               {
                   tab[k][j]=scan.nextLong();
               }
           int intToFind=scan.nextInt();
           //System.out.print(intToFind);
           //---------------------------------RekPier: start
           boolean flag=false;
           for(int k=0;k<wiersz;k++)
           {
                int t=RekPier(k,tab,0,kolumna-1,intToFind);
                if(t!=-1906)
                {
                //System.out.println(t);
                if(t==-1)
                    t=0;
                if(t<kolumna)
                if(tab[k][t]==intToFind)
                {
                    System.out.println("RekPier: " + intToFind + " w " + "(" + k + "," + t + ")");
                    flag=true;
                    break;
                }
                }
           }
           if(flag==false)
               System.out.println("RekPier: nie ma " + intToFind);
           //--------------------------------RekPier: koniec
           //--------------------------------RekOst: start
           flag=false;
           for(int k=wiersz-1;k>=0;k--)
           {
               int t=RekOst(k,tab,0,kolumna-1,intToFind);
               if(t!=-1906)
               {
               if(t==-1)
                   t=0;
               if(t<kolumna)
                   if(tab[k][t]==intToFind)
                   {
                       System.out.println("RekOst: " + intToFind + " w " + "(" + k + "," + t + ")");
                    flag=true;
                    break;
                   }
               }
           }
           if(flag==false)
               System.out.println("RekOst: nie ma " + intToFind);
           //--------------------------------RekOst: koniec
           //--------------------------------IterPier: start
           flag=false;
           for(int k=0;k<wiersz;k++)
           {
                int t=IterPier(k,tab,0,kolumna-1,intToFind);
                if(t!=-1906){
                //System.out.println(t);
                if(t==-1)
                    t=0;
                if(t<kolumna)
                if(tab[k][t]==intToFind)
                {
                    System.out.println("IterPier: " + intToFind + " w " + "(" + k + "," + t + ")");
                    flag=true;
                    break;
                }
                }
           }
           if(flag==false)
               System.out.println("IterPier: nie ma " + intToFind);
           //--------------------------------IterPier: koniec
           //--------------------------------IterOst: start
           flag=false;
           for(int k=wiersz-1;k>=0;k--)
           {
               int t=IterOst(k,tab,0,kolumna-1,intToFind);
               if(t!=-1906)
               {
               if(t==-1)
                   t=0;
               if(t<kolumna)
                   if(tab[k][t]==intToFind)
                   {
                       System.out.println("IterOst: " + intToFind + " w " + "(" + k + "," + t + ")");
                    flag=true;
                    break;
                   }
               }
           }
           if(flag==false)
               System.out.println("IterOst: nie ma " + intToFind);
           //--------------------------------IterOst: koniec
           
           System.out.println("---");
       }
    }
    
}
/* testy
3
3 4
10 10 10 10
10 20 20 30
20 20 20 40
20
3 4
10 10 10 10
10 20 20 30
20 20 20 40
50
3 3
10 10 10
10 20 20
20 20 20
10

3
1 3
10 10 10
10
1 4
1 1 1 2
2
2 3
1 2 2
2 3 3
2

2
3 3
1 2 3
2 3 4
3 4 5
3
1 1
2
2

1
1 1
2
3

2 2 2 2 2 2 2 2 2 2 2 2 2 2 2
*/