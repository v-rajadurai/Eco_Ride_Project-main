package com.example.car_backend.scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonScriptExecutor {
    
    public static List<String> execute(String imagePath) {
        List<String> results = new ArrayList<>();
        try {
            ProcessBuilder pb = new ProcessBuilder("D:/anaconda3/envs/env_easyocr/python.exe", "d:/PYTHON_PROJECTS/image-reader.py", imagePath);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                results.add(line);
            }
            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
