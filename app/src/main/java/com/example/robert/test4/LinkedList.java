package com.example.robert.test4;

public class LinkedList {

    LNode head;

    LinkedList() {

        head = null;
    }

public void addtoList(String newSchoolName) {

        LNode newNode = new LNode(newSchoolName);
        newNode.next = head;
        head = newNode;
    }
    public int numberOfElements() {
LNode curr = head;
int listsize = 0;
while(curr != null) {
    listsize++;
    curr = curr.next;
}
        return listsize;
    }






}


