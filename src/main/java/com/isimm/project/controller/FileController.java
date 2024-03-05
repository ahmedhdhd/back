package com.isimm.project.controller;

import com.isimm.project.repository.UploadDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class FileController {

	private static final String path = "C:/Users/DELL/Desktop/Files";

	@Autowired
	UploadDownloadService service;

	@PostMapping("/upload")
	public ResponseEntity<List<String>> fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
		return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);

	}
	@GetMapping(path = "/download/{name}")
	public ResponseEntity<Resource> download(@PathVariable("name") String name) throws IOException {
		File file = new File(path, name); // Corrected the path construction

		// Check if the file exists
		if (!file.exists()) {
			// Handle the case where the file does not exist, e.g., return an error response
			return ResponseEntity.notFound().build();
		}

		Path filePath = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));

		return ResponseEntity.ok()
				.headers(headers(name))
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	@GetMapping("/files")
	public ResponseEntity<List<String>> getListOfFiles() throws Exception {
		return new ResponseEntity<>(service.getListofFiles(), HttpStatus.OK);

	}

	private HttpHeaders headers(String name) {
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		return header;

	}
}
