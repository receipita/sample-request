# レシピタリクエストサンプル (Java)
## 使用方法
本サンプルはgradleを使用します。
### レシート応募方法
1. 試したいレシート画像をご用意頂き、画像を置く ※「レシート画像について」参照
2. API設定ファイル(Config.java)に設定値を入力する ※「API設定について」参照
3. レシピタ応募APIサンプルの実行を行う
```java
// コンパイル
gradle compileJava
// 実行
gradle run
```
### レシート判定結果取得方法
1. API設定ファイル(Config.java)にレシピタ応募APIレスポンスのレシートIDを設定する ※「API設定について」参照
2. レシピタ判定結果取得APIサンプルの実行を行う
```java
// コンパイル
gradle compileJava
// 実行
gradle run
```
---
### レシート画像について
- プロジェクト直下のimageディレクトリにレシピタ解析を行いたいJPEG形式のレシート画像を置く
- この画像はレシピタ応募APIで使用します
---
### API設定について
API設定(Config.java)ファイルに設定値を入力下さい
#### 共通設定
- API_SWITCH
  - APIの切り替えに使用します
    - 応募APIの場合は以下の値を入れてください
      - REQUEST_RECEIPT_API
    - 結果取得APIの場合は以下の値を入れてください
      - GET_RESULT_RECEIPT_API
- URL
  - 弊社より発行したURLです
- BUSINESS_ID
  - 弊社より発行した企業毎に付与されるユニークなID
- SECRET_KEY
  - 弊社より発行した署名作成用の秘密鍵
- CAMPAIGN_ID
  - 弊社より発行したキャンペーンを識別するためのID
- BASIC_USER
  - 弊社より発行したBASIC認証のユーザーID
- BASIC_PASS
  - 弊社より発行したBASIC認証のパスワード
#### レシピタ応募API設定
- PURCHASE_VALUE
  - ユーザー申告数 (お客様側で任意の値を設定して下さい)
  - ※対象商品の総個数or総金額判定を行う際に使用する値
- FILE_NAME
  - 画像ファイル名
    - imageディレクトリに配置したファイル名をご入力下さい
#### レシピタ判定結果取得API設定 
- RECEIPT_ID
  - レシートID (レシピタ応募APIより取得する応募レシート識別用のID)を指定して下さい
---
## 使用するビルドツール
なければインストールして下さい
- Gradle
---
## 動作確認済み環境
- OS
-- AmazonLinux2
- Javaバージョン
-- Java8