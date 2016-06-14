/*package cn.neu.dto;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cn.neu.global.Container;
import cn.neu.http.Http;
import cn.neu.recv.ChgPwdDto;
import cn.neu.recv.GType;
import cn.neu.recv.Goods;
import cn.neu.recv.GoodsVo;
import cn.neu.recv.ProfitVo;
import cn.neu.recv.Record;
import cn.neu.recv.RecordVo;
import cn.neu.recv.User;
import cn.neu.util.CipherUtil;
import cn.neu.util.MapToGoods;
import cn.neu.util.MapToRecords;
import cn.neu.vo.JXGoodsVo;
import cn.neu.vo.JXRecordsVo;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DataController {
	@FXML
	private TitledPane goodsManage;
	@FXML
	private TitledPane recordManage;
	@FXML
	private TitledPane dataManage;
	@FXML
	private TitledPane userManage;
	@FXML
	private Button listGoodsButton;
	@FXML
	private Button addGoodsButton;
	@FXML
	private Button listRecordsButton;
	@FXML
	private Button addRecordsButton;
	@FXML
	private Button listProfitsButton;
	@FXML
	private Button inputDataButton;
	@FXML
	private Button outputDataButton;
	@FXML
	private Button changePassButton;
	@FXML
	private Button registerUserButton;
	@FXML
	private Button logoutButton;
	@FXML
	private TableView<JXGoodsVo> goodsTable;
	@FXML
	private TableView<JXRecordsVo> recordsTable;
	@FXML
	private Pane listView;
	@FXML
	private Pane addView;
	@FXML
	private Pane addGoods;
	@FXML
	private Button ConfirmAddGoodsButton;
	@FXML
	private Button CancelAddGoodsButton;
	@FXML
	private TextField addGoodsName;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox addGoodsType;
	@FXML
	private TextField addGoodsCount;
	@FXML
	private TextField addGoodsPrice;
	@FXML
	private Pane addRecords;
	@FXML
	private TextField addRecordsComment;
	@FXML
	private TextField addRecordsPrice;
	@FXML
	private Button ConfirmAddRecordsButton;
	@FXML
	private Button CancelAddRecordsButton;
	@FXML
	private Pagination pagination;
	@FXML
	private Button inoutButton;
	@FXML
	private Button changePriceButton;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox goodsChoice;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox recordsChoice;
	@FXML
	private Pane profitView;
	@FXML
	private DatePicker startTime;
	@FXML
	private DatePicker endTime;
	@FXML
	private TextField inCome;
	@FXML
	private TextField outCome;
	@FXML
	private TextField in_outCome;
	@FXML
	private Pane exceldataView;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox outputChoiceBox1;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox outputChoiceBox2;
	@FXML
	private TextField outputFileName;
	@FXML
	private Text filePosition;
	@FXML
	private Text fileTip;
	@FXML
	private Pane outDataView;
	@FXML
	private Pane inDataView;
	@FXML
	private Text inDataTip;

	@FXML
	private Pane userManageView;
	@FXML

	private Pane changePassPane;
	@FXML
	private PasswordField oldPass;
	@FXML
	private PasswordField newPass;
	@FXML
	private PasswordField newPass2;
	@FXML
	private Text changePassTip;
	@FXML
	private Text oldPassTip;
	@FXML
	private Text newPassTip;
	@FXML
	private Text newPassTip2;

	@FXML
	private Pane registerPane;
	@FXML
	private TextField rUserName;
	@FXML
	private PasswordField rPassword;
	@FXML
	private TextField rEmail;
	@FXML
	private TextField rPhone;
	@FXML
	private Text rUserNameTip;
	@FXML
	private Text rPasswordTip;
	@FXML
	private Text rEmailTip;
	@FXML
	private Text rPhoneTip;
	@FXML
	private Text rRegTip;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox rUserType;
	@FXML
	private Text rTypeTip;
	@FXML
	private TextField goodsKey;
	@FXML
	private TextField recordKey;
	@FXML
	private Pane typeView;
	@FXML
	private Pane goodsTypePane;
	@FXML
	private Button goodsTypeButton;
	@SuppressWarnings("rawtypes")
	@FXML
	private ListView goodsTypeListView;
	@FXML
	private TextField goodsTypeField;

	@SuppressWarnings("unused")
	@FXML
	void addGoodsTypeOnMouseClicked() throws Exception {
		String t = goodsTypeField.getText();
		if (StringUtils.isBlank(t)) {
			return;
		}
		GType g = new GType();
		g.setG_name(t);
		String responseBody = Http.postConnect("http://localhost:8080/storage/type/g_add", Container.token,
				new Gson().toJson(g));
		if (Http.CODE == 200) {
			goodsTypeButtonOnMouseClicked(null);
		}

	}

	@SuppressWarnings("unchecked")
	@FXML
	void goodsTypeButtonOnMouseClicked(Event e) throws Exception {
		Container.oldTypeVal = null;
		Container.newTypeVal = null;
		Container.gType = null;
		typeView.setVisible(true);
		goodsTypeField.clear();
		goodsTypeListView.setCellFactory(TextFieldListCell.forListView());
		goodsTypeListView.setOnEditStart(new EventHandler<EditEvent<String>>() {
			@Override
			public void handle(EditEvent<String> e) {
				Container.oldTypeVal = goodsTypeListView.getItems().get(e.getIndex()).toString();

			}
		});
		goodsTypeListView.setOnEditCommit(new EventHandler<EditEvent<String>>() {
			@Override
			public void handle(EditEvent<String> e) {
				System.out.println(1);
				if (StringUtils.isBlank(e.getNewValue())) {
					try {
						System.out.println(2);
						goodsTypeButtonOnMouseClicked(null);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					return;
				}

				if (e.getNewValue().equals(Container.oldTypeVal)) {
					System.out.println(3);
					return;
				}
				GType g = null;
				for (GType gt : Container.gType) {
					if (gt.getG_name().equals(Container.oldTypeVal)) {
						g = gt;
						g.setG_name(e.getNewValue());
						break;
					}
				}
				System.out.println(g.toString());
				// TODO
				try {
					Http.putConnect("http://localhost:8080/storage/type/g_edit", Container.token, new Gson().toJson(g));
					if (Http.CODE == 200) {
						goodsTypeButtonOnMouseClicked(null);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		goodsTypePane.setVisible(true);
		String responseBody = null;
		responseBody = Http.getConnect("http://localhost:8080/storage/type/g_list", Container.token);
		if (Http.CODE == 200) {

			List<GType> gv = new Gson().fromJson(responseBody, new TypeToken<List<GType>>() {
			}.getType());
			Container.gType = gv;
			List<String> gs = new ArrayList<>();
			for (GType g : gv) {
				gs.add(g.getG_name());
			}
			ObservableList<String> items = FXCollections.observableArrayList(gs);

			goodsTypeListView.setItems(items);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void goodsKeyOnKeyReleased(Event e) throws Exception {
		if (goodsKey.getText() == null) {
			return;
		}
		Container.choiceKey = goodsKey.getText();
		String responseBody = null;
		try {
			if(Container.choiceType==0){
				if (goodsKey.getText() != null) {
					responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?key=" + goodsKey.getText(),
							Container.token);
				} else {
					responseBody = Http.getConnect("http://localhost:8080/storage/goods/list", Container.token);
				}
			}else{
				if (goodsKey.getText() != null) {
					responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?type="
							+ (Container.choiceType) + "&key=" + goodsKey.getText(), Container.token);
				} else {
					responseBody = Http.getConnect(
							"http://localhost:8080/storage/goods/list?type=" + Container.choiceType, Container.token);
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (Http.CODE == 200) {
			// 成功
			GoodsVo gv = new Gson().fromJson(responseBody, GoodsVo.class);

			pagination.setPageCount(gv.getCount() % 16 == 0 ? gv.getCount() / 16 : gv.getCount() / 16 + 1);
			((TableColumn) goodsTable.getColumns().get(0))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("id"));
			((TableColumn) goodsTable.getColumns().get(1))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("name"));
			((TableColumn) goodsTable.getColumns().get(2))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("price"));
			((TableColumn) goodsTable.getColumns().get(3))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("count"));
			((TableColumn) goodsTable.getColumns().get(4))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("type"));
			goodsTable.setItems(FXCollections.observableArrayList(MapToGoods.to(gv.getGoods())));
		} else {
			// 失败
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void recordKeyOnKeyReleased(Event e) throws Exception {
		if (recordKey.getText() == null) {
			return;
		}
		Container.choiceKey = recordKey.getText();
		String responseBody = null;
		try {
			if (Container.choiceType == 1 || Container.choiceType == 2 || Container.choiceType == 3
					|| Container.choiceType == 4) {
				if (Container.choiceKey != null) {
					responseBody = Http.getConnect("http://localhost:8080/storage/record/list?type="
							+ Container.choiceType + "&key=" + Container.choiceKey, Container.token);
				} else {
					responseBody = Http.getConnect(
							"http://localhost:8080/storage/record/list?type=" + Container.choiceType, Container.token);
				}

			} else {
				if (Container.choiceKey != null) {
					responseBody = Http.getConnect(
							"http://localhost:8080/storage/record/list" + "?key=" + Container.choiceKey,
							Container.token);
				} else {
					responseBody = Http.getConnect("http://localhost:8080/storage/record/list", Container.token);
				}

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (Http.CODE == 200) {
			// 成功
			RecordVo rv = new Gson().fromJson(responseBody, RecordVo.class);
			pagination.setPageCount(rv.getCount() % 16 == 0 ? rv.getCount() / 16 : rv.getCount() / 16 + 1);
			((TableColumn) recordsTable.getColumns().get(0))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("id"));
			((TableColumn) recordsTable.getColumns().get(1))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("type"));
			((TableColumn) recordsTable.getColumns().get(2))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("comment"));
			((TableColumn) recordsTable.getColumns().get(3))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("price"));
			((TableColumn) recordsTable.getColumns().get(4))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("create_time"));
			((TableColumn) recordsTable.getColumns().get(5))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("goods_name"));
			recordsTable.setItems(FXCollections.observableArrayList(MapToRecords.to(rv.getRecords())));
		} else {
			// 失败
		}
	}

	@FXML
	void goodsManageOnMouseClicked() {

		if (Container.permission == 1) {
			// do nothing
		} else if (Container.permission == 2) {

		} else {
			addGoodsButton.setVisible(false);
			inoutButton.setVisible(false);
			changePriceButton.setVisible(false);
		}

	}

	@FXML
	void recordManageOnMouseClicked() {
		if (Container.permission == 1) {
			// do nothing
		} else if (Container.permission == 2) {

		} else {
			addRecordsButton.setVisible(false);
		}
	}

	@FXML
	void dataManageOnMouseClicked() {
		if (Container.permission == 1) {
			// do nothing
		} else if (Container.permission == 2) {

		} else {
			outputDataButton.setVisible(false);
		}
	}

	@FXML
	void userManageOnMouseClicked() {
		if (Container.permission == 1) {
			// do nothing
		} else if (Container.permission == 2) {
			registerUserButton.setVisible(false);
		} else {
			registerUserButton.setVisible(false);
		}
	}

	@SuppressWarnings("unused")
	@FXML
	void chooseRecordDataFileButtonOnMouseClicked() throws Exception {
		inDataTip.setVisible(false);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择.csv数据文件");
		File position = fileChooser.showOpenDialog(Container.stage);
		if (position != null) {
			String originalDir = position.toString();
			String dir = position.toString().replaceAll("\\\\", "%5C");
			if (!dir.endsWith(".csv")) {
				inDataTip.setText("请选择.csv格式的文件");
				inDataTip.fillProperty().set(Paint.valueOf("#ff6666"));
				inDataTip.setVisible(true);
			}
			String responseBody = null;
			responseBody = Http.getConnect("http://localhost:8080/storage/record/input?fileAddr=" + dir,
					Container.token);

			if (Http.CODE == 200) {
				inDataTip.setVisible(true);
				inDataTip.setText("账务记录导入成功");
				inDataTip.fillProperty().set(Paint.valueOf("#000000"));
			} else {
				inDataTip.setVisible(true);
				inDataTip.setText("账务记录导入失败");
				inDataTip.fillProperty().set(Paint.valueOf("#ff6666"));
			}
		}
	}

	// copy from other method not modify
	@SuppressWarnings("unused")
	@FXML
	void chooseGoodsDataFileButtonOnMouseClicked() throws Exception {
		inDataTip.setVisible(false);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择.csv数据文件");
		File position = fileChooser.showOpenDialog(Container.stage);
		if (position != null) {
			String originalDir = position.toString();
			String dir = position.toString().replaceAll("\\\\", "%5C");
			if (!dir.endsWith(".csv")) {
				inDataTip.setText("请选择.csv格式的文件");
				inDataTip.fillProperty().set(Paint.valueOf("#ff6666"));
				inDataTip.setVisible(true);
			}
			String responseBody = null;
			responseBody = Http.getConnect("http://localhost:8080/storage/goods/input?fileAddr=" + dir,
					Container.token);

			if (Http.CODE == 200) {
				inDataTip.setVisible(true);
				inDataTip.setText("库存数据导入成功");
				inDataTip.fillProperty().set(Paint.valueOf("#000000"));
			} else {
				inDataTip.setVisible(true);
				inDataTip.setText("库存数据导入失败");
				inDataTip.fillProperty().set(Paint.valueOf("#ff6666"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void confirmRegisterButtonOnMouseClicked() throws Exception {
		rUserNameTip.setVisible(false);
		rPasswordTip.setVisible(false);
		rEmailTip.setVisible(false);
		rPhoneTip.setVisible(false);
		rRegTip.setVisible(false);
		rTypeTip.setVisible(false);
		String rname = rUserName.getText();
		String rpass = CipherUtil.generatePassword(rPassword.getText());
		String remail = rEmail.getText();
		String rphone = rPhone.getText();
		User user = new User();
		user.setUsername(rname);
		user.setPassword(rpass);
		user.setEmail(remail);
		user.setPhone(rphone);
		user.setPermission(Container.userType + 2);
		String responseBody = null;
		responseBody = Http.postConnect("http://localhost:8080/storage/user/register", Container.token,
				new Gson().toJson(user));

		if (Http.CODE == 200) {
			rRegTip.setText("注册成功");
			rRegTip.setVisible(true);
		} else if (Http.CODE == 400) {
			Map<String, Object> map = new Gson().fromJson(responseBody, Map.class);
			String pos = (String) map.get("pos");
			if (pos.equals("1")) {
				rUserNameTip.setText((String) map.get("msg"));
				rUserNameTip.setVisible(true);
			} else if (pos.equals("2")) {
				rPasswordTip.setText((String) map.get("msg"));
				rPasswordTip.setVisible(true);
			} else if (pos.equals("3")) {
				rTypeTip.setText((String) map.get("msg"));
				rTypeTip.setVisible(true);
			} else if (pos.equals("4")) {
				rEmailTip.setText((String) map.get("msg"));
				rEmailTip.setVisible(true);
			} else if (pos.equals("5")) {
				rPhoneTip.setText((String) map.get("msg"));
				rPhoneTip.setVisible(true);
			}
		}
	}

	@FXML
	void cancelRegisterButtonOnMouseClicked() {
		userManageView.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	@FXML
	void registerButtonOnMouseClicked() {
		exceldataView.setVisible(false);
		addView.setVisible(false);
		listView.setVisible(false);
		profitView.setVisible(false);
		typeView.setVisible(false);
		userManageView.setVisible(true);
		changePassPane.setVisible(false);
		registerPane.setVisible(true);
		rUserNameTip.setVisible(false);
		rPasswordTip.setVisible(false);
		rTypeTip.setVisible(false);
		rEmailTip.setVisible(false);
		rPhoneTip.setVisible(false);
		rRegTip.setVisible(false);
		rUserName.clear();
		rPassword.clear();
		rEmail.clear();
		rPhone.clear();

		rUserType.setItems(FXCollections.observableArrayList("管理员(可读可写)", "普通用户(只读)"));
		rUserType.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov1, Number oldVal, Number newVal) -> {
					System.out.println(newVal.intValue());
					Container.userType = newVal.intValue();
				});
	}

	*//*************
	 * change pass
	 *
	 * @throws Exception
	 ***********************//*
	@SuppressWarnings("unchecked")
	@FXML
	void confirmChangePassButtonOnMouseClicked() throws Exception {
		String oldPas = oldPass.getText();
		String newPas = newPass.getText();
		String newPas2 = newPass2.getText();

		if (!newPas.equals(newPas2)) {
			newPassTip.setText("两次输入的新密码不一致");
			newPassTip.setVisible(true);
		}
		String responseBody = null;
		ChgPwdDto c = new ChgPwdDto();
		c.setOldPass(oldPas);
		c.setNewPass(newPas);
		responseBody = Http.postConnect("http://localhost:8080/storage/user/chgpwd", Container.token,
				new Gson().toJson(c));

		if (Http.CODE == 200) {
			System.out.println(new Gson().fromJson(responseBody, Map.class).toString());
			changePassTip.setText("修改密码成功");
			changePassTip.setVisible(true);
			oldPassTip.setVisible(false);
			newPassTip.setVisible(false);
			newPassTip2.setVisible(false);
		} else if (Http.CODE == 400) {
			System.out.println(new Gson().fromJson(responseBody, Map.class).toString());
			Map<String, Object> map = new Gson().fromJson(responseBody, Map.class);
			String pos = (String) map.get("pos");
			if (pos.equals("1")) {
				oldPassTip.setText((String) map.get("msg"));
				oldPassTip.setVisible(true);
			} else if (pos.equals("2")) {
				newPassTip.setText((String) map.get("msg"));
				newPassTip.setVisible(true);
			}
		}

	}

	@FXML
	void cancelPassButtonOnMouseClicked() {
		userManageView.setVisible(false);
	}

	@FXML
	void changePassButtonOnMouseClicked() {
		exceldataView.setVisible(false);
		addView.setVisible(false);
		listView.setVisible(false);
		profitView.setVisible(false);
		userManageView.setVisible(true);
		typeView.setVisible(false);
		changePassPane.setVisible(true);
		registerPane.setVisible(false);
		changePassTip.setVisible(false);
		oldPassTip.setVisible(false);
		newPassTip.setVisible(false);
		newPassTip2.setVisible(false);
		oldPass.clear();
		newPass.clear();
		newPass2.clear();
	}

	*//*************** change pass ***************//*

	@FXML
	void inputDataButtonOnMouseClicked(MouseEvent me) {
		inDataTip.setVisible(false);
		exceldataView.setVisible(true);
		inDataView.setVisible(true);
		outDataView.setVisible(false);
		filePosition.setVisible(false);
		fileTip.setVisible(false);
		addView.setVisible(false);
		listView.setVisible(false);
		userManageView.setVisible(false);
		profitView.setVisible(false);
		typeView.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	@FXML
	void outputDataButtonOnMouseClicked(MouseEvent me) {
		exceldataView.setVisible(true);
		inDataView.setVisible(false);
		outDataView.setVisible(true);
		filePosition.setVisible(false);
		fileTip.setVisible(false);
		addView.setVisible(false);
		listView.setVisible(false);
		userManageView.setVisible(false);
		profitView.setVisible(false);
		typeView.setVisible(false);
		outputChoiceBox1.setItems(FXCollections.observableArrayList("库存商品", "账务记录"));
		outputChoiceBox1.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					System.out.print(newVal.intValue());
					Container.type1 = newVal.intValue();
					if (newVal.intValue() == 0) {
						outputChoiceBox2.setItems(FXCollections.observableArrayList("所有商品", "生产的商品", "购入的商品"));
						outputChoiceBox2.getSelectionModel().selectedIndexProperty().addListener(
								(ObservableValue<? extends Number> ov1, Number oldVal1, Number newVal1) -> {
									Container.type2 = newVal1.intValue();
								});
						outputFileName.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-库存记录");
						Container.outFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-库存记录";

					} else if (newVal.intValue() == 1) {
						outputChoiceBox2.setItems(
								FXCollections.observableArrayList("所有记录", "销售出库记录", "花销记录", "生产入库记录", "修改商品价格记录 "));
						outputChoiceBox2.getSelectionModel().selectedIndexProperty().addListener(
								(ObservableValue<? extends Number> ov1, Number oldVal1, Number newVal1) -> {
									Container.type2 = newVal1.intValue();
								});
						outputFileName.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-账务记录");
						Container.outFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-账务记录";
					}
				});
	}

	@SuppressWarnings("unused")
	@FXML
	void outPositionButtonOnMouseClicked(MouseEvent e) throws Exception {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择导出文件位置");
		fileChooser.setInitialFileName(Container.outFileName + ".csv");
		File position = fileChooser.showSaveDialog(Container.stage);
		if (position != null) {
			String originalDir = position.toString();
			String dir = position.toString().replaceAll("\\\\", "%5C");
			System.out.println(dir);
			String responseBody = null;
			if (Container.type1 == 0) {

				responseBody = Http.getConnect(
						"http://localhost:8080/storage/goods/output?type=" + Container.type2 + "&fileAddr=" + dir,
						Container.token);

				if (Http.CODE == 200) {
					fileTip.setVisible(true);
					filePosition.setVisible(true);
					filePosition.setText(originalDir);
				} else {

				}
			} else if (Container.type1 == 1) {
				responseBody = Http.getConnect(
						"http://localhost:8080/storage/record/output?type=" + Container.type2 + "&fileAddr=" + dir,
						Container.token);

				if (Http.CODE == 200) {
					fileTip.setVisible(true);
					filePosition.setVisible(true);
					filePosition.setText(originalDir);
				} else {

				}
			}

		}
	}

	@FXML
	void searchProfitButtonOnMouseClicked(MouseEvent me) throws Exception {
		String s_time = startTime.getValue().toString();
		String e_time = endTime.getValue().toString();
		String responseBody = null;

		responseBody = Http.getConnect(
				"http://localhost:8080/storage/record/profit?s_time=" + s_time + "&e_time=" + e_time, Container.token);

		if (Http.CODE == 200) {
			ProfitVo gv = new Gson().fromJson(responseBody, ProfitVo.class);
			inCome.setText(gv.getEarn() + "");
			outCome.setText(gv.getCost() + "");
			in_outCome.setText((gv.getEarn() - gv.getCost()) + "");
		} else {

		}

	}

	@FXML
	void listProfitsButtononMouseClicked(MouseEvent me) {
		addView.setVisible(false);
		listView.setVisible(false);
		profitView.setVisible(true);
		exceldataView.setVisible(false);
		userManageView.setVisible(false);
		typeView.setVisible(false);
	}

	@FXML
	void inoutButtononMouseClicked() throws Exception {
		Container.dataController = this;
		Pane root = FXMLLoader.load(getClass().getResource("inout.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("出入库");
		stage.setScene(scene);
		stage.show();
		Container.inoutStage = stage;
		String responseBody = null;
		responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?id=" + Container.goodsIdOfInout,
				Container.token);

		if (Http.CODE == 200) {
			GoodsVo gv = new Gson().fromJson(responseBody, GoodsVo.class);
			((TextField) root.getChildren().get(1)).setText(gv.getGoods().get(0).getName());
			((TextField) root.getChildren().get(2)).setText(gv.getGoods().get(0).getCount() + "");
		} else {

		}

	}

	@FXML
	void changePriceButtononMouseClicked() throws Exception {
		Container.dataController = this;
		Pane root = FXMLLoader.load(getClass().getResource("price.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("修改价格");
		stage.setScene(scene);
		stage.show();
		Container.inoutStage = stage;
		String responseBody = null;

		responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?id=" + Container.goodsIdOfInout,
				Container.token);

		if (Http.CODE == 200) {
			GoodsVo gv = new Gson().fromJson(responseBody, GoodsVo.class);
			((TextField) root.getChildren().get(1)).setText(gv.getGoods().get(0).getName());
			((TextField) root.getChildren().get(3)).setText(gv.getGoods().get(0).getPrice() + "");
		} else {

		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void listGoodsButtonOnMouseClicked(Event event) throws Exception {
		Container.choiceType = 0;
		Container.choiceKey = null;
		goodsKey.clear();
		if (Container.permission != 1 && Container.permission != 2) {
			inoutButton.setVisible(false);
			changePriceButton.setVisible(false);
		} else {
			inoutButton.setVisible(true);
			changePriceButton.setVisible(true);
		}
		goodsChoice.setVisible(true);
		recordsChoice.setVisible(false);
		goodsKey.setVisible(true);
		recordKey.setVisible(false);

		String r = null;
		r = Http.getConnect("http://localhost:8080/storage/type/g_list", Container.token);
		if (Http.CODE == 200) {

			List<GType> gv = new Gson().fromJson(r, new TypeToken<List<GType>>() {
			}.getType());
			Container.gType = gv;
			List<String> gs = new ArrayList<>();
			gs.add("所有库存");
			for (GType g : gv) {
				gs.add(g.getG_name());
			}
			ObservableList<String> items = FXCollections.observableArrayList(gs);
			goodsChoice.setItems(items);
		}

		goodsChoice.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {

					String responseBody = null;
					try {
						if (newVal.intValue() == 0) {
							Container.choiceType = 0;
							if (Container.choiceKey != null) {
								responseBody = Http.getConnect(
										"http://localhost:8080/storage/goods/list?key=" + goodsKey.getText(),
										Container.token);
							} else {
								responseBody = Http.getConnect("http://localhost:8080/storage/goods/list",
										Container.token);
							}
						} else {
							int type = Container.gType.get(newVal.intValue()-1).getId();
							Container.choiceType = type;
							if (Container.choiceKey != null) {
								responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?type=" + type
										+ "&key=" + goodsKey.getText(), Container.token);
							} else {
								responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?type=" + type,
										Container.token);
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					if (Http.CODE == 200) {
						// 成功
						GoodsVo gv = new Gson().fromJson(responseBody, GoodsVo.class);

						pagination.setPageCount(gv.getCount() % 16 == 0 ? gv.getCount() / 16 : gv.getCount() / 16 + 1);
						((TableColumn) goodsTable.getColumns().get(0))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("id"));
						((TableColumn) goodsTable.getColumns().get(1))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("name"));
						((TableColumn) goodsTable.getColumns().get(2))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("price"));
						((TableColumn) goodsTable.getColumns().get(3))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("count"));
						((TableColumn) goodsTable.getColumns().get(4))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("type"));
						goodsTable.setItems(FXCollections.observableArrayList(MapToGoods.to(gv.getGoods())));
						listView.setVisible(true);
						addView.setVisible(false);
						profitView.setVisible(false);
						goodsTable.setVisible(true);
						recordsTable.setVisible(false);
						exceldataView.setVisible(false);
						userManageView.setVisible(false);
						typeView.setVisible(false);
						// goodsTable.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					} else {
						// 失败
						// message.setText((String) new
						// Gson().fromJson(responseBody,
						// Map.class).get("msg"));
						// message.setVisible(true);
					}
				});
		pagination.setPageFactory((Integer i) -> createGoodsPage(i));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void listRecordsButtonOnMouseClicked(Event event) throws Exception {
		Container.choiceType = 0;
		Container.choiceKey = null;
		recordKey.clear();
		goodsChoice.setVisible(false);
		recordsChoice.setVisible(true);
		goodsKey.setVisible(false);
		recordKey.setVisible(true);
		inoutButton.setVisible(false);
		changePriceButton.setVisible(false);
		recordsChoice.setItems(FXCollections.observableArrayList("所有类型", "销售出库类型", "花销类型", "生产入库类型", "修改商品价格类型 "));
		recordsChoice.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					Container.choiceType = newVal.intValue();
					String responseBody = null;
					try {
						if (newVal.intValue() == 1 || newVal.intValue() == 2 || newVal.intValue() == 3
								|| newVal.intValue() == 4) {
							if (Container.choiceKey != null) {
								responseBody = Http.getConnect("http://localhost:8080/storage/record/list?type="
										+ newVal.intValue() + "&key=" + Container.choiceKey, Container.token);
							} else {
								responseBody = Http.getConnect(
										"http://localhost:8080/storage/record/list?type=" + newVal.intValue(),
										Container.token);
							}

						} else {
							if (Container.choiceKey != null) {
								responseBody = Http.getConnect(
										"http://localhost:8080/storage/record/list" + "?key=" + Container.choiceKey,
										Container.token);
							} else {
								responseBody = Http.getConnect("http://localhost:8080/storage/record/list",
										Container.token);
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					if (Http.CODE == 200) {
						// 成功
						RecordVo rv = new Gson().fromJson(responseBody, RecordVo.class);
						pagination.setPageCount(rv.getCount() % 16 == 0 ? rv.getCount() / 16 : rv.getCount() / 16 + 1);
						((TableColumn) recordsTable.getColumns().get(0))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("id"));
						((TableColumn) recordsTable.getColumns().get(1))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("type"));
						((TableColumn) recordsTable.getColumns().get(2))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("comment"));
						((TableColumn) recordsTable.getColumns().get(3))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("price"));
						((TableColumn) recordsTable.getColumns().get(4))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("create_time"));
						((TableColumn) recordsTable.getColumns().get(5))
								.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("goods_name"));
						recordsTable.setItems(FXCollections.observableArrayList(MapToRecords.to(rv.getRecords())));
						listView.setVisible(true);
						addView.setVisible(false);
						profitView.setVisible(false);
						goodsTable.setVisible(false);
						recordsTable.setVisible(true);
						exceldataView.setVisible(false);
						userManageView.setVisible(false);
						typeView.setVisible(false);
					} else {
						// 失败
					}
				});

		pagination.setPageFactory((Integer i) -> createRecordsPage(i));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node createRecordsPage(Integer i) {
		String responseBody = null;
		try {
			if (Container.choiceType == 1 || Container.choiceType == 2 || Container.choiceType == 3
					|| Container.choiceType == 4) {
				if (Container.choiceKey != null) {
					responseBody = Http.getConnect("http://localhost:8080/storage/record/list?type="
							+ Container.choiceType + "&page=" + (i + 1) + "&key=" + Container.choiceKey,
							Container.token);
				} else {
					responseBody = Http.getConnect("http://localhost:8080/storage/record/list?type="
							+ Container.choiceType + "&page=" + (i + 1), Container.token);
				}

			} else {
				if (Container.choiceKey != null) {
					responseBody = Http.getConnect(
							"http://localhost:8080/storage/record/list?page=" + (i + 1) + "&key=" + Container.choiceKey,
							Container.token);
				} else {
					responseBody = Http.getConnect("http://localhost:8080/storage/record/list?page=" + (i + 1),
							Container.token);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Http.CODE == 200) {
			// 成功
			RecordVo rv = new Gson().fromJson(responseBody, RecordVo.class);
			pagination.setPageCount(rv.getCount() % 16 == 0 ? rv.getCount() / 16 : rv.getCount() / 16 + 1);
			((TableColumn) recordsTable.getColumns().get(0))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("id"));
			((TableColumn) recordsTable.getColumns().get(1))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("type"));
			((TableColumn) recordsTable.getColumns().get(2))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("comment"));
			((TableColumn) recordsTable.getColumns().get(3))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("price"));
			((TableColumn) recordsTable.getColumns().get(4))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("create_time"));
			((TableColumn) recordsTable.getColumns().get(5))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("goods_name"));
			recordsTable.setItems(FXCollections.observableArrayList(MapToRecords.to(rv.getRecords())));
			listView.setVisible(true);
			addView.setVisible(false);
			profitView.setVisible(false);
			goodsTable.setVisible(false);
			recordsTable.setVisible(true);
			exceldataView.setVisible(false);
			userManageView.setVisible(false);
			typeView.setVisible(false);
		} else {
			// 失败
			// message.setText((String) new Gson().fromJson(responseBody,
			// Map.class).get("msg"));
			// message.setVisible(true);
		}

		Node n = new Button();
		n.setVisible(false);
		return n;
	}

	@FXML
	void goodsRowOnMouseClicked(MouseEvent e) {
		String target = e.getTarget().toString();
		if (target.endsWith("'")) {
			String id = target.substring(target.indexOf('\'') + 1, target.lastIndexOf('\''));
			Container.goodsIdOfInout = id;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node createGoodsPage(Integer i) {
		String responseBody = null;
		try {
			if(Container.choiceType==0){
				if (Container.choiceKey != null) {
					responseBody = Http.getConnect(
							"http://localhost:8080/storage/goods/list?page=" + (i + 1) + "&key=" + Container.choiceKey,
							Container.token);
				} else {
					responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?page=" + (i + 1),
							Container.token);
				}
			}else{
				if (Container.choiceKey != null) {
					responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?type="
							+ (Container.choiceType) + "&page=" + (i + 1) + "&key=" + Container.choiceKey,
							Container.token);
				} else {
					responseBody = Http.getConnect("http://localhost:8080/storage/goods/list?type="
							+ (Container.choiceType) + "&page=" + (i + 1), Container.token);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (Http.CODE == 200) {
			// 成功
			GoodsVo gv = new Gson().fromJson(responseBody, GoodsVo.class);
			pagination.setPageCount(gv.getCount() % 16 == 0 ? gv.getCount() / 16 : gv.getCount() / 16 + 1);
			((TableColumn) goodsTable.getColumns().get(0))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("id"));
			((TableColumn) goodsTable.getColumns().get(1))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("name"));
			((TableColumn) goodsTable.getColumns().get(2))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("price"));
			((TableColumn) goodsTable.getColumns().get(3))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("count"));
			((TableColumn) goodsTable.getColumns().get(4))
					.setCellValueFactory(new PropertyValueFactory<JXGoodsVo, String>("type"));
			goodsTable.setItems(FXCollections.observableArrayList(MapToGoods.to(gv.getGoods())));
			listView.setVisible(true);
			addView.setVisible(false);
			profitView.setVisible(false);
			goodsTable.setVisible(true);
			recordsTable.setVisible(false);
			exceldataView.setVisible(false);
			userManageView.setVisible(false);
			typeView.setVisible(false);
		} else {
			// 失败
			// message.setText((String) new Gson().fromJson(responseBody,
			// Map.class).get("msg"));
			// message.setVisible(true);
		}

		Node n = new Button();
		n.setVisible(false);
		return n;
	}

	@SuppressWarnings("unchecked")
	@FXML
	void addGoodsButtonOnMouseClicked(Event e) throws Exception {
		String responseBody = null;
		responseBody = Http.getConnect("http://localhost:8080/storage/type/g_list", Container.token);
		if (Http.CODE == 200) {

			List<GType> gv = new Gson().fromJson(responseBody, new TypeToken<List<GType>>() {
			}.getType());
			Container.gType = gv;
			List<String> gs = new ArrayList<>();
			for (GType g : gv) {
				gs.add(g.getG_name());
			}
			ObservableList<String> items = FXCollections.observableArrayList(gs);
			addGoodsType.setItems(items);
		}

		listView.setVisible(false);
		addView.setVisible(true);
		addGoods.setVisible(true);
		addRecords.setVisible(false);
		exceldataView.setVisible(false);
		profitView.setVisible(false);
		userManageView.setVisible(false);
		typeView.setVisible(false);
	}

	@SuppressWarnings("unused")
	@FXML
	void ConfirmAddGoodsButtonOnMouseClicked(Event e) throws Exception {
		String name = addGoodsName.getText();
		String t_name = addGoodsType.getSelectionModel().getSelectedItem().toString();
		int type = 0;
		for (GType gt : Container.gType) {
			if (gt.getG_name().equals(t_name)) {
				type = gt.getId();
				break;
			}
		}
		// int type = addGoodsType.getSelectionModel().getSelectedIndex() + 1;
		String price = addGoodsPrice.getText();
		String count = addGoodsCount.getText();
		Goods goods = new Goods();
		goods.setName(name);
		goods.setType(type);
		goods.setPrice(Double.parseDouble(price));
		goods.setCount(Integer.parseInt(count));

		String responseBody = Http.postConnect("http://localhost:8080/storage/goods/add", Container.token,
				new Gson().toJson(goods));
		if (Http.CODE == 200) {
			listGoodsButtonOnMouseClicked(e);
		} else {
			// 失败
		}

		listGoodsButtonOnMouseClicked(e);
	}

	@FXML
	void CancelAddGoodsButtonOnMouseClicked(Event e) throws Exception {
		listGoodsButtonOnMouseClicked(e);
	}

	@SuppressWarnings("unused")
	@FXML
	void ConfirmAddRecordsButtonOnMouseClicked(Event e) throws Exception {
		Record record = new Record();
		record.setComment(addRecordsComment.getText());
		record.setType(2);
		record.setPrice(Double.parseDouble(addRecordsPrice.getText()));

		String responseBody = Http.postConnect("http://localhost:8080/storage/record/add", Container.token,
				new Gson().toJson(record));
		if (Http.CODE == 200) {
			listGoodsButtonOnMouseClicked(e);
		} else {
			// 失败
		}

		listRecordsButtonOnMouseClicked(e);
	}

	@FXML
	void CancelAddRecordsButtonOnMouseClicked(Event e) throws Exception {
		listRecordsButtonOnMouseClicked(e);
	}

	@FXML
	void addRecordsButtonOnMouseClicked(Event e) {
		listView.setVisible(false);
		addView.setVisible(true);
		addRecords.setVisible(true);
		addGoods.setVisible(false);
		exceldataView.setVisible(false);
		profitView.setVisible(false);
		userManageView.setVisible(false);
		typeView.setVisible(false);
	}

	@FXML
	void logout(Event e) {
		System.exit(0);
	}
}
*/