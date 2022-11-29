package ed;

import java.util.ArrayList;
import java.util.List;

public class HashTable<T> {

    private int B;
    private double maxLoadFactor;
    public final static int LINEAR_PROBING = 0, QUADRATIC_PROBING = 1;
    private int probing;
    private List<HashNode> associativeArray;
    private int numOfElements = 0;

    public HashTable(int B, int probing, double maxLoadFactor) {
        this.B = B;
        this.probing = probing;
        this.maxLoadFactor = maxLoadFactor;
        this.associativeArray = new ArrayList<>(B);
        init();
    }

    private void init() {
        for(int i = 0; i < B; i++) {
            HashNode<T> node = new HashNode<>();
            node.setStatus(NodeStatus.EMPTY);
            node.setValue(null);
            associativeArray.add(node);
        }
    }

    public void add(T element) {
        int index, attempt = 0;
        HashNode<T> node;
        do {
            index = f(element, attempt++);
        } while((node = associativeArray.get(index)).getStatus() == NodeStatus.VALID);
        node.setValue(element);
        node.setStatus(NodeStatus.VALID);
        numOfElements++;
    }

    public boolean search(T element) {
        int index, attempt = 0;
        HashNode<T> node;
        do {
            index = f(element, attempt++);
            node = associativeArray.get(index);
            if(node.getStatus() == NodeStatus.VALID)
                if(node.getValue().equals(element))
                    return true;
        } while(associativeArray.get(index).getStatus() != NodeStatus.EMPTY);
        return false;
    }

    public void remove(T element) {
        int index, attempt = 0;
        HashNode<T> node;
        do {
            index = f(element, attempt++);
            node = associativeArray.get(index);
            if(node.getValue().equals(element))
                if(node.getStatus() == NodeStatus.VALID) {
                    node.setStatus(NodeStatus.DELETED);
                    numOfElements--;
                    break;
                }
        } while(associativeArray.get(index).getStatus() != NodeStatus.EMPTY);
    }

    public int f(T element, int attempt) {
        int hashCode = Math.abs(element.hashCode());

        switch (probing) {
            case 0:
                hashCode += attempt;
                break;

            case 1:
                hashCode += attempt * attempt;
                break;
        }

        return hashCode % B;
    }

    public double getLF() {
        return numOfElements / (double)B;
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < B; i++) {
            HashNode<T> node = associativeArray.get(i);
            result += String.format("[%d] (%d) = %s - ", i, node.getStatus().getStatusNumber(), node);
        }

        return result;
    }

}
