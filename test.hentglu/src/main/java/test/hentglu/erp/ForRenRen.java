package test.hentglu.erp;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
 
/** 
* @author zhouxianglh@gmail.com
* @version 1.0  2015-2-8 ����1:01:00 
*/
public class ForRenRen {
    private static final Logger logger = Logger.getLogger(ForRenRen.class);
    private static final String[] messages = new String[] { "��Һ�", "����Ӱ������", "��������һ����", "����������", "����", "TMD.......",
            "good job", "����������Ѳɽ", "С����,go go go", "�Բ���", "����˯����", "˯����", "һ���˹��ĵھ���", "ֽ����Ҫ������", "���껹�ܲ��ܿ��������֮��",
            "��ô����", "�е��ӻ�" };
    private static final String[] messages2 = new String[] { "�����Ǹ�������", "��˵��ʲô", "������", "�ں�֮����", "���ﲨ�ش�", "����ɳ������",
            "������������", "С��", "������", "�������", "����������", "���ǵڼ�����", "Time after Time", "����" };
    private static final String[] messages3 = new String[] { "�Ұ���!!", "����!!", "����������!!", "��������!!", "������!!", "������!!!",
            "���а�����!!", "����������.....", "HELP!!!!", "��Ҳ����������", "������ô������?", "�ҵ����Ŀ��ù���,���ǵ��Ǹ����㰡", "ˢ�����������", "�ٹ�����͹���ؼ���" };
 
    private static void sendGet() throws IOException, InterruptedException {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            String path = "http://www.rrmj.tv/appUser/appLogin";
            // �ύ ��¼
            HttpPost httpPost = new HttpPost(path);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("loginName", "lzz_1985@163.com"));
            nvps.add(new BasicNameValuePair("pwd", "miszhong123"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
            logger.info("�ɹ���¼....");
//            GetImage.close(httpResponse);
            httpResponse.close();
            // ÿ��10���ˮ
            while (true) {
                Thread.sleep(10000);
                doPost(httpclient);
            }
        } finally {
//            GetImage.close(httpclient);
        	httpclient.close();
        }
    }
 
    private static void doPost(CloseableHttpClient httpclient) throws ClientProtocolException, IOException {
        String path = "http://www.rrmj.tv/article/createReply";
        HttpPost httpPost = new HttpPost(path);
        // ��ID 1000��3000֮������Ҹ����ӹ�ˮ
        int value = (int) (Math.random() * 3000) + 1000;
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder = entityBuilder.setCharset(Charset.forName("UTF-8"));
        entityBuilder = entityBuilder.addTextBody("articleId", value + "");
        entityBuilder = entityBuilder.addTextBody("source", "web");
        entityBuilder = entityBuilder.addTextBody("redirectPath", "/page/replyList?articleId=" + value + "&page=1");
        // ������ɻظ�����(������ͬ���ݶ�γ���)
        String content = messages[(int) (Math.random() * messages.length)] + ","
                + messages2[(int) (Math.random() * messages2.length)] + ","
                + messages3[(int) (Math.random() * messages3.length)];
        @SuppressWarnings("deprecation")
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, "UTF-8");
        entityBuilder = entityBuilder.addPart("content", new StringBody(content, contentType));
        HttpEntity reqEntity = entityBuilder.build();
        httpPost.setEntity(reqEntity);
        CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
//        GetImage.close(httpResponse);
        httpResponse.close();
        
        logger.info("�ظ��ɹ�....");
    }
 
//    public static void main(String[] args) {
//        // �����쳣,����5��,���еĻ����Ǳ�������
//        int flag = 0;
//        while (flag++ < 5) {
//            try {
//                sendGet();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static void main(String[] args) {
        int  a = 24; 
        int  �� = 30;
        
        System.out.println("a=" + a); 
        System.out.println("a=" + ��); 
    }   
}