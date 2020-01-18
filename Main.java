package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner read = new Scanner(System.in);
        System.out.println("List One : ");
        singlylist listone = new singlylist();
        ask(listone, read);
        System.out.println("List Two : ");
        singlylist listtwo = new singlylist();
        ask(listtwo, read);
        System.out.println("Do You Want To Merge the List: 1. Sorted 2. Unsorted : ");
        int sortchoice = read.nextInt();
        if (sortchoice == 2) {
            System.out.println("Unsorted Merged List : ");
            ask(mergelists(listone, listtwo), read);
        }
        else{
            System.out.println("Sorted Merged List : ");
            ask(mergesorted(listone, listtwo), read);
        }
    }
    public static void ask(singlylist maxlist, Scanner read){
        boolean flag = true;
        while(flag == true) {
            int choice;
            System.out.println("1. Insert Elements together\n2. Insert by Position\n3. Delete by Position" +
                    "\n4. Delete by Key\n5. Print List\n6. Greatest Element\n7.Get from front\n8.Get From Last" +
                    "\n9.Reverse List\n10.Remove Duplicates\n11.Size\n12.Exit");
            choice = read.nextInt();
            switch(choice) {
                case 1 :
                    maxlist.inserttogether(read);
                    break;
                case 2 :
                    System.out.print("Value & Position: ");
                    int a = read.nextInt();
                    int b = read.nextInt();
                    maxlist.insert(a,b);
                    break;
                case 3 :
                    System.out.print("Position: ");
                    int c = read.nextInt();
                    maxlist.deletebypos(c);
                    break;
                case 4 :
                    System.out.println("Key: ");
                    int d = read.nextInt();
                    maxlist.deletebykey(d);
                    break;
                case 5 :
                    maxlist.printlist();
                    break;
                case 6 :
                    int e;
                    int j = 0;
                    for(e = maxlist.get(j); j < maxlist.sizelist(); j++){
                        if(maxlist.get(j) > e){
                            e = maxlist.get(j);
                        }
                    }
                    System.out.println("The greatest is " + e);
                    break;
                case 7 :
                    System.out.print("Enter Index: ");
                    int g = read.nextInt();
                    System.out.println("Value: "+ maxlist.get(g));
                    break;
                case 8 :
                    System.out.print("Position from last: ");
                    int f = read.nextInt();
                    System.out.println("Value : " + maxlist.getfromlast(f));
                    break;
                case 9 :
                    maxlist.reverselist();
                    break;
                case 10 :
                    maxlist.removeduplicates();
                    break;
                case 11 :
                    System.out.println("The Size of List = "+ maxlist.sizelist());
                    break;
                case 12:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    public static singlylist mergelists(singlylist one, singlylist two){
        singlylist listthree = new singlylist();
        insertlist(one,listthree);
        insertlist(two,listthree);
        return listthree;

    }
    public static singlylist mergesorted(singlylist one, singlylist two){
        singlylist listthree = new singlylist();
        int i = 0;
        int j = 0;
        while(one.get(i) != -1){
            if(one.get(i) < two.get(j)){
                listthree.insert(one.get(i));
                i++;
            }
            else if(one.get(i) == two.get(j)){
                listthree.insert(one.get(i));
                listthree.insert(one.get(i));
                i++;
                j++;
            }
            else{
                while(one.get(i) > two.get(j)){
                    listthree.insert((two.get(j)));
                    j++;
                }
            }
        }
        return listthree;
    }
    public static void insertlist(singlylist one, singlylist two){
        int i = 0;
        while(one.get(i) != -1){
            two.insert(one.get(i));
            i++;
        }
    }

}
class singlylist{
    Node front, rear;
    class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }
    singlylist(){
        front = rear = null;
    }
    void insert(int a){
        Node innode = new Node(a);
        if(front == null){
            front = innode;
            rear = innode;
        }
        else{
            rear.next = innode;
            rear = innode;
        }
    }
    void inserttogether(Scanner read){
        for(int i = 0;i != -1 ;){
            i = read.nextInt();
            if(i != -1)
                insert(i);
        }
    }
    void insert(int j, int pos){
        Node n = new Node(j);
        if(pos == 0){
            n.next = front;
            front = n;
            if(front == null)
            rear = n;
        }
        else if(pos >= sizelist()){
            rear.next = n;
            rear = n;
        }
        else{
            Node a = front;
            for(int i = 1; i < pos; i++){
                a = a.next;
            }
            Node b = a.next;
            a.next = n;
            n.next = b;
        }
    }
    int get(int n) {
        Node a = front;
        if (n >= sizelist())
            return -1;
        else {
            for (int i = 0; i < n; i++) {
                a = a.next;
            }
            return a.data;
        }

    }
    int sizelist(){
        int i = 0;
        Node n = front;
        while(n!= null){
            n= n.next;
            i++;
        }
        return i;
    }
    void printlist(){
        Node n = front;
        while(n!= null){
            System.out.print(n.data +" ");
            n= n.next;
        }
        System.out.println();
    }
    void deletebykey(int d){
        boolean flag = false;
        if(front.data == d)
            front = front.next;
        else {
            Node n = front;
            while (n != null) {
                if (n.next.data == d) {
                    flag = true;
                    break;
                }
                n = n.next;
            }
            if(false)
                System.out.println("Key Not Found");
            else
                n.next = n.next.next;
        }
    }
    void deletebypos(int d){
        if(d == 0)
            front = front.next;
        else {
            int i = 1;
            Node n = front;
            while (n != null) {
                if (i == d) {
                    n.next = n.next.next;
                    break;
                }
                n = n.next;
                i++;
            }
        }
    }
    int getfromlast(int d){
        if(d <= sizelist() && d > 0) {
            Node n = front;
            Node m = front;
            for (int i = 0; i < d; i++) {
                n = n.next;
            }
            while (n != null) {
                n = n.next;
                m = m.next;
            }
            return m.data;
        } else
            return -1;
    }
    void reverselist(){
        rear = front;
        Node n = front.next;
        Node m = n.next;
        n.next = front;
        n.next = rear;
        front.next = null;
        front = n;
        while(m != null){
            n = m.next;
            m.next = front;
            front = m;
            m = n;
        }
    }
    void removeduplicates(){
        Node n = front;
        int i = 0;
        while(n.next != null){
            if(n.data == n.next.data){
                n = n.next;
                deletebypos(i);
            }
            else{
                n = n.next;
                i++;
            }
        }
    }
}