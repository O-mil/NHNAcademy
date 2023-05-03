package com.nhnacademy.file;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@WebServlet(name = "fileDownloadServlet", urlPatterns = "/file/fileDownload")

@Slf4j
public class FiileDownloadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "/Users/kimhwajeong/Code/GitHub/NHNAcademy/ing../JSP/JSP 2일차 실습 - 수업/upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //download?fileName=1.pdf
        //download?fileName=
        String fileName = req.getParameter("fileName");
        if (Objects.isNull(fileName) || fileName.isEmpty()) {
            resp.sendError(400);
            return;
        }

        String filePath = UPLOAD_DIR + File.separator + fileName;
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            log.error("file이 없어!!!!: " + fileName);
            resp.sendError(404);
            return;
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (InputStream inputStream = Files.newInputStream(path);       // 자동으로 close를 시켜줘서 try()문 안에 쓰는 거임.
             OutputStream outputStream = resp.getOutputStream()
        ) {
            byte[] buffer = new byte[2048];

            int size;
            while ((size = inputStream.read(buffer)) > -1) {
                outputStream.write(buffer, 0, size);
            }
        } catch (IOException e) {
            log.error("file download: {}", fileName);
        }
    }
}
