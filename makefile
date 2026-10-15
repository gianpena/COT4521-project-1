.PHONY: run clean

Project1.class: *.java
	javac Project1.java

run: Project1.class
	java Project1

clean:
	rm -f *.class
