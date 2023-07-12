package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
         best_fit bf=new best_fit();
         first_fit ff=new first_fit();
         worst_fit wf=new worst_fit();
      //  BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
        System.out.println("please enter the number of partitions");
        int number_of_partitions , number_of_processes , size;
        String name_of_partition , name_of_proccess;
        number_of_partitions = sc.nextInt();
        ArrayList<Partiton>partitions = new ArrayList<>();
        ArrayList<Process>processes = new ArrayList<>();

        ArrayList<Partiton> temp_partitons1 = new ArrayList<>();
        ArrayList<Partiton> temp_partitons2 = new ArrayList<>();

        ArrayList<Process> temp_processes1=new ArrayList<>();
        ArrayList<Process> temp_processes2=new ArrayList<>();


        for(int i = 0 ; i < number_of_partitions ; i++){
            System.out.println("please enter the name and size of each partition");
            name_of_partition =sc.next();
            size = sc.nextInt();
            Partiton pt1 = new Partiton(name_of_partition,size);
            partitions.add(pt1);

            Partiton pt2 = new Partiton(name_of_partition,size);
            temp_partitons1.add(pt2);

            Partiton pt3 = new Partiton(name_of_partition,size);
            temp_partitons2.add(pt3);

        }
        System.out.println("please enter the number of processes");
        number_of_processes = sc.nextInt();
        for(int i = 0 ; i < number_of_processes ; i++){
            System.out.println("please enter the name and size of each process");
            name_of_proccess = sc.next();
            size = sc.nextInt();

            Process ps1 = new Process(name_of_proccess,size);
            processes.add(ps1);

            Process ps2 = new Process(name_of_proccess,size);
            temp_processes1.add(ps2);

            Process ps3 = new Process(name_of_proccess,size);
            temp_processes2.add(ps3);

        }
        boolean end = true;
        int algo=0;



        while(end){
            System.out.println("please choose your algorithm");
            System.out.println("1.first fit ,  2.best fit , 3.worst fit , 4.exit");
            algo=sc.nextInt();
            if(algo == 1) {
                ff.FirstFit(partitions, processes);
            }
            else if(algo == 2) {
                bf.BestFit(temp_partitons1, temp_processes1);
            }
            else if(algo == 3) {
                wf.WorstFit(temp_partitons2, temp_processes2);
            }
            else if(algo == 4) {
                end = false;
            }
        }
    }
}
