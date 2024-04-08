The project Three-in-a-Row Game for the subject "Programming technologies", 2024. The author of the project is Daria
Borzova, a 1st year student of the B05-311 MIPT group.

Three-in-a-Row is a genre of computer games. Games of this genre are characterized by the fact that their game world
consists of a table or grid of elements, and the player's task is to manipulate the elements in such a way that the
template combinations set by the game match, and after fulfilling the condition, the collected elements disappear.

At the moment, this project is the simplest Three-in-a-Row game, in which there is only one playing field, and the
only template combinations are the Three-in-a-Row combination and the Three-in-a-Column combination. However, in the
future I plan to develop this project, add levels, new combinations, and more.

Software Requirements:

* Language version: Java 14 or higher;
* Build System: Maven;
* Third-party libraries: not required, the standard library is sufficient;
* Compiler: javac, which comes with the JDK.

Commands to run the project:

```cmd
- mvn clean
- mvn compile
- mvn package
```

Commands for building a project:

```cmd
- mvn install
- cd target
- java -jar project-8-1.0-SNAPSHOT.jar
```

Main links to parts of the project:

* [Architecture description](https://gitlab.akhcheck.ru/tp2024-projects/project-8/-/blob/dev/docs/ArchitectureDescription.md?ref_type=heads)
* [UML diagram](https://gitlab.akhcheck.ru/tp2024-projects/project-8/-/blob/dev/docs/ThreeInARowGameUML.png?ref_type=heads)
* [Use case diagram](https://gitlab.akhcheck.ru/tp2024-projects/project-8/-/blob/dev/docs/UseCaseDiagram.png?ref_type=heads)
* [Code](https://gitlab.akhcheck.ru/tp2024-projects/project-8/-/tree/dev/src/main/java?ref_type=heads)