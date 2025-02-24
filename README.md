### **ShoeStore**

## **Описание проекта**
ShoeStore – это REST-сервис на Spring Boot, предоставляющий API для работы с товарами (кроссовками). Сервис использует базу данных PostgreSQL и предоставляет функциональность для добавления, получения и фильтрации товаров.
Функционал:
- Запуск локального REST API.
- GET-запрос с Query Parameters для фильтрации товаров (кроссовок).
- GET-запрос с Path Parameters для поиска товара по ID.
- Работа с базой данных PostgreSQL.
- Добавление и получение товаров через REST API.
- Подключение CheckStyle для кодстайла.
- Формат ответа – JSON.


## **Задание**
1. **Создать и запустить локально REST-сервис на Java (Spring Boot + PostgreSQL + Maven/Gradle).
2. **Добавить GET эндпоинт с Query Parameters для фильтрации кроссовок.
3. **Добавить GET эндпоинт с Path Parameters для поиска товара по ID.
4. **Настроить CheckStyle и исправить ошибки.
5. **Формат ответа – JSON.

## **Установка и запуск**
### **1. Клонирование репозитория**
```sh
git clone https://github.com/your-repo/shoestore.git
cd shoestore

```

### **2. Сборка и запуск приложения**
С использованием **Maven**:
```sh
mvn clean install
mvn spring-boot:run

```
С использованием **Gradle**:
```sh
gradle build
gradle bootRun

```

## **Доступные эндпоинты**
### **Добавление нового товара (POST /shoes)**
```http
POST /shoes
Content-Type: application/json

```
Пример ответа:
```json
[
 {
  "id": 1,
  "brand": "Nike",
  "model": "Air Zoom Pegasus",
  "price": 120.99,
  "size": 42,
  "city": "New York",
  "author": "Author A"
}

]
```

### **Получение списка товаров с фильтрацией (Query Parameters)**
```http
GET /shoes?brand=Nike&model=Air Zoom Pegasus&price=120.99&size=42&city=New York&author=Author A

```
Пример ответа:
```json
[
  {
    "id": 1,
    "brand": "Nike",
    "model": "Air Zoom Pegasus",
    "price": 120.99,
    "size": 42,
    "city": "New York",
    "author": "Author A"
  }
]

```

### **Настройка CheckStyle**

#### **Maven**
Добавьте в `pom.xml`:
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-checkstyle-plugin</artifactId>
  <version>3.1.0</version>
  <executions>
    <execution>
      <phase>validate</phase>
      <goals>
        <goal>check</goal>
      </goals>
    </execution>
  </executions>
</plugin>

```
Запустите проверку:
```sh
mvn checkstyle:check

```

#### **Gradle**
Добавьте в `build.gradle`:
```groovy
plugins {
  id 'checkstyle'
}

checkstyle {
  toolVersion = '8.45'
}

tasks.withType(Checkstyle).configureEach {
  reports {
    xml.required.set(true)
    html.required.set(true)
  }
}

tasks.check.dependsOn tasks.checkstyleMain

```

## **Требования**
- Java 17+
- Spring Boot 3+
- Maven/Gradle

## **Авторы**
medvedevsc2
