package crawler;

import java.util.*;

public class Node<t> {

	t value;
	char key;
	HashMap<Character, Node<t>> children;
	
	public Node()
	{
		this.children= new HashMap<Character, Node<t>>();		
	}
	
	public Node(char key)
	{
		this.key=key;
		this.children = new HashMap<Character, Node<t>>();
	}
}
