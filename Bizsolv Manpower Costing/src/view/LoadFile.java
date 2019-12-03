package view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Optional;

public class LoadFile implements View {

    @FXML
    private TextField address, adminCost, fileName, workingDays, incentiveLeave, name, locations;
    @FXML
    private ComboBox<String> rateType, allowance;
    @FXML
    private Button saveChanges, back;

    private Stage stage;
    private Master master;
    private Paragraph NEWLINE;
    private ObservableList<Data> basic, monthlyCost, governmental;
    private PdfPCell space;
    private PdfWriter writer;
    private Font regular, bold, small;
    private Computation comp;
    private ObservableList<String> allowanceList, rateTypeList;
    private float basicSalary, allowanceValue;
    private int incentiveValue, sssIndex;

    private final String MONTH_NAME[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};

    public LoadFile(Stage stage, Master master) {
        this.stage = stage;
        this.master = master;
        Scene scene = FXMLClass.getScene("/view/LoadFile.fxml", this)   ;
        this.stage.setScene(scene);
        this.stage.show();

        setup();
    }

    public void initialize() {
        saveChanges.setDefaultButton(true);
        saveChanges.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Load File");
            alert.setContentText("Are you sure you want to save the changes?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                checker();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });

        saveChanges.setOnMouseEntered(event -> {
            saveChanges.setStyle("-fx-background-color: #535353");
        });

        saveChanges.setOnMouseExited(event -> {
            saveChanges.setStyle("-fx-background-color: #ef5350");
        });

        back.setOnAction(event ->{
            Stage stage = (Stage) back.getScene().getWindow();
            LoadSelect loadSelect = new LoadSelect(stage, master);
        });

        back.setOnMouseEntered(event -> {
            back.setStyle("-fx-background-color: #535353");
        });

        back.setOnMouseExited(event -> {
            back.setStyle("-fx-background-color: #ef5350");
        });
    }

    private void checker() {
        boolean done = true;
        if(address.getText().trim().isEmpty()) {
            Toast.makeText(stage, "Address is empty", 2000, 1000, 1000, -250, 5, Color.RED);
            done = false;
        } else if(workingDays.getText().trim().isEmpty()) {
            Toast.makeText(stage, "Working days is empty", 2000, 1000, 1000, -250, 5, Color.RED);
            done = false;
        } else if(adminCost.getText().trim().isEmpty()) {
            Toast.makeText(stage, "Admin cost is empty", 2000, 1000, 1000, -250, 5, Color.RED);
            done = false;
        } else if(fileName.getText().trim().isEmpty()) {
            Toast.makeText(stage, "File name is empty", 2000, 1000, 1000, -250, 5, Color.RED);
            done = false;
        }

        if(allowance.getSelectionModel().getSelectedItem() == "None" || allowance.getSelectionModel().getSelectedItem() == null) {
            allowanceValue = 0;
        } else {
            allowanceValue = Float.parseFloat(allowance.getSelectionModel().getSelectedItem());
        }
        if(incentiveLeave.getText().isEmpty()) {
            incentiveValue = 0;
        } else {
            incentiveValue = Integer.parseInt(incentiveLeave.getText());
        }

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
            Stage stage = (Stage) saveChanges.getScene().getWindow();
            Success success = new Success(stage, master);
        }
    }

    public void generate() throws IOException, DocumentException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("files/logo.png").toURI());
        Image image = Image.getInstance(path.toAbsolutePath().toString());
        image.scaleToFit(100, 100);

        Document document = new Document();
        String destination = "E:\\" + fileName.getText().trim() + "-Edited.pdf";
        master.setFileDestination(destination);
        master.setFileName(fileName.getText() + ".pdf");
        writer = PdfWriter.getInstance(document, new FileOutputStream(destination));
        document.setPageSize(PageSize.LETTER);
        document.open();

        document.add(image);
        Paragraph para = new Paragraph();
        para.add(new Chunk(master.getCurrentEmployee().getName(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))); // add name
        para.add(Chunk.NEWLINE);
        para.add(new Chunk(address.getText(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))); // add address
        para.add(Chunk.NEWLINE);
        document.add(para);
        document.add(NEWLINE);
        setData(rateType.getSelectionModel().getSelectedItem(), master.getCurrentReport().getBasicRate(), Integer.parseInt(workingDays.getText()), incentiveValue, allowanceValue, Float.parseFloat(adminCost.getText()));
        PdfPTable table = createTable(rateType.getSelectionModel().getSelectedItem(), Integer.parseInt(workingDays.getText()));
        document.add(table);
        document.add(NEWLINE);
        document.add(NEWLINE);
        signatories(document);
        addFooter(writer, document);
        document.close();

        if(comp instanceof ComputationDaily) {
            DailyReport daily = new DailyReport();
            daily.setEmployeename(master.getCurrentEmployee().getName());
            daily.setBasicRate(comp.getBasicSalary());
            daily.setnWorkingDays(comp.getWorkingDays());
            daily.setequivalentMonthlyCost(comp.getEquivalentMonthlyCost());
            daily.seteffectiveMonthlyRate(comp.getEffectiveMonthlyRate());
            daily.setStatutory_sss(getSSS(comp.getEffectiveMonthlyRate()));
            daily.setStatutory_pagibig(getPagIbig());
            daily.setStatutory_philhealth(Float.parseFloat("" + 193.21));
            daily.setStatutory_escola(getBenefitEC());
            daily.setTotalStatutory(comp.getTotalGovernmentalCost());
            daily.setThirteenth_month(comp.getMonthBonus());
            daily.setIncentive(comp.getNumOfDayIncentive());
            daily.setTotal(comp.getTotalLaborCost());
            daily.setadmin_cost(comp.getAdminCost());
            daily.setcontractCost(comp.getContractCost());
            daily.setVersion(master.getVersion() + "-1");
            master.addDailyReport(daily);
        } else if(comp instanceof ComputationMonthly) {
            MonthlyReport monthly = new MonthlyReport();
            monthly.setEmployeename(master.getCurrentEmployee().getName());
            monthly.setBasicRate(comp.getBasicSalary());
            monthly.setnWorkingDays(comp.getWorkingDays());
            monthly.setequivalentMonthlyCost(comp.getEquivalentMonthlyCost());
            monthly.seteffectiveMonthlyRate(comp.getEffectiveMonthlyRate());
            monthly.setStatutory_sss(getSSS(comp.getEffectiveMonthlyRate()));
            monthly.setStatutory_pagibig(getPagIbig());
            monthly.setStatutory_philhealth(Float.parseFloat("" + 618.75));
            monthly.setStatutory_escola(getBenefitEC());
            monthly.setTotalStatutory(comp.getTotalGovernmentalCost());
            monthly.setThirteenth_month(comp.getMonthBonus());
            monthly.setIncentive(comp.getNumOfDayIncentive());
            monthly.setTotal(comp.getTotalLaborCost());
            monthly.setadmin_cost(comp.getAdminCost());
            monthly.setcontractCost(comp.getContractCost());
            monthly.setVersion(master.getVersion() + "-1");
            master.addMonthlyReport(monthly);
        }
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
            footer.addCell(new Phrase(String.format(master.getVersion() + "-1") , new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));

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
        governmental.add(new Data("Associate Benefit-SSS (Mand Payable)", getSSS(comp.getEffectiveMonthlyRate())));
        governmental.add(new Data("Associate Benefit-Philhealth", 193.21));
        governmental.add(new Data("Associate Benefit-Pag-ibig", getPagIbig()));
        governmental.add(new Data("Associate Benefit-EC", getBenefitEC()));
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
        setupComboBox();
    }

    private void setupComboBox() {
        allowanceList = FXCollections.observableArrayList("None");
        rateTypeList = FXCollections.observableArrayList("Daily", "Monthly");

        rateType.setItems(rateTypeList);
        allowance.setItems(allowanceList);
        initData();
    }

    private void initData() {
        name.setText(master.getCurrentEmployee().getName());
        address.setText(master.getCurrentEmployee().getAddress());
        locations.setText(master.getCurrentEmployee().getProvince());
        fileName.setText(master.getFileName());
        if(master.getCurrentReport() instanceof DailyReport) {
            rateType.getSelectionModel().select(0);
            workingDays.setText("" + (int)((DailyReport) master.getCurrentReport()).getnWorkingDays());
            adminCost.setText("" + (int)((DailyReport) master.getCurrentReport()).getadmin_cost());
            if(((DailyReport) master.getCurrentReport()).getIncentive() != 0.0) {
                incentiveLeave.setText("" + (int)((DailyReport) master.getCurrentReport()).getIncentive());
            }
            if(((DailyReport) master.getCurrentReport()).getAllowance() == 0) {
                allowance.getSelectionModel().select(0);
            } else {
                allowance.getSelectionModel().select(((DailyReport) master.getCurrentReport()).getAllowance() + "");
            }
        } else if(master.getCurrentReport() instanceof MonthlyReport) {
            rateType.getSelectionModel().select(1);
            workingDays.setText("" + (int)((MonthlyReport) master.getCurrentReport()).getnWorkingDays());
            adminCost.setText("" + (int)((MonthlyReport) master.getCurrentReport()).getadmin_cost());
            if(((MonthlyReport) master.getCurrentReport()).getIncentive() != 0.0) {
                incentiveLeave.setText("" + (int)((MonthlyReport) master.getCurrentReport()).getIncentive());
            }
            if(((MonthlyReport) master.getCurrentReport()).getAllowance() == 0) {
                allowance.getSelectionModel().select(0);
            } else {
                allowance.getSelectionModel().select(((MonthlyReport) master.getCurrentReport()).getAllowance() + "");
            }
        }
    }

    public float getSSS(float value) {
        for(int i = 0; i < master.getSSS().size(); i++) {
            if(value >= master.getSSS().get(i).getMinRange() && value <= master.getSSS().get(i).getMaxRange()) {
                sssIndex = i;
                return master.getSSS().get(i).getER();
            }
        }
        return 0;
    }

    public float getBenefitEC() {
        return master.getSSS().get(sssIndex).getEC();
    }

    public float getPagIbig() {
        if(comp.getEffectiveMonthlyRate() * 2/100.0 > 100.0) {
            return 100;
        }
        return comp.getEffectiveMonthlyRate() * 2/100;
    }
}
