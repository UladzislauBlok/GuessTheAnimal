package org.animal.businessLogicLayer;

import org.animal.presentationLayer.TreeController;

import java.util.*;

public class TreeService {

    public void addNode(TreeNode root, TreeNode newNode, TreeNode nodeForChange) {
        Deque<TreeNode> stack = new ArrayDeque<>();
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

    public List<String> getAnimalList(TreeNode root) {
        List<String> animalList = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (current.isLeaf())
                animalList.add(current.getValue());


            if (current.getLeft() != null)
                stack.push(current.getLeft());
            if (current.getRight() != null)
                stack.push(current.getRight());
        }
        return animalList;
    }

    public boolean hasPath(TreeNode root, List<String> arr, String target)
    {
        if (root==null) {
            return false;
        }

        if (root.getValue().equals(target))
            return true;

        if (root.isLeaf()) {
            arr.remove(arr.size() - 1);
            return false;
        }

        boolean right = false;
        boolean left = false;

        if (root.getLeft() != null) {
            arr.add(getFact(root.getValue(), false));
            left = hasPath(root.getLeft(), arr, target);
        }

        if (left)
            return true;

        if (root.getRight() != null) {
            arr.add(getFact(root.getValue(), true));
            right = hasPath(root.getRight(), arr, target);
        }

        if (!right && !arr.isEmpty())
            arr.remove(arr.size() - 1);

        return right;
    }

    private String getFact(String question, boolean isPositive) {
        String[] strs = question.split("\\s+");
        String verb = "";
        String restFact = "";
        switch (strs[0]) {
            case "Is" -> {
                if (isPositive) {
                    verb = " is ";
                } else {
                    verb = " isn't ";
                }
                restFact = String.join(" ", Arrays.copyOfRange(strs, 2, strs.length));
            }
            case "Does" -> {
                if (isPositive) {
                    verb = " has ";
                    restFact = String.join(" ", Arrays.copyOfRange(strs, 2, strs.length));
                } else {
                    verb = " doesn't have ";
                    restFact = String.join(" ", Arrays.copyOfRange(strs, 3, strs.length));
                }
            }
            case "Can" -> {
                if (isPositive) {
                    verb = " can ";
                } else {
                    verb = " can't ";
                }
                restFact = String.join(" ", Arrays.copyOfRange(strs, 2, strs.length));
            }
        }

        return "It" + verb + restFact;
    }

    public TreeController.Stats getStats(TreeNode root) {
        if (root == null)
            return new TreeController.Stats();

        TreeController.Stats stats = new TreeController.Stats();
        String[] strs = root.getValue().split("\\s+");
        strs[1] = strs[0];
        strs[0] = "It";
        String str = String.join(" ", strs);
        stats.setRootNode(str.substring(0, str.length() - 1));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> depthList = new ArrayList<>();
        int depth = 0;

        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode current = queue.poll();
                stats.setNumOfNode(stats.getNumOfNode() + 1);
                if (current.isLeaf() && stats.getMinAnimalDepth() == 0)
                    stats.setMinAnimalDepth(depth);

                if (current.isLeaf()) {
                    stats.setNumOfAnimal(stats.getNumOfAnimal() + 1);
                    depthList.add(depth);
                } else {
                    stats.setNumOfStatements(stats.getNumOfStatements() + 1);
                }

                if (current.getLeft() != null)
                    queue.add(current.getLeft());

                if (current.getRight() != null)
                    queue.add(current.getRight());
            }

            depth++;
        }

        stats.setHeightOfTree(depth - 1);
        stats.setAvgAnimalDepth(depthList.stream()
                .mapToInt(n -> n)
                .average()
                .orElse(0));

        return stats;
    }

    public String getTreeRepresentation(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());

        String pointerRight = "├ ";
        String pointerLeft = "└ ";

        getTreeRepresentation(sb, "  ", pointerRight, root.getRight(), true);
        getTreeRepresentation(sb, "  ", pointerLeft, root.getLeft(), false);

        return sb.toString();
    }

    private void getTreeRepresentation(StringBuilder sb, String padding, String pointer, TreeNode node,
                              boolean isRight) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (isRight) {
                paddingBuilder.append("│");
            } else {
                paddingBuilder.append(" ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "├ ";
            String pointerLeft = "└ ";

            getTreeRepresentation(sb, paddingForBoth, pointerRight, node.getRight(), true);
            getTreeRepresentation(sb, paddingForBoth, pointerLeft, node.getLeft(), false);
        }
    }
}
