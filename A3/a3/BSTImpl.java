
package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public BSTImpl(int val) {
        this.root = new NodeImpl(val);
        size = 1;
    }

    // The implementations given to you are intended to help you 
    // see how the linked cells work, and how to program with them.
    //
    // These methods are patterns to illustrate for you how to set up
    // the method implementations as recursion.
    //
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive helper methods.

    @Override
    // interface method ==================================================
    public int height() {
        // It is not recursive itself, but it calls a recursive
        // private "helper" method and passes it access to the tree cells.
        return height_recursive(this.root);
    }

    // private recursive helper
    private int height_recursive(Node c) {
        // private inner "helper" method with different signature
        // this helper method uses recursion to traverse
        // and process the recursive structure of the tree of cells
        if (c == null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht, rht) + 1;
    }

    @Override
    // interface method ==================================================
    public boolean contains(int val) {
        if (this.root == null) return false;
        return contains_r(val, root);
    }

    // private recursive helper
    private boolean contains_r(int val, Node c) {
        if (c == null) return false;
        if (c.getValue() == val) return true;
        return contains_r(val, c.getLeft()) || contains_r(val, c.getRight());
    }

    @Override
    // interface method, used by autograder, do not change
    public Node getRoot() {
        return this.root;
    }

    @Override
    // interface method ==================================================
    public int size() {
        return this.size;
    }


    @Override
    // interface method ===================================================
    public void remove(int value) {
        remove_r(value, this.root);
    }

    // private recursive helper
    private Node remove_r(int k, Node c) {
        if (c == null) return null; // item not found, nothing to do
        // now we know we have a real node to examine
        //int cflag = k.compareTo(c.getValue());
        int vc = c.getValue();
        if (k < vc) { // k is smaller than node
            c.setLeft(remove_r(k, c.getLeft()));
            return c;
        } else if (k > vc) { // k is larger than node
            c.setRight(remove_r(k, c.getRight()));
            return c;
        } else { // k==vc   // found it... now figure out how to rearrange
            // cases
            if (c.getLeft() == null && c.getRight() == null) {
                c = null;
                this.size--;
            } // leaf
            else if (c.getLeft() == null && c.getRight() != null) {
                c = c.getRight();
                this.size--;
            } // RC only
            else if (c.getLeft() != null && c.getRight() == null) {
                c = c.getLeft();
                this.size--;
            } // LC only
            else { // 2 children
                Node mc = findMaxCell(c.getLeft());
                c.setValue(mc.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }
    }

    // private recursive helper
    private Node findMaxCell(Node c) {
        if (c.getRight() == null) return c;
        return findMaxCell(c.getRight());
    }


    //====================================================================================
    //
    // The methods below here are part of the public BST interface we defined,
    // but you will write the implementation code.
    //
    // At the moment, they have return statements that return dummy values; this
    // is so they will compile, but those return values are just dummy behavior
    // you will replace the dummy returns with your own code and proper return values.
    //
    //====================================================================================


    @Override
    // interface method ==================================================
    public int insert(int val) {
        /*See BST.java for method specification */
        /*Your code here */
        /* Hint: Don't forget to update size */
        /* Hint: You can find examples of how to create a new Node object elsewhere in the code */
        return insert_r(val, this.root);

    }

    private int insert_r(int k, Node c) {
        if (c == null) {
            Node root = new NodeImpl(k);
            this.root = root;
            size++;
        }
        if (contains(k)) {
            return k;
        }
        int vc = c.getValue();
        if (k < vc) {
            Node leftChild = c.getLeft();
            if (leftChild == null) {
                size ++;
                Node leaf = new NodeImpl(k);
                c.setLeft(leaf);
                return leaf.getValue();
            }
            return insert_r(k, leftChild);
        }
        Node rightChild = c.getRight();
        if (rightChild == null) {
            size++;
            Node leaf = new NodeImpl(k);
            c.setRight(leaf);
            return leaf.getValue();
        }
        return insert_r(k, rightChild);

    }


    @Override
    // interface method ==================================================
    public int findMin() {
        /*See BST.java for method specification */
        /* Your code here */
        return findMin_r(this.root);

    }

    private int findMin_r(Node c) {
        if (c == null) {
            return -1;
        }
        if (c.getLeft() == null) {
            return c.getValue();
        }
        return findMin_r(c.getLeft());
    }


    @Override
    // interface method ==================================================
    public int findMax() {
        /*See BST.java for method specification */
        /* Your code here */
        return findMax_r(this.root);
    }

    private int findMax_r(Node c) {
        if (c == null) {
            return -1;
        }
        if (c.getRight() == null) {
            return c.getValue();
        }
        return findMax_r(c.getRight());
    }

    @Override
    // interface method ==================================================
    public Node get(int val) {
        /*See BST.java for method specification */
        /* Hint: Make sure you return a Node, not an int */
        /* Your code here */
        return get_r(val, this.root);
    }

    private Node get_r(int k, Node c) {
        if (c == null)
            return null;
        if (k == c.getValue()) {
            return c;
        } else if (k != c.getValue()) {
            if (k < c.getValue()) {
                return get_r(k, c.getLeft());
            } else if (k > c.getValue()) {
                return get_r(k, c.getRight());
            }
        }
        return null;
    }

    @Override
    // interface method ==================================================
    public boolean isFullBT() {
        /*See BST.java for method specification */
        /* Hint: How can you "break-up" the problem into smaller pieces? */
        /* Your code here */
        return isFullTree_r(this.root);
    }

    private boolean isFullTree_r(Node c) {
        if (c == null)
            return true;
        if (c.getLeft() == null && c.getRight() == null) {
            return true;
        }
        else if ((c.getLeft() != null) && (c.getRight() != null)) {
            return (isFullTree_r(c.getLeft()) && isFullTree_r(c.getRight()));
        }
        return false;
    }

    @Override
    // interface method ==================================================
    public int merge(BST nbt) {
        /*See BST.java for method specification */
        // Hint: traverse bst using pre-order
        // as each node is visited, take the value there
        // and do this.insert(value)
        // have to somehow count when an add is successful
        // so we can return the number of nodes added
        /* Your code here */
        return merge_r(nbt.getRoot());
    }

    private int merge_r(Node c) {
        int count = 0;
        if (c == null)
            return -1;
        if (!this.contains(c.getValue())) {
            insert(c.getValue());
            merge_r(c.getLeft());
            merge_r(c.getRight());
        }
        return counter(c);
    }
    private int counter(Node r) {
        if (r == null)
            return 0;
        return 1 + counter(r.getLeft()) + counter(r.getRight());
    }


    public int getMaxLeafHeightDiff() {
        /*See BST.java for method specification */
        /* Hint: Which of the methods you're given are most similar to this? */
        /* Your code here */

        return getMaxLeafHeightDiff_r();
    }

    private int getMaxLeafHeightDiff_r() {
        if (this.root == null)
            return 0;
        int lht = height_recursive(this.root.getLeft());
        int rht = height_recursive(this.root.getRight());
        int max = Math.max(lht, rht);
        int min = Math.min(lht, rht);
        int difference = 0;
        if ((this.getRoot().getRight() == null) && this.getRoot().getLeft() != null) {
            return difference;
        }
        else if ((this.getRoot().getLeft() == null) && this.getRoot().getRight() != null) {
            return difference;
        }
        else {
            if (lht > rht) {
                difference = lht - rht;
                return difference;
            }
            else if (rht > lht) {
                difference = rht - lht;
                return difference;
            }
        }
        return difference;
    }
}
