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
SEED="$POST_REQUEST_ID$TIME_STAMP"
HASH_SIGNATURE=`echo -n ${SEED} | openssl dgst -sha256 -hmac ${SECRET} | awk '{print $2}'`
BASE64_API_SIGNATURE=`echo -n $HASH_SIGNATURE | base64 | tr -d '\n'`

# ベーシック認証作成
BASIC_AUTH=`echo -n "${BASIC_AUTH_USER_ID}:${BASIC_AUTH_PASSWORD}" | base64`

################################################################################
# APIリクエスト処理
################################################################################
API_PARAM="requestReceipt?businessid=${BUSINESS_ID}&requestid=${POST_REQUEST_ID}&timestamp=${TIME_STAMP}&signature=${BASE64_API_SIGNATURE}&user_id=${USER_ID}&campaign_id=${CAMPAIGN_ID}&purchase_value=${PURCHASE_VALUE}"

REQUEST_URL="${URL}${API_PARAM}"

echo -e "--- 以下の設定でレシピタ2応募APIへCurlコマンドでリクエストします ---\n"
echo "APIリクエストURL : ${REQUEST_URL}"
echo "ベーシック認証 : ${BASIC_AUTH}"
echo "画像ファイルパス : ${IMAGE_PATH}"

# レシピタ2応募APIへリクエスト
curl \
  --request POST ${REQUEST_URL} \
  --header "Authorization: Basic ${BASIC_AUTH}" \
  --form "image01=@${IMAGE_PATH}"

echo -e "\n\n--- リクエスト処理終了します ---"
