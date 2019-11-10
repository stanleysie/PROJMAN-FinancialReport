package view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Computation;
import model.ComputationDaily;
import model.ComputationMonthly;
import model.Data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Testing implements View {

    @FXML
    private TextField name, address;
    @FXML
    private Button generate, adminEdit, logout;

    private Stage stage;
    private Paragraph NEWLINE;
    private ObservableList<Data> basic, monthlyCost, governmental;
    private PdfPCell space;
    private Font regular, bold, small;
    private Computation comp;

    public Testing(Stage stage) {
        System.out.println("SCENE: TESTING");
        this.stage = stage;
        Scene scene = FXMLClass.getScene("/view/Testing.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        setup();
    }

    public void initialize() {
        generate.setDefaultButton(true);
        generate.setOnAction(event -> {
            System.out.println("PRINTING");
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
            Success success = new Success(stage);
        });

        adminEdit.setOnAction(event -> {
            Stage stage = (Stage) adminEdit.getScene().getWindow();
            Maintenance maintenance = new Maintenance(stage);
        });

        adminEdit.setOnMouseEntered(event -> {
            adminEdit.setStyle("-fx-background-color: #535353");
        });

        adminEdit.setOnMouseExited(event -> {
            adminEdit.setStyle("-fx-background-color: #ef5350");
        });

        generate.setOnMouseEntered(event -> {
            generate.setStyle("-fx-background-color: #535353");
        });

        generate.setOnMouseExited(event -> {
            generate.setStyle("-fx-background-color: #ef5350");
        });

        logout.setOnAction(event ->{
            Stage stage = (Stage) logout.getScene().getWindow();
            Login login = new Login(stage);
        });

        logout.setOnMouseEntered(event -> {
            logout.setStyle("-fx-background-color: #535353");
        });

        logout.setOnMouseExited(event -> {
            logout.setStyle("-fx-background-color: #ef5350");
        });
    }

    public void generate() throws IOException, DocumentException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("files/logo.png").toURI());
        Image image = Image.getInstance(path.toAbsolutePath().toString());
        image.scaleToFit(100, 100);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Testing.pdf"));
        document.setPageSize(PageSize.LETTER);
        document.open();

        document.add(image);
        document.add(NEWLINE);
        Paragraph para = new Paragraph();
        para.add(new Chunk(name.getText(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        para.add(Chunk.NEWLINE);
        para.add(new Chunk(address.getText(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        para.add(Chunk.NEWLINE);
        document.add(para);
        document.add(NEWLINE);
        setData("NCR", "Daily", 537.00, 314, 0, 0);
        PdfPTable table = createTable("Daily", 314);
        document.add(table);
        document.add(NEWLINE);
        document.add(NEWLINE);
        signatories(document);
        document.close();
    }

    private PdfPTable createTable(String type, int workingDays) {
        float[] width = {5, 2, 2};
        PdfPTable table = new PdfPTable(width);
        PdfPCell emptyCell = new PdfPCell();
        PdfPCell value = new PdfPCell();
        emptyCell.setColspan(2);
        emptyCell.setBorderColor(BaseColor.WHITE);
        value.setColspan(1);
        value.setBorderColor(BaseColor.WHITE);
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
        header.setBorderColor(BaseColor.WHITE);
        header.setColspan(3);
        table.addCell(header);
    }

    private void addHeader(PdfPTable table, String title, double value) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        PdfPCell header1 = new PdfPCell();
        PdfPCell header2 = new PdfPCell();
        header1.setPhrase(new Phrase(new Chunk(title, regular)));
        header2.setPhrase(new Phrase(new Chunk("P" + df.format(value), regular)));
        header1.setBorderColor(BaseColor.WHITE);
        header2.setBorderColor(BaseColor.WHITE);
        header1.setColspan(2);
        header2.setColspan(1);
        header2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(header1);
        table.addCell(header2);
    }

    private void addHeader(PdfPTable table, String title, double percent, double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        PdfPCell header1 = new PdfPCell();
        PdfPCell header2 = new PdfPCell();
        PdfPCell header3 = new PdfPCell();
        header1.setPhrase(new Phrase(new Chunk(title, regular)));
        header1.setBorderColor(BaseColor.WHITE);
        header1.setColspan(1);
        header2.setColspan(1);
        header3.setColspan(1);
        header2.setPhrase(new Phrase(new Chunk(df.format(percent) + "%", regular)));
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setBorderColor(BaseColor.WHITE);
        header3.setPhrase(new Phrase(new Chunk("P" + df.format(value), regular)));
        header3.setBorderColor(BaseColor.WHITE);
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
        cellLeft.setBorderColor(BaseColor.WHITE);
        cellRight.setBorderColor(BaseColor.WHITE);
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
        PdfPTable table = new PdfPTable(3);
        PdfPCell cell1 = new PdfPCell();
        PdfPCell cell2 = new PdfPCell();
        PdfPCell cell3 = new PdfPCell();
        cell1.setColspan(1);
        cell2.setColspan(1);
        cell3.setColspan(1);
        cell1.setBorderColor(BaseColor.WHITE);
        cell2.setBorderColor(BaseColor.WHITE);
        cell3.setBorderColor(BaseColor.WHITE);
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setPhrase(new Phrase(new Chunk("Created By", small)));
        cell2.setPhrase(new Phrase(" "));
        cell3.setPhrase(new Phrase(" "));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(space);
        table.addCell(space);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setPhrase(new Phrase(new Chunk("Name", small)));
        cell2.setPhrase(new Phrase(" "));
        cell3.setPhrase(new Phrase(" "));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(space);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setPhrase(new Phrase(new Chunk("Reviewed By", small)));
        cell2.setPhrase(new Phrase(" ", small));
        cell3.setPhrase(new Phrase("Approved By", small));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(space);
        table.addCell(space);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setPhrase(new Phrase(new Chunk("HR Manager", small)));
        cell2.setPhrase(new Phrase("Accounting Supervisor", small));
        cell3.setPhrase(new Phrase("General Manager", small));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        document.add(table);
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
        space.setBorderColor(BaseColor.WHITE);
        space.setHorizontalAlignment(Element.ALIGN_CENTER);
        space.setColspan(3);
        NEWLINE = new Paragraph();
        NEWLINE.add(Chunk.NEWLINE);
        regular = FontFactory.getFont(FontFactory.HELVETICA, 12);
        bold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        small = FontFactory.getFont(FontFactory.HELVETICA, 10);
    }
}