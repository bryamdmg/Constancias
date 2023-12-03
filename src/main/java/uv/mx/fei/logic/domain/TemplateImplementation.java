package uv.mx.fei.logic.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class TemplateImplementation {
    String professorName;
    String periodName;
    String educativeProgram;
    String educativeExperience;
    String BSC;
    String HSM;
    String directorName;
    String fileName;
    Document document;
    FileOutputStream fileOutputStream;
    Paragraph title;

    public TemplateImplementation (String professorName, String periodName, String educativeProgram, String educativeExperience, String BSC, String HSM, String directorName, String fileName) {
        this.professorName = professorName;
        this.periodName = periodName;
        this.educativeProgram = educativeProgram;
        this.educativeExperience = educativeExperience;
        this.BSC = BSC;
        this.HSM = HSM;
        this.directorName = directorName;
        this.fileName = fileName;

        document = new Document();
        title = new Paragraph("Facultad de Estadística e Informatica\nRegión Xalapa");
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
        title.setFont(fontGILB);
        title.setAlignment(2);

        try {
            document.add(title);

            document.add(Chunk.NEWLINE);

            Paragraph concern = new Paragraph("A quién corresponda\npresente");

            concern.setFont(fontTNRB);
            concern.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(concern);

            document.add(Chunk.NEWLINE);

            Paragraph bodyText1 = new Paragraph("El que suscribe, Director de la Facultad de " +
                    "Estadística e Informática, de la Universidad Veracruzana");

            bodyText1.setFont(fontTNR);
            bodyText1.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText1);

            Paragraph bodyText2 = new Paragraph("HACE CONSTAR");

            bodyText2.setFont(fontTNRB);
            bodyText2.setAlignment(Element.ALIGN_CENTER);

            document.add(bodyText2);

            Paragraph bodyText3 = new Paragraph("que el Mtro. " + professorName +
                    ", impartió la siguiente experiencia educativa en el periodo " +
                    periodName);

            bodyText3.setFont(fontTNR);
            bodyText3.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText3);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            PdfPCell program = new PdfPCell(new Phrase("Programa educativo", fontTNRB));
            PdfPCell ee = new PdfPCell(new Phrase("Experiencia educativa", fontGILB));
            PdfPCell bscCell = new PdfPCell(new Phrase("Bloque/Sección/Créditos", fontTNRB));
            PdfPCell hsmCell = new PdfPCell(new Phrase("H/S/M", fontTNRB));

            table.addCell(program);
            table.addCell(ee);
            table.addCell(bscCell);
            table.addCell(hsmCell);

            table.addCell(educativeProgram);
            table.addCell(educativeExperience);
            table.addCell(BSC);
            table.addCell(HSM);

            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph bodyText4 = new Paragraph("A petición de la interesada y para los fines " +
                    "legales que a la misma convenga; se extiende la presente en la ciudad de " +
                    "Xalapa-Enríquez, Veracruz a los "+ java.time.LocalDate.now());

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
    }
}
