package dev.prakash.productservicettsevening.prakashTest;

public class Calculator {
    int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        // throw exception if version is not an integer

            throw new IllegalArgumentException("Age must be an Integer.");


    }

    public int multiply(int a, int b) {
        return a * b;
    }
}