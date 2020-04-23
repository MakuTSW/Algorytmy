// Piotr Makosiej -1
//package source;
import java.util.Scanner; 
public class Source {
    public static class element
    {
        int value;
        int amount=1;
        int onheap=-1;
        element(int n)
        {
            value=n;
        }
    }
    public static class func
    {
        int x=0;
        int amount=0;
        element[] tab = new element[10000];
        Binary_tree binarytree=new Binary_tree();
        Heap heap= new Heap();
        func(){};
        void insert(int n,int r,int s)
        {
            if(amount>=s)
                    return;
            int a=binarytree.SEARCH(n);
            if(a==-1)
            {
                if(r<=x)
                return;
                element e= new element(n);
                tab[x]=e;
                int k=heap.upheap(x,tab);
                binarytree.INSERT(tab[k]);
                x++;
                amount++;
            }
            else
            {
                if(amount>=s)
                    return;
                tab[a].amount++;
                amount++;
                heap.upheap(a, tab);
            }
            
        }
        void GetMax(int n)
        {
            /*System.out.print("getmax: ");
            for(int i=0;i<x;i++)
                System.out.print(tab[i].value + " "+ tab[i].amount + " " +tab[i].onheap + ", ");
            System.out.print("----");*/
            if(x==0)
            {
                System.out.println("0 0");
                return;
            }
            if(tab[0].amount<=n)
            {
                System.out.println(tab[0].value + " " + tab[0].amount);
                amount-=tab[0].amount;
                binarytree.DELETE(tab[0]);
                tab[0]=tab[x-1];
                int k=heap.downheap(0, x-1, tab);
                x--;
                
                
            }
            else
            {
                
                System.out.println(tab[0].value + " " + n);
                tab[0].amount-=n;
                amount-=n;
                heap.downheap(0, x, tab);
            }
        }
        void sort()
        {
            /*System.out.print("sort: ");
            for(int i=0;i<x;i++)
                System.out.print(tab[i].value + " "+ tab[i].amount + " ");
            System.out.print("----");*/
            if(x==0)
            {
                System.out.println("0 0");
                return;
            }
            heap.HeapSort(x, tab);
            for(int i=x-1;i>=0;i--)
                System.out.print(tab[i].value + " " + tab[i].amount + " ");
            System.out.println();
        }
    }
    public static class Heap
    {
        int upheap (int k,element a[])
        {
            int i = (k-1)/2;
            element tmp = a[k];
            while (k>0 && (a[i].amount<tmp.amount || (a[i].amount==tmp.amount && a[i].value<tmp.value)))
            {
                a[k] = a[i];
                a[k].onheap=k;
                k = i ; 
                i = (i-1)/2; 
            }
            a[k] = tmp;
            a[k].onheap=k;
            return k;
        }
        int downheap (int k, int n,element a[]) 
        {
            int j;
            element tmp = a[k];
            while ( k < n / 2 ) 
            {
                j = 2*k+1; 
                if ( j<n-1 && (a[j].amount<a[j+1].amount || (a[j].amount==a[j+1].amount && a[j].value<a[j+1].value)) ) j++;
                if (tmp.amount > a[j].amount || (tmp.amount == a[j].amount && tmp.value >= a[j].value) ) break; 
                a[k] = a[j] ;
                a[k].onheap=k;
                k = j ;
            }
            /*if(tmp.amount<=0 && k!=n-1)
            {
                //System.out.print(a[k].value + " " + a[n-1].value);
                a[k]=a[n-1];
                a[k].onheap=k;
                k=n-1;
            }*/
            a[k] = tmp;
            a[k].onheap=k;
            return k;
       }
        void HeapSort (int n,element a[]) 
        {
         for (int k = (n-2)/2; k >= 0; k--)
         downheap (k, n,a);
         while (n > 0) {
             element s=a[0];
             a[0]=a[n-1];
             a[n-1]=s;
         //swap (a[0], a[n-1]); 
         n-- ;
         downheap (0, n,a);
         }
        }

    }
    public static class Node 
    {
        public element info; 
        public Node left;
        public Node right;
        public Node(element info)
        {
            this.info = info;
            this.left = null;
            this.right = null;
        }
    }
    public static class Binary_tree
    {
        Node root=null;
        int SEARCH(int n)
        {
            //System.out.print("j");
            Node iterator=root;
            while(iterator!=null)
            {
                if(n==iterator.info.value)
                        return iterator.info.onheap;
                    if(iterator.info.value>n)
                        iterator=iterator.left;
                    else
                        iterator=iterator.right;
            }
           // System.out.print("jestem");
            return -1;
        }
        void INSERT(element n)
        {
            Node x=new Node(n);
            if(root==null)
            {
                root=x;
            }
            else
            {
                Node iterator =root;
                Node prev=root;
                boolean flag=false,flag2=false;
                while(iterator!=null)
                {
                    if(n.value==iterator.info.value)
                    {
                        return;
                    }
                    prev=iterator;
                    if(iterator.info.value>n.value)
                    {
                        iterator=iterator.left;
                        flag=false;
                    }
                    else
                    {
                        iterator=iterator.right;
                        flag=true;
                    }
                }
                //iterator=x;
                if(flag)
                    prev.right=x;
                else
                    prev.left=x;
            }
        }
        void DELETE(element n)
        {
            Node iterator=root;
            if(iterator==null)
                   return;
            
            if(root.info.value==n.value)
            {
                //SUCCESSOR
                if(iterator.right!=null)
                {
                        iterator=iterator.right;
                    if(iterator.left==null)
                    {
                        iterator.left=root.left;
                        root=iterator;
                        return;
                    }
                    Node prev=iterator;
                    while(iterator.left!=null)
                    {
                        //if(iterator.left.left!=null)
                        prev=iterator;
                        iterator=iterator.left;
                    }
                    root.info=prev.left.info;
                    prev.left=null;
                }//SUCCESSOR END
                else if(iterator.left!=null) //PREDECESSOR
                {
                    root=iterator.left;
                }
                else
                {
                    root=null;
                }
                return;
            } // PREDECESSOR END
            //---------------------------------------------------
            Node prev=iterator;
            while(iterator!=null)
            {
                if(iterator.info.value==n.value)
                {
                    break;
                }
                prev=iterator;
                if(iterator.info.value<n.value)
                    iterator=iterator.right;
                else
                    iterator=iterator.left;       
            }
            Node toDelete=iterator;
            if(iterator!=null)
            {
                //SUCCESSOR
                if(iterator.right!=null)
                {
                    iterator=iterator.right;
                    if(iterator.left==null)
                    {
                        iterator.left=toDelete.left;
                        if(prev.right.info==toDelete.info)
                        prev.right=iterator;
                        else
                        prev.left=iterator;
                        //toDelete=iterator;
                        return;
                    }
                    Node prev2=iterator;
                    while(iterator.left!=null)
                    {
                        //if(iterator.left.left!=null)
                        prev2=iterator;
                        iterator=iterator.left;
                    }
                    //---
                    //iterator=prev2.left;
                    if(iterator.right!=null)
                    {
                        toDelete.info=prev2.left.info;
                        prev2.left=iterator.right;
                        return;
                    } 
                    //---
                    toDelete.info=prev2.left.info;
                    prev2.left=null;
                }//SUCCESSOR END
                else if(iterator.left!=null) //PREDECESSOR
                {
                        prev.left=iterator.left;
                }
                else
                {
                    if(prev.right.info==toDelete.info)
                    prev.right=null;
                    else
                    prev.left=null;
                }
            }
        }
    }

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int testy=scan.nextInt();
       for(int i=0;i<testy;i++)
       {
           func h = new func();
           int n=scan.nextInt();
           int p=scan.nextInt();
           while(true)
           {
               String op=scan.next();
               if(op.equals("i"))
               {
                   int k=scan.nextInt();
                   for(int j=0;j<k;j++)
                       h.insert(scan.nextInt(),n,p);
               }else if(op.equals("g"))
               {
                   int k=scan.nextInt();
                   h.GetMax(k);
               }else if(op.equals("s"))
               {
                   h.sort();
                   break;
               }
           }
       }
       
    
}
}

/*
1
0 0
i 1 1
g 1
s

0 0
0 0

2
3 10
i 4 1 1 1 2
g 2
g 1
s
10 10
i 3 1 2 3
g 1
g 2
g 3
s

1 2
2 1
1 1 
3 1
2 1
1 1
0 0
*/