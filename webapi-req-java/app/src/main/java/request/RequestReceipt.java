package request;

import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * レシピタ応募API
 */
public class RequestReceipt {
  static final private String BOUNDARY = "boundary";
  static final private String TWO_HYPHENS = "--";
  static final private String LINE_END = "\r\n";

  /**
   * APIコール処理
   */
  public static void callApi() {
    URL url = createURL();

    HttpsURLConnection httpsURLConnection;

    try {
      httpsURLConnection = (HttpsURLConnection) url.openConnection();

      httpsURLConnection.setRequestMethod("POST");
      httpsURLConnection.setInstanceFollowRedirects(false);
      httpsURLConnection.setDoInput(true);
      httpsURLConnection.setDoOutput(true);
      httpsURLConnection.setUseCaches(false);
      httpsURLConnection.addRequestProperty("host", request.Config.URL);

      //httpsURLConnection.addRequestProperty("Authorization", createBasicAuth());
      httpsURLConnection.addRequestProperty("Authorization", request.Auth.createBasicAuth());

      httpsURLConnection.addRequestProperty(
        "Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

      OutputStream outputStream = httpsURLConnection.getOutputStream();
      final DataOutputStream dataOutputStream
        = new DataOutputStream(outputStream);

      writeMultiPartFilesToReqBody(dataOutputStream);
      dataOutputStream.writeBytes(
        TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + LINE_END);
      dataOutputStream.flush();
      dataOutputStream.close();

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
    String strURL = request.Config.URL + "requestReceipt"
      + "?businessid=" + request.Config.BUSINESS_ID
      + "&requestid=" + request.Config.REQUEST_ID
      + "&user_id=" + request.Config.USER_ID
      + "&campaign_id=" + request.Config.CAMPAIGN_ID
      + "&timestamp=" + request.Config.TIMESTAMP_STR
      + "&signature=" + request.Auth.createHmacSha256Hash()
      + "&purchase_value=" + request.Config.PURCHASE_VALUE
      ;

    URL url = null;
    try {
      url = new URL(strURL);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return url;
  }

  /**
   * マルチパートの処理
   */
  private static void writeMultiPartFilesToReqBody(DataOutputStream outputStream) {
    try {
      File file = new File(request.Config.RECEIPT_IMG_FILE_PATH);
      InputStream in = new FileInputStream(file);

      outputStream.writeBytes(TWO_HYPHENS + BOUNDARY + LINE_END);
      outputStream.writeBytes(
        "Content-Disposition: form-data; name=\"image01\";  filename=\""
          + request.Config.FILE_NAME + "\"" + LINE_END
      );
      outputStream.writeBytes("Content-Type: image/jpeg" + LINE_END);
      outputStream.writeBytes(
        String.format("Content-Transfer-Encoding: binary%s", LINE_END)
      );
      outputStream.writeBytes(LINE_END);
      writeFileToStream(
        in,
        outputStream
      );
      outputStream.writeBytes(LINE_END);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 画像をストリームに書き込み
   */
  private static void writeFileToStream(
    InputStream in,
    OutputStream out
  ) throws IOException {
    byte[] buf = new byte[1024];

    int read;
    try {
      while ((read = in.read(buf)) > 0) {
        out.write(buf, 0, read);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (in != null) {
      in.close();
    }
  }

}
