## ADS Optionale Aufgeben 

Solutions for the optional tasks of ADS module 

### Folder Structure

- src/
- ├── zettel_x/
- ├──├── Main.java  - Solution
- ├──├── README.md        - Task-related info
    
### Running Solutions:

Java Version: **21.0.9**

- Clone repository:
```
git clone https://github.com/jamalalhneidi/ADS-Optionale-Aufgaben.git
```
- Navigate to the project directory:
```
cd ADS-Optionale-Aufgaben
````
- Compile and run the solution for a specific Zettel:
        
```
javac -d bin -sourcepath src src/zettel_{x}/Main.java && java -cp bin zettel_{x}.Main
```
Replace `{x}` with the desired Zettel number (e.g., `1`, `2`, etc.).
> Note: It is recommended to use VsCode with Java extension pack for easier execution.