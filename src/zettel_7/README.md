# Aufgabe 5: Implementation von Heapsort

*Ergänzung zu der Aufgabe 4: Implementation von Sortieralgorithmen*

```
javac -d bin -sourcepath src src/zettel_7/Main.java && java -cp bin zettel_7.Main
```

<!-- > **Hinweis:** Wegen des Sortierens großer Arrays kann das Programm sehr lange laufen. Um Wartezeiten zu vermeiden, in **Main.java:10** den Wert von *MAX* reduzieren, z.B. auf 1e2, und neu kompilieren. -->


## ⚙️ Anpassungen:
* **Heapsort**: Nutzt eine `heapify`-Methode zur Erstellung und Aufrechterhaltung der Heap-Struktur.
### 🔑 Spezialisierte Schlüssel (Stabilitäts-Test)
Um das Verhalten der Algorithmen genauer zu untersuchen, werden zwei Klassen implementiert:
* **SimpleKey**: Vergleicht lediglich das Feld `key` (Integer).
* **ExtendedKey**: Besitzt zusätzlich ein Feld `pos` (ursprünglicher Index). Der Vergleich erfolgt primär über `key` und sekundär über `pos` mit niedrigerer Priorität.
