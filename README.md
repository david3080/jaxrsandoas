# RESTAPIとOAS作成デモアプリ

本レポジトリではSpringBootとJAX-RSによるRESTAPI開発とSpringdocを使ったOAS生成とオブジェクト指向言語とJSONスキーマの対応を見ていきます。

## 履歴

- 2024/05/15: 初版
- 2024/05/16: Spring Webを使ったREST
  API実装とOAS生成。アノテーションには「io.swagger.v3.oas.annotations」を使用。

## TODO

- PetStoreのAPIコードを移植し、生成されるOASが正しく生成されることを確認。
- SwaggerHubでOASからサーバの雛形を生成し、[Generatio Gapパターン](https://zenn.dev/t_kuroiwa/articles/9676eb99b72cdc)を使って実装が可能なことを示す。
- Kafkaを使ったマイクロサービスを実装し、AsyncAPIについても同じことができるか確認。この場合もSAGAパターンなどの実装は抽象クラスで用意しておき、AsyncAPIで生成されたテンプレートとうまく組み合わせて実装できるかを確認。

## なぜSpringBootとJAX-RSを選ぶのか？

Java言語でRESTAPIを開発する場合、JAX-RS単独のライブラリを使うケースとSpringBootMVCだけを使うケースと両方を組み合わせるケースが考えられますが、SpringBootMVCだけだと自由なAPI実装が可能でありRESTAPIのルールに従わせることができません。一方、JAX-RS単独のライブラリを使うケースだとSpringBootの豊富な機能を活用することができないため、それらを組み合わせSpringBootとJAX-RSを組み合わせて実装することを選びました。

※ 参考:
[Difference between JAX-RS and Spring Rest](https://stackoverflow.com/questions/42944777/difference-between-jax-rs-and-spring-rest)

## どのJAX-RSの実装を使うのか？

JAX-RSはJavaEEにおけるRESTAPI開発における標準です。この標準に従った参照実装には様々な実装があります。その実装のうち主要なものには「Glassfish
Jersey」、「Apache
CXF」、「RESTEasy」、「Restlet」があり、SpringBootと相性がよい実装としては「Apache
CXF」であるため、これを使うことにします。

## OAS3作成には何を使うか？

SpringBootではSpringdocを使ってOAS3を生成することができます。

## SpringBootプロジェクトの生成

SpringBootにはSpring
CLIが提供されており、これを使ってプロジェクトを生成することができます。

```
$ spring init --package-name sonrisa.oas --build=maven -d web -n MyApplication jaxrsandoas
```

- 「--package-name」: パッケージ名を指定します。
- 「--build=maven」: Mavenビルドシステムを使用します。
- 「-d web」: Web用パッケージを利用します。
- 「-n MyApplication」: アプリケーション名を指定します。
- 「jaxrsandoas」:
  プロジェクト名を指定します。この名前のフォルダが作成されます。

## パッケージ「SpringDoc OpenAPI Starter WebMVC UI」の追加

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
