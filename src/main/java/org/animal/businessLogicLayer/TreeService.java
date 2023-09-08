package org.animal.businessLogicLayer;

import java.util.Stack;

public class TreeService {

    public void addNode(TreeNode root, TreeNode newNode, TreeNode nodeForChange) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (current.getLeft() == nodeForChange) {
                current.setLeft(newNode);
                break;
            }

            if (current.getRight() == nodeForChange) {
                current.setRight(newNode);
                break;
            }
            if (current.getLeft() != null)
                stack.push(current.getLeft());
            if (current.getRight() != null)
                stack.push(current.getRight());
        }
    }
}
