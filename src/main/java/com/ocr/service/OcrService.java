package com.ocr.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@Service
public class OcrService {

    private final Tesseract tesseract;

    public OcrService() {
        tesseract = new Tesseract();
        try {
            // 设置训练数据路径
            File tessDataFolder = new ClassPathResource("tessdata").getFile();
            tesseract.setDatapath(tessDataFolder.getAbsolutePath());
            tesseract.setLanguage("eng"); // 设置语言
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String extractText(File imageFile) throws TesseractException {
        return tesseract.doOCR(imageFile);
    }

    public String extractText(BufferedImage image) throws TesseractException {
        return tesseract.doOCR(image);
    }
}
