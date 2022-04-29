package a4;

public class MinHeap implements Heap {

  private int size = 0; // number of elements currently in the heap
  private int[] elts;   // heap array
  private int max;      // array declared size

  // ================================================
  // constructors
  // ================================================

  public MinHeap(int umax) { // user defined heap size
    this.max = umax;
    this.elts = new int[umax];
  }

  public MinHeap() { // default heap size is 100
    this.max = 100;
    this.elts = new int[100];
  }

  private int parent(int position) {
    return position / 2;
  }

  private int leftChild(int position) {
    return (2 * position);
  }

  private int rightChild(int position) {
    return (2 * position) + 1;
  }

  //==================================================
  // methods we need to grade
  //==================================================

  public int[] getArray() { // do not change this method
    return this.elts;
  }

  //=========================================================
  // public methods -- Implement these for the assignment.
  // Note that we want a Min Heap... so the operations
  // getFront and delFront and insert have to compare
  // for min being at the root
  //=========================================================


  public void insert(int p) {
    //Hint: remember to update size.  Also, remember that we skip index 0 in the array.
    /*Your code here */
    if (size >= max) {
      return;
    }
    elts[++size] = p;
    int curr = size;

    while (elts[curr] < elts[parent(curr)]) {
      swap(curr, parent(curr));
      curr = parent(curr);
    }
  }

  private void moveDown(int x) {
    while (2 * x <= size) {
      int i = 2 * x; // get left child
      int j = i + 1; // get right child
      if (i < size && elts[i] > elts[j]) {
        i++; // i = j
      }
      if (elts[x] < elts[i]) {
        break;
      }
      swap(x, i);
      x = i;
    }
  }

  private void swap(int x, int y) {
    int temp;
    temp = elts[x];
    elts[x] = elts[y];
    elts[y] = temp;
  }

  public void delFront() {
    /*Your code here */
    if (empty() == true)
      return;

    int front = elts[1];
    swap(1, size);
    size--;
    if (size != 0)
      moveDown(1);
  }

  public int getFront() throws IllegalStateException {
    //Return the element at the front (i.e., the smallest) element in the min-heap.
    //If the min-heap has no elements, throw an IllegalStateException.
    if (empty() == true) {
      throw new IllegalStateException();
    } else {
      int front = elts[1];
      return front;
    }
  }

  public boolean empty() {
    /*Your code here */
    if (size > 0) {
      return false;
    } else {
      return true;
    }
  }

  public int size() {
    /*Your code here */
    return size;
  }

  public void clear() {
    /*Your code here */
    size = 0;
  }

  public void build(int[] e, int n) {
    //Hint: remember to skip slot 0 in the heap array.
    clear();
    for (int i = 0; i < n; i++) {
      elts[i + 1] = e[i];
      size++;
    }
    for (int i = size / 2; i > 0; i--) {
      moveDown(i);
    }
  }

  public int[] sort() {
    // Hint: the smallest element will go in slot 0
    /*Your code here */
    int[] newArray = new int[size];

    int x = size;
    for (int i = 0; i < x; i++) {
      newArray[i] = getFront();
      delFront();
    }
    return newArray;
  }
}