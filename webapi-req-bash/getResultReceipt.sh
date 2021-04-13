#!/bin/bash
# レシート応募(getResultReceipt)API リクエストサンプル

# リクエストのタイムスタンプ生成
TIME_STAMP=`date "+%s"`

################################################################################
# API設定読み込み ※setting.shファイルのAPI設定が必要です
################################################################################
source ./setting.sh

################################################################################
# API認証情報作成処理
################################################################################
# WebAPI署名作成
SEED="$GET_REQUEST_ID$TIME_STAMP"
HASH_SIGNATURE=`echo -n ${SEED} | openssl dgst -sha256 -hmac ${SECRET} | awk '{print $2}'`
BASE64_API_SIGNATURE=`echo -n $HASH_SIGNATURE | base64 | tr -d '\n'`

# ベーシック認証作成
BASIC_AUTH=`echo -n "${BASIC_AUTH_USER_ID}:${BASIC_AUTH_PASSWORD}" | base64`

################################################################################
# APIリクエスト処理
################################################################################
API_PARAM="getResultReceipt?businessid=${BUSINESS_ID}&requestid=${GET_REQUEST_ID}&timestamp=${TIME_STAMP}&signature=${BASE64_API_SIGNATURE}&campaignid=${CAMPAIGN_ID}&receiptid=${RECEIPT_ID}"
REQUEST_URL="${URL}${API_PARAM}"

echo -e "--- 以下の設定でレシピタ2結果取得APIへCurlコマンドでリクエストします ---\n"
echo "APIリクエストURL : ${REQUEST_URL}"
echo "ベーシック認証 : ${BASIC_AUTH}"

# レシピタ2結果取得APIへリクエスト
curl \
  --request GET ${REQUEST_URL} \
  --header "Authorization: Basic ${BASIC_AUTH}" \

echo -e "\n\n--- リクエスト処理終了します ---"
