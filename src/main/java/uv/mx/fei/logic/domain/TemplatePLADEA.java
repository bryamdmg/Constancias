package uv.mx.fei.logic.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class TemplatePLADEA {
    String professorName;
    String strategicAxis;
    String strategicProgram;
    String generalObjectives;
    String actions;
    String goals;
    String directorName;
    String fileName;
    Document document;
    FileOutputStream fileOutputStream;
    Paragraph title;

    public TemplatePLADEA (String professorName, String strategicAxis, String strategicProgram, String generalObjectives, String actions, String goals, String directorName, String fileName) {
        this.professorName = professorName;
        this.strategicAxis = strategicAxis;
        this.strategicProgram = strategicProgram;
        this.generalObjectives = generalObjectives;
        this.actions = actions;
        this.goals = goals;
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
                    "Estadística e Informática de la Universidad Veracruzana");

            bodyText1.setFont(fontTNR);
            bodyText1.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText1);

            //document.add(Chunk.NEWLINE);

            Paragraph bodyText2 = new Paragraph("HACE CONSTAR");

            bodyText2.setFont(fontTNRB);
            bodyText2.setAlignment(Element.ALIGN_CENTER);

            document.add(bodyText2);

            //document.add(Chunk.NEWLINE);

            Paragraph bodyText3 = new Paragraph("que la Mtra. " + professorName + ", Profesora " +
                    "adscrita a esta Facultad a mí cargo contribuyó a la consecución de las siguientes " +
                    "metas del Plan de Desarrollo de la Entidad Académica (PlaDEA) 2017-2021:");

            bodyText3.setFont(fontTNR);
            bodyText3.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText3);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            PdfPCell axis = new PdfPCell(new Phrase("Nombre(s) del (los) alumno(s)", fontTNRB));
            PdfPCell program = new PdfPCell(new Phrase("Título del trabajo", fontGILB));
            PdfPCell objectives = new PdfPCell(new Phrase("Modalidad", fontTNRB));
            PdfPCell actionsCell = new PdfPCell(new Phrase("Fecha de presentación", fontTNRB));
            PdfPCell goalsCell = new PdfPCell(new Phrase("Resultado obtenido de la defensa", fontTNRB));

            table.addCell(axis);
            table.addCell(program);
            table.addCell(objectives);
            table.addCell(actionsCell);
            table.addCell(goalsCell);

            table.addCell(strategicAxis);
            table.addCell(strategicProgram);
            table.addCell(generalObjectives);
            table.addCell(actions);
            table.addCell(goals);

            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph bodyText4 = new Paragraph("A petición de la interesado y para los usos " +
                    "legales que a la mismoa convengan, se extiende la presente en la ciudad de " +
                    "Xalapa de Enríquez, Veracruz a los "+ java.time.LocalDate.now());

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
