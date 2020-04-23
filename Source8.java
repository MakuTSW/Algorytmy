// Piotr Makosiej -1
//package source;
//import java.util.Random;
import java.util.Scanner; 
public class Source {
    public static void swap( int p,int  q, int A[] ) 
    {
        int temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    } 
    public static void Sort(int A[],int L,int R)
    {
        
        for (int k = R-1 ; L<k; k--) 
        for (int j = L; j<k; j++)
        {
            //System.out.println(k + " " + j +" "+ n);
            if( A[j] > A[j+1] ) 
            swap (j, j+1,A) ; 
        }
    }

   public static int Partition(int[] A, int x, int L, int R) { 
        int i = L-1;

        for ( int j = L; j < R; j++)

        if (A[j] <= x ) {
        i++;
        swap (i, j,A);
        }
        //swap(i+!,R, A);
        return (i+1);
       }

   
    public static int Select(int A[], int L,int R,int k)
    {
        
        int length=0;
        if(R-L<50)
        {
            Sort(A,L,R);
            return A[L+k];
        }
        for(int i=L; i<R; i+=5)
            {
                if (i + 5 <= R)
                {
                    Sort(A, i, i+5);
                    swap(L+length++, i+5/2, A);
                }
                else
                {
                    Sort(A, i, R);
                    swap(L+length++, (i+R)/2, A);
                }
            }
        int m=Select(A,L,L+length,length/2);
        int f = Partition(A, m - 1, L, R);        
        int last = Partition(A, m, f,R);        
       // System.out.println(m +" "+f+" "+last);
        if (k < f-L) return Select (A,L,f,k);
        if (k < last-L) return m;
        return Select (A,last,R,L+k-last);
    }
    
   /* public static void main(String[] args) {
        int[] ar = new int[] { 0, 65, 6, 19, 22, 85, 14, 38, 26, 57, 38, 80, 22, 92, 36, 95, 100, 23, 71, 36, 76 };
        
        System.out.println(Select(ar,ar.length-1,20,1,0));
    }*/
    /*public static void main(String[] args) {
        int[] ar = new int[10001];
        
        ar[0] = 0;
        for(int i = 0; i < ar.length - 1; i++)
        {
            ar[i+1] = i;
        }
        
        System.out.println(Select(ar,ar.length-1,50000,1,0));
    }*/
    /*public static void main(String[] args) {
        Random r = new Random();
        for(int i = 1; i < 1000000; i++)
        {
            int[] ar = new int[i];
            System.out.println(i);
            
            
            for(int j = 0; j < i; j++)
            {
                ar[j] = r.nextInt();
            }
            
            Select(ar,ar.length-1,i/2,1,0);
        }
    }*/
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int testy=scan.nextInt();
       int[] tab;
       for(int i=0;i<testy;i++)
       {
           int n=scan.nextInt();
           tab = new int[n+1];
           for(int j=0;j<n;j++)
               tab[j]=scan.nextInt();
           int m=scan.nextInt();
           for(int j=0;j<m;j++)
           {
               int k=scan.nextInt();
               if(k<1 || k>n)
                   System.out.println(k + " brak");
               else
               {
                   int x=Select(tab,0,n,k-1);
                   System.out.println(k + " " + x);
               }
               
           }
       }
    }
    
}
/*
wejscie:
1
10
1 2 3 4 5 6 7 8 9 10
3
1 6 9
wyjscie:
1 1
6 6
9 9
wejscie:
1 250
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 101 102 103 104 105 106 107 108 109 110 111 112 113 114 115 116 117 118 119 120 121 122 123 124 125 126 127 128 129 130 131 132 133 134 135 136 137 138 139 140 141 142 143 144 145 146 147 148 149 150 151 152 153 154 155 156 157 158 159 160 161 162 163 164 165 166 167 168 169 170 171 172 173 174 175 176 177 178 179 180 181 182 183 184 185 186 187 188 189 190 191 192 193 194 195 196 197 198 199 200 201 202 203 204 205 206 207 208 209 210 211 212 213 214 215 216 217 218 219 220 221 222 223 224 225 226 227 228 229 230 231 232 233 234 235 236 237 238 239 240 241 242 243 244 245 246 247 248 249 

4 1 10 20 40
wyjscie:
1 0
10 9
20 19
40 39
*/