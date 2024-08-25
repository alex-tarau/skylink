package net.microflax.skylink.simulator;

public class SimulationException extends RuntimeException{

    public SimulationException(String message) {
        super(message);
    }

    public SimulationException(String message, Throwable cause) {
        super(message, cause);
    }
}
