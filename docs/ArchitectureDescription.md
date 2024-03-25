Architecture of the Three-in-a-Row Games project.

Tiles package.

The tiles package contains classes describing game tiles.
1. Tile: This abstract class is the base for all tiles on the playing field. It was created for the convenience of 
expanding and adding new tile types. So far, he has two heirs:
   - MissingTile: Represents the missing tile on the field.
   - RegularTile: Represents a regular colored tile that can be used to create Three-in-a-row and Three-in-a-column
  combinations.
2. TileTypes: An Enum that defines the possible types of all tiles, including the missing tile and various color tiles.
3. RegularTileTypes: An Enum that extends TileTypes by defining types of regular colored tiles.
4. TileCoordinates: An auxiliary class representing the coordinates of the tile on the playing field. It is used in
   almost all other classes.
5. TileNeighbors: An auxiliary class that defines tile neighbors on the playing field. It is also widely used in other
   classes.

The playingFields package.

The playingFields package contains classes related to playing fields.
1. PlayingField: This abstract class describes the main characteristics of the playing field. It provides a common
   interface for working with playing fields. It was created for the convenience of expanding and adding new types of
   playing fields.
2. SimplestPlayingField: A class inherited from PlayingField, which is a simple 8x8 playing field.

The gameplay package

The gameplay package contains the main class responsible for the gameplay.
1. Gameplay: This class is a gameplay controller. It contains the main function, which simulates the gameplay of the
   game "Three in a row".