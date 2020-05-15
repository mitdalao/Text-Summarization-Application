package com.example.demo.controller;
import com.example.demo.VO.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;

@RestController
@RequestMapping("/ml")
public class MlController {
    @RequestMapping(value = "/demo",method = RequestMethod.POST)
    public ResponseVO demo(@RequestParam(name = "input") String input) throws IOException, InterruptedException {
        /*
        param 保存到文件当中
        执行python程序读取该文件，输出保存到另一个文件
        读取输出文件，保存在param中
        */
        File cur = new File(".");
        System.out.println(cur.getAbsoluteFile());
        File dir = new File("../input");
        dir.deleteOnExit();
        dir.mkdir();
        String strFilename = "../input/input.txt";
        File fileText = new File(strFilename);
        FileWriter fileWriter = new FileWriter(fileText);
        fileWriter.write(input);
        fileWriter.close();
        ProcessBuilder processBuilder = new ProcessBuilder();
        DeleteDir deleteDir = new DeleteDir();
        deleteDir.delete("../output");
        dir = new File("../output");
        while(dir.exists()) deleteDir.delete("../output");
        processBuilder.command("python", "../myprojectv1/main.py", "--input=../input", "--output=../");
        Process process = processBuilder.start();
        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Success!");
        } else {
            System.out.println("No Success!");
        }

        strFilename = "../output/0.dec";
        fileText = new File(strFilename);
        FileReader fileReader = new FileReader(fileText);
        char[] output = new char[10000];
        int len = fileReader.read(output);
        fileReader.close();
        return ResponseVO.buildSuccess(String.valueOf(output).substring(0, len));
    }




}
