package com.example.pdfgeneration.controller;

import com.example.pdfgeneration.service.PdfGeneratorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PdfController {
    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(HttpServletRequest request) throws IOException {
        byte[] pdfContent = pdfGeneratorService.generatePdf();
        String contentType = request.getHeader("Content-Type");
        //String accept = request.getHeader("accept");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
