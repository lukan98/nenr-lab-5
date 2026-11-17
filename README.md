# Neural Network and Drawing GUI (NENR Lab 5)

A feedforward neural network implemented from scratch in Java, with backpropagation training and a Swing interface for drawing and classifying examples. It was written for the **NENR (Fuzzy, Evolutionary and Neurocomputing)** course at FER, University of Zagreb.

## Contents

- Configurable multilayer perceptron (`NeuralNetwork`, `NeuralLayer`, `Neuron`)
- Sigmoid activation and mini-batch backpropagation
- Training data abstraction (`TrainingData`, `Point`)
- Swing GUI:
  - `ExampleCreator` — draw examples and export them to a file
  - `Classifier` — train on exported examples and classify newly drawn points
- Console demo (`Demo`) that trains a `1 → 6 → 1` network to approximate a quadratic function

## Requirements

- Java JDK 11 or newer (Swing is part of the JDK)
- Eclipse is optional; the repository includes Eclipse project metadata

## Build

From the project root:

```bash
mkdir -p bin
find src -name "*.java" -print0 | xargs -0 javac -d bin
```

## Run

Function-approximation demo:

```bash
java -cp bin hr.fer.zemris.neural.Demo
```

Example-drawing GUI:

```bash
java -cp bin hr.fer.zemris.GUI.ExampleCreator
```

Classification GUI:

```bash
java -cp bin hr.fer.zemris.GUI.Classifier
```

## Notes

The GUI classes use a hardcoded absolute path for the example file:

```text
/Users/.../NENR-workspace/lab5/examples.txt
```

Change the path in `GUI/ExampleCreator.java` and `GUI/Classifier.java` to a location on your machine before using the GUI.

## Project structure

```text
src/hr/fer/zemris/
├── neural/   # network, layers, neurons, training data and console demo
└── GUI/      # drawing board, example creator and classifier windows
```
