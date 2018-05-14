/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.PartialResponseWriter;
import javax.servlet.http.Part;

@ManagedBean
@ViewScoped
public class Fileupload {

    private Part uploadedFile;
    private String folder = "c:\\files";

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void saveFile() {
        System.out.println("ELSŐ");
        try (InputStream input = uploadedFile.getInputStream()) {
            System.out.println("MÁSODIK");
            String fileName = uploadedFile.getSubmittedFileName();
            System.out.println("HARMADIK");
            Files.copy(input, new File(folder, fileName).toPath());
            System.out.println("NEGYEDIK");
        } catch (IOException e) {
            System.out.println("ÖTÖDIK");
            e.printStackTrace();
        }
    }

}
