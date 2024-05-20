package org.shevliakov.collegeaccounting.appcore.util;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.control.TableView;
import javafx.scene.transform.Scale;
import javafx.stage.Window;

public class PrintTable {

  public void print(TableView<?> tableView) {
    // Create a printer job for the default printer
    PrinterJob job = PrinterJob.createPrinterJob();

    if (job != null) {
      // Get the default page layout
      PageLayout pageLayout = Printer.getDefaultPrinter()
          .createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, MarginType.DEFAULT);

      // Calculate the scale required to fit the table to the page
      double scaleX = pageLayout.getPrintableWidth() / tableView.getBoundsInParent().getWidth();
      double scaleY = pageLayout.getPrintableHeight() / tableView.getBoundsInParent().getHeight();
      Scale scale = new Scale(scaleX, scaleY);

      // Apply the scale to the table
      tableView.getTransforms().add(scale);
      // Print the table
      if (job.showPrintDialog(Window.getWindows().getFirst()) && job.printPage(pageLayout,
          tableView)) {
        job.endJob();
      } else {
        System.out.println("Printing failed.");
      }

      // Remove the scale transformation after printing
      tableView.getTransforms().remove(scale);
    }
  }

}
