# 🎴 UNO Game (Java)

A command-line implementation of the classic UNO card game developed in Java.

## Features

* Human player versus AI-controlled bots
* Complete UNO game logic
* Special cards support:

  * Reverse
  * Skip
  * Draw Two
  * Wild
  * Wild Draw Four
* Turn and direction management
* Score calculation
* Command-line interface
* Object-oriented architecture

## Technologies

* Java
* Object-Oriented Programming (OOP)
* Collections Framework
* Exception Handling

## Project Structure

```text
src/
└── uno/
    ├── cartes/
    ├── dialogue/
    ├── erreur/
    ├── jeu/
    ├── joueur/
    └── Main.java
```

## Compile

```bash
javac -d out src/uno/**/*.java src/uno/*.java
```

## Run

```bash
java -cp out uno.Main
```

## How to Play

1. Choose the number of players (2–10).
2. The human player competes against AI bots.
3. Play a card matching the color or value of the top card on the discard pile.
4. Use special cards strategically.
5. The game ends when a player has no cards left or the draw pile is empty.

## Concepts Used

* Inheritance
* Polymorphism
* Abstract Classes
* Design Patterns
* Exception Handling
* Game State Management

## Author

Java project developed as part of learning and practicing object-oriented programming and game development concepts.
