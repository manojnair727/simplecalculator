# simplecalculator
Repository to create a simple calculator. 


first run command 
mvn clean package 

above command will create the jar calculator.jar 

You can run the program using the command
java -Dlog-level={LOG.LEVEL} -jar calculator.jar "{expression}"

for eg: 
java -Dlog-level=INFO -jar calculator.jar "add(2,1)"

Log gets printed in both console as well as log file. I could have removed console logging. however decided to keep both.

Expression is an arithematic expression that accepts 5 kinds of operation add, sub, div, mult and let. hence these are all 
reserved words in the expression and cant be used as variable name.
Calculator expects only single expression at a time. if multiple expression needs to be evaluated then you need to execute it that many times
log-level is not a mandatory option. if not given then it will take the configuration from logback.xml




