<?php
# レシート応募(getResultReceipt)API リクエストサンプル

# リクエストのタイムスタンプ生成
$timestamp = time();

################################################################################
# API設定読み込み ※setting.phpファイルのAPI設定が必要です
################################################################################
include("./setting.php");

################################################################################
# APIリクエストの処理
################################################################################
$basic_credentials = base64_encode("$basic_user:$basic_pass");
$api_signature = getApiSignature($get_request_id, $timestamp, $secret);

$curl = curl_init();
curl_setopt_array($curl, array(
  CURLOPT_URL => $url.'/getResultReceipt?businessid='.$business_id.'&requestid='.$get_request_id.'&receiptid='.$receipt_id.'&campaignid='.$campaign_id.'&timestamp='.$timestamp.'&signature='.$api_signature,
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => '',
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 0,
  CURLOPT_FOLLOWLOCATION => true,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => 'GET',
  CURLOPT_HTTPHEADER => array(
    'Authorization: Basic '.$basic_credentials
  ),
));

// APIリクエスト
$response = curl_exec($curl);

curl_close($curl);
echo "レシート応募(getResultReceipt)APIのレスポンスJSON : ".$response;

/**
 * API署名の作成
 */
function getApiSignature($request_id, $timestamp, $secret) {
  $algo = 'sha256';
  $data = $request_id.$timestamp;
  $hash = hash_hmac($algo, $data, $secret);
  $encode = base64_encode($hash);
  return $encode;
}

?>
