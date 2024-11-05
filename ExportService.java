package components;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportService {

    public void exportToPdf(List<UserResponse> responses, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        for (UserResponse response : responses) {
            document.add(new Paragraph("Checklist Item: " + response.getChecklistItem().getDescription()));
            document.add(new Paragraph("Response: " + response.getResponse()));
            document.add(new Paragraph("Comment: " + response.getComment()));
            document.add(new Paragraph("\n"));
        }

        document.close();
    }

    public void exportToExcel(List<UserResponse> responses, OutputStream outputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Checklist Responses");
        int rowIndex = 0;

        for (UserResponse response : responses) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(response.getChecklistItem().getDescription());
            row.createCell(1).setCellValue(response.getResponse());
            row.createCell(2).setCellValue(response.getComment());
        }

        workbook.write(outputStream);
        workbook.close();
    }
}
