package com.example.robert.test4;

public class LNode {

String SchoolName;
LNode next;
double priority;

LNode() {
    this.SchoolName = "";
    this.next = null;
    this.priority = 0;
}

LNode(String newSchoolName) {
this.SchoolName = newSchoolName;
this.next = null;
//this.priority = 0;
}

LNode(String newSchoolName, LNode newNode){
this.SchoolName = newSchoolName;
this.next = newNode;
//this.priority = 0;
}


    public double getValue() {

    return this.priority;
    }

    public LNode getNext() {
        return next;
    }

    public void setValue(double newPriority) {

    this.priority = newPriority;

}

    public void setNext(LNode newNext) {
        this.next = newNext;

    }

    public void setSchoolName(String newSchoolName) {
    this.SchoolName = newSchoolName;
    }

    public String getSchoolName() {
    return this.SchoolName;
    }



}
