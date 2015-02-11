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
* @version 1.0  2015-2-8 上午1:01:00 
*/
public class ForRenRen {
    private static final Logger logger = Logger.getLogger(ForRenRen.class);
    private static final String[] messages = new String[] { "大家好", "人人影视走你", "人人美剧一二三", "人人我来了", "今天", "TMD.......",
            "good job", "大王派我来巡山", "小伙们,go go go", "对不起", "好想睡觉啊", "睡不着", "一个人过的第九年", "纸牌屋要出来了", "今年还能不能看到冰与火之歌",
            "怎么下载", "有电视机" };
    private static final String[] messages2 = new String[] { "今天是个好日子", "想说点什么", "看美剧", "乌合之众们", "哈里波特大", "伊丽沙白素珍",
            "海贼王的男人", "小丽", "好日子", "快过年了", "好晚了现在", "这是第几次了", "Time after Time", "大王" };
    private static final String[] messages3 = new String[] { "我爱你!!", "加油!!", "振作起来啊!!", "不给力啊!!", "啊啊啊!!", "好难受!!!",
            "不行啊这样!!", "不想这样了.....", "HELP!!!!", "我也不想这样啊", "积分怎么不动了?", "我的信心快用光了,你们倒是给力点啊", "刷积分真尼玛坑", "再过两天就过年回家了" };
 
    private static void sendGet() throws IOException, InterruptedException {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            String path = "http://www.rrmj.tv/appUser/appLogin";
            // 提交 登录
            HttpPost httpPost = new HttpPost(path);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("loginName", "lzz_1985@163.com"));
            nvps.add(new BasicNameValuePair("pwd", "miszhong123"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
            logger.info("成功登录....");
//            GetImage.close(httpResponse);
            httpResponse.close();
            // 每隔10秒灌水
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
        // 在ID 1000到3000之间随机找个贴子灌水
        int value = (int) (Math.random() * 3000) + 1000;
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder = entityBuilder.setCharset(Charset.forName("UTF-8"));
        entityBuilder = entityBuilder.addTextBody("articleId", value + "");
        entityBuilder = entityBuilder.addTextBody("source", "web");
        entityBuilder = entityBuilder.addTextBody("redirectPath", "/page/replyList?articleId=" + value + "&page=1");
        // 随机生成回复内容(避免相同内容多次出现)
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
        
        logger.info("回复成功....");
    }
 
//    public static void main(String[] args) {
//        // 出现异常,尝试5次,不行的话就是被禁掉了
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
        int  а = 30;
        
        System.out.println("a=" + a); 
        System.out.println("a=" + а); 
    }   
}