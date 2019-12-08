package view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class LoadSelect implements View {

    @FXML
    private Button load, menu, back, generate;
    @FXML
    private Label employee;
    @FXML
    private TableView table;

    private Stage stage;
    private Master master;
    private TableColumn tableColumn, version, report;
    private ObservableList<Employee> employees;
    private Paragraph NEWLINE;
    private ObservableList<Data> basic, monthlyCost, governmental;
    private PdfPCell space;
    private PdfWriter writer;
    private Font regular, bold, small;
    private Computation comp;

    public LoadSelect(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/LoadSelectView.fxml", this);
        this.stage.setScene(scene);
        this.stage.show();
        this.employees = FXCollections.observableArrayList();
        init();
        setup();
    }

    public void initialize() {
        load.setOnAction(event -> {
            Stage stage = (Stage) load.getScene().getWindow();
            LoadFile loadFile = new LoadFile(stage, master);
        });

        load.setOnMouseEntered(event -> {
            load.setStyle("-fx-background-color: #535353");
        });

        load.setOnMouseExited(event -> {
            load.setStyle("-fx-background-color: #ef5350");
        });

        generate.setOnAction(event -> {
            try {
                generate();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        generate.setOnMouseEntered(event -> {
            generate.setStyle("-fx-background-color: #535353");
        });

        generate.setOnMouseExited(event -> {
            generate.setStyle("-fx-background-color: #ef5350");
        });

        menu.setOnAction(event ->{
            Stage stage = (Stage) menu.getScene().getWindow();
            Menu menu = new Menu(stage, master);
        });

        menu.setOnMouseEntered(event -> {
            menu.setStyle("-fx-background-color: #535353");
        });

        menu.setOnMouseExited(event -> {
            menu.setStyle("-fx-background-color: #ef5350");
        });

        back.setOnAction(event ->{
            setup();
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-border-color: #535353; -fx-background-color: #535353");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: #FFFFFF");
        });

        table.setOnMouseClicked(event -> {
            if(!employee.isVisible()) {
                String name = ((Employee) table.getSelectionModel().getSelectedItem()).getName();
                master.setCurrentEmployee((Employee) table.getSelectionModel().getSelectedItem());
                setVersions(name);
            } else {
                if(table.getSelectionModel().getSelectedItem() != null) {
                    if(table.getSelectionModel().getSelectedItem() instanceof DailyReport) {
                        master.getReport(((DailyReport) table.getSelectionModel().getSelectedItem()).getVersion());
                    } else if(table.getSelectionModel().getSelectedItem() instanceof MonthlyReport) {
                        master.getReport(((MonthlyReport) table.getSelectionModel().getSelectedItem()).getVersion());
                    }
                }
            }
        });
    }

    public void setup() {
        back.setVisible(false);
        employee.setVisible(false);
        generate.setVisible(false);
        tableColumn = new TableColumn("Clients");
        tableColumn.setPrefWidth(450);
        tableColumn.setStyle("-fx-alignment: CENTER;");
        tableColumn.setCellValueFactory(new PropertyValueFactory("name"));
        tableColumn.setResizable(false);
        table.getColumns().clear();
        table.getColumns().add(tableColumn);
        table.setItems(master.getAllEmployees());
    }

    public void setVersions(String name) {
        employee.setVisible(true);
        back.setVisible(true);
        generate.setVisible(true);
        employee.setText(name);
        version = new TableColumn("Document Version");
        version.setCellValueFactory(new PropertyValueFactory("version"));
        version.setPrefWidth(300);
        version.setStyle("-fx-alignment: CENTER;");
        version.setResizable(false);
        report = new TableColumn("Report Type");
        report.setCellValueFactory(new PropertyValueFactory("type"));
        report.setPrefWidth(150);
        report.setStyle("-fx-alignment: CENTER;");
        report.setResizable(false);
        table.getColumns().clear();
        table.getColumns().addAll(version, report);
        table.setItems(master.getEmployeesVersion(name));
    }

    public void generate() throws IOException, DocumentException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("files/logo.png").toURI());
        Image image = Image.getInstance(path.toAbsolutePath().toString());
        image.scaleToFit(100, 100);

        Document document = new Document();
        String destination = "E:\\" + master.getFileName() + ".pdf";
        master.setFileDestination(destination);
        master.setFileName(master.getFileName() + ".pdf");
        writer = PdfWriter.getInstance(document, new FileOutputStream(destination));
        document.setPageSize(PageSize.LETTER);
        document.open();

        document.add(image);
        Paragraph para = new Paragraph();
        para.add(new Chunk(master.getCurrentEmployee().getName(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))); // add name
        para.add(Chunk.NEWLINE);
        para.add(new Chunk(master.getCurrentEmployee().getAddress(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))); // add address
        para.add(Chunk.NEWLINE);
        document.add(para);
        document.add(NEWLINE);
        PdfPTable table = null;
        if(master.getCurrentReport() instanceof DailyReport) {
            setData("Daily", master.getCurrentReport().getBasicRate(), Integer.parseInt("" + master.getCurrentReport().getnWorkingDays()), Integer.parseInt("" + master.getCurrentReport().getIncentive()), master.getCurrentReport().getAllowance(), master.getCurrentReport().getadmin_cost());
            table = createTable("Daily", Integer.parseInt("" + master.getCurrentReport().getnWorkingDays()));
        } else if(master.getCurrentReport() instanceof MonthlyReport) {
            setData("Monthly", master.getCurrentReport().getBasicRate(), Integer.parseInt("" + master.getCurrentReport().getnWorkingDays()), Integer.parseInt("" + master.getCurrentReport().getIncentive()), master.getCurrentReport().getAllowance(), master.getCurrentReport().getadmin_cost());
            table = createTable("Monthly", Integer.parseInt("" + master.getCurrentReport().getnWorkingDays()));
        }
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
        addHeader(table, "B. Equivalent Monthly Cost", comp.getEquivalentMonthlyCost());
        addRows(table, monthlyCost);
        addHeader(table, "C. Government Requirements");
        addRows(table, governmental);
        addHeader(table, "D. Total Monthly Labor Cost", comp.getTotalLaborCost());
        table.addCell(space);
        addHeader(table, "E. BIZSOLV ADMIN COST", comp.getAdminCost(), comp.getBizsolvAdminCost());
        table.addCell(space);
        addHeader(table, "F. CONTRACT COST/MONTH", comp.getContractCost());
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
        cell1.setPhrase(new Phrase(new Chunk(master.getCurrentReport().getCreator(), small)));
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
            footer.addCell(new Phrase("Version " + master.getCurrentReport().getVersion() , new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }


    public void setData(String type, float basicSalary, int workingDays, int daysOfIncentiveLeave, float allowance, float admin) {
        if(type.equalsIgnoreCase("daily")) {
            comp = new ComputationDaily(basicSalary, workingDays, daysOfIncentiveLeave, allowance, admin);
        } else if(type.equalsIgnoreCase("monthly")) {
            comp = new ComputationMonthly(basicSalary, workingDays, daysOfIncentiveLeave, allowance, admin);
        }
        basic.add(new Data("Service Fee", comp.getBasicSalary()));
        basic.add(new Data("Bonus (13th Month)", comp.getMonthBonus()));
        basic.add(new Data("5 day incentive", comp.getNumOfDayIncentive()));
        basic.add(new Data("Allowance", comp.getAllowance()));
        basic.add(new Data("Sub-total", comp.getSubTotal()));
        monthlyCost.add(new Data("Effective Monthly Rate", comp.getEffectiveMonthlyRate()));
        governmental.add(new Data("Associate Benefit-SSS", master.getCurrentReport().getStatutory_sss()));
        governmental.add(new Data("Associate Benefit-Philhealth", master.getCurrentReport().getStatutory_philhealth()));
        governmental.add(new Data("Associate Benefit-Pag-ibig", master.getCurrentReport().getStatutory_pagibig()));
        governmental.add(new Data("Associate Benefit-EC", master.getCurrentReport().getStatutory_escola()));
        float sum = 0;
        for(int i = 0; i < governmental.size(); i++) {
            sum += governmental.get(i).getValue();
        }
        comp.setGovernmentalCost(sum);
        governmental.add(new Data("Sub-total", comp.getTotalGovernmentalCost()));

        comp.setTotalLaborCost(comp.getEquivalentMonthlyCost() + comp.getTotalGovernmentalCost());
        comp.setBizsolvAdminCost(comp.getAdminCost()*comp.getTotalLaborCost()/100);
        comp.setContractCost(comp.getTotalLaborCost() + comp.getBizsolvAdminCost());
    }

    private void init() {
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
