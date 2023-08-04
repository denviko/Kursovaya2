package ru.hogwarts.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.model.Avatar;
import ru.hogwarts.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping("/avatar")
@RestController
public class AvatarController {
    private final AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam long studentId, MultipartFile file) {
        try {
            service.upload(studentId, file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(200).build();

    }

    @GetMapping("/studentId")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long studentId) {
        Avatar avatar = service.findAvatar(studentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("/file/{studentId}")
    public void downloadAvatarFile(@PathVariable Long studentId, HttpServletResponse response) {
        Avatar avatar = service.findAvatar(studentId);
        try (var out = response.getOutputStream();
             var in = new FileInputStream(avatar.getPath())) {
            in.transferTo(out);
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getSize());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/avatar")
    public List<Avatar> getPage(@RequestParam int page, @RequestParam int size) {
        return service.getAvatarPage(page, size);
    }

}