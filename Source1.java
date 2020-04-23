//Makosiej Piotr -grupa 1
//package source;
import java.util.Scanner;
/**
 *
 * @author Admin
 */

public class Source {
    public static long[] kadane(int[] tab, int n){
        int p = 0;
        int k=0;
        long maks = 0;
        long curr = 0; 
        int p2 = 0;
        int pole=Integer.MAX_VALUE;
        boolean flag = false;
        for (int i=0; i < n; i++){
            flag |= 0 <= tab[i];
            curr = curr + tab[i];
            //System.out.println(curr);
            if(curr == maks)
           {
               //System.out.println("jj");
               if(i-p2<pole || (pole == i - p2 && (p2 < p || (p2 == p && i < k))))
               {
                   p = p2;
                   k = i;
                   pole=i-p2;
               }
           }
           if (curr <= 0) {
           curr = 0;
           p2 = i+1;
           }
           else
           if (curr > maks) {
           maks = curr;
           p = p2;
           k = i;
           pole=i-p;
           }
        }
        long result[]=new long[3];
        //System.out.println(maks+" " + p+" " + k);
        result[0]=maks;
        result[1]=p;
        result[2]=k;
        return flag ? result : null;
    } 


     Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int test= scan.nextInt();
        int licznik=0;
        while(licznik<test)
        {
            long pole = Long.MAX_VALUE;
            int flag = 0;
            
            int wiersze = scan.nextInt();
            int kolumny = scan.nextInt();
            int temp[]= new int[kolumny];
            int tab[][] = new int[wiersze][kolumny];
            long result[]=new long[3];
            long maks=0,plus=0, finalLeft=0, finalRight=kolumny, finalTop=0, finalBottom=wiersze,start,finish; 
            for(int i=0;i<wiersze;i++)
                for(int j=0;j<kolumny;j++){
                    
                    tab[i][j]=scan.nextInt();
                    if(tab[i][j]>=0)
                        plus=1;
                }
            
            for(int i=0;i<wiersze;i++)
            {
                for(int k=0;k<kolumny;k++)
                    temp[k]=0;
                
                for(int j=i;j<wiersze;j++)
                {
                    for(int k=0;k<kolumny;k++)
                    temp[k]=temp[k]+tab[j][k];
                    result=kadane(temp,kolumny);
                    
                    if(result == null) { continue; }
                    if (result[0] > maks)  
                    {  
                        flag=1;
                        maks = result[0];  
                        finalLeft = result[1];  
                        finalRight = result[2];  
                        finalTop = i;  
                        finalBottom = j;
                        pole=(1+finalBottom-finalTop)*(1+finalRight-finalLeft);
                        
                    }else if(result[0]==maks)
                    {
                       // System.out.println("jestem");
                        if(pole>((1+j-i)*(1+result[2]-result[1])) || (pole == ((1+j-i)*(1+result[2]-result[1])) && (i < finalTop || (i == finalTop && (j < finalBottom || (j == finalBottom && (result[1] < finalLeft || (result[1] == finalLeft && (result[2] < finalRight)))))))))
                        {
                            //System.out.println("jestem" + maks);
                            flag=1;
                            finalLeft = result[1];  
                            finalRight = result[2];  
                            finalTop = i;  
                            finalBottom = j;
                            pole=(1+finalBottom-finalTop)*(1+finalRight-finalLeft);
                            
                        }
                            
                    }
                }
                    
            }
            if(flag==1 & plus==1)
            {
                System.out.println("[" + finalTop + ".." + finalBottom +  "," + finalLeft + ".." + finalRight + "]");   
                System.out.println("max_sum=" + maks);
            }else
                System.out.println("empty");
            licznik++;   
        }
    }
    
}

/*
1
1 6
1 -1 3 4 -1 7
[0..0,2..5]
max_sum=13

2
4 4
1 3 -7 4
3 3 3 3
-10 1 1 1
1 1 1 1
1 1 
-1
[1..3,1..3]
max_sum=15
empty

1
4 4
-1 -1 1 1
1 1 -1 -1
-1 -1 -1 -1
-1 1 1 -1
[0..0,2..3]
max_sum=2
*/