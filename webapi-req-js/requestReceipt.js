// レシピタ応募(requestReceipt)APIのリクエストサンプル

const setting = require('./setting.js');

const axios = require('axios');
const FormData = require('form-data');
const CryptoJS = require('crypto-js');

const fs = require('fs');

console.log("----- レシピタ応募(requestReceipt)APIのリクエストサンプル Start -----");

/**********************************************************************/
/* 設定値 ※setting.jsで設定の必要あり                                  */
/**********************************************************************/
const httpMethod = "POST";
const url = setting.url;
const businessId = setting.businessId;
const timestamp = setting.timestamp;
const requestId = setting.requestId;
const userId = setting.userId;
const campaignId = setting.campaignId;
const secretKey = setting.secretKey;
const basicUser = setting.basicUser;
const basicPass = setting.basicPass;
const purchaseValue = setting.purchaseValue;
const receiptImgJpgFilePath = setting.receiptImgJpgFilePath;

/**********************************************************************/
/* URLと通信の設定                                                     */
/**********************************************************************/
function existReceiptImgJpgFile(receiptImgJpgFilePath) {
  let isExist = false;
  try {
    fs.statSync(receiptImgJpgFilePath);
    isExist = true;
  } catch(err) {
    isExist = false;
  }
  return isExist;
}

if(!existReceiptImgJpgFile(receiptImgJpgFilePath)) {
  console.log("レシート画像ファイルがないか, 設定したパスに誤りがあります。" + receiptImgJpgFilePath);
  process.exit(-1);
}

/**
 * レシピタWebAPI署名の作成
 *
 * @param requestId
 * @param secretKey
 * @param timeStamp
 *
 * @return signature
 */
const createApiSign = (secretKey, timestamp, requestId) => {
  const payload = requestId + timestamp;
  const hash = CryptoJS.HmacSHA256(payload, secretKey);
  const hashInBase64 = new Buffer.from(hash.toString()).toString('base64');
  return hashInBase64;
};

// URLの作成
const apiUrl =
  url + 'requestReceipt'
  + '?businessid=' + businessId
  + '&requestid=' + requestId
  + '&user_id=' + userId
  + '&campaign_id=' + campaignId
  + '&timestamp=' + timestamp
  + '&signature=' + createApiSign(secretKey, timestamp, requestId)
  + '&purchase_value=' + purchaseValue
  ;
console.log("リクエストURL : ");
console.log(apiUrl);

/**
 * Basic認証の作成
 *
 * @param basicUser
 * @param basicPass
 *
 * @return basicAuth
 */
const createBasicAuth = (basicUser, basicPass) => {
  return new Buffer
              .from(basicUser + ':' + basicPass)
              .toString('base64');
}

// Basic認証の作成
const basicAuthForHeader = 'Basic ' + createBasicAuth(basicUser, basicPass);

let data = new FormData();
data.append(
  'image01',
  fs.createReadStream(receiptImgJpgFilePath));

const config = {
  method: httpMethod,
  url: apiUrl,
  headers: {
    'Authorization': basicAuthForHeader,
    ...data.getHeaders()
  },
  data : data
};

/**********************************************************************/
/* レシピタ応募(requestReceipt)APIにリクエスト                          */
/**********************************************************************/
axios(config)
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});
