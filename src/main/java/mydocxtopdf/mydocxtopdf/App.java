package mydocxtopdf.mydocxtopdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * 
 * @author KRISS07IN
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Hello World! Example to generate PDF's using XDocReport API");
        try {
        	InputStream in= new FileInputStream(new File("./XDocReportDOCXToPDF.docx"));
        	
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
			
			IContext context = report.createContext();
			context.put("name", "world");
			
			OutputStream out = new FileOutputStream(new File("./XDocReportDOCXToPDF_Out.docx"));
			report.process(context, out);
			
			Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);
			
			IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
			
			InputStream in1= new FileInputStream(new File("XDocReportDOCXToPDF_Out.docx"));
			OutputStream out1 = new FileOutputStream(new File("XDocReportDOCXToPDF.pdf"));
			converter.convert(in1, out1, options);	
			
			System.out.println("PDF generated!, Congratulatons!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDocReportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
