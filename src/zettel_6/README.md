# Aufgabe 4: Jede Menge Sortierverfahren

```
javac -d bin -sourcepath src src/zettel_6/Main.java && java -cp bin zettel_6.Main
```

> **Hinweis:** Wegen des Sortierens großer Arrays kann das Programm sehr lange laufen. Um Wartezeiten zu vermeiden, in **Main.java:10** den Wert von *MAX* reduzieren, z.B. auf 1e2, und neu kompilieren.

## 🎯 Ziel
Diese Aufgabe dient dem Vergleich der Anzahl der **Schlüsselvergleiche** verschiedener Sortieralgorithmen.


## 📋 Aufgabenstellung
Die Implementierung umfasst zwei Hauptkomponenten gemäß den Anforderungen:
1. Eine Klasse `Sorter` mit verschiedenen Sortieralgorithmen.
2. Eine Testumgebung zur statistischen Auswertung der Performance unter verschiedenen Bedingungen.

## ⚙️ Implementierte Algorithmen
Jede Sortiermethode gibt die Anzahl der durchgeführten Vergleiche zurück:
* **Insertion-Sort**: Ineffizienteres, aber stabiles Verfahren für kleine Mengen.
* **Quicksort (Middle Pivot)**: Nutzt das mittlere Element als Pivot.
* **Randomisiertes Quicksort (Median Random 3 Pivot)**: Eine optimierte Version, die den Median der Werte drei zufälliger Pivot-Kandidaten zur Auswahl nutzt.

## 🧪 Test-Szenarien
* **Zufällig**: Eine **zufällige** Permutation der Menge $\{1, \dots, n\}$.
* **Best-Case**: Eine bereits **aufsteigend** sortierte Folge $(1, \dots, n)$.
* **Worst-Case**: Eine **absteigend** sortierte Folge $(n, \dots, 1)$.

> **Hinweis:** Beim randomisierten Quicksort werden mehrere Durchläufe pro Folge durchgeführt, um einen belastbaren Durchschnittswert der Vergleiche zu ermitteln.
