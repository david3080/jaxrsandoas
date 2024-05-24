# RESTAPIとOAS作成デモアプリ

本レポジトリではSpringBootとJAX-RSによるRESTAPI開発とSpringdocを使ったOAS生成とオブジェクト指向言語とJSONスキーマの対応を見ていきます。

## 履歴とTODO

### 履歴

- 2024/05/15: 初版
- 2024/05/16: Spring Webを使ったREST
  API実装とOAS生成。アノテーションには「io.swagger.v3.oas.annotations」を使用。

### TODO

- Apache CXFでのRESTAPI実装。
- PetStoreのAPIコードを移植し、生成されるOASが正しく生成されることを確認。
- SwaggerHubでOASからサーバの雛形を生成し、[Generatio Gapパターン](https://zenn.dev/t_kuroiwa/articles/9676eb99b72cdc)を使って実装が可能なことを示す。
- Kafkaを使ったマイクロサービスを実装し、AsyncAPIについても同じことができるか確認。この場合もSAGAパターンなどの実装は抽象クラスで用意しておき、AsyncAPIで生成されたテンプレートとうまく組み合わせて実装できるかを確認。
- JSONスキーマの理解とJSONスキーマからのJavaクラス生成の限界の理解
- JSONスキーマのバリデーションの理解とSpectralでのバリデーションの理解、それぞれ何ができ、何ができないかの端的な説明
- Java実装について説明を記載する

## 解説

### なぜSpringBootとJAX-RSを選ぶのか？

Java言語でRESTAPIを開発する場合、JAX-RS単独のライブラリを使うケースとSpringBootMVCだけを使うケースと両方を組み合わせるケースが考えられますが、SpringBootMVCだけだと自由なAPI実装が可能でありRESTAPIのルールに従わせることができません。一方、JAX-RS単独のライブラリを使うケースだとSpringBootの豊富な機能を活用することができないため、それらを組み合わせSpringBootとJAX-RSを組み合わせて実装することを選びました。

※ 参考:
[Difference between JAX-RS and Spring Rest](https://stackoverflow.com/questions/42944777/difference-between-jax-rs-and-spring-rest)

### どのJAX-RSの実装を使うのか？

JAX-RSはJavaEEにおけるRESTAPI開発における標準です。この標準に従った参照実装には様々な実装があります。その実装のうち主要なものには「Glassfish
Jersey」、「Apache CXF」、「RESTEasy」、「Restlet」があり、Spring
CLIがサポートするライブラリである「Glassfish Jersey」を使うことにします。

### OAS3作成には何を使うか？

SpringBootではSpringdocを使ってOAS3を生成することができます。

### SpringBootプロジェクトの生成

SpringBootにはSpring
CLIが提供されており、これを使ってプロジェクトを生成することができます。

```
$ spring init --package-name sonrisa.oas --build=maven -d web,jersey -n MyApplication jaxrsandoas
```

- 「--package-name」: パッケージ名を指定します。
- 「--build=maven」: Mavenビルドシステムを使用します。
- 「-d web」: Web用パッケージを利用します。
- 「-n MyApplication」: アプリケーション名を指定します。
- 「jaxrsandoas」:
  プロジェクト名を指定します。この名前のフォルダが作成されます。

### パッケージ「SpringDoc OpenAPI Starter WebMVC UI」の追加

Springのコードにアノテーションを追加してOASドキュメントを生成するMavenのパッケージ「SpringDoc
OpenAPI Starter WebMVC UI」をpom.xmlに追加します。

```
<!-- Springdocの追加 -->
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	<version>2.5.0</version>
</dependency>
```

### API実装のしくみ

TODO:
MyApplication.javaやXXController.java、モデルクラス（レコードで実装）やそれらへのアノテーション方法とOASとの関係などを記載する。

## JSONスキーマについて

### JSONスキーマの学習

- [JSONスキーマの理解](https://json-schema.org/understanding-json-schema)
- [JSONスキーマのバリデーションサイト](https://jsonschema.net/)

## JavaコードとOAS/JSONスキーマの関係と注意点

TODO: JavaコードとOAS/JSONスキーマの関係と注意点を記載する。

## 参考サイト

- [springdoc-openapi メモ書き](https://ksby.hatenablog.com/entry/2021/03/25/072126)
- [Javaにおける多重継承問題](https://javarush.com/ja/groups/posts/ja.731.java-)
- [Java Records in Spring Boot Rest API](https://dev.to/psideris89/java-14-records-in-spring-boot-rest-api-n29)
- [Spring WebでのAPI実装](https://qiita.com/kanfutrooper/items/b2c77dcc5902eabff3ac)
- [JSON Schema Validator using Java SpringBoot](https://medium.com/@mohommad.belal/json-schema-validator-using-java-springboot-667ed42480d5)
