# 🕹️ Tic-Tac-Toe Web API (Java Spring Boot)

This is a **configurable**, single-player Tic-Tac-Toe game built with **Java Spring Boot**.
- You can play as **'X' or 'O'**, and the **AI will make random moves** after yours.
- The board size is **configurable** (3x3, 5x5, 9x9, etc.).
- API only uses **GET requests**, so it can be accessed easily in the **browser**.

---

## 🚀 Features
✅ Play against **AI** (random moves).  
✅ Fully **configurable board size** (**3x3 to 10x10**).  
✅ **Works in browser** (only GET requests).  
✅ **Returns the board** after each move.  
✅ **Game reset & board size update support**.

---

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 3+**
- **REST API (GET requests)**
- **Lombok** (for reducing boilerplate code)

---

## 🔧 Setup & Run Locally

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/suprafid/tictactoe.git
cd tictactoe
```

### 2️⃣ Build & Run the Project
```sh
./gradlew bootRun
```

🎮 How to Play (API Endpoints)
1️⃣ Start a New Game
Start a game by choosing 'X' or 'O'. The AI will play as the opposite symbol.
```sh
curl -X GET "http://localhost:8080/tictactoe/start?player=X"
```
📌 Open in browser:
👉 http://localhost:8080/tictactoe/start?player=X
(Change X to O if you want to play as O.)

✅ Response Example
```json
{
  "message": "Game started with board size: 3x3",
  "board": [
    ["-", "-", "-"],
    ["-", "-", "-"],
    ["-", "-", "-"]
  ]
}
```
Step 2: Make a Move
After your move, AI will make a random move, and the board will be returned.

✅ Request
```sh
curl -X GET "http://localhost:8080/tictactoe/move?row=1&col=1"
```
📌 Open in browser:
👉 http://localhost:8080/tictactoe/move?row=1&col=1

🔄 Response Example
```json
{
  "message": "Player moved",
  "board": [
    ["-", "-", "O"],
    ["-", "X", "-"],
    ["-", "-", "-"]
  ]
}
```
Step 3: View the Current Board
Retrieve the current game board.

✅ Request
```sh
curl -X GET "http://localhost:8080/tictactoe/board"
```
📌 Open in browser:
👉 http://localhost:8080/tictactoe/board

🔄 Response Example
```json
{
  "board": [
    ["-", "-", "O"],
    ["-", "X", "-"],
    ["-", "-", "-"]
  ]
}
```

Step 4: Reset the Game
Resets the board and allows a new game to start.

✅ Request
```sh
curl -X GET "http://localhost:8080/tictactoe/reset"
```
📌 Open in browser:
👉 http://localhost:8080/tictactoe/reset

🔄 Response Example
```json
{
  "message": "Game has been reset."
}
```

Step 5: Change Board Size
Update the board size dynamically (must be between 3 and 10).

✅ Request
```sh
curl -X GET "http://localhost:8080/tictactoe/updateBoardSize?size=5"
```
📌 Open in browser:
👉 http://localhost:8080/tictactoe/updateBoardSize?size=5

(Change size=5 to any value between 3-10.)

🔄 Response Example
```json
{
  "message": "Board size updated to 5x5."
}
```


🎯 Rules of the Game
Player chooses 'X' or 'O' at the start.
Player moves first, then AI makes a random move.
Game ends when a player wins or the board is full.
No hardcoded board size—you can change it dynamically.
