package sizebst;

/**
 * Instances of class SizeBSTN are Nodes of the Size Binary Search Tree 
 * @author lou
 *
 */
public class SizeBSTN {
	SizeBSTN LSubtree;  // left subtree of this tree (may be null)
	SizeBSTN RSubtree;  // right subtree of this tree (may be null)
	int data; // data at this node of the tree
	int size; // number of tree entries that are less than or equal to data

/**
 * create a new leaf of the tree with the given data
 * @param data
 */
	public SizeBSTN(int data){
		LSubtree = null;
		RSubtree = null;
		this.data = data;
		size = 1;
	}
	
	/* see assignment for proper format for nodeString
	 */
	public static String nodeString(SizeBSTN node){
		
		if(node.RSubtree == null && node.LSubtree == null){
			return "[null"+" "+node.data+","+node.size+" "+"null]";
		}
		
		else if(node.RSubtree != null && node.LSubtree == null){
			return "[null" + " " + node.data + "," + node.size + " " + nodeString(node.RSubtree) + "]";
		}
		
		else if(node.RSubtree == null && node.LSubtree != null){

			return "[" + nodeString(node.LSubtree) + " " + node.data + "," + node.size + " " + "null]";
		}
		
		else{
			return "[" + nodeString(node.LSubtree) + " " + node.data + "," + node.size + " " + nodeString(node.RSubtree) + "]";
		}
		
	}
	
	/**
	 * search for the number target in the tree this node is the root of
	 * @param target number to search for
	 * @return either the node that holds target,
	 * if there is one, or the node which should point to the node that 
	 * will hold target if it is added now  
	 */
	public static SizeBSTN getNode(SizeBSTN node, int target){
		
		if(node == null){
			return null;
		}
		
		if(node.data == target){
			return node;
		}
		
		else{ 
			
			if(node.LSubtree == null && node.RSubtree == null){
				return node;
			}
			
			else if(target < node.data && node.LSubtree != null){
				return SizeBSTN.getNode(node.LSubtree,target);
			}
			
			else if(target > node.data && node.RSubtree != null){
				return SizeBSTN.getNode(node.RSubtree,target);
			}
			
			else{
				return node;
			}
		}
		
		
	}
	
	/**
	 * like getNode but increments size fields as appropriate
	 * @param target number to search for
	 */
	public static void getNodeIncr(SizeBSTN node, int target){
		
		if(target < node.data && node.LSubtree != null){
			node.size++;
			getNodeIncr(node.LSubtree,target);
		}
		
		if(target < node.data && node.LSubtree == null){
			node.size++;
		}
		
		if(target > node.data && node.RSubtree != null){
			getNodeIncr(node.RSubtree,target);
		}
	}
	
	/**
	 * actually calculates number of numbers <= target.  
	 * Does search for target like getNode but adds up 
	 * the size fields of all nodes whose data is <= target.
	 * @return the number of nodes with data <= target in the
	 * tree this node is the root of.
	 */
	public static int sumNodesLeq(SizeBSTN node, int target){
		
		if(node == null)
		{
			return 0;
		}
		
		else
		{
			if(node.data <= target)
			{
				return 1 + sumNodesLeq(node.LSubtree,target) + sumNodesLeq(node.RSubtree,target);
			}
			else
			{
				return 0 + sumNodesLeq(node.LSubtree,target) + sumNodesLeq(node.RSubtree,target);
			}
		}
	}
		
}
