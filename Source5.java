//Piotr Makosiej -1
//package source;

class NSum {

    static class func
    {
        long sum;
        int tabindex;
        int index;
        func(int a,int b,long c)
        {
            index=a;
            tabindex=b;
            sum=c;
        }
    }
        static class stack 
            {
              int maxSize; 
              func [] Tab; 
              int top; 
            //--------------------------------------------------------------
             public stack(int n) 
             {
             maxSize = n; 
             Tab = new func[maxSize]; 
             top = -1; 
             }
             public void push(func p) 
             {
             Tab[++top] = p;
             }
           
             public void pop() 
             {
             //return Tab[top--];
                 top--;
             }
             public func top()
             {
             return Tab[top];
             }
        }
    static int[] createPowers(int max, int n) {
        int[] res = new int[max];
        res[res.length - 1] = 1;

        for (int i = 0; i < res.length - 1; i++) {
            res[i] = (int) Math.pow(max - i, n);
        }

        return res;
    }

    public static int[] findNComponentsRec(int x, int n) {
        int max = (int) Math.ceil(Math.pow(x, 1. / n));
        int[] powers = createPowers(max, n);
        int[] tab= new int[powers.length];
        return _findNComponentsRec(x, n, powers, 0, 0, tab, 0);
        
    }
    /*static void print(int[] tab, int pow, int l)
    {
        for(int i=0;i<l;i++)
            System.out.print((pow-tab[i]) + " ");
        
        System.out.println();
    }*/
    public static int getnextint(int x, int n, long sum, int length)
    {
        return length-(int)Math.ceil(Math.pow(x-sum, 1. /n));
    }
    static int[] _findNComponentsRec(int x, int n, int[] powers, long sum, int index, int[] tab,int tabindex) {
        
        sum+=powers[index];
        //System.out.print(sum + " ");
        tab[tabindex]=powers.length-index;
//print(tab, tab.length,tabindex+1);
        if(sum<x)
        {
            int newindex = getnextint(x,n,sum,powers.length);
            //System.out.print(newindex + " " + index);
            if(index>=newindex)
            {
                newindex=index+1;
            }
            if(newindex<powers.length &&  tabindex+1<tab.length)
            {
                int[] r = _findNComponentsRec(x,n,powers,sum,newindex,tab,tabindex+1);
            
                if(r.length > 0)
                {
                    return r;
                }
                
            }
            
        }
        else if(sum==x)
        {
            int[] r = new int[tabindex+1];
            /*for(int i=0;i<r.length;i++)
            {
                r[i]=powers.length-tab[i];
            }*/
            System.arraycopy(tab, 0, r, 0, r.length);
            return r;
        }
            sum-=powers[index];
            int newindex = getnextint(x,n,sum,powers.length);
            //System.out.print(newindex + " " + index);
            if(index<newindex)
            {
                index=newindex;
            }
            else
            {
                index++;
            }
            if(index<powers.length)
            {
                return _findNComponentsRec(x,n,powers,sum,index,tab,tabindex);
            }
            else
            {
                return new int[0];
            }
        
    }
    
        public static int[] findNComponentsIter(int x, int n) {
            int max = (int) Math.ceil(Math.pow(x, 1. / n));
            int[] powers = createPowers(max, n);
            int[] tab= new int[powers.length];
            //System.out.print(Math.pow(2, powers.length)-1);
            stack s= new stack(max);
            s.push(new func(0,0,0));
            while(s.top!=-1)
            {
                func f = s.top();
                tab[f.tabindex]=f.index;
                f.sum+=powers[f.index];
                if(f.sum<x){
                    int newindex = getnextint(x,n,f.sum,powers.length);
                    if(f.index>=newindex)
                    {
                        newindex=f.index+1;
                    }
                    if(newindex<powers.length &&  f.tabindex+1<tab.length)
                    {
                        func r = new func(newindex,f.tabindex+1,f.sum);
                        s.push(r);
                        f.sum-=powers[f.index];
                        newindex = getnextint(x,n,f.sum,powers.length);
                        if(f.index>=newindex)
                        {
                            newindex=f.index+1;
                        }
                        f.index=newindex;
                        continue;
                    }
                
             }else if(f.sum==x)
            {
                int[] r = new int[f.tabindex+1];
                for(int i=0;i<r.length;i++)
                {
                    r[i]=powers.length-tab[i];
                }
                return r;
            }
            f.sum-=powers[f.index];
            int newindex = getnextint(x,n,f.sum,powers.length);
            if(f.index>=newindex)
            {
                newindex=f.index+1;
            }
            f.index=newindex;
            if(f.index<powers.length)
            {
                func r = new func(newindex,f.tabindex,f.sum);
                s.pop();
                s.push(r);
                
            }
            else
            {
                s.pop();
            }
            }
            return new int[0];
}
}

/*public class Source {

    static void print(int[] tab)
    {
        for(int i=0;i<tab.length;i++)
            System.out.print(tab[i] + " ");
        
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = NSum.findNComponentsRec(25, 2); // arr1 = {4, 3}
        print(arr);
        arr = NSum.findNComponentsRec(225, 3); // arr2 = {6, 2, 1}
        print(arr);
        arr = NSum.findNComponentsRec(10000, 4); // arr3 = {10}
        print(arr);
        arr = NSum.findNComponentsRec(399, 2); // arr4 = {19, 5, 3, 2}
        print(arr);
        arr = NSum.findNComponentsRec(21, 3); // arr5 = {}
        print(arr);
        arr = NSum.findNComponentsIter(25, 2); // arr6 = {4, 3}
        print(arr);
        arr = NSum.findNComponentsIter(225, 3); // arr7 = {6, 2, 1}
        print(arr);
        arr = NSum.findNComponentsIter(10000, 4); // arr8 = {10}
        print(arr);
        arr = NSum.findNComponentsIter(399, 2); // arr9 = {19, 5, 3, 2}
        print(arr);
        arr = NSum.findNComponentsIter(21, 3); // arr10 = {}
        print(arr);
        
        arr = NSum.findNComponentsIter(1024, 6); // arr = {}
        print(arr);
        arr = NSum.findNComponentsIter(13, 2); // arr = {3,2}
        print(arr);
        arr = NSum.findNComponentsRec(4921, 3); // arr5 = {17,2}
        print(arr);
        
    }

}*/
