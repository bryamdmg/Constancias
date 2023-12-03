package uv.mx.fei.logic.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import uv.mx.fei.gui.AlertPopUpGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class TemplateProject {
    String professorName;
    String project;
    String duration;
    String place;
    String involved;
    String impact;
    String directorName;
    String fileName;
    Document document;
    FileOutputStream fileOutputStream;

    public TemplateProject (String professorName, String project, String duration, String place, String involved, String impact, String directorName, String fileName) {
        this.professorName = professorName;
        this.project = project;
        this.duration = duration;
        this.place = place;
        this.involved = involved;
        this.impact = impact;
        this.directorName = directorName;
        this.fileName = fileName;

        document = new Document();
    }

    public void createCertificated(){

        try {
            fileOutputStream = new FileOutputStream(fileName+".pdf");
            PdfWriter.getInstance(document, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String TNR = "C:\\Windows\\Fonts\\times.ttf";
        String GILB = "C:\\Windows\\Fonts\\GILB____.TTF";

        Font fontTNR = FontFactory.getFont(TNR, BaseFont.IDENTITY_H, true);
        fontTNR.setStyle(Font.NORMAL);
        Font fontTNRB = FontFactory.getFont(TNR, BaseFont.IDENTITY_H, true);
        fontTNRB.setStyle(Font.BOLD);
        Font fontGILB = FontFactory.getFont(GILB, BaseFont.IDENTITY_H, true);
        fontGILB.setStyle(Font.NORMAL);

        document.open();

        try {
            Paragraph concern = new Paragraph("A quién corresponda");

            concern.setFont(fontTNRB);
            concern.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(concern);

            document.add(Chunk.NEWLINE);

            Paragraph bodyText1 = new Paragraph("El que suscribe, "+ directorName +
                    " Director de la Facultad de Estadística e Informática, de la Universidad Veracruzana");

            bodyText1.setFont(fontTNR);
            bodyText1.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText1);

            Paragraph bodyText2 = new Paragraph("HACE CONSTAR");

            bodyText2.setFont(fontTNRB);
            bodyText2.setAlignment(Element.ALIGN_CENTER);

            document.add(bodyText2);


            Paragraph bodyText3 = new Paragraph("que la "+ professorName +", " +
                    "participó en un proyecto de campo con las siguientes características:");

            bodyText3.setFont(fontTNR);
            bodyText3.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText3);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            PdfPCell projectCell = new PdfPCell(new Phrase("Proyecto realizado", fontTNRB));
            PdfPCell durationCell = new PdfPCell(new Phrase("Duración", fontGILB));
            PdfPCell placeCell = new PdfPCell(new Phrase("Lugar donde se desarrolló", fontTNRB));
            PdfPCell involvedCell = new PdfPCell(new Phrase("Nombre de las y los involucrados", fontTNRB));

            table.addCell(projectCell);
            table.addCell(durationCell);
            table.addCell(placeCell);
            table.addCell(involvedCell);

            table.addCell(project);
            table.addCell(duration);
            table.addCell(place);
            table.addCell(involved);

            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph bodyText4 = new Paragraph("Impacto obtenido: " +impact);

            bodyText4.setFont(fontTNR);
            bodyText4.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText4);

            document.add(Chunk.NEWLINE);

            Paragraph closing = new Paragraph("A t e n t a m e n t e\n" +
                    "“Lis de Veracruz: Arte, Ciencia, Luz”\n" +
                    "\n" +
                    "\n" + directorName + "\n" +
                    "Director");

            closing.setFont(fontTNRB);
            closing.setAlignment(Element.ALIGN_CENTER);

            document.add(closing);

        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        } finally {
            document.close();
        }

        File file = new File(fileName+".pdf");
        try {
            copyFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile(File file) throws IOException {
        File fileToSave = new File(System.getProperty("user.home")+"/Downloads/"+file.getName());
        if (fileToSave.exists()) {
            if (confirmedCopyFile(file.getName())) {
                fileToSave.delete();
                Files.copy(file.toPath(), fileToSave.toPath());
                AlertPopUpGenerator.showConfirmationMessage("Constancia generada", "Revisa la carpeta de descargas");
            }
        } else {
            Files.copy(file.toPath(), fileToSave.toPath());
            AlertPopUpGenerator.showConfirmationMessage("Constancia generada", "Revisa la carpeta de descargas");
        }
    }

    public boolean confirmedCopyFile(String fileName) {
        return AlertPopUpGenerator.showConfirmationMessage("Archivo existente","El archivo con el nombre "+fileName+" ya existe, ¿Deseas sobreescribirlo?");
    }
}
