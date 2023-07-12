package com.company;

public class Process {
    private String name = "";
    private int size = -1;
    private boolean is_set;

    Process(String name , int size){
        this.name = name;
        this.size = size;
        this.is_set = false;
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

    public boolean isIs_set() {
        return is_set;
    }

    public void setIs_set(boolean is_set) {
        this.is_set = is_set;
    }
}
