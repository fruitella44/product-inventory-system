## CRUD - product inventory

> * Склад хранения товаров
> * Функционал: добавить товар, удалить товар, внести правки
> * Реализована пагинация с выбором нужной страницы и ссылками на следующую/предыдущую
> * Команда для шифрования пароля c помощью библиотеки Jasypt:
>> `mvn jasypt:encrypt-value "-Djasypt.encryptor.password=your_secrert_key" "-Djasypt.plugin.value=your_password"`
> * Команда для расшифровки пароля с помощью библиотеки Jasypt:
>> `mvn jasypt:decrypt-value "-Djasypt.encryptor.password=your_secrert_key" "-Djasypt.plugin.value=your_encrypted_password"`
> * Добавление данных для запуска приложение с аргументами командной строки:
>> `VM options: Djasypt-.encryptor.password=your_secrert_key`
* Структура программы:
* ![model](https://i.imgur.com/du9LLzS.png)
* RestController, контент возврата:
* ![rest](https://i.imgur.com/NhGF92m.png)
* Уровень покрытия тестами: 86%
* ![jacoco](https://i.imgur.com/psvduDL.png)
* Пример того как это выглядит:
* ![jacoco](https://i.imgur.com/XHUs64V.png)
------------------------------------
* Для создания и сборки проекта использовались следующие технологии:
  JDK-17, Maven, Spring-boot, Hibernate-5, PostgreSQL-13, Log4j2, Bootstrap-5, Thymeleaf, Lombok, Jasypt, JUnit5, Jacoco
------------------------------------
