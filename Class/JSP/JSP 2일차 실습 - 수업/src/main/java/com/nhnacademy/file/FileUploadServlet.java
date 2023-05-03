package com.nhnacademy.file;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet (name = "fileUploadServlet", urlPatterns = "/file/fileUpload")

@MultipartConfig(
//        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100, // 100 MB
        location = "/Users/kimhwajeong/Code/GitHub/NHNAcademy/ing../JSP/JSP 2일차 실습 - 수업/upload/temp"    // upload하면 서버가 파일을 저장할 임시 폴더
)

@Slf4j
public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/kimhwajeong/Code/GitHub/NHNAcademy/ing../JSP/JSP 2일차 실습 - 수업/upload";     // 저장할 경로

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part: req.getParts()) {
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);
            // content-disposition header = form-data; name=file1; filename1.pdf
            if (contentDisposition.contains("filename=")) {
                if (part.getSize() > 0) {
                    String fileName = getFileName(contentDisposition);
                    part.write(UPLOAD_DIR + File.separator + fileName);     // upload_dir에 해당하는 경로로 업로드 완료
                    part.delete();  // 임시 저장했던 temp 폴더에 있는 파일 삭제
                }
            } else {
                String value = req.getParameter(part.getName());
                log.info("name: {}, value: {}", part.getName(), value);
            }
        }
        resp.sendRedirect("/");
    }

    private String getFileName(String contentDisposition) {
        String result = null;
        for (String token: contentDisposition.split(";")) {
            if (token.trim().startsWith("filename=")) {
                result = token.trim().replace("filename=", "");
                result = result.replaceAll("[\"]", "");
                break;
            }
        }
        return result;
    }
}
