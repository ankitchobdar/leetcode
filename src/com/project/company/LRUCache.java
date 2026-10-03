package com.project.company;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<String, Node> cache = new HashMap<>();
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    static class Node {
        Node prev;
        Node next;
        String key;
        String value;

        public Node() {}
        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public String get(String key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        remove(node);
        addToHead(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            if(cache.size() > capacity){
                remove(tail.prev);
            }
            addToHead(newNode);
        } else {
            node.value = value;
            remove(node);
            addToHead(node);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void traverse() {
        Node node = head.next;
        System.out.print("[");
        while (node.next != null) {
            System.out.print(node.key+":"+node.value+", ");
            node = node.next;
        }
        System.out.print("]");
        System.out.println();
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
