package com.example.robert.test4;

public class TreeNode {
TreeNode left;
TreeNode right;
LNode value;


TreeNode() {
this.value = null;
this.left = null;
this.right = null;
}

TreeNode(LNode newValue) {
    this.value = newValue;
    this.left = null;
    this.right = null;
}

public void setLNodeP(double newP) {

    this.value.priority = newP;
}

public double getLNodeP() {

    return this.value.priority;
}

public String getSchoolName() {
return this.value.getSchoolName();

}

public int compareTo(LNode node) {
if(node.priority > this.getLNodeP()){
return 1;
} else if ( node.priority < this.getLNodeP()) {
    return -1;
} else
    return 0;
}
}
