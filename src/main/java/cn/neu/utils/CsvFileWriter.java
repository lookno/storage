package cn.neu.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import cn.neu.bean.Goods;
import cn.neu.bean.OutputGoods;
import cn.neu.bean.OutputRecord;
import cn.neu.exception.ServiceException;

public class CsvFileWriter {

	// CSV文件分隔符
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV文件头
	public static final Object[] OUTPUT_RECORD_FILE_HEADER = { "记录类型", "创建记录时间", "更新记录时间", "备注", "库存商品名称", "价格" };
	public static final Object[] GOODS_FILE_HEADER = { "库存商品名称", "库存数量", "价格", "类型" };

	// 只允许数据库为空时导入数据 否则可能会引起数据混乱
	// 并且 此.csv数据格式必须是严格遵循导出数据的格式
	// 例如一个用户将数据导出 在另外一个电脑登录导入
	public static <T> List<T> readCsvFile(String[] header, String fileName) throws Exception {
		FileReader fileReader = new FileReader(fileName);
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(header);
		CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);
		List<CSVRecord> csvRecords = csvFileParser.getRecords();
		List list = new ArrayList<>();
		for (int i = 1; i < csvRecords.size(); i++) {
			CSVRecord cr = csvRecords.get(i);
			//T t = new T();
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static void writeCsvFile(List list, Object[] header, String fileName) throws Exception {
		FileWriter fileWriter = new FileWriter(fileName);
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
		csvFilePrinter.printRecord(header);
		if (list.size() > 0) {
			if (list.get(0).getClass() == OutputRecord.class) {
				for (Object r : list) {
					OutputRecord opr = (OutputRecord) r;
					List<String> rList = new ArrayList<>();
					rList.add(opr.getTypeName() + "");
					rList.add(opr.getCreate_time() + "");
					rList.add(opr.getUpdate_time() == null ? "无" : opr.getUpdate_time() + "");
					rList.add(opr.getComment() + "");
					rList.add(opr.getGoodsName() == null ? "无" : opr.getGoodsName() + "");
					rList.add(opr.getPrice() + "");

					csvFilePrinter.printRecord(rList);
				}
			} else if (list.get(0).getClass() == OutputGoods.class) {
				for (Object r : list) {
					OutputGoods opr = (OutputGoods) r;
					List<String> rList = new ArrayList<>();
					rList.add(opr.getName() + "");
					rList.add(opr.getCount() + "");
					rList.add(opr.getPrice() + "");
					rList.add(opr.getTypeName() + "");
					csvFilePrinter.printRecord(rList);
				}
			} else {
				throw new ServiceException("暂不支持此bean");
			}
		}
		csvFilePrinter.flush();
		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();
		System.out.println("CSV文件创建成功~~~");
	}

	public static void main(String[] args) throws Exception {
		List<Object> list = new ArrayList<>();

		OutputRecord record = new OutputRecord();
		list.add(record);
		System.out.println(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
		// writeCsvFile(list, FILE_HEADER, "c:\\test.csv");
	}
}