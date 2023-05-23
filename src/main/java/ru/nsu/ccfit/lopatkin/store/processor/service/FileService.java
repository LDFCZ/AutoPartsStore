package ru.nsu.ccfit.lopatkin.store.processor.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.UploadFileDTO;
import ru.nsu.ccfit.lopatkin.store.common.repository.FileRepository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public File getFile(String id) throws IOException {
        ObjectId objectId = new ObjectId(id);
        GridFsResource gridFsResource = fileRepository.getResource(objectId);
        return getFile(gridFsResource);
    }

    public UploadFileDTO uploadFile(MultipartFile file) throws IOException {
        ObjectId id = fileRepository.saveFile(file.getInputStream(), file.getOriginalFilename());
        return new UploadFileDTO(id.toHexString(), file.getOriginalFilename());
    }

    public void deleteFile(String id) {
        fileRepository.deleteFile(new ObjectId(id));
    }

    private File getFile(GridFsResource gridFsResource) throws IOException {
        byte[] fileData = getFileData(gridFsResource);
        String fileName = gridFsResource.getFilename();
        File file = File.createTempFile("temp-", fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(fileData);
        fileOutputStream.close();
        return file;
    }

    private byte[] getFileData(GridFsResource gridFsResource) throws IOException {
        InputStream inputStream = gridFsResource.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int bytesRead;
        byte[] buffer = new byte[4096];

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] fileData = outputStream.toByteArray();
        outputStream.close();
        inputStream.close();

        return fileData;
    }
}
