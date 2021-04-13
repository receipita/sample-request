// レシート判定結果取得(getResultReceipt)APIのリクエストサンプル
//
const setting = require('./setting.js');

const axios = require('axios');
const CryptoJS = require('crypto-js');

console.log("----- レシート判定結果取得(getResultReceipt)APIのリクエストサンプル Start -----");

/**********************************************************************/
/* 設定値 ※setting.jsで設定の必要あり                                  */
/**********************************************************************/
const httpMethod = 'GET';
const url = setting.url;
const businessId = setting.businessId;
const timestamp = setting.timestamp;
const requestId = setting.requestId;
const receiptId = setting.receiptId;
const campaignId = setting.campaignId;
const secretKey = setting.secretKey;
const basicUser = setting.basicUser;
const basicPass = setting.basicPass;


/**********************************************************************/
/* URLと通信の設定                                                     */
/**********************************************************************/
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
    url + 'getResultReceipt'
  + '?businessid=' + businessId
  + '&requestid=' + requestId
  + '&receiptid=' + receiptId
  + '&campaignid=' + campaignId
  + '&timestamp=' + timestamp
  + '&signature=' + createApiSign(secretKey, timestamp, requestId)
  ;
console.log("リクエストURL : ");
console.log(apiUrl);

/**
 * Basic認証の作成
 *
 * @param basicUser
 * @param basicPath
 *
 * @return basicAuth
 */
const createBasicAuth = (basicUser, basicPath) => {
  return new Buffer
              .from(basicUser + ':' + basicPass)
              .toString('base64');
}

// Basic認証の作成
const basicAuthForHeader = 'Basic ' + createBasicAuth(basicUser, basicPass);

// 通信の設定
const config = {
  method: httpMethod,
  url: apiUrl,
  headers: {
    'Authorization': basicAuthForHeader
  }
};


/**********************************************************************/
/* レシート判定結果取得(getResultReceipt)APIにリクエスト                 */
/**********************************************************************/
axios(config)
.then(function (response) {
  console.log(JSON.stringify(response.data));
})
.catch(function (error) {
  console.log(error);
});