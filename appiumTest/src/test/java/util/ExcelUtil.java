/**
 * Company: Alo7
 * FileName: ExcelUtil
 * Author: wanghuanhuan
 * CreateTime: 2019-06-16  14:50
 */
package util;

import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class ExcelUtil {

    public static Object[][] readExcel(String excelPath,int index) {
        //创建一个数组，接收解析出来的数据
        Object[][] datas=null;

        try {
            InputStream inputStream=ExcelUtil.class.getResourceAsStream(excelPath);
            //一个excel对应的就是一个工作簿
            Workbook workbook= WorkbookFactory.create(inputStream);
//			Workbook workbook2=WorkbookFactory.create(new File("/testcases/register.xlsx"));
            //读取某个sheet
            Sheet sheet=workbook.getSheetAt(index-1);
            int lastRowNum=sheet.getLastRowNum();//获取最后一行的行号（从0开始）
            datas=new Object[lastRowNum][];    //-------去掉表头需要改动的行1

            for(int i=1;i<=lastRowNum;i++) {   //-------去掉表头需要改动的行2
                //遍历所有的行，读取每行的数据
                Row row=sheet.getRow(i);

                int lastCellNum=row.getLastCellNum();
                Object[] cellValues=new Object[lastCellNum];

                for (int j = 0; j < lastCellNum; j++) {
                    //得到循环的列
                    Cell cell=row.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); //缺省的策略
                    cell.setCellType(CellType.STRING);
                    //获得cell的值
                    String cellValue=cell.getStringCellValue();
                    cellValues[j]=cellValue;
                }
                datas[i-1]=cellValues;   //--------去掉表头需要改动的行3
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 调试
     * 所有自动化的测试用例：
     * 1. 第一行规定要写表头
     * 2. 除了相关行，不要随意的修改其他行/列
     * @param
     */

    public static Object[][] readExcel(String excelPath,int index,int startRow,int endRow,int startCell,int endCell) {
        //创建一个数组，接收解析出来的数据
        Object[][] datas=new Object[endRow-startRow+1][endCell-startCell+1];

        try {
            InputStream inputStream=ExcelUtil.class.getResourceAsStream(excelPath);
            //一个excel对应的就是一个工作簿
            Workbook workbook=WorkbookFactory.create(inputStream);
//			Workbook workbook2=WorkbookFactory.create(new File("/testcases/register.xlsx"));
            //读取某个sheet
            Sheet sheet=workbook.getSheetAt(index-1);
            for(int i=startRow;i<=endRow;i++) {
                //遍历所有的行，读取每行的数据
                Row row=sheet.getRow(i-1);
                for (int j = startCell; j <= endCell; j++) {
                    //得到循环的列
                    Cell cell=row.getCell(j-1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL); //缺省的策略
                    cell.setCellType(CellType.STRING);
                    //获得cell的值
                    String cellValue=cell.getStringCellValue();
                    datas[i-startRow][j-startCell]=cellValue;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;

    }

    public static void main(String[] args) {
        Object[][] datas=readExcel("/testcases/ApiTestData.xlsx", 2,2,7,1,4);
        for (Object[] objects : datas) {
            for (Object objects2 : objects) {
                System.out.print(objects2+",");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param filePath	读取的文件
     * @param sheetNum	sheet的索引号
     * @param caseID	case的唯一标识
     * @param cellNum	要写回的列号
     * @param result	接口返回的结果
     */
    @Deprecated
    public static void writeData(String filePath, int sheetNum, String caseID, int cellNum, String result) {
        InputStream inputStream=null;
        Workbook workbook=null;
        OutputStream outputStream=null;
        try {
            //InputStream inputStream=ExcelUtil.class.getResourceAsStream(filePath);
            inputStream=new FileInputStream(new File(filePath));
            workbook=WorkbookFactory.create(inputStream);
            Sheet sheet=workbook.getSheetAt(sheetNum-1);
            //怎么通过caseId得到行号？
            //:遍历所有行，获得每一行第一列的值，与caseId对比，相等的话即为要写回的目标行
            int lastRowNum=sheet.getLastRowNum();
            //遍历所有行
            for(int i=1;i<=lastRowNum;i++) {
                //得到当前遍历的行
                Row row=sheet.getRow(i);
                //获得第一列
                Cell firstCell=row.getCell(0,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                firstCell.setCellType(CellType.STRING);
                //获得第一列的值
                String cellValue=firstCell.getStringCellValue();
                if (caseID.equals(cellValue)) {
                    Cell cellToBeWrite=row.getCell(cellNum-1,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cellToBeWrite.setCellType(CellType.STRING);
                    cellToBeWrite.setCellValue(result);
                    break;	//已经匹配成功，结束循环。
                }

            }
            //输出流+写回到文件
            outputStream=new FileOutputStream(new File(filePath));
            workbook.write(outputStream);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (workbook !=null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }



        }
    }