package Group13_cs4050_assignment2.songs;

/**
 * a class Node to represent the nodes of the binary search tree. Each node will
 * store a Record, and references to its left child, right child, and parent.
 *
 */

public class Node {
	private SongRecord _data;
	private Node _leftChild;
	private Node _rightChild;
	private Node _parent;

	// default constructor
	public Node() {
		this(new SongRecord()); // storing null Record objects
	}

	public Node(SongRecord data) {
		this(data, null, null);
	}

	public Node(SongRecord data, Node leftChild, Node rightChild) {
		_data = data;
		setLeftChild(leftChild);
		setRightChild(rightChild);
	}

	public SongRecord getData() {
		return _data;
	}

	public void setData(SongRecord data) {
		_data = data;
	}

	public Node getLeftChild() {
		return _leftChild;
	}

	public Node getParent() {
		return _parent;
	}

	public Node getRightChild() {
		return _rightChild;
	}

	public void setLeftChild(Node leftChild) {
		_leftChild = leftChild;
		if (leftChild != null ) //&& leftChild.hasLeftChild())
			leftChild.setParent(this);
	}


	public void setRightChild(Node rightChild) {
		_rightChild = rightChild;
		if (rightChild != null )//&& rightChild.hasRightChild())
			rightChild.setParent(this);
	}

	public void setParent(Node parent) {
		_parent = parent;
	}
	
	public boolean hasLeftChild() {
		return (_leftChild != null);
	}

	public boolean hasRightChild() {
		return (_rightChild != null);
	}
	
	public boolean hasParent()	{
		return (_parent != null);
	}
	
	public boolean isLeaf() {
		return ((_leftChild == null) && (_rightChild == null));
	}
	
	public boolean isEmpty() {
		return (_data.getDataKey() == null) ;
	}
}
