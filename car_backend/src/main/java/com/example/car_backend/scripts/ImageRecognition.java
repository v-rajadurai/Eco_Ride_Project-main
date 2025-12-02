package com.example.car_backend.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ImageRecognition {

    public List<String> recognizeImage(MultipartFile file) {
        try {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);

            List<String> results = PythonScriptExecutor.execute(convFile.getAbsolutePath());
            System.out.println(results);

            return results;

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}