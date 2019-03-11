package com.hjkj.business.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class PrintUtil {

	/*** 读取word模板并替换变�?
     * @param templatePath 模板路径
     * @param contentMap 要替换的内容
     * @return word的Document
     */
    public static HWPFDocument replaceDoc(String templatePath, Map<String, String> contentMap) {
        try {
            // 读取模板
        	FileInputStream tempFileInputStream = new FileInputStream(new File(templatePath));
        	HWPFDocument document = new HWPFDocument(tempFileInputStream);
            // 读取文本内容
            Range bodyRange = document.getRange();
            // 替换内容
            for (Map.Entry<String, String> entry : contentMap.entrySet()) {
                bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
            }
            return document;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    
	
	
	public static void printContent(String path) throws FileNotFoundException
    {
		File file = new File(path);
		
		FileInputStream psStream = new FileInputStream(file);
        
		int PAGES = 1; // 获取打印总页�?
        // 指定打印输出格式
        DocFlavor flavor = DocFlavor.INPUT_STREAM.GIF;

        // 设置打印属�??
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置纸张大小,也可以新建MediaSize类来自定义大�?
        pras.add(MediaSizeName.ISO_A4);
        DocAttributeSet das = new HashDocAttributeSet();
        // 指定打印内容
        Doc doc = new SimpleDoc(psStream, flavor, das);

        // 定位默认的打印服�?
        PrintService printService = null;
        //printService = PrintServiceLookup.lookupPrintServices(flavor, pras)[0];
        
        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, pras);
        for (int i = 0; i < services.length; i++) {
			System.out.println("service found: " + services[i]);
			String svcName = services[i].toString();
			if (svcName.contains("USER-20151113OF")) {
				printService = services[i];
				System.out.println("my printer found: " + svcName);
				System.out.println("my printer found: " + printService);
				break;
			}
		}
        
        System.out.println(printService);
        // 创建打印作业
        DocPrintJob job = printService.createPrintJob();
        try
        {
            job.print(doc, pras); // 进行每一页的具体打印操作

        }
        catch (PrintException pe)
        {
            pe.printStackTrace();
        }
    }
	
	
	
	public static void printWord(String filePath) {
        ComThread.InitSTA();
        ActiveXComponent wd = new ActiveXComponent("Word.Application");
        try {
            // 不打�?文档
            Dispatch.put(wd, "Visible", new Variant(true));
            Dispatch document = wd.getProperty("Documents").toDispatch();
            // 打开文档
            Dispatch doc = Dispatch.invoke(document, "Open", Dispatch.Method,
                    new Object[] { filePath }, new int[1]).toDispatch();
            // �?始打�?
            Dispatch.callN(doc, "PrintOut");
            wd.invoke("Quit", new Variant[] {});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 始终释放资源
            ComThread.Release();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
