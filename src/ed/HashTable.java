package ed;

import java.util.ArrayList;
import java.util.List;

public class HashTable<T> {

    private int B; // length of the array
    private int R; // previous prime number
    private double maxLoadFactor;
    private double minLoadFactor;
    public final static int LINEAR_PROBING = 0, QUADRATIC_PROBING = 1, DOUBLE_HASHING = 2;
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
    public HashTable(int B, int probing, double maxLoadFactor, double minLoadFactor) {
        // next prime of B * 2
        this(B, probing, maxLoadFactor);
        this.minLoadFactor = minLoadFactor;
    }

    private void init() {
        for(int i = 0; i < B; i++) {
            HashNode<T> node = new HashNode<>();
            node.setStatus(NodeStatus.EMPTY);
            node.setValue(null);
            associativeArray.add(node);
        }

        R = getPrevPrimeNumber(B);
        System.out.println(R);
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

    public static int getPrevPrimeNumber(int num) {
        for(int i = num - 1; i > 1; i--) {
            if(isPrime(i))
                return i;
        }
        return 1;
    }

    public static int getNextPrimeNumber(int num) {
        do {
            num += 1;
        } while(!isPrime(num));
        return num;
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
            if(node.getValue() != null)
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
            case LINEAR_PROBING:
                hashCode += attempt;
                break;

            case QUADRATIC_PROBING:
                hashCode += attempt * attempt;
                break;

            case DOUBLE_HASHING:
                hashCode += attempt * (R - hashCode % R);
                break;
        }

        return hashCode % B;
    }

    public double getLF() {
        return numOfElements / (double)B;
    }

    private void resize() {
        if(getLF() > maxLoadFactor) {

        }
    }

    public static boolean isPrime(int num) {
        if (num < 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
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
