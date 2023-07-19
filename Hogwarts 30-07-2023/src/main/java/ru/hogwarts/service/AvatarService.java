package ru.hogwarts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.Repository.AvatarRepository;
import ru.hogwarts.model.Avatar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service

public class AvatarService {
    private final StudentService studentService;
    private final AvatarRepository repository;


    private final String avatarsPath;

    public AvatarService(StudentService studentService,
                         AvatarRepository repository,
                         @Value("${avatars.dir.path}") String avatarsPath) {
        this.studentService = studentService;
        this.repository = repository;
        this.avatarsPath = avatarsPath;
    }

    public void upload(long studentId, MultipartFile file) throws IOException {
        var student = studentService.get(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        var ext = file.getName().substring(file.getName().lastIndexOf(".") +1);
        var dir = Path.of(avatarsPath).toFile();
        if (!dir.exists()) {
            Files.createDirectories(Path.of(avatarsPath));
        }
        var path = avatarsPath + "/" + student.getId() + "." + file.getName() + "." + ext;
        try (var in = file.getInputStream();
             var out = new BufferedOutputStream(new FileOutputStream(path))) {
            in.transferTo(out);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setData(file.getBytes());
        avatar.setPath(path);
        avatar.setSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        repository.save(avatar);
    }

    public Avatar findAvatar(long studentId) {
        return repository.findByStudentId(studentId).orElse(new Avatar());

    }

}
