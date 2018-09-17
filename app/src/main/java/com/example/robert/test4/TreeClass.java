package com.example.robert.test4;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class TreeClass {
TreeNode root;
    private static final String TAG = "TreeClass";
    List<String> arr = new ArrayList<>();
/*
we are going to insert a linked list node into the tree.
at the moment this is the only necessary basic operation
needed since there is no need to delete a node
or find a specific node
 */
public void insert(LNode node) {
    //Log.d(TAG, "does it even start please God");
    Log.d(TAG,"in the insert method");
    TreeNode current;
    TreeNode parent = null;
    if (root == null) {
        //Log.d(TAG, "in new if statement");
        root = new TreeNode(node);
        Log.d(TAG, "root now has a value which is " + root.getSchoolName());
    } else {

        current = root;
        parent = null;

        while (current != null) {
            //Log.d(TAG, "after start of while loop");;
                //Log.d(TAG, "Got here");
             if (current.compareTo(node) > 0) {
                parent = current;
                current = current.right;
            } else if (current.compareTo(node) < 0) {
                parent = current;
                current = current.left;
            } else {
               // Log.d(TAG, node.getSchoolName() + "has "+ node.getValue() + "which is the equal");
                 parent = current;
                current = current.right;
                //System.out.println("error you done messed up son");
            }
        }

        // Log.d(TAG, "outside of while loop statement");

        if (parent.compareTo(node) > 0) {
            parent.right = new TreeNode(node);

        } else if (parent.compareTo(node) < 0) {
            parent.left = new TreeNode(node);

        } else {
            parent.right = new TreeNode(node);
        }
    }
}

public List<String> getarr() {
    return arr;
}
/*
A reverse inorder method that returns the highest rated institutions first

 */
public void reverseInOrder(TreeNode root) {
  Log.d(TAG, "hey got here");
    //  TreeNode curr;
    //int count = 0;

    //String[] arr = new String[numberOfNodes];
//List<String[]> arr = new ArrayList<>();

    //TreeNode prev = null;

    //Stack<TreeNode> TreeStack = new Stack<TreeNode>();

        if (root == null) {
            return;
        } else {
            reverseInOrder(root.right);
            arr.add(root.getSchoolName());
            reverseInOrder(root.left);

        }





    /*

    TreeStack.push(root);
//    Log.d(TAG, "root " + root.getSchoolName() + " was pushed on stack");
    int counter = 0;
    while(!(TreeStack.empty())) {
        curr = TreeStack.peek();
//        Log.d(TAG, "Curr " + curr.getSchoolName() + " will now be checked" + "loop value is " + counter);
        counter++;
        if(prev == curr) {
  //          Log.d(TAG, "Curr was equal to prev" + curr.getSchoolName());
            arr[count] = TreeStack.pop().getSchoolName();
            //arr.add( TreeStack.pop().getSchoolName());
            //curr = curr.left;
            TreeStack.push(curr.left);
    //        Log.d(TAG, "Curr " + curr.getSchoolName() + " was pushed left");
            count++;
        } else if(curr.right != null) {
      //      Log.d(TAG, "Curr.right of " + curr.getSchoolName() + " was not equal to null");
            prev = curr;
        //    Log.d(TAG, "prev is equal to " + curr.getSchoolName());
            //Log.d(TAG, "prev is " + prev.getSchoolName());

            TreeStack.push(curr.right);
          //  Log.d(TAG, "Curr's right " + curr.getSchoolName() + " was pushed right");
        }
          else {
            //Log.d(TAG, "Curr " + curr.getSchoolName() + " is being popped");
              //arr.add(TreeStack.pop().getSchoolName());
            arr[count] = TreeStack.pop().getSchoolName();
           if (TreeStack.isEmpty()) {
               break;
           }
              prev = TreeStack.peek();
            Log.d(TAG, "prev is now " + prev.getSchoolName());
              count++;

            }
        }

        */
//return arr;
}



public void reverseInOrder2() {
     TreeNode curr;
    //int count = 0;

    //String[] arr = new String[numberOfNodes];
//List<String[]> arr = new ArrayList<>();

    TreeNode prev = null;

    Stack<TreeNode> TreeStack = new Stack<TreeNode>();
    TreeStack.push(root);
//    Log.d(TAG, "root " + root.getSchoolName() + " was pushed on stack");
    int counter = 0;
    while(!(TreeStack.empty())) {
        curr = TreeStack.peek();
        counter++;
        if(prev == curr) {

            arr.add(TreeStack.pop().getSchoolName());

            TreeStack.push(curr.left);

        } else if(curr.right != null) {

            prev = curr;

            TreeStack.push(curr.right);

        }
        else {

            arr.add(TreeStack.pop().getSchoolName());
            if (TreeStack.isEmpty()) {
                break;
            }
            prev = TreeStack.peek();
            Log.d(TAG, "prev is now " + prev.getSchoolName());

        }
    }

}


}




