package com.company;
import java.util.*;

public class worst_fit {
    public void WorstFit(ArrayList<Partiton> partitons , ArrayList<Process>processes){
    //  ArrayList<Partiton> partitons = new ArrayList<>(temp_partitons.size());
      //  temp_partitons.addAll(partitons);

//        for(int i=0;i<temp_partitons.size();i++){
//            partitons.get(i).setName(temp_partitons.get(i).getName());
//            partitons.get(i).setSize(temp_partitons.get(i).getSize());
//
//        }
        for(Process i : processes){
            i.setIs_set(false);
        }
        for(int i = 0 ; i < processes.size() ; i++){
            Partiton worst =  Collections.max(partitons, Comparator.comparing(Partiton::getSize));
            for(int j =0 ; j < partitons.size() ; j++){
                if(Objects.equals(worst.getName(), partitons.get(j).getName()) && partitons.get(j) .getCurrent() == null){
                    partitons.get(j).setCurrent(processes.get(i));
                    processes.get(i).setIs_set(true);
                    if(processes.get(i).getSize() < partitons.get(j).getSize()) {
                        partitons.get(j).create_new_partition(j,partitons.size() , partitons);
                        partitons.get(j).setSize(processes.get(i).getSize());
                    }
                }
            }
        }
        print_all_partitions(partitons);
        for(Process i : processes){
            if(!i.isIs_set()) System.out.println(i.getName() + " can not be allocated");
        }
        System.out.println("Do you want to compact? ");
        System.out.println("1.Yes  ,  2.No ");
        int c=0;
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if(c==1)
        {
            int before_compaction =partitons.size();
            Partiton.compact(partitons);
            for(Process i : processes){
                if(!i.isIs_set()){
                    if(i.getSize() == partitons.get(partitons.size()-1).getSize() &&partitons.get(partitons.size()-1).getCurrent() == null){
                        partitons.get(partitons.size()-1).setCurrent(i);
                        i.setIs_set(true);
                    }
                    if(i.getSize() < partitons.get(partitons.size()-1).getSize() && partitons.get(partitons.size()-1).getCurrent() == null){
                        int g = partitons.get(partitons.size()-1).getSize();
                        partitons.get(partitons.size()-1).setCurrent(i);
                        i.setIs_set(true);
                        String n = "partition " + (before_compaction+1);
                        Partiton new_partition = new Partiton(n ,(g - i.getSize()));
                        partitons.get(partitons.size()-1).setSize(i.getSize());
                        partitons.add(new_partition);
                    }
                }
            }
            print_all_partitions(partitons);
        }

    }

    private static void print_all_partitions(ArrayList<Partiton> temp_partitons){
        for(Partiton i : temp_partitons){
            i.Print();
        }
    }

    private static int get_max_partition_size(ArrayList<Partiton> partitons){
        int max = Integer.MIN_VALUE;
        for(int j = 0 ; j < partitons.size() ; j++){
            if(max < partitons.get(j).getSize()) max = partitons.get(j).getSize();
        }
        return max;
    }
}
