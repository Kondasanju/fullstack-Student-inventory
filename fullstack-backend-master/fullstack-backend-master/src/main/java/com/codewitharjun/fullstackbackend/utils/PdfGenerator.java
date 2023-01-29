package com.codewitharjun.fullstackbackend.utils;

import com.codewitharjun.fullstackbackend.model.User;
import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;

import com.lowagie.text.DocumentException;

import com.lowagie.text.Font;

import com.lowagie.text.FontFactory;

import com.lowagie.text.PageSize;

import com.lowagie.text.Paragraph;

import com.lowagie.text.Phrase;

import com.lowagie.text.pdf.*;

public class PdfGenerator {

    public void generate(List<User> userList, HttpServletResponse response)throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        fontTitle.setSize(20);

        // Creating paragraph

        Paragraph paragraph1 = new Paragraph("List of the Users", fontTitle);

        // Aligning the paragraph in the document

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph1);
        PdfPTable table=new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3,3,3,3});
        table.setSpacingBefore(5);
        PdfPCell cell=new PdfPCell();
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);
        Font font= FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("UserName",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email",font));
        table.addCell(cell);

        for (User user: userList){
            table.addCell(String.valueOf(user.getId()));

            table.addCell(String.valueOf(user.getName()));

            table.addCell(String.valueOf(user.getUsername()));

            table.addCell(String.valueOf(user.getEmail()));

        }
        document.add(table);

        document.close();
    }
}
