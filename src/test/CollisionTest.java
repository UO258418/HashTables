package test;

import ed.HashTable;

public class CollisionTest {

    public static void main(String... args) {
        collisionTest();
    }

    public static void collisionTest() {
        HashTable<Integer> ht = new HashTable<>(5, HashTable.LINEAR_PROBING, 1);
        ht.add(0);
        ht.add(1);

        System.out.println(ht.getB());
        System.out.println(ht);

        ht.add(5);

        System.out.println(ht.getB());
        System.out.println(ht);

        ht.add(16);

        System.out.println(ht.getB());
        System.out.println(ht);

        ht.add(11);

        System.out.println(ht.getB());
        System.out.println(ht);
    }

}
