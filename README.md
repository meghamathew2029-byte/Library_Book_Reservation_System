# Library Book Reservation and Waiting Queue System

A browser-based library project written in Java. The Java program generates the web interface itself; there are no separate HTML or CSS files. The page includes embedded HTML/CSS strings because a browser needs web content to display.

## Features
- Add book details (ID, title, author)
- Display book inventory and availability
- Search by book ID or title
- Issue books to students
- Return books
- Reserve issued books
- FIFO waiting queue per book
- When a book is returned, it is automatically issued to the first waiting student

## Data structures
- Array (`Book[]`) stores book records.
- Queue (`LinkedList<String>`) stores waiting students for each book.

## Run in GitHub Codespaces or another Java environment
The environment needs Java 17 or newer.

```bash
javac LibrarySystemWeb.java
java LibrarySystemWeb
```

Open the forwarded port 8080 in the environment's Ports panel. Locally, open `http://localhost:8080`.

## Notes
- Data is stored in memory and resets when the application stops.
- This is a student project/demo, not a production library system.
- Java 17+ is recommended because the source uses text blocks.
