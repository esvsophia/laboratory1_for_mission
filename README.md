# 🧙‍♂️ Анализатор миссий магов

![Java](https://img.shields.io/badge/Java-Swing-orange)
![Maven](https://img.shields.io/badge/build-Maven-C71A36)
![License](https://img.shields.io/badge/license-Apache%202.0-blue)

Десктопное приложение на Java (Swing) для чтения и анализа отчетов о миссиях магов. Проект вдохновлен вселенной Jujutsu Kaisen («Магическая битва») и предоставляет удобный графический интерфейс для просмотра данных о миссиях, проклятиях, участниках и примененных техниках.

## ✨ Возможности
*   **Мультиформатность:** Поддержка загрузки отчетов в форматах `.json`, `.xml` и `.txt`.
*   **Удобный интерфейс:** Графическое окно с возможностью выбора файла через системный проводник.
*   **Детальный парсинг:** Извлечение информации о локации, бюджете ущерба, уровне угрозы проклятия, рангах магов и уроне от конкретных техник.

## 🛠 Технологии
*   **Java 8+** (UI реализован на базе встроенной библиотеки **Swing**)
*   **Maven** — система сборки проекта и управления зависимостями
*   **Jackson (`com.fasterxml.jackson`)** — для парсинга JSON-файлов
*   **XML DOM (`javax.xml.parsers`)** — для чтения XML-документов

---

## 📁 Структура проекта
```text
├── src/main/java/org/example/
│   ├── Curse.java        
│   ├── GUI.java         
│   ├── Main.java        
│   ├── Mission.java     
│   ├── MissionReader.java  
│   ├── Sorcerer.java      
│   └── Technique.java   
├── Данные о миссиях/     
├── pom.xml                
└── README.md             
```

---


## 🚀 Установка и запуск

Поскольку проект использует Maven для управления внешними библиотеками (Jackson), перед первым запуском необходимо собрать зависимости.

1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/esvsophia/mission-analyzer.git
   cd mission-analyzer
   ```

2. Скачайте зависимости и соберите проект:
   ```bash
   mvn clean install
   ```

3. Запустите приложение через главный класс:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```
*(Также проект можно легко открыть и запустить прямо из вашей IDE, например IntelliJ IDEA, просто запустив метод `main` в классе `Main`).*

## 📄 Примеры поддерживаемого данных 
1. json file
```json
{
  "missionId": "M-2024-017",
  "date": "2024-10-12",
  "location": "Токио, район Сибуя",
  "outcome": "SUCCESS",
  "damageCost": 1200000,
  "curse": {
    "name": "Проклятие подземного перехода",
    "threatLevel": "HIGH"
  },
  "sorcerers": [
    { "name": "Итадори Юдзи", "rank": "GRADE_1" },
    { "name": "Фушигуро Мэгуми", "rank": "GRADE_2" }
  ],
  "techniques": [
    { "name": "Черная вспышка", "type": "INNATE", "owner": "Итадори Юдзи", "damage": 500000 },
    { "name": "Техника десяти теней", "type": "SHIKIGAMI", "owner": "Фушигуро Мэгуми", "damage": 700000 }
  ]
}
```
2. xml file
```xml
<mission>
    <missionId>M-2024-017</missionId>
    <date>2024-10-12</date>
    <location>Токио, район Сибуя</location>
    <outcome>SUCCESS</outcome>
    <damageCost>1200000</damageCost>

    <curse>
        <name>Проклятие подземного перехода</name>
        <threatLevel>HIGH</threatLevel>
    </curse>

    <sorcerers>
        <sorcerer>
            <name>Итадори Юдзи</name>
            <rank>GRADE_1</rank>
        </sorcerer>
        <sorcerer>
            <name>Фушигуро Мэгуми</name>
            <rank>GRADE_2</rank>
        </sorcerer>
    </sorcerers>

    <techniques>
        <technique>
            <name>Черная вспышка</name>
            <type>INNATE</type>
            <owner>Итадори Юдзи</owner>
            <damage>500000</damage>
        </technique>
        <technique>
            <name>Техника десяти теней</name>
            <type>SHIKIGAMI</type>
            <owner>Фушигуро Мэгуми</owner>
            <damage>700000</damage>
        </technique>
    </techniques>
</mission>
```
3. txt file
```
missionId: M-2024-017
date: 2024-10-12
location: Токио, район Сибуя
outcome: SUCCESS
damageCost: 1200000

curse.name: Проклятие подземного перехода
curse.threatLevel: HIGH

sorcerer[0].name: Итадори Юдзи
sorcerer[0].rank: GRADE_1

sorcerer[1].name: Фушигуро Мэгуми
sorcerer[1].rank: GRADE_2

technique[0].name: Черная вспышка
technique[0].type: INNATE
technique[0].owner: Итадори Юдзи
technique[0].damage: 500000

technique[1].name: Техника десяти теней
technique[1].type: SHIKIGAMI
technique[1].owner: Фушигуро Мэгуми
technique[1].damage: 700000
```

## 👩‍💻 Автор
**София Егорова** (@esvsophia)  
Студентка группы Б24-902

## 📜 Лицензия
Проект распространяется под лицензией **Apache License 2.0**.
