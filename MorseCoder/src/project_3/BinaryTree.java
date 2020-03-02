package project_3;

import java.io.File;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;
import static java.util.Arrays.copyOfRange;

public class BinaryTree {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("Morse_Code.txt"));

        Branch<String, Character> morseTree = new Branch<>(
                String::isEmpty,
                key -> key.substring(1),
                key -> key.charAt(0) == '.'
        );
        Branch<int[], String> alphabetTree = new Branch<>(
                key -> key == null || key.length == 0,
                key -> copyOfRange(key, 1, key.length),
                key -> key[0] % 2 == 1
        );

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String morseCode = line.substring(1);
            char alphabetCharacter = line.charAt(0);

            morseTree.setValue(morseCode, alphabetCharacter);
            alphabetTree.setValue(findPathToNode(toUpperCase(alphabetCharacter), 0), morseCode);
            alphabetTree.setValue(findPathToNode(toLowerCase(alphabetCharacter), 0), morseCode);
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

        System.out.println("Get ..-.: " + alphabetTree.getValue(findPathToNode('f', 0)));
        System.out.println("Get .: " + alphabetTree.getValue(findPathToNode('e', 0)));
        System.out.println("Get -: " + alphabetTree.getValue(findPathToNode('t', 0)));
        System.out.println("Get --.-: " + alphabetTree.getValue(findPathToNode('q', 0)));

        System.out.println("Fun");

        System.out.println("A: " + ((int) 'A'));
        System.out.println("Z: " + ((int) 'Z'));
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

    private static class Branch<K, V> {
        private Branch<K, V> left;
        private Branch<K, V> right;
        private V value;
        private Predicate<K> isBranchEnd;
        private Function<K, K> nextKey;
        private Predicate<K> isLeftPath;

        private Branch(Predicate<K> isBranchEnd, Function<K, K> nextKey, Predicate<K> isLeftPath) {
            this.isBranchEnd = isBranchEnd;
            this.nextKey = nextKey;
            this.isLeftPath = isLeftPath;
        }

        public V getValue(K key) {
            if (isBranchEnd.test(key)) {
                return value;
            }

            return getDirection(key)
                    .getValue(nextKey.apply(key));
        }

        public void setValue(K key, V value) {
            if (isBranchEnd.test(key)) {
                this.value = value;

                return;
            }

            getDirection(key)
                    .setValue(nextKey.apply(key), value);
        }

        private Branch<K, V> getDirection(K key) {
            if (isLeftPath.test(key)) {
                if (left == null) {
                    left = new Branch<>(isBranchEnd, nextKey, isLeftPath);
                }

                return left;
            } else {
                if (right == null) {
                    right = new Branch<>(isBranchEnd, nextKey, isLeftPath);
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
