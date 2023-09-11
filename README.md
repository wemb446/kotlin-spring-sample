# kotlin-spring-sample
Sample App for Kotlin&amp;SpringBoot3

## アプリケーションの起動
・まずは mongobd 等を docker で立ち上げる
```shell
# docker-compose による mongodb, redis の起動
$ docker-compose up -d
```

・docker を立ち上げた後は IntelliJ とかからアプリ起動してください

## API一覧
user の簡単なCRUD機能のみを実装

・POST /user/list

・POST /user/detail

・POST /user/new

・POST /user/update

・POST /user/delete

## 基本的な処理の流れ
- Controller（src/main/kotlin/com/example/demo/controller/user/UserController）
  - ルーティングおよびリクエストの受付を担当

↓

- Service（src/main/kotlin/com/example/demo/service/user/UserService）
  - ビジネスロジックを担当
 
↓

- Repository（src/main/kotlin/com/example/demo/repository/sample/USER/UserMongodbRepository）
  - DB通信を担当

