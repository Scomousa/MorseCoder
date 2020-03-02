package project_3;

import java.io.File;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;
import static java.util.Arrays.copyOfRange;

public class BinaryTreeTryNumeroDos {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("Morse_Code.txt"));

        Branch<String, Character> morseTree = new MorseTree();
        Branch<int[], String> alphabetTree = new AlphabetTree();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String morseCode = line.substring(1);
            char alphabetCharacter = line.charAt(0);

            morseTree.setValue(morseCode, alphabetCharacter);
            alphabetTree.setValue(findPathToNode(toUpperCase(alphabetCharacter), 0), morseCode);
        }

        // A - 65

        System.out.println("Get f: " + morseTree.getValue("..-."));
        System.out.println("Get e: " + morseTree.getValue("."));
        System.out.println("Get t: " + morseTree.getValue("-"));
        System.out.println("Get q: " + morseTree.getValue("--.-"));

        System.out.println("Get ..-.: " + alphabetTree.getValue(findPathToNode('F', 0)));
        System.out.println("Get .: " + alphabetTree.getValue(findPathToNode('E', 0)));
        System.out.println("Get -: " + alphabetTree.getValue(findPathToNode('T', 0)));
        System.out.println("Get --.-: " + alphabetTree.getValue(findPathToNode('Q', 0)));

        System.out.println("Fun");


    }

    private static int[] findPathToNode(int node, int depth) {
        if (node == 0) {
            return new int[depth + 1];
        }

        int parent = (node - 1) / 2;
        int[] path = findPathToNode(parent, depth + 1);

        path[(path.length) - (depth + 1)] = node;

        return path;
    }

    private static class MorseTree extends Branch<String, Character> {
        @Override
        protected boolean isBranchEnd(String key) {
            return key.isEmpty();
        }

        @Override
        protected String getNextKey(String key) {
            return key.substring(1);
        }

        @Override
        protected boolean isLeftPath(String key) {
            return key.charAt(0) == '.';
        }

        @Override
        protected Branch<String, Character> getNewBranch() {
            return new MorseTree();
        }
    }

    private static class AlphabetTree extends Branch<int[], String> {
        @Override
        protected boolean isBranchEnd(int[] key) {
            return key == null || key.length == 0;
        }

        @Override
        protected int[] getNextKey(int[] key) {
            return copyOfRange(key, 1, key.length);
        }

        @Override
        protected boolean isLeftPath(int[] key) {
            return key[0] % 2 == 1;
        }

        @Override
        protected Branch<int[], String> getNewBranch() {
            return new AlphabetTree();
        }
    }

    private abstract static class Branch<K, V> {
        private Branch<K, V> left;
        private Branch<K, V> right;
        private V value;

        protected abstract boolean isBranchEnd(K key);

        protected abstract K getNextKey(K key);

        protected abstract boolean isLeftPath(K key);

        protected abstract Branch<K, V> getNewBranch();

        public V getValue(K key) {
            if (isBranchEnd(key)) {
                return value;
            }

            return getDirection(key)
                    .getValue(getNextKey(key));
        }

        public void setValue(K key, V value) {
            if (isBranchEnd(key)) {
                this.value = value;

                return;
            }

            getDirection(key)
                    .setValue(getNextKey(key), value);
        }

        private Branch<K, V> getDirection(K key) {
            if (isLeftPath(key)) {
                if (left == null) {
                    left = getNewBranch();
                }

                return left;
            } else {
                if (right == null) {
                    right = getNewBranch();
                }

                return right;
            }
        }
    }
}


/*
            78
        70
            77
    66
            76
        69
            75
-
            74
        68
            73
    65
            72
        67
            71

 */
