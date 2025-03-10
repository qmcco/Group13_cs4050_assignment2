package Group13_cs4050_assignment2.songs;

public class OrderedDictionary implements OrderedDictionaryADT {

    Node root;

    OrderedDictionary() {
        root = new Node();
    }

    /**
     * Returns the Record object with key k, or it returns null if such a record
     * is not in the dictionary.
     *
     * @param k
     * @return
     * @throws assignment/songs/DictionaryException.java
     */
    @Override
    public SongRecord find(DataKey k) throws DictionaryException {
        Node current = root;
        int comparison;
        if (root.isEmpty()) {         
            throw new DictionaryException("There is no record matches the given key");
        }

        while (true) {
            comparison = current.getData().getDataKey().compareGenreTo(k);
            if (comparison == 0) { // key found
                return current.getData();
            }
            if (comparison == 1) {
                if (current.getLeftChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getLeftChild();
            } else if (comparison == -1) {
                if (current.getRightChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getRightChild();
            }
        }

    }

    /**
     * Inserts r into the ordered dictionary. It throws a DictionaryException if
     * a record with the same key as r is already in the dictionary.
     *
     * @param r
     * @throws songs.DictionaryException
     */
    /** Insert a new song record into the database */
    @Override
    public void insert(SongRecord r) throws DictionaryException {
        /** If the root is empty, insert the passed song record at root */
        Node newNode = new Node(r);
        if (root.isEmpty()) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;
        int comparison;
        /** compare the current value to the passed song record
         * if the passed song record's size is smaller, then current goes to the left, otherwise goes to the right,
         * given the child on either side is not null*/
        while (current != null) {
            comparison = current.getData().getDataKey().compareTo(r.getDataKey());
            parent = current;

            if (comparison == 0) {
                throw new DictionaryException("A record with the same key already exists.");
            } else if (comparison > 0) { // Go left
                if (current.getLeftChild() == null) {
                    current.setLeftChild(newNode);
                    return;
                }
                current = current.getLeftChild();
            } else { // Go right
                if (current.getRightChild() == null) {
                    current.setRightChild(newNode);
                    return;
                }
                current = current.getRightChild();
            }
        }
    }


    /**
     * Removes the record with Key k from the dictionary. It throws a
     * DictionaryException if the record is not in the dictionary.
     *
     * @param k
     * @throws songs.DictionaryException
     */
    /** Initial call of recursive remove function */
    @Override
    public void remove(DataKey k) throws DictionaryException {
        root = removeRecursive(root, k);
    }

    /**
     * Returns the successor of k (the record from the ordered dictionary with
     * smallest key larger than k); it returns null if the given key has no
     * successor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws songs.DictionaryException
     */
    /** Find the successor of the passed song DataKey */
    @Override
    public SongRecord successor(DataKey k) throws DictionaryException{
        Node current = root;
        Node successor = null;
        /** compare the passed song datakey to the root, if the passed datakey is greater, then go to the right, otherwise go left,
         * and set the value to the left to be the successor */
        while (current != null) {
            int comparison;
            if (current.getData().getDataKey() != null) {
                comparison = current.getData().getDataKey().compareTo(k);
            }
            else{
                comparison = 0;
            }
            if (comparison > 0) {
                successor = current;
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }

        if (successor == null) {
            throw new DictionaryException("There is no successor for the given record key.");
        }
        return successor.getData();
    }

    /** Compare the current node to the passed song DataKey k, then go left or right based on that comparison
     * until a null position is found, then return the empty position as a replacement for the node to be removed */
    private Node removeRecursive(Node current, DataKey k) throws DictionaryException {
        if (current == null || current.isEmpty()) {
            throw new DictionaryException("No such record key exists.");
        }

        int comparison = current.getData().getDataKey().compareTo(k);

        if (comparison > 0) { // Go left
            current.setLeftChild(removeRecursive(current.getLeftChild(), k));
        } else if (comparison < 0) { // Go right
            current.setRightChild(removeRecursive(current.getRightChild(), k));
        } else {
            // Case 1: No child
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return new Node(); // Empty node replacement
            }
            // Case 2: One child
            if (current.getLeftChild() == null) return current.getRightChild();
            if (current.getRightChild() == null) return current.getLeftChild();

            // Case 3: Two children – find smallest in right subtree
            Node successorNode = findSmallest(current.getRightChild());
            current.setData(successorNode.getData());
            current.setRightChild(removeRecursive(current.getRightChild(), successorNode.getData().getDataKey()));
        }
        return current;
    }
    /** Go left until the node with the smallest size value is found */
    private Node findSmallest(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

   
    /**
     * Returns the predecessor of k (the record from the ordered dictionary with
     * largest key smaller than k; it returns null if the given key has no
     * predecessor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws songs.DictionaryException
     */
    /** Compare the values of the root node and the values of the passed song DataKey k to find the successor */
    @Override
    public SongRecord predecessor(DataKey k) throws DictionaryException{
        Node current = root;
        Node predecessor = null;
        /** if the passed dataKey k value is greater, go to the left and set the value of the predecessor to the current value, otherwise go right */
        while (current != null) {
            int comparison;
            if (current.getData().getDataKey() != null) {
                comparison = current.getData().getDataKey().compareTo(k);
            }
            else{
                comparison = 0;
            }

            if (comparison < 0) {
                predecessor = current;
                current = current.getRightChild();
            } else {
                current = current.getLeftChild();
            }
        }

        if (predecessor == null) {
            throw new DictionaryException("There is no predecessor for the given record key.");
        }
        return predecessor.getData();
    }

    /**
     * Returns the record with smallest key in the ordered dictionary. Returns
     * null if the dictionary is empty.
     *
     * @return
     */
    /** go left until the next value to the left is Null, to get the smallest value */
    @Override
    public SongRecord smallest() throws DictionaryException{
        if (root.isEmpty()) {
            throw new DictionaryException("Dictionary is empty.");
        }

        Node current = root;
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current.getData();
    }


    /*
	 * Returns the record with largest key in the ordered dictionary. Returns
	 * null if the dictionary is empty.
     */
    /** go right until the next value to the right is null to get the largest value */
    @Override
    public SongRecord largest() throws DictionaryException{
        if (root.isEmpty()) {
            throw new DictionaryException("Dictionary is empty.");
        }

        Node current = root;
        while (current.getRightChild() != null) {
            current = current.getRightChild();
        }
        return current.getData();
    }
      
    /* Returns true if the dictionary is empty, and true otherwise. */
    @Override
    public boolean isEmpty (){
        return root.isEmpty();
    }
}
