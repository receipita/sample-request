package request;

/**
 * API設定
 */
public class Config {
  public static final int REQUEST_RECEIPT_API = 0;
  public static final int GET_RESULT_RECEIPT_API = 1;

  /**
   * コールするAPIの選択
   *   0 ... レシピタ応募API (REQUEST_RECEIPT_API)
   *   1 ... レシピタ判定結果取得API (GET_RESULT_RECEIPT_API)
   */
  //public static final int API_SWITCH = REQUEST_RECEIPT_API;
  public static final int API_SWITCH = GET_RESULT_RECEIPT_API;

  /****************************************************************************/
  /* レシピタAPI共通設定値 ※以下、設定が必要です                                 +/
  /****************************************************************************/
  // URL (弊社より発行します)
  // 例) "https://xxxxx/ReceiptCampaignMileage/"
  public static final String URL = "";
  // ビジネスID (企業毎に付与されるユニークなIDで, 弊社より発行します)
  public static final String BUSINESS_ID = "";
  // 署名作成用の秘密鍵 (弊社より発行します)
  public static final String SECRET_KEY = "";
  // キャンペーンID (キャンペーンを識別するためのIDで、弊社より発行します)
  public static final String CAMPAIGN_ID = "";
  // ベーシック認証 (弊社より発行します)
  public static final String BASIC_USER = "";
  public static final String BASIC_PASS = "";

  public static final long TIMESTAMP = System.currentTimeMillis() / 1000L;
  public static final String TIMESTAMP_STR = String.valueOf(TIMESTAMP);
  public static final String REQUEST_ID = "testreqtest" + TIMESTAMP_STR;

  /****************************************************************************/
  /* レシピタ応募(RequestReceipt)API設定値                                      +/
  /****************************************************************************/
  // 送信したいレシート画像のファイル名(imageディレクトリに置く)
  public static final String FILE_NAME = "testreceipt.jpg";
  // レシート画像のファイルパス
  public static final String RECEIPT_IMG_FILE_PATH = "../../image/" + FILE_NAME;

  // ユーザー申告数 (お客様側で任意の値を設定して下さい)
  //  ※対象商品の総個数or総金額判定を行う際に使用する値
  public static final String PURCHASE_VALUE = "1";

  // ユーザー(応募)ID (お客様側で任意の値を設定して下さい)
  //  レシートデータとユーザーを紐づけるための任意のID
  //  半角英数字 512 文字まで
  public static final String USER_ID = "testuser" + TIMESTAMP_STR;

  /****************************************************************************/
  /* レシピタ判定結果取得(GetResultReceipt)API設定値                            +/
  /****************************************************************************/
  // レシートID (レシピタ応募APIより取得する応募レシート識別用のID)を指定して下さい
  public static final String RECEIPT_ID = "";

}