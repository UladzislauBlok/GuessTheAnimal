package org.animal.businessLogicLayer.exceptions;

import org.animal.businessLogicLayer.TreeNode;

public class NotFoundAnimalException extends RuntimeException {
    private final TreeNode treeNode;

    public NotFoundAnimalException(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }
}
