# Three-in-a-row

### Description of the project

The project Three-in-a-Row Game for the subject "Programming technologies", 2024. The author of the project is Daria
Borzova, a 1st year student of the B05-311 MIPT group.

Three-in-a-Row is a genre of computer games. Games of this genre are characterized by the fact that their game world
consists of a table or grid of elements, and the player's task is to manipulate the elements in such a way that the
template combinations set by the game match, and after fulfilling the condition, the collected elements disappear.

At the moment, this project is a simple Three-in-a-Row game, in which there is only one playing field, but there are 
many game combinations - "Three/four/five-in-a-row", "Three/four/five-in-a-column", "Four-in-a-square" and all 
possible combinations of them. In the future, I plan to develop this project, add training, levels, tools, and improve 
the frontend

### Software Requirements

* Language version: Java 14 or higher;
* Build System: Maven 3.6.0 or higher;
* Third-party libraries: not required, the standard library is sufficient;
* Compiler: javac 14 or higher, which comes with the JDK 14 or higher.

### Commands to building the project

```cmd
mvn clean package
```

### Commands for run a project:

```cmd
cd target
java -jar project-8-1.0.0.jar
```

###### or

```cmd
java -jar target/project-8-1.0.0.jar
```

### Main links to parts of the project

* [Documents](./docs)
* [Architecture description](./docs/ArchitectureDescription.md)
* [Java features used in the project](./docs/features.md)
* [Programming patterns used in the project](./docs/patterns.md)
* [UML diagram](./docs/UML.png)
* [Use case diagram](./docs/UseCaseDiagram.png)
* [Code style](./rulesets/checkstyle.xml)
* [Code](./src/main/java)
* [Images](./src/main/resources/icons)
* [Information about the configuration and details of the project](./pom.xml)
