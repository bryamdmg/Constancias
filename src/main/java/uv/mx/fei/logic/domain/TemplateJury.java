package uv.mx.fei.logic.domain;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Date;

public class TemplateJury {

    String professorName;
    String servedAs;
    String degreeName;
    String workTitle;
    Date presentationDate;
    String result;

    Document document;
    FileOutputStream fileOutputStream;
    Paragraph tile;

    public TemplateJury (String professorName, String servedAs, String degreeName, String workTitle, Date presentationDate, String result) {
        this.professorName = professorName;
        this.servedAs = servedAs;
        this.degreeName = degreeName;
        this.workTitle = workTitle;
        this.presentationDate = presentationDate;
        this.result = result;

        document = new Document();
        tile = new Paragraph("Facultad de Estadística e Informatica\n\nRegión Xalapa");
    }

    public void createCertificated(){

        try {
            fileOutputStream = new FileOutputStream("archivo.pdf");
            PdfWriter.getInstance(document, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        document.open();
        tile.setAlignment(2);

        try {
            document.add(tile);
        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        } finally {
            document.close();
        }
    }

}
