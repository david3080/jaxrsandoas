# RESTAPIとOAS作成デモアプリ

本レポジトリではSpringBootとJAX-RSによるRESTAPI開発とSpringdocを使ったOAS生成とオブジェクト指向言語とJSONスキーマの対応を見ていきます。

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
