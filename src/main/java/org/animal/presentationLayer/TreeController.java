package org.animal.presentationLayer;

import org.animal.businessLogicLayer.TreeNode;
import org.animal.businessLogicLayer.TreeService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeController {
    private final TreeService treeService;
    private final AnimalController animalController;

    public TreeController() {
        this.treeService = new TreeService();
        this.animalController = new AnimalController();
    }

    public void addNode(TreeNode root, TreeNode newNode, TreeNode nodeForChange) {
        treeService.addNode(root, newNode, nodeForChange);
    }

    public void printAnimalList(TreeNode root) {
        List<String> animalList = treeService.getAnimalList(root);
        animalList.sort(Comparator.naturalOrder());
        System.out.println("Here are the animals I know:");
        for (String animal : animalList) {
            System.out.println(" - " + animal.split("\\s+")[1]);
        }
    }

    public void printAnimalPath(TreeNode root) {
        List<String> pathList = new ArrayList<>();
        String target = animalController.animalProcess();
        boolean isFind = treeService.hasPath(root, pathList, target);
        if (isFind) {
            System.out.println("Facts about the " + target.split("\\s+")[1] + ":");
            for (String fact : pathList) {
                System.out.println(" - " + fact.substring(0, fact.length() - 1));
            }
        } else {
            System.out.println("No facts about the" + target.split("\\s+")[1] + ".");
        }
    }

    public void printStats(TreeNode root) {
        Stats stats = treeService.getStats(root);
        System.out.println("The Knowledge Tree stats\n");
        System.out.println("- root node                    " + stats.getRootNode());
        System.out.println("- total number of nodes        " + stats.getNumOfNode());
        System.out.println("- total number of animals      " + stats.getNumOfAnimal());
        System.out.println("- total number of statements   " + stats.getNumOfStatements());
        System.out.println("- height of the tree           " + stats.getHeightOfTree());
        System.out.println("- minimum animal's depth       " + stats.getMinAnimalDepth());
        System.out.println("- average animal's depth       " + stats.getAvgAnimalDepth());
    }

    public void printTree(TreeNode root) {
        System.out.println(" └ " + treeService.getTreeRepresentation(root));
    }
    public static class Stats {
        private String rootNode;
        private int numOfNode;
        private int numOfAnimal;
        private int numOfStatements;
        private int heightOfTree;
        private int minAnimalDepth;
        private double avgAnimalDepth;

        public Stats() {
            this.rootNode = null;
            this.numOfNode = 0;
            this.numOfAnimal = 0;
            this.numOfStatements = 0;
            this.heightOfTree = 0;
            this.minAnimalDepth = 0;
            this.avgAnimalDepth = 0;
        }

        public String getRootNode() {
            return rootNode;
        }

        public void setRootNode(String rootNode) {
            this.rootNode = rootNode;
        }

        public int getNumOfNode() {
            return numOfNode;
        }

        public void setNumOfNode(int numOfNode) {
            this.numOfNode = numOfNode;
        }

        public int getNumOfAnimal() {
            return numOfAnimal;
        }

        public void setNumOfAnimal(int numOfAnimal) {
            this.numOfAnimal = numOfAnimal;
        }

        public int getNumOfStatements() {
            return numOfStatements;
        }

        public void setNumOfStatements(int numOfStatements) {
            this.numOfStatements = numOfStatements;
        }

        public int getHeightOfTree() {
            return heightOfTree;
        }

        public void setHeightOfTree(int heightOfTree) {
            this.heightOfTree = heightOfTree;
        }

        public int getMinAnimalDepth() {
            return minAnimalDepth;
        }

        public void setMinAnimalDepth(int minAnimalDepth) {
            this.minAnimalDepth = minAnimalDepth;
        }

        public double getAvgAnimalDepth() {
            return avgAnimalDepth;
        }

        public void setAvgAnimalDepth(double avgAnimalDepth) {
            this.avgAnimalDepth = avgAnimalDepth;
        }
    }
}
