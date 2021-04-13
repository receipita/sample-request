package request;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class Auth {
  /**
   * Basic認証作成
   */
  public static String createBasicAuth() {
    String basicAuth = request.Config.BASIC_USER + ":" + request.Config.BASIC_PASS;
    return
      "Basic "
      + Base64.getUrlEncoder().encodeToString(
          basicAuth.getBytes(StandardCharsets.UTF_8)
        );
  }

  /**
   * 署名作成
   */
  public static String createHmacSha256Hash() {
    final String HMAC_SHA256 = "HmacSHA256";

    String response = null;
    String stringToSign = request.Config.REQUEST_ID + request.Config.TIMESTAMP_STR;

    try {
      Mac mac;
      byte[] sha256_hash;
      String charsetName = System.getProperty("file.encoding");
      SecretKeySpec sk = new SecretKeySpec(
        request.Config.SECRET_KEY.getBytes(charsetName),
        HMAC_SHA256
      );
      mac = Mac.getInstance(HMAC_SHA256);
      mac.init(sk);
      sha256_hash = mac.doFinal(stringToSign.getBytes(charsetName));
      StringBuilder sb = new StringBuilder();

      for (byte sha256Hash : sha256_hash) {
        sb.append(
          Integer.toHexString(
            (sha256Hash >> 4) & 0x0F
          )
        );
        sb.append(
          Integer.toHexString(
            sha256Hash & 0x0F
          )
        );
      }

      byte[] base = Base64.getEncoder()
        .encode(sb.toString().getBytes(charsetName));
      response = new String(base, charsetName);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return response;
  }

}