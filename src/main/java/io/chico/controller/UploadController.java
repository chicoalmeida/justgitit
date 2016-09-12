package io.chico.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Francisco Almeida
 * @since 24/08/2016
 */
@RestController
@Log
public class UploadController {

    @Value("${spring.http.multipart.location}")
    private String location;

    @RequestMapping(value = "/upload", method = POST, produces = IMAGE_GIF_VALUE)
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file,
                                         @RequestParam("start") int start,
                                         @RequestParam("end") int end,
                                         @RequestParam("speed") int speed,
                                         @RequestParam("repeat") boolean repeat) throws IOException {

        File videoFile = new File(location + "/" + System.currentTimeMillis() + ".mp4");
        file.transferTo(videoFile);
        log.info("Saved file to ::"+  videoFile.getAbsolutePath());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
