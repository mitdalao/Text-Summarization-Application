package com.example.demo.controller;
import java.io.File;

public class DeleteDir {


        public boolean delete(String path){
            File file = new File(path);
            if(!file.exists()){
                return false;
            }
            if(file.isFile()){
                return file.delete();
            }
            File[] files = file.listFiles();
            for (File f : files) {
                if(f.isFile()){
                    if(!f.delete()){
                        // System.out.println(f.getAbsolutePath()+" delete error!");
                        f.delete();
                        return false;
                    }
                }else{
                    if(!this.delete(f.getAbsolutePath())){
                        return false;
                    }
                }
            }
            return file.delete();
        }

        public static void main(String[] args) {
            DeleteDir t = new DeleteDir();
            System.out.println(t.delete("../output"));
        }


}
