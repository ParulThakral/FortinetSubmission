package com.parul.questions123;

import org.junit.Assert;
import org.junit.Test;

public class ReverseLinkList {

    //Resolution - Use 3 pointers curr, next and prev. Change curr pointer to previous node.
    //Time Complexity - O(n)
    //Space Complexity - O(1)
    public Node reverse(Node head) {
        if (head == null) {
            return null;
        }

        Node curr = head;
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    private static class Node {
        Node next;
        int value;

        public Node(int value, Node next) {
            this.next = next;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }


    public String getStringFromLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        while (head != null) {
            builder.append(head.value);
            if (head.next != null) {
                builder.append("-");
            }
            head = head.next;
        }

        return builder.toString();
    }

    /**
     * Test Case for empty List.
     */
    @Test
    public void checkEmptyList() {
        ReverseLinkList reverseLinkList = new ReverseLinkList();
        Node newHead = reverseLinkList.reverse(null);
        Assert.assertEquals(null, newHead);
    }

    /**
     * Test Case for even length list.
     */
    @Test
    public void checkEvenLengthList() {
        ReverseLinkList reverseLinkList = new ReverseLinkList();
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, null))));
        String expectedString = new StringBuilder(getStringFromLinkedList(head)).reverse().toString();

        Node newHead = reverseLinkList.reverse(head);
        String resultString = getStringFromLinkedList(newHead);
        Assert.assertEquals(expectedString, resultString);
    }

    /**
     * Test case for odd length list.
     */
    @Test
    public void checkOddLengthList() {
        ReverseLinkList reverseLinkList = new ReverseLinkList();
        Node head = new Node(1, new Node(2, new Node(3, null)));
        String expectedString = new StringBuilder(getStringFromLinkedList(head)).reverse().toString();

        Node newHead = reverseLinkList.reverse(head);
        String resultString = getStringFromLinkedList(newHead);
        Assert.assertEquals(expectedString, resultString);
    }

    /**
     * Test case for unit length list.
     */
    @Test
    public void checkUnitLengthList() {
        ReverseLinkList reverseLinkList = new ReverseLinkList();
        Node head = new Node(1, null);

        Node newHead = reverseLinkList.reverse(head);

        Assert.assertEquals(1, newHead.value);
        Assert.assertEquals(null, newHead.next);
    }
}
