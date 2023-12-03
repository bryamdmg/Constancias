package uv.mx.fei.logic.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class TemplateJury {

    String professorName;
    String servedAs;
    String degreeName;
    List<JuryInfo> infos;
    String directorName;
    String fileName;
    Document document;
    FileOutputStream fileOutputStream;
    Paragraph tile;

    public TemplateJury (String professorName, String servedAs, String degreeName, List<JuryInfo> infos, String directorName, String fileName) {
        this.professorName = professorName;
        this.servedAs = servedAs;
        this.degreeName = degreeName;
        this.infos = infos;
        this.directorName = directorName;
        this.fileName = fileName;

        document = new Document();
        tile = new Paragraph("Facultad de Estadística e Informatica\n\nRegión Xalapa");
    }

    public void createCertificated(){

        try {
            fileOutputStream = new FileOutputStream(fileName+".pdf");
            PdfWriter.getInstance(document, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FontFactory.register("C:\\Windows\\Fonts\\times.ttf", "TNR");
        FontFactory.register("C:\\Windows\\Fonts\\GILB____.TTF", "GILB");

        Font GLB = new Font(Font.getFamily("GILB"),8, Font.BOLD);
        Font TNRB = new Font(Font.getFamily("TNRB"), 11, Font.BOLD);
        Font TNR = new Font(Font.getFamily("TNR"), 11, Font.NORMAL);

        document.open();
        tile.setFont(GLB);
        tile.setAlignment(2);

        try {
            document.add(tile);

            document.add(Chunk.NEWLINE);

            Paragraph concern = new Paragraph("A quién corresponda");

            concern.setFont(TNRB);
            concern.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(concern);

            document.add(Chunk.NEWLINE);

            Paragraph bodyText1 = new Paragraph("El que suscribe, Director de la Facultad de " +
                    "Estadística e Informática, de la Universidad Veracruzana");

            bodyText1.setFont(TNR);
            bodyText1.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText1);

            document.add(Chunk.NEWLINE);

            Paragraph bodyText2 = new Paragraph("HACE CONSTAR");

            bodyText2.setFont(TNRB);
            bodyText2.setAlignment(Element.ALIGN_CENTER);

            document.add(bodyText2);

            document.add(Chunk.NEWLINE);

            Paragraph bodyText3 = new Paragraph("que el Mtro. " + professorName + ", fungió como" +
                    " " + servedAs +" en los siguientes trabajos recepcionales de la licenciatura " +
                    degreeName);

            bodyText3.setFont(TNR);
            bodyText3.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText3);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            PdfPCell students = new PdfPCell(new Phrase("Nombre(s) del (los) alumno(s)", TNRB));
            PdfPCell workTitle = new PdfPCell(new Phrase("Título del trabajo", TNRB));
            PdfPCell modality = new PdfPCell(new Phrase("Modalidad", TNRB));
            PdfPCell presentationDate = new PdfPCell(new Phrase("Fecha de presentación", TNRB));
            PdfPCell result = new PdfPCell(new Phrase("Resultado obtenido de la defensa", TNRB));

            table.addCell(students);
            table.addCell(workTitle);
            table.addCell(modality);
            table.addCell(presentationDate);
            table.addCell(result);

            for (JuryInfo infos : this.infos) {
                table.addCell(infos.getStudents());
                table.addCell(infos.getWorkTitle());
                table.addCell(infos.getModality());
                table.addCell(infos.getPresentationDate());
                table.addCell(infos.getResult());
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph bodyText4 = new Paragraph("A petición del interesado y para los fines " +
                    "legales que al mismo convenga, se extiende la presente en la ciudad de " +
                    "Xalapa-Enríquez, Veracruz a los "+ java.time.LocalDate.now());

            bodyText4.setFont(TNR);
            bodyText4.setAlignment(Element.ALIGN_JUSTIFIED);

            document.add(bodyText4);

            document.add(Chunk.NEWLINE);

            Paragraph closing = new Paragraph("A t e n t a m e n t e\n" +
                    "“Lis de Veracruz: Arte, Ciencia, Luz”\n" +
                    "\n" +
                    "\n" + directorName + "\n" +
                    "Director\n" +
                    "\n");

            closing.setFont(TNRB);
            closing.setAlignment(Element.ALIGN_CENTER);

            document.add(closing);

        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        } finally {
            document.close();
        }
    }

}
