package com.example.practice.datastructure.datastructurewithO1complexity;

public class Main {

    public static void main(String[] args) {
        O1OperationDataStructure dataStructure = new O1OperationDataStructure(1000);

        System.out.println("Inserting Values....");

        for(int i=1; i<101; i++){
            dataStructure.insert(i);
        }

        System.out.println("Getting random ====> "+ dataStructure.getRandom());
        System.out.println("Getting random again ====> "+ dataStructure.getRandom());
        System.out.println("Getting random again ====> "+ dataStructure.getRandom());
        System.out.println("Getting random again ====> "+ dataStructure.getRandom());
        System.out.println("Getting random again ====> "+ dataStructure.getRandom());
        System.out.println("Getting random again ====> "+ dataStructure.getRandom());


        System.out.println("Deleting 20....");
        dataStructure.delete(20);
        System.out.println("Deleting 30....");
        dataStructure.delete(30);
        System.out.println("Deleting 27....");
        dataStructure.delete(27);
        System.out.println("Deleting 200....");
        dataStructure.delete(200);

        System.out.println("Getting random again ====> "+ dataStructure.getRandom());
        System.out.println("Getting random again ====> "+ dataStructure.getRandom());
    }
}
