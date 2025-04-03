import java.util.*;

public class IteratorPatternExample {
  public static void main(String[] args) {
    // Graph Example
    Map<Integer, List<Integer>> graph = new HashMap<>();
    graph.put(1, Arrays.asList(2, 3));
    graph.put(2, Arrays.asList(4, 5));
    graph.put(3, Arrays.asList(6, 7));
    graph.put(4, Collections.emptyList());
    graph.put(5, Collections.emptyList());
    graph.put(6, Collections.emptyList());
    graph.put(7, Collections.emptyList());

    System.out.println("Graph BFS Traversal:");
    Iterator<Integer> graphIterator = new GraphIterator(graph, 1);
    while (graphIterator.hasNext()) {
      System.out.print(graphIterator.next() + " ");
    }

    // Tree Example
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);

    System.out.println("\n\nTree Inorder Traversal:");
    Iterator<Integer> treeIterator = new TreeIterator(root);
    while (treeIterator.hasNext()) {
      System.out.print(treeIterator.next() + " ");
    }

    // Array Example
    int[] array = { 10, 20, 30, 40, 50 };
    System.out.println("\n\nArray Traversal:");
    Iterator<Integer> arrayIterator = new ArrayIterator(array);
    while (arrayIterator.hasNext()) {
      System.out.print(arrayIterator.next() + " ");
    }
  }
}

// Common Iterator Interface
interface Iterator<T> {
  boolean hasNext();

  T next();
}

// Graph Iterator (BFS Traversal)
class GraphIterator implements Iterator<Integer> {
  private Queue<Integer> queue = new LinkedList<>();
  private Set<Integer> visited = new HashSet<>();
  private Map<Integer, List<Integer>> graph;

  public GraphIterator(Map<Integer, List<Integer>> graph, int startNode) {
    this.graph = graph;
    queue.add(startNode);
    visited.add(startNode);
  }

  @Override
  public boolean hasNext() {
    return !queue.isEmpty();
  }

  @Override
  public Integer next() {
    int node = queue.poll();
    for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
      if (!visited.contains(neighbor)) {
        queue.add(neighbor);
        visited.add(neighbor);
      }
    }
    return node;
  }
}

// Tree Iterator (Inorder Traversal)
class TreeIterator implements Iterator<Integer> {
  private Stack<TreeNode> stack = new Stack<>();

  public TreeIterator(TreeNode root) {
    pushLeft(root);
  }

  private void pushLeft(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public Integer next() {
    TreeNode node = stack.pop();
    pushLeft(node.right);
    return node.val;
  }
}

// Tree Node
class TreeNode {
  int val;
  TreeNode left, right;

  public TreeNode(int val) {
    this.val = val;
  }
}

// Array Iterator
class ArrayIterator implements Iterator<Integer> {
  private int[] array;
  private int index = 0;

  public ArrayIterator(int[] array) {
    this.array = array;
  }

  @Override
  public boolean hasNext() {
    return index < array.length;
  }

  @Override
  public Integer next() {
    return array[index++];
  }
}
