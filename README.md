# naive-integer-calculator

A very simple calculator that only support `+-*/` and `()` for integers

## todo

Currently negative integer is not supported in the input (the result can be negative)

## Usage

```bash
mvn clean package
echo '1+1' | java -jar target/naive-integer-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
```


### Some example
> echo '1+1' | java -jar target/naive-integer-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
> 2
> echo '1*2*3*4*5*6*7*8*9*10' | java -jar target/naive-integer-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
> 3628800
> echo '1 + 2 * 3 * 4' | java -jar target/naive-integer-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
> 25
> echo '5 - (1 + 2) * 3 * 2' | java -jar target/naive-integer-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
> -13
> echo '5 - (1 + (((2)))) * 3 * 2' | java -jar target/naive-integer-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
> -13
