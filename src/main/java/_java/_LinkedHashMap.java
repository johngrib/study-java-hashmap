package _java;

public class _LinkedHashMap {
  static class Entry<K,V> extends _HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, _HashMap.Node<K,V> next) {
      super(hash, key, value, next);
    }
  }
}
