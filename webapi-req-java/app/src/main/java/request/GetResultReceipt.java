package request;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * レシピタ結果取得API
 */
public class GetResultReceipt {

  /**
   * APIコール処理
   */
  public static void callApi() {
    URL url = createURL();

    HttpsURLConnection httpsURLConnection;

    try {
      httpsURLConnection = (HttpsURLConnection) url.openConnection();

      httpsURLConnection.setRequestMethod("GET");
      httpsURLConnection.setInstanceFollowRedirects(false);
      httpsURLConnection.setDoInput(true);
      httpsURLConnection.setDoOutput(true);
      httpsURLConnection.setUseCaches(false);
      httpsURLConnection.addRequestProperty("host", request.Config.URL);

      httpsURLConnection.addRequestProperty("Authorization", request.Auth.createBasicAuth());

    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    try {
      // APIコール
      httpsURLConnection.connect();

      final int statusCode = httpsURLConnection.getResponseCode();
      if (HttpsURLConnection.HTTP_OK != statusCode) {
        System.out.println("ERROR statusCode : " + statusCode);
      }
      int dataSize = httpsURLConnection.getContentLength();
      if (dataSize == 0) {
        System.out.println("No data received");
      }
      final InputStream inputStream = httpsURLConnection.getInputStream();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] bs = new byte[256];
      int size;
      while ((size = inputStream.read(bs)) != -1) {
        baos.write(bs, 0, size);
      }
      byte[] bodyByte = baos.toByteArray();
      final String dd = new String(bodyByte, StandardCharsets.UTF_8);
      inputStream.close();

      System.out.println(dd);

      httpsURLConnection.disconnect();

    } catch (IOException | NullPointerException e) {
      e.printStackTrace();

    } finally {
      httpsURLConnection.disconnect();
    }

  }

  /**
   * URL作成
   */
  private static URL createURL() {
    String strURL = request.Config.URL + "getResultReceipt"
      + "?businessid=" + request.Config.BUSINESS_ID
      + "&requestid=" + request.Config.REQUEST_ID
      + "&receiptid=" + request.Config.RECEIPT_ID
      + "&campaignid=" + request.Config.CAMPAIGN_ID
      + "&timestamp=" + request.Config.TIMESTAMP_STR
      + "&signature=" + request.Auth.createHmacSha256Hash()
      ;

    URL url = null;
    try {
      url = new URL(strURL);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return url;
  }

}
