package view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class GeneratePDF extends PdfPageEventHelper implements View {

    @FXML
    private Button generate;

    private Master master;
    private Stage stage;
    private Paragraph NEWLINE;
    private ObservableList<Data> basic, monthlyCost, governmental;
    private PdfPCell space;
    private PdfWriter writer;
    private Font regular, bold, small;
    private Computation comp;
    private ObservableList<String> locationList, allowanceList, rateTypeList;

    public GeneratePDF(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/GeneratePDF.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        setup();
    }

    public void initialize() {
        generate.setDefaultButton(true);
        generate.setOnAction(event -> {
            boolean done = true;

            if(done) {
                try {
                    generate();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) generate.getScene().getWindow();
                Success success = new Success(stage, master);
            }
        });

        generate.setOnMouseEntered(event -> {
            generate.setStyle("-fx-background-color: darkgrey");
        });

        generate.setOnMouseExited(event -> {
            generate.setStyle("-fx-background-color: lightgrey");
        });
    }

    public void generate() throws IOException, DocumentException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("files/logo.png").toURI());
        Image image = Image.getInstance(path.toAbsolutePath().toString());
        image.scaleToFit(100, 100);

        Document document = new Document();
        writer = PdfWriter.getInstance(document, new FileOutputStream(master.getFileName() + ".pdf"));
        document.setPageSize(PageSize.LETTER);
        document.open();

        document.add(image);
        Paragraph para = new Paragraph();
        para.add(new Chunk(master.getCurrentEmployee().getName(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))); // add name
        para.add(Chunk.NEWLINE);
        para.add(new Chunk(master.getCurrentEmployee().getProvince(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))); // add address
        para.add(Chunk.NEWLINE);
        document.add(para);
        document.add(NEWLINE);
        setData("NCR", "Daily", 537.00, 314, 0, 0);
        PdfPTable table = createTable("Daily", 314);
        document.add(table);
        document.add(NEWLINE);
        document.add(NEWLINE);
        signatories(document);
        addFooter(writer, document);
        document.close();
    }

    private PdfPTable createTable(String type, int workingDays) {
        float[] width = {5, 2, 2};
        PdfPTable table = new PdfPTable(width);
        PdfPCell emptyCell = new PdfPCell();
        PdfPCell value = new PdfPCell();
        emptyCell.setColspan(2);
        emptyCell.setBorder(Rectangle.NO_BORDER);
        value.setColspan(1);
        value.setBorder(Rectangle.NO_BORDER);
        value.setHorizontalAlignment(Element.ALIGN_CENTER);
        emptyCell.setPhrase(new Phrase(" "));
        table.addCell(emptyCell);
        value.setPhrase(new Phrase(new Chunk(type.toUpperCase(), bold)));
        table.addCell(value);
        table.addCell(emptyCell);
        value.setPhrase(new Phrase(new Chunk("" + workingDays, bold)));
        table.addCell(value);

        addHeader(table, "A. Basic Salary per day");
        addRows(table, basic);
        addHeader(table, "B. Equivalent Monthly Cost", 15222.46);
        addRows(table, monthlyCost);
        addHeader(table, "C. Government Requirements");
        addRows(table, governmental);
        addHeader(table, "D. Total Monthly Labor Cost", 16645.67);
        table.addCell(space);
        addHeader(table, "E. BIZSOLV ADMIN COST", 12.00, 1997.48);
        table.addCell(space);
        addHeader(table, "F. CONTRACT COST/MONTH", 18643.15);
        return table;
    }

    private void addHeader(PdfPTable table, String title) {
        PdfPCell header = new PdfPCell();
        header.setPhrase(new Phrase(new Chunk(title, regular)));
        header.setBorder(Rectangle.NO_BORDER);
        header.setColspan(3);
        table.addCell(header);
    }

    private void addHeader(PdfPTable table, String title, double value) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        PdfPCell header1 = new PdfPCell();
        PdfPCell header2 = new PdfPCell();
        header1.setPhrase(new Phrase(new Chunk(title, regular)));
        header2.setPhrase(new Phrase(new Chunk("P" + df.format(value), regular)));
        header1.setBorder(Rectangle.NO_BORDER);
        header2.setBorder(Rectangle.NO_BORDER);
        header1.setColspan(2);
        header2.setColspan(1);
        header2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(header1);
        table.addCell(header2);
    }

    private void addHeader(PdfPTable table, String title, double percent, double value) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        PdfPCell header1 = new PdfPCell();
        PdfPCell header2 = new PdfPCell();
        PdfPCell header3 = new PdfPCell();
        header1.setPhrase(new Phrase(new Chunk(title, regular)));
        header1.setBorder(Rectangle.NO_BORDER);
        header1.setColspan(1);
        header2.setColspan(1);
        header3.setColspan(1);
        header2.setPhrase(new Phrase(new Chunk(df.format(percent) + "%", regular)));
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setBorder(Rectangle.NO_BORDER);
        header3.setPhrase(new Phrase(new Chunk("P" + df.format(value), regular)));
        header3.setBorder(Rectangle.NO_BORDER);
        header3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
    }

    private void addRows(PdfPTable table, ObservableList<Data> data) {
        PdfPCell cellLeft = new PdfPCell();
        PdfPCell cellRight = new PdfPCell();
        DecimalFormat df = new DecimalFormat("#,###.00");

        cellLeft.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellRight.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellLeft.setBorder(Rectangle.NO_BORDER);
        cellRight.setBorder(Rectangle.NO_BORDER);
        cellLeft.setColspan(2);
        cellRight.setColspan(1);

        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getName().equalsIgnoreCase("sub-total")) {
                cellLeft.setPhrase(new Phrase(new Chunk("          " + data.get(i).getName(), bold)));
                if(data.get(i).getValue() == 0) {
                    cellRight.setPhrase(new Phrase(new Chunk("-", bold)));
                } else {
                    cellRight.setPhrase(new Phrase(new Chunk("P" + df.format(data.get(i).getValue()), bold)));
                }
            } else {
                cellLeft.setPhrase(new Phrase(new Chunk("          " + data.get(i).getName(), regular)));
                if(data.get(i).getValue() == 0) {
                    cellRight.setPhrase(new Phrase(new Chunk("-", regular)));
                } else {
                    cellRight.setPhrase(new Phrase(new Chunk("P" + df.format(data.get(i).getValue()), regular)));
                }
            }
            table.addCell(cellLeft);
            table.addCell(cellRight);
            if(data.size() > 3) {
                if(i == data.size() - 2) {
                    table.addCell(space);
                }
            }
        }
        table.addCell(space);
    }

    private void signatories(Document document) throws DocumentException {
        float[] width = {1, 4, 1, 4, 1, 4, 1};
        PdfPTable table = new PdfPTable(width);
        PdfPCell smallSpace = new PdfPCell();
        PdfPCell cell1 = new PdfPCell();
        PdfPCell cell2 = new PdfPCell();
        PdfPCell cell3 = new PdfPCell();
        smallSpace.setColspan(1);
        smallSpace.setPhrase(new Phrase("", small));
        smallSpace.setBorder(Rectangle.NO_BORDER);
        cell1.setColspan(1);
        cell2.setColspan(1);
        cell3.setColspan(1);
        space.setColspan(7);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBorder(Rectangle.NO_BORDER);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell3.setBorder(Rectangle.NO_BORDER);
        cell1.setPhrase(new Phrase(new Chunk("Created By", small)));
        addCells(table, cell1, cell2, cell3, smallSpace);
        table.addCell(space);
        table.addCell(space);
        cell1.setPhrase(new Phrase(""));
        cell1.setBorder(Rectangle.BOTTOM);
        addCells(table, cell1, cell2, cell3, smallSpace);
        cell1.setPhrase(new Phrase(new Chunk("Name", small)));
        cell1.setBorder(Rectangle.NO_BORDER);
        addCells(table, cell1, cell2, cell3, smallSpace);
        table.addCell(space);
        cell1.setPhrase(new Phrase(new Chunk("Reviewed By", small)));
        cell2.setPhrase(new Phrase(""));
        cell3.setPhrase(new Phrase("Approved By", small));
        addCells(table, cell1, cell2, cell3, smallSpace);
        table.addCell(space);
        table.addCell(space);
        cell1.setPhrase(new Phrase(""));
        cell3.setPhrase(new Phrase(""));
        cell1.setBorder(Rectangle.BOTTOM);
        cell2.setBorder(Rectangle.BOTTOM);
        cell3.setBorder(Rectangle.BOTTOM);
        addCells(table, cell1, cell2, cell3, smallSpace);
        cell1.setPhrase(new Phrase(new Chunk("HR Manager", small)));
        cell2.setPhrase(new Phrase("Accounting Supervisor", small));
        cell3.setPhrase(new Phrase("General Manager", small));
        cell1.setBorder(Rectangle.NO_BORDER);
        cell2.setBorder(Rectangle.NO_BORDER);
        cell3.setBorder(Rectangle.NO_BORDER);
        addCells(table, cell1, cell2, cell3, smallSpace);
        document.add(table);
    }

    private void addCells(PdfPTable table, PdfPCell cell1, PdfPCell cell2, PdfPCell cell3, PdfPCell smallSpace) {
        table.addCell(smallSpace);
        table.addCell(cell1);
        table.addCell(smallSpace);
        table.addCell(cell2);
        table.addCell(smallSpace);
        table.addCell(cell3);
        table.addCell(smallSpace);
    }

    private void addFooter(PdfWriter writer, Document document) {
        PdfPTable footer = new PdfPTable(1);
        try {
            footer.setWidths(new int[]{100});
            footer.setTotalWidth(527);
            footer.setLockedWidth(false);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add current page count
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Version 1.0.1") , new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }


    public void setData(String location, String type, double basicSalary, int workingDays, int daysOfIncentiveLeave, double allowance) {
        if(type.equalsIgnoreCase("daily")) {
            comp = new ComputationDaily(location, workingDays, daysOfIncentiveLeave, allowance);
        } else if(type.equalsIgnoreCase("province")) {
            comp = new ComputationMonthly(location, basicSalary, workingDays, daysOfIncentiveLeave, allowance);
        }
        basic.add(new Data("Service Fee", comp.getBasicSalary()));
        basic.add(new Data("Bonus (13th Month)", comp.getMonthBonus()));
        basic.add(new Data("5 day incentive", comp.getNumOfDayIncentive()));
        basic.add(new Data("Allowance", comp.getAllowance()));
        basic.add(new Data("Sub-total", comp.getSubTotal()));
        monthlyCost.add(new Data("Effective Monthly Rate", comp.getEffectiveMonthlyRate()));
        governmental.add(new Data("Associate Benefit-SSS (Mand Payable)", 1120.00));
        governmental.add(new Data("Associate Benefit-Philhealth", 193.21));
        governmental.add(new Data("Associate Benefit-Pag-ibig", 100.00));
        governmental.add(new Data("Associate Benefit-EC", 10.00));
        governmental.add(new Data("Sub-total", 1423.21));
    }

    private void setup() {
        basic = FXCollections.observableArrayList();
        monthlyCost = FXCollections.observableArrayList();
        governmental = FXCollections.observableArrayList();

        space = new PdfPCell();
        space.setPhrase(new Phrase(" "));
        space.setBorder(Rectangle.NO_BORDER);
        space.setHorizontalAlignment(Element.ALIGN_CENTER);
        space.setColspan(3);
        NEWLINE = new Paragraph();
        NEWLINE.add(Chunk.NEWLINE);
        regular = FontFactory.getFont(FontFactory.HELVETICA, 10);
        bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
        small = FontFactory.getFont(FontFactory.HELVETICA, 10);
    }
}