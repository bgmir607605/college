/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import jxl.CellReferenceHelper;
import jxl.CellView;
import jxl.HeaderFooter;
import jxl.Range;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Orientation;
import jxl.format.PageOrder;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Blank;
import jxl.write.DateFormat;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Andrey
 */
public class Write {
  private String filename;
  private WritableWorkbook workbook;

  public Write(String fn)
  {
    filename = fn;
  }
  
  public void write() throws IOException, WriteException
  {
    WorkbookSettings ws = new WorkbookSettings();
    ws.setLocale(new Locale("en", "EN"));
    workbook = Workbook.createWorkbook(new File(filename), ws);

    WritableSheet s2 = workbook.createSheet("Number Formats", 0);
    Label l = new Label(1, 1, "hello");
    s2.addCell(l);
    workbook.write();
    workbook.close();
  }
  
  
    
}
