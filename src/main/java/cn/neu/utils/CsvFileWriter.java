package cn.neu.utils;

import java.io.FileNotFoundException;
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
import org.springframework.stereotype.Component;
import cn.neu.bean.Goods;
import cn.neu.bean.OutputGoods;
import cn.neu.bean.OutputRecord;
import cn.neu.bean.Record;
import cn.neu.dao.GoodsDao;
import cn.neu.dto.GoodsDto;
import cn.neu.exception.ServiceException;

@Component
public class CsvFileWriter {
	// CSV文件分隔符
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV文件头
	public static final Object[] OUTPUT_RECORD_FILE_HEADER = { "记录类型", "创建记录时间", "更新记录时间", "备注", "库存商品名称", "价格" };
	public static final Object[] GOODS_FILE_HEADER = { "商品编号", "库存商品名称", "库存数量", "价格", "类型" };
	public static final String[] INPUT_RECORD_FILE_HEADER = { "记录类型", "创建记录时间", "更新记录时间", "备注", "库存商品名称", "价格" };
	public static final String[] INPUT_GOODS_FILE_HEADER = { "商品编号", "库存商品名称", "库存数量", "价格", "类型" };

	public static List<Record> readRecords(String fileName,GoodsDao goodsDao) throws Exception {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		List<Record> list = new ArrayList<>();
		try {
			fileReader = new FileReader(fileName);
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(INPUT_RECORD_FILE_HEADER);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord cr = csvRecords.get(i);
				Record record = new Record();
				String typeName = cr.get("记录类型");
				if (typeName.equals("与商品出入库无关记录")) {
					record.setType(0);
				} else if (typeName.equals("销售出库记录")) {
					record.setType(1);
				} else if (typeName.equals("购入花销记录")) {
					record.setType(2);
				} else if (typeName.equals("生产入库记录")) {
					record.setType(3);
				}
				String ct = cr.get("创建记录时间");
				record.setCreate_time(ct.equals("无")?"0000-00-00 00:00:00":ct);
				String et = cr.get("更新记录时间");
				record.setUpdate_time(et.equals("无")?"0000-00-00 00:00:00":et);
				record.setComment(cr.get("备注"));
				String goodsName = cr.get("库存商品名称");
				GoodsDto goodsDto = new GoodsDto();
				goodsDto.setName(goodsName);
				List<Goods> lgs = goodsDao.listGoods(goodsDto);
				if (lgs != null && lgs.size() > 0) {
					record.setGoods_id(lgs.get(0).getId());
				}

				record.setPrice(Double.valueOf(cr.get("价格")));

				list.add(record);
			}
		} catch (FileNotFoundException e1) {
			throw new ServiceException(".csv文件不存在", e1);
		} catch (Exception e2) {
			throw new ServiceException("文件格式错误", e2);
		} finally {
			fileReader.close();
			csvFileParser.close();
		}
		return list;
	}

	// 批量插入goods和records 互相不涉及 只是单纯的导入数据
	// 只允许数据库为空时导入数据 否则可能会引起数据混乱
	// 并且 此.csv数据格式必须是严格遵循导出数据的格式
	// 例如一个用户将数据导出 在另外一个电脑登录导入
	public static List<Goods> readGoods(String fileName) throws Exception {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		List<Goods> list = new ArrayList<>();
		try {
			fileReader = new FileReader(fileName);
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(INPUT_GOODS_FILE_HEADER);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord cr = csvRecords.get(i);
				Goods goods = new Goods();
				goods.setId(Integer.valueOf(cr.get("商品编号")));
				goods.setName(cr.get("库存商品名称"));
				goods.setCount(Integer.valueOf(cr.get("库存数量")));
				goods.setPrice(Double.valueOf(cr.get("价格")));
				String type = cr.get("类型");
				goods.setType(type.contains("生产") ? 1 : type.contains("购") ? 2 : 0);
				list.add(goods);
			}
		} catch (FileNotFoundException e1) {
			throw new ServiceException(".csv文件不存在", e1);
		} catch (Exception e2) {
			throw new ServiceException("文件格式错误", e2);
		} finally {
			fileReader.close();
			csvFileParser.close();
		}
		return list;
	}

	@SuppressWarnings({ "resource", "rawtypes" })
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
					rList.add(opr.getId() + "");
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