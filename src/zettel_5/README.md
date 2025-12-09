# 🏟️ Aufgabe 5: Spieler desselben Vereins finden

## 🎯 Ziel
Das Programm soll, basierend auf dem Namen eines Spielers, alle anderen Spieler ausgeben, die demselben Verein angehören.

## 💾 Datenstruktur zur Speicherung

Um die Spielerdaten effizient zu speichern und abzurufen, werden zwei separate Hashtabellen verwendet:

1.  **Hashtable 1: Name -> Verein** 
    * **Schlüssel**: Spielername (String)
    * **Wert**: Name des Vereins (String)
    * **Zweck:** Dient dazu, schnell den Verein eines eingegebenen Spielers zu ermitteln.

2.  **Hashtable 2: Verein -> Liste von Namen** 
    * **Schlüssel**: Name des Vereins (String)
    * **Wert**: Eine Liste von Spielernamen (z.B. eine `LinkedList`), die diesem Verein angehören. 
    * **Zweck:** Dient dazu, alle Spieler abzurufen, die zu einem bestimmten Verein gehören.

## ⚙️ Lösungsablauf

Der Ablauf zur Lösung der Aufgabe gliedert sich in folgende Schritte: 

1.  **Namen einlesen:** Lese den Namen des Spielers ein. 
2.  **Verein ermitteln:** Nutze **Hashtable 1**, um den Verein des eingegebenen Spielers zu erhalten. 
3.  **Spieler abrufen:** Nutze **Hashtable 2** mit dem gefundenen Vereinsnamen als Schlüssel, um die zugehörige Liste aller Spieler dieses Vereins zu erhalten. 
4.  **Liste ausgeben:** Gib die gefundene Liste der Spieler aus. 

## 🛠️ Spezifikationen der Hashtabelle

Beide Hashtabellen verwenden die folgenden Spezifikationen:

### 1. Kollisionsbehandlung
* **Methode:** **Geschlossenes Hashing** (**Open Addressing**) 
* **Sondierung:** **Double Hashing** 

### 2. Sondierungsfunktion
Die Sondierungsfunktion $h(x, i)$ ist definiert als: 

$$h(x,i):=(h_{1}(x)+i \cdot (1+h_{2}(x))) \bmod m \quad i \in [0, m-1]$$

### 3. Hash-Funktionen ($h_1$ und $h_2$)

Es wird eine universelle Familie von Hash-Funktionen $\mathcal{H}$ verwendet: 

```math
\mathcal{H} \coloneqq \{h_{a,b}:0 \le a,b < p\}
```

Die Funktion $h_{a,b}(x)$ ist definiert als: 

$$h_{a,b}(x) \coloneqq ((ax+b) \bmod p) \bmod m$$

* $p$ ist eine Primzahl. 
* $a$ und $b$ werden zufällig gewählt. 

Die beiden Hash-Funktionen $h_1(x)$ und $h_2(x)$ werden aus dieser universellen Familie abgeleitet: 

* $h_{1}(x) = h_{a_{1}, b_{1}}(x)$ 
* $h_{2}(x) = h_{a_{2}, b_{2}}(x)$ 

### 4. Tabellengröße ($m$)

* $m$ (die Größe der Hashtabelle) wird als **Primzahl** gewählt. 
* Die Lastfaktor $\alpha = \frac{n}{m}$ (wobei $n$ die Anzahl der Elemente ist) sollte im Bereich $0.5 \leq \alpha \leq  1$ liegen. 

### 5. Schleifen-Garantie

* **Frage:** Ist garantiert, dass die Sondierungsfunktion $h(x, i)$ alle Buckets (d.h. die Indizes $0$ bis $m-1$) durchläuft? 
* **Antwort:** Ja, dies ist garantiert **genau dann**, wenn der größte gemeinsame Teiler (ggT) von $h_2(x)$ und $m$ gleich 1 ist. 
    $$\gcd(h_{2}(x), m) = 1$$