package test;

import structures.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class ProjectTests {
  @Rule public Timeout timeout = new Timeout(1000000L, TimeUnit.SECONDS);

  private BSTInterface<Integer> emptyTree;
  private BSTInterface<String> oneNodeTree;
  private static final String FOO = "foo";

  @Before
  public void beforeBSTTests() {
    emptyTree = new BinarySearchTree<Integer>();
    oneNodeTree = new BinarySearchTree<String>();
    oneNodeTree.add(FOO);
  }

  @Test
  public void testEmpty() {
    assertTrue(emptyTree.isEmpty());
  }

  @Test
  public void testNotEmpty() {
    assertFalse(oneNodeTree.isEmpty());
  }

  @Test
  public void testSize() {
    assertEquals(0, emptyTree.size());
    assertEquals(1, oneNodeTree.size());
  }

  @Test
  public void testContains() {
    assertTrue(oneNodeTree.contains(FOO));
  }

  @Test
  public void testRemove() {
    assertFalse(emptyTree.remove(0));
  }

  @Test
  public void testGet() {
    assertEquals(FOO, oneNodeTree.get(FOO));
  }

  @Test
  public void testAdd() {
    emptyTree.add(1);
    assertFalse(emptyTree.isEmpty());
    assertEquals(1, emptyTree.size());
  }

  @Test
  public void testGetMinimum() {
    assertEquals(null, emptyTree.getMinimum());
  }

  @Test
  public void testGetMaximum() {
    assertEquals(FOO, oneNodeTree.getMaximum());
  }

  @Test
  public void testHeight() {
    assertEquals(-1, emptyTree.height());
    assertEquals(0, oneNodeTree.height());
  }

  @Test
  public void testPreorderIterator() {
    Iterator<String> i = oneNodeTree.preorderIterator();
    while (i.hasNext()) {
      assertEquals(FOO, i.next());
    }
  }

  @Test
  public void testInorderIterator() {
    Iterator<String> i = oneNodeTree.inorderIterator();
    while (i.hasNext()) {
      assertEquals(FOO, i.next());
    }
  }

  @Test
  public void testPostorderIterator() {
    Iterator<Integer> i = emptyTree.postorderIterator();
    assertFalse(i.hasNext());
  }

  @Test
  public void testEquals() {
    BSTInterface<String> tree = new BinarySearchTree<String>();
    assertFalse(oneNodeTree.equals(tree));
    tree.add(new String("foo"));
    assertFalse(oneNodeTree.equals(tree));
  }

  @Test
  public void testSameValues() {
    BSTInterface<Integer> tree = new BinarySearchTree<Integer>();
    assertTrue(emptyTree.sameValues(tree));

    emptyTree.add(1);
    emptyTree.add(2);

    tree.add(2);
    tree.add(1);

    assertTrue(emptyTree.sameValues(tree));
  }

  @Test
  public void testIsBalanced() {
    // disabled due to late change of isBalanced() specification
    // assertTrue(emptyTree.isBalanced());
    emptyTree.add(1);
    assertTrue(emptyTree.isBalanced());
    emptyTree.add(2);
    assertTrue(emptyTree.isBalanced());
    emptyTree.add(3);
    assertFalse(emptyTree.isBalanced());
  }

  @Test
  public void testBalance() {
    emptyTree.add(1);
    emptyTree.add(2);
    emptyTree.add(3);
    assertFalse(emptyTree.isBalanced());
    emptyTree.isBalanced();
    emptyTree.balance();
    assertTrue(emptyTree.isBalanced());
  }

  private BSTInterface<Integer> tree;

  @Before
  public void beforeScapegoatTests() {
    tree = new ScapegoatTree<Integer>();
  }

  @Test
  public void testScapegoatAdd() {
    tree.add(0);
    tree.add(1);
    tree.add(2);
    tree.add(3);
    assertEquals(3, tree.height());
    tree.add(4);
    assertEquals(3, tree.height());
    tree.add(5);
    assertEquals(4, tree.height());
    tree.add(6);
    assertEquals(4, tree.height());
  }

  @Test
  public void testScapegoatRemove() {
    for (int i : new int[] {3, 1, 5, 0, 4, 2, 6}) {
      tree.add(i);
    }

    for (int i : new int[] {1, 2, 0, 4}) {
      tree.remove(i);
    }

    BSTInterface<Integer> smallTree = new BinarySearchTree<Integer>();
    smallTree.add(5);
    smallTree.add(3);
    smallTree.add(6);

    assertTrue(tree.equals(smallTree));
  }

}
