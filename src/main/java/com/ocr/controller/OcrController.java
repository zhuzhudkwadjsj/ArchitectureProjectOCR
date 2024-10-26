package com.ocr.controller;

import com.ocr.service.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api/ocr")
public class OcrController {

    private final OcrService ocrService;

    @Autowired
    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/extract-text")
    public ResponseEntity<String> extractText(@RequestParam("file") MultipartFile file) {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            String extractedText = ocrService.extractText(image);
            return new ResponseEntity<>(extractedText, HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
