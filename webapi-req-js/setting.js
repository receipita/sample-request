//////////////////////////////////////////////////////////////////////////////
// API共通設定値 ※以下、設定が必要です
//////////////////////////////////////////////////////////////////////////////
// URL (弊社より発行します)
//  例) "https://xxxxx/ReceiptCampaignMileage/"
exports.url = "";
// ビジネスID (企業毎に付与されるユニークなIDで, 弊社より発行します)
exports.businessId = "";
// 署名作成用の秘密鍵 (弊社より発行します)
exports.secretKey = "";
// キャンペーンID (キャンペーンを識別するためのIDで、弊社より発行します)
exports.campaignId = "";
// ベーシック認証 (弊社より発行します)
exports.basicUser = "";
exports.basicPass = "";

const time = Math.round((new Date()).getTime() / 1000);
exports.timestamp = time;

// リクエストID (お客様側で任意の値を設定して下さい)
//  半角英数 100 文字まで
exports.requestId = "testreqtest" + time;

//////////////////////////////////////////////////////////////////////////////
// レシピタ応募(requestReceipt)API設定値 ※以下、設定が必要です
//////////////////////////////////////////////////////////////////////////////
// ユーザー(応募)ID (お客様側で任意の値を設定して下さい)
// レシートデータとユーザーを紐づけるための任意のID
// 半角英数字 512 文字まで
exports.userId = "testuser" + time;
// ユーザー申告数 (お客様側で任意の値を設定して下さい)
//  ※対象商品の総個数or総金額判定を行う際に使用する値
exports.purchaseValue = "1"
// 送信したいレシート画像のファイル名(imageディレクトリに置く)
const receiptImgJpgFile = "testreceipt.jpg";
// レシート画像のファイルパス
exports.receiptImgJpgFilePath = "../image/" + receiptImgJpgFile;

//////////////////////////////////////////////////////////////////////////////
// レシピタ判定結果取得(getResultReceipt)API設定値 ※以下、設定が必要です
//////////////////////////////////////////////////////////////////////////////
// レシートID (レシピタ応募APIより取得する応募レシート識別用のID)を指定して下さい
exports.receiptId = "";
