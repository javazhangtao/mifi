package com.mifi.emay;

import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * @��Ŀ���ƣ�EmayClientForHttpV1.0?
 * @��������??
 * @�����ˣ�HL.W??
 * @����ʱ�䣺2015-9-9 ����5:29:46??
 * @�޸��ˣ�HL.W??
 * @�޸�ʱ�䣺2015-9-9 ����5:29:46??
 * @�޸ı�ע��
 */
public class SDKClientTest {

    public static String sn = "8SDK-EMY-6699-RDQST";// ������к�,��ͨ������������Ա��ȡ
    public static String key = "190273";// ���к��״μ���ʱ�Լ��趨
    public static String password = "190273";// ����,��ͨ������������Ա��ȡ
    public static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";

    public static void main(String[] args) {
	try {
	    StartMenu();
	    while (true) {
		System.out.println("��������Ž��в���");
		byte[] command = new byte[4];
		System.in.read(command);
		int operate = 0;
		try {
		    String commandString = new String(command);
		    commandString = commandString.replaceAll("\r\n", "").trim();
		    operate = Integer.parseInt(commandString);
		} catch (Exception e) {
		    System.out.println("����������󣡣���");
		}
		String param = "";
		switch (operate) {
		case 1:
		    String url = baseUrl + "regist.action";
		    String ret = SDKHttpClient.regist(url, param);
		    System.out.println("ע����:" + ret);
		    break;
		case 2:
		    param = "cdkey=" + sn + "&password=" + key;
		    url = baseUrl + "querybalance.action";
		    String balance = SDKHttpClient.getBalance(url, param);
		    System.out.println("��ǰ���:" + balance);
		    break;
		case 3:
		    String mdn = "18310329817";
		    String message = "��weixiang�����óɹ�";
		    message = URLEncoder.encode(message, "UTF-8");
		    String code = "888";
		    long seqId = System.currentTimeMillis();
		    param = "cdkey=" + sn + "&password=" + key + "&phone=" + mdn + "&message=" + message;
		    url = baseUrl + "sendtimesms.action";
		    ret = SDKHttpClient.sendSMS(url, param);
		    System.out.println("���ͽ��:" + ret);

		    break;
		case 4:
		    param = "cdkey=" + sn + "&password=" + key;
		    url = baseUrl + "getmo.action";
		    List<Mo> mos = SDKHttpClient.getMos(url, sn, key);
		    System.out.println("��ȡ����������" + mos.size());
		    break;
		case 5:
		    param = "cdkey=" + sn + "&password=" + key;
		    url = baseUrl + "getreport.action";
		    List<StatusReport> srs = SDKHttpClient.getReports(url, sn, key);
		    System.out.println("��ȡ����������" + srs.size());
		    break;
		case 6:
		    System.exit(0);
		default:
		    System.out.println("û�и����� " + operate);
		    break;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void StartMenu() {
	int i = 1;
	System.out.println(i + "���������к�,����ʹ�á���ע�����ٴ�ʹ��ʱ���ø÷���.");
	i += 1;
	System.out.println(i + "������ѯ");
	i += 1;
	System.out.println(i + "�����ʹ�����ϢID�Ķ���,�ɹ���ѯ״̬����");
	i += 1;
	System.out.println(i + "����ȡ���ж���");
	i += 1;
	System.out.println(i + "��������ж���״̬����");
	i += 1;
	System.out.println(i + "���رճ���");
    }
}
