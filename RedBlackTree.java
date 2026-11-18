
public class RedBlackTree {

    public Node root;

    // Inserir um novo nó
    public void addNode(int value) {
        Node newNode = new Node(value);
        Node parent = null;
        Node current = root;

        // Encontrar a posição do novo nó
        while (current != null) {
            parent = current;

            if (value < current.value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            } else {
                return;
            }
        }

        // Conectar o novo nó à árvore
        newNode.parent = parent;

        if (parent == null) {
            root = newNode;
        } else if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        fixInsertion(newNode);
    }

    // Verificar se um nó é vermelho
    private boolean isRed(Node node) {
        return node != null && node.red;
    }

    // Corrigir as propriedades Red-Black
    private void fixInsertion(Node node) {

        while (isRed(node.parent)) {

            Node parent = node.parent;
            Node grandparent = parent.parent;

            // Pai está à esquerda do avô
            if (parent == grandparent.left) {

                Node uncle = grandparent.right;

                if (isRed(uncle)) {
                    // Caso 1: tio vermelho
                    parent.red = false;
                    uncle.red = false;
                    grandparent.red = true;
                    node = grandparent;

                } else {
                    // Caso 2: nó à direita do pai
                    if (node == parent.right) {
                        node = parent;
                        rotateLeft(node);
                    }

                    // Caso 3: rotação à direita
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    rotateRight(node.parent.parent);
                }

            } else {

                // Pai está à direita do avô
                Node uncle = grandparent.left;

                if (isRed(uncle)) {
                    // Caso 1: tio vermelho
                    parent.red = false;
                    uncle.red = false;
                    grandparent.red = true;
                    node = grandparent;

                } else {
                    // Caso 2: nó à esquerda do pai
                    if (node == parent.left) {
                        node = parent;
                        rotateRight(node);
                    }

                    // Caso 3: rotação à esquerda
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    rotateLeft(node.parent.parent);
                }
            }
        }

        // A raiz deve ser sempre preta
        root.red = false;
    }

    // Rotação à esquerda
    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    // Rotação à direita
    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;

        if (y.right != null) {
            y.right.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.right = x;
        x.parent = y;
    }

    // Buscar um valor
    public Node searchNode(int value) {
        Node current = root;

        while (current != null) {
            if (value == current.value) {
                return current;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // Percurso pré-ordem
    public void preOrder() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node == null) return;

        System.out.print(node.value + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    // Percurso em ordem
    public void inOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node == null) return;

        inOrderTraversal(node.left);
        System.out.print(node.value + " ");
        inOrderTraversal(node.right);
    }

    // Percurso pós-ordem
    public void postOrder() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node == null) return;

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.value + " ");
    }

    // Imprimir a estrutura e as cores
    public void printTree() {
        printTreeIn(root, 0);
    }

    private void printTreeIn(Node node, int level) {
        if (node == null) return;

        printTreeIn(node.right, level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }

        System.out.println(node.value +
                (node.red ? " (VERMELHO)" : " (PRETO)"));

        printTreeIn(node.left, level + 1);
    }
}
