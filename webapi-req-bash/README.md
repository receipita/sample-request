# レシピタリクエストサンプル (Bash)
## 使用方法
### レシート応募方法
1. 試したいレシート画像をご用意頂き、画像を置く ※「レシート画像について」参照
2. API設定ファイル(setting.js)に設定値を入力する ※「API設定について」参照
3. レシピタ応募APIサンプルの実行を行う
```bash
sh requestReceipt.sh
```
### レシート判定結果取得方法
1. API設定ファイル(setting.js)にレシピタ応募APIレスポンスのレシートIDを設定する ※「API設定について」参照
2. レシピタ判定結果取得APIサンプルの実行を行う
```bash
sh getResultReceipt.sh
```
---
### レシート画像について
- プロジェクト直下のimageディレクトリにレシピタ解析を行いたいJPEG形式のレシート画像を置く
- この画像はレシピタ応募APIで使用します
---
### API設定について
API設定(setting.sh)ファイルに設定値を入力下さい
#### 共通設定
- BUSINESS_ID
  - 弊社より発行した企業毎に付与されるユニークなID
- SECRET
  - 弊社より発行した署名作成用の秘密鍵
- CAMPAIGN_ID
  - 弊社より発行したキャンペーンを識別するためのID
- BASIC_AUTH_USER_ID
  - 弊社より発行したBASIC認証のユーザーID
- BASIC_AUTH_PASSWORD
  - 弊社より発行したBASIC認証のパスワード
#### レシピタ応募API設定
- PURCHASE_VALUE
  - ユーザー申告数 (お客様側で任意の値を設定して下さい)
  - ※対象商品の総個数or総金額判定を行う際に使用する値
- IMAGE_FILE_NAME
  - 画像ファイル名
    - imageディレクトリに配置したファイル名をご入力下さい
#### レシピタ判定結果取得API設定 
- RECEIPT_ID
  - レシートID (レシピタ応募APIより取得する応募レシート識別用のID)を指定して下さい
---
## 使用するコマンド
以下コマンドがなければyumなどでインストールして下さい
- awk
- base64
- curl
- date
- echo
- openssl
---
## 動作確認済み環境
- OS
-- AmazonLinux2
- Bashバージョン
-- 4.2.46