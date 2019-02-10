
import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

/*@ author
Sang Nguyen
011967423.
Class CS- 146
Instruction Prof. Mr. Mike
*/

public class patientClinicRoom3{
	 private static class Tree {
	        Node root;
	        Node nil;

	        public Tree(){
	            nil = new Node();
	            nil.color = Color.BLACK; // nil leaf node is black by default
	            root = nil;
	        }
	    }
    // declare Name and Priority for Patient.

    public static class Node {

        private String name;
        private int key;
        Node right;
        Node left;
        Node parent;
        Color color;

       // Define the content for Name and priority.

        public Node() {
            this.name = null;
            this.key = 0;
            this.right = null;
            this.left = null;
            this.parent = null;

        }

        public Node(String _name,int _pri) {
			this.name = _name;
			this.key = _pri;
			this.right = null;
			this.left = null;
			this.parent = null;

		  }

        // Set string name.
        public void setName(String name){
            this.name = name;
        }
        // Set Int name.
        public void setKey(int key){
            this.key = key;
        }
        // Get string name.
        public String getName(){
            return this.name;
        }
        // Get Int name.
        public  int getKey(){
            return this.key;
        }
        public String getColor(){
        	if (color == Color.RED)
        		return "RED";
        	else
        		return "BLACK";
        }

     }
    // List name give to get random.
    private static String[] names = {"Sang", "Tien", "Thong", "Khoa", "Mike", "Phuc", "Jenny", "Mors", "Kim", "Ryback",
            "Alexander", "Vanessa", "Julie", "Jacob", "Jason", "Jack", "Rose","Phuong ", " Mohamet", "April", "Phung",
            "Kenny", "Calvin", "Dylan", "Lisa", "Hari", "Rivas", "Ricardo", "Ronaldo", "Messi", "Ibrahimovic",
            "Neuer", "Arsene", "Wagner", "Love", "Melissa", "Terry", "Lampard", "Gerrard"  };
    // default: 20 people at a time.
   
    private static int fullnumberpations = 20;
     // simulation the number of patient in the first time equal zero
    private static int numberOfPatients=0;
    // declare random.
    public static Random rand = new Random();

    private static  Tree T = new Tree();

    public static void main(String[] args){

    	// create BST tree
    	createBST_List(T);
        char repeat = 'y';
        Scanner sc = new Scanner(System.in);
        // Create a list of Patient.
       // create a do while loop.
        do{
            System.out.println(" --------------- Wellcome to Clinic Room----------------");
            System.out.println("-----------Here is Patient's Information----------------");
            System.out.println(" ------------------------Menu---------------------------");
            System.out.println(" 1 : list Paitent in BST tree  and number of patient in Clinic room ");
            System.out.println(" 2 : Delete any patient by using call any patient's priority number ( 1-100)");
            System.out.println(" 3 : Insert patient for clinic room");
            System.out.println(" 4 : Search any patient follow priority number ( BST case (1-100)) : ");
            System.out.println(" 5 : Rearrange tree for lowest to hightest priority number");
            System.out.println(" 9 : Quit.");
            System.out.println(" --------------------------------------------------------");
            System.out.print  (" Choose :   ");
            switch (sc.nextInt()) {
               case 1:
               // biuld BST ( preorder)
            	   BSTnumbersofPaitentinroom ();
               break;
               case 2:
               // to see BST first and then choose any number in BST to elete.
            	   BSTnumbersofPaitentinroom ();
               Scanner sc2 = new Scanner(System.in);
       		System.out.println("Delete the new key ( have in list (1-100):");
   		   int key=sc2.nextInt();
   		   // get  a any Node with given key and use Tree search to search exactly Node.  
   		   Node a = treeSearch(T.root,key);
   		   // in the case Node a is not empty.
   		  if ( a!= T.nil) {
   			 System.out.println("the patient was deleted :   ");
   		    BSTdelete(T, a);
   		 System.out.println();
   		 System.out.println("the patient list in emergency room still being is: ");
    		BSTnumbersofPaitentinroom ();  // recount number of patient.
   		   }else 
   			  // in the case we can not find out the node patient who has that key.
   			{System.out.println("there is no patient get that priority:  " + key + " in this emergency room.  ");
   		BSTnumbersofPaitentinroom ();  // recount number of patient
   			}
   		break;
                  case 3:
            	  // Insert name ( any name ) and then insert with random priority number ( 1-50)
            	   Scanner sc_1 = new Scanner(System.in);
            	   if(numberOfPatients<fullnumberpations) {
            		   Node b = createBSTNode();
            		   System.out.println("Insert the new name:");
            		   b.setName(sc_1.nextLine());
                	   BSTinsert(T,b);
                	   System.out.println();
                	   System.out.println("The new patient is:  " + b.getKey() + " : " + b.getName()+ " : " +b.getColor() );
                	   System.out.println();
            		   BSTnumbersofPaitentinroom();
                	   }
                	    // notation that the room is full when over 20.
            	   else System.out.println("The room is full");

            	   break;
               case 4:
               // give any key in BST and the we will know patient's information.
            	   Scanner sc8 = new Scanner(System.in);
         	      System.out.println("Insert priority number that you want to look for (1-100):");
     		       int key8=sc8.nextInt();
          	   	Node bst = treeSearch(T.root, key8);
            	if(bst!=T.nil) System.out.println(bst.getKey()+": "+bst.getName()+ " : " + bst.getColor());
      	   else  System.out.println("There is no patient who has priority "+ key8 +" in BST tree");
            	System.out.println();
      	    break;    
               case 5: 
            	   System.out.println("the list patient print from smallest to biggest priority number is : ");  
            	   inOrder(T.root);
            	   System.out.println("The number of patient in Emmegency room are :  " + numberOfPatients);
            	 break;
                  case 9:
                  // Exist
                    repeat = 'n';
                    break;
            }

        }
        while(repeat != 'n');
    }
    // Create a node tree search
    
     public static Node treeSearch(Node a, int key){
	        while(a !=T.nil){
	            if(a.key == key)
	                return a;
	            if(a.key > key)
	                a = a.left;
	            else
	                a = a.right;
	        }
	        return a;
    }
    // Create a BST node
     public static Node createBSTNode()


     {

 		if(numberOfPatients<fullnumberpations) {
     	int priority;
     	int nameID= rand.nextInt(names.length-1 );

 		do{priority = rand.nextInt(100)+1;}
 		while(treeSearch(T.root,priority)!=T.nil);

 		 return  new Node(names[nameID],priority);
 		}

 return  T.nil;
     }
    // create a BSTinsert a node.

     public static void BSTinsert( Tree T, Node newPatient){

    	 numberOfPatients++; 			// increase step by step number patients into emergency room. 
    	 Node y = T.nil;				
	      Node x = T.root;
	        while(x != T.nil){
	            y = x;
	            if(newPatient.key < x.key)
	                x = x.left;
	            else
	                x = x.right;
	        }
	         newPatient.parent = y;

	        if(y == T.nil)// Tree is empty
	            T.root = newPatient;
	        else
	        	if(newPatient.key < y.key)
	            y.left = newPatient; 					// Tree only has root
	        else
	            y.right = newPatient;					// Tree only has root
	        newPatient.left = T.nil;					//  given newPatient on the left tree equal null.
			newPatient.right = T.nil;					//  given newPatient on the right tree equal null.
			newPatient.color = Color.RED;				//  given newPatient the color .
			Insert_Fixup(T, newPatient);				//  given newPatient 2 colors  ( RED, BLACK) with color will arrange to follow under.
     		}
     
	      // create a RB insert BST list and fix up the color for any node ( with 2 colors RED and BLACK)


		 	private static void Insert_Fixup(Tree T, Node z){

				while(z.parent.color == Color.RED){
					if(z.parent == z.parent.parent.left){
						Node y = z.parent.parent.right; // x's uncle
						if(y.color == Color.RED){
							z.parent.color = Color.BLACK;			// case 1
							y.color = Color.BLACK;					// case 1
							z.parent.parent.color = Color.RED;		// case 1
							z = z.parent.parent;					// case 1
						} else {
						    if(z == z.parent.right) {
		                        z = z.parent;						// case 2
		                        leftRotate(T, z);					// case 2
		                    }
		                    z.parent.color = Color.BLACK;			// case 3
		                    z.parent.parent.color = Color.RED;		// case 3
		                    rightRotate(T, z.parent.parent);		// case 3
		                }
					} else {
		                Node y = z.parent.parent.left; // x's uncle
		                if(y.color == Color.RED){
		                    z.parent.color = Color.BLACK;			// case 1
		                    y.color = Color.BLACK;					// case 1
		                    z.parent.parent.color = Color.RED;		// case 1
		                    z = z.parent.parent;					// case 1
		                } else {
		                    if(z== z.parent.left) {
		                        z = z.parent;						// case 2
		                        rightRotate(T, z);					// case 2
		                    }
		                    z.parent.color = Color.BLACK;			// case 3
		                    z.parent.parent.color = Color.RED;		// case 3
		                    leftRotate(T, z.parent.parent);			// case 3
		                }
					}
				}
				T.root.color = Color.BLACK; 			//return the root color is black.

			}
		 	// Create a patient list with 20 patients in emergency room.
			public static void createBST_List(Tree T)
		 	{
		 		for(int k= 0; k <fullnumberpations ; k++)
		 		{
		 			Node a = createBSTNode();
		 			BSTinsert(T ,a);

		 		}
 			}
			// Create a function Left rotation to use some case so that we will get balance tree in insert and delete tree. 
		 	private static void leftRotate(Tree T,Node x){
				Node y = x.right;
				x.right = y.left;
				if(y.left != T.nil)
					y.left.parent = x;
				y.parent = x.parent;
				if(x.parent == T.nil)
					T.root = y;
				else if(x == x.parent.left)
					x.parent.left = y;
				else
					x.parent.right = y;
				y.left = x;
				x.parent = y;
			}
		 // Create a function right rotation to use some case so that we will get balance tree in insert and delete tree. 
			private static void rightRotate(Tree T, Node x){
				Node y = x.left;
				x.left = y.right;
				if(y.right!= T.nil)
					y.right.parent = x;
				y.parent = x.parent;
				if(x.parent == T.nil)
					T.root = y;
				else if(x == x.parent.right)
					x.parent.right = y;
				else
					x.parent.left = y;
				y.right = x;
				x.parent = y;
			}

     // create a Transplant.
     public static void Transplant( Tree T, Node a, Node b){
    	// Handle a as root
         if(a.parent == T.nil)
             T.root = b;
      // if a is a left child
         else if(a == a.parent.left)
             a.parent.left = b;
      // if a is a right child
         else
             a.parent.right = b;
      // connect Node a and b together.        
             b.parent = a.parent;
     }

     // Create a Treemin to find out min value.
     public static Node treeMin(Node x){
         while(x.left != T.nil) {
             x = treeMin(x.left);
         }
         return x;
     }
// Create a Treemax to find out max value.
     public static Node treeMax(Node x){
         while(x.right != T.nil) {
             x = treeMax(x.right);
         }
         return x;
     }
    // Create a BSTdelete to use for deleting a node.
     public static void BSTdelete(Tree t, Node z){
 Node x;
 Node y;
 if(z!=t.nil) {
    	y = z;
    	Color ycolor=y.color;
    	numberOfPatients--;
       
    	 if(z.left == t.nil)
    	 	{
    		 	x =z.right;
    		 	Transplant(t,z, z.right);}
         else if(z.right == t.nil)
         	{
        	 	x=z.left;
             	Transplant(t,z, z.left);
         	}
         else {
             y = treeMin(z.right);
              ycolor=y.color;
             x=y.right;
             if(y.parent == z)
            	 x.parent=y;
            else {
                 Transplant(t,y, y.right);
                 y.right = z.right;
                 y.right.parent = y;
            }
             Transplant(t,z, y);
             y.left = z.left;
             y.left.parent = y;
             y.color=z.color;

         }
    	 if(ycolor==Color.BLACK)
    		 BSTdeleteFixup(t, x);
    		System.out.println(z.getKey() + " : " +z.getName() + " : " + z.getColor());
         }
         else System.out.println("There is no patient who has key ");
    }
  // create a RB delete BST list and fix up the color for any node ( with 2 colors RED and BLACK)

     public static void BSTdeleteFixup(Tree t, Node x)
     {
    	while(x!=t.root && x.color==Color.BLACK)
    	{
    		if( x== x.parent.left)
    		{
    			Node w = x.parent.right;
    			if (w.color== Color.RED)
    			{
    				w.color= Color.BLACK;		// case 1
    				x.parent.color= Color.RED;	// case 1
    				leftRotate(t, x.parent);	// case 1 
    				w=x.parent.right;			// case 1

    			}
    			if (w.left.color== Color.BLACK && w.right.color== Color.BLACK)
    			{
    				w.color=Color.RED;		// case 2
    				x= x.parent;			// case 2

    			}
    			else {if ( w.right.color== Color.BLACK)
    			{
    				w.left.color= Color.BLACK; 		 //case3
    				w.color = Color.RED;			// case 3
    				rightRotate(t,w);				// case 3
    				w=x.parent.right;				// case 3
    			}
    			w.color= x.parent.color;			// case 4
    			x.parent.color= Color.BLACK;		// case 4
    			w.right.color= Color.BLACK;			// case 4
    			leftRotate(t, x.parent);			// case 4
    			x=t.root;							// case 4
    			}
    		}
    		// re fix up with opposite sides. 
    		else
    		{
    			Node w = x.parent.left;			
    			if (w.color== Color.RED)
    			{
    				w.color= Color.BLACK;			// case 1
    				x.parent.color= Color.RED;		// case 1
    				rightRotate(t, x.parent);		// case 1
    				w=x.parent.left;				// case 1

    			}
    			if (w.left.color== Color.BLACK && w.right.color== Color.BLACK)
    			{
    				w.color=Color.RED;		// case 2
    				x= x.parent;			// case 2

    			}
    			else {if ( w.left.color== Color.BLACK)//
    			{
    				w.right.color= Color.BLACK; 		//Case3
    				w.color = Color.RED;				//Case3
    				leftRotate(t,w);					//Case3
    				w=x.parent.left;					//Case3
    			}
    			w.color= x.parent.color;				//Case4
    			x.parent.color= Color.BLACK;			//Case4
    			w.left.color= Color.BLACK;				//Case4
    			rightRotate(t, x.parent);				//Case4
    			x=t.root;								//Case4
    			}
    		}
    	}
    	x.color= Color.BLACK;			//return the root color is black.
     }

    // create Preorder for BST.
 // we also print when we get a node in the tree is null. 
// we will arrange the tree to follow way root-left-right.
    public static void preOrder( Node a){
	    if ( a!=T.nil) {
	    	System.out.println( a.key + ": "+a.name +" : " + a.getColor() );
			preOrder( a.left);

			preOrder( a.right);
	    }

	}
 // create Inorder for BST.
 // we also print when we get a node in the tree is null. 
 // we will arrange the tree to follow way left- root-right.
    public static void inOrder( Node abc){
        if ( abc != T.nil) {
    		inOrder( abc.left);
    		System.out.println( abc.key + ": "+abc.name +" : " + abc.getColor() );
    		inOrder( abc.right);
        }
    }

    //create a public funtion to know number patient being in hospital base on BST tree.
    public static void BSTnumbersofPaitentinroom () {
    	preOrder(T.root);
        int index = numberOfPatients;
        // we have 2 case : 
        // case 1 number of patients in emergency room is bigger than 19. 
        if (index >19)
            System.out.println(" the number of patients in Emergency room in this time is "
                    + 20);
        else {
        	// case 2 number of patients in emergency room is less than 19.
            System.out.println(" the number of patients in Emergency room in this time is "
                    + ( numberOfPatients));
        }
    }

}
