package com.project.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache {
    private final Map<String, Node> cache = new HashMap<>();
    private final Node head = new Node();
    private final Node tail = new Node();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();

    public LRUCache(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    static class Node {
        Node prev, next;
        String key, value;
        Node() {}
        Node(String key, String value) { this.key = key; this.value = value; }
    }

    public String get(String key) {
        lock.lock();
        try {
            Node node = cache.get(key);
            if (node == null) return null;
            remove(node);
            addToHead(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(String key, String value) {
        lock.lock();
        try {
            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                remove(node);
                addToHead(node);
                return;
            }
            if (cache.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                cache.remove(lru.key);      // the missing step
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
        } finally {
            lock.unlock();
        }
    }

    public String snapshot() {
        lock.lock();
        try {
            StringBuilder sb = new StringBuilder("[");
            for (Node n = head.next; n != tail; n = n.next) {
                sb.append(n.key).append(':').append(n.value);
                if (n.next != tail) sb.append(", ");
            }
            return sb.append(']').toString();
        } finally {
            lock.unlock();
        }
    }

    public void traverse() {
        System.out.println(snapshot());   // print outside the lock
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
class TestLRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put("1", "a");
        lruCache.put("2", "b");
        lruCache.put("3", "c");
        lruCache.traverse();
        lruCache.get("1");
        lruCache.traverse();
        lruCache.put("2", "z");
        lruCache.traverse();
        lruCache.put("5", "e");
        lruCache.traverse();
    }
}
