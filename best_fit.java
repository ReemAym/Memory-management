package com.company;
import java.util.*;

public class best_fit {
    public void BestFit(ArrayList<Partiton> partitons , ArrayList<Process>processes){
     //   ArrayList<Partiton> partitons = new ArrayList<>(temp_partitons.size());
      //  ArrayList<Process> temp_processes=new ArrayList<>();

       // partitons.addAll(partitons);

//        for(int i=0;i<temp_partitons.size();i++){
//            partitons.get(i).setName(temp_partitons.get(i).getName());
//            partitons.get(i).setSize(temp_partitons.get(i).getSize());
//
//        }
        for(Process i : processes){
            i.setIs_set(false);
        }
        for(int i = 0 ; i < processes.size() ; i++){
            Partiton best = search(processes.get(i).getSize() , partitons);
            for(int j = 0 ; j < partitons.size() ; j++){
                if(Objects.equals(best.getName(), partitons.get(j).getName()) && partitons.get(j) .getCurrent() == null ){
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
            int before_compaction = partitons.size();
            Partiton.compact(partitons);
            for(Process i : processes){
                if(!i.isIs_set()){
                    if(i.getSize() == partitons.get(partitons.size()-1).getSize() && partitons.get(partitons.size()-1).getCurrent() == null){
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



    public static Partiton search(int process_size ,ArrayList<Partiton> temp_partitons ){
        ArrayList<Partiton> t = new ArrayList<>();
        for(int j = 0 ; j < temp_partitons.size() ; j++){
            if(process_size <= temp_partitons.get(j).getSize()){
                t.add(temp_partitons.get(j));
            }
        }
        t.sort(Comparator.comparing(Partiton::getSize));
        return t.get(0);
    }
    private static void print_all_partitions(ArrayList<Partiton> temp_partitons){
        for(Partiton i : temp_partitons){
            i.Print();
        }
    }
}
