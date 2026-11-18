public class Main {
    public static void main(String[] args) {

        RedBlackTree tree = new RedBlackTree();

        tree.addNode(10);
        tree.addNode(5);
        tree.addNode(15);
        tree.addNode(3);
        tree.addNode(7);
        tree.addNode(12);
        tree.addNode(20);

        System.out.println("Árvore Red-Black:");
        tree.printTree();

        System.out.println("\nEm ordem:");
        tree.inOrder();

        System.out.println("\n\nBusca pelo valor:");
        Node found = tree.searchNode(7);

        System.out.println(found != null
                ? "Valor encontrado"
                : "Valor não encontrado");
    }
}