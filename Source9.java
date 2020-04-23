// Piotr Makosiej -1
//package source;
import java.util.Scanner;
public class Source {
    public static class StackArray
    {
        private Node[] Elem;
        private int top;
        public StackArray() {
        Elem = new Node[1000000];
        top =0;
        }
        public void push(Node x) {
        Elem[top]=x;
        top++;
        }
        public void pop(){
        top--;
        }
        public Node top(){
        return Elem[top-1];
        }
        public boolean isEmpty(){
            if(top==0)
                return true;
            else
                return false;
        }
    }

   public static class QueueArray
    {
	private Node[] array;
	private int start;
	private int end;
	private int count;
	QueueArray()
	{
		array = new Node[1000000];
		start = 0;
		end = -1;
		count = 0;
	}

	public void dequeue()
	{
		if (!isEmpty())
		{
            start = (start + 1) % array.length;
            count--;
		}
	}
	public void enqueue(Node item)
	{
		if (!isFull())
		{
            end = (end + 1) % array.length;
            array[end] = item;
            count++;
		}
	}
	public Node peek()
	{
		if (!isEmpty())
		{
			return array[start];
		}
            return null;
	}

	public boolean isEmpty()
	{
		return (count == 0);
	}

	public boolean isFull()
	{
		return (count == array.length);
	}
    }

    public static class Node
    {
        public int info;
        public Node left;
        public Node right;
        public Node(int info)
        {
            this.info = info;
            this.left = null;
            this.right = null;
        }
    }
    public static class Binary_tree
    {
        Node root=null;
        public int CreatePre(int n)
        {

            Node x=root;
            return _CreatePre(n,x,null,true);
        }
        public int _CreatePre(int n,Node curr,Node parent,boolean flag)
        {
            if(curr==null)
            {
                Node a=new Node(n);
                if(parent==null)
                {
                    root=a;
                }
                else
                {
                    if(flag)
                        parent.right=a;
                    else
                        parent.left=a;
                }
                return 0;
            }
            if(curr.info>n)
                return _CreatePre(n,curr.left,curr,false);
            else
                return _CreatePre(n,curr.right,curr,true);
        }
        public int postindex;

        public Node _CreatePost(int[] postorder,int min, int max)
	{
		if (postindex < 0) {
			return null;
		}
		int curr = postorder[postindex];
		if (curr < min || curr > max) {
			return null;
		}
		Node root = new Node(curr);
		postindex--;
		root.right = _CreatePost(postorder, curr + 1, max);
		root.left = _CreatePost(postorder, min, curr - 1);

		return root;
	}
	public void CreatePost(int[] postorder)
	{
		postindex = postorder.length - 1;
		root = _CreatePost(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
        public void LEVELORDER()
        {
            Node iterator;
           QueueArray q = new QueueArray();
            q.enqueue(root);
            System.out.print("LEVELORDER: ");
            while(!q.isEmpty())
            {
                iterator = q.peek();
                q.dequeue();


                if(iterator.left != null)
                {
                    q.enqueue(iterator.left);
                }
                if(iterator.right != null)
                {
                    q.enqueue(iterator.right);
                }

                System.out.print(iterator.info+" ");
            }
            System.out.println();
        }

        public void PREORDER()
        {
            if(root==null)
                return;
            Node iterator=root;
            StackArray s=new StackArray();
            s.push(root);
            System.out.print("PREORDER: ");
            while(!s.isEmpty())
            {
                Node x=s.top();
                s.pop();
                System.out.print(x.info+" ");
                if(x.right!=null)
                s.push(x.right);
                if(x.left!=null)
                s.push(x.left);
            }
            System.out.println();
        }

        public void INORDER()
        {
            Node iterator=root;
            StackArray s=new StackArray();

            System.out.print("INORDER: ");
            while (iterator != null || !s.isEmpty())
            {

                while (iterator !=  null)
                {
                    s.push(iterator);
                    iterator = iterator.left;
                }
                iterator = s.top();
                s.pop();

                System.out.print(iterator.info + " ");

                iterator = iterator.right;
            }
            System.out.println();
        }

        public void POSTORDER()
        {
            Node iterator=root;
            StackArray s=new StackArray();
            Node myRoot = root;
            s.push(root);
            Node prev=null;
            System.out.print("POSTORDER: ");
            while (!s.isEmpty())
            {
                Node current = s.top();

                if (prev == null || prev.left == current ||  prev.right == current)
                {
                    if (current.left != null)
                        s.push(current.left);
                    else if (current.right != null)
                        s.push(current.right);
                    else
                    {
                        s.pop();
                         System.out.print(current.info + " ");
                    }
                }
                else if (current.left == prev)
                {
                    if (current.right != null)
                        s.push(current.right);
                    else
                    {
                        s.pop();
                        System.out.print(current.info + " ");
                    }
                }
                else if (current.right == prev)
                {
                    s.pop();
                    System.out.print(current.info + " ");
                }

                prev = current;
            }
            System.out.println();
        }

        public void PARENT(int n)
        {
            //System.out.println(n);
            Node iterator;
            Node prev=root;
            if(root.info<n)
            {
                if(root.right!=null)
                    iterator=root.right;
                else
                {
                    System.out.println("PARENT " + n + ": BRAK");
                    return;
                }
            }else
            {
                if(root.left!=null)
                    iterator=root.left;
                else
                {
                    System.out.println("PARENT " + n + ": BRAK");
                    return;
                }
            }
            while(iterator!=null)
            {
                if(iterator.info==n)
                {
                    System.out.println("PARENT " + n + ": "+ prev.info);
                    return;
                }
                prev=iterator;
                if(iterator.info<n)
                    iterator=iterator.right;
                else
                    iterator=iterator.left;
            }
            System.out.println("PARENT " + n + ": BRAK");

        }
        public void INSERT(int n)
        {
            Node x=new Node(n);
            if(root==null)
                root=x;
            else
            {
                Node iterator =root;
                Node prev=root;
                boolean flag=false,flag2=false;
                while(iterator!=null)
                {
                    if(n==iterator.info)
                    return;
                    prev=iterator;
                    if(iterator.info>n)
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
        public void DELETE(int n)
        {
            Node iterator=root;
            if(iterator==null)
                   return;

            if(root.info==n)
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
                if(iterator.info==n)
                    break;
                prev=iterator;
                if(iterator.info<n)
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
        public void SUCCESSOR(int n)
        {
            Node iterator=root;
            Node prevprev = null;
            Node prev=null;
            while(iterator!=null)
            {
                if(iterator.info==n)
                    break;
                prevprev = prev;
                prev=iterator;
                if(iterator.info<n)
                    iterator=iterator.right;
                else
                    iterator=iterator.left;

            }
            if(iterator!=null)
            {
                if(iterator.right!=null)
                    iterator=iterator.right;
                else
                {
                    if(prev!=null)
                    {
                        Node iterator2=root;
                        Node successor=root;
                        while(iterator2!=null)
                        {
                            if(iterator2.info==n)
                                break;
                            if(iterator2.info<n)
                                iterator2=iterator2.right;
                            else
                            {
                                successor=iterator2;
                                iterator2=iterator2.left;
                            }
                        }
                        if(successor.info>n)
                        System.out.println("SUCCESSOR " + n + ": " + successor.info);
                        else
                        System.out.println("SUCCESSOR " + n + ": BRAK");
                    }
                    else
                     System.out.println("SUCCESSOR " + n + ": BRAK");
                     return;
                }
            }
            else
            {
                 System.out.println("SUCCESSOR " + n + ": BRAK");
                 return;
            }
            while(iterator.left!=null)
            {
                iterator=iterator.left;
            }
             System.out.println("SUCCESSOR " + n + ": " + iterator.info);
        }
        public void PREDECESSOR(int n)
        {
            Node iterator=root;
            Node prev=null;
            Node prevprev = null;
            while(iterator!=null)
            {
                if(iterator.info==n)
                    break;
                prevprev = prev;
                prev=iterator;
                if(iterator.info<n)
                    iterator=iterator.right;
                else
                    iterator=iterator.left;

            }
            if(iterator!=null)
            {
                if(iterator.left!=null)
                    iterator=iterator.left;
                else
                {
                    if(prev!=null)
                    {
                        Node iterator2=root;
                        Node successor=root;
                        while(iterator2!=null)
                        {
                            if(iterator2.info==n)
                                break;
                            if(iterator2.info<n)
                            {
                                successor=iterator2;
                                iterator2=iterator2.right;
                            }
                            else
                                iterator2=iterator2.left;
                        }
                        if(successor.info<n)
                        System.out.println("PREDECESSOR " + n + ": " + successor.info);
                        else
                        System.out.println("PREDECESSOR " + n + ": BRAK");
                    }
                    else
                     System.out.println("PREDECESSOR " + n + ": BRAK");
                     return;
                }
            }
            else
            {
                 System.out.println("PREDECESSOR " + n + ": BRAK");
                 return;
            }
            while(iterator.right!=null)
            {
                iterator=iterator.right;
            }
             System.out.println("PREDECESSOR " + n + ": " + iterator.info);
        }
    }

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int testy=scan.nextInt();
       for(int i=0;i<testy;i++)
       {
           System.out.println("ZESTAW: " + (i+1));
           int n=scan.nextInt();
           Binary_tree binarytree=new Binary_tree();
           String op = scan.next();

           switch(op)
                {
                    case "PREORDER":
                        for(int j=0;j<n;j++)
                        {
                            int x=scan.nextInt();
                            binarytree.CreatePre(x);
                        }
                        break;
                    case "POSTORDER":
                        int[] t= new int[n];
                        for(int j=0;j<n;j++)
                        {
                            t[j]=scan.nextInt();
                        }
                        binarytree.CreatePost(t);
                        break;
                }
           int operacje=scan.nextInt();

           for(int j=0;j<operacje;j++)
           {
               op = scan.next();
               switch(op)
                {
                    case "PREORDER":
                        binarytree.PREORDER();
                        break;
                    case "POSTORDER":
                        binarytree.POSTORDER();
                        break;
                    case "INORDER":
                        binarytree.INORDER();
                        break;
                    case "LEVELORDER":
                        binarytree.LEVELORDER();
                        break;
                    case "PARENT":
                        int z=scan.nextInt();
                        binarytree.PARENT(z);
                        break;
                    case "INSERT":
                        binarytree.INSERT(scan.nextInt());
                        break;
                    case "DELETE":
                        binarytree.DELETE(scan.nextInt());
                        break;
                    case "SUCCESSOR":
                        binarytree.SUCCESSOR(scan.nextInt());
                        break;
                    case "PREDECESSOR":
                        binarytree.PREDECESSOR(scan.nextInt());
                        break;
                }
           }

       }


}
}
