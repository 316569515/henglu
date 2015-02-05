package test.hentglu.erp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import jbarcodebean.Code128;
import jbarcodebean.JBarcodeBean;

public class LabelBarCode implements Printable {

	private String labelNumber;
	private String creatDep;
	private String billNumber;
	private String receiveName;
	private String packNum;
	private String tranType;
	private String startCity;
	private String endCity;
	private String endProvince;
	private String endAreaAddress;
	private String printDate;
	private String sentNumber;
	private String customer;
	private String receivingCompany;
	private String weight;
    private String volume;

	public LabelBarCode(String labelNumber, String creatDep, String billNumber, String receiveName, String packNum, String tranType,
			String startCity, String endCity, String endProvince, String printDate, String endAreaAddress, String sentNumber,
			String customer, String receivingCompany,String weight,String volume) {
		super();
		this.labelNumber = labelNumber;
		this.creatDep = creatDep;
		this.billNumber = billNumber;
		this.receiveName = receiveName;
		this.packNum = packNum;
		this.tranType = tranType;
		this.startCity = startCity;
		this.endCity = endCity;
		this.endProvince = endProvince;
		this.endAreaAddress = endAreaAddress;
		this.printDate = printDate;
		this.sentNumber = sentNumber;
		this.customer = customer;
		this.receivingCompany = receivingCompany;
		this.weight = weight;
		this.volume = volume;

	}

	public String getLabelNumber() {
		return labelNumber;
	}

	public void setLabelNumber(String labelNumber) {
		this.labelNumber = labelNumber;
	}

	public String getCreatDep() {
		return creatDep;
	}

	public void setCreatDep(String creatDep) {
		this.creatDep = creatDep;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getPackNum() {
		return packNum;
	}

	public void setPackNum(String packNum) {
		this.packNum = packNum;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getEndProvince() {
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getEndAreaAddress() {
		return printDate;
	}

	public void setEndAreaAddress(String endAreaAddress) {
		this.endAreaAddress = endAreaAddress;
	}

	public String getSentNumber() {
		return sentNumber;
	}

	public void setSentNumber(String sentNumber) {
		this.sentNumber = sentNumber;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getReceivingCompany() {
		return receivingCompany;
	}

	public void setReceivingCompany(String receivingCompany) {
		this.receivingCompany = receivingCompany;
	}
	
	public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int print(Graphics gra, PageFormat pf, int pageIndex) throws PrinterException {
		if (pageIndex >= 0) {
			if(this.customer!=null && !"".equals(this.customer)){
				if (customer.contains("联想移动通信科技有限公司")) {
					setBookLXYD(gra, pf);
				}else{
					setBook(gra, pf);
				}
			}else {
				setBook(gra, pf);
			}
			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}

	/***
	 * 正常标签打印模版
	 * @param gra
	 * @param pf
	 */
	private void setBook(Graphics gra, PageFormat pf) {
		Component c = null;
		String str = "恒路物流";
		//转换成Graphics2D
		Graphics2D g2 = (Graphics2D) gra;
		//设置打印颜色为黑色
		g2.setColor(Color.black);
		//打印起点坐标
		double x = pf.getImageableX();
		double y = pf.getImageableY();
		Font font = new Font("新宋体", Font.PLAIN, 9);
		g2.setFont(font);//设置字体
		BasicStroke bs_3 = new BasicStroke(1.0f);
		g2.setStroke(bs_3);
		float heigth = font.getSize2D();//字体高度

		JBarcodeBean bb = new JBarcodeBean();
		bb.setCodeType(new Code128());
		bb.setShowText(true);
		bb.setCode(labelNumber);
		bb.setAngleDegrees(90);

		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		//创建生成图片（在老系统中不稳定）
		//			Image localImage = Toolkit.getDefaultToolkit().createImage(localByteArrayOutputStream.toByteArray());

		//将条码生成图片，然后保存在C:\EasLabelFile目录下
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream("C:\\EasLabelFile\\" + labelNumber + ".jpg");
			bb.gifEncode(localFileOutputStream);
			localFileOutputStream.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int img_width = 60;
		//			int img_height = localImage.getHeight(c);
		int code_height = 20;
		int code_font_size = 6;
		int sjr_height = 30;
		int sjr_font_size = 8;
		int trantype_font_size = 5;
		int mdd_font_size = 25;
		int mdd_height = 90;
		int rect_width = 238;
		int rect_height = 174;

		int j_width = 15;

		Font cfont = g2.getFont();
		g2.drawRect((int) (x + 5), (int) (y + 0), rect_width - j_width, rect_height);
		Font newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize() + 3);
		g2.setFont(newFont);
		g2.drawString(str, (float) (x + 6 + 1), (float) (y + 3 + heigth));
		//客户发货单号
		if (this.sentNumber != null && !"".equals(sentNumber)) {
			newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + 3);
			g2.setFont(newFont);
			g2.drawString(this.sentNumber, (float) (x + 90), (float) (y + 5 + heigth));
			g2.drawLine((int) (x + 5), (int) (y + 6 + heigth), (int) (x + 5 + rect_width - j_width), (int) (y + 6 + heigth));
		}

		//			//录单网点
		//			newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize());
		//			g2.setFont(newFont);
		//			g2.drawString(this.creatDep, (float) (x + 100), (float) (y + 5 + heigth));
		//			g2.drawLine((int) (x + 5), (int) (y + 6 + heigth), (int) (x + 5 + rect_width - j_width),
		//					(int) (y + 6 + heigth));
		//运单编号
		newFont = new Font(cfont.getName(), cfont.getStyle(), cfont.getSize() + code_font_size);
		g2.setFont(newFont);
		g2.drawString(this.billNumber, (float) (x + 75), (float) (y + 6 + heigth + newFont.getSize2D()));
		//运单编号单元格
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 5 + heigth + code_height), (int) (x + 5 + rect_width - j_width), (int) (y + 5
				+ heigth + code_height));
		//			//录单网点
		newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize());
		g2.setFont(newFont);
		g2.drawString(this.creatDep, (float) (x + 150), (float) (y + 5 + heigth + code_height));
		g2.drawLine((int) (x + 5), (int) (y + 6 + heigth), (int) (x + 5 + rect_width - j_width), (int) (y + 6 + heigth));

		//			//收件人单元格
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 5 + heigth + code_height + sjr_height), (int) (x + 5 + rect_width - j_width),
				(int) (y + 5 + heigth + code_height + sjr_height));

		//收件人
		int packReceiverLength = receiveName.length();
		int packReceiverInt = 0;
		if (packReceiverLength <= 2) {
			packReceiverInt = 36;
		} else if (packReceiverLength == 3) {
			packReceiverInt = 30;
		} else if (packReceiverLength == 4) {
			packReceiverInt = 24;
		} else if (packReceiverLength == 5) {
			packReceiverInt = 18;
		} else if (packReceiverLength == 6) {
			packReceiverInt = 8;
		} else {
			packReceiverInt = 0;
		}
		newFont = new Font("华文楷体", Font.BOLD, cfont.getSize() + sjr_font_size);
		g2.setFont(newFont);
		g2.drawString(this.receiveName, (float) (x + 7 + img_width + 6 + packReceiverInt), (float) (y + 5 + heigth + code_height
				+ newFont.getSize2D() + 4));
		//			//（收件人、运输方式下面的线）
		//			g2.drawLine((int) (x + 7 + img_width + 60), (int) (y + 5 + heigth + code_height),
		//					(int) (x + 7 + img_width + 60), (int) (y + 5 + heigth + code_height + sjr_height));
		//			newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize() + trantype_font_size);
		//			g2.setFont(newFont);
		//			int packNumLength = 0;
		//			int packNumInt = Integer.parseInt(packNum);
		//			if(packNumInt <= 9){
		//				packNumLength = 82;
		//			}else if(packNumInt <= 99){
		//				packNumLength = 78;
		//			}else if(packNumInt <= 999){
		//				packNumLength = 74;
		//			}else{
		//				packNumLength = 70;
		//			}
		//			//件数
		//			g2.drawString(this.packNum + "件", (float) (x + 7 + img_width + packNumLength), (float) (y + 5 + heigth + code_height
		//					+ newFont.getSize2D() + 5));
		//运输方式
		g2.drawLine((int) (x + 7 + img_width + 60 + 70), (int) (y + 5 + heigth + code_height), (int) (x + 7 + img_width + 60 + 70),
				(int) (y + 5 + heigth + code_height + sjr_height));
		newFont = new Font("华文楷体", Font.BOLD, cfont.getSize() + trantype_font_size + 3);
		g2.setFont(newFont);
		g2.drawString(this.tranType, (float) (x + 7 + img_width + 60 + 60 + 18), (float) (y + 5 + heigth + code_height
				+ newFont.getSize2D() + 5));

		//发货人市
		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + 7);
		g2.setFont(newFont);
		g2.drawString(this.startCity, (float) (x + 7 + img_width + 6), (float) (y + 5 + heigth + code_height + sjr_height + newFont
				.getSize2D()));

		//收货人市
		int endCityLength = endCity.length();
		if (endCityLength <= 3) {
			mdd_font_size = 25;
		} else if (endCityLength <= 5) {
			mdd_font_size = 15;
		} else if (endCityLength <= 7) {
			mdd_font_size = 12;
		} else {
			mdd_font_size = 10;
		}

		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + mdd_font_size);
		g2.setFont(newFont);
		g2.drawString(this.endCity, (float) (x + 7 + img_width + 40), (float) (y + 5 + heigth + code_height + sjr_height
				+ newFont.getSize2D() + 20));

		if (this.endAreaAddress != null && !"".equals(endAreaAddress)) {
			if (endAreaAddress.length() <= 4) {
				//（收件人、运输方式下面的线）
				g2.drawString("→", (float) (x + 7 + img_width + 6 + 55), (float) (y + 5 + heigth + code_height + sjr_height + 22));
				//收货人区县
				newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + 7);
				g2.setFont(newFont);
				g2.drawString(this.endAreaAddress, (float) (x + 7 + img_width + 6 + 90),
						(float) (y + 5 + heigth + code_height + sjr_height + newFont.getSize2D()));
			} else {
				newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + mdd_font_size - 10);
				g2.setFont(newFont);
				//（收件人、运输方式下面的线）
				g2.drawString("→", (float) (x + 7 + img_width + 6 + 50 - 2), (float) (y + 5 + heigth + code_height + sjr_height + 22));
				//收货人区县
				newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + 2);
				g2.setFont(newFont);
				g2.drawString(this.endAreaAddress, (float) (x + 7 + img_width + 6 + 70), (float) (y + 5 + heigth + code_height + sjr_height
						+ newFont.getSize2D() + 3));
			}
		} else {
			//（收件人、运输方式下面的线）
			g2.drawString("→", (float) (x + 7 + img_width + 6 + 55), (float) (y + 5 + heigth + code_height + sjr_height + 22));
		}

		//收货人省
		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() - 25);
		g2.setFont(newFont);

		//件数
		g2.drawString(this.packNum + "件", (float) (x + 7 + img_width + 6), (float) (y + 5 + heigth + code_height + sjr_height
				+ newFont.getSize2D() + 95));
		if (endProvince.length() <= 4) {
			g2.drawString(this.endProvince, (float) (x + 7 + img_width + 60 + 35), (float) (y + 5 + heigth + code_height + sjr_height
					+ newFont.getSize2D() + 95));
		} else {
			newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() - 22);
			g2.setFont(newFont);
			g2.drawString(this.endProvince, (float) (x + 7 + img_width + 60 - 5), (float) (y + 5 + heigth + code_height + sjr_height
					+ newFont.getSize2D() + 95));
		}
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 5 + heigth + code_height + sjr_height + mdd_height),
				(int) (x + 5 + rect_width - j_width), (int) (y + 5 + heigth + code_height + sjr_height + mdd_height));
		//打印时间
		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize());
		g2.setFont(newFont);
		g2.drawString(this.printDate, (float) (x + 7 + img_width + 6),
				(float) (y + 9 + heigth + code_height + sjr_height + mdd_height + newFont.getSize2D()));
		//重量
		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize()+4);
        g2.setFont(newFont);
        if(!this.weight.equals("0.0")){
            g2.drawString(this.weight + "Kg", (float) (x + 7 + img_width + 6 + 52),
                    (float) (y + 7 + heigth + code_height + sjr_height + mdd_height + newFont.getSize2D()));
        }
        //体积
        g2.setFont(newFont);
        if(!this.volume.equals("0.0")){
            g2.drawString(this.volume+"m³", (float) (x + 7 + img_width + 6 + 52 + 55),
                    (float) (y + 7 + heigth + code_height + sjr_height + mdd_height + newFont.getSize2D()));
        }
		//条码右侧的线
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 6 + heigth), (int) (x + 7 + img_width), (int) (y + rect_height));
		//条码
		//			g2.drawImage(localImage, (int) (x + 6), (int) (y + 7 + heigth), c);
		ImageIcon localImage2 = new ImageIcon("C:\\EasLabelFile\\" + labelNumber + ".jpg");
		g2.drawImage(localImage2.getImage(), (int) (x + 6), (int) (y + 7 + heigth), c);
	}

	/***
	 * 联想移动标签打印模版
	 * @param gra
	 * @param pf
	 * @param pageIndex
	 */
	private void setBookLXYD(Graphics gra, PageFormat pf) {
		Component c = null;
		String hengluwuliu = "恒路物流";
		//转换成Graphics2D
		Graphics2D g2 = (Graphics2D) gra;
		//设置打印颜色为黑色
		g2.setColor(Color.black);
		//打印起点坐标
		double x = pf.getImageableX();
		double y = pf.getImageableY();

		Font font = new Font("新宋体", Font.PLAIN, 9);
		g2.setFont(font);//设置字体
		BasicStroke bs_3 = new BasicStroke(1.0f);
		g2.setStroke(bs_3);
		float heigth = font.getSize2D();//字体高度

		JBarcodeBean bb = new JBarcodeBean();
		bb.setCodeType(new Code128());
		bb.setShowText(true);
		bb.setCode(labelNumber);
		bb.setAngleDegrees(90);

		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		//创建生成图片（在老系统中不稳定）
		//		Image localImage = Toolkit.getDefaultToolkit().createImage(localByteArrayOutputStream.toByteArray());

		//将条码生成图片，然后保存在C:\EasLabelFile目录下
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream("C:\\EasLabelFile\\" + labelNumber + ".jpg");
			bb.gifEncode(localFileOutputStream);
			localFileOutputStream.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int img_width = 60;
		//		int img_height = localImage.getHeight(c);
		int code_height = 20;
		int code_font_size = 6;
		int sjr_height = 30;
		int sjr_font_size = 8;
		int trantype_font_size = 5;
		int mdd_font_size = 25;
		int mdd_height = 90;
		int rect_width = 238;
		int rect_height = 174;

		int j_width = 15;

		Font cfont = g2.getFont();
		g2.drawRect((int) (x + 5), (int) (y + 0), rect_width - j_width, rect_height);
		Font newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize() + 3);
		g2.setFont(newFont);
		//收货人单位
		if (this.receivingCompany != null && !"".equals(receivingCompany)) {
			String receCompany = "";
			if(this.receivingCompany.length()>13){
				receCompany = this.receivingCompany.substring(0, 13);
			}else{
				receCompany = this.receivingCompany;
			}
			g2.drawString(receCompany, (float) (x + 6 + 1), (float) (y + 3 + heigth));
		}
		//收货人
		if (this.receiveName != null && !"".equals(receiveName)) {
			newFont = new Font("华文楷体", Font.BOLD, cfont.getSize() + 3);
			g2.setFont(newFont);
			g2.drawString(":", (float) (x + 164), (float) (y + 3 + heigth));
			g2.drawString(this.receiveName, (float) (x + 172), (float) (y + 3 + heigth));
			g2.drawLine((int) (x + 5), (int) (y + 6 + heigth), (int) (x + 5 + rect_width - j_width), (int) (y + 6 + heigth));
		}

		//		//录单网点
		//		newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize());
		//		g2.setFont(newFont);
		//		g2.drawString(this.creatDep, (float) (x + 100), (float) (y + 5 + heigth));
		//		g2.drawLine((int) (x + 5), (int) (y + 6 + heigth), (int) (x + 5 + rect_width - j_width),
		//				(int) (y + 6 + heigth));
		//运单编号
		newFont = new Font(cfont.getName(), cfont.getStyle(), cfont.getSize() + code_font_size);
		g2.setFont(newFont);
		g2.drawString(this.billNumber, (float) (x + 75), (float) (y + 6 + heigth + newFont.getSize2D()));
		//运单编号单元格
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 5 + heigth + code_height), (int) (x + 5 + rect_width - j_width), (int) (y + 5
				+ heigth + code_height));
		//		//录单网点
		newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize());
		g2.setFont(newFont);
		g2.drawString(this.creatDep, (float) (x + 150), (float) (y + 5 + heigth + code_height));
		g2.drawLine((int) (x + 5), (int) (y + 6 + heigth), (int) (x + 5 + rect_width - j_width), (int) (y + 6 + heigth));

		//			//收件人单元格
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 5 + heigth + code_height + sjr_height), (int) (x + 5 + rect_width - j_width),
				(int) (y + 5 + heigth + code_height + sjr_height));

		//客户发货单号
		if (this.sentNumber != null && !"".equals(sentNumber)) {
			int packReceiverLength = sentNumber.length();
			int packReceiverInt = 0;
			if (packReceiverLength <= 2) {
				packReceiverInt = 36;
			} else if (packReceiverLength == 3) {
				packReceiverInt = 30;
			} else if (packReceiverLength == 4) {
				packReceiverInt = 24;
			} else if (packReceiverLength == 5) {
				packReceiverInt = 18;
			} else if (packReceiverLength == 6) {
				packReceiverInt = 8;
			} else {
				packReceiverInt = 0;
			}
			newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + sjr_font_size);
			g2.setFont(newFont);
			g2.drawString(this.sentNumber, (float) (x + 7 + img_width + 6 + packReceiverInt), (float) (y + 5 + heigth + code_height
					+ newFont.getSize2D() + 4));
		}
		//运输方式
		g2.drawLine((int) (x + 7 + img_width + 60 + 70), (int) (y + 5 + heigth + code_height), (int) (x + 7 + img_width + 60 + 70),
				(int) (y + 5 + heigth + code_height + sjr_height));
		newFont = new Font("华文楷体", Font.BOLD, cfont.getSize() + trantype_font_size + 3);
		g2.setFont(newFont);
		g2.drawString(this.tranType, (float) (x + 7 + img_width + 60 + 60 + 18), (float) (y + 5 + heigth + code_height
				+ newFont.getSize2D() + 5));

		//发货人市
		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + 7);
		g2.setFont(newFont);
		g2.drawString(this.startCity, (float) (x + 7 + img_width + 6), (float) (y + 5 + heigth + code_height + sjr_height + newFont
				.getSize2D()));

		//收货人市
		int endCityLength = endCity.length();
		if (endCityLength <= 3) {
			mdd_font_size = 25;
		} else if (endCityLength <= 5) {
			mdd_font_size = 15;
		} else if (endCityLength <= 7) {
			mdd_font_size = 12;
		} else {
			mdd_font_size = 10;
		}

		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + mdd_font_size);
		g2.setFont(newFont);
		g2.drawString(this.endCity, (float) (x + 7 + img_width + 40), (float) (y + 5 + heigth + code_height + sjr_height
				+ newFont.getSize2D() + 20));

		if (this.endAreaAddress != null && !"".equals(endAreaAddress)) {
			if (endAreaAddress.length() <= 4) {
				//（收件人、运输方式下面的线）
				g2.drawString("→", (float) (x + 7 + img_width + 6 + 55), (float) (y + 5 + heigth + code_height + sjr_height + 22));
				//收货人区县
				newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + 7);
				g2.setFont(newFont);
				g2.drawString(this.endAreaAddress, (float) (x + 7 + img_width + 6 + 90),
						(float) (y + 5 + heigth + code_height + sjr_height + newFont.getSize2D()));
			} else {
				newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + mdd_font_size - 10);
				g2.setFont(newFont);
				//（收件人、运输方式下面的线）
				g2.drawString("→", (float) (x + 7 + img_width + 6 + 50 - 2), (float) (y + 5 + heigth + code_height + sjr_height + 22));
				//收货人区县
				newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() + 2);
				g2.setFont(newFont);
				g2.drawString(this.endAreaAddress, (float) (x + 7 + img_width + 6 + 70), (float) (y + 5 + heigth + code_height + sjr_height
						+ newFont.getSize2D() + 3));
			}
		} else {
			//（收件人、运输方式下面的线）
			g2.drawString("→", (float) (x + 7 + img_width + 6 + 55), (float) (y + 5 + heigth + code_height + sjr_height + 22));
		}

		//收货人省
		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() - 25);
		g2.setFont(newFont);

		//件数
		g2.drawString(this.packNum + "件", (float) (x + 7 + img_width + 6), (float) (y + 5 + heigth + code_height + sjr_height
				+ newFont.getSize2D() + 95));
		if (endProvince.length() <= 4) {
			g2.drawString(this.endProvince, (float) (x + 7 + img_width + 60 + 35), (float) (y + 5 + heigth + code_height + sjr_height
					+ newFont.getSize2D() + 95));
		} else {
			newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize() - 22);
			g2.setFont(newFont);
			g2.drawString(this.endProvince, (float) (x + 7 + img_width + 60 - 5), (float) (y + 5 + heigth + code_height + sjr_height
					+ newFont.getSize2D() + 95));
		}
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 5 + heigth + code_height + sjr_height + mdd_height),
				(int) (x + 5 + rect_width - j_width), (int) (y + 5 + heigth + code_height + sjr_height + mdd_height));
		//打印时间
		newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize());
		g2.setFont(newFont);
		g2.drawString(this.printDate, (float) (x + 7 + img_width + 6),
				(float) (y + 9 + heigth + code_height + sjr_height + mdd_height + newFont.getSize2D()));
		//重量
        newFont = new Font(cfont.getName(), Font.BOLD, cfont.getSize()+4);
        g2.setFont(newFont);
        if(!this.weight.equals("0.0")){
            g2.drawString(this.weight + "Kg", (float) (x + 7 + img_width + 6 + 52),
                    (float) (y + 7 + heigth + code_height + sjr_height + mdd_height + newFont.getSize2D()));
        }
        //体积
        g2.setFont(newFont);
        if(!this.volume.equals("0.0")){
            g2.drawString(this.volume+"m³", (float) (x + 7 + img_width + 6 + 52 + 55),
                    (float) (y + 7 + heigth + code_height + sjr_height + mdd_height + newFont.getSize2D()));
        }
		//恒路物流
		newFont = new Font(cfont.getName(), Font.PLAIN, cfont.getSize() + 3);
		g2.setFont(newFont);
		g2.drawString(hengluwuliu, (float) (x + 7 + img_width + 6 + 100),
				(float) (y + 9 + heigth + code_height + sjr_height + mdd_height + newFont.getSize2D()));

		//条码右侧的线
		g2.drawLine((int) (x + 7 + img_width), (int) (y + 6 + heigth), (int) (x + 7 + img_width), (int) (y + rect_height));
		//条码
		//		g2.drawImage(localImage, (int) (x + 6), (int) (y + 7 + heigth), c);
		ImageIcon localImage2 = new ImageIcon("C:\\EasLabelFile\\" + labelNumber + ".jpg");
		g2.drawImage(localImage2.getImage(), (int) (x + 6), (int) (y + 7 + heigth), c);
	}

	public static void main(String[] args) {
		
		JBarcodeBean bb = new JBarcodeBean();
		bb.setCodeType(new Code128());
		bb.setShowText(true);
		bb.setCode("11111111111111");
		bb.setAngleDegrees(90);

		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		//创建生成图片（在老系统中不稳定）
		//			Image localImage = Toolkit.getDefaultToolkit().createImage(localByteArrayOutputStream.toByteArray());

		//将条码生成图片，然后保存在C:\EasLabelFile目录下
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream("C:\\EasLabelFile\\" + "11111111111111" + ".jpg");
			bb.gifEncode(localFileOutputStream);
			localFileOutputStream.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		//    通俗理解就是书、文档
//		Book book = new Book();
//		//    设置成竖打
//		PageFormat pf = new PageFormat();
//		pf.setOrientation(PageFormat.PORTRAIT);
//
//		//    通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
//		Paper p = new Paper();
//		p.setSize(260, 180);//纸张大小 
//		p.setImageableArea(5, 5, 260, 180);//A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
//		//		pf.setPaper(p);
//		//    把 PageFormat 和 Printable 添加到书中，组成一个页面
//		book.append(new LabelBarCode("1111111111", "1111111111", "1111111111", "1111111111", "1111111111", "1111111111", "1111111111",
//				"1111111111", "1111111111", "1111111111", "1111111111", "1111111111", null, "1111111","",""), pf, 1);
//		book.append(new LabelBarCode("2222222222", "2222222222", "2222222222", "2222222222", "2222222222", "2222222222", "2222222222",
//				"2222222222", "2222222222", "2222222222", "2222222222", "2222222222", null, "1111111","",""), pf, 1);
//		//获取打印服务对象
//		PrinterJob job = PrinterJob.getPrinterJob();
//		// 设置打印类
//		job.setPageable(book);
//
//		try {
//			//可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
//			//boolean a=job.printDialog();
//			//if(a)
//			//{        
//			job.print();
//
//			//}
//		} catch (PrinterException e) {
//			e.printStackTrace();
//		}
	}

}
