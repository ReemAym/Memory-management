package com.company;
import java.util.ArrayList;

public class Partiton {
    private String name = "";
    private int size = -1;
    private Process current;
    Partiton(String name , int size)
    {
        this.name = name;
        this.size = size;
        this.current =null;
    }

    public void create_new_partition(int j,int number_of_partitions , ArrayList<Partiton>partitons){
        String n = "partiion " + number_of_partitions;
        Partiton new_partition = new Partiton(n ,(size - current.getSize()));
        partitons.add(j+1,new_partition);
    }

    public void Print(){
        if(current != null)
            System.out.println(this.name + " has size of " + this.size +" and currently ocuppied by process  " + current.getName());
        else
            System.out.println(this.name + " has size of " + this.size + " External fragment");
    }

    public static void compact(ArrayList<Partiton> temp_partiton){
        int count = 0;
        int new_partition_size = 0;
        for (Partiton i : temp_partiton) {
            if (i.getCurrent() == null) {
                new_partition_size += i.getSize();
                count++;
            }
        }
        if(count >1){
            int s = temp_partiton.size();
            temp_partiton.removeIf(i -> i.getCurrent() == null);
            String n = "partition " + s;
            Partiton new_partition = new Partiton(n ,new_partition_size);
            temp_partiton.add(new_partition);
        }

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Process getCurrent() {
        return current;
    }

    public void setCurrent(Process current) {
        this.current = current;
    }
}
