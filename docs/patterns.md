# Programming patterns used in the project

### Generative

##### A Simple Factory

This pattern is used when the creation of an object implies some kind of logic, and not just a few assignments. Then it 
makes sense to delegate the task to a dedicated factory, rather than repeating the same code everywhere. 

In my code, a kind of "simple factory" of playing fields is the primaryFillingOfPlayingField() method of the 
AbstractPlayingField class, which creates new playing fields by itself.

##### Factory Method

This pattern is useful for some general class processing, but the required subclasses are dynamically determined during 
runtime. That is, when the client does not know which subclass he may need.

In my code, a kind of "factory method" of game tiles is also the primaryFillingOfPlayingField() method of the
AbstractPlayingField class, because it creates game tiles of different classes on the field depending on their 
location.

##### Builder

This pattern is used when an object can have multiple properties and when you need to avoid the Telescoping 
constructor.

In my code, the "builder" pattern is used when filling in a field with game files in the methods 
primaryFillingOfPlayingField(), deleteReadyCombinations() and replaceMissingTile() of the AbstractPlayingField class. 
Instead of immediately creating a tile with all the information about it, piling up a lot of variables in the 
constructor, I first create all the tiles (so the constructor doesn't turn out to be too long), and then for each of 
the tiles, using the setTileNeighbors() method, I set its neighbors.

##### Prototype

This pattern is used when the required object is similar to an existing one or when creating from scratch is more 
expensive than cloning.

In my code, the "prototype" pattern is actively used in the AbstractPlayingField class. Every time one game tile has 
to be replaced by another, the second tile simply copies the information about the coordinates and neighbors of the 
first tile, since when replacing one tile with another, the coordinates and neighbors of this tile do not change. To 
use this pattern, there are many methods in my code like "copyFrom" and "copyTo".

### Structural

##### Adapter

In my code, the "adapter" pattern is used in the "Gameplay" class. Unfortunately, Swing and Threads work very poorly 
together, so I had to make an internal static adapter class, GameplaySwingWorker, which allows two incompatible 
classes to work together correctly.

##### Bridge

In my code, the "bridge" pattern is used in a tile package. There is an abstract class "AbstractTile" and 2 of its 
implementations "MissingTile" and "RegularTile". The "bridges" linking two separate class hierarchies are the 
tileCoordinates and tileNeighbors fields.

##### Facade

In my code, the entire "Gameplay" class is actually a "facade". It provides a simplified interface for a complex 
system of tiles and game fields.

### Behavioral

##### Command

The user of my game is "Invoker". He initiates the execution of "Commands" without knowing exactly how to execute 
them. By clicking on the buttons ("Invoker"), he sets the "Concrete Command", which are transmitted to the game field 
("Receiver"), which knows how to perform actions related to executing commands.

##### Mediator

In my code, the game field is an intermediary between gameplay and tiles. Thanks to it, the Gameplay class does not 
even suspect the implementation of the tile package, although it actively uses tiles.

##### Memento

In my game, you can press the buttons. Then their state will change from "unselected" to "selected". At the same time, 
this state can be canceled.

##### Observer

As soon as one of the tiles changes its state (for example, color or even changes its class), neighboring tiles 
immediately learn about it, and information is entered into them that their neighbor has changed. Thus, a changeable 
tile is an "object" that maintains a list of its "subordinates" ("observers") and automatically notifies neighboring 
tiles of any state change.
