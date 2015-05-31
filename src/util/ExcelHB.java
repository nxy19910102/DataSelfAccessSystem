package util;
//package applicationEntity;
//
//import java.text.SimpleDateFormat;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFHeader;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.Region;
////<jsp:directive.page import="com.yuantiao.ipub.ytcms.service.ArticleService"/>  
////<jsp:directive.page import="com.yuantiao.ipub.ytcms.stat.bean.ArticleInfo"/>  
////<jsp:directive.page import="org.apache.poi.hssf.usermodel.*"/>  
////<jsp:directive.page import="org.apache.poi.hssf.util.HSSFColor"/>  
////<jsp:directive.page import="org.apache.poi.hssf.util.Region"/>  
////<jsp:directive.page import="java.text.SimpleDateFormat"/>  
//public class ExcelHB {
//	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期用格式化工具  
//	  
//	int index = 0;//行标记,记录创建到第几行了  
//	  
//	HSSFWorkbook wb = new HSSFWorkbook();   
//	HSSFSheet s = wb.createSheet(); 
////	s.setSheetName(0, "MySheet");  
//	
//	HSSFHeader header = s.getHeader();  
//	  
//	HSSFRow trow=null;  
//	HSSFCell tcell=null;  
//	HSSFCell cell = null;  
//	HSSFFont font = wb.createFont();  
//	HSSFFont font2 = wb.createFont();  
//	HSSFCellStyle cellStyle = wb.createCellStyle();  
////	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗                                  
//	font.setFontHeight((short) 380);        // 设置字体大小  
//	font.setFontName("宋体");      // 设置单元格字体     
//	HSSFCellStyle cellStyle2 = wb.createCellStyle();  
//	font2.setColor(HSSFFont.COLOR_RED); // 设置单元格字体的颜色.  
//	  
//	  
//	HSSFCellStyle cellStyle1_2 = wb.createCellStyle();  
//	  
//	cellStyle1_2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
//	 
//	   
//	//创建第一行和第二行的文字信息  
//	/**********************标题第一行***************************/  
//	//创建第一行的标题  
//	int cellIndex = 0;//CELL的位置标记  
//	  
//	cellIndex = 0;//CELL的位置标记  
//	trow=s.createRow(index++);//创建行  
//	trow.setHeight((short)400);  
//	tcell=trow.createCell((short)cellIndex++);  
//	tcell.setCellStyle(cellStyle1_2);  
//	setGB2312String(tcell,"序号");  
//	  
//	
//	  
//	tcell=trow.createCell((short)cellIndex++);  
//	tcell.setCellStyle(cellStyle1_2);  
//	setGB2312String(tcell,"分值合计1");  
//	  
//	   
//	              
//	String pName = "";  
//	String equalStr = "";//主判断  
//	int totalScore = 0,loopQuantity = 0;  
//	boolean flag = false;//判断标记  
//	ArticleInfo tempArticleInfo = null;  
//	  
//	for(int i=0;i<list.size();i++)  
//	{  
//	    articleInfo = (ArticleInfo)list.get(i);   
//	      
//	      
//	    //如果对比变量为空时进行初始化  
//	    if("".equals(equalStr)){  
//	    //如果类型为1时按作者进行统计和判断  
//	        if(statetype == 1){  
//	                equalStr = articleInfo.getAuthoer();  
//	        }else{  
//	            equalStr = articleInfo.getQuote();  
//	        }  
//	    }  
//	      
//	    //开始循环生成EXCEL行数据  
//	    cellIndex = 0;//初始化CELL下标  
//	    trow=s.createRow(index++);//创建行  
//	    trow.setHeight((short)400);  
//	    tcell=trow.createCell((short)cellIndex++);  
//	    tcell.setCellStyle(cellStyle1_3);  
//	    setGB2312String(tcell,""+(i+1));  
//	      
//	    tcell=trow.createCell((short)cellIndex++);  
//	    tcell.setCellStyle(cellStyle1_3);  
//	    setGB2312String(tcell,statetype == 1? articleInfo.getAuthoer() : articleInfo.getQuote());//判断是显示作者信息还是出处信息  
//	      
//	    
//	      
//	    tcell=trow.createCell((short)cellIndex++);  
//	    tcell.setCellStyle(cellStyle1_3);  
//	    setGB2312String(tcell,"");  
//	      
//	          
//	          
//	        tempArticleInfo = null; //每次循环进行初始化，以便知道什么时候到达最后条记录  
//	        flag = false;  
//	        if(i+1 != list.size()){  
//	                tempArticleInfo = (ArticleInfo)list.get(i+1);     
//	        }  
//	          
//	          
//	        //进行统一判断，方便后面使用，简化代码  
//	        if(tempArticleInfo != null){  
//	            if(statetype == 1){  
//	                    flag = equalStr.equals(tempArticleInfo.getAuthoer());  
//	            }else{  
//	                    flag = equalStr.equals(tempArticleInfo.getQuote());  
//	            }  
//	        }  
//	  
//	        //下一条记录不等于当前的记录对比值时进行相应的处理  
//	        if(!flag || tempArticleInfo == null){  
//	                totalScore += articleInfo.getScore();  
//	            loopQuantity ++;  
//	                  
//	                //进行跨列处理  
//	                //#########注意：跨行操作时Region(1,2,3,4)第1个值的行号必须要比3位置的行号小，如果大于3就不能正常合并单元格  
//	                 s.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));  
//	                      
//	                    //获取最先需要跨的CELL，然后把值加入  
//	                 tcell = s.getRow(index-loopQuantity).getCell((short)(cellIndex-1));  
//	                    setGB2312String(tcell,""+totalScore);  
//	                      
//	                  
//	                //清空统计数据  
//	                totalScore = 0;  
//	                loopQuantity = 0;  
//	        }else{  
//	            totalScore += articleInfo.getScore();  
//	            loopQuantity ++;  
//	        }  
//	      
//	  
//	      
//	      
//	}  
//	  
//	    //s.addMergedRegion(new Region(1,(short)(0),0,(short)(0)));  
//	  
//	    pName="栏目统计表";  
//	    response.reset();  
//	    response.setContentType("application/x-msdownload");  
//	    response.setHeader("Content-Disposition","attachment; filename="+new String(pName.getBytes("gb2312"),"ISO-8859-1")+".xls");  
//	    ServletOutputStream outStream=null;  
//	  
//	    try{  
//	        outStream = response.getOutputStream();  
//	        wb.write(outStream);  
//	    }catch(Exception e)  
//	    {  
//	     e.printStackTrace();  
//	    }finally{  
//	        outStream.close();  
//	    }  
//}
