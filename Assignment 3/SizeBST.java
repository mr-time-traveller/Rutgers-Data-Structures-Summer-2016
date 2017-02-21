package sizebst;

public class SizeBST {
	SizeBSTN rootNode;

	public SizeBST(SizeBSTN root){
		rootNode =  root;
	}

	public String toString(){
		if (rootNode == null)
			return "(null)";
		else {
			return "("+ SizeBSTN.nodeString(rootNode) + ")";
		}
	}

	public boolean search(int target){
		
		SizeBSTN ptr = rootNode;
		
		if(rootNode == null){
			return false;
		} 
		
		while(ptr != null){
			
			if(ptr.data == target){ 		
				return true;
			}
			
			else if(ptr.data > target){ 	
				ptr = ptr.LSubtree;	
			}
			
			else{ 						
				ptr = ptr.RSubtree;
			}
		}
		return false;
	}

	
	public void insert(int newData){
		
		SizeBSTN ptr = SizeBSTN.getNode(rootNode,newData);
		SizeBSTN nNode = new SizeBSTN(newData);
		
		if(rootNode == null){
			rootNode = nNode;
		}
		
		if(ptr == null){
			return;
		}
		
		if(ptr.data == newData){
			return;
		}
		
		else{
			
			if(nNode.data > ptr.data){
				SizeBSTN.getNodeIncr(rootNode,newData);
				ptr.RSubtree = nNode;
				nNode.size = 1;
			}
			
			else{
				SizeBSTN.getNodeIncr(rootNode,newData);
				ptr.LSubtree = nNode;
				nNode.size = 1;
			}
		}
	}

	
	public int numLEq(int target){
		return SizeBSTN.sumNodesLeq(rootNode, target);
	}

	public static void main(String args []){
		SizeBST tree1 = new SizeBST(null);
		System.out.println("empty: "+tree1);
		tree1.insert(40);
		System.out.println("40 "+tree1);
		System.out.println(tree1);
		
		tree1.insert(20);
		System.out.println(tree1);
		tree1.insert(60);
		
		System.out.println(tree1);
		tree1.insert(30);
		System.out.println(tree1);
		tree1.insert(10);
		System.out.println(tree1);
		tree1.insert(50);
		System.out.println(tree1);
		tree1.insert(55);
		System.out.println(tree1);		
		tree1.insert(53);
		System.out.println(tree1);
		
		
		System.out.println(tree1.numLEq(55));
		System.out.println(tree1.search(43));
		// add any test code you want here - this is not graded.  Just be sure it compiles
	}
}
