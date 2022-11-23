package _java;

import java.util.List;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static _java._HashMap.Node;

public class HashMapTest {
  static void printRecur(Node<Key, Integer> node, int level) {
    if (node instanceof _HashMap.TreeNode) {
      // 트리
      _HashMap.TreeNode<Key, Integer> tree = (_HashMap.TreeNode<Key, Integer>) node;
      tree.level = level;
      System.out.println(Strings.repeat("  ", level) + "Tree " + ((Node) tree));

      if (tree.left != null) {
        printRecur(tree.left, level + 1);
      }
      if (tree.right != null) {
        printRecur(tree.right, level + 1);
      }
      return;
    } else {
      // 연결 리스트
      node.level = level;
      System.out.println(Strings.repeat("  ", level) + "Link " + node);
      if (node.getNext() != null) {
        printRecur(node.getNext(), level + 1);
      }
    }
  }

  @Nested
  static class NormalInput {
    private _HashMap<Key, Integer> map = new _HashMap<>();

    @BeforeEach
    void prepareTest() {
      map.clear();
      for (int i = 0; i < 34; i++) {
        map.put(new Key(i), i);
      }
    }

    @Test
    void it() {
      final Node<Key, Integer>[] nodes = map.getTable();

      for (var node : nodes) {
        if (node == null) {
          continue;
        }
        System.out.println("bucket: " + node.hashCode());
        printRecur(node, 0);
      }
    }
  }

  @Nested
  static class IncrementalInput {
    private _HashMap<Key, Integer> map = new _HashMap<>();

    @BeforeEach
    void prepareTest() {
      map.clear();
      List<Integer> givenList = List.of(
          0, 3, 6, 9, 12, 15, 18, 21, 24,
          1, 4, 7, 10, 13, 16, 19, 22, 25, 28,
          2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35, 38
      );
      for (var i :  givenList) {
        map.put(new Key(i), i);
      }
    }

    @Test
    void it() {
      final Node<Key, Integer>[] nodes = map.getTable();

      for (var node : nodes) {
        if (node == null) {
          continue;
        }
        System.out.println();
        printRecur(node, 0);
      }
    }
  }
}
