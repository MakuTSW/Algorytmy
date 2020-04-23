// Piotr Makosiej-1
//package source;
import java.util.Scanner;


public class Source {
    public static class carriage{
        String name;
        carriage next=null;
        carriage prev=null;
    }
    
    
    public static class train{
        String name;
        carriage first=null;
        carriage last=null;
        train next=null;
        public static boolean isReversed(carriage c)
        {
            return c.prev == null || c.next == null;
        }
        public void AddFirst(String W1)
        {
            carriage w=new carriage();
            w.name=W1;
       
            if(first.prev == null)
            {
                w.next=first;
                first.prev=w;
            first=w;
            }
            else
            {
                w.prev=first;
            first.next=w;
            first=w;
            }
            
        }
        public void AddLast(String W1)
        {
            carriage w=new carriage();
            w.name=W1;
            if(last.next == null)
            {
            w.prev=last;
            last.next=w;
            last=w;
            }
            else
            {
                w.next=last;
            last.prev=w;
            last=w;
            }
            
            
        }
        
        public void Display()
        {
            boolean flag = first.next == null;
            
            carriage previous = null;
            carriage w = first;
           System.out.print(this.name + ": ");
            while(w != null)
            {
                //System.out.format("\nflag: %b, previous: %s, w: %s, w.prev: %s, w.next: %s\n", flag, previous == null ? "null" : previous.name, w == null ? "null" : w.name, w.prev == null ? "null" : w.prev.name, w.next == null ? "null" : w.next.name);
                if(previous == (flag ? w.prev : w.next))
                {
                    flag = !flag;
                }
                
                System.out.print(w.name + " ");
                previous = w;
                
                if(last == w) { break; }
                w = flag ? w.prev : w.next;
                
                
            }
         //System.out.println("\n\n\n");
        }
                
    }
    
    public static class trains{
        
        train first=null;
        
        public void New(String T1,String W1)
        {
            train t=new train();
            carriage w=new carriage();
            w.name=W1;
            t.name=T1;
            t.first=w;
            t.last=w;
            if(first==null)
            first=t;
            else{
                t.next=first;
                first=t;
            }
            
        }
        public void InsertFirst(String T1,String W1)
        {
            train t=new train();
            t=first;
            while(t!=null)
            {
                if(T1.equals(t.name))
                {
                    t.AddFirst(W1);
                    
                    break;
                }
                t=t.next;
            }
        }
        public void InsertLast(String T1,String W1)
        {
            train t=new train();
            t=first;
            while(t!=null)
            {
                if(T1.equals(t.name))
                {
                    t.AddLast(W1);
                    
                    break;
                }
                
                t=t.next;
            }
        }
        public void DelFirst(String T1,String T2)
        {
            train t=new train(), prev = null;
            t=first;
            
            while(t!=null)
            {
                if(T1.equals(t.name))
                {
                    //System.out.format("first: %s, last: %s\n", t.first.name, t.last.name);
                    if(t.first==t.last)
                    { //System.out.println("13");
                        //New(T2,t.first.name);
                   
                        
                            if(prev == null)
                            {
                                first = first.next;
                            }
                            else
                            {
                                prev.next = t.next;
                            }
                            
                            New(T2,t.first.name);
                        // todo: delete train
                    }
                    else
                    {
                        String firstName = t.first.name;
                        //pierwszy nieodwrocony
                        if(t.first.prev == null)
                        {
                            // drugi nieodwrocony
                            if(t.first.next.prev == t.first)
                            {
                                //System.out.println("Nie Nie");
                                t.first = t.first.next;
                                t.first.prev = null;
                            }
                            // drugi odwrocony (t.first.next.next == t.firsts
                            else
                            {
                                //System.out.println("Nie Tak");
                                t.first = t.first.next;
                                t.first.next = null;
                            }
                        }
                        // pierwszy odwrocony
                        else
                        {
                            if(t.first.prev.prev == t.first)
                            {
                               // System.out.println("Tak Nie");
                                t.first = t.first.prev;
                                t.first.prev = t.first.next;
                                t.first.next = null;
                            }
                            else
                            {
                                //System.out.println("Tak Tak");
                                t.first = t.first.prev;
                                t.first.next = t.first.prev;
                                t.first.prev = null;
                            }
                        }
                        
                        New(T2,firstName);
                    }
                    
                    break;
                }
                prev = t;
                t=t.next;
            }
        }
        public void DelLast(String T1,String T2)
        {
            train t=new train(), prev = null;
            t=first;
            //System.out.println("1");
            while(t != null)
            {
                if(T1.equals(t.name))
                {
                    //System.out.format("first: %s, last: %s\n", t.first.name, t.last.name);
                    if(t.first==t.last)
                    {
                        if(prev == null)
                        {
                            first = first.next;
                        }
                        else
                        {
                            prev.next = t.next;
                        }
                            
                        New(T2,t.last.name);
                    }
                    else
                    {
                        String lastName = t.last.name;
                        //ostatni nieodwrocony
                        if(t.last.next == null)
                        {
                            // przedostatni nieodwrocony
                            if(t.last.prev.next == t.last)
                            {
                                //System.out.println("Nie Nie");
                                t.last = t.last.prev;
                                
                                t.last.next = null;
                            }
                            // przedostatni odwrocony
                            else
                            {
                                //System.out.println("Tak Nie");
                                t.last = t.last.prev;
                                
                                t.last.prev = null;
                            }
                        }
                        //osstatni odwrocony
                        else
                        {
                            // przedostatni nieodwrocony
                            if(t.last.next.prev == t.last)
                            {
                                //System.out.println("Nie Tak");
                                t.last = t.last.next;
                               // t.last.next = t.last.prev;
                                t.last.prev = null;
                            }
                            // przedostatni odwrocony
                            else
                            {
                                //System.out.println("Tak Tak");
                                t.last = t.last.next;
                                //t.last.prev = t.last.next;
                                t.last.next = null;
                            }
                        }
                        
                        New(T2,lastName);
                        // przedostatni nieodwrocony
                        /*if(t.last.prev.next == t.last)
                        {
                            //ostatni nieodwrocony
                            if(t.last.next == null)
                            {
                                t.last = t.last.prev;
                                t.last.next = null;
                            }
                            //osstatni odwrocony
                            else
                            {
                                t.last = t.last.prev;
                                t.last.next = t.last.prev;
                                t.last.prev = null;
                            }
                        }
                        // przedostatni odwrocony
                        else
                        {
                            // ostatni nieodwrocony
                            if(t.last.next == null)
                            {
                                t.last = t.last.prev;
                                t.last.prev = null;
                            }
                            // ostatni odwrocony
                            else
                            {
                                t.last = t.last.prev;
                                t.last.prev = t.last.next;
                                t.last.next = null;
                            }
                        }*/
                    }
                    break;
                }
                
                prev = t;
                t=t.next;
            }
        }
        
        public void Union(String T1,String T2)
        {
            train prev = null, t = first, pom1 = null, prev2 = null, pom2 = null;
            
            while(t!=null)
            {
                if(t.name.equals(T1)){
                    pom1=t;}
                if(t.name.equals(T2)){
                    prev2 = prev;
                    pom2=t;
                }
                prev = t;
                t = t.next;
            }
            
            if(pom1 == null)
            {
                //System.out.println("T1 NO TRAIN");
                return;
            }
            else if(pom2 == null)
            {
                //System.out.println("T2 NO TRAIN");
                return;
            }//System.out.format("pom1.first: %s, pom1.last: %s\n", pom1.first.name, pom1.last.name);
            //System.out.format("pom2.first: %s, pom2.last: %s\n", pom2.first.name, pom2.last.name);
            //System.out.format("pom1.last.next: %s, pom2.first.prev: %s\n", pom1.last.next == null ? "null" : pom1.last.next.name, pom2.first.prev == null ? "null" : pom2.first.prev.name);
            // ostatni1 nieodwrocony
            if(pom1.last.next == null)
            {
                pom1.last.next = pom2.first;
                
                // pierwszy2 nieodwrocony
                if(pom2.first.prev == null)
                {
                    //System.out.println("t1 nie, t2 nie");
                    pom2.first.prev = pom1.last;
                }
                else
                {
                    //System.out.println("t1 nie, t2 tak");
                    pom2.first.next = pom1.last;
                }
            }
            else
            {
                pom1.last.prev = pom2.first;
                
                // pierwszy2 nieodwrocony
                if(pom2.first.prev == null)
                {
                    //System.out.println("t1 tak, t2 nie");
                    pom2.first.prev = pom1.last;
                }
                else
                {
                    //System.out.println("t1 tak, t2 tak");
                    pom2.first.next = pom1.last;
                }
            }
            
            pom1.last = pom2.last;
            
            if(prev2 == null)
            {
                first = first.next;
            }
            else
            {
                prev2.next = pom2.next;
            }
            /*train t=new train();
            train pom1=null;
            train pom2=null;
            t=first;
            while(t!=null)
            {
                
                if(t.name.equals(T1))
                    pom1=t;
                if(t.name.equals(T2))
                    pom2=t;
                if(pom1!=null && pom2!=null)
                {
                    if(pom1.flag)
                    {
                        if(pom1.first.prev == null)
                        {
                            pom1.first.prev = pom2.flag ? pom2.last : pom2.first;
                        }
                        else
                        {
                            pom1.first.next = pom2.flag ? pom2.last : pom2.first;
                        }
                    }
                    else
                    {
                        if(pom1.last.next == null)
                        {
                            pom1.last.next = pom2.flag ? pom2.last : pom2.first;
                        }
                        else
                        {
                            pom1.last.prev = pom2.flag ? pom2.last : pom2.first;
                        }
                    }
                    
                    if(pom2.flag)
                    {
                        pom2.last.next = pom1.flag ? pom1.first : pom1.last;
                    }
                    else
                    {
                        pom2.first.prev = pom1.flag ? pom1.first : pom1.last;
                    }
                    

                    if(pom1.flag)
                    {
                        pom1.first = pom2.flag ? pom2.first : pom2.last;
                    }
                    else
                    {
                        pom1.last = pom2.flag ? pom2.first : pom2.last;
                    }
                    t=first;
                    if(pom2==t)
                    {
                        first=first.next;
                    }
                    else
                    do{
                        if(pom2==t)
                            if(t.next!=null)
                                t.next=t.next.next;
                            else
                                t.next=null;
                            
                        t=t.next;
                    }while(pom2!=t);
                    break;
                }
                
                t=t.next;
            }*/
        }
        public void Reverse(String T1)
        {
            train t=first;
            while(t!=null)
            {
                if(t.name.equals(T1))
                {
                    carriage swap = t.first;
                    t.first = t.last;
                    t.last = swap;
                    
                    break;
                }
                t=t.next;
            }
        }
        
        public void Display(String T1)
        {
            train t=new train();
            t=first;
            while(t!=null)
            {
                if(T1.equals(t.name))
                {
                    t.Display();
                    break;
                }
                t=t.next;
            }
            System.out.println();
        }
    }
     public static void maisdn(String[] args) {
        Scanner scan = new Scanner(System.in);
        trains t=new trains();
   t.New("t1", "w1");
        t.InsertLast("t1", "w2");
        t.InsertLast("t1", "w3");
        t.New("t2", "w11");
        t.InsertLast("t2", "w12");
        t.InsertLast("t2", "w13");
        
        t.Reverse("t2");
     }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        
        int testy = scan.nextInt();
        for(int i = 0; i < testy; i++)
        {
            int komendy = scan.nextInt();
            trains t=new trains();
            for(int j = 0; j < komendy; j++)
            {
                String op = scan.next();
               switch(op)
                {
                    case "New":
                        t.New(scan.next(), scan.next());
                        break;
                    case "InsertFirst":
                        t.InsertFirst(scan.next(), scan.next());
                        break;
                    case "InsertLast":
                        t.InsertLast(scan.next(), scan.next());
                        break;
                    case "DelFirst":
                        t.DelFirst(scan.next(), scan.next());
                        break;
                    case "DelLast":
                        t.DelLast(scan.next(), scan.next());
                        break;
                    case "Display":
                        t.Display(scan.next());
                        break;
                    case "Reverse":
                        t.Reverse(scan.next());
                        break;
                    case "Union":
                        t.Union(scan.next(), scan.next());
                        break;
                }
               
            }
        }
        
    }
    
}