//Custom lightweight stack interface for history logging includes Push(Node) Pop() Clear() isEmpty()
public class Stack {
	Node top;
	
	//different stack instantiations (null vs with Node)
	public Stack(Node t){
		top = t;
	}
	public Stack(){
		top = null;
	}
	
	//stack operations (Push(),Pop(),Clear(),isEmpty())
	public void Push(Node n){
		if(top == null){
			top = n;
		}
		else{
			Node curr = top;
			n.setNext(curr);
			top = n;
		}
	}
	public Node Pop(){
		if(top != null){
			Node t = top;
			top = t.getNext();
			return t;
		}
		else return null;
	}
	public void Clear(){
		top = null;
	}
	public boolean isEmpty(){
		if(top == null) return true;
		else return false;
	}
	
}
