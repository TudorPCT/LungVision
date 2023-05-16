package com.lungvision.lungdiseasexrayclassificationbe;

import com.lungvision.lungdiseasexrayclassificationbe.model.User;
import com.lungvision.lungdiseasexrayclassificationbe.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@SpringBootApplication
public class LungDiseaseXRayClassificationBeApplication {

    public static void main(String[] args) throws IOException {
//        BufferedImage bImage = ImageIO.read(new File("src/test/resources/pneumonia.jpeg"));
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ImageIO.write(bImage, "jpeg", bos );
//        byte [] data = bos.toByteArray();
//
//        MedicalScanDto medicalScanDto = new MedicalScanDto(data, "pneumonia.jpeg", FileType.JPEG);
//
//        PredictionService predictionService = new PredictionService(new RestTemplate());
//        predictionService.predict(medicalScanDto);
        SpringApplication.run(LungDiseaseXRayClassificationBeApplication.class, args);
    }

}
