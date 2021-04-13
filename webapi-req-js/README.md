# レシピタリクエストサンプル (Javascript : Node.js)
## 使用方法
### レシート応募方法
1. 試したいレシート画像をご用意頂き、画像を置く ※「レシート画像について」参照
2. API設定ファイル(setting.js)に設定値を入力する ※「API設定について」参照
3. レシピタ応募APIサンプルの実行を行う
```javascript
node requestReceipt.js
```
### レシート判定結果取得方法
1. API設定ファイル(setting.js)にレシピタ応募APIレスポンスのレシートIDを設定する ※「API設定について」参照
2. レシピタ判定結果取得APIサンプルの実行を行う
```javascript
node getResultReceipt.js
```
---
### レシート画像について
- プロジェクト直下のimageディレクトリにレシピタ解析を行いたいJPEG形式のレシート画像を置く
- この画像はレシピタ応募APIで使用します
---
### API設定について
API設定(setting.js)ファイルに設定値を入力下さい
#### 共通設定
- url
  - 弊社より発行したURL
- businessId
  - 弊社より発行した企業毎に付与されるユニークなID
- secretKey
  - 弊社より発行した署名作成用の秘密鍵
- campaignId
  - 弊社より発行したキャンペーンを識別するためのID
- basicUser
  - 弊社より発行したBASIC認証のユーザーID
- basicPass
  - 弊社より発行したBASIC認証のパスワード
#### レシピタ応募API設定
- purchaseValue
  - ユーザー申告数 (お客様側で任意の値を設定して下さい)
  - ※対象商品の総個数or総金額判定を行う際に使用する値
- receiptImgJpgFile
  - 画像ファイル名
    - imageディレクトリに配置したファイル名をご入力下さい
#### レシピタ判定結果取得API設定 
- receiptId
  - レシートID (レシピタ応募APIより取得する応募レシート識別用のID)を指定して下さい
---
## 使用するライブラリ
以下なければnpmなどでインストールして下さい
- crypto-js 4.0.0
  - https://github.com/brix/crypto-js
  - npm install crypto-js
- axios 0.21.1
  - https://github.com/axios/axios
  - npm install axios
- form-data 4.0.0
  - https://github.com/form-data/form-data
  - npm install form-data
---
## 動作確認済み環境
- OS
-- AmazonLinux2
- Node.jsバージョン
-- 14.16